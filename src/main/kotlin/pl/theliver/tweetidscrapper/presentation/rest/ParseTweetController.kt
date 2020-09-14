package pl.theliver.tweetidscrapper.presentation.rest

import org.springframework.web.bind.annotation.*
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent
import pl.theliver.tweetidscrapper.presentation.rest.dto.TweetDto

@RequestMapping("/scrapTweet")
@RestController
class ParseTweetController {

    @PostMapping("/{tweetId}")
    fun scrapTweet(
            @PathVariable tweetId: String,
            @RequestBody htmlContent: String
    ) = TweetDto.from(TweetPageContent(htmlContent, TweetId(tweetId)).extractTweet())

}
