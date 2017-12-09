def continuousSubArraySum(numbers: Seq[Int], 
                          expectedSum: Int, 
                          currentSum: Int, 
                          startPosition: Int, 
                          endPosition: Int): (Int, Int) = {
  if(numbers.size == 0 || currentSum == expectedSum) {
    (startPosition, endPosition)
  } else {
    val head = numbers.head
    if((currentSum + head) > expectedSum) {
      val diff =  (currentSum + head) - expectedSum
      println(s"expectedSum = $expectedSum, currentSum = $currentSum, head = ${head}, diff = $diff")
      (startPosition + 1, endPosition)
    } else {
     println(s"expectedSum = $expectedSum, currentSum = $currentSum, head = ${head}")
      continuousSubArraySum(numbers.tail, expectedSum, currentSum + head, startPosition, endPosition + 1)     
    }
  }
}

val result = continuousSubArraySum(numbers = Seq(1, 2, 3, 7,5), 
                     expectedSum = 15,
                     currentSum = 0,
                     startPosition = 1,
                     endPosition = 0)