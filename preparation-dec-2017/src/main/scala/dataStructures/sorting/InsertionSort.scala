package dataStructures.sorting

import scala.collection.mutable.MutableList


// Time complexity - O(n*n)
object InsertionSort extends App {

  val input = MutableList(9,7,6,15,16,5,10,11)

  def insertionSort() : Unit = {
    for (marker <- 1 until input.size) { // marker is the index of unsorted elements
      val element = input(marker)
      var j = marker - 1 // j is the index of sorted elements

      while(j >= 0 && input(j) > element) {
        input(j + 1) = input(j)
        j = j - 1
      }
      input(j + 1) = element
    }
  }

  insertionSort()

  println(input.mkString(","))
}
