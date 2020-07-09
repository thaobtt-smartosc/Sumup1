package testcases;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.Address;
import data.DataTest;
import data.Product;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderSuccessPage;
import pages.ProductDetailPage;
@Feature("Test Checkout Page")
public class CheckoutPageTest extends BaseTest {
	HomePage homePage;
	LoginPage loginPage;
	ProductDetailPage productDetailPage;
	CheckoutPage checkoutPage;
	CartPage cartPage;
	OrderSuccessPage orderSuccessPage;
	Product product1, product2;
	List<Product> listProduct;
	Address address;
	String shippingMethod = "Table Rate";
	Float subTotal;
	String URL_ORDER_PLACE_SUCCESS = "https://demo.smartosc.com/checkout/onepage/success/";

	@BeforeTest
	public void preCondition() {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		productDetailPage = new ProductDetailPage(driver);
		checkoutPage = new CheckoutPage(driver);
		cartPage = new CartPage(driver);
		orderSuccessPage = new OrderSuccessPage(driver);
		DataTest dataTest = new DataTest();
		product1 = dataTest.getProduct("product1");
		product2 = dataTest.getProduct("product2");
		address = dataTest.getAddress("default_address");
		listProduct = new ArrayList<Product>();
		listProduct.add(product1);
		listProduct.add(product2);
	}
	public void clearCart() {
		cartPage.open();
		cartPage.removeAllProductInCart();
	}

	public void addProducts() {
		for (Product product : listProduct) {
			productDetailPage.addProductToCart(product);
			Assert.assertEquals(productDetailPage.messageSuccessDisplay1(),
					"You added " + product.getName() + " to your shopping cart.");
		}
	}

	public Float getSubtotal() {
		float subTotal = 0;
		for (Product product : listProduct) {
			Float price = Float.parseFloat(product.getPrice().replace("US$", "").replace(',', '.'));
			subTotal += price;
		}
		return subTotal;
	}
	public void fillShippingAddressInfo()
	{
		if (checkoutPage.checkButtonSignIn()) {
			checkoutPage.fillEmail("thaobtt@smartosc.com");
			checkoutPage.fillShippingAddressInfo(address);
		} else {
			checkoutPage.clickOnNewAddressButton();
			checkoutPage.fillShippingAddressInfo(address);
			checkoutPage.clickOnShipHereButton();
		}
	}
	public void verifyProductInfo()
	{
		for (Product product : listProduct) {
			Assert.assertTrue(checkoutPage.checkProductNameDisplay(product),
					"Product " + product.getName() + " is not added to cart!");
			Assert.assertEquals(product.getPrice(),
					checkoutPage.checkSummaryProductPrice(product).replace("\u00A0", " "),
					"Price of product " + product.getName() + " is not correct");
			Assert.assertEquals(product.getSize(), checkoutPage.checkSummaryProductSize(product),
					"Size of product " + product.getName() + " is not correct");
			Assert.assertEquals(product.getColor(), checkoutPage.checkSummaryProductColor(product),
					"Color of product " + product.getName() + " is not correct");
		}
	    BigDecimal bd = new BigDecimal(getSubtotal()).setScale(2, RoundingMode.FLOOR);
	    Assert.assertEquals(bd +" US$",checkoutPage.getSummarySubTotal().replace("\u00A0", " ").replace(',', '.') , "Sub total is not correct");
	}

	@Test
	@Story("Test Checkout Page ")
	public void verifyCheckOutpage() throws Exception {
		addProducts();
		productDetailPage.redirectToCheckoutPage();
		fillShippingAddressInfo();
		checkoutPage.chooseShippingMethod(shippingMethod);
		checkoutPage.clickOnNextButton();
		verifyProductInfo();
		checkoutPage.clickOnPaymentMethodButton();
		checkoutPage.clickOnPlaceOrderButton();
		if (orderSuccessPage.checkOrderNumberDisplay()) {
			Assert.assertEquals(driver.getCurrentUrl(), URL_ORDER_PLACE_SUCCESS);
		}
	}
}
