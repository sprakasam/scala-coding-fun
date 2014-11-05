package com.samples.time_complex_bounded

/**
 * This program prints the different permutation of a given word
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

  def printPermutation(input: Array[Char]) {
    val length = input.length
    val used = new Array[Boolean](length)

    def innerFunction(in: Array[Char], out: StringBuffer, pointer: Int) {

      if(pointer >= length) {
        println(out.toString)
      }

      for(i <- 0 until length) {
        if(used(i)) {
          // skip to the next letter
//          println("skipping the letter=>"+in(i))
        } else {
//          println("appending the letter %s to the word %s".format(in(i), out.toString))
          out.append(in(i))
          used(i)  = true
//          println("calling innerFunction(%s, %s, %d, %s)".format("abc", out.toString, pointer+1, used.toList))
          innerFunction(in, out, pointer + 1)
//          println("finished innerFunction(%s, %s, %d, %s)".format("abc", out.toString, pointer+1, used.toList))
          used(i) = false
          out.setLength(out.length() - 1)
//          println("finished innerFunction => %s with length=%d, index=%d".format(out.toString, out.length(), i))
        }
      }
    }

    innerFunction(input, new StringBuffer, 0)
  }

  printPermutation("abc".toCharArray)
}
