module City ( City, newC, nameC, distanceC, bsAs, stgo ) where --PREGUNTAR AL PROFE QUE CAMBIAMOS ESTA LINEA, PARA NO REPETIR CODIGO

import Point


data City = Cit String Point deriving (Eq, Show)


newC :: String -> Point -> City
newC = Cit

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ p1) (Cit _ p2) = difP p1 p2


point1 :: Point
point1 = newP 1 2
point2 :: Point
point2 = newP 3 4

bsAs :: City
bsAs = newC "Buenos Aires" point1
stgo :: City
stgo = newC "Santiago" point2

distanceBetweenCities :: Float
distanceBetweenCities = distanceC bsAs stgo