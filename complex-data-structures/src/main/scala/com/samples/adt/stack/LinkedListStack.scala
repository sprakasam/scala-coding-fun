package com.samples.adt.stack

import com.samples.adt.linked_list.Node

class LinkedListStack[String] extends MyStack[String] {

  private var tail: Node[String] = null
  private var head: Node[String] = null

  /**
   * Adds the given value into the stack
   * create a node with the given value and this node's next is None.
   * case 1: pushing the very first element into the stack - if head is null, then assign the current node to head node and head node to tail node
   * case 2: pushing the second element or in other positions - assign the current node as next node to head node and then move the head node by one position
   * @param value
   */
  def push(value: String) {
    val node = Node(value)
    if(head == null) {
      head = node
    } else {
      head.next = Some(node)
      head = node
    }
    if(tail == null) {  // tail is the first element in the stack
      tail = head
    }
  }

  /**
   * Removes the head node from the stack
   * case 1: no elements available in the stack then throw exception
   * case 2: start from tail node, pass the tail node as a parameter to a inner function, the inner function does the following steps,
   *         case 2a) only one node in the stack, then assign null to head and tail and return the current node's value
   *         case 2b) next node in the stack is the head node, then assign current node to head node and return the head node's value
   *         case 2c) call this inner func by passing the next node as a parameter
   * @return
   */
  def pop: String = {
    if(tail == null) throw new RuntimeException("No elements in the stack")
    def innerFn(tmp: Node[String]): String = {
      if(tmp.next.isEmpty) {
        tail = null
        head = null
        tmp.value
      } else if (tmp.next.get == head) {
        val result = head.value
        head = tmp
        head.next = None
        result
      }
      else {
        innerFn(tmp.next.get)
      }
    }
    innerFn(tail)
  }

  def isEmpty = tail == null

  /**
   * Returns all the elements in the stack, starting from the head node, loop through till tail node...
   * @return
   */
  def elements: List[String] = {
    var tmp = tail
    val result = new scala.collection.mutable.ListBuffer[String]
    while(tmp.next.isDefined) {
      result.prepend(tmp.value)
      tmp = tmp.next.get
    }
    result.prepend(tmp.value)
    result.toList
  }

  /**
   * Removes the given value from the stack
   * case 1: no elements in the stack so return false
   * case 2: only one element and if match with the given value remove it and return true, otherwise return false
   * case 3: start from the tail and loop through the elements in the stack. if the given value not found, then return false
   *         In case if the given value found, then move the next node's next node to current node's next
   * @param value
   */
  def remove(value: String): Boolean = {
    if(tail == null) return false

    def innerFn(tmp: Node[String]) : Boolean = {
      if(tmp.next.isDefined && tmp.next.get.value == value) {
        tmp.next = tmp.next.get.next
        true
      } else if(tmp.next.isEmpty) {
        false
      } else {
        innerFn(tmp.next.get)
      }
    }

    if(tail.next.isEmpty) {
      if(tail.value == value) {
        tail = null
        head = null
        true
      } else {
        false
      }
    } else {
      innerFn(tail)
    }
  }

  /**
   * Inserts the given value after the given node in the stack.
   * case 1: no elements in the stack then throw exception
   * case 2: only element in stack
   * case 3: do recursive starting from the tail and insert the given value if the given node is in the stack. Do a swap b/w current node and next node
   * @param currentNode
   * @param value
   */
  def insertAfter(currentNode: Node[String], value: String) {
    lazy val newNode = Node(value)
    if(tail == null) throw new RuntimeException("stack is empty!!!")
    if(tail.next.isEmpty && tail.value == currentNode.value) {
      head.next = Some(newNode)
      head = newNode
    } else {
      var tmp = tail
      while(tmp.next.isDefined) {
        if(tmp.value == currentNode.value) {
          val nextNode = tmp.next
          tmp.next = Some(newNode)
          newNode.next = nextNode
          return
        }
        tmp = tmp.next.get
      }
    }
  }
}

object LinkedListStack extends App {
  val myStack = new LinkedListStack[String]()
  myStack.push("prakasam")
  myStack.push("chitra")
  myStack.push("suresh")
  myStack.push("kokila")
  myStack.push("preetham")

  println("Elements in the stack = "+myStack.elements)
//  println("Get the first element in the stack=" +myStack.pop)
//  println("Get the second element in the stack=" +myStack.pop)
//  println("Get the third element in the stack=" +myStack.pop)

  myStack.remove("chitra")
  println("Elements in the stack after removing chitra = "+myStack.elements)

  myStack.insertAfter(Node("prakasam"), "chitra")
  println("Elements in the stack after inserting chitra = "+myStack.elements)

}
