import numpy as np

ele=0.0
for i in range(10000):
   ele+=0.01
   l=[1,2,3];
   l.append(ele)
   arr=np.array(l)
   if round(arr.std()*1000,0)==816:
    print ele