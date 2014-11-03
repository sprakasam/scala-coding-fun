package com.samples

import java.util.{Hashtable => HTable}

object FirstNonRepeated extends App {

  // Time complexity - O(n)
  def findNonRepeated(str: String) : Char =  {
    val charHash = new HTable[Char, Int]()
    val length = str.length
    // scan the string and build the hash table
    for(i <- 0 until length) {
      val char = str.charAt(i)
      Option(charHash.get(char)).map(count => charHash.put(char, count + 1)).getOrElse(charHash.put(char, 1))
    }

    for(i <- 0 until length) {
      val char = str.charAt(i)
      if(charHash.get(char) == 1) return char
    }
    '0'
  }

  println("""The first non-repeated character in the word "%s" is %c""".format("suresh babu", findNonRepeated("suresh babu")))
}
