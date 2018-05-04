package hu.elte.inf.zoltantudlik.testingStars.repositories

import hu.elte.inf.zoltantudlik.testingStars.TestingStarsApplication
import hu.elte.inf.zoltantudlik.testingStars.rest.ContentService
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.AddRating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject


class ContentRepository {

    @Inject
    lateinit var contentService: ContentService

    init {
        TestingStarsApplication.modelComponent.inject(this)
    }

    fun getUsers(): Observable<List<User>> {
        return contentService.getUsers()
    }

    fun addRating(rating: Rating) : Completable {
        return contentService.addRating(rating.id, AddRating(rating.value))
    }

}