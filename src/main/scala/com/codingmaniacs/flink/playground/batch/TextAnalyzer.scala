package com.codingmaniacs.flink.playground.batch

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala._

object TextAnalyzer {

  val DEFAULT_MIN_WORD_LENGTH = 3

  val isValidWord: Int => String => Boolean = (minLength: Int) => {
    case w if w == null || w.trim.isEmpty => false
    case w if w.length <= minLength => false
    case _ => true
  }

  val defaultFilteringFn: String => Boolean = isValidWord(TextAnalyzer.DEFAULT_MIN_WORD_LENGTH)

  def countWords(fileName: String)(implicit environment: ExecutionEnvironment): Seq[(String, Int)] = {
    val dataSet = environment.readTextFile(fileName)
    val words = transformDataSet(defaultFilteringFn)(dataSet)
    words.collect()
  }

  def countTopNWords(fileName: String, numberOfWords: Int)(implicit environment: ExecutionEnvironment): Seq[(String, Int)] = {

    val dataSet = environment.readTextFile(fileName)

    // First will return a the first n elements from this data set, but It doesn't guarantee the order if the
    // parallelism is greater than 1
    val words = transformDataSet(defaultFilteringFn)(dataSet).first(numberOfWords)

    words.collect().sortBy(_._1)
  }

  val transformDataSet: (String => Boolean) => DataSet[String] => DataSet[(String, Int)] =
    (validationFunction: String => Boolean) => (baseData: DataSet[String]) => {
      baseData
        .flatMap(line => line.toLowerCase.split("\\W+"))
        .filter(word => validationFunction(word))
        .map(word => (word, 1))
        .groupBy("_1")
        .sum("_2").setParallelism(1)
        .sortPartition("_2", Order.DESCENDING)
    }
}
