module Test (bsAs, stgo, cordoba, medium, vip, linBsSt, linStBs, linkCoSg, bsInLin, capBsSt, t1, tUsesLin, tDelay, argentina, argentinaConnected )
  where

import City
import Link
import Point 
import Quality
import Region 
import Tunel

bsAs = newC "Buenos Aires" (newP 1 2)
rosario = newC "Rosario" (newP 3 4)
cordoba = newC "Cordoba" (newP 5 6)
stgo = newC "Santiago" (newP 7 8)

medium = newQ "Medium" 10 0.5
vip = newQ "Vip" 20 0.2

linBsSt = newL bsAs stgo medium
linStBs = newL stgo bsAs vip 

bsInLin = connectsL bsAs linBsSt
capBsSt = capacityL linBsSt

t1 = newT [linBsSt, linStBs]

tUsesLin = usesT linBsSt t1 
tDelay = delayT t1

linBsRo = newL bsAs rosario vip
linRoCo = newL rosario cordoba vip 
linkCoSg = newL cordoba stgo medium

argentina = newR

argentina1 = foundR argentina bsAs
argentina2 = foundR argentina1 rosario
argentina3 = foundR argentina2 cordoba
argentina4 = foundR argentina3 stgo

argentina5 = linkR argentina4 bsAs rosario vip
argentina6 = linkR argentina5 rosario cordoba vip
argentina7 = linkR argentina6 stgo cordoba medium

linkedRoCo = linkedR argentina7 rosario cordoba

capacityCoSt = availableCapacityForR argentina7 stgo cordoba

argentinaConnected = tunelR argentina7 [bsAs, rosario, cordoba, stgo]
  
testConnected = connectedR argentinaConnected cordoba stgo

capInReg = availableCapacityForR argentinaConnected cordoba stgo

delayBsStinREGION = delayR argentinaConnected stgo bsAs
