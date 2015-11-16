package walmart_exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestScenario extends Reusable_Functions {
	public static void main(String args[]) throws InterruptedException
	{
		String Quantity ="1";
		String Item_Input ="Apple <mark>iPhone</mark> 5C 8GB Verizon (Locked)";
		LaunchApplication();
		Login("vimalchandra.gorijala@sjsu.edu","sanjose");
		Search("iphone");
		String productId=SelectItem(Item_Input);
		System.out.println("Product Id:"+productId);
		Thread.sleep(3000);
		SelectItemQuantity(Quantity);
		Thread.sleep(3000);
		AddItemstoCart();
		ViewCartItems();
		WebElement ViewCartItemInfo = driver.findElement(By.id("CartItemInfo"));
		WebElement ViewCartItemQuantity= driver.findElement(By.xpath(".//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div/div/div[4]/div/div[1]/div/div/div/div[1]/div/div"));
		System.out.println(ViewCartItemInfo.getAttribute("data-us-item-id"));
		System.out.println(ViewCartItemQuantity.getAttribute("innerHTML"));
		ValidateCartItem(ViewCartItemInfo,productId,ViewCartItemQuantity,Quantity);

	}


}
