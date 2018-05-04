package hu.elte.inf.zoltantudlik.testingStars.utils

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher


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