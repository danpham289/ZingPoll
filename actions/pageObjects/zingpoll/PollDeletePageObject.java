package pageObjects.zingpoll;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.zingpoll.PollDeletePageUI;

public class PollDeletePageObject extends AbstractPage {
	private WebDriver driver;
	public PollDeletePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Verify Delete Poll Success Message is displayed")
	public boolean isDeletePollSuccessMessageDisplayed() {
		waitElementVisible(driver, PollDeletePageUI.DELETE_POLL_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, PollDeletePageUI.DELETE_POLL_SUCCESS_MESSAGE);
	}
	
}
