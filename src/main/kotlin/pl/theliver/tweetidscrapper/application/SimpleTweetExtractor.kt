package pl.theliver.tweetidscrapper.application

import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent
import java.text.SimpleDateFormat
import java.util.*

abstract class SimpleTweetExtractor {

    fun extractTweet(tweetPageContent: TweetPageContent, tweetId: TweetId): TweetExtractResult {
        return try {
            extractTweetImplementation(tweetPageContent, tweetId)
        } catch (e: Exception) {
            TweetExtractResult.ExtractError
        }
    }

    protected abstract fun extractTweetImplementation(
            tweetPageContent: TweetPageContent,
            tweetId: TweetId
    ): TweetExtractResult

    protected fun parseDate(dateString: String) = formatter.parse(dateString)!!

    companion object {
        private val formatter = SimpleDateFormat("H:mm aaa · MMM d, yyyy", Locale.ENGLISH)
    }
}