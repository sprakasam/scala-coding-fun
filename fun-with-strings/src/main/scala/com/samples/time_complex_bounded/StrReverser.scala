package com.samples.time_complex_bounded

/**
 * Reverse the string using standard recursion and tail recursion
 */

object StrReverser extends App {
  
  val simpleReverse : (String => String) = {_.reverse}
  
  // SURESH -> HSERUS
  val combinatorReverse : (String => String) = {
    _.toCharArray.foldRight[String](""){(c : Char, b : String)=> b.concat(c.toString)}
  }

  // stdRecursion("SURESH")
  // -> stdRecursion("URESH") + 'S'
  // -> stdRecursion("RESH") + 'U'
  // -> stdRecursion("ESH")  + 'R'
  // -> stdRecursion("SH") + 'E'
  // -> stdRecursion("H") + 'S'
  // -> H
  def stdRecursion(x : String) : String = {
    val len = x.length()
    if(len == 1) {
      x
    } else {
      stdRecursion(x.substring(1, len)) + x.charAt(0)
    }
  }

  // tailRecursion("SURESH")
  // H + tailRecursion("SURES")
  // S + tailRecursion("SURE")
  // E + tailRecursion("SUR")
  // R + tailRecursion("SU")
  // U + tailRecursion("S")
  // S
  def tailRecursion(s : String) : String = {
    val len = s.length
    if (len == 1) {
      s
    } else {
      s.substring(len-1, len) + tailRecursion(s.substring(0, len-1))
    }
  }

  def pointers(s : String) {
    val chars = s.toCharArray
    val pivot = s.size / 2
    var end = s.size - 1
    for(i <- 0 until pivot) {
      val temp = chars(end)
      chars(end) = chars(i)
      chars(i) = temp
      end = end - 1
    }
    println(new String(chars))
  }

//  println(simpleReverse("SURESH"))
//  println(combinatorReverse("SURESH"))
//  println(stdRecursion("SURESH"))
//  println(tailRecursion("SURESH"))
  pointers("SURESH")
}