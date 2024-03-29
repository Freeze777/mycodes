package algo.stack;

import java.util.Stack;

public class LongestValidParanthesis {

    public int longestValidParenthesesUsingStack(String s) {
        Stack<Integer> st=new Stack<Integer>();
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                st.push(i);
            }else{
                if(!st.isEmpty()&&s.charAt(st.peek())=='('){
                    st.pop();
                    int currLen=st.isEmpty()?(i+1):(i-st.peek());
                    max=Math.max(max,currLen);
                }else
                    st.push(i);
                
            }
        }
        return max;
    }

    public int longestValidParenthesesUsingDP(String s) {
        if(s.length()==0||s.length()==1)
            return 0;
        int[] dp=new int[s.length()];
        dp[1]=s.substring(0,2).equals("()")?2:0;
        int max=dp[1];
        for(int i=2;i<dp.length;i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){ // ()
                    dp[i]=2+dp[i-2];
                }else{                  // ))
                    int idx=i-dp[i-1]-1;
                    if(idx>=0 && s.charAt(idx)=='(') {// (......))
                        dp[i]=(idx>0)?dp[idx-1]+dp[i-1]+2:dp[i-1]+2;
                    }
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }


}
