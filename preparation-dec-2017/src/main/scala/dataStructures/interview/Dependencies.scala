package dataStructures.interview

import scala.annotation.tailrec

object Dependencies extends App {

  val matrix = Map(
    'a' -> List('d', 'c'),
    'b' -> List(),
    'c' -> List('f'),
    'd' -> List('b'),
    'e' -> List(),
    'f' -> List('e')
  )

  def getDependencies(obj: Char) {

    @tailrec
    def recursiveExplore(currentChar: Char,
                         currentDependencies: List[Char],
                         result: List[Char]): List[Char] = {
      val foundDependencies: Option[List[Char]] = matrix.find(entry => entry._1 == currentChar).map(e => e._2)

      // 1. Identify the proper termination condition
      // 2: Identify the variables required to store the intermediate results
      // 3: Make sure the intermediate variable are updated properly in each iteration
      val isExploreDone = (foundDependencies.isEmpty || foundDependencies.get.isEmpty) && currentDependencies.isEmpty
      if (isExploreDone)
        result
      else {
        if (foundDependencies.get.isEmpty) {
          recursiveExplore(currentDependencies.head,
            currentDependencies.tail,
            result)
        } else {
          recursiveExplore(foundDependencies.get.head,
            currentDependencies ++ foundDependencies.get.tail,
            result ++ foundDependencies.get)
        }
      }
    }

    val result = recursiveExplore(currentChar = obj,
      currentDependencies = List.empty[Char],
      result = List.empty[Char])

    println(result.mkString(" "))
  }

  getDependencies('a')
}
