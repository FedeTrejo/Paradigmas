module Quality (Quality, newQ, capacityQ, delayQ, midQ, vipQ) where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ = Qua

capacityQ :: Quality -> Int
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float
delayQ (Qua _ _ delay) = delay


midQ :: Quality
midQ = newQ "Medium" 50 0.25

midC :: Int
midC = capacityQ midQ
midD :: Float
midD = delayQ midQ


vipQ :: Quality
vipQ = newQ "Vip" 100 0.1

same :: Bool
same = midQ == vipQ


