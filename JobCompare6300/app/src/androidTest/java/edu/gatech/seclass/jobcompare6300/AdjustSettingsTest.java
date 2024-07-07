package edu.gatech.seclass.jobcompare6300;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Before;
import org.junit.Test;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AdjustSettingsTest {
    private ActivityScenario<AdjustSettings> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(AdjustSettings.class);
    }
    @Test
    public void testUIComponentWhenUserOpenTheSettingWindow() {
        Espresso.onView(withId(R.id.yearly_bonus_seekbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.yearly_salary_seekbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.training_dev_fund_seekbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.leave_time_seekbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(withId(R.id.telework_days_seekbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void testTheSeekBars() {
        // if first time open app and no
        Espresso.onView(withId(R.id.yearly_bonus_seekbar)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.yearly_bonus_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));

        Espresso.onView(withId(R.id.yearly_salary_seekbar)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.yearly_salary_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));

        Espresso.onView(withId(R.id.training_dev_fund_seekbar)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.training_dev_fund_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));

        Espresso.onView(withId(R.id.leave_time_seekbar)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.leave_time_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));

        Espresso.onView(withId(R.id.yearly_bonus_seekbar)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.yearly_bonus_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));
    }
    @Test
    public void testCancelButton() {
        Espresso.onView(withId(R.id.cancel_button)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.yearly_bonus_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));
        Espresso.onView(withId(R.id.training_dev_fund_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));
        Espresso.onView(withId(R.id.leave_time_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));
        Espresso.onView(withId(R.id.yearly_salary_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));
        Espresso.onView(withId(R.id.telework_days_weight_value))
                .check(ViewAssertions.matches(ViewMatchers.withText("5")));
    }
}
