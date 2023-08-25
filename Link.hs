module Link ( Link, newL, linksL, connectsL, capacityL, delayL)
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
