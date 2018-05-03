package hu.elte.inf.zoltantudlik.testingStars.people

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseViewState
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User

data class PeopleViewState(var loading: Boolean = false,
                           val people: List<User>? = null,
                           val error: Throwable? = null) : BaseViewState