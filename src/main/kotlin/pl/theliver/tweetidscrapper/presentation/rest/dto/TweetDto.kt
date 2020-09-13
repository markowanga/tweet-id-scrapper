package pl.theliver.tweetidscrapper.presentation.rest.dto

data class TweetDto(
        private val tweetId: String,
        private val tweetContent: String,
        private val tweetUsername: String
)
