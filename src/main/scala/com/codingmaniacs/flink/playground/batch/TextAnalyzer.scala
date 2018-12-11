package com.codingmaniacs.flink.playground.batch

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala._

object TextAnalyzer {

  val MIN_WORD_LENGTH = 3

  def countWords(fileName: String)(implicit environment: ExecutionEnvironment): Seq[(String, Int)] = {
    val dataSet = environment.readTextFile(fileName)
    val words = transformDataSet(dataSet)
    words.collect()
  }

  def countTopNWords(fileName: String, numberOfWords: Int)(implicit environment: ExecutionEnvironment): Seq[(String, Int)] = {

    val dataSet = environment.readTextFile(fileName)

    // First will return a the first n elements from this data set, but It doesn't guarantee the order if the
    // parallelism is greater than 1
    val words = transformDataSet(dataSet).first(numberOfWords)

    words.collect().sortBy(_._1)
  }

  val isValidWord: String => Boolean = {
    case w if w == null || w.trim.isEmpty => false
    case w if w.length <= MIN_WORD_LENGTH => false
    case _ => true
  }

  val transformDataSet: DataSet[String] => DataSet[(String, Int)] = (baseData: DataSet[String]) => {
    baseData
      .flatMap(line => line.toLowerCase.split("\\W+"))
      .filter(word => isValidWord(word))
      .map(word => (word, 1))
      .groupBy("_1")
      .sum("_2").setParallelism(1)
      .sortPartition("_2", Order.DESCENDING)
  }


}
