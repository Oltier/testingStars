package hu.elte.inf.zoltantudlik.testingStars.dagger.components

import dagger.Component
import hu.elte.inf.zoltantudlik.testingStars.dagger.modules.RepositoryModule
import hu.elte.inf.zoltantudlik.testingStars.people.PeoplePresenter
import hu.elte.inf.zoltantudlik.testingStars.rate.RatePresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoryModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PresenterComponent {

    fun inject(peoplePresenter: PeoplePresenter)
    fun inject(ratePresenter: RatePresenter)

}