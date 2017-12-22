package dataStructures.permutation

/**
  * This program prints the different dataStructures.permutation of a given word
  *
  * INPUT:
  *
  * abc
  *
  * OUTPUT:
  *
  * abc
  * acb
  * bac
  * bca
  * cab
  * cba
  *
  */
object StringPermutation extends App {

  val input = "abcd"
  def permuteTopDown(remainder: String, permutations: List[String]): List[String] = {

    // if s = ab, c = 'c', i = 1 then acb
    def insertCharAt(s: String, c: Char, i: Int): String = {
      if (i == s.length)
        s + c
      else
        s.zipWithIndex.foldLeft("")((result, entry) => {
          if (entry._2 == i) result + c + entry._1 else result + entry._1
        })
    }

    if(remainder.isEmpty) { // exit condition
      permutations
    } else if (permutations.isEmpty) {     // base case
      permuteTopDown(remainder.tail, permutations :+ remainder.head.toString)
    } else {
      var p = List.empty[String]
      for (stringIndex <- 0 until permutations.size) {
        val string = permutations(stringIndex)
        for (charIndex <- 0 until string.length + 1) {
          p = p :+ insertCharAt(string, remainder.head, i = charIndex)
        }
      }
      permuteTopDown(remainder.tail, p)
    }
  }

  println(permuteTopDown(input, permutations = List.empty[String]).mkString(","))
}
