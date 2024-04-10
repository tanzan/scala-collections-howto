package com.intellias.scala.collections.howto.sort

import org.scalameter.Bench
import org.scalameter.api._

import scala.util.Random

class SortBenchmark extends Bench.OfflineReport {

  override def online = true

  private val sizes = Gen.range("size")(1000, 20000, 1000)

  private val arrayRanges = for {
    size <- sizes
  } yield Random.shuffle((0 until size).toArray[Int]).toArray

  private val sortedRanges = for {
    size <- sizes
  } yield (0 until size).toArray[Int]

  performance of "Sort" in {
    measure method "quickSort" config(exec.minWarmupRuns := 100, exec.maxWarmupRuns := 100, exec.benchRuns := 500) in {
      using(arrayRanges) in { r =>
        QuickSort.sort(r)
      }
    }

    measure method "sortedQuickSort" in {
      using(sortedRanges) in { r =>
        QuickSort.sort(r)
      }
    }

    measure method "copyArray" in {
      using(arrayRanges) config(exec.minWarmupRuns := 100, exec.maxWarmupRuns := 100, exec.benchRuns := 1500) in { r =>
        Array.copyAs[Int](r, r.length)
      }
    }
  }

}
