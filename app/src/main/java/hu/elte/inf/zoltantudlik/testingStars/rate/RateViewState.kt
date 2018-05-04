package hu.elte.inf.zoltantudlik.testingStars.rate

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseViewState

data class RateViewState(val loading: Boolean = false, val success: Boolean = false, val error: Throwable? = null): BaseViewState