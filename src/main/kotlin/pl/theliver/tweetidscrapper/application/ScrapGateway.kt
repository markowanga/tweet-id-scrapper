package pl.theliver.tweetidscrapper.application

import org.springframework.stereotype.Component
import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent

@Component
class ScrapGateway(
        private val scrapService: ScrapService,
        private val tweetExtractor: TweetExtractor
) {

    fun scrapTweetBy(tweetId: TweetId): TweetExtractResult = scrapService.scrapTweetBy(tweetId)

    fun scrapPageContentBy(tweetId: TweetId, maxSecondsWait: Long): TweetPageContent =
            scrapService.scrapPageContentBy(tweetId, maxSecondsWait)

    fun parseTweetPage(tweetPageContent: TweetPageContent, tweetId: TweetId) =
            tweetExtractor.extractTweet(tweetPageContent, tweetId)

}
