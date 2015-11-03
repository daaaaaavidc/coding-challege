package com.github.daaaaaavidc.codingChallenge

case class HashtagGraph(
  mostRecent: Long = 0,
  edges: Map[Set[String], Long] = Map()) {

  // Call d(G) the total degree of a graph and note that:
  // - d(G) is the sum of the degrees of each vertex in G, each
  //   edge being counted twice, i.e. d(G) = 2 * NUM_EDGES.
  // - The average degree of a graph is d(G) / NUM_VERTICES.
  // 
  // So, the average degree of a graph is 2 * NUM_EDGES / NUM_VERTICES.
  def averageDegree: Double = {
    val avg = (2 * edges.size).toDouble / edges.keySet.flatten.size
    math.round(avg * 100) / 100.toDouble
  }
}

trait HashtagGraphHelpers {

  // Build edges (Sets of vertices) between all pairs of distinct
  // hashtags provided.
  def buildEdges(hashtags: List[String]): List[Set[String]] = {
    val vs = hashtags.toSet.toList

    vs.take(vs.length - 1).zip((1 until vs.length))
      .flatMap { vi =>
        val (v, i) = vi
        vs.drop(i).map(w => Set(v, w))
      }
  }

  def updateGraph(graph: HashtagGraph, tweet: TweetTags): HashtagGraph = {
    val mostRecent = math.max(graph.mostRecent, tweet.ts)

    // Our new edge set is calculate as follows:
    // - Take unexpired edges from our old graph.
    // - Add new edges, making sure to update the edge's timestamp if necessary.
    val newEdges =
      graph.edges.filter(mostRecent - _._2 <= 60 * 1000) ++
      buildEdges(tweet.hashtags).map { e =>
        val ts = math.max(graph.edges.getOrElse(e, 0L), tweet.ts)
        (e, ts)
      }

    HashtagGraph(mostRecent, newEdges)
  }

  def isRecentTweet(mostRecent: Long, tweet: TweetTags): Boolean =
    mostRecent - tweet.ts <= 60 * 1000
}
