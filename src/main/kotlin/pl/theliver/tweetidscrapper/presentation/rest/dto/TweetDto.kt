package pl.theliver.tweetidscrapper.presentation.rest.dto

data class TweetDto(
        val tweetId: String,
        val tweetContent: String,
        val tweetUsername: String,
        val createdDate: String
)
