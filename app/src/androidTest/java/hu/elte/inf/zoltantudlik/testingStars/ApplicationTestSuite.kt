package hu.elte.inf.zoltantudlik.testingStars

import hu.elte.inf.zoltantudlik.testingStars.people.PeopleTestSuite
import hu.elte.inf.zoltantudlik.testingStars.rate.RateTestSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        PeopleTestSuite::class,
        RateTestSuite::class
)
class ApplicationTestSuite