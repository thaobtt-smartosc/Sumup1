package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ActionUtility {
	WebDriver driver;
	WaitUtility waitUtility;

	/**
	 * @param driver
	 * @param waitUtility
	 */
	public ActionUtility(WebDriver driver) {
		this.driver = driver;
		waitUtility = new WaitUtility(driver);
	}

	/**
	 * 
	 * @param locator
	 * @param data
	 */
	public void sendKeys(By locator, String data) {
		waitUtility.waitUntilVisibility(locator);
		driver.findElement(locator).sendKeys(data);
	}

	/**
	 * 
	 * @param locator
	 */
	public void click(By locator) {
		waitUtility.waitUntilVisibility(locator);
		waitUtility.waitUntilClickable(locator);
		driver.findElement(locator).click();
	}

	/**
	 * 
	 * @param locator
	 */
	public void clickByJS(By locator) {
		waitUtility.waitUntilExits(locator, 60);
		((JavascriptExecutor) driver).executeScript("return arguments[0].click();", driver.findElement(locator));
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public String getTextByJS(By locator) {
		waitUtility.waitUntilExits(locator, 60);
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;",
				driver.findElement(locator));
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public boolean checkElementDisplay(By locator) {
		try {
			waitUtility.waitUntilVisibility(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param by
	 * @param waitingTime
	 * @return
	 */
	public boolean checkElementExist(By by, int waitingTime) {
		try {
			waitUtility.waitUntilExits(by, waitingTime);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param text
	 */
	public void selectIteam(By locator, String text) {
		waitUtility.waitUntilVisibility(locator);
		waitUtility.waitUntilClickable(locator);
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}

	/**
	 * 
	 * @param locator
	 */
	public void clear(By locator) {
		waitUtility.waitUntilVisibility(locator);
		driver.findElement(locator).clear();
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public String getValueByJS(By locator) {
		waitUtility.waitUntilExits(locator);
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",
				driver.findElement(locator));
	}

}
