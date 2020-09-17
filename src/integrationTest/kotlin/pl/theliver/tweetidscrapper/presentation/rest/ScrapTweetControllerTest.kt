package pl.theliver.tweetidscrapper.presentation.rest

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class ScrapTweetControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testGetBirthdayDOW() {
        readTestData().stream().parallel().forEach { testScrapTweet(it) }
    }

    private fun testScrapTweet(testCase: TextCase) {
        val path = "/scrapTweet/${testCase.id}"
        val result: MvcResult = mockMvc.perform(MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andReturn()

        val resultContent = result.response.contentAsByteArray.toString(Charsets.UTF_8)
        assertNotNull(resultContent)
        assertEquals(testCase.response, resultContent)
    }

    private fun readTestData(): List<TextCase> {
        val classLoader = ScrapTweetControllerTest::class.java.classLoader!!
        val stream = classLoader.getResourceAsStream("test_data.json")!!
        val content = stream.readAllBytes().toString(Charsets.UTF_8)
        return Gson().fromJson(content)
    }

    private inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)


    data class TextCase(val id: String, val response: String, val code: Int)

}