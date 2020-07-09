package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LogoutPage extends BasePage {

	String XPATH_SIGN_OUT = "//span[@class='base']";

	public LogoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String checkUserSignOut() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_SIGN_OUT));
		return driver.findElement(By.xpath(XPATH_SIGN_OUT)).getText();
	}

}
