package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click city in list
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify ShowActivity is displiayed
        onView(withId(R.id.text_city_name))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistent() {
        String city = "Vancouver";

        // Add
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(city));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click city
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify correct name shown
        onView(withId(R.id.text_city_name))
                .check(matches(withText(city)));
    }

    @Test
    public void testBackButton() {
        String city = "Calgary";

        // Add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(city));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click city> open ShowActivity
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Press Back button
        onView(withId(R.id.button_back)).perform(click());

        // Verify returned to MainActivity
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
