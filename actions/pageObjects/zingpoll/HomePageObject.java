package pageObjects.zingpoll;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.zingpoll.AbstractPageUI;
import pageUIs.zingpoll.HomePageUI;

public class HomePageObject extends AbstractPage{

	private WebDriver driver;
	private int newOptionsSize;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Input data into Question Content textbox {0}")
	public void inputToQuestionContentTextbox(String questionContent) {
		waitElementVisible(driver, HomePageUI.QUESTION_CONTENT_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.QUESTION_CONTENT_TEXTBOX, questionContent);
		
	}
	
	@Step("Input data into Answer Option 1 textbox {0}")
	public void inputToOption1Textbox(String value) {
		waitElementVisible(driver, HomePageUI.OPTION1_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.OPTION1_TEXTBOX,value);
		
	}
	
	@Step("Input data into Answer Option 2 textbox {0}")
	public void inputToOption2Textbox(String value) {
		waitElementVisible(driver, HomePageUI.OPTION2_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.OPTION2_TEXTBOX,value);
		
	}
	
	@Step("Input data into Guest Email textbox {0}")
	public void inputToEmailTextbox(String value) {
		waitElementVisible(driver, HomePageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.EMAIL_TEXTBOX,value);		
	}
	
	@Step("Click Create Poll Button and navigate to Voting Page")
	public VotingPageObject clickToCreatePollButton() {
		sleepInSecond(2);
		waitElementClickable(driver, HomePageUI.CREATE_POLL_BUTTON);			
		clickToElementByJS(driver, HomePageUI.CREATE_POLL_BUTTON);		
		waitElementInvisible(driver, AbstractPageUI.LOADING_ICON);
		return PageGeneratorManager.getVotingPageObject(driver);
	}
	
	@Step("Input data into New Option textbox {0}")
	public void inputToDynamicNewOptionTextbox(String value) {
		newOptionsSize = countElementNumber(driver, HomePageUI.NEW_OPTION_TEXTBOX);
		finds(driver, HomePageUI.NEW_OPTION_TEXTBOX).get(newOptionsSize-1).click();
		finds(driver, HomePageUI.NEW_OPTION_TEXTBOX).get(newOptionsSize-1).sendKeys(value);
	}

	@Step("Click Sign In menu")
	public void clickToSignInMenu() {
		waitElementClickable(driver, HomePageUI.SIGN_IN_MENU);
		clickToElement(driver, HomePageUI.SIGN_IN_MENU);
				
	}

	@Step("Verify Sign In Popup is displayed")
	public boolean isSignInPopupDislayed() {
		waitElementVisible(driver, HomePageUI.SIGN_IN_POPUP);
		return isElementDisplayed(driver, HomePageUI.SIGN_IN_POPUP);
	}

	@Step("Check New User Radiobutton")
	public void checkNewUserRadioButton() {
		waitElementClickable(driver, HomePageUI.NEW_USER_RADIO_BUTTON);
		clickToElementByJS(driver, HomePageUI.NEW_USER_RADIO_BUTTON);
	}

	@Step("Input data into Full Name textbox {0}")
	public void inputToFullNameTextbox(String fullName) {
		waitElementVisible(driver, HomePageUI.FULL_NAME_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.FULL_NAME_TEXTBOX, fullName);
	}

	@Step("Input data into Register Email textbox {0}")
	public void inputToRegisterEmailTextbox(String email) {
		waitElementVisible(driver, HomePageUI.REGISTER_EMAIL_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.REGISTER_EMAIL_TEXTBOX, email);	
	}

	@Step("Input data into Password textbox {0}")
	public void inputToPasswordTextbox(String password) {
		waitElementVisible(driver, HomePageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.PASSWORD_TEXTBOX, password);		
	}

	@Step("Input data into Re-Password textbox {0}")
	public void inputToReenterPasswordTextbox(String password) {
		waitElementVisible(driver, HomePageUI.RE_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.RE_PASSWORD_TEXTBOX, password);	
	}
	
	@Step("Check Agree Term checkbox")
	public void checkAgreecheckbox() {
		waitElementClickable(driver, HomePageUI.AGREE_TERM_CHECKBOX);
		checkToCheckbox(driver, HomePageUI.AGREE_TERM_CHECKBOX);	
	}

