package pl.theliver.tweetidscrapper.infrastructure

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Service
import pl.theliver.tweetidscrapper.application.ScrapService
import pl.theliver.tweetidscrapper.domain.Tweet
import pl.theliver.tweetidscrapper.domain.TweetContent
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetUsername


@Service
class SeleniumScrapService : ScrapService {

    override fun scrapTweetBy(tweetId: TweetId): Tweet? = getPageContent(tweetId)?.extractTweet(tweetId)

    private fun getPageContent(tweetId: TweetId): PageContent? {
        val content: PageContent?
        val driver: WebDriver = FirefoxDriver()
        try {
            driver.get(getTwitterUrlBy(tweetId))
            waitForLoad(driver)
            content = PageContent(driver.pageSource)
        } finally {
            driver.quit()
        }
        return content
    }

    fun waitForLoad(driver: WebDriver?) {
        val wait = WebDriverWait(driver, 20)
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(
                                ".css-901oao.r-jwli3a.r-1qd0xha.r-1blvdjr.r-16dba41.r-ad9z0x.r-bcqeeo.r-bnwqim.r-qvutc0"
                        )
                )
        )
    }

    data class PageContent(val value: String) {

        fun extractTweet(tweetId: TweetId): Tweet {
            val article = Jsoup.parse(value).select("article").toList()
                    .first { elementContainsUniqueTweetElement(it, tweetId) }
            println(article)
            val usernameString = article
                    .selectFirst("div[class=css-1dbjc4n r-1wbh5a2 r-dnmrzs r-1ny4l3l]")!!
                    .selectFirst("div[dir=ltr]")
                    .text()
            val tweetString = article.selectFirst(".css-901oao.r-jwli3a.r-1qd0xha.r-1blvdjr.r-16dba41.r-ad9z0x.r-bcqeeo.r-bnwqim.r-qvutc0").text()
            return Tweet(
                    tweetId,
                    TweetContent(tweetString),
                    TweetUsername(usernameString)
            )
        }

        private fun elementContainsUniqueTweetElement(element: Element, tweetId: TweetId) =
                element.select("a").toList().any {
                    it.attr("href")?.contains("/${tweetId.value}/retweets") ?: false
                }

    }

    companion object {
        private fun getTwitterUrlBy(tweetId: TweetId) = "https://twitter.com/any/status/${tweetId.value}"
    }

}