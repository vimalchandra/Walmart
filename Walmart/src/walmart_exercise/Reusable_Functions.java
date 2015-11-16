package walmart_exercise;


import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * @author Vimal
   This class consists of all reusable functions 
 */

public class Reusable_Functions {
	
	public static WebDriver driver;
	
	public static void waitimplicitly(long waittime)
	{
		driver.manage().timeouts().implicitlyWait(waittime, TimeUnit.SECONDS);
	}
	
	/**
	 * @author Vimal
	   Method for launching the requested Web page
	 */
	
	public static void LaunchApplication(String browserName, String URL) throws InterruptedException
	{
		if(browserName.equalsIgnoreCase("googlechrome"))
		{
		System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver.exe");
		driver = new ChromeDriver();
		waitimplicitly(2);		
		}
		else if(browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\iexplore.exe");
			driver = new ChromeDriver();
			waitimplicitly(2);		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			waitimplicitly(2);
		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();	
			waitimplicitly(2);
		}
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		Thread.sleep(3000);			
		
	}
	
	/**
	 * @author Vimal
	   Method for logging into the account
	 */
	
	public static void Login(Properties objects,String username, String password) throws InterruptedException
	{
		WebElement login_textbox = driver.findElement(By.id(objects.getProperty("username")));
		login_textbox.sendKeys(username);
		WebElement passwd_textbox = driver.findElement(By.id(objects.getProperty("password")));
		passwd_textbox.sendKeys(password);
		WebElement signin_button = driver.findElement(By.xpath(objects.getProperty("signin_button")));
		signin_button.click();
		Thread.sleep(3000);
	}
	
	/**
	 * @author Vimal
	   Method for searching requested item 
	 */
	
	public static void SearchItem(Properties objects,String item) throws InterruptedException
	{
		WebElement search_textbox = driver.findElement(By.id(objects.getProperty("search_textbox")));
		search_textbox.sendKeys(item);
		Thread.sleep(4000);
		WebElement search_button = driver.findElement(By.xpath(objects.getProperty("search_button")));
		waitimplicitly(2);
		search_button.click();
		Thread.sleep(4000);		
	}
	
	/**
	 * @author Vimal
	   Method for searching requested item 
	 */
	
	public static String SelectItem(Properties objects,String ItemName) throws InterruptedException
	{
		WebElement listview = driver.findElement(By.xpath(objects.getProperty("listview")));
		listview.click();
		Thread.sleep(3000);
		List<WebElement> list_values = driver.findElements(By.xpath(objects.getProperty("list_values")));
		String value=null;
		for(WebElement item:list_values)
		{	
			if(item.findElement(By.xpath(objects.getProperty("list_item"))).getAttribute("innerHTML").contains(ItemName))
			{
				value=item.getAttribute("data-item-id");
				item.findElement(By.xpath(objects.getProperty("list_item"))).click();
				return value;
			}	
		}
		return value;
	}
	
	/**
	 * @author Vimal
	   Method for selecting requested number of items 
	 */
	
	public static void SelectItemQuantity(Properties objects,String visibletext)
	{
		 WebElement quantity_dropdown = driver.findElement(By.id(objects.getProperty("quantity_dropdown")));
		 Select quant = new Select(quantity_dropdown);
		 quant.selectByVisibleText(visibletext);		 
	}
	
	/**
	 * @author Vimal
	   Method for adding items to the cart 
	 */
	
	public static void AddItemstoCart(Properties objects) throws InterruptedException
	{
		WebElement addcart_button = driver.findElement(By.id(objects.getProperty("addcart_button")));
		
		if(addcart_button.isDisplayed()==true)
		{
			addcart_button.click();
		}
		else
		{
			System.out.println("out of stock");
		}
		Thread.sleep(3000);
	}
	
	/**
	 * @author Vimal
	   Method for viewing items in the cart 
	 */
	
	public static void ViewCartItems(Properties objects) throws InterruptedException
	{
		WebElement viewcart_button = driver.findElement(By.id(objects.getProperty("viewcart_button")));
		viewcart_button.click();
		Thread.sleep(2000);
	}
	
	/**
	 * @author Vimal
	   Method for validating the items in the cart 
	 */
	
	public static void ValidateCartItem(WebElement z,String value,WebElement z1,String itemsincart)
	{
		String valuepresent = String.valueOf(z1.getAttribute("innerHTML"));
		String CartItemId= String.valueOf(z.getAttribute("data-us-item-id"));
		if(CartItemId.equals(value)&& valuepresent.equals(itemsincart))
		{
			System.out.println("Verification Successful - The item is in the cart");
		}
		else
		{
			System.out.println("Verification UnSuccessful - The item is not in the cart");
		}
	}
	
	
}
