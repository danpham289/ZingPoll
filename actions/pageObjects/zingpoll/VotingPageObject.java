package pageObjects.zingpoll;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.zingpoll.AbstractPageUI;
import pageUIs.zingpoll.ResultPageUI;
import pageUIs.zingpoll.VotingPageUI;

public class VotingPageObject extends AbstractPage {
	private WebDriver driver;
	public VotingPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Verify Create Poll Success Message is displayed")
	public boolean isCreatePollSuccessMessageDisplayed() {
		waitElementVisible(driver, VotingPageUI.CREATE_POLL_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, VotingPageUI.CREATE_POLL_SUCCESS_MESSAGE);
	}
	
	@Step("Verify Question Content is displayed {0}")
	public boolean isQuestionContentDisplayed(String questionContent) {
		waitElementVisible(driver, VotingPageUI.DYNAMIC_QUESTION_CONTENT_TEXT,questionContent);
		return isElementDisplayed(driver, VotingPageUI.DYNAMIC_QUESTION_CONTENT_TEXT,questionContent);
	}
	
	@Step("Verify Answer Option is displayed {0}")
	public boolean isAnswerOptionTextDisplayed(String option) {
		waitElementVisible(driver, VotingPageUI.DYNAMIC_ANSWER_OPTION_TEXT,option);
		return isElementDisplayed(driver, VotingPageUI.DYNAMIC_ANSWER_OPTION_TEXT,option);
	}
	
	@Step("Verify Answer Option Image is displayed {0}")
	public boolean isAnswerOptionImageDisplayed(String option) {
		waitForJStoLoad(driver);
		waitElementVisible(driver, VotingPageUI.DYNAMIC_ANSWER_OPTION_IMAGE, option);
		return isImageLoaded(driver, VotingPageUI.DYNAMIC_ANSWER_OPTION_IMAGE, option);
	}
	
	@Step("Get Poll URL")
	public String getPollUrl() {
		String url = getPageUrl(driver);
		String[] text  = url.split("/");
		int length = url.split("/").length;
		return text[length-1];
	}
	
	@Step("Verify QR Code Image is displayed {0}")
	public boolean isQRCodeImageDisplayed(String pollURL) {
		waitElementVisible(driver, VotingPageUI.DYNAMIC_QR_CODE_IMAGE_BY_URL,pollURL);
		return isElementDisplayed(driver, VotingPageUI.DYNAMIC_QR_CODE_IMAGE_BY_URL,pollURL);
	}
	
	@Step("Verify Update Poll Success Message is displayed")
	public boolean isUpdatePollSuccessMessageDisplayed() {
		waitElementVisible(driver, VotingPageUI.UPDATE_POLL_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, VotingPageUI.UPDATE_POLL_SUCCESS_MESSAGE);
	}
	
	@Step("Click Manage Poll link and navigate to Manage Poll page")
	public ManagePollPageObject clickToManagePollLink() {
		waitElementClickable(driver, VotingPageUI.MANAGE_POLL_LINK);
		clickToElement(driver, VotingPageUI.MANAGE_POLL_LINK);
		waitForJStoLoad(driver);
		return PageGeneratorManager.getManagePollPageObject(driver);
	}

	public boolean isEmailTextboxDisplayed() {
		waitElementVisible(driver, VotingPageUI.GUEST_EMAIL_TEXTBOX);
		return isElementDisplayed(driver, VotingPageUI.GUEST_EMAIL_TEXTBOX);
	}

	public boolean isNameTextboxDisplayed() {
		waitElementVisible(driver, VotingPageUI.GUEST_NAME_TEXTBOX);
		return isElementDisplayed(driver, VotingPageUI.GUEST_NAME_TEXTBOX);
	}

	public void clickToVoteButton() {
		waitElementClickable(driver, VotingPageUI.VOTE_BUTTON);
		clickToElement(driver, VotingPageUI.VOTE_BUTTON);
		//waitElementInvisible(driver, AbstractPageUI.LOADING_ICON);
		waitForJStoLoad(driver);
	}

	public boolean isRequiredEmailErrorMessageDisplayed() {
		waitElementVisible(driver, VotingPageUI.REQUIRED_EMAIL_ERROR_MESSAGE);
		return isElementDisplayed(driver, VotingPageUI.REQUIRED_EMAIL_ERROR_MESSAGE);
		
	}

	public boolean isRequiredNameErrorMessageDisplayed() {
		waitElementVisible(driver, VotingPageUI.REQUIRED_NAME_ERROR_MESSAGE);
		return isElementDisplayed(driver, VotingPageUI.REQUIRED_NAME_ERROR_MESSAGE);		
	}

	public boolean isNewOptionRadioButtonDisplayed() {
		//waitElementVisible(driver, VotingPageUI.NEW_OPTION_RADIOBUTON);
		return isElementDisplayed(driver, VotingPageUI.NEW_OPTION_RADIOBUTON);
	}

	public boolean isNewOptionTextboxDisplayed() {
		waitElementVisible(driver, VotingPageUI.NEW_OPTION_TEXTBOX);
		return isElementDisplayed(driver, VotingPageUI.NEW_OPTION_TEXTBOX);
	}

	public boolean isPreviewResultLinkUndisplayed() {
		return isElementUndisplayed(driver, VotingPageUI.PREVIEW_RESULT_LINK);
	}

	public boolean isShowDetailLinkUndisplayed() {
		return isElementUndisplayed(driver, VotingPageUI.SHOW_DETAIL_LINK);
	}

	public void selectAnswerOptionRadiobutton(int index) {
		WebElement element = finds(driver, VotingPageUI.OPTION_RADIOBUTTON_LIST).get(index);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
	}

	public boolean isPollReachVotingLimitDisplayed() {
		waitElementVisible(driver, VotingPageUI.POLL_REACH_VOTE_LIMIT_MESSAGE);
		return isElementDisplayed(driver, VotingPageUI.POLL_REACH_VOTE_LIMIT_MESSAGE);
	}

	public boolean isVoteButtonUndisplayed() {
		return isElementUndisplayed(driver, VotingPageUI.VOTE_BUTTON);
	}

	

	public void inputToPollPasswordTextbox(String pollPassword) {
		waitElementVisible(driver, VotingPageUI.POLL_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, VotingPageUI.POLL_PASSWORD_TEXTBOX, pollPassword);
	}

	public void sendEnterKeyToPollPasswordTextbox() {
		
	}

	public boolean isRequirePasswordAlertMessageDisplayed() {
		waitElementVisible(driver, VotingPageUI.REQUIRE_PASSWORD_MESSAGE);
		return isElementDisplayed(driver, VotingPageUI.REQUIRE_PASSWORD_MESSAGE);
	}

	public boolean isRequirePasswordAlertMessageUndisplayed() {
		return isElementUndisplayed(driver, VotingPageUI.REQUIRE_PASSWORD_MESSAGE);
	}
}
