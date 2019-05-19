package com.ap.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ap.ui.base.TestBase;
// extends to testbase
public class TestUtil extends TestBase {
	
	//util allow us to read all information for excel, basically read information for website from excel sheet
	public static long Page_Load = 10;
	public static long Implicit_Wait = 10;
	
	public static String XL_SHEET_PATH = "path of the xl sheet";
	
	static Workbook book;
	// first one is work book
	static Sheet sheet;
	// the sheet we have on workbook
	static JavascriptExecutor js;
	//we created the object
	

	public static Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		// if sheet not have a value dont perform the action we created the method here
		try{
			file = new FileInputStream(XL_SHEET_PATH);
			// we put try catch block we do come cross the error we get with the help of try catch method first doin gon sheet level
			// then doing on workbook level
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		try{
			book = WorkbookFactory.create(file);
		}catch (InvalidFormatException e){
			e.printStackTrace();
			
		}catch (IOException e){
			e.printStackTrace();
			
		}
		// basically written method to going throw the  information we have on excel sheet./
		// it can be modified according to the changes its array we want to know how many row and columns we have in it
		// i am treating everything as a string
		sheet = book.getSheet(sheetName);
		Object [][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for  (int i = 0; i< sheet.getLastRowNum(); i++){
			for (int j = 0; j <sheet.getRow(0).getLastCellNum(); j++){
				data [i][j]=sheet.getRow(i + 1).getCell(j).toString();			
		}	
	}
	return data;
	
	// going to take screenshot and going to save in the project aswell not on desktop.
	}	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		// after taking screenshot saving in directory
		FileUtils.copyFile (srcFile, new File(currentDir + "/screenshot/" + System.currentTimeMillis() + ".png"));
		// after savaing file giving the name as a 'screenshot' and after that saving with time its taking and saving as a extention
				// .png "user dir mean our project" 
		// it will create a new file under our project of screenshot.
		
		
	}

	// catpuring everything during happening on the project either its fail or pass. o the console./
	//want to know where error have it for develope to fix it.
	// because window application using so much html, scc, xml, everything come under javascript.
	public static void runTimeInfo(String messageType, String message) throws InterruptedException{
		js = (JavascriptExecutor) driver;
		// var jquery is a machine to save that information. jquery will translate it to make it readable
		//we need the information from responsible body aswell.
		js.executeScript("if (!window.JQuery){"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https:ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery;" + "}"); 	
		Thread.sleep(6000);
		// use jQuery to add jquery-growl to the apge
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		//getscript is just getting the information
		// use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(6000);
		// java script and j query always start with dollar sign.
		// creating log file good to create for deverloper to help usefull.
		js.executeScript("$growl( {title: 'GET' , message: '/'}, arg1);");
		// use jQuery to add jquery-growl styles to the page
		if(messageType.equals("error")){
			js.executeScript("$growl.error({title: 'ERROR' message: '"+message+"'});");
		}else if(messageType.equals("info")){
			js.executeScript("$growl.error({ title: 'Notice' message: 'ntoice message will appear here' });");
			
		}else if(messageType.equals("warning")){
			js.executeScript("$growl.warning({ title: 'Warning!!' message: 'warning message will appear here' });");
		
		}else
			System.out.println("Show No Error Message");
		Thread.sleep(6000);
	}	
	
}






























