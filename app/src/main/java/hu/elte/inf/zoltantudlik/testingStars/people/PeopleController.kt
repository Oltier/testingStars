package hu.elte.inf.zoltantudlik.testingStars.people

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.zoltantudlik.testing_stars.R
import hu.elte.inf.zoltantudlik.testingStars.common.mvi.BaseController
import hu.elte.inf.zoltantudlik.testingStars.rate.RateController
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import kotlinx.android.synthetic.main.view_personlist.view.*

class PeopleController : BaseController<PeopleContract, PeopleViewState, PeopleViewStateChange, PeoplePresenter>(), PeopleContract {

    var isEnabled: Boolean = true

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.view_personlist, container, false)
    }

    override fun onPostCreateView(view: View) {
        view.personRecyclerView.layoutManager = LinearLayoutManager(activity?.baseContext, LinearLayoutManager.VERTICAL, false)
        view.personRecyclerView.adapter = recyclerAdapter

    }

    override fun getInitialViewState(): PeopleViewState {
        return PeopleViewState(true, null, null)
    }

    override fun createPresenter() = PeoplePresenter(getInitialViewState())

    override fun onAttach(view: View) {
        super.onAttach(view)

        isEnabled = true
    }

    private val recyclerAdapter = PeopleAdapter(object : PeopleClickListener<User> {
        override fun onClick(person: User) {
            if (isEnabled) {
                isEnabled = false
                router.pushController(RouterTransaction.with(RateController(person)).pushChangeHandler(FadeChangeHandler()).popChangeHandler(FadeChangeHandler()))
            }
        }
    })

    override fun render(viewState: PeopleViewState) {
        if (viewState.loading) {
        } else {
            if (viewState.error != null) {
                Toast.makeText(applicationContext, "Unable to retrieve list at the moment :(", Toast.LENGTH_SHORT).show()
            } else {
                if (viewState.people != null) {
                    (view?.personRecyclerView?.adapter as PeopleAdapter).setData(viewState.people)
                }
            }
        }
    }
}