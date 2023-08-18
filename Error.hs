module Error 
    where

import City
import Link
import Point
import Region
import Tunel
import Test

--CHECKEAR QUE NO SE REPITA CODIGO CON TEST
linStBsHasNoCit :: Bool
linStBsHasNoCit = linksL stgo city3 linStBs

city3 :: City
city3 = newC "City 3" (newP 3 4)
link12 :: Link
link12 = newL bsAs stgo medium
city3NotInLink12 :: Bool
city3NotInLink12 = connectsL city3 link12


link23 :: Link
link23 = newL stgo city3 vip
tunnel12 = newT [link12]
tunnel12 :: Tunel
city3NotConnectedToTunnel12 :: Bool
city3NotConnectedToTunnel12 = connectsT city3 bsAs tunnel12
tunnel12NotUseLink23 :: Bool
tunnel12NotUseLink23 = usesT link23 tunnel12


stgoNotConnectedTobsAs :: Bool
stgoNotConnectedTobsAs = connectedR region bsAs stgo
stgoNotLinkedTobsAs :: Bool
stgoNotLinkedTobsAs = linkedR region bsAs stgo

noLinkDelay :: Float
noLinkDelay = delayR region2 bsAs stgo
noLinkCapacity :: Int
noLinkCapacity = availableCapacityForR region2 bsAs stgo

region :: Region
region = foundR newR bsAs
duplicateCity :: Region 
duplicateCity = foundR region bsAs

region2 :: Region
region2 = newR
linkCitiesNotInRegion :: Region
linkCitiesNotInRegion = linkR region2 bsAs stgo vip

