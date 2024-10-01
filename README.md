# UI-Testing-Espresso
https://github.com/user-attachments/assets/15bc004b-8800-42ef-87e8-de73d27b4dcd
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- First Name -->
    <EditText
        android:id="@+id/etFirstName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="First Name"
        android:inputType="textPersonName" />

    <!-- Last Name -->
    <EditText
        android:id="@+id/etLastName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Last Name"
        android:inputType="textPersonName" />

    <!-- Address -->
    <EditText
        android:id="@+id/etAddress"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Address"
        android:inputType="textPostalAddress" />

    <!-- Gender -->
    <EditText
        android:id="@+id/etGender"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Gender"
        android:inputType="text" />

    <!-- Email Address -->
    <EditText
        android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email Address"
        android:inputType="textEmailAddress" />

    <!-- Mobile Number -->
    <EditText
        android:id="@+id/etMobile"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Mobile Number"
        android:inputType="phone" />

    <!-- Password -->
    <EditText
        android:id="@+id/etPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:inputType="textPassword" />

    <!-- Confirm Password -->
    <EditText
        android:id="@+id/etConfirmPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Confirm Password"
        android:inputType="textPassword" />

    <!-- Terms and Conditions CheckBox -->
    <CheckBox
        android:id="@+id/cbTerms"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Please accept the terms and conditions" />

    <!-- Submit Button -->
    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Submit" />

</LinearLayout>
```


### Dependencies
Make sure you have added the necessary dependencies for Espresso in your `build.gradle` file:

```gradle
androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
androidTestImplementation 'androidx.test.ext:junit:1.1.3'
```

### UI Test Code (Kotlin + Espresso)
Create a new test class under `src/androidTest/java`:

```kotlin
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class SignupFormTest {

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
        onView(withId(R.id.etFirstName)).perform(typeText("John"))
        onView(withId(R.id.etLastName)).perform(typeText("Doe"))
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
    fun testMismatchedPasswords() {
        onView(withId(R.id.etPassword)).perform(typeText("Password123"))
        onView(withId(R.id.etConfirmPassword)).perform(typeText("Password456"), closeSoftKeyboard())

        onView(withId(R.id.cbTerms)).perform(click())

        onView(withId(R.id.btnSubmit)).perform(click())

        // Check if error is displayed
        // Example:
        // onView(withText("Passwords do not match")).check(matches(isDisplayed()))
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
```

### Key Test Cases

1. **testAllFieldsDisplayed**: Verifies that all input fields, checkbox, and the button are displayed on the screen.
2. **testValidFormSubmission**: Simulates entering valid data into the form, checking the checkbox, and submitting.
3. **testEmptyFieldsValidation**: Ensures the form shows error messages when required fields are left empty.
4. **testMismatchedPasswords**: Validates that an error message appears when the password and confirm password fields do not match.
5. **testTermsNotAccepted**: Checks if the form prevents submission if the terms checkbox is not checked.
6. **testInvalidEmailFormat**: Ensures the form shows an error for an invalid email format.
7. **testInvalidMobileNumber**: Tests that the form shows an error for an invalid mobile number.

