package com.samples.code_eval

import scala.annotation.tailrec
import scala.util.{Success, Try}

/**
 *   INPUT SAMPLE:
 *
 *   Your program should accept a file as its first argument. The file contains multiple separated lines; each line contains 3 numbers
 *   that are space delimited. The first number is the first divider (X), the second number is the second divider (Y), and the third number
 *   is how far you should count (N). You may assume that the input file is formatted correctly and the numbers are valid positive integers.
 *
 *   For example:
 *   3 5 10
 *   2 7 15
 *
 *   OUTPUT SAMPLE:
 *
 *   Print out the series 1 through N replacing numbers divisible by X with “F”, numbers divisible by Y with “B” and numbers
 *   divisible by both with “FB”. Since the input file contains multiple sets of values, your output should print out one line
 *   per set. Ensure that there are no trailing empty spaces in each line you print.
 *
 *
 *   1 2 F 4 B F 7 8 F B
 *   1 F 3 F 5 F B F 9 F 11 F 13 FB 15
 *   CONSTRAINTS:
 *
 *   The number of test cases ≤ 20
 *   "X" is in range [1, 20]
 *   "Y" is in range [1, 20]
 *   "N" is in range [21, 100]
 */


case class Line(firstDivisor: Int, secondDivisor: Int, count: Int) {

  def validateInput() = {
    require(firstDivisor >= 1 && firstDivisor <= 20, "first divisor is not in range  1, 20")
    require(firstDivisor >= 1 && firstDivisor <= 20, "second divisor is not in range 1, 20")
    require(count >= 21 && firstDivisor <= 100, "count is not in range 21, 100")
    true
  }

  @tailrec
  final def fizzBuzzes(sb: StringBuilder = new StringBuilder(), n: Int = 1): String = {
    if (n > count) {
      sb.toString()
    } else {
      val fizzOrBuzz = if (n % firstDivisor == 0 && n % secondDivisor == 0) "FB " else if (n % firstDivisor == 0) "F " else if (n % secondDivisor == 0) "B " else n.toString + " "
      fizzBuzzes(sb.append(fizzOrBuzz), n + 1)
    }
  }
}

object FizzBuzz extends App {

  val source = scala.io.Source.fromFile(args(0))

  val lines = source.getLines.filter(_.length > 0)

  for (l <- lines) {
    val splitted = l.split(" ")
    val line = Line(splitted(0).toInt, splitted(1).toInt, splitted(2).toInt)
    Try(line.validateInput()) match {
      case Success(b) => println(line.fizzBuzzes())
      case _          => println("invalid line, check the constraints")
    }
  }

  source.close()
}