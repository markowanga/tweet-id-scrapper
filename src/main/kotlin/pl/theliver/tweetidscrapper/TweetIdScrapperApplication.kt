package pl.theliver.tweetidscrapper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class TweetIdScrapperApplication

fun main(args: Array<String>) {
    runApplication<TweetIdScrapperApplication>(*args)
}
