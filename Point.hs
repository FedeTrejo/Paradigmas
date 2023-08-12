module Point (Point, newP, difP) where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi

difP :: Point -> Point -> Float
difP (Poi x1 y1) (Poi x2 y2) = sqrt (fromIntegral ((x1 - x2)^2 + (y1 - y2)^2))


origin :: Point
origin = newP 0 0

distance :: Float
distance = difP origin (newP 3 4)
