package hu.elte.inf.zoltantudlik.testingStars.people

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.zoltantudlik.testing_stars.R
import hu.elte.inf.zoltantudlik.testingStars.MainActivity
import hu.elte.inf.zoltantudlik.testingStars.rate.RateRobot
import hu.elte.inf.zoltantudlik.testingStars.utils.BaseRobot
import hu.elte.inf.zoltantudlik.testingStars.utils.RecyclerHelper
import hu.elte.inf.zoltantudlik.testingStars.utils.ViewHelper
import org.awaitility.Awaitility
import org.hamcrest.Matchers.allOf
import java.util.concurrent.TimeUnit


fun peopleList(func: PeopleRobot.() -> Unit) = PeopleRobot().apply { func() }

fun peopleList(activity: MainActivity, func: PeopleRobot.() -> Unit) = PeopleRobot().apply {
    hideSoftInput()
}

class PeopleRobot() : BaseRobot() {

    private lateinit var peopleController: PeopleController

    constructor(peopleController: PeopleController) : this() {
        this.peopleController = peopleController
    }

    fun waitForPeopleListToLoad(activity: MainActivity) {
        Awaitility.await().atMost(2000, TimeUnit.MILLISECONDS).until {
            activity.router.hasRootController()
                    && activity.router.backstack.last().controller() is PeopleController
        }

        peopleController = activity.router.backstack.last().controller() as PeopleController
    }

    fun checkPeopleDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.personRecyclerView))
                .check(matches(isDisplayed()))
                .check(matches(RecyclerHelper.hasItems()))

    }

    fun checkIfUserValueMatchesAtPosition(position: Int, name: String, rating: String) {
        Espresso.onView(ViewMatchers.withId(R.id.personRecyclerView))
                .check(matches(isDisplayed()))
                .check(matches(RecyclerHelper.atPosition(position, allOf(
                        ViewMatchers.hasDescendant(withText(name)),
                        ViewMatchers.hasDescendant(withText(rating))
                ))))
    }

    fun checkNoPeopleDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.personRecyclerView))
                .check(matches(isDisplayed()))
                .check(matches(RecyclerHelper.emptyList()))
    }

    fun checkNoInternetToastIsShown() {
        Espresso.onView(ViewMatchers.withText("Unable to retrieve list at the moment :("))
                .inRoot(ToastMatcher())
                .check(matches(isDisplayed()))
    }

    fun openUserRatingAndBack() {
        Espresso
                .onView(allOf(withId(R.id.personRecyclerView), RecyclerHelper.atPosition(0, hasDescendant(withId(R.id.person_image)))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<PeopleAdapter.ViewHolder>(0, ViewHelper.clickChildViewWithId(R.id.cardView)))
        Espresso.onView(withId(R.id.ratingView)).check(matches(isDisplayed()))

        Espresso.pressBack()
        checkPeopleDisplayed()
    }

    infix fun openUserRating(func: RateRobot.() -> Unit): RateRobot {
        Espresso
                .onView(allOf(withId(R.id.personRecyclerView), RecyclerHelper.atPosition(0, hasDescendant(withId(R.id.person_image)))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<PeopleAdapter.ViewHolder>(0, ViewHelper.clickChildViewWithId(R.id.cardView)))

        return RateRobot().apply(func)
    }

}