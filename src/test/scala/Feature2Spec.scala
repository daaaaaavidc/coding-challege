package com.github.daaaaaavidc.codingChallenge

import argonaut._, Argonaut._
import org.specs2.mutable.Specification

class Feature2Spec extends Specification with HashtagGraphHelpers {

  "TweetTags JSON decoder" should {
    def parseTweet(json: String) = json.decodeOption[TweetTags]

    "decode valid tweet JSON into TweetTags" in {
      val json1 = """{"created_at":"Thu Oct 29 18:10:49 +0000 2015","id":659794531844509700,"id_str":"659794531844509700","text":"I'm at Terminal de Integra\u00e7\u00e3o do Varadouro in Jo\u00e3o Pessoa, PB https:\/\/t.co\/HOl34REL1a","source":"\u003ca href=\"http:\/\/foursquare.com\" rel=\"nofollow\"\u003eFoursquare\u003c\/a\u003e","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":null,"in_reply_to_user_id_str":null,"in_reply_to_screen_name":null,"user":{"id":60196177,"id_str":"60196177","name":"Jo\u00e3o Cassimiro","screen_name":"Jcassimironeto","location":"Paraiba","url":"http:\/\/www.facebook.com\/profile.php?id=1818814650","description":"jcassimironeto","protected":false,"verified":false,"followers_count":240,"friends_count":654,"listed_count":0,"favourites_count":26,"statuses_count":2065,"created_at":"Sun Jul 26 01:15:03 +0000 2009","utc_offset":-7200,"time_zone":"Brasilia","geo_enabled":true,"lang":"pt","contributors_enabled":false,"is_translator":false,"profile_background_color":"022330","profile_background_image_url":"http:\/\/pbs.twimg.com\/profile_background_images\/671814600\/1028c894ede2eb444ebfd12f94f6cb93.jpeg","profile_background_image_url_https":"https:\/\/pbs.twimg.com\/profile_background_images\/671814600\/1028c894ede2eb444ebfd12f94f6cb93.jpeg","profile_background_tile":true,"profile_link_color":"0084B4","profile_sidebar_border_color":"FFFFFF","profile_sidebar_fill_color":"C0DFEC","profile_text_color":"333333","profile_use_background_image":true,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/618238977565433856\/YM1aKFZj_normal.jpg","profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/618238977565433856\/YM1aKFZj_normal.jpg","profile_banner_url":"https:\/\/pbs.twimg.com\/profile_banners\/60196177\/1395970110","default_profile":false,"default_profile_image":false,"following":null,"follow_request_sent":null,"notifications":null},"geo":{"type":"Point","coordinates":[-7.11792683,-34.88985837]},"coordinates":{"type":"Point","coordinates":[-34.88985837,-7.11792683]},"place":{"id":"c9f2f46c0d1b963d","url":"https:\/\/api.twitter.com\/1.1\/geo\/id\/c9f2f46c0d1b963d.json","place_type":"city","name":"Jo\u00e3o Pessoa","full_name":"Jo\u00e3o Pessoa, Para\u00edba","country_code":"BR","country":"Brasil","bounding_box":{"type":"Polygon","coordinates":[[[-34.971299,-7.243257],[-34.971299,-7.055696],[-34.792907,-7.055696],[-34.792907,-7.243257]]]},"attributes":{}},"contributors":null,"is_quote_status":false,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[],"urls":[{"url":"https:\/\/t.co\/HOl34REL1a","expanded_url":"https:\/\/www.swarmapp.com\/c\/2tATygSTvBu","display_url":"swarmapp.com\/c\/2tATygSTvBu","indices":[62,85]}],"user_mentions":[],"symbols":[]},"favorited":false,"retweeted":false,"possibly_sensitive":false,"filter_level":"low","lang":"pt","timestamp_ms":"1446142249438"}"""
      val json2 = """{"created_at":"Fri Oct 30 15:29:44 +0000 2015","id":660116384035299332,"id_str":"660116384035299332","text":"How is Trump Doing in the Polls https:\/\/t.co\/1euweGx6SF #Trump #Election #News","source":"\u003ca href=\"http:\/\/twitter.com\" rel=\"nofollow\"\u003eTwitter Web Client\u003c\/a\u003e","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":null,"in_reply_to_user_id_str":null,"in_reply_to_screen_name":null,"user":{"id":3773377247,"id_str":"3773377247","name":"Jessica Higgins","screen_name":"jessica_ny4","location":null,"url":null,"description":null,"protected":false,"verified":false,"followers_count":4,"friends_count":44,"listed_count":0,"favourites_count":0,"statuses_count":6401,"created_at":"Fri Sep 25 17:46:33 +0000 2015","utc_offset":-25200,"time_zone":"Pacific Time (US & Canada)","geo_enabled":true,"lang":"de","contributors_enabled":false,"is_translator":false,"profile_background_color":"C0DEED","profile_background_image_url":"http:\/\/abs.twimg.com\/images\/themes\/theme1\/bg.png","profile_background_image_url_https":"https:\/\/abs.twimg.com\/images\/themes\/theme1\/bg.png","profile_background_tile":false,"profile_link_color":"0084B4","profile_sidebar_border_color":"C0DEED","profile_sidebar_fill_color":"DDEEF6","profile_text_color":"333333","profile_use_background_image":true,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/647468484495044608\/PXZtZBQV_normal.jpg","profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/647468484495044608\/PXZtZBQV_normal.jpg","default_profile":true,"default_profile_image":false,"following":null,"follow_request_sent":null,"notifications":null},"geo":null,"coordinates":null,"place":{"id":"94965b2c45386f87","url":"https:\/\/api.twitter.com\/1.1\/geo\/id\/94965b2c45386f87.json","place_type":"admin","name":"New York","full_name":"New York, USA","country_code":"US","country":"United States","bounding_box":{"type":"Polygon","coordinates":[[[-79.762590,40.477383],[-79.762590,45.015851],[-71.777492,45.015851],[-71.777492,40.477383]]]},"attributes":{}},"contributors":null,"is_quote_status":false,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[{"text":"Trump","indices":[56,62]},{"text":"Election","indices":[63,72]},{"text":"News","indices":[73,78]}],"urls":[{"url":"https:\/\/t.co\/1euweGx6SF","expanded_url":"http:\/\/latestnews2016presidentialelection.com","display_url":"latestnews2016presidentialelection.com","indices":[32,55]}],"user_mentions":[],"symbols":[]},"favorited":false,"retweeted":false,"possibly_sensitive":false,"filter_level":"low","lang":"en","timestamp_ms":"1446218984980"}"""

      parseTweet(json1) must beSome(TweetTags(1446142249438L, List()))
      parseTweet(json2) must beSome(TweetTags(1446218984980L, List("trump", "election", "news")))
    }
  }

