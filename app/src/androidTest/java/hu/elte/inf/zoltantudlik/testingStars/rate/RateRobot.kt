package hu.elte.inf.zoltantudlik.testingStars.rate

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.zoltantudlik.testing_stars.R
import hu.elte.inf.zoltantudlik.testingStars.utils.BaseRobot
import hu.elte.inf.zoltantudlik.testingStars.utils.RecyclerHelper
import hu.elte.inf.zoltantudlik.testingStars.utils.ViewHelper
import org.hamcrest.Matchers.allOf

fun rate(func: RateRobot.() -> Unit) = RateRobot().apply { func() }

class RateRobot() : BaseRobot() {

    fun checkInitialState() {
        onView(withId(R.id.ratingView)).check(matches(ViewHelper.hasRating(0f)))
        onView(withId(R.id.ratingView)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_view_border)).check(matches(isDisplayed()))
        onView(withId(R.id.profilePicture)).check(matches(isDisplayed()))
        onView(withId(R.id.nickName)).check(matches(allOf(
                isDisplayed(),
                withText("Average Joe")
        )))
        onView(withId(R.id.btnSendReview)).check(matches(isDisplayed()))
    }

    fun checkRatingSelection() {
        onView(withId(R.id.ratingView)).perform(click())
        onView(withId(R.id.ratingView)).check(matches(ViewHelper.hasRating(3f)))
    }

    fun checkSendReview() {
        onView(withId(R.id.ratingView)).perform(click())
        onView(withId(R.id.btnSendReview)).perform(click())

        onView(withText("Rating successfully sent."))
                .inRoot(ToastMatcher())
                .check(matches(isDisplayed()))

        onView(ViewMatchers.withId(R.id.personRecyclerView))
                .check(matches(isDisplayed()))
                .check(matches(RecyclerHelper.atPosition(0, allOf(
                        ViewMatchers.hasDescendant(withText("Average Joe")),
                        ViewMatchers.hasDescendant(withText("3.85"))
                ))))
    }


}