package main.java;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MandSTest {
	
	private static String DRIVERPATH = "C:\\Users\\BIPOC2\\Downloads\\geckodriver-v0.11.1-win64\\";
	private static String BASEURL = "http://www.marksandspencer.com/";
	private String productName = "Socks";
	private String invalidProductName = "jhghj";
	private static int WAITTIME = 30;
    private static WebDriver driver;
	

	@BeforeClass
	public static void setup(){
		System.setProperty("webdriver.gecko.driver", DRIVERPATH+"geckodriver.exe");
		//Enable these lines if your Selenium version is 3.0 and Firefox version is 48.0 and above
//		DesiredCapabilities capabilities=DesiredCapabilities.firefox();
//		capabilities.setCapability("marionette", true);
    	driver = new FirefoxDriver();
    	driver.manage().timeouts().implicitlyWait(WAITTIME, TimeUnit.SECONDS);
    	driver.get(BASEURL);
	}
	
	@Test
	public void searchValidProduct()
	{
		driver.get(BASEURL);
    	WebElement searchfield = driver.findElement(By.xpath("//*[@id='global-search']"));
    	searchfield.clear();
    	searchfield.sendKeys(productName);
    	
    	WebElement goButton = driver.findElement(By.xpath("//*[@id='goButton']"));
    	goButton.click();
    	
    	assertNotNull(driver.findElement(By.xpath("//div[@id='product-listing']")));
	}
	
	@Test
	public void searchInvalidProduct()
	{
		driver.get(BASEURL);
    	WebElement searchfield = driver.findElement(By.xpath("//*[@id='global-search']"));
    	searchfield.clear();
    	searchfield.sendKeys(invalidProductName);
    	driver.findElement(By.xpath("//*[@id='goButton']")).click();
    	
    	String screenErrorMessage = driver.findElement(By.xpath("//div[@class='M083 search-results']//p[@class='criteria']")).getText();
    	String errorMessage = "You searched for "+invalidProductName+", we're sorry we couldn't find anything to match your search";
    	assertEquals(errorMessage,screenErrorMessage);
	}
    	
     @AfterClass
   	 public static void closeBrowser(){
    	 if(driver!=null) {
 			driver.close();
    	 }
   		 driver.quit();
   	 }

    	
    	       
            
    
	

	
}
		


