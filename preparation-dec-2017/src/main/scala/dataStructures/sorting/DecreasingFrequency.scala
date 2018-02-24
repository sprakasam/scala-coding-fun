package dataStructures.sorting

import scala.collection.convert.Wrappers.MutableMapWrapper
import scala.collection.mutable


object DecreasingFrequency extends App {
  /**
    * You are given an array with duplicates. You have to sort the array with decreasing frequency of elements.
    * If two elements have the same frequency, sort them by their actual value in increasing order.
    * Ex: [2 3 5 3 7 9 5 3 7]
    * Output: [3 3 3 5 5 7 7 2 9]
    */

  case class NumberFrequency(value: Int, var frequency: Int) extends Ordered[NumberFrequency] {
    def compare(that: NumberFrequency) = {
      if (this.frequency == that.frequency)
        this.value.compareTo(that.value)
      else if (this.frequency < that.frequency)
        1
      else
        -1
    }

    def incrementFrequency(): Unit = this.frequency += 1

    def values: List[Int] = (1 to frequency).map(_ => value).toList
  }

  def sort(numbers: List[Int]): List[Int] = {
    var frequencyMap: List[NumberFrequency] = List.empty[NumberFrequency]
    for(outer <- 0 until numbers.size) {
      val key = numbers(outer)
      if (!frequencyMap.exists(_.value == key)) {
        frequencyMap = frequencyMap :+ NumberFrequency(key, 1)
        for (inner <- outer + 1 until numbers.size) {
          if (numbers(inner) == key) {
            frequencyMap.find(_.value == key).map(_.incrementFrequency())
          }
        }
      } // end of inner
    }
    frequencyMap.sorted.map(_.values).flatten
  }

  val input = List(2, 3, 5, 3, 7, 9, 5, 3, 7)
  println(sort(input).mkString(","))
}
