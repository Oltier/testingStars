package hu.elte.inf.zoltantudlik.testingStars.common.mvi

interface BaseViewStateChange<VS : BaseViewState> {
    fun computeNewState(previousState: VS): VS
}