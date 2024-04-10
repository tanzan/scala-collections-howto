package com.intellias.scala.collections.howto.search

import org.scalameter.Bench
import org.scalameter.api._

import scala.util.Random

class SearchBenchmark extends Bench.OfflineReport {

  override def online = true

  private val sizes = Gen.range("size")(2_000_000, 100_000, -100_000)

  private val listRanges = for {
    size <- sizes
  } yield (size, (0 until size).toList)

  private val arrayRanges = listRanges.map(tuple => tuple.copy(_2 = tuple._2.toArray))

  performance of "Search" in {
    measure method "listSearch" in {
      using(listRanges) in {
        case (size, r) =>
          val target = Random.nextInt(size)
          LinearSearch.search(r, target)
      }
    }

    measure method "arraySearch" in {
      using(arrayRanges) in {
        case (size, r) =>
          val target = Random.nextInt(size)
          LinearSearch.search(r, target)
      }
    }

    measure method "binarySearch" in {
      using(arrayRanges) config(exec.minWarmupRuns := 60, exec.maxWarmupRuns := 60, exec.benchRuns := 100) in {
        case (size, r) =>
          val target = Random.nextInt(size)
          r.search(target)
      }
    }
  }
}
