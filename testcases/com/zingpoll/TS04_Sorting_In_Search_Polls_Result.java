package com.zingpoll;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.zingpoll.HomePageObject;
import pageObjects.zingpoll.MailinatorPageObject;
import pageObjects.zingpoll.PageGeneratorManager;

public class TS04_Sorting_In_Search_Polls_Result extends AbstractTest {
	
	private WebDriver driver;
	HomePageObject homePage;
	MailinatorPageObject mailinatorPage;	

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.URL);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		homePage.waitForJStoLoad(driver);
		
	}

	@Test
	public void TC01_Sorting_In_Search_Poll_Result() throws InterruptedException{
		log.info("Step: Input text search into Search textbox then click Search icon");
		homePage.inputToPollSearchTextbox("test");	
		homePage.clickToSearchIcon();	
		
		log.info("Step: The polls are sorted descending by the created date ");
		verifyTrue(homePage.isDateCreatedPollSortedByDescending());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
//		closeBrowserAndDriver(driver);
	}

}
