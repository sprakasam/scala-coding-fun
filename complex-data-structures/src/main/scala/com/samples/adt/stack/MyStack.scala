package com.samples.adt.stack

import com.samples.adt.linked_list.Node

trait MyStack[A] {

  def push(value: A)

  def pop: A

  def isEmpty: Boolean

  def remove(value: A): Boolean

  def insertAfter(node: Node[A], value: A)

}
