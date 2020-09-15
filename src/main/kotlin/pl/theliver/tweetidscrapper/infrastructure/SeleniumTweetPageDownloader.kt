package pl.theliver.tweetidscrapper.infrastructure

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import pl.theliver.tweetidscrapper.application.TweetExtractor
import pl.theliver.tweetidscrapper.application.TweetPageDownloader
import pl.theliver.tweetidscrapper.domain.TweetExtractResult
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent


@Component
class SeleniumTweetPageDownloader(
        private val webDriverProvider: WebDriverProvider,
        private val tweetExtractor: TweetExtractor
) : TweetPageDownloader {

    override fun getTwitterPage(tweetId: TweetId, maxSecondsWait: Long): TweetPageContent {
        val contentTweet: TweetPageContent
        val driver: WebDriver = webDriverProvider.getWebDriver()
        try {
            println("Open url: ${getTwitterUrlBy(tweetId)}")
            driver.get(getTwitterUrlBy(tweetId))
            waitForLoad(driver, tweetId, maxSecondsWait)
            contentTweet = TweetPageContent(driver.pageSource)
        } finally {
            driver.quit()
        }
        return contentTweet
    }

    private fun waitForLoad(driver: WebDriver?, tweetId: TweetId, maxSecondsWait: Long) {
        val wait = WebDriverWait(driver, maxSecondsWait)
        try {
            wait.until {
                tweetExtractor.extractTweet(TweetPageContent(it.pageSource), tweetId) != TweetExtractResult.ExtractError
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    companion object {
        private fun getTwitterUrlBy(tweetId: TweetId) = "https://twitter.com/any/status/${tweetId.value}"
    }

}