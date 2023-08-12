--module City ( City, newC, nameC, distanceC )
module City ( City)--, newC, nameC, distanceC )

   where

import Point
data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC name point = Cit name point

nameC :: City -> String
nameC (Cit name point) = name

distanceC :: City -> City -> Float
distanceC (Cit name1 point1) (Cit name2 point2) = difP point1 point2

--TESTING

punto1 = newP 1 2
punto2 = newP 3 4

city1 = newC "Buenos Aires" punto1
city2 = newC "Santiago" punto2

distance_between_Santiago_and_BuenosAires = distanceC city1 city2