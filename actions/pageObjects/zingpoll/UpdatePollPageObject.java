package pageObjects.zingpoll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.zingpoll.AbstractPageUI;
import pageUIs.zingpoll.HomePageUI;
import pageUIs.zingpoll.UpdatePollPageUI;

public class UpdatePollPageObject extends AbstractPage{

	private WebDriver driver;
	public UpdatePollPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Input data into Question Content textbox {0}")
	public void inputToQuestionContentTextbox(String questionContent) {
		waitElementVisible(driver, UpdatePollPageUI.QUESTION_CONTENT_TEXTBOX);
		sendKeysToElement(driver, UpdatePollPageUI.QUESTION_CONTENT_TEXTBOX, questionContent);
	}
	
	@Step("Input data into Dynamic Answer Option textbox {0}")
	public void inputToDynamicOptionTextbox(String updateOption, String optionNumber) {
		waitElementVisible(driver, UpdatePollPageUI.DYNAMIC_OPTION_TEXTBOX,optionNumber);
		sendKeysToElement(driver, UpdatePollPageUI.DYNAMIC_OPTION_TEXTBOX, updateOption, optionNumber);
	}
	
	@Step("click Dynamic Answer Option Image Icon")
	public void clickToDynamicOptionImageIcon(String optionNumber) {
		waitElementClickable(driver, UpdatePollPageUI.DYNAMIC_OPTION_IMAGE_ICON, optionNumber);
		clickToElement(driver, UpdatePollPageUI.DYNAMIC_OPTION_IMAGE_ICON, optionNumber);
	}
	
	@Step("click Update Poll button and navigate to Voting Page")
	public VotingPageObject clickToUpdatePollButton() {
		sleepInSecond(1);
		waitElementClickable(driver, UpdatePollPageUI.UPDATE_POLL_BUTTON);
		clickToElementByJS(driver, UpdatePollPageUI.UPDATE_POLL_BUTTON);
		waitElementInvisible(driver, AbstractPageUI.LOADING_ICON);
		return PageGeneratorManager.getVotingPageObject(driver);
	}
	
	@Step("Input data into Dynamic New Answer Option textbox {0}")
	public void inputToDynamicNewOptionTextbox(String value) {
		newOptionsSize = countElementNumber(driver, UpdatePollPageUI.NEW_OPTION_TEXTBOX);
		finds(driver, UpdatePollPageUI.NEW_OPTION_TEXTBOX).get(newOptionsSize-1).click();
		finds(driver, UpdatePollPageUI.NEW_OPTION_TEXTBOX).get(newOptionsSize-1).sendKeys(value);		
	}
	
	@Step("click New Answer Option Image Icon")
	public void clickToNewOptionImageIcon() {
		newOptionsSize = countElementNumber(driver, HomePageUI.NEW_OPTION_IMAGE_ICON);		
		finds(driver, HomePageUI.NEW_OPTION_IMAGE_ICON).get(newOptionsSize-2).click();		
	}
	
	@Step("click Dynamic Answer Option Delete Icon {0}")
	public void clickToDynamicOptionDeleteIcon(String optionNumber) {
		waitElementClickable(driver, UpdatePollPageUI.DYNAMIC_OPTION_DELETE_ICON, optionNumber);
		clickToElement(driver, UpdatePollPageUI.DYNAMIC_OPTION_DELETE_ICON, optionNumber);
	}
	
	private int newOptionsSize;
}
