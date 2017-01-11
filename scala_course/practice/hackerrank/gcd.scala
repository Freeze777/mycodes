def gcd(x:Int,y:Int):Int={
	if (x==0) return y
	else return gcd(y%x,x)
}
/*println(gcd(1,5))
println(gcd(10,100))
println(gcd(22,131))*/
