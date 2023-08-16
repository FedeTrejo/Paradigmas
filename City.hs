module City ( City, newC, nameC, distanceC )
    where
import Point


data City = Cit String Point deriving (Eq, Show)


newC :: String -> Point -> City
newC = Cit

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ p1) (Cit _ p2) = difP p1 p2
