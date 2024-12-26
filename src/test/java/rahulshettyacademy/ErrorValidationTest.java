package rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	@Test(groups= {"Error Handling"},retryAnalyzer=Retry.class)	
	public void errorValiation() throws IOException, InterruptedException	{		
		// TODO Auto-generated method stub		
		String productName="IPHONE 13 PRO";		
		landingPage.loginApplication("thoufidm87@gmail.com", "thissEcommerce@123");		
		String errorMessage=landingPage.getErrorMessage();		
		Assert.assertEquals(errorMessage, "Incorrect email or password.");					
		}	
	@Test	
	public void productErrorValidation() throws IOException, InterruptedException	{		
		// TODO Auto-generated method stub		
		String productName="IPHONE 13 PRO";		
		ProductCatalogue productCatalogue=landingPage.loginApplication("thoufidm87@gmail.com","thisisEcommerce@123");		
		List<WebElement> products=productCatalogue.getProductList();		
		productCatalogue.addToCart(productName);		
		CartPage cartPage=productCatalogue.goToCart();		
		Boolean flag=cartPage.verifyProductName(productName);		
		Assert.assertTrue(flag);	
		}
	}
