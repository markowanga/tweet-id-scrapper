package pl.theliver.tweetidscrapper.presentation.rest

import org.springframework.web.bind.annotation.*
import pl.theliver.tweetidscrapper.application.ScrapGateway
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent
import pl.theliver.tweetidscrapper.presentation.rest.dto.TweetResultDto

@RequestMapping("/parseTweet")
@RestController
class ParseTweetController(private val scrapGateway: ScrapGateway) {

    @PostMapping("/{tweetId}")
    fun scrapTweet(
            @PathVariable tweetId: String,
            @RequestBody htmlContent: String
    ) = TweetResultDto.from(
            scrapGateway.parseTweetPage(TweetPageContent(htmlContent), TweetId(tweetId))
    ).wrapInResponseEntity()

}
