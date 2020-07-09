package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import data.Product;

public class WishlistPage extends BasePage {

	String XPATH_EMPTY_WISHLIST = "//div[@class='message info empty']";
	String XPATH_BUTTON_REMOVE_WISHLIST = "//div[@class='products-grid wishlist']//a[@class='btn-remove action delete']";
	String XPATH_SUMMARY_PRODUCT_NAME = "//div[@class='products-grid wishlist']//a[normalize-space(text())='%s']";
	String XPATH_SUMMARY_PRODUCT_PRICE = "//div[@class='products-grid wishlist']//div[@class='product-item-info'][.//a[normalize-space(text())='%s']]//span[@class='price']";
	String XPATH_BUTTON_UPDATE_WISHLIST = "//div[@class='primary']//span[text()='Update Wish List']";
	String XPATH_BUTTON_SHARE_WISHLIST = "//div[@class='primary']//span[text()='Share Wish List']";
	String XPATH_BUTTON_ADD_ALL_TO_CART = "//div[@class='primary']//span[text()='Add All to Cart']";
	String XPATH_MESSAGE = "//div[@data-bind='html: message.text']";

	public WishlistPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void openWishlist() {
		driver.get("https://demo.smartosc.com/wishlist/");
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public boolean checkProductNameDisplay(Product product) {
		return actionUtility.checkElementExist(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_NAME, product.getName())),
				10);
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public String getSummaryProductPrice(Product product) {
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_PRICE, product.getName())));
	}

	public String checkButtonUpdateWishlist() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_BUTTON_UPDATE_WISHLIST));
		return driver.findElement(By.xpath(XPATH_BUTTON_UPDATE_WISHLIST)).getText();
	}

	public String checkButtonShareWishlist() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_BUTTON_SHARE_WISHLIST));
		return driver.findElement(By.xpath(XPATH_BUTTON_SHARE_WISHLIST)).getText();
	}

	public String checkButtonAddAllToCart() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_BUTTON_ADD_ALL_TO_CART));
		return driver.findElement(By.xpath(XPATH_BUTTON_ADD_ALL_TO_CART)).getText();
	}

	public void removeAllProductInWishList() {
		while (driver.findElements(By.xpath(XPATH_EMPTY_WISHLIST)).size() == 0) {
			actionUtility.clickByJS(By.xpath(XPATH_BUTTON_REMOVE_WISHLIST));
			waitUtility.sleep(5);
		}
	}

	public boolean checkMessageDisplay() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE));
	}

}
