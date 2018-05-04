package hu.elte.inf.zoltantudlik.testingStars.people

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import hu.elte.inf.zoltantudlik.testingStars.MainActivity
import hu.elte.inf.zoltantudlik.testingStars.utils.initialize
import org.junit.After
import org.junit.Rule
import org.junit.Test


class PeopleTestCases {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var activity: MainActivity

    @After
    fun tearDown() {
        activity.finish()
    }

    @Test
    fun testNoInternetConnectionToast() {
        initialize {
            enableInternetConnection(false)
            initDagger()
            activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))
        }

        peopleList {
            checkNoPeopleDisplayed()
            checkNoInternetToastIsShown()
        }

    }

    @Test
    fun testPeopleDisplayed() {
        initialize {
            initDagger()
            enableInternetConnection(true)
            activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))
        }

        peopleList {
            checkPeopleDisplayed()
        }
    }

    @Test
    fun testUsersDisplayedWithProperValues() {
        initialize {
            initDagger()
            enableInternetConnection(true)
            activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))
        }

        peopleList {
            checkIfUserValueMatchesAtPosition(0, "Average Joe", "3.85")
            checkIfUserValueMatchesAtPosition(1, "John Doe", "3.20")
            checkIfUserValueMatchesAtPosition(2, "Alice Avalanche", "2.80")
        }
    }

    @Test
    fun testOpenUserRatingAndBack() {
        initialize {
            initDagger()
            enableInternetConnection(true)
            activity = activityRule.launchActivity(Intent(Intent.ACTION_MAIN))
        }

        peopleList {
            openUserRatingAndBack()
        }
    }

}