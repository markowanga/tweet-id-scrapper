package pl.theliver.tweetidscrapper.domain

data class Tweet(
        val id: TweetId,
        val content: TweetContent,
        val username: TweetUsername
)