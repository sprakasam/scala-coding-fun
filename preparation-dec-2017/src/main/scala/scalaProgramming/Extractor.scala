package scalaProgramming

object Extractor extends App {

  trait User {
    def name: String
  }
  class FreeUser(val name: String) extends User
  class PremiumUser(val name: String) extends User

  object FreeUser {
    def unapply(user: FreeUser): Option[String] = Some(user.name)
  }
  object PremiumUser {
    def apply(s: String) = new PremiumUser(s)
    def unapply(user: PremiumUser): Option[String] = Some(user.name)
  }

  val user: User = new PremiumUser("Daniel")
  user match {
    case FreeUser(name) => println("Hello " + name)
    case PremiumUser(name) => println("Welcome back, dear " + name)
  }

  val f = new Foo
}
