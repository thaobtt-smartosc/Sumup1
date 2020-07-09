package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.HomePage;

@Feature("Test Home Page")
public class HomePageTest extends BaseTest {
	String XPATH_SIGN_IN_PAGE = "https://demo.smartosc.com/customer/account/login/referer/aHR0cHM6Ly9kZW1vLnNtYXJ0b3NjLmNvbS8%2C/";
	String XPATH_SIGN_UP_PAGE = "https://demo.smartosc.com/customer/account/create/";
	String XPATH_PRODUCT_DETAIL_PAGE = "https://demo.smartosc.com/breathe-easy-tank.html";
	String XPATH_SEAARCH_PAGE = "https://demo.smartosc.com/catalogsearch/result/?q=";
	String KEY_SEARCH = "abcd";
	String KEY_SEARCH_VALID = "HERO HOODIE";
	String MESSAGE_SEARCH_NO_RESULTS = "Your search returned no results.";
	String URL_CATEGORY = "https://demo.smartosc.com/men/tops-men/tees-men.html";
	HomePage homePage;

	@BeforeClass
	public void preCondition() {
		homePage = new HomePage(driver);
	}

	@Test
	@Story("Test Click On Sign In")
	public void testClickOnSignIn() {
		homePage.open().clickOnSignIn();
		Assert.assertEquals(driver.getCurrentUrl(), XPATH_SIGN_IN_PAGE);
	}

	@Test
	@Story("Test Click On Create An Account")
	public void testClickOnCreateAnAccount() {
		homePage.open().clickOnCreateAnAccount();
		Assert.assertEquals(driver.getCurrentUrl(), XPATH_SIGN_UP_PAGE);
	}

	@Test
	@Story("Test Click On A Product")
	public void testClickOnAProduct() {
		homePage.open().clickOnAProduct();
		Assert.assertEquals(driver.getCurrentUrl(), XPATH_PRODUCT_DETAIL_PAGE);
	}

	@Test
	@Story("Test Seach With Invalid Key Word")
	public void testSearchInvalidInput() {
		homePage.open().seach(KEY_SEARCH);
		Assert.assertEquals(driver.getCurrentUrl(), XPATH_SEAARCH_PAGE + KEY_SEARCH);
		Assert.assertEquals(homePage.checkResultsSearch(), MESSAGE_SEARCH_NO_RESULTS);
	}

	@Test
	@Story("Test Seach With Valid Key Word")
	public void testSearchValidInput() {
		homePage.open().seach(KEY_SEARCH_VALID);
		Assert.assertEquals(homePage.checkResultsSearchWithValidInput(), homePage.fomatKeySearch(KEY_SEARCH_VALID));
	}

	@Test
	@Story("Test Open Category")
	public void testOpenCategory() {
		homePage.open().openCategory();
		Assert.assertEquals(driver.getCurrentUrl(), URL_CATEGORY);

	}

}
