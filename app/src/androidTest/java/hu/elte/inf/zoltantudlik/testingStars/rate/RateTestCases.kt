package hu.elte.inf.zoltantudlik.testingStars.rate

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import hu.elte.inf.zoltantudlik.testingStars.MainActivity
import hu.elte.inf.zoltantudlik.testingStars.people.peopleList
import hu.elte.inf.zoltantudlik.testingStars.utils.initialize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RateTestCases() {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    lateinit var activity: MainActivity

    @After
    fun tearDown() {
        activity.finish()
    }

    @Test
    fun testInitialRatingState() {
        initialize {
            enableInternetConnection(true)
            initDagger()
        }
        activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))

        peopleList {
            waitForPeopleListToLoad(activity)
        } openUserRating {
            checkInitialState()
        }
    }

    @Test
    fun testRateSelection() {
        initialize {
            enableInternetConnection(true)
            initDagger()
        }
        activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))

        peopleList {
            waitForPeopleListToLoad(activity)
        } openUserRating {
            checkRatingSelection()
        }
    }

    @Test
    fun testSendReview() {
        initialize {
            enableInternetConnection(true)
            initDagger()
        }
        activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))

        peopleList {
            waitForPeopleListToLoad(activity)
        } openUserRating {
            checkSendReview()
        }
    }

}