package pl.theliver.tweetidscrapper.application

import org.springframework.stereotype.Service
import pl.theliver.tweetidscrapper.domain.TweetId

@Service
class ScrapService(
        private val tweetPageDownloader: TweetPageDownloader,
        private val tweetExtractor: TweetExtractor
) {

    fun scrapTweetBy(tweetId: TweetId) =
            tweetExtractor.extractTweet(scrapPageContentBy(tweetId), tweetId)

    fun scrapPageContentBy(tweetId: TweetId, maxSecondsWait: Long = 20L) =
            tweetPageDownloader.getTwitterPage(tweetId, maxSecondsWait)

}
