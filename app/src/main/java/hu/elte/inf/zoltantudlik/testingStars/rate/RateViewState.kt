package hu.elte.inf.zoltantudlik.testingStars.rate

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseViewState

data class RateViewState(val loading: Boolean, val success: Boolean, val error: Throwable?): BaseViewState