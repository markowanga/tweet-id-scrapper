package pl.theliver.tweetidscrapper.presentation.rest.dto


data class TweetScrapResponse(
        val scrapStatus: Boolean,
        val tweet: TweetDto?
)