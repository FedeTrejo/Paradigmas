module Show where

import Test
import Region

showTelco :: IO ()  
showTelco = do

  putStrLn "Cities:"
  print bsAs
  print stgo
  print cordoba

  putStrLn "Links:"
  print linBsSt
  print linStBs
  print linkCoSg

  putStrLn "Tunnel:" 
  print t1

  putStrLn "Region:"
  print argentinaConnected
  
  putStrLn "Connected cities:"
  print (connectedR argentinaConnected bsAs stgo)

  putStrLn "Capacity:"
  print (availableCapacityForR argentinaConnected cordoba stgo)

  putStrLn "Done!"