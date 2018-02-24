package dataStructures.subsequence

object ArraySubset extends App {

  val input = Array("a", "b", "c")

  def subset(remainder: Array[String], result: Array[Array[String]]): Array[Array[String]] = {
    if(remainder.isEmpty) // exit case
      result
    else if (result.isEmpty) { // base case
      val subsets = Array(Array(remainder.head))
      subset(remainder.tail, subsets)
    } else {
      // add the remainders head to all the subsets in the result
      val stringToAdd = remainder.head
      // result => Array(Array.emptyString, Array("a"))
      // To add b => Array(Array("b"), Array("a", "b"))
      // Outcome will be Array(Array.emptyString, Array("a"), Array("b"), Array("a", "b"))
      val subsets = result.foldLeft(Array(Array(stringToAdd)))((accum, subsets) => {
        accum :+ (subsets :+ stringToAdd)
      })

      subset(remainder.tail, result ++ subsets)
    }
  }

  subset(input, Array.empty[Array[String]]).zipWithIndex.foreach(a => println(a._2 +"=>"+ a._1.mkString("")))
}
