package com.intellias.scala.collections.howto.sort

import scala.reflect.ClassTag

object InsertionSort {

  def sort[T : Ordering : ClassTag](input: Array[T]): Array[T] = {
    val ord = implicitly[Ordering[T]]
    val output = Array.copyAs[T](input, input.length)
    for {
      i <- 1 until output.length
      key = output(i)
    } {
      var j = i - 1
      while (j >= 0 && ord.gt(output(j), key)) {
        output(j + 1) = output(j)
        j -= 1
      }
      output(j + 1) = key
    }
    output
  }
}
