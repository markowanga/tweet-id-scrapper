package pl.theliver.tweetidscrapper.presentation.rest

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments


internal class ScrapTweetControllerTest {

    @Test
    fun testScrapTweets() {
        readTestData().forEach { testScrapTweet(it) }
    }

    private fun testScrapTweet(testCase: TestCase) {
        val current = getSingleTestCase(testCase.id, "http://192.168.0.124:8068")
        println("current : $current")
        println("expected: $testCase")
        assertEquals(testCase, current)
    }


    data class TestCase(val id: String, val response: String, val code: Int) : Arguments {
        override fun get(): Array<Any> {
            return listOf(id, response, code).toTypedArray()
        }
    }

    companion object {
        fun readTestData(): Array<TestCase> {
            val classLoader = ScrapTweetControllerTest::class.java.classLoader!!
            val stream = classLoader.getResourceAsStream("test_data.json")!!
            val content = stream.readAllBytes().toString(Charsets.UTF_8)
            val list: List<TestCase> = Gson().fromJson(content)
            return list.toTypedArray()
        }

        private inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)

    }

}