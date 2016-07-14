package com.rosorio.android.sample.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by robertoosorio on 14-07-16.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityEspressoTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureSignInEmptyErrorValidation() {
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withText(R.string.error_field_required)).
                inRoot(withDecorView(
                        not(is(mActivityRule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));
//        onView(withText(R.string.error_field_required)).
//                inRoot(withDecorView(
//                        not(is(mActivityRule.getActivity().
//                                getWindow().getDecorView())))).
//                check(matches(isDisplayed()));
    }

    @Test
    public void ensureSignInEmailValidation() {
        onView(withId(R.id.email)).perform(typeText("robertocarlos"));
        onView(withId(R.id.password)).perform(typeText("11211121"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withText(R.string.error_invalid_email)).
                inRoot(withDecorView(
                        not(is(mActivityRule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void ensureSignInPasswordValidation() {
        onView(withId(R.id.email)).perform(typeText("roberto@hackinglabs.cl"));
        onView(withId(R.id.password)).perform(typeText("123"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withText(R.string.error_invalid_password)).
                inRoot(withDecorView(
                        not(is(mActivityRule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void ensureSignIn(){
        onView(withId(R.id.email)).perform(typeText("roberto@hackinglabs.cl"));
        onView(withId(R.id.password)).perform(typeText("11211121"));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(withText(startsWith("Welcome:"))).
                inRoot(withDecorView(
                        not(is(mActivityRule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }
}
