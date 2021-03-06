package pl.theliver.tweetidscrapper.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.theliver.tweetidscrapper.application.SimpleTweetExtractor
import pl.theliver.tweetidscrapper.infrastructure.singleextractors.*

@Configuration
class ExtractorsConfiguration {
    @Bean
    fun getSimpleTweetExtractors(): List<SimpleTweetExtractor> = listOf(
            NormalTweetExtractor(),
            NotExistsTweetExtractor(),
            RetweetTweetExtractor(),
            SuspendAccountTweetExtractor(),
            ProfileRedirectTweetExtractor()
    )
}
