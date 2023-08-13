--module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
module Region ( Region, newR, foundR, linkR, connectedR)

   where

import City
import Link
import Tunel
import Quality


data Region = Reg [City] [Link] [Tunel]


newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunnels) city
  | city `elem` cities = error "City already exists" --COMO CARAJO HACEMOS UNA VARIABLE QUE MUESTRE ESTE CODIGO DE ERROR
  | otherwise = Reg (city:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region
linkR (Reg cities links tunnels) c1 c2 q =
  Reg cities (newL c1 c2 q:links) tunnels

--tunelR :: Region -> [ City ] -> Region

connectedR :: Region -> City -> City -> Bool
connectedR (Reg _ links _) c1 c2 =
  any (linksL c1 c2) links -- @fede La función any en Haskell permite verificar si algún elemento de una lista cumple cierta condición.


--linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades


regBA :: Region
regBA = foundR newR bsAs
--PREGUNTAR SI TODAS LAS FUNCIONES  TENDRIAN QUE TENER SHOW
linkBaSt :: Region
linkBaSt = linkR regBA bsAs stgo midQ

conRBaSt :: Bool
conRBaSt = connectedR linkBaSt bsAs stgo
--PREGUNTAR SI HACEMOS MAS VARIABLES PARA HACER REGIONES MAS COMPLEJAS

