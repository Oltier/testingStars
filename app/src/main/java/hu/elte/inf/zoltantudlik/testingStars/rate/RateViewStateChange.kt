package hu.elte.inf.zoltantudlik.testingStars.rate

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseViewStateChange

interface RateViewStateChange : BaseViewStateChange<RateViewState> {

    class Loading(val loading: Boolean) : RateViewStateChange {

        override fun computeNewState(previousState: RateViewState): RateViewState {
            return previousState.copy(loading = loading)
        }

        override fun toString(): String {
            return "Loading"
        }
    }

    class Loaded(val people: Boolean) : RateViewStateChange {

        override fun computeNewState(previousState: RateViewState): RateViewState {
            return previousState.copy(loading = false, success = people, error = null)
        }

        override fun toString(): String {
            return "Loaded"
        }
    }

    class Error(val error: Throwable) : RateViewStateChange {
        override fun computeNewState(previousState: RateViewState): RateViewState {
            return previousState.copy(loading = false, error = error)
        }

        override fun toString(): String {
            error.printStackTrace()
            return "Error"
        }
    }
}