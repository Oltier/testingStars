package hu.elte.inf.zoltantudlik.testingStars.common.mvi

import android.annotation.SuppressLint
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.*

abstract class BasePresenter<V : BaseViewContract<VS>, VS : BaseViewState, VSC : BaseViewStateChange<VS>>(private val initialState: VS) : MviBasePresenter<V, VS>(initialState) {

    abstract fun prepareIntentObservables(): ArrayList<Observable<VSC>>

    @SuppressLint("BinaryOperationInTimber")
    fun reduceState(previousViewState: VS, stateChanges: VSC): VS {
        Timber.i(this.javaClass.simpleName + " -> " + stateChanges.toString())

        return stateChanges.computeNewState(previousViewState)
    }

    override fun bindIntents() {
        val intentObservables = prepareIntentObservables()

        val stateObservable = Observable.merge(intentObservables)
                .scan(initialState, { previousViewState, stateChanges -> this.reduceState(previousViewState, stateChanges) })
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(stateObservable, BaseViewContract<VS>::render)
    }
}
