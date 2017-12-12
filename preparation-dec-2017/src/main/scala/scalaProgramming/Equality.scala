package scalaProgramming

object Equality extends App {

  class Foo(a: Int) {}
  val f1 = new Foo(1)
  val f2 = new Foo(1)
  println("Foo=" + (f1 == f2))

  case class Bar(a: Int)
  val b1 = Bar(1)
  val b2 = Bar(1)
  println("Bar=" + (b1 == b2))

}

