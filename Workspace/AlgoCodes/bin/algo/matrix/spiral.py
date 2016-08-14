class Solution(object):
    def spiralOrder(self, matrix):
        if len(matrix)==0:
            return []
        if len(matrix[0])==0:
            return []
        l=[]
        row=len(matrix)
        col=len(matrix[0])
        top=0
        bottom=row-1
        left=0
        right=col-1
        while True:
                l+=[matrix[top][i] for i in range(left,right+1)]
                top+=1
                if top>bottom or left>right:
                    break
                l+=[matrix[i][right] for i in range(top,bottom+1)]
                right-=1
                if top>bottom or left>right:
                    break
                l+=[matrix[bottom][i] for i in range(right,left-1,-1)]
                bottom-=1
                if top>bottom or left>right:
                    break
                l+=[matrix[i][left] for i in range(bottom,top-1,-1)]
                left+=1
                if top>bottom or left>right:
                    break
        return l
                