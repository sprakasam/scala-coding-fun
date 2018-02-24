package dataStructures.continuous

import scala.collection.mutable.Map

object NumOfCharsLCS extends App {

  val A = "aa"
  val B = "baaa"

  def insertCharAt(s: String, c: Char, i: Int): String = {
    if ( i == 0 ) // start
      c + s
    else if (i == s.length) // end
      s + c
    else // middle
       s.substring(0, i) + c + s.substring(i + 1, s.length)
  }

  def updateSubSequence(char: Char, position: Int, map: Map[Char, List[Int]]) : Unit = {
    if(map.contains(char)) {
      map.update(char, map.get(char).get :+ position)
    } else {
      map.update(char, List(position))
    }
  }

  val result = B.foldLeft(Map.empty[Char, List[Int]])((map, char) => {
    val isSubSequenceExists   = map.exists(e => e._1 ==  char)

    if(!isSubSequenceExists) {
      for (i <- 0 to A.length) {
        if (B.contains(insertCharAt(A, char, i))) {
          updateSubSequence(char, i, map)
        }
      }
    }
    map
  })

  println(result.values.flatten.size)

}
