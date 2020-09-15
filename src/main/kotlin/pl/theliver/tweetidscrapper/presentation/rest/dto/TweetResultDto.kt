package pl.theliver.tweetidscrapper.presentation.rest.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.theliver.tweetidscrapper.domain.TweetExtractResult

data class TweetResultDto(
        val status: Status,
        val tweet: TweetDto?
) {

    fun wrapInResponseEntity() =
            ResponseEntity(
                    this,
                    when (status) {
                        Status.ERROR -> HttpStatus.INTERNAL_SERVER_ERROR
                        else -> HttpStatus.OK
                    }
            )

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