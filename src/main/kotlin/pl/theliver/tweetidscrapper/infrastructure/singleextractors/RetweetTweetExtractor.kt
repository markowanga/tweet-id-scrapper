package pl.theliver.tweetidscrapper.infrastructure.singleextractors

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import pl.theliver.tweetidscrapper.application.SimpleTweetExtractor
import pl.theliver.tweetidscrapper.domain.*
import pl.theliver.tweetidscrapper.infrastructure.JsoupUtils

class RetweetTweetExtractor : SimpleTweetExtractor() {

    override fun extractTweetImplementation(tweetPageContent: TweetPageContent, tweetId: TweetId): TweetExtractResult {
        val articles = Jsoup.parse(tweetPageContent.value).select("article").toList()
        if (articles.size != 1) {
            return TweetExtractResult.ExtractError
        }
        val article = articles[0]
        val usernameString = JsoupUtils.selectFromElement(article, USERNAME_SELECTORS)
                .map { it.text().trim() }
                .first { it.isNotEmpty() && it[0] == '@' && !it.contains(' ') }
        val tweetString = JsoupUtils.selectFromElement(article, CONTENT_SELECTORS).getFirstTextElement()
        val createdDateString = JsoupUtils.selectFromElement(article, CREATED_DATE_SELECTORS)
                .getFirstTextElement()
        val createdDate = parseDate(createdDateString)
        return TweetExtractResult.ExtractSuccessful(Tweet(
                tweetId,
                TweetContent(tweetString),
                TweetUsername(usernameString),
                TweetCreatedDate(createdDate)
        ))
    }

    private fun List<Element>.getFirstTextElement() = first().text()!!


    companion object {
        private val CREATED_DATE_SELECTORS = listOf("a[class=css-4rbku5 css-18t94o4 css-901oao css-16my406 r-1re7ezh r-1loqt21 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0]")
        private val CONTENT_SELECTORS = listOf("div[class=css-901oao r-hkyrab r-1qd0xha r-1blvdjr r-16dba41 r-ad9z0x r-bcqeeo r-bnwqim r-qvutc0]")
        private val USERNAME_SELECTORS = listOf("span")
    }

}