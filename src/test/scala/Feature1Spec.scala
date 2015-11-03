package com.github.daaaaaavidc.codingChallenge

import argonaut._, Argonaut._
import org.specs2.mutable.Specification

class Feature1Spec extends Specification {

  "TweetText JSON decoder" should {
    def parseTweet(json: String) = json.decodeOption[TweetText]

    "decode valid JSON into TweetText" in {
      val json = """{"created_at":"Thu Oct 29 17:51:01 +0000 2015","id":659789759787589600,"id_str":"659789759787589632","text":"Spark Summit East this week! #Spark #Apache","source":"<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":null,"in_reply_to_user_id_str":null,"in_reply_to_screen_name":null,"user":{"id":40077534,"id_str":"40077534","name":"scott bordow","screen_name":"sbordow","location":null,"url":null,"description":"azcentral sports high school sports columnist. If you send me a tweet, you consent to letting azcentral sports use and showcase it in any media.","protected":false,"verified":true,"followers_count":4704,"friends_count":2249,"listed_count":94,"favourites_count":51,"statuses_count":15878,"created_at":"Thu May 14 20:36:46 +0000 2009","utc_offset":-25200,"time_zone":"Pacific Time (US & Canada)","geo_enabled":true,"lang":"en","contributors_enabled":false,"is_translator":false,"profile_background_color":"C0DEED","profile_background_image_url":"http://abs.twimg.com/images/themes/theme1/bg.png","profile_background_image_url_https":"https://abs.twimg.com/images/themes/theme1/bg.png","profile_background_tile":false,"profile_link_color":"0084B4","profile_sidebar_border_color":"C0DEED","profile_sidebar_fill_color":"DDEEF6","profile_text_color":"333333","profile_use_background_image":true,"profile_image_url":"http://pbs.twimg.com/profile_images/576178462496423936/YnOZ-StV_normal.jpeg","profile_image_url_https":"https://pbs.twimg.com/profile_images/576178462496423936/YnOZ-StV_normal.jpeg","default_profile":true,"default_profile_image":false,"following":null,"follow_request_sent":null,"notifications":null},"geo":null,"coordinates":null,"place":{"id":"a612c69b44b2e5da","url":"https://api.twitter.com/1.1/geo/id/a612c69b44b2e5da.json","place_type":"admin","name":"Arizona","full_name":"Arizona, USA","country_code":"US","country":"United States","bounding_box":{"type":"Polygon","coordinates":[[[-114.818269,31.332246],[-114.818269,37.004261],[-109.045152,37.004261],[-109.045152,31.332246]]]},"attributes":{}},"contributors":null,"is_quote_status":false,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[],"urls":[],"user_mentions":[],"symbols":[]},"favorited":false,"retweeted":false,"filter_level":"low","lang":"en","timestamp_ms":"1446141111691"}"""
      val tweet = parseTweet(json).getOrElse(TweetText("", "", false))

      tweet mustEqual TweetText(
        "Thu Oct 29 17:51:01 +0000 2015",
        "Spark Summit East this week! #Spark #Apache",
        false)

      tweet.toString mustEqual "Spark Summit East this week! #Spark #Apache (timestamp: Thu Oct 29 17:51:01 +0000 2015)"
    }

    "strip non-ASCII unicode characters from text" in {
      val json = """{"created_at":"Thu Oct 29 18:10:49 +0000 2015","id":659794531844509700,"id_str":"659794531844509700","text":"I'm at Terminal de Integração do Varadouro in João Pessoa, PB https://t.co/HOl34REL1a","source":"<a href=\"http://foursquare.com\" rel=\"nofollow\">Foursquare</a>","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":null,"in_reply_to_user_id_str":null,"in_reply_to_screen_name":null,"user":{"id":60196177,"id_str":"60196177","name":"João Cassimiro","screen_name":"Jcassimironeto","location":"Paraiba","url":"http://www.facebook.com/profile.php?id=1818814650","description":"jcassimironeto","protected":false,"verified":false,"followers_count":240,"friends_count":654,"listed_count":0,"favourites_count":26,"statuses_count":2065,"created_at":"Sun Jul 26 01:15:03 +0000 2009","utc_offset":-7200,"time_zone":"Brasilia","geo_enabled":true,"lang":"pt","contributors_enabled":false,"is_translator":false,"profile_background_color":"022330","profile_background_image_url":"http://pbs.twimg.com/profile_background_images/671814600/1028c894ede2eb444ebfd12f94f6cb93.jpeg","profile_background_image_url_https":"https://pbs.twimg.com/profile_background_images/671814600/1028c894ede2eb444ebfd12f94f6cb93.jpeg","profile_background_tile":true,"profile_link_color":"0084B4","profile_sidebar_border_color":"FFFFFF","profile_sidebar_fill_color":"C0DFEC","profile_text_color":"333333","profile_use_background_image":true,"profile_image_url":"http://pbs.twimg.com/profile_images/618238977565433856/YM1aKFZj_normal.jpg","profile_image_url_https":"https://pbs.twimg.com/profile_images/618238977565433856/YM1aKFZj_normal.jpg","profile_banner_url":"https://pbs.twimg.com/profile_banners/60196177/1395970110","default_profile":false,"default_profile_image":false,"following":null,"follow_request_sent":null,"notifications":null},"geo":{"type":"Point","coordinates":[-7.11792683,-34.88985837]},"coordinates":{"type":"Point","coordinates":[-34.88985837,-7.11792683]},"place":{"id":"c9f2f46c0d1b963d","url":"https://api.twitter.com/1.1/geo/id/c9f2f46c0d1b963d.json","place_type":"city","name":"João Pessoa","full_name":"João Pessoa, Paraíba","country_code":"BR","country":"Brasil","bounding_box":{"type":"Polygon","coordinates":[[[-34.971299,-7.243257],[-34.971299,-7.055696],[-34.792907,-7.055696],[-34.792907,-7.243257]]]},"attributes":{}},"contributors":null,"is_quote_status":false,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[],"urls":[{"url":"https://t.co/HOl34REL1a","expanded_url":"https://www.swarmapp.com/c/2tATygSTvBu","display_url":"swarmapp.com/c/2tATygSTvBu","indices":[62,85]}],"user_mentions":[],"symbols":[]},"favorited":false,"retweeted":false,"possibly_sensitive":false,"filter_level":"low","lang":"pt","timestamp_ms":"1446142249438"}"""

      parseTweet(json) must beSome(TweetText(
        "Thu Oct 29 18:10:49 +0000 2015",
        "I'm at Terminal de Integrao do Varadouro in Joo Pessoa, PB https://t.co/HOl34REL1a",
        true))
    }
  }
}
