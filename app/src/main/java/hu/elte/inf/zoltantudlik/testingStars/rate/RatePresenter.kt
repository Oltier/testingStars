package hu.elte.inf.zoltantudlik.testingStars.rate

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BasePresenter
import hu.elte.inf.zoltantudlik.testingStars.rest.ApiManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*

class RatePresenter(initialViewState: RateViewState) : BasePresenter<RateContract, RateViewState, RateViewStateChange>(initialViewState) {

    override fun prepareIntentObservables(): ArrayList<Observable<RateViewStateChange>> {
        val observables = ArrayList<Observable<RateViewStateChange>>()

        observables.add(intent { view ->
            view.rating()
                    .flatMap { review ->
                        ApiManager.instance.addRating(review)
                                .andThen(Observable.just(true))
                                .map<RateViewStateChange> { _ -> RateViewStateChange.Loaded(true) }
                                .startWith(RateViewStateChange.Loading(true))
                                .onErrorReturn { throwable: Throwable -> RateViewStateChange.Error(throwable) }
                                .subscribeOn(Schedulers.io())
                    }
        })

        return observables
    }
}