package hu.elte.inf.zoltantudlik.testingStars.utils

import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import junit.framework.TestCase
import org.junit.Test

class ListExtensionTest : ListExtension {

    @Test
    fun testCalculatingAverageThreeValues() {
        val ratings = listOf(Rating("id1", 1), Rating("id2", 2), Rating("id3", 3))

        TestCase.assertEquals(2.0f, ratings.avg())
    }

    @Test
    fun testCalculatingAverageTwoValues() {
        val ratings = listOf(Rating("id1", 1), Rating("id2", 2))

        TestCase.assertEquals(1.5f, ratings.avg())
    }

    @Test
    fun testCalculatingAverageEmptyList() {
        val ratings = emptyList<Rating>()

        TestCase.assertEquals(Float.NaN, ratings.avg())
    }

}