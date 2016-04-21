package walmart_exercise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestScenario extends Reusable_Functions {
	public static void main(String args[]) throws InterruptedException, FileNotFoundException, IOException
	{
		Properties objects=new Properties();
		objects.load(new FileInputStream("ObjectRepository/objects.properties"));
		String Quantity ="1";
		String Item_Input ="Hanes Men's 6 Pack  Crew";
		
		/*
		   Launches Chrome browser with given URL 
		 */
		
		LaunchApplication("GoogleChrome","https://www.walmart.com/account/login?returnUrl=%2Faccount%2F");
		
		/*
		   Logs into the account with given credentials
		 */
		
		Login(objects,"vimalchandra.gorijala@sjsu.edu","sanjose");
		
		/*
		   Searches the item given and displays the results 
		 */
		
		SearchItem(objects,"socks");
		
		/*
		   Loads the item page of given input item and collects 'data-item-id' of it
		 */
		String productId=SelectItem(objects,Item_Input);
		Thread.sleep(3000);
		
		/*
		   Selects the quantity of the given item
		 */
		
		SelectItemQuantity(objects,Quantity);
		Thread.sleep(3000);
		
		/*
		   Adds the item to the cart
		 */
		
		AddItemstoCart(objects);
		
		/*
		   Views the items in the cart
		 */
		
		ViewCartItems(objects);
		
		/*
		   Validates the item in the cart
		 */
		
		WebElement viewcartiteminfo = driver.findElement(By.id(objects.getProperty("viewcartiteminfo")));
		WebElement viewcartitemquantity = driver.findElement(By.xpath(objects.getProperty("viewcartitemquantity")));
		ValidateCartItem(viewcartiteminfo,productId,viewcartitemquantity,Quantity);
		driver.quit();
	}
}
