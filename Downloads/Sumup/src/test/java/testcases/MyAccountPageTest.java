package testcases;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import utility.ScreenShots;
@Feature("Test My Account Page")
public class MyAccountPageTest extends BaseTest{
	HomePage homePage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	String message_required="This is a required field.";
	String message_no_match_password="Please enter the same value again.";
	String message_successful="You saved the account information.";
	String message_error_current_password="The password doesn't match this account. Verify the password and try again.";
	String message_successful_change_billing_address="You saved the address.";
	
	@BeforeClass
	public void preCondition()
	{
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		myAccountPage=new MyAccountPage(driver);
		homePage.open().clickOnSignIn();
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");
	}
	@Test(dataProvider = "change_password_successful",priority = 1)
	@Story("Test Change Pasword Successfull")
	public void testChangePasswordSuccessful(String current_password, String new_password, String confirm_password)
	{
		homePage.clickOnOption("My Account");
		myAccountPage.changePassword(current_password, new_password, confirm_password);
		Assert.assertEquals(myAccountPage.checkChangePasswordSucessful(),message_successful );
	}
	@Test(dataProvider = "change_password_with_invalid_current_password", priority = 2)
	@Story("Test Change Pasword With Invalid Current Password")
	public void testChangePasswordWithInvalidCurrentPassword(String current_password, String new_password, String confirm_password)
	{
		homePage.clickOnOption("My Account");
		myAccountPage.changePassword(current_password, new_password, confirm_password);
		if(current_password=="")
		{
			Assert.assertEquals(myAccountPage.checkCurrentPassword(current_password), message_required);
			ScreenShots.captureScreenShots(driver,"MyAccount","BlankCurrentPassword");
		}
		else {
			Assert.assertEquals(myAccountPage.checkCurrentPassword(current_password), message_error_current_password);
			ScreenShots.captureScreenShots(driver,"MyAccount","InvalidCurrentPassword");
		}
	}
	@Test(dataProvider = "change_password_with_invalid_confirm_password", priority = 3)
	@Story("Test Change Pasword With Invalid Confirm Password")
	public void testChangePasswordWithInvalidConfirmPassword(String current_password, String new_password, String confirm_password)
	{
		homePage.clickOnOption("My Account");
		myAccountPage.changePassword(current_password, new_password, confirm_password);
		if(current_password=="")
		{
			Assert.assertEquals(myAccountPage.checkConfirmPassword(), message_required);
		}
		else {
			Assert.assertEquals(myAccountPage.checkConfirmPassword(), message_no_match_password);
			ScreenShots.captureScreenShots(driver,"MyAccount","InvalidConfirmPassword");
		}
	}
	
	@DataProvider(name = "change_password_successful")
	public static Object[][] test_change_password_successful() {
		return new Object[][] { { "Thao123456#", "Thao123456#","Thao123456#"}};
	}
	@DataProvider(name = "change_password_with_invalid_current_password")
	public static Object[][] test_change_password_with_invalid_current_password() {
		return new Object[][] { { "abcd", "Thao123456#","Thao123456#"},{ "", "Thao123456#","Thao123456#"}};
	}
	@DataProvider(name = "change_password_with_invalid_confirm_password")
	public static Object[][] test_change_password_with_invalid_confirm_password() {
		return new Object[][] { { "Thao123456#", "Thao123456#","ahihi do ngoc"}};
	}
	
	
	
	@Test(dataProvider = "change_billing_address_successful",priority = 4)
	@Story("Test Change Billing Address Successfull")
	public void testChangeBillingAddressSuccessful(String firstname,String lastname,String phonenumber,String streetaddress,String city,String zip,String country)
	{
		homePage.clickOnOption("My Account");
		myAccountPage.changeBillingAddress(firstname, lastname, phonenumber, streetaddress, city, zip, country);
		
			Assert.assertEquals(myAccountPage.checkChangeBillingAddressSuccessful(),message_successful_change_billing_address);
	}
	
	
	@DataProvider(name = "change_billing_address_successful")
	public static Object[][] test_change_billing_address_successful() {
		return new Object[][] { { "ahihi", "do ngoc","12345678","Ha Noi","Ha Noi","A1B 2C3","Canada"}};
	}
	
	
}
