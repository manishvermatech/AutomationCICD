package Demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import junit.framework.Assert;

public class OrangeHRM {

	public String baseurl ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;
	@BeforeTest
	public void setup()
	
	{
		System.out.println("Before Test Execution");
		driver= new ChromeDriver();
		driver.get(baseurl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	
	@Test(priority=1)
	public void TestLoginwithInvalid() throws InterruptedException
	{
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();	
		
		String message_expected ="Invalid credentials";
		
		String message_actual= driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']']")).getText();
		
		Assert.assertEquals(message_expected, message_actual);
		Thread.sleep(1500);
	}
	
	@Test(priority=2)
	public void TestLoginwithValid()
	{
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		// Verify  whether login is successfully by checking the page title.
		
		String pageTitle= driver.getTitle();
		
		Assert.assertEquals("OrangeHRM", pageTitle);
		
	}
	
	@Test(priority=3)
	public void AddeEmployee() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Manish");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Verma");
		driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")).sendKeys("0");
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		Thread.sleep(2000);
		String pagename =driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
		Assert.assertEquals("Personal Details", pagename);
	}
	@Test (priority=4)
	public void searchemployeebyname()
	{
		
	}
	
	public void Logout()
	
	{
		
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
	    driver.findElement(By.xpath("(//a[normalize-space()='Logout'])[1]")).click();
		
	}
	
	@AfterTest
	public void Teardown() throws InterruptedException
	{
		Thread.sleep(4000); // Wait for 5 Seconds before quit.
		Logout();
		//driver.close();
		//driver.quit();
		
	}
	
	
	
}
