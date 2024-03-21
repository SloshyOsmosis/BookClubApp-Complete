package com.example.bookclubapp_java;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddReadBookTest {
    private DBHelper dbHelper;

    //Initialise Database before testing
    @Before
    public void setUp(){
        Context context = ApplicationProvider.getApplicationContext();
        dbHelper = new DBHelper(context);
    }

    @Rule
    public ActivityScenarioRule<AddReadBook> scenarioRule = new ActivityScenarioRule<>(AddReadBook.class);

    @Test
    public void testAddBookUserInterfaceDisplayed(){
        //Tests if the EditTexts are displayed
        Espresso.onView(ViewMatchers.withId(R.id.input_Title)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.input_Author)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.input_ISBN)).check(matches(isDisplayed()));

        //Tests if the Spinners are displayed
        Espresso.onView(ViewMatchers.withId(R.id.genreSpinner)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.statusSpinner)).check(matches(isDisplayed()));

        //Tests if the button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.addReadBook)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddBook(){
        //Inputs test data into the input fields
        Espresso.onView(withId(R.id.input_Title)).perform(ViewActions.typeText("TestTitle"));
        Espresso.onView(withId(R.id.input_Author)).perform(ViewActions.typeText("TestAuthor"));
        Espresso.onView(withId(R.id.input_ISBN)).perform(ViewActions.typeText("10"));

        //Close keyboard
        Espresso.closeSoftKeyboard();

        //Selects the first item in the spinners
        Espresso.onView(withId(R.id.genreSpinner)).perform(ViewActions.click());
        Espresso.onData(anything()).atPosition(0).perform(ViewActions.click());
        Espresso.onView(withId(R.id.statusSpinner)).perform(ViewActions.click());
        Espresso.onData(anything()).atPosition(0).perform(ViewActions.click());

        //Clicks the add button
        Espresso.onView(withId(R.id.addReadBook)).perform(ViewActions.click());

        //Checks if the book is in the database. Returns True if the test is successful.
        boolean bookExists = dbHelper.checkBookInLibrary("TestTitle", "TestAuthor", "Fiction", "10", "Read");
        assertTrue(bookExists);
    }
}