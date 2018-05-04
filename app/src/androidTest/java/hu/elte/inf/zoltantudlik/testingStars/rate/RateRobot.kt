package hu.elte.inf.zoltantudlik.testingStars.rate

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.zoltantudlik.testing_stars.R
import hu.elte.inf.zoltantudlik.testingStars.people.PeopleController
import hu.elte.inf.zoltantudlik.testingStars.utils.BaseRobot
import kotlinx.android.synthetic.main.view_profile.view.*

class RateRobot(val peopleController: PeopleController) : BaseRobot() {

    fun checkInitialState() {
        onView(withId(R.id.ratingView)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_view_border)).check(matches(isDisplayed()))
        onView(withId(R.id.profilePicture)).check(matches(isDisplayed()))
        onView(withId(R.id.nickName)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSendReview)).check(matches(isDisplayed()))
    }

}