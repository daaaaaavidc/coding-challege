package com.github.daaaaaavidc.codingChallenge

import argonaut._, Argonaut._
import java.io.{File, PrintWriter}
import scala.io.Source

object Feature2 extends App with HashtagGraphHelpers {

  val inFile = sys.env.get("INPUT_FILE").getOrElse("tweet_input/tweets.txt")
  val input = Source.fromFile(inFile)

  val outFile = sys.env.get("OUTPUT_FILE2").getOrElse("tweet_output/ft2.txt")
  val output = new PrintWriter(new File(outFile))

  // By folding, we're given the previous hashtag graph state with each of
  // our unprocessed input lines. We just process them and return a new graph.
  input.getLines.foldLeft(HashtagGraph()) { (graph, line) =>
    line.decodeOption[TweetTags] match {
      case Some(tweet) => {
        // If this is a recent tweet, we'll process it and compute a new graph.
        // Otherwise the graph remains the same.
        val updated = {
          if(isRecentTweet(graph.mostRecent, tweet)) updateGraph(graph, tweet)
          else graph
        }

        output.write(updated.averageDegree + "\n")
        updated
      }
      // Otherwise, we return the current graph unchanged, without logging.
      case _ => graph
    }
  }

  output.close
  input.close
}
