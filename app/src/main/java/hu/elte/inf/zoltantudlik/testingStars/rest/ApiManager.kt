//package hu.elte.inf.zoltantudlik.testingStars.rest
//
//import com.google.gson.*
//import hu.elte.inf.zoltantudlik.testingStars.rest.entities.AddRating
//import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
//import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
//import io.reactivex.Completable
//import io.reactivex.Observable
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//class ApiManager private constructor() {
//
//
//    private object Holder {
//        val INSTANCE = ApiManager()
//    }
//
//    companion object {
//        val BASE_URL = "https://api.quality-stars.medicalbox.online/"
//
//        val instance: ApiManager by lazy { Holder.INSTANCE }
//    }
//
//    var contentService: ContentService
//
//    init {
//        val loggingInterceptor = HttpLoggingInterceptor()
//
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//
//        val gson = GsonBuilder().create()
//
//        val restAdapter = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(okHttpClient)
//                .build()
//
//        contentService = restAdapter.create(ContentService::class.java)
//    }
//}