## Insight Data Engineering - Coding Challenge

My solutions are written in Scala and can be run with `sbt`.

If you don't already have a Scala/sbt environment, be warned that sbt will download a bunch of stuff on first launch. Sorry about that! Otherwise this is a pleasant environment to work in, and subsequent runs will be fast.

The sbt project handles dependencies itself, but we use:
* [Specs2](https://etorreborre.github.io/specs2/) for unit tests
* [Argonaut](http://argonaut.io) for JSON decoding

Each feature is implemented in its own main class and can be run with `sbt run-main`. By default, we expect to read input from `tweet_input/tweets.txt` and write output to `tweet_output/`.

These values are configurable by environment variables. See [run.sh](https://github.com/daaaaaavidc/coding-challege/blob/master/run.sh) for more details.

### Implementation details

###### Feature 1

Most of the actual business logic of this feature is handled by our JSON decoder. It grabs `created_at` and `text` from the JSON input, unescapes and strips non-ASCII unicode characters, and returns an instance of our `TweetText` case class. This case class has an overridden `toString` method which enables us to easily print each tweet in the desired format.

Input lines are processed in batch, and processed lines are written to our output file once per batch.

###### Feature 2

Again we take advantage of our JSON decoder, this time grabbing `timestamp_ms` and `hashtags`. We store our graph as a `Map`, the keys being edges (a `Set` of vertices) and the values being a `Long` timestamp.

We could process/write in batch here again, but the instructions suggested we should be printing for each line, so that's what I did. The instructions did not mention stripping non-ASCII unicode characters from tweets in this step, so I didn't.

### Testing

Nontrivial functionality is unit tested. Tests are available in [src/test/scala](https://github.com/daaaaaavidc/coding-challege/tree/master/src/test/scala) and can be run with `sbt`.

```
~/.things/coding-challenge $ sbt test
[info] Loading project definition from /Users/david/.things/coding-challenge/project
[info] Set current project to codingChallenge (in build file:/Users/david/.things/coding-challenge/)
[info] Feature1Spec
[info] 
[info] TweetText JSON decoder should
[info]   + decode valid JSON into TweetText
[info]   + strip non-ASCII unicode characters from text
[info] 
[info] 
[info] Total for specification Feature1Spec
[info] Finished in 386 ms
[info] 2 examples, 0 failure, 0 error
[info] 
[info] Feature2Spec
[info] 
[info] TweetTags JSON decoder should
[info]   + decode valid tweet JSON into TweetTags
[info] HashtagGraph should
[info]   + Compute average degree of a HashtagGraph
[info] HashtagGraphHelpers should
[info]   + Determine if TweetTags isRecent
[info]   + Builds edge set from hashtags
[info]   + Computes updated graph in response to a new tweet
[info]   + Recent tweets without multiple hashtags should still update `mostRecent`
[info]   + Computes rolling average while processing tweets
[info] 
[info] 
[info] Total for specification Feature2Spec
[info] Finished in 454 ms
[info] 7 examples, 0 failure, 0 error
[info] 
[info] Passed: Total 9, Failed 0, Errors 0, Passed 9
[success] Total time: 2 s, completed Nov 2, 2015 7:05:42 PM
```

