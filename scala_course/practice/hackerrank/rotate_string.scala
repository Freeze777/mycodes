object Solution{
	def rotate(l:List[Char],n:Int):Unit={
		if(n==0){
			val sb = new StringBuilder()
			print(l.addString(sb)+" ")			
			return
		}
		else{	
			if(n!=l.length){
				val sb = new StringBuilder()
				print(l.addString(sb)+" ")
			}	
			rotate(l.tail:::(List.fill(1)(l.head)),n-1)
		}
	}

	def main(args: Array[String]){
		var T = readInt()
		var t = 0
		for( t <- 1 to T){
			var s = readLine().trim().toList
			rotate(s,s.length)
			println()
		}
	}
}
