package scalaProgramming

object ControlExpressions extends App {

  for (i <- 1 to 5; j <- 1 to 5) {

    print (s"($i,$j)" + " ")
    if (j == 5) {
      println()
    }
  }

}
