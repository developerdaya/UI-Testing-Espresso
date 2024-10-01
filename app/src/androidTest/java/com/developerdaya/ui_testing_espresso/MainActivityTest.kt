package com.developerdaya.ui_testing_espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Test to check if all input fields and button are displayed
    @Test
    fun testAllFieldsDisplayed() {
        onView(withId(R.id.etFirstName)).check(matches(isDisplayed()))
        onView(withId(R.id.etLastName)).check(matches(isDisplayed()))
        onView(withId(R.id.etAddress)).check(matches(isDisplayed()))
        onView(withId(R.id.etGender)).check(matches(isDisplayed()))
        onView(withId(R.id.etEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.etMobile)).check(matches(isDisplayed()))
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.etConfirmPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.cbTerms)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSubmit)).check(matches(isDisplayed()))
    }

    // Test to enter valid data and click submit
    @Test
    fun testValidFormSubmission() {
        onView(withId(R.id.etFirstName)).perform(typeText("Developer"))
        onView(withId(R.id.etLastName)).perform(typeText("Daya"))
        onView(withId(R.id.etAddress)).perform(typeText("123 Main Street"))
        onView(withId(R.id.etGender)).perform(typeText("Male"))
        onView(withId(R.id.etEmail)).perform(typeText("john@example.com"))
        onView(withId(R.id.etMobile)).perform(typeText("1234567890"))
        onView(withId(R.id.etPassword)).perform(typeText("Password123"))
        onView(withId(R.id.etConfirmPassword)).perform(typeText("Password123"), closeSoftKeyboard())

        onView(withId(R.id.cbTerms)).perform(click())

        // Simulate button click
        onView(withId(R.id.btnSubmit)).perform(click())

        // Validate that no errors are shown, or show a success message
        // This will vary depending on your implementation
        // e.g., onView(withText("Registration successful")).check(matches(isDisplayed()))
    }

    // Test for empty fields
    @Test
    fun testEmptyFieldsValidation() {
        onView(withId(R.id.btnSubmit)).perform(click())
        // Check for error messages on empty fields
        onView(withId(R.id.etFirstName)).check(matches(hasErrorText("First Name is required")))
        onView(withId(R.id.etLastName)).check(matches(hasErrorText("Last Name is required")))
        // Add similar checks for other fields if you show errors
    }

    // Test mismatched passwords
    @Test
    fun testInvalidPasswordFormat() {
        onView(withId(R.id.etPassword)).perform(typeText("Pass@1122"))
        onView(withId(R.id.etPassword)).perform(typeText("Pass@1122"))
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.etPassword))
            .check(matches(hasErrorText("Password must be at least 8 characters and include one uppercase letter, one lowercase letter, one number, and one special character.")))
    }


    // Test missing checkbox acceptance
    @Test
    fun testTermsNotAccepted() {
        onView(withId(R.id.etFirstName)).perform(typeText("John"))
        onView(withId(R.id.etLastName)).perform(typeText("Doe"))
        // Enter valid input for other fields...

        // Do not check the terms checkbox
        onView(withId(R.id.btnSubmit)).perform(click())

        // Check if the error for checkbox is shown
        // Example: onView(withText("You must accept the terms and conditions")).check(matches(isDisplayed()))
    }

    // Test invalid email format
    @Test
    fun testInvalidEmailFormat() {
        onView(withId(R.id.etEmail)).perform(typeText("invalid-email"), closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        // Example: Check if invalid email error message is displayed
        // onView(withId(R.id.etEmail)).check(matches(hasErrorText("Invalid email format")))
    }

    // Test mobile number validation
    @Test
    fun testInvalidMobileNumber() {
        onView(withId(R.id.etMobile)).perform(typeText("12345"), closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        // Example: Check if error is displayed for invalid mobile number
        // onView(withId(R.id.etMobile)).check(matches(hasErrorText("Invalid mobile number")))
    }
}
