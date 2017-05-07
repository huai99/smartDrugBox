package com.siehuai.smartdrugbox.Pharmacy.view;

import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.siehuai.smartdrugbox.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class P_AddMedicineActivityTest {

    @Rule
    public ActivityTestRule<P_AddMedicineActivity> mActivityRule = new ActivityTestRule<>(P_AddMedicineActivity.class);

    @Test
    public void changeTextTest() {
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Testing"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btn_confirm)).perform(ViewActions.click());
    }
}
