from sklearn import linear_model
import numpy as np

x_matrix = []
y_matrix = []


f,n = map(int,raw_input().split())

tmp=n
while tmp>0:
    inp=map(float,raw_input().split())
    inp=np.array(inp)
    x_matrix.append(inp[0:f])
    y_matrix.append(inp[f])
    tmp-=1
    
x_matrix=np.array(x_matrix)
y_matrix=np.array(y_matrix)

lin = linear_model.LinearRegression()
lin.fit(x_matrix, y_matrix)   
 
q=int(raw_input())
 
while q>0:
    x=map(float,raw_input().split())
    print round(lin.predict(x), 2)
    q-=1
