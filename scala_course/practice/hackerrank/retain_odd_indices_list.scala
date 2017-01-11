def f(l:List[Int]):List[Int]={
	def fh(l:List[Int],res:List[Int]):List[Int]={
		if(l.length==0||l.length==1)return res
		//if(l.length==1)return res:::List(l.head)	
		return fh(l.drop(2),res:::List[Int](l(1))) 
	}
	fh(l,List[Int]()) 
}

println(f(List[Int](0,1,2,3,4,5,6,7)))
