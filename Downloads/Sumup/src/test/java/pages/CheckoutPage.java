package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import data.Address;
import data.Product;

public class CheckoutPage extends BasePage {
	String INPUT_EMAIL = "//div[@class='control _with-tooltip']//input[@id='customer-email']";
	String INPUT_FIRST_NAME = "//input[@name='firstname']";
	String INPUT_LAST_NAME = "//input[@name='lastname']";
	String INPUT_STREET = "//input[@name='street[0]']";
	String INPUT_CITY = "//input[@name='city']";
	String SELECT_STATE = "//select[@name='region_id']";
	String INPUT_POSTCODE = "//input[@name='postcode']";
	String SELECT_COUNTRY = "//select[@name='country_id']";
	String INPUT_PHONE_NUMBER = "//input[@name='telephone']";

	String XPATH_BUTTON_NEXT = "//button[@data-role='opc-continue']";
	String XPATH_BUTTON_NEW_ADDRESS = "//div[@class='new-address-popup']/button";
	String XPATH_BUTTON_SHIP_HERE = "//button[@class='action primary action-save-address']";

	String XPATH_SUMMARY_SUB_TOTAL = "//tr[@class='totals sub']//span[@class='price']";
	String XPATH_SUMMARY_PRODUCT_NAME = "//strong[@class='product-item-name'][text()='%s']";
	String XPATH_SUMMARY_PRODUCT_PRICE = "//li[@class='product-item'][.//strong[text()='%s']]//span[@class='price']";
	String XPATH_SUMMARY_PRODUCT_SIZE = "//li[@class='product-item'][.//strong[text()='%s']]//div[@class='content']//dt[text()='Size']//following-sibling::dd[1]";
	String XPATH_SUMMARY_PRODUCT_COLOR = "//li[@class='product-item'][.//strong[text()='%s']]//div[@class='content']//dt[text()='Color']//following-sibling::dd[1]";

	String XPATH_SHIPPING_METHOD = "//tr[.//td[text()='%s']]//input";
	String XPATH_BUTTON_PLACE_ORDER = "//div[@class='payment-method _active']//button[@title='Place Order']";
	String XPATH_BUTTON_PAYMENT_METHOD = "//input[@id='banktransfer']";

	String XPATH_LOADING = "//div[@class='loading-mask'][@style='display: block;']";
	String XPATH_EMAIL = "//div[@class='control _with-tooltip']//input[@id='customer-email']";
	String XPATH_BUTTON_SIGN_IN = "//button[@class='action action-auth-toggle']";

	/**
	 * 
	 * @param driver
	 */
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean checkShippingAddressFormDisplay() {
		return actionUtility.checkElementDisplay(By.xpath(INPUT_FIRST_NAME));
	}

	public boolean checkButtonNewAddressDisplay() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_BUTTON_NEW_ADDRESS));
	}

	public void clickOnNewAddressButton() {
		actionUtility.click(By.xpath(XPATH_BUTTON_NEW_ADDRESS));
	}

	public void clickOnShipHereButton() {
		actionUtility.click(By.xpath(XPATH_BUTTON_SHIP_HERE));
	}

	/**
	 * 
	 * @param method
	 */
	public void chooseShippingMethod(String method) {
		actionUtility.click(By.xpath(String.format(XPATH_SHIPPING_METHOD, method)));
	}

	public void clickOnNextButton() {
		actionUtility.click(By.xpath(XPATH_BUTTON_NEXT));
	}

	public void waitLoadingInvisibility() {
		waitUtility.waitUntilExits(By.xpath(XPATH_LOADING), 60);
	}

	public void clickOnPaymentMethodButton() {
		waitUtility.sleep(6);
		actionUtility.click(By.xpath(XPATH_BUTTON_PAYMENT_METHOD));
	}

	public void clickOnPlaceOrderButton() {
		actionUtility.click(By.xpath(XPATH_BUTTON_PLACE_ORDER));
	}

	public boolean checkButtonSignIn() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_BUTTON_SIGN_IN));
	}

	/**
	 * 
	 * @param email
	 */
	public void fillEmail(String email) {
		actionUtility.sendKeys(By.xpath(XPATH_EMAIL), email);
	}

	/**
	 * 
	 * @param address
	 */
	public void fillShippingAddressInfo(Address address) {
		actionUtility.sendKeys(By.xpath(INPUT_FIRST_NAME), address.getFirstName());
		actionUtility.sendKeys(By.xpath(INPUT_LAST_NAME), address.getLastName());
		actionUtility.sendKeys(By.xpath(INPUT_STREET), address.getStreet());
		actionUtility.sendKeys(By.xpath(INPUT_CITY), address.getCity());
		actionUtility.sendKeys(By.xpath(INPUT_POSTCODE), address.getZipCode());
		actionUtility.sendKeys(By.xpath(INPUT_PHONE_NUMBER), address.getPhoneNumber());
		actionUtility.selectIteam(By.xpath(SELECT_STATE), address.getState());
	}

	public String getSummarySubTotal() {
		waitUtility.waitUntilVisibility(By.xpath(XPATH_SUMMARY_SUB_TOTAL));
		return actionUtility.getTextByJS(By.xpath(XPATH_SUMMARY_SUB_TOTAL));
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
	public String checkSummaryProductPrice(Product product) {
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_PRICE, product.getName())));
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public String checkSummaryProductSize(Product product) {
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_SIZE, product.getName())));
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public String checkSummaryProductColor(Product product) {
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_COLOR, product.getName())));
	}

}
