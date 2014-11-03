package com.samples.adt.trees

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class BinaryTree(var root: Node) {

  def addNodes(nodes: List[Int]) {
    nodes.foreach(addNode(_))
  }

  /**
   * Adds a node into the binary search tree.
   * Traverse the tree starting from the root node, visit the left side of the tree if the given value is less than the
   * current node otherwise visit the right side of the tree
   * @return
   */
  def addNode(value: Int, node: Node = root): Node = {
    if (value < node.value) {
      if (node.hasLeft) {
        addNode(value, node.leftChild.get)
      } else {
        node.leftChild = Some(Node(value))
        node
      }
    }
    else {
      if (node.hasRight) {
        addNode(value, node.rightChild.get)
      } else {
        node.rightChild = Some(Node(value))
        node
      }
    }
  }

  /**
   *  For example,
   *  Input =>       5
   *                / \
   *               3   10
   *              / \  / \
   *             1  4  7  12
   *
   *  Output => 4
   * @return
   */
  def leafCount: Int = {
    val isTreeBottomReached = (nodes: List[Node]) => nodes.foldLeft(true){(accumulatorFlag: Boolean, node: Node) => node.isLeaf}

    @tailrec
    def horizontalTraversal(leafCount: Int, nodes: List[Node]): Int = {

      val nodesInTheCurrentRow = nodes
      val nodesInTheNextRow = new scala.collection.mutable.ListBuffer[Node]

      var count = 0
      for(node <- nodesInTheCurrentRow) {
        count = if(node.isLeaf) count + 1 else count
        val childNodes = node.childrens
        nodesInTheNextRow.append(childNodes :_*)
      }

      if(isTreeBottomReached(nodesInTheCurrentRow.toList)) {
        count + leafCount
      } else {
        horizontalTraversal(leafCount + count, nodesInTheNextRow.toList)
      }
    }

    horizontalTraversal(0, List(root))
  }

  /**
   * Breadth First Traversal means traversing the tree in horizontal manner.
   *
   * For example,
   *  Input =>       5
   *                / \
   *               3   10
   *              / \  / \
   *             1  4  7  12
   *
   *  Output => 5, 3, 10, 1, 4, 7, 12
   *
   * Logic:
   *
   * a) Initially, the result parameter holds the root node value 5          => Map(0 -> List(5))
   * b) visit node 5, get the childrens of node 5 and add it to the result   => Map(0 -> List(5), 1 -> List(3, 10))
   * c) check whether node 3 and 5 has any childrens, if not then return the result otherwise continue traversing
   * c) visit node 3, get the childrens of node 3 and then node 10           => Map(0 -> List(5), 1 -> List(3, 10), 2 -> List(1, 4, 7, 12))
   * d) ok, now the nodes 1, 4, 7, 12 has no childrens and the bottom is reached.
   *
   *
   * @return
   */
  def breadthFirstTraversal = {

    val isTreeBottomReached = (nodes: List[Node]) => nodes.foldLeft(true){(accumulatorFlag: Boolean, node: Node) => node.isLeaf}

    @tailrec
    def horizontalTraversal(accumulator: List[Node], rowStartIndex: Int): List[Node] = {

      val nodesInTheCurrentRow = accumulator
      val nodesInTheNextRow = new scala.collection.mutable.ListBuffer[Node]

      for(index <- rowStartIndex.to(accumulator.size-1)) {
        val childNodes = accumulator(index).childrens
        nodesInTheNextRow.append(childNodes :_*)
      }

      if(isTreeBottomReached(nodesInTheCurrentRow.toList)) {
        accumulator
      } else {
        horizontalTraversal(accumulator ::: nodesInTheNextRow.toList, accumulator.size)
      }
    }

    horizontalTraversal(List(root), 0)
  }

  /**
   * Pre-order - visit the nodes in the order ROOT LEFT RIGHT.
   *
   * For example,
   *  Input =>       5
   *                / \
   *               3   10
   *              / \  / \
   *             1  4  7  12
   *
   *  Output => 5,3,1,4,10,7,12
   *
   *  Logic:
   *  a) Add the root node into a ListBuffer collection. This collection acts as a accumulator inside the recursive loop.
   *  b) The recursive traversal looks like as shown below,
   *      |preOrderTraversal(accumulator(), Node(5))
   *      |                                        |
   *      |                                         -->      |preOrderTraversal(accumulator(5), Node(3))
   *      |                                                  |                                          |
   *      |                                                  |                                           --> preOrderTraversal(accumulator(5,3), Node(1))
   *      |                                                  |                                                                                          |
   *      |                                                  |accumulator(5,3,1)  <---------------------------------------------------------------------
   *      |                                                  |
   *      |                                                   --> preOrderTraversal(accumulator(5,3,1), Node(4))
   *      |                                                                                                      |
   *      |preOrderTraversal(accumulator(5,3,1,4), Node(5))  <---------------------------------------------------
   *      |                                               |
   *      |                                                --> |preOrderTraversal(accumulator(5,3,1,4), Node(10))
   *      |                                                    |                                                |
   *      |                                                    |                                                 --> preOrderTraversal(accumulator(5,3,1,4,10), Node(7))
   *      |                                                    |                                                                                                        |
   *      |                                                    |accumulator(5,3,1,4,10,7)  <----------------------------------------------------------------------------
   *      |                                                    |
   *      |                                                     --> preOrderTraversal(accumulator(5,3,1,4,10,7), Node(12))
   *      |                                                                                                              |
   *      |preOrderTraversal(accumulator(5,3,1,4,10,7,12), Node(5)) <--------------------------------------------------
   *      |accumulator(5,3,1,4,10,7,12).toList
   *
   * @param accumulator - visited nodes in the pre-order traversal
   * @param node - refers to the current node in the recursive loop
   * @return
   */
  def preOrderTraversal(accumulator: scala.collection.mutable.ListBuffer[Node], node: Node = root) : List[Node] = {
    val temp = accumulator += node
    node.leftChild.map(preOrderTraversal(temp, _))
    node.rightChild.map(preOrderTraversal(temp, _))
    temp.toList
  }

  /**
   * In-order - visit the nodes in the order LEFT ROOT RIGHT
   *
   * For example,
   *  Input =>       5
   *                / \
   *               3   10
   *              / \  / \
   *             1  4  7  12
   *
   *  Output => 1,3,4,5,7,10,12
   *
   *  The recursive traversal looks like as shown below,
   *      |inOrderTraversal(accumulator(), Node(5))
   *      |                                        |
   *      |                                         -->      |inOrderTraversal(accumulator(), Node(3))
   *      |                                                  |                                       |
   *      |                                                  |                                        --> inOrderTraversal(accumulator(), Node(1))
   *      |                                                  |                                                                                    |
   *      |                                                  |accumulator(1) <--------------------------------------------------------------------
   *      |                                                  |
   *      |                                                   --> accumulator(1,3)
   *      |                                                                       |
   *      |                                                                        --> inOrderTraversal(accumulator(1,3), Node(4))
   *      |                                                                                                                      |
   *      |                                                   accumulator(1,3,4) <-----------------------------------------------
   *      |                                                  |
   *      |inOrderTraversal(accumulator(1,3,4), Node(5))  <--
   *      |                                            |
   *      |                                                --> |inOrderTraversal(accumulator(1,3,4,5), Node(10))
   *      |                                                    |                                               |
   *      |                                                    |                                                --> inOrderTraversal(accumulator(1,3,4,5), Node(7))
   *      |                                                    |                                                                                                  |
   *      |                                                    |accumulator(1,3,4,5,7) <--------------------------------------------------------------------------
   *      |                                                    |
   *      |                                                     --> accumulator(1,3,4,5,7,10)
   *      |                                                                                 |
   *      |                                                                                  --> inOrderTraversal(accumulator(1,3,4,5,7,10), Node(12))
   *      |                                                                                                                                          |
   *      |                                                     accumulator(1,3,4,5,7,10,12) <-------------------------------------------------------
   *      |                                                     |
   *      |accumulator(1,3,4,5,7,10,12).toList               <--
   *
   * @param accumulator - visited nodes in the in-order traversal
   * @param node - refers to the current node in the recursive loop
   * @return
   */
  def inOrderTraversal(accumulator: scala.collection.mutable.ListBuffer[Node], node: Node = root) : List[Node] = {
    node.leftChild.map(inOrderTraversal(accumulator, _))
    val updatedResult = accumulator += node
    node.rightChild.map(inOrderTraversal(updatedResult, _))
    updatedResult.toList
  }

  /**
   * Post-order - visit the nodes in the order LEFT RIGHT ROOT
   *
   * For example,
   *  Input =>       5
   *                / \
   *               3   10
   *              / \  / \
   *             1  4  7  12
   *
   *  Output => 1,4,3,7,12,10,5
   *
   *  The recursive traversal looks like as shown below,
   *      |postOrderTraversal(accumulator(), Node(5))
   *      |                                        |
   *      |                                         -->        |postOrderTraversal(accumulator(), Node(3))
   *      |                                                    |                                       |
   *      |                                                    |                                        --> postOrderTraversal(accumulator(), Node(1))
   *      |                                                    |                                                                                    |
   *      |                                                    |accumulator(1) <--------------------------------------------------------------------
   *      |                                                    |
   *      |                                                     --> postOrderTraversal(accumulator(1), Node(4))
   *      |                                                                                                   |
   *      |                                                     accumulator(1,4,3) <--------------------------
   *      |                                                    |
   *      |postOrderTraversal(accumulator(1,4,3), Node(5))  <--
   *      |                                            |
   *      |                                                --> |postOrderTraversal(accumulator(1,4,3), Node(10))
   *      |                                                    |                                               |
   *      |                                                    |                                                --> postOrderTraversal(accumulator(1,4,3), Node(7))
   *      |                                                    |                                                                                                  |
   *      |                                                    |accumulator(1,4,3,7) <----------------------------------------------------------------------------
   *      |                                                    |
   *      |                                                    | --> postOrderTraversal(1,4,3,7, Node(12))
   *      |                                                    |                                         |
   *      |                                                    |                                          --> accumulator(1,4,3,7,12)
   *      |                                                    |                                                                    |
   *      |                                                    | accumulator(1,4,3,7,12,10) <---------------------------------------
   *      |                                                                              |
   *      |accumulator(1,4,3,7,12,10,5) <------------------------------------------------
   *
   * @param accumulator - visited nodes in the in-order traversal
   * @param node - refers to the current node in the recursive loop
   */
  def postOrderTraversal(accumulator: scala.collection.mutable.ListBuffer[Node], node: Node = root) : List[Node] = {
    node.leftChild.map(postOrderTraversal(accumulator, _))
    node.rightChild.map(postOrderTraversal(accumulator, _))
    val updatedResult = accumulator += node
    updatedResult.toList
  }

  /**
   * Visit the nodes from left to right, swap the nodes right to left and left to right.
   * @param n
   */
  def mirror(n: Node = root) {
    if (n != null) {
      if (n.hasLeft) {
        val tmp = n.leftChild
        n.leftChild = n.rightChild
        n.rightChild = tmp
      }
    }
    if (n.hasLeft) {
      println("left traversal nodes ="+breadthFirstTraversal)
      println("recursive call =>"+n.leftChild.get)
      mirror(n.leftChild.get)
      println("finished call for %d, left traversal nodes ="+n.leftChild.get+"|||||"+breadthFirstTraversal)
    }
    if (n.hasRight) {
      println("right traversal nodes ="+breadthFirstTraversal)
      println("recursive call =>"+n.rightChild.get)
      mirror(n.rightChild.get)
      println("finished call for %d, right traversal nodes ="+n.rightChild.get+"|||||"+breadthFirstTraversal)

    }
  }

  def remove(node: Node): Boolean =  {

    var parent = this.root
    var focusNode = this.root

    var isAtLeftChild = true

    // find the node in the tree
    while (focusNode != node) {

      parent = focusNode

      if (node < focusNode) {
        isAtLeftChild = true
        focusNode = focusNode.leftChild.getOrElse(null)
      } else {
        isAtLeftChild = false
        focusNode = focusNode.rightChild.getOrElse(null)
      }

      if(Option(focusNode).isEmpty) return false

    }

    if(focusNode.leftChild.isEmpty && focusNode.rightChild.isEmpty) {  // case 1: focusNode is a leaf node
      if(focusNode == root)
        root = null
      else if(isAtLeftChild)
        parent.leftChild = None
      else
        parent.rightChild = None
    } else if(focusNode.rightChild.isEmpty) {  // case 2: focusNode has no right child
      if(focusNode == root)
        root = focusNode.leftChild.get
      else if(isAtLeftChild)
        parent.leftChild = focusNode.leftChild
      else
        parent.rightChild = focusNode.leftChild
    } else if(focusNode.leftChild.isEmpty) { // case 3: focusNode has no left child
      if(focusNode == root)
        root = focusNode.rightChild.get
      else if(isAtLeftChild)
        parent.leftChild = focusNode.rightChild
      else
        parent.rightChild = focusNode.rightChild
    } else {                 // case 4: focusNode has both right child and left child

      val replacementNode = findReplacementNode(focusNode)

      if(focusNode == root)
        root = replacementNode
      else if(isAtLeftChild) {
        parent.leftChild = Some(replacementNode)
      }
      else
        parent.rightChild = Some(replacementNode)
    }

    true
  }

  def findReplacementNode(replacedNode: Node) = {
    var replacementParent = replacedNode
    var replacement = replacedNode

    var focusNode = replacedNode.rightChild

    while(focusNode.isDefined) {
      replacementParent = replacement
      replacement = focusNode.get
      focusNode = focusNode.get.leftChild
    }

    replacement.leftChild = replacedNode.leftChild
    replacement
  }

  /**
   * Finds the node in the binary tree.
   * @param value
   * @return
   */
  def findNode(value: Int) : Node = {
    var focusNode = root
    while(focusNode.value != value) {

      if(value < focusNode.value) {
        focusNode = focusNode.leftChild.get
      } else {
        focusNode = focusNode.rightChild.get
      }
      if(Option(focusNode).isEmpty) return null
    }

    focusNode
  }

  /**
   *
   * @param sum
   * @param node
   * @return
   */
  def hasPathSum(sum: Int, node: Node = root) : Boolean = {
    val subSum = sum - node.value
    (node.hasLeft, node.hasRight) match {
      case (false, false) => subSum == 0 // tree bottom reached
      case (true, false)  => hasPathSum(subSum, node.leftChild.get) // left side traversal
      case (false, true)  => hasPathSum(subSum, node.rightChild.get)
      case (true, true)   => hasPathSum(subSum, node.leftChild.get) || hasPathSum(subSum, node.rightChild.get)
    }
  }

  /**
   * Lowest common ancestor node's value will not be higher than given 2 node's value or lower than given 2 node's value.
   * It should be between them. Visit each node and node's value is greater than given node's value then go left other wise
   * if it's lesser go right or else return the lowest common ancestor.
   * @param child1
   * @param child2
   * @return
   */
  def findLowestCommonAncestor(child1: Node, child2: Node): Node = {

    var tmp = Option(root)
    while(tmp.isDefined) {
      val value = tmp.get.value
      if(value > child1.value && value > child2.value) {
        tmp = tmp.get.leftChild
      } else if(value < child1.value && value < child2.value) {
        tmp = tmp.get.rightChild
      } else {
        return tmp.get
      }
    }
    null
  }

}

