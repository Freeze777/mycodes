def gcd(a,b):
    if a==0:
        return b
    if b==0:
        return a
    return gcd(b,a%b)

class Solution(object):
    def canMeasureWater(self, x, y, z):
        if z==x or z==y or z==x+y:
            return True
        if x==0:
            return z==y
        if y==0:
            return z==x
        if z>x+y:
            return False
        g=gcd(x,y)
        return z%g==0
        