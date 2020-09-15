package pl.theliver.tweetidscrapper.infrastructure.singleextractors

import org.jsoup.Jsoup
import pl.theliver.tweetidscrapper.application.SimpleTweetExtractor
import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent

class ProfileRedirectTweetExtractor : SimpleTweetExtractor() {

    override fun extractTweetImplementation(tweetPageContent: TweetPageContent, tweetId: TweetId) =
            when (Jsoup.parse(tweetPageContent.value).select("span[class=css-901oao css-16my406 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0]").toList()
                    .any { it.text().trim() == "@any" }) {
                true -> TweetExtractResult.SuspendAccount
                false -> TweetExtractResult.ExtractError
            }

}