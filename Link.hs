module Link (Link, newL, linksL, connectsL, capacityL, delayL) where

import Quality 
import City --PREGUNTAR SI PARA AHORRA RECURSOS IMPORTAMOS SOLAMENTE LAS FUNCIONES QUE USAMOS O IMPORTAMOS TODO PARA TENER CODIGO MAS ROBUSTO

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link
newL = Lin
   
connectsL :: City -> Link -> Bool
connectsL city (Lin city1 city2 _) = city == city1 || city == city2

linksL :: City -> City -> Link -> Bool
linksL city1 city2 (Lin c1 c2 _) = c1 == city1 && c2 == city2 --DEBERIAMOS IMPLEMENTARLO AL REVES?

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float
delayL (Lin _ _ quality) = delayQ quality


linkBASP = newL bsAs stgo midQ

baInLink = connectsL bsAs linkBASP

citiesLinked = linksL bsAs stgo linkBASP

linkCapacity = capacityL linkBASP

linkDelay = delayL linkBASP