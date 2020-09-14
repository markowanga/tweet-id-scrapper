package pl.theliver.tweetidscrapper.application

import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent

interface TweetPageDownloader {
    fun getTwitterPage(tweetId: TweetId, maxSecondsWait: Long): TweetPageContent
}
