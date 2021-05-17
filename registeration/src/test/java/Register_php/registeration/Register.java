package Register_php.registeration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Register {
	WebDriver driver;
	  @Test(dataProvider="testdata")
	  public void registerclass(String firstname,String lastname,String phone,String mail,String password,String confirmpassword){
		
		 driver.findElement(By.linkText("MY ACCOUNT")).click();
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);  
		  driver.findElement(By.linkText("Sign Up")).click();
		  driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		  driver.findElement(By.name("firstname")).sendKeys(firstname);
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  driver.findElement(By.name("lastname")).sendKeys(lastname);
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  driver.findElement(By.name("phone")).sendKeys(phone);
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  driver.findElement(By.name("email")).sendKeys(mail);
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  driver.findElement(By.name("password")).sendKeys(password);
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  driver.findElement(By.name("confirmpassword")).sendKeys(confirmpassword);
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 WebElement submit_btn= driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[8]/button"));
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  JavascriptExecutor java=( JavascriptExecutor )driver;
          java.executeScript("scroll(0,250)");
          driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
          submit_btn.click();
          WebDriverWait wait=new WebDriverWait(driver,20);
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div[1]/div/div/div[1]/div/div[2]/h3")));
          String welcomemessage=driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div/div[1]/div/div[2]/h3")).getText();
          Assert.assertEquals(welcomemessage, "Hi, "+firstname+" "+lastname);
          driver.findElement(By.linkText(firstname.toUpperCase())).click();
          
	  }
	 
	  @BeforeClass
	  public void openbrowser()
	  {
		  System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver.exe")  ;
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
		  driver.manage().window().maximize();   
		  driver.get("https://phptravels.net/");
	  }
	  
	  @AfterClass
	  public void closebrowser()
	  {
		//  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 
		//  driver.close();
	  }
	  @DataProvider
		 public Object [][] testdata() throws InvalidFormatException, IOException {
		  read_excel obj=new read_excel();
		      return obj.readsheet();
		  }
	  
	
}
