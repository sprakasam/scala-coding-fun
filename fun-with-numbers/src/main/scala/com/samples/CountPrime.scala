package com.samples

/**
 * This program determines the sum of the first 1000 prime numbers.
 */
object CountPrime extends App {

  def isPrime(n: Int, divisor: Int): Boolean = {
    for(i <- 2 to n/2)
      if( n % i == 0 ) {
        return false
      }
    true
  }

  def countPrimes(n: Int = 2, accum: Int = 0, noOfPrimes: Int = 1000): Int = {
    if(noOfPrimes == 0) accum
    else {
      if(isPrime(n, n - 1)) countPrimes(n + 1, accum = accum + n, noOfPrimes - 1)
      else countPrimes(n + 1, accum, noOfPrimes)
    }
  }

  println(countPrimes())

}
