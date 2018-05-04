package hu.elte.inf.zoltantudlik.testingStars

import hu.elte.inf.zoltantudlik.testingStars.dagger.MockNetworkModule
import hu.elte.inf.zoltantudlik.testingStars.dagger.components.DaggerApplicationComponent
import hu.elte.inf.zoltantudlik.testingStars.dagger.components.DaggerModelComponent
import hu.elte.inf.zoltantudlik.testingStars.dagger.components.DaggerPresenterComponent
import hu.elte.inf.zoltantudlik.testingStars.dagger.modules.AppModule


class TestApplication : TestingStarsApplication() {

    override fun initDagger() {
        val appModule = AppModule(this)
        val applicationComponent = DaggerApplicationComponent.builder()
                .appModule(appModule)
                .build()

        modelComponent = DaggerModelComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(MockNetworkModule())
                .build()

        presenterComponent = DaggerPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
    }
}