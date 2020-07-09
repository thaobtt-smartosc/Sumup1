package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	int WAITTING_TIME = 60;

	/**
	 * @param driver
	 */
	public WaitUtility(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 
	 * @param locator
	 */
	public void waitUntilExits(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, WAITTING_TIME);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * 
	 * @param locator
	 * @param waittingTime
	 */
	public void waitUntilExits(By locator, long waittingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waittingTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * 
	 * @param locator
	 */
	public void waitUntilVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, WAITTING_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * 
	 * @param locator
	 * @param waittingTime
	 */
	public void waitUntilVisibility(By locator, int waittingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waittingTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * 
	 * @param locator
	 */
	public void waitUntilClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, WAITTING_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * 
	 * @param time
	 */
	public void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
