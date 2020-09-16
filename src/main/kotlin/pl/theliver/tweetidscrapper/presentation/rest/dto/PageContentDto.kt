package pl.theliver.tweetidscrapper.presentation.rest.dto

import io.swagger.annotations.ApiModelProperty

data class PageContentDto(
        @ApiModelProperty("Html content of the page")
        val pageContent: String
)