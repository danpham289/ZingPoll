package pageObjects.zingpoll;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.zingpoll.ResultPageUI;
import pageUIs.zingpoll.VotingPageUI;

public class ResultPageObject extends AbstractPage {
	private WebDriver driver;
	public ResultPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isPollReachVotingLimitDisplayed() {
		waitElementVisible(driver, ResultPageUI.POLL_REACH_VOTE_LIMIT_MESSAGE);
		return isElementDisplayed(driver, ResultPageUI.POLL_REACH_VOTE_LIMIT_MESSAGE);
	}
	public boolean isVotingPollSuccessMessageDisplayed() {
		waitElementVisible(driver, ResultPageUI.VOTE_POLL_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, ResultPageUI.VOTE_POLL_SUCCESS_MESSAGE);
	}

	
	
}
