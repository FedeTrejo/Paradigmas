module Exception where

import City  
import Link
import Point
import Region
import Tunel 
import Test

noDelay :: Float
noDelay = delayR argentina bsAs stgo  

noCap :: Int
noCap = availableCapacityForR argentina bsAs stgo

arg2 :: Region  
arg2 = foundR newR bsAs

dupCity :: Region
dupCity = foundR arg2 bsAs

linkNotInReg :: Region  
linkNotInReg = linkR arg2 bsAs stgo vip