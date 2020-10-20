package com.zingpoll;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import driverFactory.DriverManager;
import io.qameta.allure.Description;
import pageObjects.zingpoll.HomePageObject;
import pageObjects.zingpoll.MailinatorPageObject;
import pageObjects.zingpoll.ManagePollPageObject;
import pageObjects.zingpoll.PageGeneratorManager;
import pageObjects.zingpoll.PollDeletePageObject;
import pageObjects.zingpoll.ResultPageObject;
import pageObjects.zingpoll.UpdatePollPageObject;
import pageObjects.zingpoll.VotingPageObject;

public class TS04_Settings_Poll_By_Anonymous_User extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	HomePageObject homePage;
	VotingPageObject votingPage;
	MailinatorPageObject mailinatorPage;
	ManagePollPageObject managePollPage;
	UpdatePollPageObject updatePollPage;
	PollDeletePageObject pollDeletePage;
	ResultPageObject resultPage;

	String pollPassword="123456";
	String pollUrl="";

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition: Launch app");
		driver = getBrowserDriver(browserName,GlobalConstants.URL);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		homePage.waitForJStoLoad(driver);
	}
	
	@Parameters({ "browser" })
	@Test
	public void TC_04_TC15_Create_Poll_With_Bunch_Of_Answer_Options_And_Set_Password(String browserName) {
		log.info("Pre-condition: Click ZingPoll icon");
		homePage.clickToZingPollIcon();
		
		log.info("Step 1: Input question content");
		homePage.inputToQuestionContentTextbox(GlobalConstants.NUMBER_QUESTION_CONTENT);

		log.info("Step 2: Input 20 answer options");
		int optionsNumber = 20;
		for (int i = 1; i <= optionsNumber; i++) {
			if (i < 3) {
				homePage.inputToOption1Textbox(String.valueOf(randomNumber()));
				homePage.inputToOption2Textbox(String.valueOf(randomNumber()));
			} else if (i >= 3) {
				homePage.inputToDynamicNewOptionTextbox(String.valueOf(randomNumber()));
			}
		}

		log.info("Step 3: Input email");
		homePage.inputToEmailTextbox(GlobalConstants.ANONYMOUS_EMAIL);
		
		log.info("Step: Click Poll Settings");
		homePage.clickToPollSettingsButton();

		log.info("Step: Select 'Set Password' and input data to password textbox");
		verifyFalse(homePage.isPollPasswordTextboxEnabled());
		homePage.selectSetPasswordCheckbox();
		verifyTrue(homePage.isPollPasswordTextboxEnabled());
		homePage.inputToPollPasswordTextbox(pollPassword);

		log.info("Step: Click Create Poll button and navigate to Voting page");
		votingPage = homePage.clickToCreatePollButton();

//		log.info("VP: Create Poll Successfully Msg displays and get poll url  ");
//		verifyTrue(votingPage.isCreatePollSuccessMessageDisplayed());
//		pollUrl = votingPage.getPollUrl();
//		driver.quit();
//		
//		log.info("Step: Open Voting Poll page in the other session");
//		driver = getBrowserDriver(browserName,GlobalConstants.URL + pollUrl);
//		votingPage = PageGeneratorManager.getVotingPageObject(driver);
//		votingPage.waitForJStoLoad(driver);
//		
		log.info("VP: Require Password Alert message displays");
		verifyTrue(votingPage.isRequirePasswordAlertMessageDisplayed());
		
		log.info("Step: Input Poll Password ");
		votingPage.inputToPollPasswordTextbox(pollPassword);

		log.info("Step: Select an answer option and click Vote button");
		votingPage.selectAnswerOptionRadiobutton(0);
		votingPage.clickToVoteButton();
		resultPage = PageGeneratorManager.getResultPageObject(driver);
		log.info("VP: msg 'Your answer(s) have been submitted.' displays");
		verifyTrue(resultPage.isVotingPollSuccessMessageDisplayed());
			
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
