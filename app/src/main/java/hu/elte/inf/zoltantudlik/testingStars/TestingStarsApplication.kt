package hu.elte.inf.zoltantudlik.testingStars

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


open class TestingStarsApplication : Application() {

    companion object {
        lateinit var instance: TestingStarsApplication
    }

    override fun onCreate() {
        super.onCreate()

        TestingStarsApplication.instance = this


        RxJavaPlugins.setErrorHandler { throwable -> Timber.e(throwable) }
    }
}