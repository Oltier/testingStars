package hu.elte.inf.zoltantudlik.testingStars.utils

import android.support.annotation.NonNull
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher

object RecyclerHelper {

    @JvmStatic
    fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position) ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    @JvmStatic
    fun hasItems(): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has items")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val adapter = view.adapter ?: // has no item on such position
                        return false
                return adapter.itemCount > 0
            }
        }
    }

    @JvmStatic
    fun emptyList(): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("list is empty")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val adapter = view.adapter ?: // has no item on such position
                        return true
                return adapter.itemCount == 0
            }
        }
    }
}