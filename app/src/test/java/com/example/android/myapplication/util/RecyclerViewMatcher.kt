package com.example.android.myapplication.util

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import java.lang.IllegalStateException

fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)

/**
 * Fork of the RecyclerViewMatcher from https://github.com/dannyroa/espresso-samples
 */
class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPositionOnView(position: Int, targetViewId: Int) = object : TypeSafeMatcher<View>() {
        var resources: Resources? = null
        var childView: View? = null

        override fun describeTo(description: Description) {
            var idDescription = Integer.toString(recyclerViewId)

            this.resources?.let {
                idDescription = try {
                    it.getResourceName(recyclerViewId)
                } catch (var4: Resources.NotFoundException) {
                    String.format("%s (resource name not found)", recyclerViewId)
                }
            }

            description.appendText("RecyclerView with id: $idDescription at position: $position")
        }

        public override fun matchesSafely(view: View): Boolean {

            this.resources = view.resources

            if (childView == null) {
                val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                if (recyclerView.id == recyclerViewId) {

                    childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView


                } else {
                    return false
                }
            }

            return if (targetViewId == -1) {
                view === childView
            } else {
                val childView1 = childView
                if (childView1 != null) {
                    val targetView = childView1.findViewById<View>(targetViewId)
                    view === targetView
                } else {
                    false
                }

            }
        }
    }
}