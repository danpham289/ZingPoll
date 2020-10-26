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

public class Anonymous_User_TS03_Poll_Settings extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	HomePageObject homePage;
	VotingPageObject votingPage;
	MailinatorPageObject mailinatorPage;
	ManagePollPageObject managePollPage;
	UpdatePollPageObject updatePollPage;
	PollDeletePageObject pollDeletePage;
	ResultPageObject resultPage;

	String pollURL = "";
	int votingLimitNumber = 2;
	String managePollPassword;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.URL);
		log.info("Pre-condition: Launch app");
		homePage = PageGeneratorManager.getHomePageObject(driver);
		homePage.waitForJStoLoad(driver);
	}
	
	@Parameters({ "browser" })
	@Description()
	@Test
	public void TC08_TC13_Poll_Settings(String browserName) {
		log.info("Step: Input full valid data to create poll");
		homePage.inputToQuestionContentTextbox(GlobalConstants.COUNTRY_QUESTION_CONTENT);
		homePage.inputToOption1Textbox(GlobalConstants.OPTION1);
		homePage.inputToOption2Textbox(GlobalConstants.OPTION2);
		homePage.inputToEmailTextbox(GlobalConstants.ANONYMOUS_EMAIL);

		log.info("Step: Click Poll Settings");
		homePage.clickToPollSettingsButton();

		log.info("Step: Optimize Poll_Link");
		pollURL = String.valueOf(randomNumber()).substring(1);
		homePage.inputToPollURLTextbox(pollURL);

		log.info("Step: Select 'Set voting limit' and input limit vote number");
		verifyFalse(homePage.isLimitVoteNumberTextboxEnabled());
		homePage.selectSetVotingLimitCheckbox();
		verifyTrue(homePage.isLimitVoteNumberTextboxEnabled());
		homePage.inputToLimitVoteNumberTextbox(String.valueOf(votingLimitNumber));

		log.info("Step: Click Create Poll button and navigate to Voting page");
		votingPage = homePage.clickToCreatePollButton();

		log.info("VP: The Poll Link after created equals the optimized link ");
		verifyEquals(votingPage.getPollUrl(), pollURL);
		driver.quit();

		log.info("Step: Open Voting Poll page in the other session and Select one answer option then Click Vote button - Repeat this step 'votingLimitNumber' times");
		for (int i = 0; i < votingLimitNumber; i++) {	
			driver = getBrowserDriver(browserName,GlobalConstants.URL + pollURL);
			votingPage = PageGeneratorManager.getVotingPageObject(driver);
			votingPage.waitForJStoLoad(driver);
			votingPage.selectAnswerOptionRadiobutton(0);
			votingPage.clickToVoteButton();
			resultPage = PageGeneratorManager.getResultPageObject(driver);
			log.info("VP: msg 'Your answer(s) have been submitted.' displaysin Result page");
			verifyTrue(resultPage.isVotingPollSuccessMessageDisplayed());
			
			if(i==(votingLimitNumber-1)) {
				log.info("VP: msg ' This poll reached its vote limit.' displays after reaching vote limit in Result page");
				verifyTrue(resultPage.isPollReachVotingLimitDisplayed());
			}
			driver.quit();
		}
		
		log.info("Step: Open Voting Poll page in the other session for the 'votingLimitNumber' + 1 times");
		driver = getBrowserDriver(browserName,GlobalConstants.URL + pollURL);
		votingPage = PageGeneratorManager.getVotingPageObject(driver);
		
		log.info("VP: msg ' This poll reached its vote limit.' displays after reaching vote limit in Voting page ");
		verifyTrue(votingPage.isPollReachVotingLimitDisplayed());
		
		log.info("VP: Vote button does not displays after reaching vote limit in Voting page ");
		verifyTrue(votingPage.isVoteButtonUndisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
