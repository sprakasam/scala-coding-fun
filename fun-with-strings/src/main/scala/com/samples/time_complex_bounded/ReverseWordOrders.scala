package com.samples.time_complex_bounded

/**
 * This program reverses the given sentence
 *
 * INPUT:
 * I like my job
 *
 * OUTPUT:
 * job my like I
 */
object ReverseWordOrders extends App {

  val input = "I like my job"
  val result = input.split(" ").foldLeft("")((a, b) => b + " " +a).trim()
  println(result)
}
