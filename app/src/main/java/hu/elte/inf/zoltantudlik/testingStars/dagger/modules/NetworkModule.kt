package hu.elte.inf.zoltantudlik.testingStars.dagger.modules

import android.app.Application
import com.example.zoltantudlik.testing_stars.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.elte.inf.zoltantudlik.testingStars.rest.ContentService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideNetworkCache(application: Application): Cache {
        return Cache(application.cacheDir, 1024 * 1024 * 50)
    }

    @Provides
    @Singleton
    @Named("CacheControlInterceptor")
    fun provideCacheControlInterceptor(): Interceptor = Interceptor { chain ->
        chain.proceed(chain.request()).newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", String.format("max-age=%d", 3600))
                .build()
    }

    @Provides
    @Singleton
    @Named("LoggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    @Named("NoCacheOkhttp")
    fun provideOkHttpClientContent(@Named("LoggingInterceptor") loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    @Named("CachedOkhttp")
    fun provideOkHttpClientStatic(cache: Cache, @Named("CacheControlInterceptor") cacheControlInterceptor: Interceptor,
                                  @Named("LoggingInterceptor") loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(cacheControlInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }


    @Provides
    @Singleton
    open fun provideContentService(@Named("NoCacheOkhttp") okHttpClientNoCache: OkHttpClient, gson: Gson): ContentService {
        val restAdapter = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClientNoCache)
                .build()
        return restAdapter.create(ContentService::class.java)
    }
}