package pl.theliver.tweetidscrapper.infrastructure

import org.openqa.selenium.WebDriver


interface WebDriverProvider {
    fun getWebDriver(): WebDriver
}