package dataStructures.trees


object BinaryTreeOperations extends App {

  case class Node(data:Int, var left: Option[Node], var right: Option[Node])

  val values = List(1,2,3,4,5,6,7)

  def createTree(values: List[Int], start: Int, end: Int): Option[Node] = {
    if(end < start)
      return None
    val mid = (start + end) / 2
    val n = Node(values(mid), None, None)
    n.left = createTree(values, start, mid - 1)
    n.right = createTree(values, mid + 1, end)

    Some(n)
  }

  // visit the nodes in the order left, root , right
  def inorder(node: Node): Unit = {
    node.left.map(n => inorder(n))
    print(node.data +",")
    node.right.map(n => inorder((n)))
  }

  def mirror(n: Node): Unit = {
    if(n != null) {
      if(n.left.isDefined) {
        val temp = n.left
        n.left = n.right
        n.right = temp
      }

      n.left.map(mirror(_))
      n.right.map(mirror(_))
    }
  }

  val root = createTree(values, 0, values.size - 1)

  inorder(root.get)

  mirror(root.get)

  println()

  inorder(root.get)

}
