package testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;
import utility.ScreenShots;

@Feature("Test Login Page")
public class LoginPageTest extends BaseTest {

	HomePage homePage;
	LoginPage loginPage;
	public static String message_required = "This is a required field.";
	public static String message_successfull = "Welcome, thao btt!";
	public static String message_invalid_email = "Please enter a valid email address (Ex: johndoe@domain.com).";
	public static String message_invalid_pass = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";

	@BeforeClass
	public void preCondition() {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
	}

	@Test(dataProvider = "test_email", priority = 1)
	@Story("Test login with invalid email")
	@Description("Invalid email")
	@Step("Login with email: {0}, password: {1}")
	@Attachment
	public void testLoginWithInvalidEmail(String email, String pass) throws IOException {
		homePage.open().clickOnSignIn();
		loginPage.login(email, pass);
		if (email == "") {
			Assert.assertEquals(loginPage.checkEmail(), message_required);
			ScreenShots.captureScreenShots(driver, "Login", "BankEmail");

		} else {
			Assert.assertEquals(loginPage.checkEmail(), message_invalid_email);
			ScreenShots.captureScreenShots(driver, "Login", "InvalidEmail");
		}

	}

	@Test(dataProvider = "test_password", priority = 2)
	@Story("Test login with invalid password")
	@Description("Invalid Password")
	@Step("Login with email: {0}, password: {1}")
	public void testLoginWithInvalidPass(String email, String pass) {
		homePage.open().clickOnSignIn();
		loginPage.login(email, pass);
		if (pass == "") {
			Assert.assertEquals(loginPage.checkPassword(pass), message_required);
			ScreenShots.captureScreenShots(driver, "Login", "BlankPassword");
		} else {
			Assert.assertEquals(loginPage.checkPassword(pass), message_invalid_pass);
			ScreenShots.captureScreenShots(driver, "Login", "InvalidPassword");
		}
	}

	@Test(dataProvider = "test_successful", priority = 3)
	@Story("Test login successfull")
	@Description(" Successfull")
	@Step("Login with email: {0}, password: {1}")
	public void testLoginSuccessfull(String email, String pass) {
		homePage.open().clickOnSignIn();
		loginPage.login(email, pass);
		Assert.assertEquals(loginPage.checkSuccessful(), message_successfull);
	}

	@DataProvider(name = "test_email")
	public static Object[][] test_email() {
		return new Object[][] { { "@#$%", "Thao123456#" }, { "", "Thao123456#" }, };
	}

	@DataProvider(name = "test_password")
	public static Object[][] test_password() {
		return new Object[][] { { "thaobtt@smartosc.com", "@%#" }, { "thaobtt@smartosc.com", "" }, };
	}

	@DataProvider(name = "test_successful")
	public static Object[][] test_successful() {
		return new Object[][] { { "thaobtt@smartosc.com", "Thao123456#" }, };
	}

}
