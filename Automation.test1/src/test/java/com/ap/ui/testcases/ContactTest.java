package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.ContactsPage;
import com.ap.ui.pages.HomePage;

public class ContactTest extends TestBase {
	
	ContactsPage contactspage;
	HomePage homePage;
	// making connection between home page and contact page
	public ContactTest(){
		super();
		
	}
	
	@BeforeMethod
	public void setUpdriver(){
		intialization();
		contactspage = new ContactsPage();
		homePage = new HomePage();
	// intinalization the driver for every new browser	
	}
	
	@Test
	public void testContact(){
		homePage.clickOnContactLink();
		contactspage = contactspage.fillContactForm("Customer service", "qwe@abc.com",
				"Testing", "This is test purpose");
		contactspage.submitMessage();
		String successMsg = contactspage.getMessage();
		Assert.assertEquals(successMsg, "Your message has been successfully sent to our team");
		// created method to check the success of our order.
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
