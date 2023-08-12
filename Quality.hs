module Quality (Quality, newQ, capacityQ, delayQ) where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ = Qua

capacityQ :: Quality -> Int
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float
delayQ (Qua _ _ delay) = delay


basicQuality :: Quality
basicQuality = newQ "Basic" 10 0.5

basicCapacity :: Int
basicCapacity = capacityQ basicQuality
basicDelay :: Float
basicDelay = delayQ basicQuality


premiumQuality :: Quality
premiumQuality = newQ "Premium" 100 0.1

areEqual :: Bool
areEqual = basicQuality == premiumQuality


