package pl.theliver.tweetidscrapper.domain


sealed class TweetExtractResult {
    object TweetNotExists : TweetExtractResult()
    data class ExtractSuccessful(val value: Tweet) : TweetExtractResult()
    object ExtractError : TweetExtractResult()
}