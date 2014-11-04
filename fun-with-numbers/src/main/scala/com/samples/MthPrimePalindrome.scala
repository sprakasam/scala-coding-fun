package com.samples

/**
 * Write a program which determines the largest prime palindrome less than 1000.
 */
object MthPrimePalindrome extends App {

  def isPalindrome(str: String): Boolean = {
    val strLen = str.size
    def onePass(index: Int, leftPointer: Int = 0, rightPointer: Int = strLen-1): Boolean = {
      if(index == strLen/2 || index == (strLen+1)/2)
        true
      else
        if(str.charAt(leftPointer) == str.charAt(rightPointer))
        onePass(index + 1, leftPointer + 1, rightPointer - 1)
        else false
    }
    onePass(0)
  }

  println(isPalindrome(99+""))
}
