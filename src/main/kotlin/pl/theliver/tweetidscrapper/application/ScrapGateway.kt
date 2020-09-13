package pl.theliver.tweetidscrapper.application

import org.springframework.stereotype.Component
import pl.theliver.tweetidscrapper.domain.Tweet
import pl.theliver.tweetidscrapper.domain.TweetId

@Component
class ScrapGateway(
        private val scrapService: ScrapService
) {
    fun scrapTweetBy(tweetId: TweetId): Tweet = scrapService.scrapTweetBy(tweetId)
}