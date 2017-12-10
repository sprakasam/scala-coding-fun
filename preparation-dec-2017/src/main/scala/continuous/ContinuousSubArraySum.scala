package continuous

import scala.annotation.tailrec


object ContinuousSubArraySum extends App {

  @tailrec
  def continuousSubArraySum(numbers: Seq[Int],
                            expectedSum: Int,
                            currentSum: Int,
                            startPosition: Int,
                            endPosition: Int): (Int, Int) = {
    if (numbers.isEmpty || currentSum == expectedSum) {
      (startPosition, endPosition)
    } else {
      val head = numbers.head
      if ((currentSum + head) > expectedSum) {
        (startPosition + 1, endPosition)
      } else {
        continuousSubArraySum(numbers.tail, expectedSum, currentSum + head, startPosition, endPosition + 1)
      }
    }
  }

  val result = continuousSubArraySum(numbers = Seq(1, 2, 3, 7, 5),
    expectedSum = 15,
    currentSum = 0,
    startPosition = 1,
    endPosition = 0)

  println(result)
}