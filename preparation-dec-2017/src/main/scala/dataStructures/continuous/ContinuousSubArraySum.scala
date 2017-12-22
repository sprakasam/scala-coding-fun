package dataStructures.continuous

import scala.annotation.tailrec


object ContinuousSubArraySum extends App {

  @tailrec
  def continuousSubArraySum(numbers: Seq[Int],
                            expectedSum: Int,
                            startPosition: Int,
                            endPosition: Int): (Int, Int) = {
    val currentSum = ((startPosition - 1) until endPosition).map(numbers(_)).sum
    if (currentSum == expectedSum) {
      (startPosition, endPosition)
    } else {
      if (currentSum  > expectedSum) {
        continuousSubArraySum(numbers, expectedSum, startPosition + 1, endPosition)
      } else {
        continuousSubArraySum(numbers, expectedSum, startPosition, endPosition + 1)
      }
    }
  }

  val result = continuousSubArraySum(numbers = Seq(1, 2, 3, 7, 5),
    expectedSum = 18,
    startPosition = 1,
    endPosition = 1)

  println(result)
}