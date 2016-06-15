class Solution(object):
    def isValid(self, s):
        if len(s)==0 or len(s)%2!=0:
            return False
        d={')':'(','}':'{',']':'['}
        st=[]
        for c in s:
            if c in d: #closing bracket
                if len(st)!=0 and st[-1]==d[c]:
                    st.pop()
                else:
                    st.append(c)
            else:
                st.append(c)
             
        return (len(st)==0)
                    