package zignalLabs

object JakeCodingExercise extends  App {
  val input = List(5, 6, 2, 4)
  val number = 35

  // output should be true for 8

  // O(n * n)
  def checkSumExists(inputNumber: Int): Boolean = {
    var result = false
    for (i <- 0 until input.size) { // O(n)
      val restOfTheList = input.filter(_ != input(i))
      for (j <- 0 until (input.size - 1)) { // O(n-1)
        if (!result && input(i) + restOfTheList(j) == inputNumber)
          result = true
      }
    }
//    var memo: Set[Int] = Set(input.head)
//    var j = 0
//    for(i <- 1 until input.size) {
//      j = i
//      while(!result && j > 0) {
//        if(input(i) + memo(j) == number)
//          result = true
//        j = j - 1
//      }
//
//      if(!result)
//        memo = memo :+ input(i)
//
//    }
    result
  }

  println(s"List has 2 numbers that add up to $number: ${

  import scala.collection.immutable.HashMap

  checkSumExists(number)}")

}
