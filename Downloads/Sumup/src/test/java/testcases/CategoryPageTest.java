package testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.DataTest;
import data.Product;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.CartPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.LoginPage;
import pages.WishlistPage;
@Feature("Test Category Page")
public class CategoryPageTest extends BaseTest {
	CategoryPage categoryPage;

	HomePage homePage;
	LoginPage loginPage;
	CartPage cartPage;
	WishlistPage wishlistPage;
	Product product1, product2;
	List<Product> listProduct;

	@BeforeTest
	public void preCondition() {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		categoryPage = new CategoryPage(driver);
		cartPage = new CartPage(driver);
		wishlistPage = new WishlistPage(driver);
		DataTest dataTest = new DataTest();
		product1 = dataTest.getProduct("product1");
		product2 = dataTest.getProduct("product2");
		listProduct = new ArrayList<Product>();
		listProduct.add(product1);
		listProduct.add(product2);
		homePage.open().clickOnSignIn();
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");
		Assert.assertTrue(homePage.checkUserLoggedIn(), "Login unsucessfully.Please check!");
		categoryPage.open();

	}

	public void addProductsToWishlistPage() {
		for (Product product : listProduct) {
			categoryPage.addProductToWishlist(product);
			categoryPage.open();
		}
	}

	public void addProductsToComparePage() {
		for (Product product : listProduct) {
			categoryPage.addProductToCompare(product);
			Assert.assertTrue(categoryPage.checkMessageSuccessful(),
					"You added product " + product.getName() + " to the comparison list.");
		}
	}

	@Test(priority = 1)
	@Story("Test Sort By Price")
	public void verifySortByPrice() throws Exception {
		categoryPage.open();
		categoryPage.sortBy("Price");
		categoryPage.verifySortByPriceCorrect();
	}

	@Test(priority = 2)
	@Story("Test Add Product To Cart")
	public void addProductsToCartPage() {
		for (Product product : listProduct) {
			categoryPage.addProductToCart(product);
			Assert.assertTrue(categoryPage.checkMessageSuccessful(),
					"You added " + product.getName() + " to your shopping cart.");
		}
	}

	@Test(priority = 3)
	@Story("Test Add Product To Wishlist On Left Bar")
	public void verifyProductInfoInWishlistOnLeftBar() {
		addProductsToWishlistPage();
		for (Product product : listProduct) {
			Assert.assertTrue(categoryPage.checkProductNameDisplay(product),
					"Product " + product.getName() + " is not added to wishlist!");
			Assert.assertEquals(categoryPage.getSummaryProductPrice(product).replace("\u00A0", " "),
					product.getPrice());
		}
	}

	@Test(priority = 4)
	@Story("Test Add Product To Compare On Left Bar")
	public void verifyProductInfoInCompareOnLeftBar() {
		addProductsToWishlistPage();
		for (Product product : listProduct) {
			Assert.assertTrue(categoryPage.checkProductNameInCompareDisplay(product),
					"Product " + product.getName() + " is not added to compare!");
		}
	}

	@Test(priority = 5)
	@Story("Test Filter")
	public void verifyFilter() {
		categoryPage.filter("Size", "S");
		Assert.assertEquals(categoryPage.checkResultFilter("S"), "S");
		categoryPage.filter("Color", "Black");
		Assert.assertEquals(categoryPage.checkResultFilter("Black"), "Black");
		categoryPage.filter("Price", "60,00");
		Assert.assertEquals(categoryPage.checkResultFilterPrice("60,00"), "60,00 US$ - 69,99 US$");

	}

}
