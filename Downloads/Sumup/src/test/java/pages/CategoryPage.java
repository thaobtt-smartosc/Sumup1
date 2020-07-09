package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import base.BasePage;
import data.Product;

public class CategoryPage extends BasePage {
	String XPATH_SELECT_SORT_By = "(//select[@id='sorter'])[1]";
	String XPATH_NEXT_BUTTON = "(//div[@class='pages']//a[@class='action  next'])[2]";
	String XPATH_LIST_PRICE_OF_PRODUCT = "//li[@class='item product product-item']//span[@class='price']";
	String XPATH_PRICE_OF_PRODUCT = "(//li[@class='item product product-item']//span[@class='price'])[%s]";
	String XPATH_ADD_TO_WISHLIST = "//div[@class='product-item-info']//div[.//a[normalize-space(text())='%s']]//a[@title='Add to Wish List']";
	String XPATH_ADD_TO_COMPARE = "//div[@class='product-item-info']//div[.//a[normalize-space(text())='%s']]//a[@title='Add to Compare']";
	String XPATH_ADD_TO_CART = "//div[@class='product-item-info']//div[.//a[normalize-space(text())='%s']]//button[@title='Add to Cart']";
	String XPATH_FIND_PRODUCT = "//div[@class='product-item-info']//div[.//a[normalize-space(text())='%s']]";
	String XPATH_SUMMARY_PRODUCT_NAME = "//div[@class='block block-wishlist']//div[@class='product-item-info']//span[normalize-space(text())='%s']";
	String XPATH_SUMMARY_PRODUCT_PRICE = "//div[@class='block block-wishlist']//div[@class='product-item-info'][.//span[normalize-space(text())='%s']]//span[@class='price']";
	String XPATH_SUMMARY_PRODUCT_NAME_COMPARE = "//div[@class='block block-compare']//a[normalize-space(text())='%s']";
	String XPATH_MESSAGE_SUCCESSFUL = "//div[@data-bind='html: message.text']";

	String XPATH_CHOOSE_SIZE = "//div[@class='swatch-option text'][@option-label='%s']";
	String XPATH_CHOOSE_COLOR = "//div[@class='swatch-option color'][@option-label='%s']";

	String XPATH_FILTER_TITLE_OPTION = "//div[@class='filter-options']//div[@class='filter-options-title'][text()='%s']";
	String XPATH_FILTER_OPTION = "//div[@class='filter-options']//div[@option-label='%s']";
	String XPATH_FILTER_OPTION_PRICE = "//div[@class='filter-options']//span[@class='price'][contains(text(),'%s')]";
	String XPATH_RESULTS_FILTER = "//div[@class='filter-current']//span[text()='%s']";
	String XPATH_RESULTS_FILTER_PRICE = "//div[@class='filter-current']//span[contains(text(),'%s')]";

	String XPATH_NUMBER_ICON_CART = "//div[@class='details-qty qty']//input";
/**
 * @param driver
 */
	public CategoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void open() {
		driver.get("https://demo.smartosc.com/women/tops-women/jackets-women.html");
	}
/**
 * @param option
 */
	public void sortBy(String option) {
		waitUtility.waitUntilVisibility(By.xpath(XPATH_SELECT_SORT_By));
		actionUtility.selectIteam(By.xpath(XPATH_SELECT_SORT_By), option);
	}

	public float getPrice(By xpath) {
		return Float.parseFloat(driver.findElement(xpath).getText().replace("US$", "").replace(",", "."));
	}

