package net.moudra.myapplicationwithtest;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import net.moudra.myapplicationwithtest.R;

// Espresso library test
public class EspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public EspressoTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        getActivity();
    }

    public void testFormSending() {
        //SystemClock.sleep(2000);
        // Fills the text into form
        Espresso.onView(ViewMatchers.withId(R.id.name_edittext))
                .perform(ViewActions.typeTextIntoFocusedView("Jana Moudra"))
                .check(ViewAssertions.matches(ViewMatchers.withText("Jana Moudra")));

        //SystemClock.sleep(2000);
        // Opens new Activity
        Espresso.onView(ViewMatchers.withId(R.id.name_btn))
                .perform(ViewActions.click());

        //SystemClock.sleep(2000);
        // Checks the text
        Espresso.onView(ViewMatchers.withId(R.id.name_textview))
                .check(ViewAssertions.matches(ViewMatchers.withText("Jana Moudra")));
    }
}
