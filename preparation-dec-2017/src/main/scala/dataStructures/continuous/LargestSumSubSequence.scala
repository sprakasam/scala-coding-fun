package dataStructures.continuous

import dataStructures.continuous.ContinuousSubArraySum.continuousSubArraySum

import scala.collection.mutable.ListBuffer

object LargestSumSubSequence extends App {

  val a = Array(-2, -3, 4, -1, -2, 1, 5, -3)
  println("Maximum contiguous sum is " + maxSubArraySum(a))

  def maxSubArraySum(a: Array[Int]) = {
    val size = a.length
    var maxSoFar = Integer.MIN_VALUE
    var maxEndingHere = 0

    for ( i <- 0 until size) {
      maxEndingHere = maxEndingHere + a(i)
      if (maxSoFar < maxEndingHere) maxSoFar = maxEndingHere
      if (maxEndingHere < 0) maxEndingHere = 0
    }
    maxSoFar
  }

  case class MaxSum(maxSoFar: Int, maxEndingHere:Int)
  val result = a.foldLeft(MaxSum(Integer.MIN_VALUE,0))((maxSum, value) => {
    val currentSum = maxSum.maxEndingHere + value
    val maxEnding = if(currentSum < 0 ) 0 else currentSum
    if(maxSum.maxSoFar < currentSum)
      maxSum.copy(maxSoFar = currentSum, maxEndingHere =  maxEnding)
    else
      maxSum.copy(maxEndingHere = maxEnding)

  })

  println(result.maxSoFar)

}