	public boolean verifyPrice(float maxPricePage) throws Exception {
		waitUtility.waitUntilVisibility(By.xpath(XPATH_LIST_PRICE_OF_PRODUCT));
		List<WebElement> productList = driver.findElements(By.xpath(XPATH_LIST_PRICE_OF_PRODUCT));
		if (getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, 1))) < maxPricePage) {
			throw new Exception(
					"Max price of previous page: " + maxPricePage + " but price of first product on current page: "
							+ getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, 1))));
		}
		for (int i = 1; i < productList.size(); i++) {
			float pre = getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, i)));
			float next = getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, i + 1)));
			if (pre > next) {
				throw new Exception("Previous price: " + pre + " but Next price: " + next);
			}
		}

		maxPricePage = getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, productList.size() - 1)));
		if (actionUtility.checkElementDisplay(By.xpath(XPATH_NEXT_BUTTON))) {
			actionUtility.click(By.xpath(XPATH_NEXT_BUTTON));
			verifyPrice(maxPricePage);
		}
		return true;
	}

	public boolean verifySortByPriceCorrect() throws Exception {
		float maxPricePage = 0;
		List<WebElement> productList = driver.findElements(By.xpath(XPATH_LIST_PRICE_OF_PRODUCT));
		// check product list has data
		if (productList.size() < 1) {
			throw new Exception("No product!");
		}
		return verifyPrice(maxPricePage);
	}

	public void addProductToWishlist(Product product) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_FIND_PRODUCT, product.getName()))))
				.perform();
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_ADD_TO_WISHLIST, product.getName()))))
				.click().perform();
	}

	public void addProductToCompare(Product product) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_FIND_PRODUCT, product.getName()))))
				.perform();
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_ADD_TO_COMPARE, product.getName()))))
				.click().perform();
	}

	public void addProductToCart(Product product) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_FIND_PRODUCT, product.getName()))))
				.perform();
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_CHOOSE_SIZE, product.getSize())))).click()
				.perform();
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_CHOOSE_COLOR, product.getColor()))))
				.click().perform();
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_ADD_TO_CART, product.getName())))).click()
				.perform();

	}

	public void filter(String titleOption, String option) {
		Actions actions = new Actions(driver);
		waitUtility.waitUntilVisibility(By.xpath(String.format(XPATH_FILTER_TITLE_OPTION, titleOption)));
		actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_FILTER_TITLE_OPTION, titleOption))))
				.click().perform();
		if (titleOption == "Price") {
			waitUtility.waitUntilVisibility(By.xpath(String.format(XPATH_FILTER_OPTION_PRICE, option)));
			actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_FILTER_OPTION_PRICE, option))))
					.click().perform();
		} else {
			waitUtility.waitUntilVisibility(By.xpath(String.format(XPATH_FILTER_OPTION, option)));
			actions.moveToElement(driver.findElement(By.xpath(String.format(XPATH_FILTER_OPTION, option)))).click()
					.perform();
		}

	}

	public String checkResultFilter(String option) {
		actionUtility.checkElementDisplay(By.xpath(String.format(XPATH_RESULTS_FILTER, option)));
		return driver.findElement(By.xpath(String.format(XPATH_RESULTS_FILTER, option))).getText();
	}

	public String checkResultFilterPrice(String option) {
		actionUtility.checkElementDisplay(By.xpath(String.format(XPATH_RESULTS_FILTER_PRICE, option)));
		return driver.findElement(By.xpath(String.format(XPATH_RESULTS_FILTER_PRICE, option))).getText();
	}

	public boolean checkProductNameDisplay(Product product) {
		return actionUtility.checkElementExist(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_NAME, product.getName())),6);
	}

	public String getSummaryProductPrice(Product product) {
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_PRICE, product.getName())));
	}

	public boolean checkProductNameInCompareDisplay(Product product) {
		return actionUtility.checkElementExist(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_NAME_COMPARE, product.getName())), 6);
	}

	public boolean checkMessageSuccessful() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE_SUCCESSFUL));
		return actionUtility.checkElementExist(By.xpath(XPATH_MESSAGE_SUCCESSFUL), 6);
	}

	public String getNumberCart() {
		return driver.findElement(By.xpath("//span[@class='counter-number']")).getText();
	}
}
