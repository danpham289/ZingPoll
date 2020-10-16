package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	private static WebDriver driver;
	protected final Log log;

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		Browsers browser = Browsers.valueOf(browserName.toUpperCase());

		if (browser == Browsers.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser == Browsers.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == Browsers.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Please input correct browser name.");
		}
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String url) {
		Browsers browser = Browsers.valueOf(browserName.toUpperCase());

		if (browser == Browsers.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser == Browsers.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == Browsers.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Please input correct browser name.");
		}
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);

		return driver;
	}

	protected void closeBrowserAndDriver(WebDriver driver) {

//		try {
//			if (driver != null) {
//				driver.quit();
//			}
//
//			String osName = System.getProperty("os.name").toLowerCase();
//			log.info("OS is " + osName);
//			log.info("Browser Driver is " + driver.toString().toLowerCase());
//			String cmd = null;
//			if (driver.toString().toLowerCase().contains("chromedriver")) {
//				if (osName.contains("mac")) {
//					cmd = "pkill chromedriver";
//				} else if (osName.contains("window")) {
//					cmd = "taskkill /F/FI \"IMAGENAME eq chromedriver*\"";
//				}
//				
//			} else if (driver.toString().toLowerCase().contains("geckodriver")) {
//				if (osName.contains("mac")) {
//					cmd = "pkill geckodriver";
//				} else if (osName.contains("window")) {
//					cmd = "taskkill /F/FI \"IMAGENAME eq geckodriver*\"";
//				}
//
//			}
//			
//			Process process = Runtime.getRuntime().exec(cmd);
//			process.waitFor();
//
//			log.info("-------------QUIT BROWSER SUCCESS------------------");
//		} catch (Exception e) {
//			log.info(e.getMessage());
//		}
		
		try {
			// get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = null;
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt();
	}

	public void sleepInSeconds(int timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected int longTimeOut = 30;

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
//			if (condition == true) {
//				log.info("----------PASSED-------");
//			} else {
//				log.info("----------FAILED-------");
//			}
			Assert.assertTrue(condition);
			log.info("----------PASSED-------");
		} catch (Throwable e) {
			pass = false;
			log.info("----------FAILED-------");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
//
//			if (condition == false) {
//				log.info("----------PASSED-------");
//			} else {
//				log.info("----------FAILED-------");
//			}
			Assert.assertFalse(condition);
			log.info("----------PASSED-------");
		} catch (Throwable e) {
			pass = false;
			log.info("----------FAILED-------");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
