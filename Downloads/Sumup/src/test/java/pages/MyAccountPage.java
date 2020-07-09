package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class MyAccountPage extends BasePage {

	String XPATH_CURRENT_PASSWORD = "//input[@id='current-password']";
	String XPATH_NEW_PASSWORD = "//input[@id='password']";
	String XPATH_CONFIRM_PASSWORD = "//input[@id='password-confirmation']";
	String XPATH_BUTTON_SAVE = "//button[@class='action save primary']";
	String XPATH_BUTTON_CHANGE_PASSWORD = "//a[@class='action change-password']";
	String MESSAGE_ERROR_CURRENT_PASSWORD = "//div[@id='current-password-error']";
	String MESSAGE_ERROR = "//div[@data-bind='html: message.text']";
	String MESSAGE_ERROR_NEW_PASSWORD = "//div[@id='password-error']";
	String MESSAGE_ERROR_CONFIRM_PASSWORD = "//div[@id='password-confirmation-error']";

	String XPATH_FIRST_NAME = "//input[@id='firstname']";
	String XPATH_LAST_NAME = "//input[@id='lastname']";
	String XPATH_PHONE_NUMBER = "//input[@id='telephone']";
	String XPATH_STRESS_ADDRESS = "//input[@id='street_1']";
	String XPATH_CITY = "//input[@id='city']";
	String XPATH_ZIP = "//input[@id='zip']";
	String XPATH_COUNTRY = "//select[@id='country']";
	String XPATH_BUTTON_SAVE_ADDRESS = "//button[@title='Save Address']";
	String MESSAGE_ERROR_FIRST_NAME = "//div[@id='firstname-error']";
	String MESSAGE_ERROR_LAST_NAME = "//div[@id='lastname-error']";
	String MESSAGE_ERROR_PHONE_NUMBER = "//div[@id='telephone-error']";
	String MESSAGE_ERROR_STRESS_ADDRESS = "//div[@id='street_1-error']";
	String MESSAGE_ERROR_CITY = "//div[@id='city-error']";
	String MESSAGE_ERROR_ZIP = "//div[@id='zip-error']";
	String MESSAGE_SUCCESSFUL = "//div[@data-ui-id='message-success']";
	String XPATH_EDIT_ADDRESS = "//div[@class='box box-billing-address']//a[@class='action edit']";

	/**
	 * 
	 * @param driver
	 */
	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param current_password
	 * @param new_password
	 * @param confirm_password
	 */
	public void changePassword(String current_password, String new_password, String confirm_password) {
		actionUtility.click(By.xpath(XPATH_BUTTON_CHANGE_PASSWORD));
		actionUtility.sendKeys(By.xpath(XPATH_CURRENT_PASSWORD), current_password);
		actionUtility.sendKeys(By.xpath(XPATH_NEW_PASSWORD), new_password);
		actionUtility.sendKeys(By.xpath(XPATH_CONFIRM_PASSWORD), confirm_password);
		actionUtility.clickByJS(By.xpath(XPATH_BUTTON_SAVE));
	}

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param phonenumber
	 * @param streetaddress
	 * @param city
	 * @param zip
	 * @param country
	 */

	public void changeBillingAddress(String firstname, String lastname, String phonenumber, String streetaddress,
			String city, String zip, String country) {
		actionUtility.click(By.xpath(XPATH_EDIT_ADDRESS));
		actionUtility.clear(By.xpath(XPATH_FIRST_NAME));
		actionUtility.sendKeys(By.xpath(XPATH_FIRST_NAME), firstname);
		actionUtility.clear(By.xpath(XPATH_LAST_NAME));
		actionUtility.sendKeys(By.xpath(XPATH_LAST_NAME), lastname);
		actionUtility.clear(By.xpath(XPATH_PHONE_NUMBER));
		actionUtility.sendKeys(By.xpath(XPATH_PHONE_NUMBER), phonenumber);
		actionUtility.clear(By.xpath(XPATH_STRESS_ADDRESS));
		actionUtility.sendKeys(By.xpath(XPATH_STRESS_ADDRESS), streetaddress);
		actionUtility.clear(By.xpath(XPATH_CITY));
		actionUtility.sendKeys(By.xpath(XPATH_CITY), city);
		actionUtility.clear(By.xpath(XPATH_ZIP));
		actionUtility.sendKeys(By.xpath(XPATH_ZIP), zip);
		actionUtility.selectIteam(By.xpath(XPATH_COUNTRY), country);
		actionUtility.click(By.xpath(XPATH_BUTTON_SAVE_ADDRESS));
	}

	/**
	 * 
	 * @param current_password
	 * @return
	 */

	public String checkCurrentPassword(String current_password) {
		if (current_password == "") {
			actionUtility.checkElementDisplay(By.xpath(MESSAGE_ERROR_CURRENT_PASSWORD));
			return driver.findElement(By.xpath(MESSAGE_ERROR_CURRENT_PASSWORD)).getText();
		} else {
			actionUtility.checkElementDisplay(By.xpath(MESSAGE_ERROR));
			return driver.findElement(By.xpath(MESSAGE_ERROR)).getText();
		}

	}

	public String checkChangePasswordSucessful() {
		actionUtility.checkElementDisplay(By.xpath(MESSAGE_ERROR));
		return driver.findElement(By.xpath(MESSAGE_ERROR)).getText();
	}

	public String checkNewPassword() {
		actionUtility.checkElementDisplay(By.xpath(MESSAGE_ERROR_NEW_PASSWORD));
		return driver.findElement(By.xpath(MESSAGE_ERROR_NEW_PASSWORD)).getText();
	}

	public String checkConfirmPassword() {
		actionUtility.checkElementDisplay(By.xpath(MESSAGE_ERROR_CONFIRM_PASSWORD));
		return driver.findElement(By.xpath(MESSAGE_ERROR_CONFIRM_PASSWORD)).getText();
	}

	public String checkChangeBillingAddressSuccessful() {
		actionUtility.checkElementDisplay(By.xpath(MESSAGE_SUCCESSFUL));
		return driver.findElement(By.xpath(MESSAGE_SUCCESSFUL)).getText();
	}

}
