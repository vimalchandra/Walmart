package walmart_exercise;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
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
	public static void LaunchApplication() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver.exe");
		driver = new ChromeDriver();
		waitimplicitly(2);
		driver.navigate().to("https://www.walmart.com/account/login?returnUrl=%2Faccount%2F");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	/**
	 * @author Vimal
	   Method for logging into the account
	 */
	public static void Login(String username, String password) throws InterruptedException
	{
		WebElement login_textbox = driver.findElement(By.id("login-username"));
		login_textbox.sendKeys(username);
		WebElement passwd_textbox = driver.findElement(By.id("login-password"));
		passwd_textbox.sendKeys(password);
		WebElement signin_button = driver.findElement(By.xpath("html/body/div[2]/section/section[4]/div/div/div/div/div/div/div/form/div/button"));
		signin_button.click();
		Thread.sleep(3000);
	}
	/**
	 * @author Vimal
	   Method for searching requested item 
	 */
	public static void Search(String term) throws InterruptedException
	{
		WebElement search_textbox = driver.findElement(By.id("search"));
		search_textbox.sendKeys(term);
		Thread.sleep(4000);
		WebElement search_button = driver.findElement(By.xpath(".//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[3]/button"));
		waitimplicitly(2);
		search_button.click();
		Thread.sleep(4000);
		
	}
	/**
	 * @author Vimal
	   Method for searching requested item 
	 */
	public static String SelectItem(String title) throws InterruptedException
	{
		WebElement listview = driver.findElement(By.xpath(".//*[@id='utility-bar-container']/div/div[2]/a[2]"));
		listview.click();
		Thread.sleep(3000);
		List<WebElement> list_values = driver.findElements(By.xpath(".//*[@id='tile-container']/div[*]"));
		//System.out.println(drop.size()+" "+drop.isEmpty());
		String value=null;
		for(WebElement item:list_values)
		{	
			//System.out.println(item.getAttribute("innerHTML"));
			if(item.findElement(By.xpath("./div/div/h4/a")).getAttribute("innerHTML").contains(title))
			{
				value=item.getAttribute("data-item-id");
				item.findElement(By.xpath("./div/div/h4/a")).click();
				return value;
			}	
		}
		return value;
	}
	/**
	 * @author Vimal
	   Method for selecting requested number of items 
	 */
	public static void SelectItemQuantity(String visibletext)
	{
		 WebElement quantity_dropdown = driver.findElement(By.id("WMItemQtyDropDown"));
		 Select quant = new Select(quantity_dropdown);
		 quant.selectByVisibleText(visibletext);
		 
	}
	/**
	 * @author Vimal
	   Method for adding items to the cart 
	 */
	public static void AddItemstoCart() throws InterruptedException
	{
		WebElement addcart_button = driver.findElement(By.id("WMItemAddToCartBtn"));
		
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
	public static void ViewCartItems() throws InterruptedException
	{
		WebElement viewcart_button = driver.findElement(By.id("PACViewCartBtn"));
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
