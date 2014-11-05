package com.samples.time_complex_bounded

/**
 * This program reverses the given sentence and converts vowel char to upper case and consonent to lower case in it.
 *
 * INPUT:
 *
 * i like my job
 *
 * OUTPUT:
 *
 * bOj ym EkIl I
 */
object VowelsAndConsonents extends App {

  // Algorithmic approach - Time Complexity is O(n) in all cases Best, Average and Worst
  def convert(input: Array[Char]) : Array[Char] = {

    require(input != null && input.size >0, "Invalid input!")
    val flags = new Array[Boolean](128) // assuming the input contains ASCII character set
    val vowels = List('A','E','I','O','U','a','e','i','o','u')

    // scan through the vowels and update the flags array. This is constant time operation.
    vowels.foreach(v => {
      val asciiValueAsIndex = v.toInt
      flags(asciiValueAsIndex) = true
    })

    val result = new scala.collection.mutable.ListBuffer[Char]

    // scan through the input array. This is a linear time operation and the time complexity is O(n)
    val inputSize = input.size - 1
    for (i <- inputSize to 0 by -1) {
      val asciiValueAsIndex = input(i).toInt
      if (flags(asciiValueAsIndex)) {
        result += input(i).toUpper           // this is a vowel character
      } else {
        result += input(i).toLower                // this is a consonant character
      }
    }
    result.toArray
  }

  // BuiltIn approach - Time complexity is O(n ^ 2) in worst case
  def convertWithBuiltInMethods(input: Array[Char]): Array[Char] = {
    val vowels = List('A','E','I','O','U','a','e','i','o','u')
    val result = input.map(i => if(vowels.contains(i)) i.toUpper else i.toLower)
    result.reverse.toArray
  }

  println("Convert vowel char to upper and consonent to lower #####")
  println("""The input is "i like my job" =>"""+convert("i like my job".toCharArray).mkString(""))
  println("""The input is "i like my job" =>"""+convertWithBuiltInMethods("i like my job".toCharArray).mkString(""))

}
