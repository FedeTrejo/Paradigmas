module Region ( Region, newR, foundR, linkR, connectedR, linkedR, delayR, availableCapacityForR, tunelR)

   where

import City
import Link
import Tunel
import Quality

data Region = Reg [City] [Link] [Tunel]
  deriving (Eq)

instance Show Region where
  show (Reg cities links tunnels) = 
    "Region: " ++
    show (length cities) ++ " cities, " ++ 
    show (length links) ++ " links, " ++
    show (length tunnels) ++ " tunnels"

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunnels) city
  | city `elem` cities = error "City already exists"
  | otherwise = Reg (city:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region
linkR (Reg cities links tunnels) c1 c2 q
  | c1 `elem` cities && c2 `elem` cities =
      Reg cities (newL c1 c2 q : links) tunnels

  | otherwise =
      error "Cities don't exist in the region"

findLink :: Region -> City -> City -> Link
findLink (Reg _ links _) city1 city2 = findMatchingLink links
  where
    findMatchingLink [] = error "There is no valid link between cities"
    findMatchingLink (l:ls)
      | linksL city1 city2 l = l
      | otherwise = findMatchingLink ls

regionToLinks :: Region -> [Link]
regionToLinks (Reg _ links _) = links

tunelR :: Region -> [City] -> Region
tunelR region [] = region
tunelR region [_] = region
tunelR region c@(c1:c2:restCities) =
  if hasAvailableCapacity region c
    then tunelR (placeTunnel region link newTunnel) (c2 : restCities)
    else error "There is no available capacity on the link between those cities"
  where
    link = findLink region c1 c2
    newTunnel = newT (regionToLinks region)  

placeTunnel :: Region -> Link -> Tunel -> Region
placeTunnel (Reg cities links tunnels) oldLink newTunel =
  Reg cities links (placeT tunnels)
  where
    placeT [] = [newTunel]  
    placeT (t:tunnels)
      | usesT oldLink t = placeT tunnels
      | otherwise = t : placeT tunnels

countLinkInTunnels :: Link -> [Tunel] -> Int
countLinkInTunnels _ [] = 0
countLinkInTunnels link (t:tunnels)
  | usesT link t = 1 + countLinkInTunnels link tunnels
  | otherwise = countLinkInTunnels link tunnels

usedCapacity :: Link -> Region -> Int
usedCapacity link (Reg _ _ tunnels) = countLinkInTunnels link tunnels

hasAvailableCapacity :: Region -> [City] -> Bool
hasAvailableCapacity region (c:cities) 
   | length cities == 1 = availableCapacityForR region c (head cities) >= 1
   | otherwise = availableCapacityForR region c (head cities) >= 1 && hasAvailableCapacity region cities

connectedR :: Region -> City -> City -> Bool
connectedR (Reg cities links tunnels) c1 c2 =
  linksConnected || tunnelsConnected

  where 
    linksConnected = any (linksL c1 c2) links
    
    tunnelsConnected = foldr (\tunel acc -> acc || connectsT c1 c2 tunel) False tunnels

linkedR :: Region -> City -> City -> Bool 
linkedR (Reg cities [] tunnels) c1 c2 = False
linkedR (Reg cities (l:links) tunnels) c1 c2 =  if (linksL c1 c2 l) then True
                                                else linkedR (Reg cities links tunnels) c1 c2

delayR :: Region -> City -> City -> Float
delayR (Reg _ links tunnels) c1 c2
    | isDirectLink = delayL directLink
    | isTunneled = sum (map delayT connectingTunnels) + tunnelDelay
    | otherwise = error "There is no direct link or tunnel between those cities"
  where
      isDirectLink = isLinkAvailable links c1 c2 || isLinkAvailable links c2 c1
     
      isLinkAvailable [] _ _ = False
      isLinkAvailable (l:ls) city1 city2
          | linksL city1 city2 l = True
          | otherwise = isLinkAvailable ls city1 city2
     
      directLink = getDirectLink links c1 c2
     
      getDirectLink [] _ _ = error "Direct link not found"
      getDirectLink (l:ls) city1 city2
          | linksL city1 city2 l = l
          | otherwise = getDirectLink ls city1 city2
     
      isTunneled = isTunnelAvailable tunnels c1 c2 || isTunnelAvailable tunnels c2 c1
     
      isTunnelAvailable [] _ _ = False
      isTunnelAvailable (t:ts) city1 city2
          | connectsT city1 city2 t = True
          | otherwise = isTunnelAvailable ts city1 city2
     
      connectingTunnels = filter (\t -> connectsT c1 c2 t || connectsT c2 c1 t) tunnels
      tunnelDelay = sum [delayT t | t <- tunnels, not (connectsT c1 c2 t || connectsT c2 c1 t)]

availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR (Reg cities [] _) _ _ = error "No links between those cities in the region"
availableCapacityForR r@(Reg cities (l:links) tunnels) c1 c2 =
  if linksL c1 c2 l
    then capacityL l - (usedCapacity l r)
    else availableCapacityForR (Reg cities links tunnels) c1 c2