package com.ap.ui.ExtendReport.Listener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class ExtentReporterN implements IReporter{
	private ExtentReports extent;
	// impletmenting and creating constructor
	// amd making the object called extent 
	//we creating object we never want to share result privately
	
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite>suites, 
			String outputDirectory){
		// creating method and using list 
		// xml suite will create all pages for classes base of result we have 
		// saving as a string and shared with extendreport
		
		extent = new ExtentReports(outputDirectory + File.separator
				+ "Extent.html", true);
		// extentreports as a object extent to save all the output 
		//extent invoking the objet
		// file seperator need to how u will see the resultby running the test on multiple just sapreate it.
		// save the file name as Extend.html. becz its easy to open. the purpose of true boolean is to generate only true report not
		// error report. or if dont receive any result dont make the report.
	// two additional new line of code that I forget Earlier	
		
		for (ISuite suite : suites){
			// its conditional operator
			Map<String, ISuiteResult>result = suite.getResults();
			//obtaining a key value it cannot be duplicate and then it will map it to one location
			// map cannot containg duplicatge value. map is bascially a interface in java. map is interface in java creating one key value and unique
			// value. here unique value from the result and also sharing with the extentreport.  [LOGIN--pass     Checkout-fail]
			// map allow will go to pages of class like login pass. giving specificlly giving name to the login page and saving it seperately.
		
		for(ISuiteResult r : result.values()){
			ITestContext context =r.getTestContext();
			// using loop here to . context of the result 
			// context will be some time of result in extent report.
			// r is a readable value here 
			
			buildTestNodes(context.getPassedTests(), LogStatus.PASS);
			buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
			buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			//capturing the result.
			
		}
	}
	
	extent.flush();//basically adding the result u get and attaching to the html file u have.
	extent.close();	// basically as soon u done with the report closing it.	
	
}
		
private void buildTestNodes(IResultMap tests, LogStatus status){
	ExtentTest test;
	//basically its creating a private constructor calling it and name it as tests.
	
	if(tests.size()>0){
		for (ITestResult result : tests.getAllResults()){
			// It conditional  with in the test then execute 
			test = extent.startTest(result.getMethod().getMethodName());
			
			test.setStartedTime(getTime(result.getStartMillis()));
			test.setEndedTime(getTime(result.getEndMillis()));
			//how long report will take it. u have to specify how much time it take to done.
			
			for(String group : result.getMethod().getGroups())
				test.assignCategory(group);
			//we are organizing everything result and assign it to category wise.
			
			if(result.getThrowable() !=null){
				test.log(status, result.getThrowable());
				//if there is error throw null.
			}else{
				test.log(status, "Test" + status.toString(). toLowerCase() + "ed");
				// ed if bascially failed 
				
			}
			extent.endTest(test);
					
	}
			
}		
}
private Date getTime(long millis){
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(millis);
	return calendar.getTime();
	//private constructor allow 
	
}

}