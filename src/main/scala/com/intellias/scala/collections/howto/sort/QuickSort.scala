package com.intellias.scala.collections.howto.sort

import scala.reflect.ClassTag

object QuickSort {

  def sort[T : Ordering : ClassTag](input: Array[T]): Array[T] = {
    val ord = implicitly[Ordering[T]]
    val output = Array.copyAs[T](input, input.length)

    def swap(i: Int, j: Int): Unit = {
      val tmp = output(i)
      output(i) = output(j)
      output(j) = tmp
    }

    def partition(low: Int, hi: Int): Int = {
      val pivot = hi
      var i = low
      for {
        j <- low to hi
        if ord.lt(output(j), output(pivot))
      } {
        swap(i, j)
        i += 1
      }
      swap(i, pivot)
      i
    }

    def doSort(low: Int, hi: Int): Unit = {
      var stack = (low, hi) :: Nil
      while (stack.nonEmpty) {
        val (low, hi) = stack.head
        stack = stack.tail
        if (low < hi) {
          val p = partition(low, hi)
          stack = (low, p - 1) :: stack
          stack = (p + 1, hi) :: stack
        }
      }
    }

    doSort(0, input.length - 1)
    output
  }
}
