object Solution{
	
	def main(args:Array[String]){
		var T = 1
		var t = 0
		for(t <- 1 to T){
			var p = readLine().toArray[Char]
			var q = readLine().toArray[Char]
			var n = p.length
			var i = 0 
			for(i <- 0 until n){
				print(p(i)+""+q(i))
			}
			println()
		}
	}
}
