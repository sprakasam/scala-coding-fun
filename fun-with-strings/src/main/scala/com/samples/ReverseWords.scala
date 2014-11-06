package com.samples

import scala.io.Source

case class Line1(line: String) {
  def printReverseWords() {
    if(line.size > 0 ) {
      println ( line.split(" ").reverse.foldLeft("")(_ +" "+ _) )
    }
  }
}
object ReverseWords extends App {

  val source = Source.fromFile(args(0))
  val lines = source.getLines()
  for(l <- lines) Line1(l).printReverseWords()
  source.close()
}
