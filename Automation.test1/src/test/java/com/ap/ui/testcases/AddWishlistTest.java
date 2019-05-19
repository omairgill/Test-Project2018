package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.HomePage;
import com.ap.ui.pages.LoginPageOR;
import com.ap.ui.pages.ProductDetailPage;
import com.ap.ui.pages.SearchPage;

public class AddWishlistTest extends TestBase  {
	
	LoginPageOR loginPage;
	HomePage homePage;
	SearchPage searchPage;
	ProductDetailPage productDetailPage;

public AddWishlistTest(){
	super();
	
}
@BeforeMethod
public void setUpdriver(){
	intialization();
	loginPage = new LoginPageOR();
	homePage = new HomePage();

}
@Test
public void testAddPrdouctToWishList(){
	String product = "Printed Chiffon Dress";
	
	homePage.clickonSignIn();
	homePage = loginPage.login(propt.getProperty("username"), propt.getProperty("password"));
	// search product
	searchPage= homePage.searchProduct(product);
	String header = searchPage.getHeader();
	Assert.assertTrue(header.toLowerCase().contains(product.toLowerCase()));
	// add product to the wishlist
	productDetailPage.clickAddWishListButton();
	productDetailPage.verifyAddwishListMsg();
	homePage.logOut();
}
@AfterMethod 
public void tearDown(){
	
	driver.quit();
	
}
}