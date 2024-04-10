package com.intellias.scala.collections.howto.search

object BinarySearch {

  def search[T](arr: Array[T], x: T)(implicit ord: Ordering[T]): Boolean = {
    var l = 0
    var r = arr.length - 1
    while (l <= r) {
      val mid = l + (r - l) / 2
      if (ord.equiv(arr(mid), x)) return true
      else if (ord.lt(arr(mid), x)) l = mid + 1
      else r = mid - 1
    }
    false
  }
}
