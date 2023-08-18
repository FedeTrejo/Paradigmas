module Tunel ( Tunel, newT, connectsT, usesT, delayT ) where

import Link
import City
import Point
import Quality

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 (Tun links) = connectsL city1 (head links) && connectsL city2 (last links)

usesT :: Link -> Tunel -> Bool
usesT _ (Tun []) = False
usesT l1 (Tun (x:xs)) = (x == l1) || usesT l1 (Tun xs)

delayT :: Tunel -> Float
delayT (Tun links) = sum [delayL link | link <- links]
