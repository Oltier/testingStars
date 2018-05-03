package hu.elte.inf.zoltantudlik.testingStars.people

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseViewStateChange
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User

interface PeopleViewStateChange : BaseViewStateChange<PeopleViewState> {
    class Loading(val loading: Boolean) : PeopleViewStateChange {

        override fun computeNewState(previousState: PeopleViewState): PeopleViewState {
            return previousState.copy(loading = loading)
        }

        override fun toString(): String {
            return "Loading"
        }
    }

    class Loaded(val people: List<User>) : PeopleViewStateChange {

        override fun computeNewState(previousState: PeopleViewState): PeopleViewState {
            return previousState.copy(loading = false, people = people, error = null)
        }

        override fun toString(): String {
            return "Loaded"
        }
    }

    class Error(val error: Throwable) : PeopleViewStateChange {
        override fun computeNewState(previousState: PeopleViewState): PeopleViewState {
            return previousState.copy(loading = false, error = error)
        }

        override fun toString(): String {
            error.printStackTrace()
            return "Error"
        }
    }
}