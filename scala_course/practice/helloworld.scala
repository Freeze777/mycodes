
object Main {
  def main(args: Array[String]) {
    /*println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }*/
    /*println(balance("(if (zero? x) max (/ 1 x))".toList))
    println(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
    println(balance(":-)".toList))
    println(balance("())(".toList))
    println(balance("((())()))".toList))*/
    println(countChange(4,List(1,2)))
    println(countChange(300,List(5,10,20,50,100,200,500)))
    println(countChange(301,List(5,10,20,50,100,200,500)))
    println(countChange(300,List(500,5,50,100,20,200,10)))
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
	def nCr(n:Int,r:Int):Int={
		if(r==0)return 1
		if(r==1)return n
		if(r>n/2)return nCr(n,n-r)
		return (n*(nCr(n-1,r-1)))/r
	}
	nCr(r,c)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean ={
	def isBracket(c: Char):Boolean= (c=='(')||(c==')')
	var l=chars.filter(isBracket)
	def isBalanced(idx: Int):Int={
		if(idx>=l.length||l(idx)==')')return idx
		var nextClosing=idx+1
		if(l(idx)=='('){
			nextClosing=isBalanced(idx+1)
			if(nextClosing>=l.length)
				return nextClosing
			if(l(nextClosing)==')')
				return isBalanced(nextClosing+1)
			return isBalanced(nextClosing)
		}
		return isBalanced(nextClosing)
	}
	(isBalanced(0)==l.length)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]):Int = {
	def countChangeHelper(money: Int, coins: List[Int],num_denom: Int):Int = {
		if(money==0)return 1
		if(money<0)return 0
		if(num_denom <=0 && money>0)return 0
		return countChangeHelper(money,coins,num_denom-1)+countChangeHelper(money-coins(num_denom-1),coins,num_denom)
	}
	countChangeHelper(money,coins,coins.length)
    }
}

