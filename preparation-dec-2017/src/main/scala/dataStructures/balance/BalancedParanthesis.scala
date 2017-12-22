package dataStructures.balance

object BalancedParanthesis extends App {

  val input = "((()())())(("

  val visitedAndIndex = input.zipWithIndex.foldLeft(("", 0))((visited, i) => {
    if(i._2 > 0 && visited._1.isEmpty)
      visited
    else if(i._1 == ')'){
      if(visited._1.isEmpty)
        ("", i._2)
      else
       (visited._1.tail, i._2)
    }
    else
      (visited._1 + "(", i._2)
  })

  val result = if(visitedAndIndex._1.isEmpty) visitedAndIndex._2 else 0

  val result1 = input.zipWithIndex.foldLeft(("", 1))((openBracesAndLIndex, entry) => {
    if(entry._1 == ')') {
      if (openBracesAndLIndex._1.length > 1)
        (openBracesAndLIndex._1.tail, openBracesAndLIndex._2)
      else
        ("", entry._2 + 1)
    } else
      (openBracesAndLIndex._1 + "(", openBracesAndLIndex._2)
  })
  println(result1._2)

}
