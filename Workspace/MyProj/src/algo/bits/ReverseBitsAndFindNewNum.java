package algo.bits;

public class ReverseBitsAndFindNewNum {
	    // you need treat n as an unsigned value
	    public int reverseBits(int n) {
	        int rev=0;
	        int bits=31;
	        while(bits-->0){
	            int lsb=(n&1);
	            rev|=lsb;
	            rev<<=1;
	            n>>=1;
	        }
	        return rev;
	    }
	    public static void main(String[] args) {
	    	
			System.out.println(new ReverseBitsAndFindNewNum().reverseBits(43261596));
		}
}
