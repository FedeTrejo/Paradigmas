module ShowR where

import City
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel]
--EN PROCESO
instance Show Region where
  show (Reg cities links tunnels) = 
    "Region with " ++ show (length cities) ++ " cities, " ++
                           show (length links) ++ " links, " ++
                           show (length tunnels) ++ " tunnels"

