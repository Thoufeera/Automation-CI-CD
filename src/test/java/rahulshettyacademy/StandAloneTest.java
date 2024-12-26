package rahulshettyacademy;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("thoufidm87@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("thisisEcommerce@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("productName")).findFirst().orElse(null);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card-body button:last-of-type"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List <WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean flag=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(flag);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a =new Actions(driver);
		WebElement selectCountry=driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		a.sendKeys(selectCountry, "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item:nth-of-type(2)"))).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String ConfirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
		
		
		
		

		
		
		
		
		

	}

}
