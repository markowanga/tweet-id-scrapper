package pl.theliver.tweetidscrapper.presentation.rest.dto

import io.swagger.annotations.ApiModelProperty
import pl.theliver.tweetidscrapper.domain.TweetExtractResult

data class TweetResultDto(
        @ApiModelProperty("Status of tweet result")
        val status: Status,
        @ApiModelProperty("Returned tweet details, when other status than SUCCESS, value is null")
        val tweet: TweetDto?
) {

    enum class Status {
        SUCCESS, NO_EXISTS, ERROR, SUSPEND_ACCOUNT
    }

    companion object {

        fun from(tweetExtractResult: TweetExtractResult) =
                when (tweetExtractResult) {
                    is TweetExtractResult.ExtractError -> nullableTweetResult(Status.ERROR)
                    is TweetExtractResult.ExtractSuccessful ->
                        TweetResultDto(Status.SUCCESS, TweetDto.from(tweetExtractResult.value))
                    is TweetExtractResult.TweetNotExists -> nullableTweetResult(Status.NO_EXISTS)
                    TweetExtractResult.SuspendAccount -> nullableTweetResult(Status.SUSPEND_ACCOUNT)
                }

        private fun nullableTweetResult(status: Status) = TweetResultDto(status, null)

    }

}