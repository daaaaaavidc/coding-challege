package com.github.daaaaaavidc.codingChallenge

import argonaut._, Argonaut._
import java.text.SimpleDateFormat

// For FEATURE_1 we need to extract `created_at` and `text` from Twitter API
// JSON. Additionally, we've been asked to strip non-ASCII unicode characters
// and log this data out in a specific format.
//
// We use the following case class with Argonaut decoders to do all of this.

case class TweetText(createdAt: String, text: String, strippedUnicode: Boolean) {
  override def toString = s"$text (timestamp: $createdAt)"
}

object TweetText {
  implicit def tweetTextDecoder: DecodeJson[TweetText] = DecodeJson { c => 
    for {
      createdAt <- (c --\ "created_at").as[String]
      rawText <- (c --\ "text").as[String]
    } yield tweetFrom(createdAt, rawText) 
  }

  private def tweetFrom(createdAt: String, rawText: String) = {
    // Remove non-ASCII unicode characters.
    val asciiText = rawText.replaceAll("[^\\u0020-\\u007F]", "")
    val strippedUnicode = (asciiText.length < rawText.length)

    // Our JSON decoder will automatically handle escaped characters, which
    // might lead to extra whitespace as tabs/endlines/etc.
    val text = asciiText.replaceAll("\\s+", " ").trim

    TweetText(createdAt, text, strippedUnicode)
  }
}

// For FEATURE_2, we need to extract `created_at` and `entities.hashtags` from Twitter
// API JSON. We additionally parse the UTC datetime string to unix epoch timestamp
// so that we can handle it more easily later while processing hashtag graphs.

case class Hashtag(text: String)
case class TweetTags(ts: Long, hashtags: List[String])

object TweetTags {
  implicit def hashtagCodec = casecodec1(Hashtag.apply, Hashtag.unapply)("text") 

  def stripUnicode(hashtag: Hashtag): String = {
    hashtag.text.replaceAll("[^\\u0020-\\u007F]", "").toLowerCase
  }

  // Note: the instructions suggested getting `created_at`, but we're just going
  // to parse it to unix epoch, so we get `timestamp_ms` instead.
  implicit def tweetTagsDecoder: DecodeJson[TweetTags] = DecodeJson { c =>
    for {
      ts <- (c --\ "timestamp_ms").as[Long]
      hashtags <- (c --\ "entities" --\ "hashtags").as[List[Hashtag]]
      // We strip non-ASCII unicode again, and ignore empty hashtags.
      hashtagText = hashtags.map(stripUnicode).filter(_ != "")
    } yield TweetTags(ts, hashtagText)
  }
}
