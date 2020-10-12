package pageUIs.zingpoll;

public class VotingPageUI {
	public static final String CREATE_POLL_SUCCESS_MESSAGE = "//div[@id='messageCreatePollSuccess' and contains(.,'Your poll has been created.')]";
	public static final String UPDATE_POLL_SUCCESS_MESSAGE = "//div[@id='messageUpdatePollSuccess' and contains(.,'Your poll has been updated.')]";
	public static final String DYNAMIC_QUESTION_CONTENT_TEXT = "//div[@class='col-xs-12 question_title mt-10px' and contains(text(),'%s')]";
	public static final String DYNAMIC_ANSWER_OPTION_TEXT = "//div[@class='radio eachAnswer']//label[contains(text(),'%s')]";
	public static final String DYNAMIC_ANSWER_OPTION_IMAGE = "//div[@class='radio eachAnswer']//label[contains(text(),'%s')]//img[@class='label-group imageAnswer labelClick']";
	
	public static final String DYNAMIC_QR_CODE_IMAGE_BY_URL = "//div[@class='qrcode']/img[@src='/qr/%s.png']";
	public static final String MANAGE_POLL_LINK = "//a[@class='manage-link']";
	
	public static final String PREVIEW_RESULT_LINK = "//div[@id='bResult']/small[text()='Preview result']";
	public static final String SHOW_DETAIL_LINK = "//div[@id='bDetails']/small[text()='Show detail']";
	public static final String NEW_OPTION_TEXTBOX = "//input[@name='newAnswer']";
	public static final String NEW_OPTION_RADIOBUTON = "//input[@name='newAnswer']//ancestor::td/preceding-sibling::td/input[@type='radio' and @id='selectNewAnswer']";
	public static final String GUEST_EMAIL_TEXTBOX = "//input[@type='email' and @id='guestEmail']";
	public static final String GUEST_NAME_TEXTBOX = "//input[@type='text' and @id='guestName']";
	public static final String REQUIRED_EMAIL_ERROR_MESSAGE = "//small[@data-bv-for='guestEmail' and text()='Please enter your email.']";
	public static final String REQUIRED_NAME_ERROR_MESSAGE = "//small[@data-bv-for='guestName' and text()='Please enter a value']";
	public static final String VOTE_BUTTON = "//input[@id='btnSubmitVote']";
	public static final String OPTION_RADIOBUTTON_LIST = "//input[@class='css-checkbox myCheckBox answer']";
	
}
