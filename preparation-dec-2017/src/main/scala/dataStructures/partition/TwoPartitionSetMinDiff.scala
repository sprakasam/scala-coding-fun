package dataStructures.partition

import scala.collection.mutable.ListBuffer

object TwoPartitionSetMinDiff extends App {

  def findNearest(index: Int, numbers: List[Int]): Int = numbers(index + 1)

  def partitionSet(numbers: List[Int]): Int = {
    val set1 = new ListBuffer[Int]
    val set2 = new ListBuffer[Int]
    set1 += numbers.head
    set2 += numbers(1)
    val iterations = numbers.length / 2
    for (i <- 1 until iterations) {
      val index = i * 2
      println(s"index = $index, index + 1 = ${index + 1}")
      val max = List(numbers(index), numbers(index + 1)).max
      val min = List(numbers(index), numbers(index + 1)).min
      val set1Sum = set1.sum + numbers(index)
      val set2Sum = set2.sum + numbers(index)

      if (set1Sum < set2Sum) {
        set1 += max
        set2 += min
      } else {
        set1 += min
        set2 += max
      }

    }

    println(set1.mkString(","))
    println(set2.mkString(","))
    Math.abs(set1.sum - set2.sum)
  }

  println(partitionSet(List(1,2,3,4,5,6,7,8,9,10)))
  println(partitionSet(List(3, 1, 4, 2, 2, 1)))

}
