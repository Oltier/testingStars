package hu.elte.inf.zoltantudlik.testingStars.people

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.zoltantudlik.testing_stars.R
import hu.elte.inf.zoltantudlik.testingStars.MainActivity
import hu.elte.inf.zoltantudlik.testingStars.utils.BaseRobot
import hu.elte.inf.zoltantudlik.testingStars.utils.RecyclerHelper
import org.awaitility.Awaitility
import java.util.concurrent.TimeUnit


fun peopleList(func: PeopleRobot.() -> Unit) = PeopleRobot().apply { func() }

fun peopleList(activity: MainActivity, func: PeopleRobot.() -> Unit) = PeopleRobot().apply {
    hideSoftInput()
}

class PeopleRobot() : BaseRobot() {

    lateinit var peopleController: PeopleController

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
        Awaitility.await().atMost(5000, TimeUnit.MILLISECONDS).until {
            var itemsLoaded = true

            Espresso.onView(ViewMatchers.withId(R.id.personRecyclerView))
                    .withFailureHandler { _, _ -> itemsLoaded = false }
                    .check(matches(isDisplayed()))
                    .check(matches(RecyclerHelper.hasItems()))

            return@until itemsLoaded
        }
    }

    fun checkNoPeopleDisplayed() {
        Awaitility.await().pollDelay(1000, TimeUnit.MILLISECONDS).atMost(2000, TimeUnit.MILLISECONDS).until {
            var listIsEmpty = true

            Espresso.onView(ViewMatchers.withId(R.id.personRecyclerView))
                    .withFailureHandler { _, _ -> listIsEmpty = false }
                    .check(matches(isDisplayed()))
                    .check(matches(RecyclerHelper.emptyList()))

            return@until listIsEmpty
        }
    }

    fun checkNoInternetToastIsShown() {
        Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until {
            var toastShown = true

            Espresso.onView(ViewMatchers.withText("Unable to retrieve list at the moment :("))
                    .inRoot(ToastMatcher())
                    .withFailureHandler { _, _ -> toastShown = false }
                    .check(matches(isDisplayed()))

            return@until toastShown
        }
    }

}