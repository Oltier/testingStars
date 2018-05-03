package hu.elte.inf.zoltantudlik.testingStars.rate

import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseViewContract
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import io.reactivex.Observable

interface RateContract : BaseViewContract<RateViewState> {
    fun rating(): Observable<Rating>
}