package EntrataTests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
public class BaseTest
{
	public WebDriverWait wait;
	public WebDriver driver;
	@BeforeTest
    public void testSetUp()  {
     
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
        driver = new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
 
        driver.manage().window().maximize();
        driver.get("https://www.entrata.com//");
       
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //to decline cookies
       WebElement e= driver.findElement(By.className("text-block-40"));
       e.click();
       
    }


	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}

//	To check if the first row of footer contains only 11 links.
	@Test
public void login() throws IOException
{
	
	JavascriptExecutor js= (JavascriptExecutor)driver;
	//Scrolling to the footer
	js.executeScript("window.scrollBy(0,10700)"," ");
	WebElement footer1=driver.findElement(By.id("w-node-_44a56412-771a-947b-e261-6f13b2895391-bfc62253"));
	Assert.assertSame((footer1.findElements(By.tagName("a")).size()), 11);
	System.out.println(footer1.findElements(By.tagName("a")).size());
}

//Test to open demo page and fill the form without submitting it along with 
//checking the input is correct or not
	@Test
   public void reqDemo() throws InterruptedException
{
		driver.navigate().to("https://go.entrata.com/watch-demo.html");
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://go.entrata.com/watch-demo.html");
		//to get title
 		String actualTitle = driver.getTitle();
		//to print the title of page
		System.out.println("title is-----"+actualTitle);
		String expectedTitle="Entrata | Optimize Property Management with One Platform";
		//comparing actual title and expected title
		Assert.assertEquals(actualTitle,expectedTitle, "Matched");
		
		
 		//to fill the form send text to first name
 		WebElement FirstName = driver.findElement(By.id("FirstName"));
 		
 		Assert.assertTrue(FirstName.isDisplayed());  
 		 FirstName.isDisplayed();
 		 FirstName.isEnabled();
 		
 		FirstName.sendKeys("Payal");
 		 
    	 WebElement LastName = driver.findElement(By.id("LastName"));
    	 LastName.isDisplayed();
    	 LastName.isEnabled();
 
    	 Assert.assertTrue(LastName.isDisplayed());  
    	 LastName.sendKeys("Pandey");
    	 
    	 //to validate the wrong emailid message
    	  WebElement EmailId = driver.findElement(By.id("Email"));
          EmailId.sendKeys("payalpandey73");
          LastName.click();
          EmailId.click();
        
          Thread.sleep(500);
          WebElement WrongEmailIdMessage = driver.findElement(By.xpath("//div[@id='ValidMsgEmail']"));
          WrongEmailIdMessage.isEnabled();
          Assert.assertTrue(WrongEmailIdMessage.isDisplayed());
          EmailId.clear();
          
        
         WebElement EmailId1 = driver.findElement(By.id("Email"));
         EmailId1.sendKeys("payalpandey73@gmail.com");
       
         
         WebElement CompanyName = driver.findElement(By.id("Company"));
         CompanyName.sendKeys("entrata");
         
         //validate wrong phone number
         WebElement PhoneNumber = driver.findElement(By.id("Phone"));
         PhoneNumber.sendKeys("abcdef");
         LastName.click();
         PhoneNumber.click();
       
         Thread.sleep(500);
         WebElement WrongPhoneNumber = driver.findElement(By.xpath("//div[text()='Must be a phone number. ']"));
         WrongPhoneNumber.isEnabled();
         Assert.assertTrue(WrongPhoneNumber.isDisplayed());
         PhoneNumber.clear();
         
         WebElement PhoneNumber1 = driver.findElement(By.id("Phone"));
         PhoneNumber1.sendKeys("8073855642");
         Thread.sleep(500);
         
         Select sell=new Select(driver.findElement(By.id("Unit_Count__c")));
         sell.selectByIndex(3);
         
         WebElement JobTitle = driver.findElement(By.id("Title"));
         JobTitle.sendKeys("Software Tester");
         
         Select sell2=new Select(driver.findElement(By.id("demoRequest")));
         sell2.selectByValue("a Resident");
         
         Thread.sleep(1000);
		
   }
	
//Test to verify the title of the page
	@Test(priority = 1)
	public void verifyTitle()  {

		//to get the title of page
		String actualTitle = driver.getTitle();

		//to print the title of page
		System.out.println("title is-----"+actualTitle);

		//assign the value of expected title
		String expectedTitle="Entrata | Optimize Property Management with One Platform";
		//statement to print the expected title
		System.out.println("expectedtitle...."+expectedTitle);

		//comparing actual title and expected title
		Assert.assertEquals(actualTitle, expectedTitle);


	}

	//Test to verify the logo of page
	@Test
	public void verifyLogo() {

		boolean flag =driver.findElement(By.xpath("//a[@class='brand w-nav-brand']")).isDisplayed();
		Assert.assertTrue(flag);
		
	}

//Test to check the hover over the Products tab	
	@Test
	public void mouseHover() throws InterruptedException {

		WebElement Products= driver.findElement(By.xpath("//div[text()='Products']"));

		//Creating object of an Actions class
		Actions action = new Actions(driver);

		//Performing the mouse hover action on the target element.
		action.moveToElement(Products).perform();
		Thread.sleep(200);
		// Assertion to check if the element is displayed after hover
		Assert.assertTrue(Products.isDisplayed(), "Products menu is not displayed after hover");
	}

//To close the driver	
@AfterTest
public void flush()
{
	driver.close();
}



}

	