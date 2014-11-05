package com.samples.code_eval

/**
 * Write a program which determines the largest prime palindrome less than 1000.
 */
object MthPrimePalindrome extends App {

  def isPrime(n: Int): Boolean = {
    for(i <- 2 to n/2)
      if( n % i == 0 ) {
        return false
      }
    true
  }

  def largePalindromeLessThanGivenN(str: String): Int = {
    val strLen = str.size
    def isPalindrome(index: Int, leftPointer: Int = 0, rightPointer: Int = strLen-1): Boolean = {
      if(index == strLen/2 || index == (strLen+1)/2)
        true
      else
        if(str.charAt(leftPointer) == str.charAt(rightPointer))
        isPalindrome(index + 1, leftPointer + 1, rightPointer - 1)
        else false
    }
    if(isPrime(str.toInt) && isPalindrome(0)) str.toInt else largePalindromeLessThanGivenN((str.toInt - 1) + "")

  }

  println(largePalindromeLessThanGivenN(1000+""))
}
