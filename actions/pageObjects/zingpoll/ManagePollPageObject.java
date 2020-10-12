package pageObjects.zingpoll;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.zingpoll.AbstractPageUI;
import pageUIs.zingpoll.ManagePollPageUI;

public class ManagePollPageObject extends AbstractPage {
	private WebDriver driver;
	public ManagePollPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click Update icon of the poll")
	public void clickToUpdateIconOfPoll() {
		waitElementClickable(driver, ManagePollPageUI.UPDATE_POLL_BUTTON);
		clickToElement(driver, ManagePollPageUI.UPDATE_POLL_BUTTON);
	}
	
	@Step("Verify Confirm Manage Poll Password Popup is displayed")
	public boolean isConfirmManagePollPasswordPopupDisplayed() {
		waitElementsVisible(driver, ManagePollPageUI.CONFIRM_MANAGE_POLL_PASSWORD_POPUP);
		return isElementDisplayed(driver, ManagePollPageUI.CONFIRM_MANAGE_POLL_PASSWORD_POPUP);
	}
	
	@Step("input data into Manage Poll Password textbox {0}")
	public void inputToManagePollPasswordTextbox(String managePollPassword) {
		waitElementsVisible(driver, ManagePollPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, ManagePollPageUI.PASSWORD_TEXTBOX, managePollPassword);
	}
	
	@Step("Click OK button")
	public void clickToOKButton() {
		waitElementClickable(driver, ManagePollPageUI.OK_BUTTON);
		clickToElement(driver, ManagePollPageUI.OK_BUTTON);
		waitElementInvisible(driver, AbstractPageUI.LOADING_ICON);
	}
	
	@Step("Click Delete icon of the poll")
	public void cickToPollDeleteIcon() {
		waitElementClickable(driver, ManagePollPageUI.DELETE_POLL_BUTTON);
		clickToElement(driver, ManagePollPageUI.DELETE_POLL_BUTTON);
	}
	

}
