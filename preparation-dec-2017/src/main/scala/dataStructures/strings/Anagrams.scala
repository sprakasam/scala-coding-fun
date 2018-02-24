package dataStructures.strings

object Anagrams extends App {

  val a = "fcrxzwscanmligyxyvym"
  val b = "jxwtrhvujlmrpdoqbisbwhmgpmeoke"

  // expected out is 30

  val sb = new StringBuilder()
  val commonChars = a.foldLeft(sb)((output, char) => {
    val countInB = b.count(_ == char)
    val countInA = output.count(_ == char) + 1
    if(b.exists(_ == char) && countInA <= countInB) output.append(char) else output
  })

  val result = (a.length - commonChars.length) + (b.length - commonChars.length)

  println(result)

}
