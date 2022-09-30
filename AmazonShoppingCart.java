package test.codes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonShoppingCart {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
	// Chrome Setup and Disable Browser Notifications

	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
		
	// Launch URL
			
    ChromeDriver driver = new ChromeDriver(options);
	driver.get("https://www.amazon.in/");
    driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	// SignIn to Account and verify successful login with Screenshot
	
	driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
	driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("+4792503217");
	driver.findElement(By.xpath("//input[@id='continue']")).click();
	driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("amazon123");
	driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	Thread.sleep(3000);
	
	  
   // To Take a screenshot of incorrect login  credentials.
	
 	File source1 = driver.getScreenshotAs(OutputType.FILE);
 	File destination1 = new File("./snaps/userLoginPage.png");
 	FileUtils.copyFile(source1, destination1);
	 	
   //  Validating Successful login
	
	String userName = driver.findElement(By.xpath("//div[@class='nav-line-1-container']/span")).getText();
	System.out.println(userName);
    if(userName.contains("vidya"))
    {
    	System.out.println("Username : Vidya logged in ");
    }
  
    
	// Search for OnePlus Mobile in search .
	
	driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys("oneplus 9 pro",Keys.ENTER);
	
	// Select the brand Oneplus under brand
	
	driver.findElement(By.xpath("//span[text()='OnePlus']")).click();
	
	// Print the model name, price and customer rating of first resulting item
	
	String modelName = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
	System.out.println("Model name------> "+modelName);
	
	String customerRating = driver.findElement(By.xpath("//span[contains(text(),'OnePlus 9 Pro 5G')]//following::span")).getAttribute("aria-label");
	System.out.println("customerRating----->"+customerRating);	
	
	String Price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
	System.out.println("Price in Rs----->"+Price);
	Thread.sleep(3000);
	
	
	// First on the product
	
	driver.findElement(By.xpath("//span[@class='a-price-whole']")).click();
	Set<String> windowHandles1 = driver.getWindowHandles();
	List<String> windowHandles2 = new ArrayList<String>(windowHandles1);
	driver.switchTo().window(windowHandles2.get(1));
	
	//Take screen shot using Selenium function and copy the snap to local directory using Java Function
	
	File source2 = driver.getScreenshotAs(OutputType.FILE);
	File destination2 = new File("./snaps/scrnshotAmazon1Cart.png");
	FileUtils.copyFile(source2, destination2);
	
	// Click on Add to cart
	
	driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
	
	Thread.sleep(1000);
	
	// Verify the amount after adding to cart
	
	String PriceAftAddCart = driver.findElement(By.xpath("//b[text()='Cart subtotal']/following::span[@id='attach-accessory-cart-subtotal']")).getText();
	
	if(PriceAftAddCart.contains("54,999"))
		
	{
		System.out.println("Price is validated successfully");
	}
	else
	{
		System.out.println("Price mismatch");
	}
	

	// Proceed to check out & Enter delivery address details.
	
	driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
    	
	

	
	
	
}
	
}

