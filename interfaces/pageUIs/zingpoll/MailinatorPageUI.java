package pageUIs.zingpoll;

public class MailinatorPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='addOverlay']";
	public static final String GO_BUTTON = "//button[@id='go-to-public']";
	public static final String ACTIVATION_EMAIL_LINK = "//a[contains(.,'[ZingPoll] Activation Email')]";
	public static final String BODY_EMAIL_IFRAME = "//iframe[@id='msg_body']";
	public static final String ACTIVATE_BUTTON = "//a[contains(text(),'ACTIVATE NOW')]";
	
	public static final String MANAGE_POLL_BUTTON = "//a[@class='mcnButton ' and contains(text(),'MANAGE POLL')]";	
	public static final String MANAGE_POLL_PASSWORD_TEXT = "//span[contains(text(),'Your management password:')]/following-sibling::span";	
	public static final String CREATE_POLL_SUCCESS_EMAIL_LINK = "//tbody/tr[1]//a[contains(.,'[ZingPoll] Created Poll Successfully')]";
	public static final String QUESTION_CONTENT_IN_CREATE_POLL_SUCCESS_EMAIL = "//td[@class='mcnTextContent']//span[contains(text(),'Question content is')]/b";
	
}
