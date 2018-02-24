package dataStructures.strings

object OneAway extends App {

  val input1 = "pale"
  val input2 = "ple"

  val input3 = "pales"
  val input4 = "pale"

  val input5 = "pale"
  val input6 = "bale"

  val input7 = "pale"
  val input8 = "bae"

  def oneAway(s1: String, s2: String): Boolean = {
    if(s1.startsWith(s2) || s1.endsWith(s2))
      true
    else {
      // insert
      var newS2 = s2
      for(i <- 0 until s1.length) {
        if(s1(i) != newS2(i) && s2.length == s1.length) // replace
          newS2 = newS2.substring(0, i) + s1(i) + newS2.substring(i + 1, newS2.length)
        if (s1(i) != newS2(i))  // insert
          newS2 = newS2.substring(0, i) + s1(i) + newS2.substring(i, newS2.length)
      }
      if(s1 == newS2) true else false
    }
  }

  println(oneAway(input1, input2))
  println(oneAway(input3, input4))
  println(oneAway(input5, input6))
  println(oneAway(input7, input8))
}
