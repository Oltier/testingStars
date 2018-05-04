package hu.elte.inf.zoltantudlik.testingStars.rest

import hu.elte.inf.zoltantudlik.testingStars.rest.entities.AddRating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ContentService {

    @GET("api/users")
    fun getUsers(): Observable<List<User>>

    @POST("api/users/{id}/ratings")
    fun addRating(
            @Path("id") id: String,
            @Body rating: AddRating
    ): Completable

}