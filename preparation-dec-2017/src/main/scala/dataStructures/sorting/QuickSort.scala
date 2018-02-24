package dataStructures.sorting

// Time complexity - 2T(n/2) + O(n) => O(nlogn)
object QuickSort extends App {
  val arr = Array(10, 7, 8, 9, 1, 5)

  def partition(arr: Array[Int], low: Int, high: Int) = {
    val pivot = arr(high)
    var i = low - 1
    // index of smaller element
    var j = low
    while (j < high) { // If current element is smaller than or equal to pivot
      if (arr(j) <= pivot) {
        i += 1
        // swap arr[i] and arr[j]
        val temp = arr(i)
        arr(i) = arr(j)
        arr(j) = temp
      }

      j += 1
    }
    // swap arr[i+1] and arr[high] (or pivot)
    val temp = arr(i + 1)
    arr(i + 1) = arr(high)
    arr(high) = temp
    i + 1
  }

  /* The main function that implements QuickSort()
    arr[] --> Array to be sorted,
    low  --> Starting index,
    high  --> Ending index */
  def sort(arr: Array[Int], low: Int, high: Int): Unit = {
    if (low < high) {
      /* pi is partitioning index, arr[pi] is now at right place */
      val pi = partition(arr, low, high)
      // Recursively sort elements before
      // partition and after partition
      sort(arr, low, pi - 1)
      sort(arr, pi + 1, high)
    }
  }

  def quickSort(arr: Array[Int], left: Int, right: Int): Unit = {
    if (left >= right) {
      return
    }
      val pivot = (left + right) / 2
      val index = quickPartition(arr, left, right, pivot)
      quickSort(arr, left, index - 1)
      quickSort(arr, index, right)

  }

  def quickPartition(arr: Array[Int], left: Int, right: Int, pivot: Int): Int = {
    var l = left
    var r = right
    while (l <= r) {
      println(l+ ","+ pivot +","+ r)
      println(arr.mkString(","))
      while (arr(l) < arr(pivot))
        l = l + 1

      while (arr(r) > arr(pivot))
        r = r - 1

      if (l <= r) { // swap
        val temp = arr(l)
        arr(l) = arr(r)
        arr(r) = temp
        l = l + 1
        r = r - 1
      }
    }

    l + 1
  }
  quickSort(arr, 0, 5)

  println(arr.mkString(","))
}
