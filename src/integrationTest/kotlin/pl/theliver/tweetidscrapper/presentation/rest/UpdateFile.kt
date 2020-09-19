package pl.theliver.tweetidscrapper.presentation.rest

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File

private fun getSingleTestCase(id: String): ScrapTweetControllerTest.TestCase {
    val client: OkHttpClient = OkHttpClient().newBuilder()
            .build()
    val request: Request = Request.Builder()
            .url("http://192.168.0.124:8068/scrapTweet/$id")
            .method("GET", null)
            .build()
    val response: Response = client.newCall(request).execute()
    return ScrapTweetControllerTest.TestCase(id, response.body!!.string(), response.code)
}

fun main() {
    val classLoader = ScrapTweetControllerTest::class.java.classLoader!!
    val stream = classLoader.getResourceAsStream("ids.txt")!!
    val content = stream.readAllBytes().toString(Charsets.UTF_8)
    val lines = content.lines()
    val responses = lines.map { getSingleTestCase(it) }
    val json = Gson().toJson(responses)!!
    File("new_test_data").writeText(json)
}
