package com.testidea1.weatherapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.testidea1.weatherapp.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void EnsureComponentsShow(){
        onView(withId(R.id.city_field)).check(matches(isDisplayed()));
        onView(withId(R.id.humidity_title)).check(matches(isDisplayed()));
        onView(withId(R.id.extra_data)).check(matches(isDisplayed()));
    }

    @Test
    public void EnsureUpdateButtonFuncitionality(){
        onView(withId(R.id.btnUpdate)).check(matches(isClickable()));
        onView(withId(R.id.btnUpdate)).perform(click()
        );
        onView(withId(R.id.current_temperature_field)).check(matches(isDisplayed()));
    }

}
