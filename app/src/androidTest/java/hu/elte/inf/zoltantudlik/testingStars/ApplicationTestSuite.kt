package hu.elte.inf.zoltantudlik.testingStars

import hu.elte.inf.zoltantudlik.testingStars.people.PeopleTestSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        PeopleTestSuite::class
)
class ApplicationTestSuite