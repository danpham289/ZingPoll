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

public class Users_TS01_Sign_Up extends AbstractTest {
	
	private WebDriver driver;
	HomePageObject homePage;
	MailinatorPageObject mailinatorPage;	

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.URL);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
	}

	@Test
	public void TC01_Sign_Up_1() throws InterruptedException{
		homePage.waitForJStoLoad(driver);
		//click Sign In menu
		homePage.clickToSignInMenu();
		
		//Verify Sign Up popup display
		Assert.assertTrue(homePage.isSignInPopupDislayed());
		
		//check New User radio button
		homePage.checkNewUserRadioButton();
		
		//Input valid data into the fields
		homePage.inputToFullNameTextbox(GlobalConstants.FULLNAME);
		homePage.inputToRegisterEmailTextbox(GlobalConstants.REGISTER_EMAIL);
		homePage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
		homePage.inputToReenterPasswordTextbox(GlobalConstants.PASSWORD);
		
		//Select Agree Terms checkbox
		homePage.checkAgreecheckbox();
		
		//Click Register button
		homePage.clickToRegisterButton();
		
		//Verify "An activation link has sent to your mailbox." Message displays then undisplayed
		verifyTrue(homePage.isActivationLinkMessageDisplayed());
		verifyTrue(homePage.isActivationLinkMessageUnDisplayed());
		
		//driver.close();
		
		//Open Mailinator.com
		driver.get(GlobalConstants.EMAIL_URL);
		mailinatorPage = PageGeneratorManager.getMailinatorPageObject(driver);
		
		
		//Input email into mail textbox
		mailinatorPage.inputToMailTextbox(GlobalConstants.REGISTER_EMAIL);
		
		// Click Go button
		mailinatorPage.clickToGoButton();
		
		// Verify Activate email display
		verifyTrue(mailinatorPage.isActivateEmailDisplayed());
		
		
		//click to Activation email
		mailinatorPage.clickToActivationMailLink();
		
		//click to Acivate Now buton in email then navigate to Zingpoll page
		homePage = mailinatorPage.clickToActivateNowButton();
		
		// Verify activate account successfully message displays and new-account is auto-logged in
		verifyTrue(homePage.isActivateAccountSuccessMessageDisplayed());		
		verifyTrue(homePage.isFullNameDisplayed(GlobalConstants.FULLNAME));		
				
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
