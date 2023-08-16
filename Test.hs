module Test (bsAs, stgo, medium, vip, linBsSt, linStBs, bsInLin, capBsSt, reg0, reg1, reg2, t1, tUsesLin, tDelay)
  where

import City
import Link
import Point 
import Quality
import Region
import Tunel

bsAs :: City
bsAs = newC "Buenos Aires" (newP 1 2)
stgo :: City
stgo = newC "Santiago" (newP 3 4)

medium :: Quality
medium = newQ "Medium" 10 0.5
vip :: Quality
vip = newQ "Vip" 20 0.2

linBsSt :: Link
linBsSt = newL bsAs stgo medium
linStBs :: Link
linStBs = newL stgo bsAs vip 

bsInLin :: Bool
bsInLin = connectsL bsAs linBsSt
capBsSt :: Int
capBsSt = capacityL linBsSt

reg0 :: Region
reg0 = newR
reg1 :: Region
reg1 = foundR reg0 bsAs
reg2 :: Region
reg2 = linkR reg1 bsAs stgo medium

-- bsAsInReg :: Bool AVERIGUAR PORQUE SALTA LA EXCEPCION DE LINKR
-- bsAsInReg = connectedR reg2 bsAs stgo a 
-- capInReg :: Int AVERIGUAR PORQUE SALTA LA EXCEPCION DE LINKR
-- capInReg = availableCapacityForR reg2 bsAs stgo

t1 :: Tunel
t1 = newT [linBsSt, linStBs]

tUsesLin :: Bool
tUsesLin = usesT linBsSt t1 
tDelay :: Float
tDelay = delayT t1