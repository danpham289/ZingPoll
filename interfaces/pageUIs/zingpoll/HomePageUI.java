package pageUIs.zingpoll;

public class HomePageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='guestEmail']";
	public static final String OPTION1_TEXTBOX = "//input[@id='inputtext1']";
	public static final String OPTION1_IMAGE_ICON = OPTION1_TEXTBOX + "/following-sibling::span[@class='btn-file span-camera']";
	public static final String OPTION2_TEXTBOX = "//input[@id='inputtext2']";
	public static final String OPTION2_IMAGE_ICON = OPTION2_TEXTBOX + "/following-sibling::span[@class='btn-file span-camera']";
	public static final String NEW_OPTION_TEXTBOX = "//input[@name='optionAnswer' and @placeholder ='Add new option']";
	public static final String NEW_OPTION_IMAGE_ICON = NEW_OPTION_TEXTBOX + "/following-sibling::span[@class='btn-file span-camera']";

	public static final String IFRAME = "//iframe[@id='_hjRemoteVarsFrame']";

	public static final String QUESTION_CONTENT_TEXTBOX = "//input[@type='text' and @name='question_content']";
	public static final String CREATE_POLL_BUTTON = "//input[@id='btAsknow']";
	public static final String POLL_SETTINGS_BUTTON = "//a[contains(text(),'Poll Settings')]";
	public static final String VOTING_TIME_CHECKBOX = "//input[@id='timeLimit']";
	public static final String PARTICIPANTS_CHECKBOX = "//input[@id='participant']";
	public static final String ALLOW_EDIT_ANSWER_CHECKBOX = "//input[@id='is_allowUpdate']";
	public static final String OPTIMIZE_POLL_LINK_CHECKBOX = "//input[@id='customUrl']";
	public static final String POLL_URL_TEXTBOX = "//input[@id='myUrl']";
	public static final String REQUIRED_EMAIL_CHECKBOX = "//input[@id='is_require_email']";
	public static final String REQUIRED_NAME_CHECKBOX = "//input[@id='is_require_name']";
	public static final String ALLOW_VOTER_ADD_ANWSER_CHECKBOX = "//input[@id='is_add_answer']";
	public static final String HIDE_VOTING_RESULT_CHECKBOX = "//input[@id='is_hidden']";
	public static final String SET_VOTING_LIMIT_CHECKBOX = "//input[@id='voteLimit']";
	public static final String SET_PASSWORD_CHECKBOX = "//input[@id='cbpasswordVote']";

	public static final String SIGN_IN_MENU = "//a[@id='Loginform']";
	public static final String ZINGPOLL_ICON = "//img[@class='navbar-brand__img']";
	public static final String NEW_USER_RADIO_BUTTON = "//input[@id='newUser']";
	public static final String FULL_NAME_TEXTBOX = "//input[@id='reg-userName']";
	public static final String REGISTER_EMAIL_TEXTBOX = "//input[@id='reg-email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='reg-password']";
	public static final String RE_PASSWORD_TEXTBOX = "//input[@id='reg-retype-password']";
	public static final String AGREE_TERM_CHECKBOX = "//input[@id='reg-agree-cb']";
	public static final String REGISTER_BUTTON = "//input[@id='reg-submit']";
	public static final String SIGN_IN_POPUP = "//div[@class='modal-dialog modal_dialog_custom']//div[@class='modal-content']";
	public static final String ACTIVATION_LINK_MESSAGE = "//div[@class='alert-message-container']/div[contains(.,'An activation link has sent to your mailbox.')]";

	public static final String SIGN_IN_EMAIL_TEXTBOX = "//input[@id='loginEmail']";
	public static final String SIGN_IN_PASSWORD_TEXTBOX = "//input[@id='loginPassword']";
	public static final String SIGN_IN_BUTTON = "//button[@id='button-login']";
	public static final String FULL_NAME_TEXT = "//div[@class='username' and contains(text(),'%s')]";

	public static final String ACTIVATE_ACCOUNT_SUCCESS_MESSAGE = "//div[@class='alert-message-container']/div[contains(.,'Your account is activated successfully!')]";
}
