package com.codingmaniacs.flink.playground.batch

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala.{ExecutionEnvironment, _}

object TextAnalyzer {

  val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

  def countWords(fileName: String): Seq[(String, Int)] = {
    val dataSet = environment.readTextFile(fileName)
    val count = dataSet
      .flatMap(line => line.toLowerCase.split("\\W+"))
      .filter(word => word.nonEmpty && word.length > 3)
      .map(word => (word, 1))
      .groupBy(0)
      .sum(1)
      .sortPartition(1, Order.DESCENDING).setParallelism(1)

    count.collect()
  }

}
