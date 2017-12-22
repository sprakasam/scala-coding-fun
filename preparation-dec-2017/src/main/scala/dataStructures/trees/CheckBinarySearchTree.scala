package dataStructures.trees

object CheckBinarySearchTree extends App {

  case class Node(data:Int, left: Option[Node], right: Option[Node])

  def isBST(root: Node, nodes: List[Node]): Boolean = {
    if (nodes.isEmpty)
      true
    else {
      val isLeafLevelReached = nodes.count(n => n.left.isEmpty && n.right.isEmpty) == nodes.size
      if (isLeafLevelReached)
        true
      else {
        val isWrongBST = nodes.exists(n => n.left.get.data > n.data || n.right.get.data < n.data)
        if (isWrongBST)
          false
        else {
          val currentBranchNodes = nodes.foldLeft(List.empty[Node])((accum, n) => accum ++ List(n.left.get, n.right.get))

          isBST(root, currentBranchNodes)
        }
      }
    }
  }

  val n1 = Node(1, None, None)
  val n3 = Node(3, None, None)
  val n5 = Node(5, None, None)
  val n7 = Node(7, None, None)
  val n2 = Node(2, Some(n1), Some(n3))
  val n6 = Node(6, Some(n5), Some(n7))
  val root = Node(4, Some(n2), Some(n6))

  println(isBST(root, List(root)))

}
