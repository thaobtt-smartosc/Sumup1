package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {
	String ID_INPUT_EMAIL = "email";
	String ID_INPUT_PASSWORD = "pass";
	String ID_BUTTON_SIGN_IN = "send2";
	String XPATH_LOGGED = "//div[@class='panel header']//span[@class='logged-in']";
	String ERROR_EMAIL = "//div[@id='email-error']";
	String ERROR_PASS = "//div[@id='pass-error']";
	String ERROR_PASS1 = "//div[@data-bind='html: message.text']";

	/**
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param email
	 * @param pass
	 */
	public void login(String email, String pass) {
		actionUtility.sendKeys(By.id(ID_INPUT_EMAIL), email);
		actionUtility.sendKeys(By.id(ID_INPUT_PASSWORD), pass);
		waitUtility.sleep(6);
		actionUtility.click(By.id(ID_BUTTON_SIGN_IN));
	}

	public String checkSuccessful() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_LOGGED));
		return driver.findElement(By.xpath(XPATH_LOGGED)).getText();
	}

	public String checkEmail() {
		actionUtility.checkElementDisplay(By.xpath(ERROR_EMAIL));
		return driver.findElement(By.xpath(ERROR_EMAIL)).getText();
	}

	/**
	 * 
	 * @param pass
	 * @return
	 */
	public String checkPassword(String pass) {
		if (pass == "") {
			actionUtility.checkElementDisplay(By.xpath(ERROR_PASS));
			return driver.findElement(By.xpath(ERROR_PASS)).getText();
		} else {
			actionUtility.checkElementDisplay(By.xpath(ERROR_PASS1));
			return driver.findElement(By.xpath(ERROR_PASS1)).getText();
		}

	}

}
