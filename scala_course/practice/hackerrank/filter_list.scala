def f(n:Int,l:List[Int]):List[Int]={
	return l.filter((_ < n)) //(_ < n) is called a predicate and _ is a placeholder
}

println(f(3,List[Int](0,1,2,3,4,5,6,7)))
