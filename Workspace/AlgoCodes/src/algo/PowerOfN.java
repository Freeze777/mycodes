package algo;
public class PowerOfN {
    public static double myPow(double x, int n) {
        return (n>=0)?pow(x,n):pow(1.0/x,-n);//bug: didnt think of -ve exponents
    }
    public static double pow(double x, int n) {
        if(x==0.0||x==1.0) //bug: if not handled will give stackoverflow
            return x;
        if(n==0)
            return 1;
        if(n==1)
            return x;
        if((n&1)==0)
            return pow(x*x,n>>1);
        else
            return x*pow(x*x,n>>1);
    }
    public static void main(String[] args) {
		System.out.println(myPow(8.9,-5));
		System.out.println(Math.pow(8.9,-5));
		System.out.println((5)/(-10));
	}
}