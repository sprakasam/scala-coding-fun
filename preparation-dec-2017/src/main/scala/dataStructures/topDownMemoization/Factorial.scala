package dataStructures.topDownMemoization

object Factorial extends App {

  // Top Down approach
  def fibTopDown(n: Int, memo: Array[Int]): Int = {

    if (n <= 2) {
      memo(n) = 1
      println("before==="+ memo.mkString(",") + ",n=" + n )

      return memo(n)
    } else {
      println("after===" + memo.mkString(",") + ",n=" + n)

      memo(n) = fibTopDown(n - 1, memo) + fibTopDown(n - 2, memo)
    }


    return memo(n)
  }


  println(fibTopDown(5, Array.fill(6)(0)))

}
