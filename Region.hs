module Region ( Region, newR, foundR, linkR, connectedR, linkedR, delayR, availableCapacityForR, tunelR)

   where

import City
import Link
import Tunel
import Quality


data Region = Reg [City] [Link] [Tunel] deriving Show


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
    findMatchingLink [] = error "No existe un enlace válido entre las ciudades."
    findMatchingLink (l:ls)
      | linksL city1 city2 l = l
      | otherwise = findMatchingLink ls

regionToLinks :: Region -> [Link]
regionToLinks (Reg _ links _) = links

tunelR :: Region -> [City] -> Region
tunelR region [] = region
tunelR region [_] = region
tunelR region (c1:c2:restCities) =
  if hasAvailableCapacity link region
    then tunelR (placeTunnel region link newTunnel) (c2 : restCities)
    else error "No hay capacidad disponible en el enlace entre las ciudades."
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

hasAvailableCapacity :: Link -> Region -> Bool
hasAvailableCapacity link region = availableCapacityForR region (getCity1 link) (getCity2 link) > 0

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
delayR (Reg cities [] tunnels) _ _ = error "No links between those cities in the region"
delayR (Reg cities (l:links) tunnels) c1 c2 =
  if linksL c1 c2 l
    then delayL l
    else delayR (Reg cities links tunnels) c1 c2

availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR (Reg cities [] _) _ _ = error "No links between those cities in the region"
availableCapacityForR r@(Reg cities (l:links) tunnels) c1 c2 =
  if linksL c1 c2 l
    then capacityL l - (usedCapacity l r)
    else availableCapacityForR (Reg cities links tunnels) c1 c2