case class Node(value: Int, var leftChild: Option[Node] = None, var rightChild: Option[Node] = None) {
  override def toString = value + ""

  def childrens: List[Node] = {
    val result = new scala.collection.mutable.ListBuffer[Node]()
    if (this.leftChild.isDefined) result += this.leftChild.get
    if (this.rightChild.isDefined) result += this.rightChild.get
    result.toList
  }

//  def !=(other: Node): Boolean = this.value != other.value

  def ==(other: Node): Boolean = this.value == other.value

  def <(other:Node): Boolean = this.value < other.value

  def hasLeft = leftChild.isDefined

  def hasRight = rightChild.isDefined

  def isLeaf = !hasLeft && !hasRight
}

object BinaryTree extends App {
  val tree = new BinaryTree(Node(5))
  tree.addNodes(List(3, 10, 1, 4, 7, 12))

  println("The output of leaf count is "+tree.leafCount)

  println("The output of BFT is ")
  tree.breadthFirstTraversal.foreach{n => print(n +",")}

  println()
  println("The output of Pre-Order Traversal is ")
  tree.preOrderTraversal(ListBuffer()).foreach{n => print(n +",")}

  println()
  println("The output of In-Order Traversal is ")
  tree.inOrderTraversal(scala.collection.mutable.ListBuffer[Node]()).foreach{n => print(n +",")}

  println()
  println("The output of Post-Order Traversal is ")
  tree.postOrderTraversal(scala.collection.mutable.ListBuffer[Node]()).foreach{n => print(n +",")}

  println()
  println("Does the tree has sum 22 => "+tree.hasPathSum(22))

  println()
//  println("The path %s has sum 22 => "+tree.pathHasSum(22, nodes = scala.collection.mutable.ListBuffer[Node](tree.root)).toString())
//  println("The lowest common ancestor for 4 and 7 is "+ tree.findLowestCommonAncestor(tree.findNode(4), tree.findNode(7)))
//  println("Finding the node 11 in the tree ")
//  tree.breadthFirstTraversal.foreach{n => print(n +",")}
//  println(" is ")
//  print(tree.findNode(11))

  println()
  println("The mirror of the binary tree => ")
  tree.breadthFirstTraversal.foreach{n => print(n +",")}
  tree.mirror()
  print(" is ")
  tree.breadthFirstTraversal.foreach{n => print(n +",")}
//
//    println()
//    tree.remove(Node(10))
//    println("After removing node 10, the output is")
//    tree.breadthFirstTraversal.foreach{n => print(n +",")}
}

