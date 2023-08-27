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
 
  print t1

  print argentinaConnected
  
  putStrLn "Connected Cordoba & Santiago"
  print (connectedR argentinaConnected cordoba stgo)

  putStrLn "Capacity:"
  print (availableCapacityForR argentinaConnected cordoba stgo)

  putStrLn "Done!"