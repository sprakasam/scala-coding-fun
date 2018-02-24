package dataStructures.sorting

import scala.collection.mutable.MutableList

object MergeSort extends App {

   // Time Complexity => 2T(n/2) + O(n) => O(nlogn)
  /**
    * . Find the middle point to divide the array into two halves:
             middle m = (l+r)/2
     2. Call mergeSort for first half:
             Call mergeSort(arr, l, m)
     3. Call mergeSort for second half:
             Call mergeSort(arr, m+1, r)
     4. Merge the two halves sorted in step 2 and 3:
             Call merge(arr, l, m, r)
    */

  val input = MutableList(12, 11, 13, 5, 6, 7)

  def merge(array: MutableList[Int], l: Int, m: Int, r: Int): Unit = {

    val firstHalf = (l to m).map(array(_)).toList
    val secondHalf = (m+1 to r).map(array(_)).toList

    def sort(a1: List[Int], a2: List[Int], result: List[Int]): List[Int] = {
      if(a1.isEmpty && a2.isEmpty)
        result
      else if (a1.nonEmpty && a2.isEmpty)
        sort(a1.tail, a2, result :+ a1.head)
      else if (a1.isEmpty && a2.nonEmpty)
        sort(a1, a2.tail, result :+ a2.head)
      else {
        if(a1.head < a2.head) {
          sort(a1.tail, a2, result :+ a1.head)
        } else {
          sort(a1, a2.tail, result :+ a2.head)
        }
      }
    }

    val sorted = sort(firstHalf, secondHalf, List.empty[Int])
    var sIndex = 0
    for(i <- l to r) {
      array.update(i, sorted(sIndex))
      sIndex = sIndex + 1
    }
  }

  def mergeSort(array: MutableList[Int], leftIndex: Int, rightIndex: Int): Unit = {
    if(leftIndex < rightIndex) {
      val middle = (leftIndex + rightIndex) / 2
      mergeSort(array, leftIndex, middle)
      mergeSort(array, middle + 1, rightIndex)

      merge(array, leftIndex, middle, rightIndex)
    }
  }

  mergeSort(input, 0, input.size - 1)
  println(input.mkString(","))
}
