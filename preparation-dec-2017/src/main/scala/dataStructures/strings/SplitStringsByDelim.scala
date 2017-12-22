package dataStructures.strings


object SplitStringsByDelim extends App {

  val result = "geeks;for;geeks".foldLeft(List.empty[String], 0)((list, char) => {
    if(char == ';') {
      (list._1, list._2 + 1)
    } else {
      if(list._1.size > list._2) {
        (list._1.updated(list._2, list._1(list._2)+ char), list._2)
      } else {
        val update = list._1 :+ char + ""
        (update, list._2)
      }
    }
  })

  result._1.foreach(println _ )

}
