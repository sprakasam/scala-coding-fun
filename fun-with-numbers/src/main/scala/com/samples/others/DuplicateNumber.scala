package com.samples.others

/**
 * Finds the duplicate number from the given set of numbers using recursive function
 */
object DuplicateNumber extends App {
  val numbers = Set(1, 2, 3, 2, 5)
  val list = List(1, 2, 3, 2, 5)

  @tailrec
  def hasDuplicate(actual: List[Int], filtered: List[Int] = Nil): Boolean = {
    actual match {
      case Nil                                      =>  filtered.size != list.size
      case head :: tail if !filtered.contains(head) =>  hasDuplicate(tail, head :: filtered)
      case head :: tail                             =>  hasDuplicate(tail, filtered)
    }
  }

  println ("the given list %s contains duplicate (yes/no): %s".format(list.toString(), hasDuplicate(list)))

}
