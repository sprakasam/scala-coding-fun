package com.samples

object Palindrome extends App{

  /**
   * Create 2 pointers, one moves from the beginning and another one moves from the end
   *
   * In case, if array size is even, the first pointer + 1 minus the second pointer is 0 return true
   * In case, if array size is odd, the first pointer == second pointer then return true
   * @param tmp
   * @return
   */
  def standardApproach(tmp: Array[Char]): Boolean = {  // Time Complexity => O(n)
    // Time Complexity => O(1)
    if (tmp.length == 1) true
    else if (tmp.length == 2) tmp(0) == tmp(1)
    else {
      var firstPointer = 0
      var secondPointer = tmp.length - 1
      while (tmp(firstPointer) == tmp(secondPointer)) {
        if((firstPointer+1) - secondPointer == 0) return true
        if(firstPointer == secondPointer) return true
        firstPointer = firstPointer + 1
        secondPointer = secondPointer - 1
      }
      firstPointer == secondPointer
    }
  }

  /**
   * Uses recursive inner function to reverse the given string. Like,
   *
   * abccba => a + abccb =>....
   * @param s
   * @return
   */
  def recursiveApproach(s: String): Boolean = {
    def reverseAndCheck(str: String): String = {
      val strLen = str.length
      if (strLen == 1) {
        str
      } else {
        str.substring(strLen-1, strLen) + reverseAndCheck(str.substring(0, strLen-1))
      }
    }
    s == reverseAndCheck(s)
  }

  def builtInApproach(input: List[Char]): Boolean = {
    val size = input.size
    val isOddSizedList = size % 2 != 0
    val halfSize = size / 2
    def innerFn(input: List[Char], firstHalf: List[Char], count: Int): Boolean = {
      input match {
        case Nil => false
        case head :: tail => {
          if (count < halfSize) {
            // add the elements into first half
            innerFn(tail, head :: firstHalf, count + 1)
          } else {
            if (isOddSizedList)  firstHalf == tail else firstHalf == input
          }
        }
      }
    }

    innerFn(input, List(), 0)
  }

  println("abdba is palindrome =>"+standardApproach("abdba".toCharArray))
  println("total is palindrome =>"+standardApproach("total".toCharArray))
  println("abccba is palindrome =>"+standardApproach("abccba".toCharArray) )

  println("abdba is palindrome =>"+recursiveApproach("abdba"))
  println("total is palindrome =>"+recursiveApproach("total"))
  println("abccba is palindrome =>"+recursiveApproach("abccba") )

  println("abdba is palindrome =>"+builtInApproach("abdba".toCharArray.toList))
  println("total is palindrome =>"+builtInApproach("total".toCharArray.toList))
  println("abccba is palindrome =>"+builtInApproach("abccba".toCharArray.toList) )

}
