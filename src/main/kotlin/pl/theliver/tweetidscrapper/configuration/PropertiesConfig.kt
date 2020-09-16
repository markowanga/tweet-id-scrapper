package pl.theliver.tweetidscrapper.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("pl.theliver.tweetidscrapper")
class PropertiesConfig {
    var seleniumHost: String = ""
}