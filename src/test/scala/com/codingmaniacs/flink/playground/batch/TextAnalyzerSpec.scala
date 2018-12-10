package com.codingmaniacs.flink.playground.batch

import org.specs2.mutable.Specification

class TextAnalyzerSpec extends Specification {

  "The basic TextAnalyzer" should {
    "count the number of words in a empty file" in {
      val emptyFile = "src/test/resources/empty-file.txt"
      val count = TextAnalyzer.countWords(emptyFile)
      count must have size 0
      count should containTheSameElementsAs(List())
    }

    "count the number of words in a file" in {
      val emptyFile = "src/test/resources/lorem-ipsum.txt"
      val count = TextAnalyzer.countWords(emptyFile)
      count must have size 81
      count must contain (atLeast(("lorem",6), ("from",6), ("ipsum",6), ("latin",3), ("classical",2), ("comes",2)))
    }
  }
}
