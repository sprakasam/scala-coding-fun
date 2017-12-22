package scalaProgramming

object PatternMatching extends App{

  trait Person {
    val name: String
    val age: Int
  }

  case class Adult(name: String, age: Int) extends Person

  case class Child(name: String, age: Int) extends Person

  val p: Person = Adult("John", 34)

  // wildcard patterns
  p match {
    case Adult("John", 35) => println("matches")
    case a: Adult => println("no match")
    case c: Child => println("child")
    case _ => throw new RuntimeException("invalid match")
  }

  // constant patterns
  def constant(x: Any): Unit = {
    x match {
      case 0 => println("0")
      case "true" => println("true")
      case Nil => println("nil")
      case Math.PI => println("pi")
    }
  }
  println(constant(Nil))

  // typed patterns
  def describe(x: Any): Unit = x match { // Any extended by AnyVal and AnyRef
    case i: Int => println("int")  // Int extends AnyVal
    case s: String => println("string") // String extends AnyRef
    case _ => println("invalid type")
  }

  println(describe(1))

  // TODO: constructor patterns

  // TODO: sequence patterns

}
