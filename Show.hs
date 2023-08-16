module Show where

import Test

ourShow :: IO ()
ourShow = do

  putStrLn "Cities:"
  print bsAs
  print stgo

  putStrLn "Qualities:" 
  print medium
  print vip

  putStrLn "Links:"
  print linBsSt
  print linStBs

  putStrLn "Link properties:"
  print ("bsAs is in link linBsSt: " ++ show bsInLin)
  print capBsSt

  --putStrLn "Region properties:" FALTA ARREGLAR EN TEST
  --print ("bsAs is connected to stgo in reg2: " ++ show bsAsInReg)
  --print capInReg

  putStrLn "Tunnels:"
  print t1

  putStrLn "Tunnel properties:"
  print ("Tunnel t1 uses link linBsSt: " ++ show tUsesLin)  
  print tDelay

  putStrLn "Done!"





