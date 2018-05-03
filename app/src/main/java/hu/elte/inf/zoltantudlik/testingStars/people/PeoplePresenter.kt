package hu.elte.inf.zoltantudlik.testingStars.people

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BasePresenter
import hu.elte.inf.zoltantudlik.testingStars.rest.ApiManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PeoplePresenter(initialViewState: PeopleViewState) : BasePresenter<PeopleContract, PeopleViewState, PeopleViewStateChange>(initialViewState) {

    override fun prepareIntentObservables(): ArrayList<Observable<PeopleViewStateChange>> {
        val observables = ArrayList<Observable<PeopleViewStateChange>>()

        observables.add(ApiManager.instance.getUsers()
                .map<PeopleViewStateChange> { data -> PeopleViewStateChange.Loaded(data) }
                .startWith(PeopleViewStateChange.Loading(true))
                .onErrorReturn { throwable -> PeopleViewStateChange.Error(throwable) }
                .subscribeOn(Schedulers.io())
        )

        return observables
    }
}