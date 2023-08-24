module Link ( Link, newL, linksL, connectsL, capacityL, delayL, getCity1, getCity2 )
    where
import Quality 
import City

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link
newL = Lin
   
connectsL :: City -> Link -> Bool
connectsL city (Lin city1 city2 _) = city == city1 || city == city2

linksL :: City -> City -> Link -> Bool
linksL city1 city2 (Lin c1 c2 _) = (c1 == city1 && c2 == city2) || (c1 == city2 && c2 == city1)

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float
delayL (Lin _ _ quality) = delayQ quality

--Funciones auxiliares para acceder a las ciudades de un link "getters" sin constructores
getCity1 :: Link -> City
getCity1 (Lin city1 _ _) = city1

getCity2 :: Link -> City
getCity2 (Lin _ city2 _) = city2