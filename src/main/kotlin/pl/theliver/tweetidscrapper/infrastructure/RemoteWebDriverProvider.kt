package pl.theliver.tweetidscrapper.infrastructure

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.springframework.stereotype.Component
import pl.theliver.tweetidscrapper.configuration.PropertiesConfig
import java.net.URL

@Component
class RemoteWebDriverProvider(
        private val propertiesConfig: PropertiesConfig
) : WebDriverProvider {

    override fun getWebDriver(): WebDriver {
        val driverOptions = ChromeOptions().apply { setAcceptInsecureCerts(true) }
        return RemoteWebDriver(URL(propertiesConfig.seleniumHost), driverOptions)
    }

}
