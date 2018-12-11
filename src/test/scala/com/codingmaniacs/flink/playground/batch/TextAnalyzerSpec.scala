package com.codingmaniacs.flink.playground.batch

import org.apache.flink.api.scala.ExecutionEnvironment
import org.specs2.mutable.Specification

class TextAnalyzerSpec extends Specification {

  "The simple word validator" should {
    "handle null a not valid word" in {
      val word: String = null
      val result = TextAnalyzer.defaultFilteringFn(word)
      result must beFalse
    }

    "handle empty string a not valid word" in {
      val word: String = ""
      val result = TextAnalyzer.defaultFilteringFn(word)
      result must beFalse
    }

    "handle an string containing spaces a not valid word" in {
      val word: String = "           "
      val result = TextAnalyzer.defaultFilteringFn(word)
      result must beFalse
    }

    "handle an string with a size less than the threshold as not valid word" in {
      val word: String = "rage"
      val minWordLength = 4
      val result = TextAnalyzer.isValidWord(minWordLength)(word)
      result must beFalse
    }

    "handle an string with a size greater than the threshold as valid word" in {
      val word: String = "awesome"
      val minWordLength = 5
      val result = TextAnalyzer.isValidWord(minWordLength)(word)
      result must beTrue
    }
  }

  "The basic TextAnalyzer" should {
    "count the number of words in a empty file" in {
      val emptyFile = "src/test/resources/empty-file.txt"
      implicit val environment: ExecutionEnvironment = ExecutionEnvironment.createLocalEnvironment()

      val words = TextAnalyzer.countWords(emptyFile)
      words must have size 0
      words should containTheSameElementsAs(List())
    }

    "count the number of words in a file" in {
      val ipsumFile = "src/test/resources/lorem-ipsum.txt"
      implicit val environment: ExecutionEnvironment = ExecutionEnvironment.createLocalEnvironment()

      val words = TextAnalyzer.countWords(ipsumFile)
      words must have size 81
      words must contain(atLeast(("lorem", 6), ("from", 6), ("ipsum", 6), ("latin", 3), ("classical", 2), ("comes", 2)))
    }

    "return the top N words in a file" in {
      val ipsumFile = "src/test/resources/lorem-ipsum.txt"
      implicit val environment: ExecutionEnvironment = ExecutionEnvironment.createLocalEnvironment()

      val numberOfWords = 6
      val words = TextAnalyzer.countTopNWords(ipsumFile, numberOfWords)
      words must have size numberOfWords
      words must contain(atMost(("from", 6), ("ipsum", 6), ("lorem", 6), ("latin", 3), ("cicero", 2), ("line", 2)))
    }
  }
}
