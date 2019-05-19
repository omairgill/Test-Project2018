package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.HomePage;
import com.ap.ui.pages.LoginPageOR;

public class LoginTest extends TestBase {
	
	
	LoginPageOR loginPage;//object repository page
	HomePage homePage;
	
	public LoginTest(){
		super();// read about super concept.
		
	}
	
	@BeforeMethod
	public void setUpdriver(){
		intialization();
		loginPage = new LoginPageOR();
		
	}
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.verifyPageTitle();
		Assert.assertEquals(title, "Later will check on the site");
		// testing the title of the page.
		// assert is a function in testNG to verify the value is true or false
	}
	@Test(priority=2)
	public void apLogoTest(){
		boolean value = loginPage.validateAPImage();
		Assert.assertTrue(value);
	}
		
	
	@Test(priority=3)
	public void loginTest(){
		homePage = loginPage.login(propt.getProperty("username"), propt.getProperty("password"));
	}
	// method to getting the value from property object. propt. is a property object we created in TestBase.
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
		// method closing the Browser.
	}
	
}

