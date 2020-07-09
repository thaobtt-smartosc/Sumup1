package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends HomePage {
	String ERROR_FIRST_NAME = "//div[@id='firstname-error']";
	String ERROR_LAST_NAME = "//div[@id='lastname-error']";
	String ERROR_EMAIL = "//div[@id='email_address-error']";
	String ERROR_PASSWORD = "//div[@id='password-error']";
	String ERROR_CONFIRM_PASSWORD = "//div[@id='password-confirmation-error']";
	String XPATH_BUTTON_CREATE_AN_ACCOUNT = "//div[@class='column main']//button[@title='Create an Account']";
	String INPUT_FIRST_NAME = "//input[@id='firstname']";
	String INPUT_LAST_NAME = "//input[@id='lastname']";
	String INPUT_EMAIL = "//input[@id='email_address']";
	String INPUT_PASSWORD = "//input[@id='password']";
	String INPUT_CONFIRM_INPUT_PASSWORD = "//input[@id='password-confirmation']";

	/**
	 * 
	 * @param driver
	 */
	public SignupPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnButtonCreatAnAccount() {
		waitUtility.sleep(6);
		actionUtility.click(By.xpath(XPATH_BUTTON_CREATE_AN_ACCOUNT));

	}

	public String checkFirstName() {
		actionUtility.checkElementDisplay(By.xpath(ERROR_FIRST_NAME));
		return driver.findElement(By.xpath(ERROR_FIRST_NAME)).getText();
	}

	public String checkLastName() {
		actionUtility.checkElementDisplay(By.xpath(ERROR_LAST_NAME));
		return driver.findElement(By.xpath(ERROR_LAST_NAME)).getText();
	}

	public String checkEmail() {
		actionUtility.checkElementDisplay(By.xpath(ERROR_EMAIL));
		return driver.findElement(By.xpath(ERROR_EMAIL)).getText();
	}

	public String checkPassword() {
		actionUtility.checkElementDisplay(By.xpath(ERROR_PASSWORD));
		return driver.findElement(By.xpath(ERROR_PASSWORD)).getText();
	}

	public String checkConfirmPassword() {
		actionUtility.checkElementDisplay(By.xpath(ERROR_CONFIRM_PASSWORD));
		return driver.findElement(By.xpath(ERROR_CONFIRM_PASSWORD)).getText();
	}

}
