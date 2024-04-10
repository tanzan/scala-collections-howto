
package com.intellias.scala.collections.howto.sort

import org.scalameter.Bench
import org.scalameter.api._

import scala.util.Random

class InsertionSortBenchmark extends Bench.OfflineReport {

  override def online = true

  private val sizes = Gen.range("size")(50, 1000, 50)

  private val arrayRanges = for {
    size <- sizes
  } yield Random.shuffle((0 until size).toArray[Int]).toArray


  performance of "Sort" config(exec.minWarmupRuns := 60, exec.maxWarmupRuns := 60, exec.benchRuns := 1000)  in {
    measure method "quickSort" in {
      using(arrayRanges) in { r =>
        QuickSort.sort(r)
      }
    }

    measure method "insertionSort" in {
      using(arrayRanges) in { r =>
        InsertionSort.sort(r)
      }
    }
  }

}
