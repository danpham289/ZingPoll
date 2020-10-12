package commons;

import java.util.Random;

public class GlobalConstants {
	public static final String ROOT_FOLDER = System.getProperty("user.dir");
	public static final String URL = "https://test.zingpoll.com/";
	public static final String NUMBER_QUESTION_CONTENT = "What is your favorite number?";
	public static final String COUNTRY_QUESTION_CONTENT = "What is your favorite country?";
	public static final String UPDATE_COUNTRY_QUESTION_CONTENT = "Which country do you want to travel?";
	public static final String OPTION1 = "VIETNAM";
	public static final String UPDATE_OPTION1 = "AUSTRALIA";
	public static final String OPTION1_IMAGE_FILE = "VNimage.png";
	public static final String OPTION2 = "UNITED STATES";
	public static final String UPDATE_OPTION2 = "KOREA";
	public static final String OPTION2_IMAGE_FILE = "USimage.jpg";
	public static final String OPTION3 = "JAPAN";
	public static final String UPDATE_OPTION3 = "CHINA";
	public static final String UPDATE_OPTION3_IMAGE_FILE = "CHINAimage.jpeg";
	public static final String OPTION4 = "NEW ZEALAND";
	public static final String UPDATE_OPTION4 = "FRANCE";
	public static final String UPDATE_OPTION4_IMAGE_FILE = "FRANCEimage.gif";
	public static final String OPTION5 = "SINGAPORE";
	public static final String EMAIL = "testing"+randomNumber()+"@mailinator.com";
	public static final String ANONYMOUS_EMAIL = "anonymous@mailinator.com";
	public static final String EMAIL_URL = "https://www.mailinator.com/";	
	public static final String FULLNAME = "Testing";	
	public static final String PASSWORD ="123456";
		
	public static long LONG_TIMEOUT = 30;
	public static long SHORT_TIMEOUT = 5;
	
	
	public static int randomNumber() {
		Random random = new Random();
		return random.nextInt();
	}
}
