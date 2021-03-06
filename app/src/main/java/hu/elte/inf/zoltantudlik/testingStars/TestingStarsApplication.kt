package hu.elte.inf.zoltantudlik.testingStars

import android.app.Application
import hu.elte.inf.zoltantudlik.testingStars.dagger.components.*
import hu.elte.inf.zoltantudlik.testingStars.dagger.modules.AppModule
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


open class TestingStarsApplication : Application() {

    companion object {
        @JvmStatic lateinit var modelComponent: ModelComponent
        @JvmStatic lateinit var presenterComponent: PresenterComponent
    }

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { throwable -> Timber.e(throwable) }

        initDagger()
    }

    open fun initDagger() {
        val appModule = AppModule(this)

        val applicationComponent = DaggerApplicationComponent.builder()
                .appModule(appModule)
                .build()


        modelComponent = DaggerModelComponent.builder()
                .applicationComponent(applicationComponent)
                .build()

        presenterComponent = DaggerPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
    }
}