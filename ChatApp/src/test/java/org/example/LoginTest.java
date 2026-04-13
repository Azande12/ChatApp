package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUserSuccessMessage() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("Registration successful. Welcome John Doe.", result);
    }

    @Test
    public void testRegisterUserUsernameFailMessage() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("Username is not correctly formatted; please ensure that your username " +
                "contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserPasswordFailMessage() {
        String result = login.registerUser("kyl_1", "password", "+27838968976", "John", "Doe");
        assertEquals("Password is not correctly formatted; please ensure that the password " +
                "contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterUserCellPhoneFailMessage() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "John", "Doe");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailedWrongPassword() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testLoginFailedWrongUsername() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertFalse(login.loginUser("wrong_username", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("Welcome John, Doe it is great to see you again.", login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailed() {
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(false));
    }

    @Test
    public void testUsernameCorrectlyFormattedAssertTrue() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormattedAssertFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexityAssertTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityAssertFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormattedAssertTrue() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedAssertFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}