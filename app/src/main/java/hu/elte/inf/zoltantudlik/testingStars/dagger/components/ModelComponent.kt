package hu.elte.inf.zoltantudlik.testingStars.dagger.components

import dagger.Component
import hu.elte.inf.zoltantudlik.testingStars.dagger.modules.NetworkModule
import hu.elte.inf.zoltantudlik.testingStars.repositories.ContentRepository
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ModelComponent {
    fun inject(contentRepository: ContentRepository)
}