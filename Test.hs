module Test (bsAs, stgo, cordoba, medium, vip, linBsSt, linStBs, linkCoSg, bsInLin, capBsSt, t1, tUsesLin, tDelay, argentina, argentinaConnected )
  where

import City
import Link
import Point 
import Quality
import Region 
import Tunel

bsAs :: City
bsAs = newC "Buenos Aires" (newP 1 2)
rosario :: City
rosario = newC "Rosario" (newP 3 4)
cordoba :: City
cordoba = newC "Cordoba" (newP 5 6)
stgo :: City
stgo = newC "Santiago" (newP 7 8)

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

t1 :: Tunel
t1 = newT [linBsSt, linStBs]

tUsesLin :: Bool
tUsesLin = usesT linBsSt t1 
tDelay :: Float
tDelay = delayT t1

linBsRo :: Link
linBsRo = newL bsAs rosario vip
linRoCo :: Link
linRoCo = newL rosario cordoba vip 
linkCoSg :: Link
linkCoSg = newL cordoba stgo medium

argentina :: Region
argentina = newR

argentina1 :: Region
argentina1 = foundR argentina bsAs
argentina2 :: Region
argentina2 = foundR argentina1 rosario
argentina3 :: Region
argentina3 = foundR argentina2 cordoba
argentina4 :: Region
argentina4 = foundR argentina3 stgo

argentina5 :: Region
argentina5 = linkR argentina4 bsAs rosario vip
argentina6 :: Region
argentina6 = linkR argentina5 rosario cordoba vip
argentina7 :: Region
argentina7 = linkR argentina6 stgo cordoba medium

linkedRoCo :: Bool
linkedRoCo = linkedR argentina7 rosario cordoba

capacityCoSt :: Int
capacityCoSt = availableCapacityForR argentina7 stgo cordoba

argentinaConnected :: Region  
argentinaConnected = 
  let region = linkR argentina7 bsAs stgo medium
  in tunelR region [bsAs, rosario, cordoba, stgo]
  
testConnected :: Bool
testConnected = connectedR argentinaConnected bsAs stgo

capInReg :: Int 
capInReg = availableCapacityForR argentinaConnected cordoba stgo

delayBsAsStgo :: Float
delayBsAsStgo = delayR argentina7 stgo cordoba
