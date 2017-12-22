package dataStructures.strings

object Anagrams extends App {

  val a = "fcrxzwscanmligyxyvym"
  val b = "jxwtrhvujlmrpdoqbisbwhmgpmeoke"

  // expected out is 30

  val commonChars = a.foldLeft("")((output, char) => {
    val countInB = b.count(_ == char)
    val countInA = output.count(_ == char) + 1
    if(b.exists(_ == char) && countInA <= countInB) output + char else output
  })

  val result = (a.length - commonChars.length) + (b.length - commonChars.length)

  println(result)

}
