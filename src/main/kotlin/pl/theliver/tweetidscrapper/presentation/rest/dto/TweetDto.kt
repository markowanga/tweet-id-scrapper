package pl.theliver.tweetidscrapper.presentation.rest.dto

import io.swagger.annotations.ApiModelProperty
import pl.theliver.tweetidscrapper.domain.Tweet
import java.text.SimpleDateFormat

data class TweetDto(
        @ApiModelProperty("Id of returned tweet")
        val tweetId: String,
        @ApiModelProperty("Content of returned tweet")
        val tweetContent: String,
        @ApiModelProperty("Author username of returned tweet")
        val tweetUsername: String,
        @ApiModelProperty("Created date time of tweet in format yyyy-MM-ddTHH:mm", example="2020-06-12T12:42")
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
