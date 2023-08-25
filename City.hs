module City where

import Point

data City = Cit String Point
  deriving (Eq)

instance Show City where
  show :: City -> String
  show (Cit name point) = 
    name ++ " (" ++ show point ++ ")"

newC :: String -> Point -> City  
newC = Cit

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ p1) (Cit _ p2) = difP p1 p2