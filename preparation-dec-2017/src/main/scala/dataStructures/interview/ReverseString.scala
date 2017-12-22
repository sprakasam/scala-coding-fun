package dataStructures.interview

object ReverseString extends App {

  val input = "Hello, how are you doing?"

  def reverseWord(word: String): String = {
    val splitWords = word.split(" ").toList
    splitWords.foldLeft("")((a,b) => {b + " " + a})
  }

  println(reverseWord(input))
}
