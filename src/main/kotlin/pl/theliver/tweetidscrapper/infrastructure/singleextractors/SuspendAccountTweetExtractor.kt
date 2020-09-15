package pl.theliver.tweetidscrapper.infrastructure.singleextractors

import org.jsoup.Jsoup
import pl.theliver.tweetidscrapper.application.SimpleTweetExtractor
import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent
import pl.theliver.tweetidscrapper.infrastructure.JsoupUtils

class SuspendAccountTweetExtractor : SimpleTweetExtractor() {

    override fun extractTweetImplementation(tweetPageContent: TweetPageContent, tweetId: TweetId): TweetExtractResult {
        val jsoup = Jsoup.parse(tweetPageContent.value)!!
        val suspendInfo = jsoup.select("span[class=css-901oao css-16my406 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0]").toList()
                .any { it.text().trim() == "This Tweet is from a suspended account." }
        val suspendLinksElements = JsoupUtils.selectFromElement(jsoup, listOf(
                "a[href=https://help.twitter.com/rules-and-policies/notices-on-twitter]"
//                "span[class=css-901oao css-16my406 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0]",
//                "span[class=css-901oao css-16my406 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0]"
        )).toList()
        val suspendLinksOk = suspendLinksElements.size == 1 && suspendLinksElements[0].text() == "Learn more"
        return when (suspendInfo && suspendLinksOk) {
            true -> TweetExtractResult.SuspendAccount
            false -> TweetExtractResult.ExtractError
        }
    }

}
