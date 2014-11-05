package com.samples.code_eval

import scala.io.Source


/**
 *
 * Given a number n and two integers p1,p2 determine if the bits in position p1 and p2 are the same or not. Positions p1 and p2 are 1 based.
 *
 * INPUT SAMPLE:
 *
 * The first argument will be a path to a filename containing a comma separated list of 3 integers, one list per line. E.g.
 *
 *
 * 86,2,3
 * 125,1,2
 *
 * OUTPUT SAMPLE:
 *
 * Print to stdout, 'true'(lowercase) if the bits are the same, else 'false'(lowercase). E.g.
 *
 *
 * true
 * false

 */
case class Line2(n: Int, p1: Int, p2: Int) {
  def bitsAreSame() {
    val binaryChars = Integer.toString(n, 2).toCharArray.toList
    val len = binaryChars.length
    println(binaryChars(len - p1) == binaryChars(len - p2))
  }
}
object BitPositions extends App {

  val source = Source.fromFile(args(0))
  val lines = source.getLines()
  for(l <- lines) {
    val splitted = l.split(",").toList
    Line2(splitted(0).toInt, splitted(1).toInt, splitted(2).toInt).bitsAreSame()
  }
  source.close()
}
