package pageUIs.zingpoll;

public class UpdatePollPageUI {
	public static final String QUESTION_CONTENT_TEXTBOX = "//input[@name='question']";
	public static final String DYNAMIC_OPTION_TEXTBOX = "//input[@name='optionAnswer' and not (contains(@placeholder,'Add new option')) and @id='inputtext%s']";
	public static final String DYNAMIC_OPTION_IMAGE_ICON = "//input[@name='optionAnswer' and not (contains(@placeholder,'Add new option')) and @id='inputtext%s']/following-sibling::span[@class='btn-file span-camera']";
	public static final String DYNAMIC_OPTION_DELETE_ICON = "//span[@id='deleteButton%s']";
	public static final String NEW_OPTION_TEXTBOX = "//input[@name='optionAnswer' and @placeholder ='Add new option']";
	public static final String NEW_OPTION_IMAGE_ICON = NEW_OPTION_TEXTBOX + "/following-sibling::span[@class='btn-file span-camera']";
	public static final String UPDATE_POLL_BUTTON = "//input[@id='btnSubmitUpdate']";
	
	
	
}
