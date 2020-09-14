package pl.theliver.tweetidscrapper.presentation.rest.dto

import pl.theliver.tweetidscrapper.domain.TweetExtractResult

data class TweetResultDto(
        val status: Status,
        val tweet: TweetDto?
) {

    enum class Status {
        SUCCESS, NO_EXISTS, ERROR
    }

    companion object {
        fun from(tweetExtractResult: TweetExtractResult) =
                when (tweetExtractResult) {
                    is TweetExtractResult.ExtractError -> TweetResultDto(Status.ERROR, null)
                    is TweetExtractResult.ExtractSuccessful ->
                        TweetResultDto(Status.SUCCESS, TweetDto.from(tweetExtractResult.value))
                    is TweetExtractResult.TweetNotExists -> TweetResultDto(Status.NO_EXISTS, null)
                }

    }

}