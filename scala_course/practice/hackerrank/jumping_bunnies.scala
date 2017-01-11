object Solution{
	def lcm(x:Long,y:Long):Long={
		def gcd(x:Long,y:Long):Long={
			if (x==0) return y
			else return gcd(y%x,x)
		}
		return x / gcd(x,y) * y  //to avoid overflow
	}
	
	def main(args:Array[String]){
		var T = readLine().toInt
		var arr = readLine().split(' ').toList.map(_.toLong)
		println(arr.reduceLeft(lcm))
	}
}
