package dataStructures.trees

object BinaryTreeDFS extends App {

  case class Node(data:Int, left: Option[Node], right: Option[Node])

  // visit the nodes in the order root, left , right
  def preorder(node: Node): Unit = {
    println(node.data)
    node.left.map(n => preorder(n))
    node.right.map(n => preorder((n)))
  }

  // visit the nodes in the order left, root , right
  def inorder(node: Node): Unit = {
    node.left.map(n => inorder(n))
    println(node.data)
    node.right.map(n => inorder((n)))
  }

  // visit the nodes in the order left, right, root
  def postorder(node: Node): Unit = {
    node.left.map(n => postorder(n))
    node.right.map(n => postorder((n)))
    println(node.data)
  }

  val n1 = Node(1, None, None)
  val n3 = Node(3, None, None)
  val n5 = Node(5, None, None)
  val n7 = Node(7, None, None)
  val n2 = Node(2, Some(n1), Some(n3))
  val n6 = Node(6, Some(n5), Some(n7))
  val root = Node(4, Some(n2), Some(n6))

  println("===========================PREORDER=======================")
  preorder(root)
  println("===========================INORDER=======================")
  inorder(root)
  println("===========================POSTORDER=======================")
  postorder(root)
}
