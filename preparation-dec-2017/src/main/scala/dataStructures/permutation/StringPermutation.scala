package dataStructures.permutation

/**
 * This program prints the different dataStructures.permutation of a given word
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

  def permute(input: Array[Char], index: Int, length: Int) : Unit = {
    if(index == length) {
      println(new String(input))
    }

    for(j <- index until length) {
      swap(input, j, index)
      permute(input, index + 1, length)
      swap(input, j, index) // make the array same before swap
    }
  }

  def swap(input: Array[Char], a: Int, b:Int) : Array[Char] = {
    val temp = input(a)
    input(a) = input(b)
    input(b) = temp
    input
  }



  permute("abc".toCharArray, 0, 3)
}
