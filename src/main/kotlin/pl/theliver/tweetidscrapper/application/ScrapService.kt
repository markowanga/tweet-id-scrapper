package pl.theliver.tweetidscrapper.application

import pl.theliver.tweetidscrapper.domain.Tweet
import pl.theliver.tweetidscrapper.domain.TweetId

interface ScrapService {
    fun scrapTweetBy(tweetId: TweetId): Tweet?
}