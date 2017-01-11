for(i <- 0 until n by 2) // step increments of 2
var s = readLine() //I/O
var arr = s.toArray[Char] // string to char array
var l =s.toList // string to list of char
var l =l:+'$' // append an element to end of the list -O(n)
var T = readLine().toInt// string to int
var arr = readLine().split(' ').toList.map(_.toInt)//list of integers
var ans =arr.reduceLeft(function_name)// reduce operation on an list
