package com.github.daaaaaavidc.codingChallenge

import argonaut._, Argonaut._
import java.io.{File, PrintWriter}
import scala.collection.parallel._
import scala.io.{BufferedSource, Source}
import scala.util.{Try, Success, Failure}

object Feature1 extends App {

  val inFile = sys.env.get("INPUT_FILE").getOrElse("tweet_input/tweets.txt")
  val input = Source.fromFile(inFile)

  val outFile = sys.env.get("OUTPUT_FILE1").getOrElse("tweet_output/ft1.txt")
  val output = new PrintWriter(new File(outFile))

  val batchSize = 5000
  val unicodeTweets = input.getLines.grouped(batchSize)
    .foldLeft(0) { (count, lines) =>
      // Lines which aren't succesfully parsed to our TweetText case class
      // will be ignored (decodeOption returning None, ignored via flatMap).
      val tweets = lines.par.flatMap(_.decodeOption[TweetText])

      // Write these to our output file, taking advantage of TweetText's
      // overridden toString method, which appropriately formats our lines.
      output.write(tweets.mkString("\n") + "\n")

      // Accumulate number of tweets from which we stripped non-ASCII unicode.
      val stripped = tweets.filter(_.strippedUnicode).length
      count + stripped
    }

  // We kept track of the number of unicodeTweets while processing (foldLeft!).
  output.write(s"\n${unicodeTweets} tweets contained unicode.")

  output.close
  input.close
}
