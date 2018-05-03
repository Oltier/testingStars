package hu.elte.inf.zoltantudlik.testingStars.rest

import com.google.gson.*
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.AddRating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager private constructor() {

//    var deserializer: JsonDeserializer<User> = object : JsonDeserializer<User> {
//        @Throws(JsonParseException::class)
//        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): User {
//            val jsonObject = json.asJsonObject
//
//            val name = jsonObject.get("name").asString
//            val id = jsonObject.get("id").asString
//            val _ratings = jsonObject.getAsJsonArray("_ratings")
//                    .map{ rating -> }
//
//            return User(name = name, id = id, _ratings = _ratings)
//        }
//    }

    private object Holder {
        val INSTANCE = ApiManager()
    }

    companion object {
        val BASE_URL = "https://api.quality-stars.medicalbox.online/"

        val instance: ApiManager by lazy { Holder.INSTANCE }
    }

    var contentService: ContentService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

//        val gson = GsonBuilder().registerTypeAdapter(User::class.java, deserializer).create()
        val gson = GsonBuilder().create()

        val restAdapter = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

        contentService = restAdapter.create(ContentService::class.java)
    }

    fun getUsers(): Observable<List<User>> {
        return contentService.getUsers()
    }

    fun addRating(rating: Rating) : Completable {
        return contentService.addRating(rating.id, AddRating(rating.value))
    }
}