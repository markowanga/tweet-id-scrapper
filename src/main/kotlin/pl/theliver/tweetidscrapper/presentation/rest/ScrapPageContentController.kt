package pl.theliver.tweetidscrapper.presentation.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.theliver.tweetidscrapper.application.ScrapGateway
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.domain.TweetPageContent
import pl.theliver.tweetidscrapper.presentation.rest.dto.PageContentDto

@RequestMapping("/scrapPageContent")
@RestController
class ScrapPageContentController(
        private val scrapGateway: ScrapGateway
) {

    @GetMapping("/{tweetId}/{maxSecondsWait}")
    fun scrapTweet(
            @PathVariable tweetId: String,
            @PathVariable maxSecondsWait: Long
    ) = toDto(scrapGateway.scrapPageContentBy(TweetId(tweetId), maxSecondsWait))

    private fun toDto(pageContent: TweetPageContent): PageContentDto = PageContentDto(pageContent.value)

}
