package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.SignupPage;
import utility.ScreenShots;

@Feature("Test Sign Up Page")
public class SignupPageTest extends BaseTest {
	HomePage homePage;
	SignupPage signupPage;
	String MESSAGE_REQUIRED="This is a required field.";

	@BeforeClass
	public void preCondition() {
		homePage = new HomePage(driver);
		signupPage = new SignupPage(driver);
	}
	@Test
	@Story("Test Sign Up ")
	@Description("Test Sign Up With Blank Fields")
	@Step("Sign up with blank fields")
	public void testSignup() {
		homePage.open().clickOnCreateAnAccount();
		signupPage.clickOnButtonCreatAnAccount();
		ScreenShots.captureScreenShots(driver,"SignUp","BlankAllField");
		Assert.assertEquals(signupPage.checkFirstName(), MESSAGE_REQUIRED);
		Assert.assertEquals(signupPage.checkLastName(), MESSAGE_REQUIRED);
		Assert.assertEquals(signupPage.checkEmail(), MESSAGE_REQUIRED);
		Assert.assertEquals(signupPage.checkPassword(), MESSAGE_REQUIRED);
		Assert.assertEquals(signupPage.checkConfirmPassword(), MESSAGE_REQUIRED);
		
	}

}
