module FalseTests where

import Link
import Test
import Region
import Tunel

linNoCord :: Bool
linNoCord = linksL stgo cordoba linBsSt

cordNotInLink :: Bool
cordNotInLink = connectsL cordoba linBsSt  

tunBs = newT [linBsSt]

cordNotInTun :: Bool 
cordNotInTun = connectsT cordoba bsAs tunBs

tunNotUseLink :: Bool
tunNotUseLink = usesT linkCoSg tunBs

stgoNotConn :: Bool
stgoNotConn = connectedR argentina bsAs stgo

stgoNotLinked :: Bool
stgoNotLinked = linkedR argentina bsAs stgo