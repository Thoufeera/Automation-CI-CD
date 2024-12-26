package rahulshettyacademy.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password(.+)$")
	public void logged_in_username_and_password(String username,String password) throws IOException
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
	}
	
	@And("^checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName)
	{
		CartPage cartPage = landingPage.goToCart();
		Boolean flag = cartPage.verifyProductName(productName);
		Assert.assertTrue(flag);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmation_page(String string)
	{
		String ConfirmMessage = confirmationPage.getConfirrmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
	}

}
