package hu.elte.inf.zoltantudlik.testingStars.people

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.wanari.mlp.egisru.io.reactivex.android.plugins.RxAndroidTestPlugins
import hu.elte.inf.zoltantudlik.testingStars.TestingStarsApplication
import hu.elte.inf.zoltantudlik.testingStars.dagger.components.PresenterComponent
import hu.elte.inf.zoltantudlik.testingStars.repositories.ContentRepository
import hu.elte.inf.zoltantudlik.testingStars.rest.ContentService
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Observable
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import java.net.SocketTimeoutException


class PeoplePresenterTest {

    lateinit var peopleContractMock: PeopleContract
    lateinit var contentServiceMock: ContentService
    lateinit var peoplePresenter: PeoplePresenter
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
        peopleContractMock = Mockito.mock(PeopleContract::class.java)
        peoplePresenter = PeoplePresenter(PeopleViewState())
        contentRepositoryMock = Mockito.mock(ContentRepository::class.java)
        contentServiceMock = Mockito.mock(ContentService::class.java)
        contentRepositoryMock.contentService = contentServiceMock
        peoplePresenter.contentRepository = contentRepositoryMock
    }

    @After
    fun tearDownTest() {
        peoplePresenter.destroy()
        RxAndroidTestPlugins.resetAndroidTestPlugins()
    }

    @Test
    fun testListingUsers() {

        val mockUserList = listOf(User("test", "id", listOf(Rating("ratingId", 1))))

        val mockedData = Observable.just(mockUserList)

        Mockito.doReturn(mockedData).`when`(contentServiceMock).getUsers()

        peoplePresenter.attachView(peopleContractMock)

        argumentCaptor<PeopleViewState>().apply {
            verify(contentServiceMock, times(1)).getUsers()
            verify(peopleContractMock, Mockito.timeout(1000).times(3)).render(capture())

            TestCase.assertEquals(allValues.size, 3)

            //Default State
            TestCase.assertEquals(false, firstValue.loading)
            TestCase.assertNull(firstValue.error)
            TestCase.assertNull(firstValue.people)

            //Loading State
            TestCase.assertEquals(true, secondValue.loading)
            TestCase.assertNull(secondValue.error)
            TestCase.assertNull(secondValue.people)

            //Default State
            TestCase.assertEquals(false, thirdValue.loading)
            TestCase.assertNull(thirdValue.error)
            TestCase.assertEquals(mockUserList, thirdValue.people)


        }
    }

    @Test
    fun testListingUsersFail() {

        val ex = SocketTimeoutException("Timeout")
        Mockito.doReturn(Observable.error<SocketTimeoutException>(ex)).`when`(contentServiceMock).getUsers()

        peoplePresenter.attachView(peopleContractMock)

        argumentCaptor<PeopleViewState>().apply {
            verify(contentServiceMock, times(1)).getUsers()
            verify(peopleContractMock, Mockito.timeout(1000).times(3)).render(capture())

            TestCase.assertEquals(allValues.size, 3)

            //Default State
            TestCase.assertEquals(false, firstValue.loading)
            TestCase.assertNull(firstValue.error)
            TestCase.assertNull(firstValue.people)

            //Loading State
            TestCase.assertEquals(true, secondValue.loading)
            TestCase.assertNull(secondValue.error)
            TestCase.assertNull(secondValue.people)

            //Default State
            TestCase.assertEquals(false, thirdValue.loading)
            TestCase.assertEquals(ex, thirdValue.error)
            TestCase.assertEquals(emptyList<User>(), thirdValue.people)


        }
    }

}