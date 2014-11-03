package com.samples

object ReverseWordOrders extends App {

  // Time complexity is O(n ^ 2)
  def reverseString(input: Array[Char]) {  // input = "I like my job"
    var start = 0
    var end = 0
    val length = input.length
    // reverse the entire string
    reverseWord(input, start, length - 1)  // input = "boj ym ekil I"

    // read the string from the back
    while(end < length) {       // 0 < 13
      if(input(end) != ' ') {
        start = end   // save position of beginning of word  start = 0, end = 0

        while(end < length && input(end) != ' ') {  // 0 < 13
          end = end +1            // end = 1
        }

        end = end - 1   // Back up to end of word

        reverseWord(input, start, end)   // reverse the word
      }
      end = end + 1 // advance to the next location
    }

    def reverseWord(input: Array[Char], s: Int, e: Int) {
      var temp: Char = '0'
      var start = s
      var end = e
      while(start < end) {
        temp = input(start)
        input(start) = input(end)
        input(end) = temp
        start = start + 1
        end = end - 1
      }

    }
  }

  val input = "I like my job".toCharArray
  reverseString(input)
  input.foreach(print(_))
}
