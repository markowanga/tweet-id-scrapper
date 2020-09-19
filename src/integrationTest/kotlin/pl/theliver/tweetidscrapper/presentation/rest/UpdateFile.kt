package pl.theliver.tweetidscrapper.presentation.rest

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File

internal fun getSingleTestCase(id: String, host: String): ScrapTweetControllerTest.TestCase {
    val client: OkHttpClient = OkHttpClient().newBuilder()
            .build()
    val request: Request = Request.Builder()
            .url("$host/scrapTweet/$id")
            .method("GET", null)
            .build()
    val response: Response = client.newCall(request).execute()
    return ScrapTweetControllerTest.TestCase(id, response.body!!.string(), response.code)
}

internal fun main() {
    val classLoader = ScrapTweetControllerTest::class.java.classLoader!!
    val stream = classLoader.getResourceAsStream("ids.txt")!!
    val content = stream.readAllBytes().toString(Charsets.UTF_8)
    val lines = content.lines()
    val responses = lines.map {
        getSingleTestCase(it, "http://192.168.0.124:8068")
    }
    val json = Gson().toJson(responses)!!
    File("test_data.json").writeText(json)
}
