package dataStructures.subsequence

// using matrix of characters
object LongestCommonSubSequence extends App {


  val s1 = "AGGTAB"
  val s2 = "GXTXAYB"
  val X = s1.toCharArray
  val Y = s2.toCharArray
  val m = X.length
  val n = Y.length

  def lcs(X: Array[Char], Y: Array[Char], m: Int, n: Int): Int = {
    if (m == 0 || n == 0)
      0
    else if (X(m - 1) == Y(n - 1))
      1 + lcs(X, Y, m - 1, n - 1)
    else
      max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n))
  }

  /* Utility function to get max of 2 integers */
  def max(a: Int, b: Int) = if (a > b) a else b

  System.out.println("Length of LCS is" + " " + lcs(X, Y, m, n))
}
