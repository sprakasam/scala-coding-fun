package com.samples.adt.linked_list

import scala.collection.mutable.ListBuffer

class MyLinkedList[A] {
  private var currentNode: Node[A] = null
  private var root: Node[A] = null

  def insertInFront(value: Node[A]) {
    value.next = Some(this.root) // Assign the root as next to the given node
    this.root = value
  }

  /**
   * Check while adding the first element in to the list. While adding from 2nd element into the list,
   * first assign the next pointer of current element to the given element and then move the current element's pointer
   * to point to the given element.
   *
   * Initialize the tail only once to the first element in the list
   * @param value
   */
  def add(value: Node[A]) {
    if(currentNode == null) {
      currentNode = value
    } else {
      currentNode.next = Some(value)     // Attach the given value as the next node of the current node
      currentNode = value                // Move the current node pointer to the given node
    }

    if(root == null) {
      root = currentNode
    }
  }

  /**
   * Recursively iterate till the end of the list and store the values inside a list buffer
   * Time Complexity - O(n)
   * @return
   */
  def values: List[Node[A]] = {
    // start from the root node and loop through all the nodes based on the next pointer till the tail comes
    def traverse(currentNode: Node[A], result: ListBuffer[Node[A]]) : List[Node[A]] = {
      if (currentNode.next.isDefined) {
        traverse(currentNode.next.get, result += currentNode)
      } else {
        result += currentNode
        result.toList
      }
    }
    traverse(root, new ListBuffer[Node[A]])
  }

  /**
   * Algorithm Time Complexity - O(n) + O(n/2) => O(2n) => O(n)
   * Declare 2 pointers and assign root node reference to them. Iterate through till the end of the list.
   * Inside the loop,
   *  a) increment the counter,
   *  b) whenever the counter is divisible by 2, then move the second pointer.
   *  c) move the first pointer
   * Return the second pointer which is the middle element in the linked list
   *
   * @return
   */
  def findMiddle : Node[A] = {
    var firstPointer = root
    var secondPointer = root
    var count = 0

    while(firstPointer.next.isDefined) {
      count = count + 1
      if(count % 2 == 0) secondPointer = secondPointer.next.get
      firstPointer = firstPointer.next.get
    }

    secondPointer
  }

  /**
   * Insert the value in the given position
   */
  def insertBefore(value: Node[A], position: Int) {
    var tmp = root
    for(i <- 0 until position-2) {
      tmp = tmp.next.getOrElse(throw new RuntimeException("invalid position %d".format(position)))
    }
    value.next = tmp.next
    tmp.next = Some(value)
  }

  /**
   * Time Complexity - O(n-1) => O(n) worst case
   * @param value
   * @return
   */
  def delete(value: Node[A]): Boolean = {
    def innerFn(tmp: Node[A]): Boolean = {
      if (tmp.next.isDefined && value == tmp.next.get) {
        tmp.next = value.next
        true
      }
      else if (tmp.next.isDefined) innerFn(tmp.next.get)
      else throw new NoSuchElementException("")
    }
    innerFn(root)
  }

  def find(value: A): Node[A] = {
    def innerFn(tmp: Node[A]): Node[A] = {
      if (value == tmp.value) tmp
      else if (tmp.next.isDefined) innerFn(tmp.next.get)
      else throw new NoSuchElementException("")
    }
    innerFn(root)
  }

  /**
   * 2 pass algorithm
   *
   * Move the first pointer to one element before to the given position in the list. Initialize the second pointer.
   * Iterate through the list using the first pointer till the end of the list. Inside the loop, move the second pointer
   * along with first pointer where as the first pointer is ahead of second point by n elements.
   * Once the loop is done, then the second pointer behind n elements from the first point which is the result.
   * @param n
   * @return
   */
  def findNthElementFromTail(n: Int) : Node[A] = {
    var firstPointer = root
    for(i <- 0 until n - 1) {
      if(firstPointer.next.isDefined) {
        firstPointer = firstPointer.next.get  // move the first pointer to 5th element
      } else {
        throw new NoSuchElementException("")
      }
    }

    var secondPointer = root

    while(firstPointer.next.isDefined) {
      firstPointer = firstPointer.next.get
      secondPointer = secondPointer.next.get
    }

    secondPointer
  }
}

case class Node[A](value: A, var next: Option[Node[A]] = None) {
  override def toString = value.toString
}


object LinkedListManager extends App {
  val myLinkedList = new MyLinkedList[Int]

  for(i <- 1 until 10) myLinkedList.add(Node(i))

  println("Elements in the linked list: " + myLinkedList.values)

  println("The middle element = "+ myLinkedList.findMiddle)

  myLinkedList.insertInFront(Node(0))

  println("After adding 0 in front, elements in the linked list: " + myLinkedList.values)

  val third = myLinkedList.find(2)
  println("The third element in the linked list is " + third)

  myLinkedList.delete(third)
  println("After deleting third element, the linked list is "+myLinkedList.values)

  println("4th element from the tail = "+myLinkedList.findNthElementFromTail(4))

  myLinkedList.insertBefore(Node(2), 3)
  println("inserted value 2 in the 3rd position = "+myLinkedList.values)

}