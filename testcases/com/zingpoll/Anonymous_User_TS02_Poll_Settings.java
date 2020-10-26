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
import pageObjects.zingpoll.UpdatePollPageObject;
import pageObjects.zingpoll.VotingPageObject;

public class Anonymous_User_TS02_Poll_Settings extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	HomePageObject homePage;
	VotingPageObject votingPage;
	MailinatorPageObject mailinatorPage;
	ManagePollPageObject managePollPage;
	UpdatePollPageObject updatePollPage;
	PollDeletePageObject pollDeletePage;
	
	String pollURL=null;
	

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		log.info("Pre-condition: Launch app");		
		driver.get(GlobalConstants.URL);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		homePage.waitForJStoLoad(driver);
	}

	@Description()
	@Test
	public void TC05_TC07_TC09_TC10_TC11_TC12_Poll_Settings() {	
		log.info("Step: Input full valid data to create poll");
		homePage.inputToQuestionContentTextbox(GlobalConstants.COUNTRY_QUESTION_CONTENT);
		homePage.inputToOption1Textbox(GlobalConstants.OPTION1);
		homePage.inputToOption2Textbox(GlobalConstants.OPTION2);
		homePage.inputToEmailTextbox(GlobalConstants.ANONYMOUS_EMAIL);
		
		log.info("Step: Click Poll Settings");	
		homePage.clickToPollSettingsButton();
		
		log.info("VP: The settings of logged-in user 'Set voting time', 'Allow voters to edit their answers', 'Participants' do not display");
		verifyTrue(homePage.isSetVotingTimeUndisplayed());
		verifyTrue(homePage.isAllowVotersEditAnswersUndisplayed());
		verifyTrue(homePage.isParticipantsUndisplayed());	
		
		log.info("VP: Optimize_Poll_Link is auto-checked and is disabled");
		verifyTrue(homePage.isOptimizePollLinkCheckboxChecked());
		verifyTrue(homePage.isOptimizePollLinkCheckboxDisabled());
		
		log.info("VP: Poll_Link is auto-generated");
		pollURL= homePage.getPollURLTextFromSetting();
		verifyTrue(!pollURL.isEmpty());
		
		log.info("Step: Select 'Required email to voting'");
		homePage.selectRequiredEmailCheckbox();
		
		log.info("Step: Select 'Required name to voting'");
		homePage.selectRequiredNameCheckbox();
		
		log.info("Step: Select 'Allow voters to add new answers'");
		homePage.selectAllowVotersAddNewAnswersCheckbox();
		
		log.info("Step: Select 'Hide voting result'");
		homePage.selectHideVotingResultCheckbox();
		
		log.info("Step: Click Create Poll button and navigate to Voting page");
		votingPage = homePage.clickToCreatePollButton();
		
		log.info("VP: The Poll Link after created equals the auto-generated link ");
		verifyEquals(votingPage.getPollUrl(), pollURL);
		
		log.info("VP: New Option textbox/RadioButton display at Voting page ");
		verifyTrue(votingPage.isNewOptionRadioButtonDisplayed());
		verifyTrue(votingPage.isNewOptionTextboxDisplayed());
		
		log.info("VP: Email/Name is required when voting ");
		verifyTrue(votingPage.isEmailTextboxDisplayed());
		verifyTrue(votingPage.isNameTextboxDisplayed());
		votingPage.selectAnswerOptionRadiobutton(0);
		votingPage.clickToVoteButton();
		verifyTrue(votingPage.isRequiredEmailErrorMessageDisplayed());
		verifyTrue(votingPage.isRequiredNameErrorMessageDisplayed());
				
		log.info("VP: Preview result/'Show Detail' does not display at Voting page ");
		verifyTrue(votingPage.isPreviewResultLinkUndisplayed());
		verifyTrue(votingPage.isShowDetailLinkUndisplayed());
	}
	

//	@Test
	public void TC08_Optimize_Poll_Link_In_Setting_When_Create_Poll() {
		log.info("Step: Optimize Poll_Link");
		pollURL=String.valueOf(randomNumber()).substring(1);
		homePage.inputToPollURLTextbox(pollURL);
		
		log.info("Step: Click Create Poll button and navigate to Voting page");
		votingPage = homePage.clickToCreatePollButton();
		
		log.info("VP: The Poll Link after created equals the optimized link ");
		verifyEquals(votingPage.getPollUrl(), pollURL);
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
