package pl.theliver.tweetidscrapper.presentation.rest.dto

import pl.theliver.tweetidscrapper.domain.Tweet
import java.text.SimpleDateFormat

data class TweetDto(
        val tweetId: String,
        val tweetContent: String,
        val tweetUsername: String,
        val createdDate: String
) {
    companion object {
        fun from(tweet: Tweet): TweetDto = with(tweet) {
            TweetDto(
                    id.value,
                    content.value,
                    username.value,
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(createdDate.value)
            )
        }
    }
}
