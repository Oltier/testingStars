package hu.elte.inf.zoltantudlik.testingStars.rest

import android.content.Context
import com.google.gson.Gson
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.AddRating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import java.net.SocketTimeoutException


class MockContentService(context: Context, gson: Gson): MockRestInterface(context, gson), ContentService {
    override fun getUsers(): Observable<List<User>> {
        return Observable.fromCallable {
            if(IS_CONNECTED_TO_INTERNET) {
                return@fromCallable readJsonFromAssets("users.json", Array<User>::class.java).toList()
            } else {
                throw SocketTimeoutException("No internet connection")
            }
        }
    }

    override fun addRating(id: String, rating: AddRating): Completable {
        if(IS_CONNECTED_TO_INTERNET) {
            return Completable.complete()
        } else {
            throw SocketTimeoutException("No internet connection")
        }
    }


}