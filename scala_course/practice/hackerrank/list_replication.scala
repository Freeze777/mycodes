def f(n:Int,l:List[Int]):List[Int]={
	def fh(n:Int,l:List[Int],res:List[Int]):List[Int]={
		if(l.length==0)return res
		var i=0
		var tmp=res:::List.fill(n)(l.head)
		return fh(n,l.tail,tmp)
	}	
	fh(n,l,List())
}

println(f(3,List[Int](1,2,3)))
