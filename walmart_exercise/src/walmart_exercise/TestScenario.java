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
		LaunchApplication("GoogleChrome");
		Login(objects,"vimalchandra.gorijala@sjsu.edu","sanjose");
		SearchItem(objects,"socks");
		String productId=SelectItem(objects,Item_Input);
		Thread.sleep(3000);
		SelectItemQuantity(objects,Quantity);
		Thread.sleep(3000);
		AddItemstoCart(objects);
		ViewCartItems(objects);
		WebElement viewcartiteminfo = driver.findElement(By.id(objects.getProperty("viewcartiteminfo")));
		WebElement viewcartitemquantity = driver.findElement(By.xpath(objects.getProperty("viewcartitemquantity")));
		ValidateCartItem(objects,viewcartiteminfo,productId,viewcartitemquantity,Quantity);
	}
}
