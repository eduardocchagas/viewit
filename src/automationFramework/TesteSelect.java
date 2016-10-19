package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteSelect {
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://blazedemo.com");
		driver.manage().window().maximize();
		Select from = new Select(driver.findElement(By.xpath("/html/body/div[3]/form/select[1]")));
		from.selectByVisibleText("Boston");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
