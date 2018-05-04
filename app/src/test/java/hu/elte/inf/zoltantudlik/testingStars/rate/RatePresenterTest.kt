package hu.elte.inf.zoltantudlik.testingStars.rate

import com.nhaarman.mockito_kotlin.*
import com.wanari.mlp.egisru.io.reactivex.android.plugins.RxAndroidTestPlugins
import hu.elte.inf.zoltantudlik.testingStars.TestingStarsApplication
import hu.elte.inf.zoltantudlik.testingStars.dagger.components.PresenterComponent
import hu.elte.inf.zoltantudlik.testingStars.repositories.ContentRepository
import hu.elte.inf.zoltantudlik.testingStars.rest.ContentService
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.AddRating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito


class RatePresenterTest {

    lateinit var rateContractMock: RateContract
    lateinit var contentServiceMock: ContentService
    lateinit var ratePresenter: RatePresenter
    lateinit var contentRepositoryMock: ContentRepository

    companion object {
        @BeforeClass
        @JvmStatic
        fun appInit() {
            TestingStarsApplication.presenterComponent = Mockito.mock(PresenterComponent::class.java)
        }
    }

    @Before
    fun initTest() {
        RxAndroidTestPlugins.initAndroidTestPlugins()
        rateContractMock = Mockito.mock(RateContract::class.java)
        ratePresenter = RatePresenter(RateViewState())
    }

    @After
    fun tearDownTest() {
        ratePresenter.destroy()
        RxAndroidTestPlugins.resetAndroidTestPlugins()
    }

    @Test
    fun testRatingSuccess() {

        contentRepositoryMock = mock<ContentRepository> {}
        contentServiceMock = Mockito.mock(ContentService::class.java)
        contentRepositoryMock.contentService = contentServiceMock
        ratePresenter.contentRepository = contentRepositoryMock

        Mockito.doReturn(Completable.complete()).`when`(contentServiceMock).addRating(any(), any())
        Mockito.doReturn(Observable.just(Rating("testId2", 3))).`when`(rateContractMock).rating()


        ratePresenter.attachView(rateContractMock)

        argumentCaptor<RateViewState>().apply {
            verify(rateContractMock, Mockito.timeout(1000).times(3)).render(capture())

            TestCase.assertEquals(allValues.size, 3)

            //Default State
            TestCase.assertEquals(false, firstValue.loading)
            TestCase.assertNull(firstValue.error)
            TestCase.assertEquals(false, firstValue.success)

            //Loading State
            TestCase.assertEquals(true, secondValue.loading)
            TestCase.assertNull(secondValue.error)
            TestCase.assertEquals(false, secondValue.success)

            //Default State
            TestCase.assertEquals(false, thirdValue.loading)
            TestCase.assertNull(thirdValue.error)
            TestCase.assertEquals(true, thirdValue.success)


        }
    }

}