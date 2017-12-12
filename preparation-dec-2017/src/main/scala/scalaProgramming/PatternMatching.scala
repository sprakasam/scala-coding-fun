package scalaProgramming

object PatternMatching {

  // wildcard patterns
  trait Person {
    val name: String
    val age: String
  }

  case class Adult(name: String, age: String) extends Person

  case class Child(name: String, age: String) extends Person

//  val p = Adult("John", 34)


}
