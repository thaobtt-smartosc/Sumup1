package testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.DataTest;
import data.Product;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.WishlistPage;
@Feature("Test Wishlist Page")
public class WishlistPageTest extends BaseTest {
	HomePage homePage;
	LoginPage loginPage;
	ProductDetailPage productDetailPage;
	WishlistPage wishlistPage;
	Product product1, product2;
	List<Product>listProduct;
	String button_update_wishlist="Update Wish List";
	String button_share_wishlist="Share Wish List";
	String button_add_all_to_cart="Add All to Cart";
	
	
	@BeforeTest
	public void preCondition()
	{
		homePage = new HomePage(driver);
		loginPage=new LoginPage(driver);
		productDetailPage=new ProductDetailPage(driver);
		wishlistPage=new WishlistPage(driver);
		DataTest dataTest=new DataTest();
		product1=dataTest.getProduct("product1");
		product2=dataTest.getProduct("product2");
		listProduct=new ArrayList<Product>();
		listProduct.add(product1);
		listProduct.add(product2);
		
	}
	@Test( priority = 1)
	@Story("Test Clear Product In Wishlist")
	@Description("Clear Product In Wishlist")
	public void clearAllProductInWishlist()
	{
		homePage.open().clickOnSignIn();
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");
		Assert.assertTrue(homePage.checkUserLoggedIn(), "Login unsucessfully.Please check!");
		wishlistPage.openWishlist();
		wishlistPage.removeAllProductInWishList();	
		Assert.assertTrue(wishlistPage.checkMessageDisplay(), "Delete product unsucessfully. Please check!");
	
	}
	@Test( priority = 2)
	@Story("Test Add Product To Wishlist")
	@Description("Add Product To Wishlist")
	public void addProducts() {
		for (Product product : listProduct) {
			productDetailPage.addProductToWishList(product);
			Assert.assertTrue(productDetailPage.messageSuccessDisplay(),
					"Add to wishlist unsucessfully.Plz check!");
		}
	}
	
	@Test( priority = 3)
	@Story("Test Three Button ")
	@Description("Test Three Button")
	public void verifyThreeButton()
	{
		Assert.assertEquals(wishlistPage.checkButtonUpdateWishlist(),button_update_wishlist);
		Assert.assertEquals(wishlistPage.checkButtonShareWishlist(),button_share_wishlist);
		Assert.assertEquals(wishlistPage.checkButtonAddAllToCart(),button_add_all_to_cart);
	}
	@Test( priority = 4)
	@Story("Test Product Info In Wishlist")
	@Description("Product Info In Wishlist")
	public void verifyProductInfoInWishList() {
		for (Product product : listProduct) {
			Assert.assertTrue(wishlistPage.checkProductNameDisplay(product),
					"Product " + product.getName() + " is not added to wishlist!");
			Assert.assertEquals(product.getPrice(), wishlistPage.getSummaryProductPrice(product).replace("\u00A0"," "),
					"Price of product " + product.getName() + " is not correct");
		}
	}
//	@Test
//	@Story("Test Infomation of Product In Wishlist")
//	public void verifyProductInWishList() {
//		clearAllProductInWishlist();
//		addProducts();
//		verifyThreeButton();
//		verifyProductInfoInWishList();
//		
//	}

}

