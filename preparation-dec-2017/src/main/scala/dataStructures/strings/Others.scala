package dataStructures.strings

object Others extends App {

  val A = "aacdb"
  val B = "gafd"

  var uncommonA = ""
  var uncommonB = ""
  for(i <- 0 until A.length) {
    if(i < A.size && !B.contains(A(i)))
      uncommonA = uncommonA + A(i)
    if(i < B.size && !A.contains(B(i)))
      uncommonB = uncommonB + B(i)
  }

  println(uncommonA + uncommonB)

}
