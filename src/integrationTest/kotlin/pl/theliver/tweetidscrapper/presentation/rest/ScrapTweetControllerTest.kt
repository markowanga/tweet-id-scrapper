package pl.theliver.tweetidscrapper.presentation.rest

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.provider.Arguments
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import pl.theliver.tweetidscrapper.application.ScrapGateway
import pl.theliver.tweetidscrapper.domain.TweetId
import pl.theliver.tweetidscrapper.presentation.rest.dto.TweetResultDto

@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class ScrapTweetControllerTest {

    @Autowired
    lateinit var scrapGateway: ScrapGateway

    @Test
    fun testScrapTweets() {
        readTestData().forEach { testScrapTweet(it) }
    }

    fun testScrapTweet(textCase: TextCase) {
        val response = scrapGateway.scrapTweetBy(TweetId(textCase.id))
                .let { TweetResultDto.from(it) }
        val responseJson = Gson().toJson(response)
        println(textCase.response, responseJson)
        assertEquals(textCase.response, responseJson)
    }


    data class TextCase(val id: String, val response: String, val code: Int) : Arguments {
        override fun get(): Array<Any> {
            return listOf(id, response, code).toTypedArray()
        }
    }

    companion object {
        fun readTestData(): Array<TextCase> {
            val classLoader = ScrapTweetControllerTest::class.java.classLoader!!
            val stream = classLoader.getResourceAsStream("test_data.json")!!
            val content = stream.readAllBytes().toString(Charsets.UTF_8)
            val list: List<TextCase> = Gson().fromJson(content)
//            return list
            return list.toTypedArray()
        }

        private inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)

    }

}