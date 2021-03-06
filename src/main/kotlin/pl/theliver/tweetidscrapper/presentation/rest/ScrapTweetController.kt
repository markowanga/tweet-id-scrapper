package pl.theliver.tweetidscrapper.presentation.rest

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.theliver.tweetidscrapper.application.ScrapGateway
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.presentation.rest.dto.TweetResultDto

@RequestMapping("/scrapTweet")
@RestController
class ScrapTweetController(
        private val scrapGateway: ScrapGateway
) {

    @ApiOperation("Scrap and return tweet by id")
    @GetMapping("/{tweetId}")
    fun scrapTweet(@PathVariable tweetId: String) =
            TweetResultDto.from(scrapGateway.scrapTweetBy(TweetId(tweetId)))

}
