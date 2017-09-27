package br.com.movieapp.presentation.home

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.movieapp.domain.model.Movie
import br.com.movieapp.domain.model.MovieResponse
import br.com.movieapp.presentation.application.MovieApplication
import br.com.movieapp.presentation.application.di.AppModule
import br.com.movieapp.presentation.application.di.NetworkTestModule
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.onView
import br.com.movieapp.R
import br.com.movieapp.presentation.application.di.DaggerAppTestComponent
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by pedrohenrique on 27/09/17.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{
    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    lateinit var mServer: MockWebServer

    @Before
    fun setUp(){
        mServer = MockWebServer()
        mServer.start()

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val application = instrumentation.targetContext.applicationContext as MovieApplication

        val component = DaggerAppTestComponent.builder()
                .appModule(AppModule(application))
                .networkTestModule(NetworkTestModule(mServer.url("/").toString()))
                .build()

        application.setComponent(component)
    }

    @Test
    fun testLoadRecyclerView(){
        mActivityRule.launchActivity(null)

        val responseMovie = Movie(1, "The Avengers", "Test", "url_test")
        var response = MovieResponse(1, 20, arrayListOf(responseMovie))

        mServer.enqueue(MockResponse().setResponseCode(200).setBody(Gson().toJson(response)))

        onView(withId(R.id.search_button)).perform(click())
        onView(withId(R.id.search_src_text)).perform(replaceText("The Avengers"), closeSoftKeyboard())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}