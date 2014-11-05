package com.samples.time_complex_bounded

/**
 * This program sort the entries in the hashmap in ascending order based on the values
 *
 * INPUT:
 *
 * Map[String,Int]("suresh" -> 4, "preetham" ->2, "kokila" ->3)
 *
 * OUTPUT:
 *
 * preetham,2
 * kokila,3
 * suresh,4
 */
object SortMap extends App {

  def sortMap(input: Map[String, Int]): Map[String, Int] = {
    input.toSeq.sortWith(_._2 < _._2).toMap[String, Int]
  }

  // quick sorting
  def sortMap1(input: Map[String, Int]): Map[String, Int] = {
      if(input.size <= 1) {
        input
      } else {
        val pivot = input.toSeq(input.size/2)
          sortMap1(input.filter(pivot._2 > _._2)) ++
          input.filter(pivot ==) ++
          sortMap1(input.filter(pivot._2 < _._2))
      }
  }

  val wordAndCountMap = Map[String,Int]("suresh" -> 4, "preetham" ->2, "kokila" ->3)
  val result = sortMap1(wordAndCountMap)
  result.foreach(entry => println(entry._1 +"," +entry._2))

}
