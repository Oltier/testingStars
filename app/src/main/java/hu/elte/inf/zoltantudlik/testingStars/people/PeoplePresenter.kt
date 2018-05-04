package hu.elte.inf.zoltantudlik.testingStars.people

import hu.elte.inf.zoltantudlik.testingStars.TestingStarsApplication
import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BasePresenter
import hu.elte.inf.zoltantudlik.testingStars.repositories.ContentRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PeoplePresenter(initialViewState: PeopleViewState) : BasePresenter<PeopleContract, PeopleViewState, PeopleViewStateChange>(initialViewState) {

    @Inject
    lateinit var contentRepository: ContentRepository

    init {
        TestingStarsApplication.presenterComponent.inject(this)
    }

    override fun prepareIntentObservables(): ArrayList<Observable<PeopleViewStateChange>> {
        val observables = ArrayList<Observable<PeopleViewStateChange>>()

        observables.add(contentRepository.getUsers()
                .map<PeopleViewStateChange> { data -> PeopleViewStateChange.Loaded(data) }
                .startWith(PeopleViewStateChange.Loading(true))
                .onErrorReturn { throwable -> PeopleViewStateChange.Error(throwable) }
                .subscribeOn(Schedulers.io())
        )

        return observables
    }
}