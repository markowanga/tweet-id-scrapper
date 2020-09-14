package pl.theliver.tweetidscrapper.presentation.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.theliver.tweetidscrapper.application.ScrapGateway
import pl.theliver.tweetidscrapper.domain.Tweet
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.presentation.rest.dto.TweetDto
import java.text.SimpleDateFormat

@RequestMapping("/")
@RestController
class ScrapTweetController(
        private val scrapGateway: ScrapGateway
) {

    @GetMapping("/scrapTweet/{tweetId}")
    fun scrapTweet(@PathVariable tweetId: String): TweetDto {
        println("controller scrapTweet")
        return toDto(scrapGateway.scrapTweetBy(TweetId(tweetId)))
    }

    private fun toDto(tweet: Tweet): TweetDto = with(tweet) {
        TweetDto(
                id.value,
                content.value,
                username.value,
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(createdDate.value)
        )
    }

}
