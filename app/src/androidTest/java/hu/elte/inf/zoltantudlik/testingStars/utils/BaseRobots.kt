package hu.elte.inf.zoltantudlik.testingStars.utils

import android.os.IBinder
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Root
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.WindowManager
import com.bluelinelabs.conductor.Controller
import hu.elte.inf.zoltantudlik.testingStars.MainActivity
import org.awaitility.Awaitility
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Ignore
import java.util.concurrent.TimeUnit


abstract class BaseRobot {

    fun hideSoftInput() {
        closeSoftKeyboard()
    }

    fun simulateActivityRestore(simulateOn: MainActivity) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            simulateOn.recreate()
        }
    }

    class ResultRobot {

        fun checkCurrentController(activity: MainActivity, controllerClass: Class<out Controller>) {
            Awaitility.await().atMost(2000, TimeUnit.MILLISECONDS).until {
                activity.router.hasRootController()
                        && controllerClass.isInstance(activity.router.backstack.last().controller())
            }
        }

        @Ignore("Waiting for dialog implementation")
        fun checkIfDialogShown() {
            // TODO Should implement dialog assertion
        }

        fun checkIfToastShownWithText(toastText: String) {
            onView(withText(toastText))
                    .inRoot(ToastMatcher())
                    .check(matches(isDisplayed()))
        }
    }

    class ToastMatcher : TypeSafeMatcher<Root>() {

        override fun describeTo(description: Description) {
            description.appendText("is toast")
        }

        override fun matchesSafely(root: Root): Boolean {
            val type = root.windowLayoutParams.get().type
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                val windowToken: IBinder = root.decorView.windowToken
                val appToken: IBinder = root.decorView.applicationWindowToken
                if (windowToken == appToken) {
                    return true
                }
            }
            return false
        }
    }
}