package pageObjects.zingpoll;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageObject getHomePageObject(WebDriver driver ) {
		return new HomePageObject(driver);
	}
	public static VotingPageObject getVotingPageObject(WebDriver driver ) {
		return new VotingPageObject(driver);
	}
	public static MailinatorPageObject getMailinatorPageObject(WebDriver driver ) {
		return new MailinatorPageObject(driver);
	}
	public static ManagePollPageObject getManagePollPageObject(WebDriver driver ) {
		return new ManagePollPageObject(driver);
	}
	public static UpdatePollPageObject getUpdatePollPageObject(WebDriver driver ) {
		return new UpdatePollPageObject(driver);
	}
	public static PollDeletePageObject getPollDeletePageObject(WebDriver driver ) {
		return new PollDeletePageObject(driver);
	}

}
