package com.ap.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ap.ui.base.TestBase;

public class OrderSummaryPage extends TestBase{
	
	@FindBy(css = "[class='cart_navigation clearfix'][title='Proceed to checkout']")
	WebElement proceedCheckOutButton;
	
	@FindBy(css = "[name='processAddress']")
	WebElement processAddressButton;
	
	@FindBy(css = "[name='processCarrier']")
	WebElement processCarrierButton;
	
	@FindBy(id = "cgv")
	WebElement termAndConditionCheckBox;
	
	@FindBy(css = "[class='payment_module'][title='Pay by bank wire']")
	WebElement payByBankWire;
	
	@FindBy(css = "[id='cart_navigation'] button")
	WebElement confirmOrder;
	
	@FindBy(css = "[class='cheque-indent'][class='dark']")
	WebElement orderConfirm;
	

	public OrderSummaryPage(){
		PageFactory.initElements(driver, this);
	}
	
	public OrderSummaryPage proceedCheckOut(){
		proceedCheckOutButton.click();
		return this;
	}
	
	public OrderSummaryPage proceedAddressCheckOut(){
		processAddressButton.click();
		return this;
	}

	public OrderSummaryPage proceedShipping(){
		termAndConditionCheckBox.click();
		processCarrierButton.click();
		return this;
	
	}	
	public OrderSummaryPage confirmOrder(){
		payByBankWire.click();
		confirmOrder.click();
		return this;
		
	}
	public String getConfirmationMessage(){
		return orderConfirm.getText();
	}
	
	
	
	
	
	
	
	
	
	
	
}
