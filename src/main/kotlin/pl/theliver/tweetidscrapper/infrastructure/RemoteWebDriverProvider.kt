package pl.theliver.tweetidscrapper.infrastructure

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.springframework.stereotype.Component
import java.net.URL

@Component
class RemoteWebDriverProvider : WebDriverProvider {

    override fun getWebDriver(): WebDriver {
        val driverOptions = ChromeOptions().apply { setAcceptInsecureCerts(true) }
        return RemoteWebDriver(URL(SELENIUM_HUB_HOST), driverOptions)
    }

    companion object {
        private const val SELENIUM_HUB_HOST = "http://selenium-hub:4444/wd/hub"
    }

}
