package com.ap.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ap.ui.base.TestBase;

import junit.framework.Assert;

public class ProductDetailPage extends TestBase{
	
	@FindBy(id="quantity_wanted")
	WebElement quantityInput;
	
	@FindBy(css="[id='group_1']")
	WebElement sizeDropDown;
	
	@FindBy(css="[id='add_to_cart']")
	WebElement AddcartButton;
	
	@FindBy(css="[class='title='Proceed to checkout']")
	WebElement proceedCheckOut;
	
	@FindBy(css="[title='Add to my wishlist']")
	WebElement addToWishListButton;
	
	@FindBy(css="[class='fancybox-error']")
	WebElement addWishListMsg;
	
	@FindBy(css="[class='fancybox-item fancybox-close']")
	WebElement addWishListMsgCloseButton;
	
	

	public ProductDetailPage(){
		PageFactory.initElements(driver, this);
	}
	public void verifyAddwishListMsg(){
		Assert.assertEquals(addWishListMsg.getText(), "Added to your wishlist");
		addWishListMsgCloseButton.click();
	}
	public ProductDetailPage selectProductColor(String color){
		String locator = "[name='"+color+"']";
		driver.findElement(By.cssSelector(locator)).click();
		return this;
		// here u created a variable interacting with color coming from excel sheet accordingly select the color 
		// by using locator value and then click on it.
	}
	public ProductDetailPage inputQuantity(String quantity){
		quantityInput.clear();
		quantityInput.sendKeys(quantity);
		return this;
		
	}
	public ProductDetailPage selectSize(String size){
		Select select = new Select(sizeDropDown);
		select.selectByVisibleText(size);
		return this;
		
	}
	public ProductDetailPage clickAddCart(){
		AddcartButton.click();
		return this;
	}
	public ProductDetailPage clickAddWishListButton(){
		addToWishListButton.click();
		return this;
	}
	
	public OrderSummaryPage ProceedCheckOut(){
		proceedCheckOut.click();
		return new OrderSummaryPage();
	}
	
}










