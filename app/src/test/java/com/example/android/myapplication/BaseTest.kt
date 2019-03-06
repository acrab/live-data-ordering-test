package com.example.android.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.myapplication.util.FragmentFactoryWithMockViewModelFactory
import com.example.android.myapplication.util.withRecyclerView
import com.example.android.myapplication.util.withRowContaining
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

abstract class BaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var data: MutableLiveData<List<ListItem>>
    protected lateinit var scenario: FragmentScenario<*>

    protected inline fun <reified T:Fragment> standardSetup() {

        //Mock viewModel, so we can provide data to the oneItem
        val viewModel: ListViewModel = mock {
            on { listData } doReturn data
        }

        //Mock viewModelFactory, used to inject viewModel
        val viewModelFactory: ViewModelProvider.Factory = mock {
            on { create<ListViewModel>(any()) } doReturn viewModel
        }

        //Fragment factory used to inject viewModel factory
        val fragmentFactory = FragmentFactoryWithMockViewModelFactory(viewModelFactory)

        scenario = launchFragmentInContainer<T>(factory = fragmentFactory)
    }

    @Test
    fun oneItem() {
                
        onView(withId(R.id.data_list))
            .perform(RecyclerViewActions.scrollToPosition<StringHolder>(0))

        onView(withRecyclerView(R.id.data_list).atPositionOnView(0, R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("FIRST")))
    }

    @Test
    fun oneItemDifferentLookup() {
        onView(withId(R.id.data_list))
            .check(withRowContaining(withText("FIRST")))
    }

    @After
    fun tearDown() {
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}