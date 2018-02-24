package dataStructures.sorting


//
object SelectionSort extends App {

  /**
    * The selection sort algorithm sorts an array by repeatedly finding the minimum element
    * (considering ascending order) from unsorted part and putting it at the beginning. The algorithm maintains two subarrays in a given array.

      1) The subarray which is already sorted.
      2) Remaining subarray which is unsorted.

      In every iteration of selection sort, the minimum element (considering ascending order) from the unsorted
      subarray is picked and moved to the sorted subarray.
    */

  val input = Array(64, 25, 12, 22, 11)

  val unsorted = Array.emptyIntArray

  def sort(unsorted: Array[Int], sorted: Array[Int]): Array[Int] = {
    if (unsorted.isEmpty)
      sorted
    else {
      var minimum = unsorted.head
      var j = 0
      for (i <- 1 until unsorted.size) {
        if (unsorted(i) < minimum) {
          j = i
          minimum = unsorted(i)
        }
      }
      val sortedSubArray = unsorted.filter(_ != unsorted(j))
      sort(sortedSubArray, sorted :+ minimum)
    }
  }

  def sortStr(unsorted: Array[Char], sorted: Array[Char]) : String = {
    if (unsorted.isEmpty)
      sorted.mkString
    else {
      var minimum = unsorted.head
      var j = 0
      for (i <- 1 until unsorted.size) {
        if (unsorted(i) > minimum) {
          j = i
          minimum = unsorted(i)
        }
      }
      val sortedSubArray = unsorted.filter(_ != unsorted(j))
      sortStr(sortedSubArray, sorted :+ minimum)
    }
  }

  val string = "alkasingh".toCharArray
  println(sortStr(string, Array.emptyCharArray))

  val result = sort(input, Array.emptyIntArray)
  println(result.mkString(","))
}
