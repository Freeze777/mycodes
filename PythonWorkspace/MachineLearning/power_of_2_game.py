import math as mth

tst=int(raw_input())
while tst>0:
   winner=True
   num=int(raw_input())
   while num!=1:
      if (num&(num-1))==0:
         num=num>>2
      else:
         n_bits=int(mth.ceil(mth.log(num)/mth.log(2)))
         num=num-(1<<(n_bits-1))
          
      winner=~winner
      
   print "Louise" if winner else "Richard"
   tst-=1