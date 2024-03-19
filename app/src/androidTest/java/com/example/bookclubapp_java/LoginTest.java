package com.example.bookclubapp_java;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertTrue;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginTest {
    DBHelper dbHelper;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testingLoginButton(){
        //Inputs test credentials.
        onView(withId(R.id.userName)).perform(ViewActions.typeText("JohnDoeTest"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(ViewActions.typeText("TestPassword"));
        //Checks if the EditTexts are visible on the screen.
        onView(withId(R.id.userName)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));

        //Closes the keyboard.
        Espresso.closeSoftKeyboard();

        //Clicks the register button.
        onView(withId(R.id.logButton)).perform(ViewActions.click());

        boolean userExists = dbHelper.checkUser("JohnDoeTest", "TestPassword");
        assertTrue(userExists);
    }
    @Test
    public void testLoginEmptyFields(){
        //Tests the register activity without inputs
        onView(withId(R.id.logButton)).perform(ViewActions.click());
    }
}