  "HashtagGraph" should {
    "Compute average degree of a HashtagGraph" in {
      val edges1 = Map[Set[String], Long]()
      val edges2 = Map(Set("A", "B") -> 1L, Set("B", "C") -> 1L, Set("C", "D") -> 1L)
      val edges3 = Map(
        Set("A", "B") -> 1L,
        Set("A", "C") -> 1L,
        Set("A", "D") -> 1L,
        Set("B", "C") -> 1L,
        Set("B", "D") -> 1L,
        Set("C", "D") -> 1L)

      HashtagGraph(1L, edges1).averageDegree mustEqual 0.0
      HashtagGraph(1L, edges2).averageDegree mustEqual 1.5
      HashtagGraph(1L, edges3).averageDegree mustEqual 3.0
    }
  }

  "HashtagGraphHelpers" should {
    "Determine if TweetTags isRecent" in {
      val mostRecent = System.currentTimeMillis
      val expiredTweet = TweetTags(mostRecent - 61000, List("hadoop", "storm"))
      val recentTweet = TweetTags(mostRecent - 60000, List("spark", "apache"))
     
      isRecentTweet(mostRecent, expiredTweet) mustEqual false
      isRecentTweet(mostRecent, recentTweet) mustEqual true
    }

    "Builds edge set from hashtags" in {
      val hashtags1 = List("kafka", "kafka")
      val hashtags2 = List("spark", "apache")
      val hashtags3 = List("apache", "storm", "hadoop")

      buildEdges(hashtags1) mustEqual List()
      buildEdges(hashtags2) mustEqual List(Set("spark", "apache"))
      buildEdges(hashtags3) mustEqual List(
        Set("apache", "storm"), Set("apache", "hadoop"), Set("storm", "hadoop"))
    }

    "Computes updated graph in response to a new tweet" in {
      val mostRecent = System.currentTimeMillis
      val edges = Map(
        Set("A", "B") -> (mostRecent - 31000),
        Set("A", "C") -> (mostRecent - 25000),
        Set("D", "E") -> mostRecent)

      val graph = HashtagGraph(mostRecent, edges)
      val tweet = TweetTags(mostRecent + 30000, List("B", "C", "D"))
      val updated = updateGraph(graph, tweet)

      updated.mostRecent mustEqual (mostRecent + 30000)
      updated.edges mustEqual Map(
        Set("A", "C") -> (mostRecent - 25000),
        Set("D", "E") -> (mostRecent),
        Set("B", "C") -> (mostRecent + 30000),
        Set("B", "D") -> (mostRecent + 30000),
        Set("C", "D") -> (mostRecent + 30000))
    }

    "Recent tweets without multiple hashtags should still update `mostRecent`" in {
      val mostRecent = System.currentTimeMillis
      val edges = Map(
        Set("A", "B") -> mostRecent,
        Set("A", "C") -> mostRecent,
        Set("D", "E") -> mostRecent)

      val graph = HashtagGraph(mostRecent, edges)
      val tweet = TweetTags(mostRecent + 30000, List("A"))
      val updated = updateGraph(graph, tweet)

      updated.mostRecent mustEqual (mostRecent + 30000)
      updated.edges mustEqual edges
    }

    "Computes rolling average while processing tweets" in {
      val tweets = List(
        TweetTags(1446141061000L, List("spark", "apache")),
        TweetTags(1446141090000L, List("apache", "hadoop", "storm")),
        TweetTags(1446141115000L, List("apache")),
        TweetTags(1446141116000L, List("flink", "spark")),
        TweetTags(1446141119000L, List("hbase", "spark")),
        TweetTags(1446141125000L, List("hadoop", "apache")))

      // Iterate through our tweets, computing new graphs and accumulating averages.
      val (_, averages) = tweets.foldLeft((HashtagGraph(), List[Double]())) { (acc, tweet) =>
        val (graph, avgs) = acc
        val updated = updateGraph(graph, tweet)
        (updated, updated.averageDegree :: avgs)
      }

      // We prepended (List has O(1) prepend) new averages, so our list is backwards.
      averages mustEqual List(1.67, 2.0, 2.0, 2.0, 2.0, 1.0)
    }
  }
}
