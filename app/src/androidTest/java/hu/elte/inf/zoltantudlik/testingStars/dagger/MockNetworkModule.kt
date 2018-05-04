package hu.elte.inf.zoltantudlik.testingStars.dagger

import android.support.test.InstrumentationRegistry
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import hu.elte.inf.zoltantudlik.testingStars.dagger.modules.NetworkModule
import hu.elte.inf.zoltantudlik.testingStars.rest.ContentService
import hu.elte.inf.zoltantudlik.testingStars.rest.MockContentService
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
open class MockNetworkModule : NetworkModule() {

    @Provides
    @Singleton
    override fun provideContentService(okHttpClientNoCache: OkHttpClient, gson: Gson): ContentService {
        return MockContentService(InstrumentationRegistry.getContext(), gson)
    }
}