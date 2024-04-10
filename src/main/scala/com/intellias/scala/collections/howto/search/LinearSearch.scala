package com.intellias.scala.collections.howto.search

object LinearSearch {

  def search[T](arr: Array[T], x: T): Boolean = {
    var i = 0
    while(i < arr.length) {
      if (arr(i) == x) return true
      i += 1
    }
    false
  }

  def search[T](list: List[T], x: T): Boolean = {
    var xs = list
    while (xs.nonEmpty) {
      if (xs.head == x) return true
      xs = xs.tail
    }
    false
  }


}
