package hu.elte.inf.zoltantudlik.testingStars.common.mvi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.hannesdorfmann.mosby3.MviController

abstract class BaseController<V : BaseViewContract<VS>, VS : BaseViewState, VSC : BaseViewStateChange<VS>, P : BasePresenter<V, VS, VSC>> : MviController<V, P>(), BaseViewContract<VS> {

    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        unbinder = ButterKnife.bind(this, view)
        onPostCreateView(view)
        return view
    }

    override fun onDestroyView(view: View) {
        unbinder.unbind()
        super.onDestroyView(view)
    }

    abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    abstract fun onPostCreateView(view: View)

    abstract fun getInitialViewState(): VS
}