/*def fib(n:Int):Long={
	if(n==1)0L
	else if (n==2)1L
	else fib(n-1)+fib(n-2)
}*/
def fib(n:Int):Long={	
	
	def fib_helper(curr:Int,n:Int,fn_1:Long,fn_2:Long):Long={
		if(n==curr){
			return (fn_1+fn_2)
		}
		else{
			return fib_helper(curr+1,n,(fn_1+fn_2),fn_1)
		}
		
	}
	if(n==1)0L
	else if (n==2)1L
	else fib_helper(2,n,0,1)

}
println(fib(3))
println(fib(4))
println(fib(40))

