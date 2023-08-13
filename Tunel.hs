module Tunel ( Tunel, newT, connectsT, usesT, delayT ) where

import Link
import City
import Point
import Quality

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT _ _ (Tun []) =  False
connectsT c1 c2 (Tun (x:xs)) = (connectsL c1 x || connectsL c2 x) || connectsT c1 c2 (Tun xs)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT _ (Tun []) = False
usesT l1 (Tun (x:xs)) = (x == l1) || usesT l1 (Tun xs)

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum [delayL link | link <- links]


tu :: City
tu = newC "Tucuman" (newP 5 6)

sa :: City
sa = newC "Salta" (newP 7 8)

l2 :: Link
l2 = newL tu sa vipQ

t1 :: Tunel
t1 = newT [linkBASP, l2]

conTBaSt :: Bool
conTBaSt = connectsT bsAs stgo t1

usesl2 :: Bool
usesl2 = usesT l2 t1

totDelay :: Float
totDelay = delayT t1
