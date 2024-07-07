package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Before;
import org.junit.Test;

public class MainActivityTest {

    private ActivityScenario<MainActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(MainActivity.class);
    }
    @Test
    public void testUIComponentsCurrentJob() {
        Espresso.onView(withId(R.id.btn_compare_offers))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.btn_enter_job_offers))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.btn_edit_current_job))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.btn_adjust_settings))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
}
