package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class CartPage extends BasePage {
	 String XPATH_BUTTON_REMOVE_PRODUCT_CART = "//form//a[@title='Remove item']";
	    String XPATH_EMPTY_CART = "//div[@class='cart-empty']";

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void open()
	{
		driver.get("https://demo.smartosc.com/checkout/cart");
	}
	public void removeAllProductInCart()
	{
		while(driver.findElements(By.xpath(XPATH_EMPTY_CART)).size()==0)
		{
			actionUtility.click(By.xpath(XPATH_BUTTON_REMOVE_PRODUCT_CART));
			waitUtility.sleep(6);
		}
	}
	

}
