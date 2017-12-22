package dataStructures.matrix

import scala.annotation.tailrec

object MaxSumPath extends App {
  var N = 4
  var M = 6

  def findMaxPath1(matrix: List[List[Int]]): Int = {
    @tailrec
    def traverse(remaining: List[List[Int]],
                 previousRowIndex: Int,
                 sumAccumulator: Int): Int = {
      remaining match {
        case Nil => sumAccumulator
        case head :: tail =>
          if (remaining.size == matrix.size) {
            val (value, index) = head.zipWithIndex.maxBy(_._1)
            traverse(tail, index, sumAccumulator + value)
          } else {
            val left = previousRowIndex - 1
            val right = previousRowIndex + 1
            val value1 = if(left >= 0) (head(left), left)  else (0, 0)
            val value2 = if(previousRowIndex + 1 < head.size) (head(right), right) else (0, 0)
            val maxValue = List(value1, value2).maxBy(_._1)
            traverse(tail, maxValue._2, sumAccumulator + maxValue._1)
          }
      }
    }
    traverse(matrix, 0, 0)
  }

  val N1 = 3
  val M1 = 4
  def validPaths(matrix: List[List[Int]], row: Int, col: Int): List[(Int, Int, Int)] = {
    def rightPath(cost: Int) = (row + 1, col, cost)
    def downPath(cost: Int) = (row, col + 1, cost)
    if(row + 1 < N1 && col + 1 < M1) {
      List(rightPath(matrix(row+1)(col)), downPath(matrix(row)(col+1)))
    } else if(row + 1 == N1 && col + 1 < M1) {
      List(downPath(matrix(row)(col+1)))
    } else if(row + 1 < N1 && col + 1 == M1) {
      List(rightPath(matrix(row+1)(col)))
    } else {
      Nil
    }
  }

  def findMaxPath(matrix: List[List[Int]], row: Int, col: Int, maxPathSum: Int): Int = {
    val vp = validPaths(matrix, row, col)
    if(vp.isEmpty)
      maxPathSum
    else {
      val nextElementPath: (Int, Int, Int) = vp.minBy(_._3)

      println(vp.map(_._3).mkString("|") +"|"+ nextElementPath._3)
      findMaxPath(matrix, nextElementPath._1, nextElementPath._2, maxPathSum + nextElementPath._3)
    }
  }

  val mat = Array(Array(10, 10, 2, 0, 20, 4), Array(1, 0, 0, 30, 2, 5), Array(0, 10, 4, 0, 2, 0), Array(1, 0, 2, 20, 0, 4))
  System.out.println(findMaxPath1(mat.map(_.toList).toList) )

  val min = Array(Array(1,3,5,8), Array(4,2,1,7), Array(4,3,2,3))

  System.out.println(findMaxPath(min.map(_.toList).toList, 0, 0, min(0)(0)))
}
