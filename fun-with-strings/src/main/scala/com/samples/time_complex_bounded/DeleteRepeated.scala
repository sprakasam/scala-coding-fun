package com.samples.time_complex_bounded

/**
 * This program deletes the characters from the given string.
 * Note: Time Complexity should be O(n)
 *
 * INPUT:
 * scala is awesome
 * and delete "as"
 *
 * OUTPUT:
 * cl i weome
 */
object DeleteRepeated extends App {

  // Time complexity - O(n)
  def removeRepeated(str: String, remove: String) : String = {
    val s = str.toCharArray
    val r = remove.toCharArray
    val flags = new Array[Boolean](128) // assumes ASCII

    // Iterate through each character in the remove, setting the corresponding value in the lookup array to be true
    val removeLen = remove.length
    for(i <- 0 until removeLen) {
      flags(r(i)) = true
    }

    // Iterate through str with a source and dest index, copying each character only if its corresponding value in the lookup array is false
    var srcIndex = 0
    var destIndex = 0
    val strLen = str.length
    while(srcIndex < strLen) {      // Iterate through str
      val srcCharAscii = s(srcIndex).toInt
      if(!flags(srcCharAscii)) { // character that are not deleted
        s(destIndex) = s(srcIndex) // copy the character
        destIndex = destIndex + 1
      }
      srcIndex = srcIndex + 1
    }
    new String(s, 0, destIndex)
  }

  println("""Removed the character %c from the word %s and the result is %s""".format('a', "scala is awesome", removeRepeated("scala is awesome", "a")))

  println("""Removed the character seq %s from the word %s and the result is %s""".format("as", "scala is awesome", removeRepeated("scala is awesome", "as")))

}
