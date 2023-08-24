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

--Función auxiliar para encontrar el enlace entre dos ciudades
findLink :: Region -> City -> City -> Link
findLink (Reg _ links _) city1 city2 = findMatchingLink links
  where
    findMatchingLink [] = error "No existe un enlace válido entre las ciudades."
    findMatchingLink (l:ls)
      | linksL city1 city2 l = l
      | otherwise = findMatchingLink ls

--Funcion auxiliar para acceder a los links de una region
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

--Agregar el tunel a la lista de tuneles de la region
placeTunnel :: Region -> Link -> Tunel -> Region
placeTunnel (Reg cities links tunnels) oldLink newTunel =
  Reg cities links (placeT tunnels)
  where
    placeT [] = [newTunel]  
    placeT (t:tunnels)
      | usesT oldLink t = placeT tunnels
      | otherwise = t : placeT tunnels

--Función auxiliar para contar la cantidad de veces que aparece un enlace en una lista de túneles
countLinkInTunnels :: Link -> [Tunel] -> Int
countLinkInTunnels _ [] = 0
countLinkInTunnels link (t:tunnels)
  | usesT link t = 1 + countLinkInTunnels link tunnels
  | otherwise = countLinkInTunnels link tunnels

--Función para obtener la capacidad utilizada de un enlace en una región
usedCapacity :: Link -> Region -> Int
usedCapacity link (Reg _ _ tunnels) = countLinkInTunnels link tunnels

--Función para verificar si un enlace tiene capacidad disponible, mayor a 0
hasAvailableCapacity :: Link -> Region -> Bool
hasAvailableCapacity link region = availableCapacityForR region (getCity1 link) (getCity2 link) > 0

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
availableCapacityForR r@(Reg cities (l:links) tunnels) c1 c2 =
  if linksL c1 c2 l
    then capacityL l - (usedCapacity l r)
    else availableCapacityForR (Reg cities links tunnels) c1 c2