package dataStructures.strings

object SecondString  extends App {

  val s1 = "geekforgeeks"
  val s2 = "geeeek"

  val filteredS1 = s2.foldLeft(s1)((string, char) => string.replaceFirst(char +"", ""))

  val result: Boolean = (s1.length - filteredS1.length) == s2.length
  println(result)

}
