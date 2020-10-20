package com.zingpoll;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import driverFactory.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.zingpoll.HomePageObject;
import pageObjects.zingpoll.MailinatorPageObject;
import pageObjects.zingpoll.ManagePollPageObject;
import pageObjects.zingpoll.PageGeneratorManager;
import pageObjects.zingpoll.PollDeletePageObject;
import pageObjects.zingpoll.UpdatePollPageObject;
import pageObjects.zingpoll.VotingPageObject;

@Epic("First Test")
@Feature("Create/Update/Delete Poll")
public class TS01_Create_Update_Delete_Poll_By_Anonymous_User extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	HomePageObject homePage;
	VotingPageObject votingPage;
	MailinatorPageObject mailinatorPage;
	ManagePollPageObject managePollPage;
	UpdatePollPageObject updatePollPage;
	PollDeletePageObject pollDeletePage;
	
	String pollURL=null;
	String managePollPassword;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
//		driverManager = DriverFactory.getBrowserDriverManager(browserName);
//		driver = driverManager.getDriver();
		driver = getBrowserDriver(browserName);
		log.info("Pre-condition: Launch app");		
		driver.get(GlobalConstants.URL);
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		homePage.waitForJStoLoad(driver);
	}
	
	@Story("Story 01: Create Poll")
	@Description("Anonymous user can create poll with attached images successfully")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void TC01_Create_Poll_With_Attached_Image() {
		log.info("Step 1: Input question content");
		homePage.inputToQuestionContentTextbox(GlobalConstants.COUNTRY_QUESTION_CONTENT);
		
		log.info("Step 2: Input 2 first answer options with images uploaded from your picture ");
		homePage.inputToOption1Textbox(GlobalConstants.OPTION1);
		homePage.clickToOption1ImageIcon();
		homePage.attachImageToOptionFromYourPicture(driver,GlobalConstants.OPTION1_IMAGE_FILE);	
		
		homePage.inputToOption2Textbox(GlobalConstants.OPTION2);
		homePage.clickToOption2ImageIcon();
		homePage.attachImageToOptionFromYourPicture(driver, GlobalConstants.OPTION2_IMAGE_FILE);
		
		log.info("Step 3: Input more 2 new options with images uploaded from internet picture ");
		homePage.inputToDynamicNewOptionTextbox(GlobalConstants.OPTION3);
		homePage.clickToNewOptionImageIcon();
		homePage.attachImageToOptionFromInternetPicture(driver,GlobalConstants.OPTION3);	
		
		homePage.inputToDynamicNewOptionTextbox(GlobalConstants.OPTION4);
		homePage.clickToNewOptionImageIcon();
		homePage.attachImageToOptionFromInternetPicture(driver,GlobalConstants.OPTION4);
		
		log.info("Step 4: Input email");
		homePage.inputToEmailTextbox(GlobalConstants.ANONYMOUS_EMAIL);

		log.info("Step 5: Click Create Poll");
		votingPage = homePage.clickToCreatePollButton();

		log.info("VP: The poll is created successfully");
		verifyTrue(votingPage.isCreatePollSuccessMessageDisplayed());

		
		log.info("VP: The poll is created with correct question content");
		verifyTrue(votingPage.isQuestionContentDisplayed(GlobalConstants.COUNTRY_QUESTION_CONTENT));
		
		log.info("VP: The poll is created with 4 correct options and attached images");
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.OPTION1));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.OPTION1));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.OPTION2));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.OPTION2));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.OPTION3));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.OPTION3));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.OPTION4));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.OPTION4));
		
		log.info("Step 6: Get URL of the created poll");
		pollURL = votingPage.getPollUrl(); 
		
		log.info("VP: QR Code of the poll display");
		verifyTrue(votingPage.isQRCodeImageDisplayed(pollURL));
		
		log.info("VP: Create Poll Successfully mail is sent to the creator's email");
		driver.get(GlobalConstants.EMAIL_URL);
		mailinatorPage = PageGeneratorManager.getMailinatorPageObject(driver);
		mailinatorPage.inputToMailTextbox(GlobalConstants.ANONYMOUS_EMAIL);
		mailinatorPage.clickToGoButton();
		verifyTrue(mailinatorPage.isCreatePollSuccessfullyEmailDisplayed());
		
		log.info("VP: Content of Create Poll Successfully mail display correctly ");
		mailinatorPage.clickToCreatePollSuccessEmailLink();
		verifyTrue(mailinatorPage.getQuestionContentFromCreatePollSuccessfullyEmail().contains(GlobalConstants.COUNTRY_QUESTION_CONTENT));
		
		log.info("Step 6: Get Manage Poll Password from Create Poll Successfully Email");
		managePollPassword = mailinatorPage.getManagePollPasswordFromCreatePollSuccessfullyEmail();
		
		log.info("VP: Link of Manage Poll button in Create Poll Successfully Email is link of the correct manage poll page ");
		verifyTrue(mailinatorPage.getLinkOfManagePollButtonFromCreatePollSuccessfullyEmail().contains(GlobalConstants.URL+"manage/"+pollURL+"?code="));

	}
	
	@Test(dependsOnMethods = "TC01_Create_Poll_With_Attached_Image",priority=2)
	public void TC02_Anonymous_User_Edit_Poll(){
		log.info("Step 1: Click Manage Poll button from Create Poll Successfully Email");
		managePollPage = mailinatorPage.clickToManagePollButton();
		
		log.info("Step 2: Click Update icon of the poll");
		managePollPage.clickToUpdateIconOfPoll();
		
		log.info("VP: Confirm manage poll password popup displays");
		verifyTrue(managePollPage.isConfirmManagePollPasswordPopupDisplayed());
				
		log.info("Step 3: Input Password to manage poll and click OK button");
		managePollPage.inputToManagePollPasswordTextbox(managePollPassword);
		managePollPage.clickToOKButton();
		updatePollPage = PageGeneratorManager.getUpdatePollPageObject(driver);
				
		log.info("Step 4: Update question content");
		updatePollPage.inputToQuestionContentTextbox(GlobalConstants.UPDATE_COUNTRY_QUESTION_CONTENT);
		
		log.info("Step 5: Delete the first option");
		updatePollPage.clickToDynamicOptionDeleteIcon("0");
		
		log.info("Step 6: Update first answer options with images uploaded from internet picture");		
		updatePollPage.inputToDynamicOptionTextbox(GlobalConstants.UPDATE_OPTION2,"1");
		updatePollPage.clickToDynamicOptionImageIcon("1");
		updatePollPage.attachImageToOptionFromInternetPicture(driver,GlobalConstants.UPDATE_OPTION2);
		
		log.info("Step 7: Update more 2 new options with images uploaded from your picture ");
		updatePollPage.inputToDynamicOptionTextbox(GlobalConstants.UPDATE_OPTION3,"2");
		updatePollPage.clickToDynamicOptionImageIcon("2");
		updatePollPage.attachImageToOptionFromYourPicture(driver,GlobalConstants.UPDATE_OPTION3_IMAGE_FILE);
		updatePollPage.inputToDynamicOptionTextbox(GlobalConstants.UPDATE_OPTION4,"3");
		updatePollPage.clickToDynamicOptionImageIcon("3");
		updatePollPage.attachImageToOptionFromYourPicture(driver,GlobalConstants.UPDATE_OPTION4_IMAGE_FILE);
		
		log.info("Step 8: Input more 1 new option with images uploaded from internet picture");
		updatePollPage.inputToDynamicNewOptionTextbox(GlobalConstants.OPTION5);
		updatePollPage.clickToNewOptionImageIcon();
		updatePollPage.attachImageToOptionFromInternetPicture(driver,GlobalConstants.OPTION5);
		
		log.info("Step 9: Click Update Poll");
		votingPage = updatePollPage.clickToUpdatePollButton();
		
		log.info("VP: URL of the poll is not changed");
		verifyTrue(votingPage.getPollUrl().equals(pollURL));
		
		log.info("VP: Update Poll Success message displays and Poll is updated with correct data");
		verifyTrue(votingPage.isUpdatePollSuccessMessageDisplayed());
		verifyTrue(votingPage.isQuestionContentDisplayed(GlobalConstants.UPDATE_COUNTRY_QUESTION_CONTENT));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.UPDATE_OPTION2));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.UPDATE_OPTION2));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.UPDATE_OPTION3));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.UPDATE_OPTION3));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.UPDATE_OPTION4));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.UPDATE_OPTION4));
		verifyTrue(votingPage.isAnswerOptionTextDisplayed(GlobalConstants.OPTION5));
		verifyTrue(votingPage.isAnswerOptionImageDisplayed(GlobalConstants.OPTION5));		
	}
	
	@Test(dependsOnMethods = "TC02_Anonymous_User_Edit_Poll",priority=3)
	public void TC03_Anonymous_User_Delete_Poll(){
		log.info("Step 9: Click Manage Poll, it navigate to Manage Poll page");
		managePollPage = votingPage.clickToManagePollLink();
		
		log.info("Step 9: Click Delete icon of poll");
		managePollPage.cickToPollDeleteIcon();
		
		log.info("VP: Confirm manage poll password popup displays");
		verifyTrue(managePollPage.isConfirmManagePollPasswordPopupDisplayed());
				
		log.info("Step 3: Input Password to manage poll and click OK button");
		managePollPage.inputToManagePollPasswordTextbox(managePollPassword);
		managePollPage.clickToOKButton();
		pollDeletePage = PageGeneratorManager.getPollDeletePageObject(driver);
		
		log.info("VP: Delete Poll Successfullu message displays");
		verifyTrue(pollDeletePage.isDeletePollSuccessMessageDisplayed());
		
		log.info("VP: Open the poll URL , it returns ... page (not implement, it is opening Error page) ");		
	}
	
//	@Test(priority=4)
	public void TC04_Create_Poll_With_Bunch_Of_Answer_Options(){
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

		log.info("Step 4: Click Create Poll");
		votingPage = homePage.clickToCreatePollButton();
		
		log.info("VP: The poll is created successfully");
		verifyTrue(votingPage.isCreatePollSuccessMessageDisplayed());
	}



	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
