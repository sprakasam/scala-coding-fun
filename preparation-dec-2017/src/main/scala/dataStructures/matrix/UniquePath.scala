package dataStructures.matrix

object UniquePath extends App {

  val matrix = Array(Array(1,2,3,4,5,6,7,8,9))
  val M = 3
  val N = 3

  // populate the path values
  val pathValueArrayArray: Array[Array[Int]] = Array.ofDim[Int](M + 1, N + 1)

  for(i <- 0 to M; j <- 0 to N if i == 0 || j == 0)
    pathValueArrayArray(i)(j) = 1

  for(i <- 1 until M; j <- 1 until N)  {
    val v = pathValueArrayArray(i-1)(j) + pathValueArrayArray(i)(j-1)
    println(s"i=$i, j=$j, value = $v")
    pathValueArrayArray(i)(j) = v
  }

  println(pathValueArrayArray(M-1)(N-1))

}
