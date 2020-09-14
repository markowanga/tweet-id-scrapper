package pl.theliver.tweetidscrapper.application

import org.springframework.stereotype.Service
import pl.theliver.tweetidscrapper.domain.TweetId

@Service
class ScrapService(
        private val pageDownloader: PageDownloader
) {

    fun scrapTweetBy(tweetId: TweetId) =
            scrapPageContentBy(tweetId).extractTweet()

    fun scrapPageContentBy(tweetId: TweetId, maxSecondsWait: Long = 20L) =
            pageDownloader.getTwitterPage(tweetId, maxSecondsWait)

}