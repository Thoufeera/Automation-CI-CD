package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\rahulshettyacademy\\GlobalDta.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	public List<HashMap<String,String>> readData(String filePath) throws IOException
	{
		//json to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//string to map
		ObjectMapper mapper=new ObjectMapper();
		List <HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
	 TakesScreenshot ts=(TakesScreenshot)driver; 
	 File source=ts.getScreenshotAs(OutputType.FILE);
	 File target=new File(System.getProperty("user.dir")+"\\Reports\\"+testcaseName+".png");
	 FileUtils.copyFile(source,target);
	 return System.getProperty("user.dir")+"\\Reports\\"+testcaseName+".png";
	 }
																					

	@BeforeMethod(alwaysRun=true)	
	public LandingPage launchApplication() throws IOException 
	{		
		driver=initializeDriver();		
		landingPage = new LandingPage(driver);		
		landingPage.goTo();		
		return landingPage;	
		}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}