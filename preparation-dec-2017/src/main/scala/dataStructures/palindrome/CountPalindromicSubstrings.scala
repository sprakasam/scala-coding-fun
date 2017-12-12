package dataStructures.palindrome

import scala.collection.mutable.ListBuffer

object CountPalindromicSubstrings extends App {

  val string = "xyaabax"
  val startPosition = 2
  val endPosition = 3

  def generatePalindrome(s: String): List[String] = {
    val palindromes = new ListBuffer[String]
    val characters = s.toCharArray
    for (i <- 0 until s.length) {
      palindromes += "" + characters(i)
      for(j <- i + 1 until s.length) {
        val p = { ( i to j).map(index => characters(index))}.mkString("")
        println("p="+p)
        if(p.reverse == p) palindromes += p
      }
    }
    palindromes.toList
  }

  generatePalindrome(string.substring(startPosition, endPosition +1)).map(println _ )

}
