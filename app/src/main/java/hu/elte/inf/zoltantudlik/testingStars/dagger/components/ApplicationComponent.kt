package hu.elte.inf.zoltantudlik.testingStars.dagger.components

import android.app.Application
import android.content.Context
import dagger.Component
import hu.elte.inf.zoltantudlik.testingStars.dagger.modules.AppModule

@Component(modules = arrayOf(AppModule::class))
interface ApplicationComponent {

    fun getApplication(): Application

    fun getContext(): Context

}