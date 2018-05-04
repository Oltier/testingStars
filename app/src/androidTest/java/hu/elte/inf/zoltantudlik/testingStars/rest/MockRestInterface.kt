package hu.elte.inf.zoltantudlik.testingStars.rest

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import java.io.*
import java.lang.reflect.Type


open class MockRestInterface(val context: Context, val gson: Gson) {

    companion object {
        @JvmStatic
        var IS_CONNECTED_TO_INTERNET = true
    }

    @Throws(FileNotFoundException::class, JsonIOException::class, JsonSyntaxException::class)
    fun <T> readJsonFromAssets(assetPath: String, type: Type): T {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open(assetPath)
        return gson.fromJson<T>(InputStreamReader(inputStream, "UTF-8"), type)
    }

    @Throws(FileNotFoundException::class, JsonIOException::class, JsonSyntaxException::class)
    fun <T> readJsonFromAssets(assetPath: String, clazz: Class<T>): T {
        val assetManager = context.resources.assets
        val inputStream: InputStream = assetManager.open(assetPath)
        return gson.fromJson<T>(InputStreamReader(inputStream, "UTF-8"), clazz)
    }

    @Throws(FileNotFoundException::class, IOException::class)
    fun readJsonStringFromAssets(assetPath: String): String? {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open(assetPath)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

        bufferedReader.use { reader ->
            val buf = StringBuilder()
            var str: String?
            str = reader.readLine()
            while (str != null) {
                buf.append(str)
                str = reader.readLine()
            }
            return buf.toString()
        }
    }
}