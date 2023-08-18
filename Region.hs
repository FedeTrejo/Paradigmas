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

tunelR :: Region -> [ City ] -> Region
tunelR region@(Reg cities links tunnels) orderedCities
  | all (`elem` cities) orderedCities && allCitiesLinked = Reg cities links (newTunnel:tunnels)
  | otherwise = error "Cities are not ordered or cities not linked"
  where
    allCitiesLinked = all (\(c1, c2) -> any (\l -> linksL c1 c2 l) links) (zip orderedCities (tail orderedCities))
    newLinks = [l | (c1, c2) <- zip orderedCities (tail orderedCities), l <- links, linksL c1 c2 l]
    newTunnel = newT newLinks

connectedR :: Region -> City -> City -> Bool
connectedR (Reg cities links tunels) city1 city2 =
    foldr (\tunel tunels -> tunels || connectsT city1 city2 tunel) False tunels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities [] tunnels) c1 c2 = False
linkedR (Reg cities (l:links) tunnels) c1 c2 =  if (linksL c1 c2 l) then True
                                                else linkedR (Reg cities links tunnels) c1 c2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities [] tunnels) _ _ = error "No links between those cities in the region"
delayR (Reg cities (l:links) tunnels) c1 c2 =
  if linksL c1 c2 l
    then delayL l
    else delayR (Reg cities links tunnels) c1 c2

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cities [] _) _ _ = error "No links between those cities in the region"
availableCapacityForR (Reg cities (l:links) tunnels) c1 c2 =
  if linksL c1 c2 l
    then capacityL l
    else availableCapacityForR (Reg cities links tunnels) c1 c2