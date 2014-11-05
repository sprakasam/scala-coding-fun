package com.samples

import scala.util.{Success, Try}

/**
 *
    Write a program which sorts numbers.

    INPUT SAMPLE:

    Your program should accept as its first argument a path to a filename. Input example is the following

    70.920 -38.797 14.354 99.323 90.374 7.581
    -37.507 -3.263 40.079 27.999 65.213 -55.552
    OUTPUT SAMPLE:

    Print sorted numbers in the following way. Please note, that you need to print the numbers till the 3rd digit after the dot including trailing zeros.

    -38.797 7.581 14.354 70.920 90.374 99.323
    -55.552 -37.507 -3.263 27.999 40.079 65.213
 */

case class Line3(str: String) {

  def numbers: List[Float] = str.split(" ").map(_.toFloat).toList

//  def sort: List[Float] = {
//    def doRecrusive(numbers: List[Float], accum: List[Float]): List[Float] = {
//      numbers match {
//        case Nil          =>  accum
//        case head :: tail =>
//      }
//    }
//  }
}

object NumbersSorting extends App {
//  val source = scala.io.Source.fromFile(args(0))
//
//  val lines = source.getLines.filter(_.length > 0)
//
//  for (l <- lines) {
//    val splitted = l.split(" ")
//    val line = Line(splitted(0).toInt, splitted(1).toInt, splitted(2).toInt)
//    Try(line.validateInput()) match {
//      case Success(b) => println(line.sort)
//      case _          => println("invalid line, check the constraints")
//    }
//  }
//
//  source.close()
}
