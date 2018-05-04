package hu.elte.inf.zoltantudlik.testingStars.rate

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import hu.elte.inf.zoltantudlik.testingStars.MainActivity
import hu.elte.inf.zoltantudlik.testingStars.people.PeopleController
import hu.elte.inf.zoltantudlik.testingStars.people.peopleList
import hu.elte.inf.zoltantudlik.testingStars.utils.BaseRobot
import hu.elte.inf.zoltantudlik.testingStars.utils.initialize
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RateTestCases() {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    lateinit var activity: MainActivity

    @Before
    fun startUpActivity() {
        initialize {
            enableInternetConnection(true)
            initDagger()
        }
        activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))
    }

    @Test
    fun testInitialRatingState() {
        peopleList {
            waitForPeopleListToLoad(activity)
        } openUserRating {
            checkInitialState()
        }
    }

}