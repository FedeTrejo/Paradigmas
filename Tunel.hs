--module Tunel ( Tunel, newT, connectsT, usesT, delayT )
module Tunel --( Tunel, newT, connectsT, usesT, delayT )


   where

import Link
data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT _ _ [] =  False
connectsT c1 c2 (l:rest) = if connectsL c1 link || connectsL c2 link then True
                           else connectsT c1 c2 rest

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT _ [] = False
usesT l1 (l:rest) = if l == l1 then True
                     else usesT l1 rest

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum [delayL link | link <- links]

--EJEMPLO DE TESTING

tucuman :: City
tucuman = newC "Tucuman" (newP 5 6)

salta :: City
salta = newC "Salta" (newP 7 8)

link2 = newL tucuman salta vipQ

tunnel = newT [linkBASP, link2]

connectedBSAS_STGO = connectsT bsAs stgo tunnel

usesLink2 = usesT link2 tunnel

totalDelay = delayT tunnel


