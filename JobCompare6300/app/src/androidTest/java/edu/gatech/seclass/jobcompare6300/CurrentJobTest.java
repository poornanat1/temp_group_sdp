package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Before;
import org.junit.Test;


public class CurrentJobTest {

    private ActivityScenario<CurrentJob> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(CurrentJob.class);
    }
    @Test
    public void testUIComponentsCurrentJob() {
        Espresso.onView(withId(R.id.titleEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.companyEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.stateSpinner))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.cityEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.costOfLivingEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.salaryEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.bonusEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.trainingEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.leaveTimeEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.teleworkDaysSpinner))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.mainMenu))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.saveButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
}
