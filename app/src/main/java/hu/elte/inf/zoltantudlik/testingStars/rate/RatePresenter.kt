package hu.elte.inf.zoltantudlik.testingStars.rate

import hu.elte.inf.zoltantudlik.testingStars.TestingStarsApplication
import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BasePresenter
import hu.elte.inf.zoltantudlik.testingStars.repositories.ContentRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class RatePresenter(initialViewState: RateViewState) : BasePresenter<RateContract, RateViewState, RateViewStateChange>(initialViewState) {

    @Inject
    lateinit var contentRepository: ContentRepository

    init {
        TestingStarsApplication.presenterComponent.inject(this)
    }

    override fun prepareIntentObservables(): ArrayList<Observable<RateViewStateChange>> {
        val observables = ArrayList<Observable<RateViewStateChange>>()

        observables.add(intent { view -> view.rating() }
                .flatMap { rating ->
                    contentRepository.addRating(rating)
                            .andThen(Observable.just(true))
                            .map<RateViewStateChange> { _ -> RateViewStateChange.Loaded(true) }
                            .startWith(RateViewStateChange.Loading(true))
                            .onErrorReturn { throwable: Throwable -> RateViewStateChange.Error(throwable) }
                            .subscribeOn(Schedulers.io())
                }
        )

        return observables
    }
}