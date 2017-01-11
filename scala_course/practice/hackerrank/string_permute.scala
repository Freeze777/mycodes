object Solution{
	
	def main(args:Array[String]){
		var T = readInt()
		var t = 0
		for(t <- 1 to T){
			var s = readLine().toArray[Char]			
			var n = s.length
			var i = 0 
			for(i <- 0 until n by 2){
				print(s(i+1)+""+s(i))
			}
			println()
		}
	}
}
