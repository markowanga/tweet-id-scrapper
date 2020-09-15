package pl.theliver.tweetidscrapper.application

import org.springframework.stereotype.Component
import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent


@Component
class TweetExtractor(private val simpleExtractors: List<SimpleTweetExtractor>) {

    fun extractTweet(tweetPageContent: TweetPageContent, tweetId: TweetId): TweetExtractResult {
        val nonErrorResults = simpleExtractors
                .map { it.extractTweet(tweetPageContent, tweetId) }
                .filter { it != TweetExtractResult.ExtractError }
        return if (nonErrorResults.toSet().size == 1) {
            nonErrorResults[0]
        } else {
            println("multiple results")
            println(nonErrorResults)
            TweetExtractResult.ExtractError
        }
    }

}
