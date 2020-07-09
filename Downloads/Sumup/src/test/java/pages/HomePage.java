package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.interactions.Actions;

import base.BasePage;

public class HomePage extends BasePage {
	String XPATH_LINK_SIGN_IN = "//div[@class='panel header']//li[@class='authorization-link']/a";
	String XPATH_SIGN_IN = "//div[@class='panel header']//span[@class='logged-in']";
	String XPATH_ACTION_SWITCH = "//div[@class='panel header']//button[@class='action switch']";
	String XPATH_OPTION = "//div[@class='panel header'][.//button[@class='action switch']]//a[contains(text(),'%s')]";
	String XPATH_SIGN_OUT = "//div[@class='panel header']//li[@class='customer-welcome']//a[contains(text(),'Sign Out')]";
	String XPATH_SIGN_UP = "//div[@class='panel header']//a[contains(text(),'Create an Account')]";
	String XPATH_A_PRODUCT = "//div[@class='product-item-info']//img[@alt='Breathe-Easy Tank']";
	String XPATH_RESULTS_SEARCH_PAGE = "//div[@class='message notice']/div";
	String XPATH_MY_ACCOUNT = "//div[@class='panel wrapper']//a[text()='My Account']";
	String XPATH_NAME_OF_PRODUCT = "(//div[@class='search results']//a[@class='product-item-link'])[1]";

	/**
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public HomePage open() {
		driver.get("https://demo.smartosc.com/");
		return this;
	}

	public HomePage clickOnSignIn() {
		actionUtility.click(By.xpath(XPATH_LINK_SIGN_IN));
		return this;
	}

	/**
	 * 
	 * @param option
	 * @return
	 */
	public HomePage clickOnOption(String option) {
		Actions actions = new Actions(driver);
		waitUtility.waitUntilVisibility(By.xpath(XPATH_ACTION_SWITCH));
		actions.moveToElement(driver.findElement(By.xpath(XPATH_ACTION_SWITCH))).click().perform();
		waitUtility.sleep(6);
		waitUtility.waitUntilVisibility(By.xpath(String.format(XPATH_OPTION, option)));
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_OPTION, option)))).click().perform();
		return this;
	}

	public HomePage clickOnCreateAnAccount() {
		actionUtility.click(By.xpath(XPATH_SIGN_UP));
		return this;
	}

	public boolean checkUserLoggedIn() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_SIGN_IN));
	}

	public HomePage clickOnAProduct() {
		actionUtility.click(By.xpath(XPATH_A_PRODUCT));
		return this;
	}

	/**
	 * 
	 * @param keys
	 * @return
	 */
	public HomePage seach(String keys) {
		driver.findElement(By.id("search")).sendKeys(keys, Keys.ENTER);
		return this;
	}

	public String checkResultsSearchWithValidInput() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_NAME_OF_PRODUCT));
		return driver.findElement(By.xpath(XPATH_NAME_OF_PRODUCT)).getText();
	}

	public String checkResultsSearch() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_RESULTS_SEARCH_PAGE));
		return driver.findElement(By.xpath(XPATH_RESULTS_SEARCH_PAGE)).getText();
	}

	/**
	 * 
	 * @param keys
	 * @return
	 */
	public String fomatKeySearch(String keys) {
		String[] arr = keys.split(" ");
		String key1 = "";
		for (String x : arr) {
			key1 = key1 + (x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase()) + " ";
		}
		return key1.trim();
	}

	public HomePage openCategory() {
		Actions actionsCategory = new Actions(driver);
		actionsCategory
				.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation']//li[.//span[text() = 'Men']]")))
				.perform();
		actionsCategory
				.moveToElement(driver.findElement(
						By.xpath("//nav[@class='navigation']//li[.//span[text() = 'Men']]//a[.//span[text()='Tops']]")))
				.perform();
		actionsCategory.moveToElement(driver.findElement(By.xpath(
				"//nav[@class='navigation']//li[.//span[text() = 'Men']][.//a[.//span[text()='Tops']]]//span[text()='Tees']")))
				.perform();
		actionsCategory.click().perform();
		waitUtility.sleep(6);
		return this;
	}

	/**
	 * 
	 * @param xpath
	 * @return
	 */
	public String getName(By xpath) {
		return driver.findElement(xpath).getText();
	}
}