	@Step("Click Register Button")
	public void clickToRegisterButton() {
		waitElementClickable(driver, HomePageUI.REGISTER_BUTTON);
		clickToElement(driver, HomePageUI.REGISTER_BUTTON);
		waitElementInvisible(driver, AbstractPageUI.LOADING_ICON);
	}

	@Step("Verify Activation Link Message is displayed")
	public boolean isActivationLinkMessageDisplayed() {
		waitElementVisible(driver, HomePageUI.ACTIVATION_LINK_MESSAGE);
		return isElementDisplayed(driver, HomePageUI.ACTIVATION_LINK_MESSAGE);
	}

	@Step("Input data into Sign In Email textbox {0}")
	public void inputToSignInEmailTextbox(String email) {
		waitElementVisible(driver, HomePageUI.SIGN_IN_EMAIL_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.SIGN_IN_EMAIL_TEXTBOX, email);
		
	}

	@Step("Input data into Sign In Password textbox {0}")
	public void inputToSignInPasswordTextbox(String password) {
		waitElementVisible(driver, HomePageUI.SIGN_IN_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.SIGN_IN_PASSWORD_TEXTBOX, password);
	}

	@Step("Click Sign In Button")
	public void clickToSignInButton() {
		waitElementClickable(driver, HomePageUI.SIGN_IN_BUTTON);
		clickToElement(driver, HomePageUI.SIGN_IN_BUTTON);
	}

	@Step("Verify Full Name is displayed instead of Sign In on top menu ")
	public boolean isFullNameDisplayed(String fullname) {
		waitElementVisible(driver, HomePageUI.FULL_NAME_TEXT,fullname);
		return isElementDisplayed(driver, HomePageUI.FULL_NAME_TEXT,fullname);
	}
	
	@Step("Verify Activate Account Success Message is displayed")
	public boolean isActivateAccountSuccessMessageDisplayed() {
		waitElementVisible(driver, HomePageUI.ACTIVATE_ACCOUNT_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, HomePageUI.ACTIVATE_ACCOUNT_SUCCESS_MESSAGE);
	}
	
	@Step("Click Answer Option1 Image Icon")
	public void clickToOption1ImageIcon() {
		waitElementClickable(driver, HomePageUI.OPTION1_IMAGE_ICON);
		clickToElement(driver, HomePageUI.OPTION1_IMAGE_ICON);
	}
	
	@Step("Click Answer Option2 Image Icon")
	public void clickToOption2ImageIcon() {
		waitElementClickable(driver, HomePageUI.OPTION2_IMAGE_ICON);
		clickToElement(driver, HomePageUI.OPTION2_IMAGE_ICON);
	}

	@Step("Click New Option Image Icon")
	public void clickToNewOptionImageIcon() {
		newOptionsSize = countElementNumber(driver, HomePageUI.NEW_OPTION_IMAGE_ICON);
		
		finds(driver, HomePageUI.NEW_OPTION_IMAGE_ICON).get(newOptionsSize-2).click();
	}

	public void clickToPollSettingsButton() {
		waitElementClickable(driver, HomePageUI.POLL_SETTINGS_BUTTON);
		clickToElement(driver, HomePageUI.POLL_SETTINGS_BUTTON);
		waitForJStoLoad(driver);
	}

	public boolean isSetVotingTimeUndisplayed() {	
		return isElementUndisplayed(driver, HomePageUI.VOTING_TIME_CHECKBOX);
	}

	public boolean isAllowVotersEditAnswersUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.ALLOW_EDIT_ANSWER_CHECKBOX);
	}

	public boolean isParticipantsUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.PARTICIPANTS_CHECKBOX);
	}

	public boolean isActivationLinkMessageUnDisplayed() {
		waitElementInvisible(driver, HomePageUI.ACTIVATION_LINK_MESSAGE);
		return isElementUndisplayed(driver, HomePageUI.ACTIVATION_LINK_MESSAGE);
	}

	public boolean isOptimizePollLinkCheckboxChecked() {
		//waitElementVisible(driver, HomePageUI.OPTIMIZE_POLL_LINK_CHECKBOX);
		return isElementSelected(driver, HomePageUI.OPTIMIZE_POLL_LINK_CHECKBOX);
		
	}

	public boolean isOptimizePollLinkCheckboxDisabled() {
		//waitElementVisible(driver, HomePageUI.OPTIMIZE_POLL_LINK_CHECKBOX);
		return !isElementEnabled(driver, HomePageUI.OPTIMIZE_POLL_LINK_CHECKBOX);	
	}

	public String getPollURLTextFromSetting() {
		waitElementVisible(driver, HomePageUI.POLL_URL_TEXTBOX);
		return getElementAttribute(driver, HomePageUI.POLL_URL_TEXTBOX,"placeholder");
	}

	public void clickToZingPollIcon() {
		waitElementClickable(driver, HomePageUI.ZINGPOLL_ICON);
		clickToElement(driver, HomePageUI.ZINGPOLL_ICON);
		waitForJStoLoad(driver);
	}

	public void inputToPollURLTextbox(String pollURL) {
		scrollToElement(driver, HomePageUI.POLL_URL_TEXTBOX);
		waitElementVisible(driver, HomePageUI.POLL_URL_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.POLL_URL_TEXTBOX, pollURL);
	}

	public void selectRequiredEmailCheckbox() {
		//waitElementClickable(driver,HomePageUI.REQUIRED_EMAIL_CHECKBOX);
		clickToElementByJS(driver,HomePageUI.REQUIRED_EMAIL_CHECKBOX);
	}

	public void selectRequiredNameCheckbox() {
		//waitElementClickable(driver,HomePageUI.REQUIRED_NAME_CHECKBOX);
		clickToElementByJS(driver,HomePageUI.REQUIRED_NAME_CHECKBOX);
	}

	public void selectAllowVotersAddNewAnswersCheckbox() {
		//waitElementClickable(driver,HomePageUI.ALLOW_VOTER_ADD_ANWSER_CHECKBOX);
		clickToElementByJS(driver,HomePageUI.ALLOW_VOTER_ADD_ANWSER_CHECKBOX);		
	}

	public void selectHideVotingResultCheckbox() {
		//waitElementClickable(driver,HomePageUI.HIDE_VOTING_RESULT_CHECKBOX);
		clickToElementByJS(driver,HomePageUI.HIDE_VOTING_RESULT_CHECKBOX);
		
	}


	public void selectSetVotingLimitCheckbox() {
		clickToElementByJS(driver,HomePageUI.SET_VOTING_LIMIT_CHECKBOX);		
	}

	public boolean isLimitVoteNumberTextboxEnabled() {
		return isElementEnabled(driver, HomePageUI.VOTING_LIMIT_NUMBER_TEXTBOX);
	}

	public void inputToLimitVoteNumberTextbox(String value) {
		waitElementVisible(driver, HomePageUI.VOTING_LIMIT_NUMBER_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.VOTING_LIMIT_NUMBER_TEXTBOX, value);
	}
	

	public void inputToPollSearchTextbox(String value) {
		waitElementVisible(driver, HomePageUI.SEARCH_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.SEARCH_TEXTBOX, value);
	}

	public void clickToSearchIcon() {
		waitElementClickable(driver, HomePageUI.SEARCH_ICON);
		clickToElement(driver, HomePageUI.SEARCH_ICON);
		waitElementsVisible(driver, HomePageUI.SEARCH_RESULT_TEXT);
	}

	public boolean isDateCreatedPollSortedByDescending() {
		waitElementsVisible(driver, HomePageUI.CREATED_DATE_TEXT);
		return isDateSortedByDescending(driver, HomePageUI.CREATED_DATE_TEXT);
	}

	public boolean isPollPasswordTextboxEnabled() {
		return isElementEnabled(driver, HomePageUI.SET_PASSWORD_TEXTBOX);
	}

	public void selectSetPasswordCheckbox() {
		//waitElementVisible(driver, HomePageUI.SET_PASSWORD_CHECKBOX);
		clickToElementByJS(driver, HomePageUI.SET_PASSWORD_CHECKBOX);
	}

	public void inputToPollPasswordTextbox(String pollPassword) {
		waitElementVisible(driver, HomePageUI.SET_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, HomePageUI.SET_PASSWORD_TEXTBOX, pollPassword);
	}



	

}
