package pl.theliver.tweetidscrapper.application

import pl.theliver.tweetidscrapper.domain.PageContent
import pl.theliver.tweetidscrapper.domain.Url

interface PageDownloadService {
    fun getTwitterPage(url: Url): PageContent
}