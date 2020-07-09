package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;

@Feature("Test Logout Page")
public class LogoutPageTest extends BaseTest {
	String XPATH_URL_SIGN_OUT = "https://demo.smartosc.com/customer/account/logoutSuccess/";
	String message_logout_succssful="You are signed out";
	HomePage homePage;
	LoginPage loginPage;
	LogoutPage logoutPage;
	@BeforeClass
	public void preCondition() {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		logoutPage=new LogoutPage(driver);
	}
	@Test
	@Story("Test logout successfull")
	@Description("Logout successfull")
	@Step("Logout")
	public void testLogout()
	{
		homePage.open().clickOnSignIn();
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");
		homePage.clickOnOption("Sign Out");
		Assert.assertEquals(driver.getCurrentUrl(), XPATH_URL_SIGN_OUT);
		Assert.assertEquals(logoutPage.checkUserSignOut(),message_logout_succssful );
	}
}
