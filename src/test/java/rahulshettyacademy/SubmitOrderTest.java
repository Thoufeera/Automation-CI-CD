package rahulshettyacademy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest { 
	// String productName="ZARA COAT 3";
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(Map<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(input.get("productName"));
		CartPage cartPage = landingPage.goToCart();
		Boolean flag = cartPage.verifyProductName(input.get("productName"));
		Assert.assertTrue(flag);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String ConfirmMessage = confirmationPage.getConfirrmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" })
	public void verifyOrderHistory(Map<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = landingPage.goToOrder();
		Assert.assertTrue(orderPage.verifyOrderHistory(input.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = readData(
				System.getProperty("user.dir") + "\\src\\main\\resources\\rahulshettyacademy\\data\\PurchaseData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	
	
	
	/*
	 * HashMap<String,String> map=new HashMap<String,String>(); map.put("email",
	 * "thoufidm87@gmail.com"); map.put("password", "thisisEcommerce@123");
	 * map.put("productName", "ZARA COAT 3"); HashMap<String,String> map1=new
	 * HashMap<String,String>(); map1.put("email", "chammu@gmail.com");
	 * map1.put("password", "thisisEcommerce@321"); map1.put("productName",
	 * "IPHONE 13 PRO"); * public Object[][] getData() { return new Object [][]
	 * {{"thoufidm87@gmail.com","thisisEcommerce@123","ZARA COAT 3"},{
	 * "chammu@gmail.com","thisisEcommerce@321","IPHONE 13 PRO"}}; }
	 */}