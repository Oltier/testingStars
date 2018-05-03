package hu.elte.inf.zoltantudlik.testingStars.common.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseViewContract<in VS : BaseViewState> : MvpView {
    fun render(viewState: VS)
}