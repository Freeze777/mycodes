#lamda expression java

py=[15,12,8,8,7,7,7,6,5,3]
his=[10,25,17,11,13,17,20,13,9,15]
py_sum=sum(py)
his_sum=sum(his)
py_his_sum=10*sum(map(lambda x,y:x*y,py,his))
py_his_sum-=py_sum*his_sum
py2=10*sum(map(lambda x:x*x,py))
py2-=py_sum**2
his2=10*sum(map(lambda x:x*x,his))
his2-=his_sum**2
py2=pow(py2,0.5)
his2=pow(his2,0.5)
ans=py_his_sum/float(py2*his2)
print round(ans,3)