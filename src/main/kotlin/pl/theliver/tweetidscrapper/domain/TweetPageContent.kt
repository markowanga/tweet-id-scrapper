package pl.theliver.tweetidscrapper.domain

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import pl.theliver.tweetidscrapper.infrastructure.JsoupUtils

data class TweetPageContent(
        val value: String,
        val tweetId: TweetId
) {
    fun extractTweet(): Tweet {
        val article = Jsoup.parse(value).select("article").toList()
                .first { elementContainsTweetDetails(it, tweetId) }
        val usernameString = JsoupUtils.selectFromElement(article, USERNAME_SELECTORS)
                .first().text()
        val tweetString = JsoupUtils.selectFromElement(article, CONTENT_SELECTORS)
                .first().text()
        return Tweet(
                tweetId,
                TweetContent(tweetString),
                TweetUsername(usernameString)
        )
    }

    companion object {
        private const val CONTENT_SELECTOR_2 = "span[class=css-901oao css-16my406 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0]"
        val CONTENT_SELECTORS = listOf("div[class=css-1dbjc4n r-156q2ks]", CONTENT_SELECTOR_2)
        val USERNAME_SELECTORS = listOf("div[class=css-1dbjc4n r-1wbh5a2 r-dnmrzs r-1ny4l3l]", "div[dir=ltr]")

        fun elementContainsTweetDetails(element: Element, tweetId: TweetId) =
                element.select("a").toList().any {
                    it.attr("href")?.contains("/${tweetId.value}/likes") ?: false
                }
    }

}