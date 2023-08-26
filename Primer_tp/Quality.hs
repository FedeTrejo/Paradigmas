module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float
  deriving (Eq)
  
instance Show Quality where
  show (Qua name capacity delay) = 
    name ++ " (" ++ show capacity ++ ", " ++ show delay ++ ")"

newQ :: String -> Int -> Float -> Quality
newQ = Qua

capacityQ :: Quality -> Int  
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float
delayQ (Qua _ _ delay) = delay