package com.samples.others

/**
 *
 */
object BubbleSort extends App {

  val numbers = scala.collection.mutable.Seq(32, 39,21, 45, 23, 3)
  for(i <- numbers) {
    val currentIndex = numbers.indexOf(numbers(i))
    for(j <- numbers.takeRight( (numbers.size - 1)- currentIndex)) {
      if(numbers(j - 1) > numbers (j)) {
        val temp = numbers(j)
        numbers(j) = numbers(j-1)
        numbers(j-1) = temp
      }
    }
  }

  println(numbers)
}
