package dataStructures.linkedList

object ReverseLinkedList extends App {

  case class Node(value: Int, next: Option[Node])

  var result: List[Node] = List.empty[Node]

  val node5 = Node(5, next = None)
  val node4 = Node(4, next = Some(node5))
  val node3 = Node(3, next = Some(node4))
  val node2 = Node(2, next = Some(node3))
  val root = Node(1, next = Some(node2))

  def reverse(node: Node): List[Node] = {

    if(node.next.isDefined) // traverse until last node reaches
      reverse(node.next.get)

    if (result.isEmpty)
      result = result :+ node
    else {
      result = result.updated(result.size - 1, result.last.copy(next = Some(node)))
      result = result :+ node
    }

    result
  }

  println(reverse(root).map(_.value).mkString(","))
}
