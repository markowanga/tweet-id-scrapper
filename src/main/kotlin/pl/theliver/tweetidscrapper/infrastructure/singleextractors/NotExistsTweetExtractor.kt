package pl.theliver.tweetidscrapper.infrastructure.singleextractors

import org.jsoup.Jsoup
import pl.theliver.tweetidscrapper.application.SimpleTweetExtractor
import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent

class NotExistsTweetExtractor : SimpleTweetExtractor() {

    override fun extractTweetImplementation(tweetPageContent: TweetPageContent, tweetId: TweetId) =
        when (Jsoup.parse(tweetPageContent.value).select("span").toList()
                .any { it.text().trim() == "Sorry, that page doesn’t exist!" }) {
            true -> TweetExtractResult.TweetNotExists
            false -> TweetExtractResult.ExtractError
        }

}