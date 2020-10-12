package pageObjects.zingpoll;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.zingpoll.MailinatorPageUI;

public class MailinatorPageObject extends AbstractPage {
	private WebDriver driver;
	public MailinatorPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToMailTextbox(String email) {
		waitElementVisible(driver, MailinatorPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, MailinatorPageUI.EMAIL_TEXTBOX, email);
	}
	public void clickToGoButton() {
		waitElementClickable(driver, MailinatorPageUI.GO_BUTTON);
		clickToElement(driver, MailinatorPageUI.GO_BUTTON);
	}
	public void clickToActivationMailLink() {
		waitElementClickable(driver, MailinatorPageUI.ACTIVATION_EMAIL_LINK);
		clickToElement(driver, MailinatorPageUI.ACTIVATION_EMAIL_LINK);
	}
	public HomePageObject clickToActivateNowButton() {
		waitElementVisible(driver, MailinatorPageUI.BODY_EMAIL_IFRAME);
		switchToFrame(driver, MailinatorPageUI.BODY_EMAIL_IFRAME);
		clickToElementByJS(driver, MailinatorPageUI.ACTIVATE_BUTTON);
		swicthToWindowByTitle(driver, "Simple, Instant and Visual Poll");
		return PageGeneratorManager.getHomePageObject(driver);
	}
	public boolean isActivateEmailDisplayed() {
		waitElementVisible(driver, MailinatorPageUI.ACTIVATION_EMAIL_LINK);
		return isElementDisplayed(driver, MailinatorPageUI.ACTIVATION_EMAIL_LINK);
	}
	public boolean isCreatePollSuccessfullyEmailDisplayed() {
		waitElementVisible(driver, MailinatorPageUI.CREATE_POLL_SUCCESS_EMAIL_LINK);
		return isElementDisplayed(driver, MailinatorPageUI.CREATE_POLL_SUCCESS_EMAIL_LINK);
	}

	public void clickToCreatePollSuccessEmailLink() {
		waitElementClickable(driver, MailinatorPageUI.CREATE_POLL_SUCCESS_EMAIL_LINK);
		clickToElement(driver, MailinatorPageUI.CREATE_POLL_SUCCESS_EMAIL_LINK);
		waitElementVisible(driver, MailinatorPageUI.BODY_EMAIL_IFRAME);
		switchToFrame(driver, MailinatorPageUI.BODY_EMAIL_IFRAME);
		
	}
	public String getManagePollPasswordFromCreatePollSuccessfullyEmail() {
		return find(driver, MailinatorPageUI.MANAGE_POLL_PASSWORD_TEXT).getAttribute("innerText");
	}
	public String getLinkOfManagePollButtonFromCreatePollSuccessfullyEmail() {
		System.out.println(getElementAttribute(driver, MailinatorPageUI.MANAGE_POLL_BUTTON, "href"));
		return getElementAttribute(driver, MailinatorPageUI.MANAGE_POLL_BUTTON, "href");
	}

	public String getQuestionContentFromCreatePollSuccessfullyEmail() {
		return find(driver, MailinatorPageUI.QUESTION_CONTENT_IN_CREATE_POLL_SUCCESS_EMAIL).getAttribute("innerText");
	}
	public ManagePollPageObject clickToManagePollButton() {
//		switchToFrame(driver, MailinatorPageUI.BODY_EMAIL_IFRAME);
		clickToElementByJS(driver, MailinatorPageUI.MANAGE_POLL_BUTTON);
		swicthToWindowByTitle(driver, "Poll Management");
		return PageGeneratorManager.getManagePollPageObject(driver);
		
	}

}
