module FalseTests where

import Link
import Test
import Region
import Tunel

fT = [
  linksL stgo cordoba linBsSt,

  connectsL cordoba linBsSt,

  connectsT cordoba bsAs (newT [linBsSt]),

  usesT linkCoSg (newT [linBsSt]),
   
  connectedR argentina bsAs stgo,

  linkedR argentina bsAs stgo]