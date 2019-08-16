package com.sqisland.espresso.toolbartitle

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.view.View

import org.hamcrest.Description
import org.hamcrest.Matcher

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun toolbarTitle(){
        val title = InstrumentationRegistry.getTargetContext().getString(R.string.title)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(title)))
        }
    //expectedTitle is function's parameter
    //CharSequence is the type of the param
    //this function return a view matcher -- Matcher<View>
    private fun withToolbarTitle(expectedTitle: CharSequence):Matcher<View>{
        //return a bounded matcher object, type is <View, Toolbar>, its param is Toolbar::class.java
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java){
            override fun describeTo(description: Description?) { //when the matching fails, provide information to know what is expected
                description?.appendText("with toolbar title:" + expectedTitle)
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
                 }

        }


    }

}