package dataStructures.sorting

import scala.collection.mutable.MutableList


// best case O(n) , worst case O(n * n)
object BubbleSort extends App {

  val input = MutableList(5, 1, 4, 2, 8)

  def bubbleSort(isSorted: Boolean): MutableList[Int] = {
    var sorted = true
    for(i <- 0 until input.size - 1) {
      if(input(i) > input(i + 1)) {
        val temp = input (i)
        input(i) = input(i + 1)
        input(i + 1) = temp
        sorted = false
      }
    }

    if(sorted)
      input
    else
      bubbleSort(false)
  }

  println(bubbleSort(false).mkString(","))

}
