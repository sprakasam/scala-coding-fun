package dataStructures.arrays

import scala.collection.mutable.MutableList

object DoubleFirstElement extends App {

  var input = MutableList(0, 2, 2, 2, 0, 6, 6, 0, 0, 8)

  def addZeroToEnd(currentIndex: Int): Unit = {
    // shift all the numbers in the array to front by 1 position
    for(i <- currentIndex until input.size - 1)
      input(i) = input(i + 1)
    input(input.size - 1) = 0
  }

  def recursively(start: Int): MutableList[Int] = {
    if (start == input.size - 1)
      input
    else {
      println(start+"====>"+input.mkString(","))

      // double if next element is same
      if (input(start) == 0) {
        addZeroToEnd(start)
      }

      if (input(start) == input(start + 1) ) {
        input(start) = input(start) * 2
        // make next element as zero
        addZeroToEnd(start + 1)
      }

      recursively(start + 1)
    }
  }

  println(recursively(0))

}
