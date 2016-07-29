package algo.arrays;


public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        int max=Integer.MIN_VALUE;
        int pos_prod=1,neg_prod=1;
        for(int i=0;i<nums.length;i++){
            int n1=pos_prod*nums[i];
            int n2=neg_prod*nums[i];
            pos_prod=Math.max(Math.max(n1,n2),nums[i]);
            neg_prod=Math.min(Math.min(n1,n2),nums[i]);
            max=Math.max(pos_prod,max);
        }
        return max;
    }
}