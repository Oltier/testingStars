package hu.elte.inf.zoltantudlik.testingStars.utils

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.example.zoltantudlik.testing_stars.R
import com.github.ornolfr.ratingview.RatingView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


object ViewHelper {

    fun isRefreshing(): Matcher<View> {
        return object : BoundedMatcher<View, SwipeRefreshLayout>(SwipeRefreshLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("SwipeRefreshLayout is refreshing")
            }

            override fun matchesSafely(view: SwipeRefreshLayout): Boolean {
                return view.isRefreshing
            }
        }
    }

    fun hasRating(expected: Float): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText(expected.toString())
            }

            override fun matchesSafely(item: View?): Boolean {
                val ratingView: RatingView = item!!.findViewById<RatingView>(R.id.ratingView)
                return ratingView.rating == expected
            }

        }
    }

    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? = null

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }

    fun waitForThrottleTimeout(throttleFirstTimeoutMillis: Long, wrappedAction: ViewAction): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? = wrappedAction.constraints

            override fun getDescription(): String {
                return wrappedAction.description + " Waiting for throttle first timeout."
            }

            override fun perform(uiController: UiController, view: View) {
                wrappedAction.perform(uiController, view)
                uiController.loopMainThreadForAtLeast(throttleFirstTimeoutMillis + 50)
            }
        }
    }

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }
}