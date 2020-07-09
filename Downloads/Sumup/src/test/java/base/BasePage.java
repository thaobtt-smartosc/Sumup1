package base;

import org.openqa.selenium.WebDriver;

import utility.ActionUtility;
import utility.WaitUtility;

public class BasePage {
	public WebDriver driver;
	public ActionUtility actionUtility;
	public WaitUtility waitUtility;

	/**
	 * @param driver
	 * @param actionUtility
	 * @param waitUtilit
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		actionUtility = new ActionUtility(driver);
		waitUtility = new WaitUtility(driver);
	}

}
