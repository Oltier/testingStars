package hu.elte.inf.zoltantudlik.testingStars.utils

import android.support.test.InstrumentationRegistry
import hu.elte.inf.zoltantudlik.testingStars.TestApplication
import hu.elte.inf.zoltantudlik.testingStars.rest.MockRestInterface

fun initialize(func: Initializer.() -> Unit) = Initializer().apply { func() }

class Initializer {

    fun initDagger() {
        val applicationContext = InstrumentationRegistry.getTargetContext().applicationContext
        (applicationContext as? TestApplication)?.initDagger()
    }

    fun enableInternetConnection(isInternetEnabledOnMock: Boolean) {
        MockRestInterface.IS_CONNECTED_TO_INTERNET = isInternetEnabledOnMock
    }

}