package dataStructures.subsequence

object LongestIncreasingSubsequence extends App {

  def computeLIS(array: Array[Int]): Int = {
    val lis: Array[Int] = (1 to array.length).toArray.map ( i => 1)
    for(i <- 1 until array.length) {
      for(j <- 0 until i) {
          if(array(i) > array(j) && lis(i) < lis(j) + 1)
            lis(i) = lis(j) + 1
      }
    }
    println(lis.mkString(","))
    lis.max
  }

  case class IncreasingSequence(index: Int, element: Int)

  val result = Array(10, 22, 9, 33, 21, 50, 41, 60).tail.foldLeft(IncreasingSequence(1, 10))((accum, element) => {
    if(element > accum.element)
      IncreasingSequence(accum.index + 1, element)
    else
      accum
  })

  println(computeLIS(Array(10, 22, 9, 33, 21, 50, 41, 60)))

  println(result.index)
}
