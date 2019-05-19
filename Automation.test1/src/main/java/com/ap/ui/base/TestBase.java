package com.ap.ui.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ap.ui.util.TestUtil;
import com.ap.ui.util.WebEventListener;

public class TestBase {
	
	// setting up property value for the class we r working on
	public static WebDriver driver;
	public static Properties propt;
	public static EventFiringWebDriver en_driver;
	public static WebEventListener EventListener;
	
	public TestBase(){
		//we are using the try catch method to 
		// we using constructor here.
		// and blue print of the class
		try{
			propt = new Properties();
			// here getproperty u r absorbing the information in directory
			FileInputStream ipa = new FileInputStream(System.getProperty("user.dir")+ "/Automation.test1/src/main/java/com/ap/ui/config/config.properties");
			propt.load(ipa);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		//we created a method and u r saying that string vlaue utilities propt
		// string value of browser , this object holding this Browser
		// if browser equal to chrome open it
		
	}
	
	public static void intialization(){
		String browserName = propt.getProperty("browser");
		// here in webdriver we have to 
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FF")){
			System.setProperty("webdriver.gecko.driver", "provie file path of the driver");
			driver = new FirefoxDriver();
		}
		// we creating object here and holding the informaiton call the object quiker, instead calling the object mulitple time,
		//this is here for event listener
		// creating object for action that's occurring and sharing with driver
		en_driver = new EventFiringWebDriver(driver);
		// creating object of webeventlistener to register it with eventfiringwebdriver
		EventListener = new WebEventListener();
		//regester  giving error because we never work yet with webeventlistner. dealing with all the events 
		// event can be captured based on the method we create webeventlistener class
		en_driver.register(EventListener);
		// since we know driver object is for browser and  en_driver is for event thats taking place. we declaring 
		// that they are  equal to each when they are exchanging the info
		driver = en_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Page_Load, TimeUnit.SECONDS);
		
		driver.get(propt.getProperty("url"));
		
	}

}
