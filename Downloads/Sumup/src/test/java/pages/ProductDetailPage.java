package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import base.BasePage;
import data.Product;

public class ProductDetailPage extends BasePage {
	String XPATH_CHOOSE_SIZE = "//div[@class='swatch-option text'][@option-label='%s']";
	String XPATH_CHOOSE_COLOR = "//div[@class='swatch-option color'][@option-label='%s']";
	String ID_INPUT_QTY = "qty";
	String ID_BUTTON_ADD_TO_CART = "product-addtocart-button";
	String XPATH_ADD_TO_WISHLIST = "//div[@class='product-social-links']//span[text()='Add to Wish List']";
	String XPATH_MESSAGE_ADDTOCART_SUCCESS = "//div[@role='alert'][./div[@data-ui-id='message-success']]";
	String XPATH_MESSAGE_SUCCESS = "//div[@data-bind = 'html: message.text']";
	String XPATH_ICON_MINI_CART = "//div[@class='header content']//a[@class='action showcart']";
	String XPATH_BUTTON_PROCEED_TO_CHECKOUT = "//div[@class='actions']//button[@class='action primary checkout']";

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param url
	 */
	public void open(String url) {
		driver.get(url);
	}

	/**
	 * 
	 * @param size
	 */
	public void chooseSize(String size) {
		actionUtility.click(By.xpath(String.format(XPATH_CHOOSE_SIZE, size)));
	}

	/**
	 * 
	 * @param color
	 */
	public void chooseColor(String color) {
		actionUtility.click(By.xpath(String.format(XPATH_CHOOSE_COLOR, color)));
	}

	/**
	 * 
	 * @param qty
	 */
	public void inputQty(int qty) {
		actionUtility.sendKeys(By.id(ID_INPUT_QTY), String.valueOf(qty));
	}

	/**
	 * 
	 * @param product
	 */
	public void addProductToWishList(Product product) {
		open(product.getUrl());
		waitUtility.sleep(6);
		actionUtility.click(By.xpath(XPATH_ADD_TO_WISHLIST));
	}

	public void addToCart() {
		actionUtility.click(By.id(ID_BUTTON_ADD_TO_CART));
	}

	/**
	 * 
	 * @param product
	 */
	public void addProductToCart(Product product) {
		open(product.getUrl());
		chooseSize(product.getSize());
		chooseColor(product.getColor());
		addToCart();
		waitUtility.sleep(6);
	}

	public void redirectToCheckoutPage() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(XPATH_ICON_MINI_CART))).click().perform();
		actions.moveToElement(driver.findElement(By.xpath(XPATH_BUTTON_PROCEED_TO_CHECKOUT))).click().perform();

	}

	public String messageSuccessDisplay1() {
		actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE_SUCCESS));
		return driver.findElement(By.xpath(XPATH_MESSAGE_SUCCESS)).getText();
	}

	public boolean messageSuccessDisplay() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE_SUCCESS));
	}
}
