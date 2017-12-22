package dataStructures.continuous

import scala.annotation.tailrec

object LongestSubArrayWithDivisible extends App {

  val input = Seq(2,7,6,1,4,5)
  val divisible = 3

  @tailrec
  def continuousSubArraySum(numbers: Seq[Int],
                            currentSum: Int,
                            startPosition: Int,
                            endPosition: Int,
                            subArray: Seq[Int]): Seq[Int] = {
    val sa: Seq[Int] = ((startPosition - 1) until endPosition).map(numbers(_))
    val sum = sa.sum
    if (endPosition == numbers.size) {
      subArray
    } else {
      if (sum > currentSum && sum % divisible == 0) {
        val bestSubArray = if (subArray.sum < sum) sa else subArray
        continuousSubArraySum(numbers, sum, startPosition + 1, endPosition, bestSubArray)
      } else
        continuousSubArraySum(numbers, currentSum, startPosition, endPosition + 1, subArray)
    }
  }

  val result = continuousSubArraySum(numbers = input,
    currentSum = 0,
    startPosition = 1,
    endPosition = 1,
    Seq.empty[Int])

  println(result.mkString(","))
}
