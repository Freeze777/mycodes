def generate(l,s,depth,ans,d):
    if depth==len(s):
        ans.append("".join(l))
        return
    for c in d[s[depth]]:
        l[depth]=c
        generate(l,s,depth+1,ans,d)
    return

class Solution(object):
    def letterCombinations(self, digits):
        if len(digits)==0:
            return []
        ans=[]
        l=['']*len(digits)
        d={'1':'','2':'abc','3':'def','4':'ghi','5':'jkl','6':'mno','7':'pqrs','8':'tuv','9':'wxyz','0':' '}
        generate(l,digits,0,ans,d)
        return ans