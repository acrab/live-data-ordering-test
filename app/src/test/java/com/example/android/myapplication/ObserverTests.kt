package com.example.android.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Suite

//@RunWith(AndroidJUnit4::class)
//class ObserverFragment_Before : BaseTest() {
//    @Before
//    fun setup() {
//        data = MutableLiveData(emptyList())
//        data.value = listOf(ListItem("FIRST"))
//        standardSetup<ListFragmentWithObserver>()
//    }
//}
//
//@RunWith(AndroidJUnit4::class)
//class ObserverFragment_BeforeNoInit : BaseTest() {
//    @Before
//    fun setup() {
//        data = MutableLiveData()
//        data.value = listOf(ListItem("FIRST"))
//        standardSetup<ListFragmentWithObserver>()
//    }
//}

@RunWith(Suite::class)
@Suite.SuiteClasses(ObserverFragment_After::class, ObserverFragment_AfterNoInit::class)
class ObserverTests

@RunWith(Suite::class)
@Suite.SuiteClasses(ObserverFragment_AfterNoInit::class, ObserverFragment_After::class)
class ObserverTestsBackwards

@RunWith(AndroidJUnit4::class)
class ObserverFragment_After : BaseTest() {
    @Before
    fun setup() {
        data = MutableLiveData(emptyList())
        standardSetup<ListFragmentWithObserver>()
        data.value = listOf(ListItem("FIRST"))
    }
}

@RunWith(AndroidJUnit4::class)
class ObserverFragment_AfterNoInit : BaseTest() {
    @Before
    fun setup() {
        data = MutableLiveData()
        standardSetup<ListFragmentWithObserver>()
        data.value = listOf(ListItem("FIRST"))
    }
}