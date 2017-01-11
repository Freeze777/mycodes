object Solution{

	def nCr(n:Int,r:Int):Int={
		if(r==0)return 1
		if(r==1)return n
		if(r>n/2)return nCr(n,n-r)
		return (n*(nCr(n-1,r-1)))/r
	}
	//println(nCr(10,4))
	def main(args:Array[String]){
		var k = readInt()

		var n = 0
		for(n <- 0 to k-1){
			var r = 0
			var arr = new Array[Int](n+1)
			for(r <- 0 to n/2){
				arr(r)=nCr(n,r);
				arr(n-r)=arr(r);
			}

			for(r <- 0 to n)
				print(arr(r)+" ")
			println()
		}
	}
}
