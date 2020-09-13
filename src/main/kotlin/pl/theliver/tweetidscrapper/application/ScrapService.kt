package pl.theliver.tweetidscrapper.application

import org.springframework.stereotype.Service
import pl.theliver.tweetidscrapper.domain.TweetId

@Service
class ScrapService(
        private val pageDownloader: PageDownloader
) {

    fun scrapTweetBy(tweetId: TweetId) =
            pageDownloader.getTwitterPage(tweetId).extractTweet()

}