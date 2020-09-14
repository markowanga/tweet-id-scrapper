package pl.theliver.tweetidscrapper.infrastructure

import org.jsoup.Jsoup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import pl.theliver.tweetidscrapper.application.PageDownloader
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent


@Component
class SeleniumPageDownloader(
        private val webDriverProvider: WebDriverProvider
) : PageDownloader {

    override fun getTwitterPage(tweetId: TweetId): TweetPageContent {
        val contentTweet: TweetPageContent
        val driver: WebDriver = webDriverProvider.getWebDriver()
        try {
            println("Open url: ${getTwitterUrlBy(tweetId)}")
            driver.get(getTwitterUrlBy(tweetId))
            waitForLoad(driver, tweetId)
            contentTweet = TweetPageContent(driver.pageSource, tweetId)
        } finally {
            driver.quit()
        }
        return contentTweet
    }

    private fun waitForLoad(driver: WebDriver?, tweetId: TweetId) {
        val wait = WebDriverWait(driver, 40)
        try {
            wait.until { TweetPageContent.elementContainsTweetDetails(Jsoup.parse(it!!.pageSource!!), tweetId) }
        } catch (e: Exception) {
            println(e)
        }
    }

    companion object {
        private fun getTwitterUrlBy(tweetId: TweetId) = "https://twitter.com/any/status/${tweetId.value}"
    }

}