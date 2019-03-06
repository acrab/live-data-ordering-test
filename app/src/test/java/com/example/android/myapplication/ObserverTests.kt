package com.example.android.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(TestWithConstructorData::class, TestWithoutConstructorData::class)
class WithThenWithout

@RunWith(Suite::class)
@Suite.SuiteClasses(TestWithoutConstructorData::class, TestWithConstructorData::class)
class WithoutThenWith

@RunWith(AndroidJUnit4::class)
class TestWithConstructorData : BaseTest() {
    @Before
    fun setup() {
        data = MutableLiveData(emptyList())
        standardSetup<ListFragmentWithObserver>()
        data.value = listOf(ListItem("FIRST"))
    }
}

@RunWith(AndroidJUnit4::class)
class TestWithoutConstructorData : BaseTest() {
    @Before
    fun setup() {
        data = MutableLiveData()
        standardSetup<ListFragmentWithObserver>()
        data.value = listOf(ListItem("FIRST"))
    }
}