package pl.theliver.tweetidscrapper.application

import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent

interface PageDownloader {
    fun getTwitterPage(tweetId: TweetId): TweetPageContent
}