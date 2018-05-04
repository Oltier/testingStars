package hu.elte.inf.zoltantudlik.testingStars.rate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.example.zoltantudlik.testing_stars.R
import com.github.ornolfr.ratingview.RatingView
import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseController
import hu.elte.inf.zoltantudlik.testingStars.people.PeopleController
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_profile.view.*

class RateController() : BaseController<RateContract, RateViewState, RateViewStateChange, RatePresenter>(), RateContract {

    @BindView(R.id.btnSendReview)
    lateinit var btnSendRating: Button

    @BindView(R.id.ratingView)
    lateinit var ratingView: RatingView

    override fun rating(): Observable<Rating> {
        return ratingSubject
    }

    private val ratingSubject: PublishSubject<Rating> = PublishSubject.create()

    lateinit var person: User

    constructor(parent: User) : this() {
        this.person = parent
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.view_profile, container, false)
    }

    override fun createPresenter(): RatePresenter {
        return RatePresenter(getInitialViewState())
    }

    override fun getInitialViewState(): RateViewState {
        return RateViewState(false, false, null)
    }

    override fun onPostCreateView(view: View) {
        view.nickName?.text = person.name


        ratingView.setOnRatingChangedListener { _, newRating ->
            if (newRating != 0.00f) {
                btnSendRating.alpha = 1f
                btnSendRating.setOnClickListener {
                    if (view.ratingView.rating != 0.0f) {
                        ratingSubject.onNext(Rating(id = person.id, value = newRating.toInt()))
                    }
                }
            }
        }

    }

    override fun render(viewState: RateViewState) {
        if (viewState.loading) {
            btnSendRating.isEnabled = false
            ratingView.isEnabled = false
        } else {
            btnSendRating.isEnabled = true
            ratingView.isEnabled = true
        }

        if (viewState.error != null) {
            Toast.makeText(applicationContext, "Rating sending failed", Toast.LENGTH_SHORT).show()
        }

        if (viewState.success) {

            Toast.makeText(applicationContext, "Rating successfully sent.", Toast.LENGTH_SHORT).show()
            router.setRoot(RouterTransaction.with(PeopleController()).popChangeHandler(FadeChangeHandler()).pushChangeHandler(FadeChangeHandler()))

        }
    }
}