class Solution(object):
    def generateMatrix(self, n):
        l=[]
        for i in range(n):
            l.append([])
            for j in range(n):
                l[i].append(0)
        count=1
        top=0
        bottom=n-1
        left=0
        right=n-1
        while True:
            for i in range(left,right+1):
                l[top][i]=count
                count+=1
            top+=1
            if top>bottom or left>right:
                break
            for i in range(top,bottom+1):
                l[i][right]=count
                count+=1
            right-=1
            if top>bottom or left>right:
                break
            for i in range(right,left-1,-1):
                l[bottom][i]=count
                count+=1
            bottom-=1
            if top>bottom or left>right:
                break
            for i in range(bottom,top-1,-1):
                l[i][left]=count
                count+=1
            left+=1
            if top>bottom or left>right:
                break
        return l
               
        