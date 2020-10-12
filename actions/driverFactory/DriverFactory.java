package driverFactory;

import commons.Browsers;

public class DriverFactory {
	public static DriverManager getBrowserDriverManager(String browserName) {
		Browsers browser = Browsers.valueOf(browserName.toUpperCase());
		DriverManager driverManager = null;
		
		if(browser==Browsers.CHROME) {
			driverManager = new ChromeDriverManager();
		}else if (browser==Browsers.FIREFOX) {
			driverManager = new FirefoxDriverManager();
		}else {
			System.out.println("Please input valid browser.");
		}		
		return driverManager;
	}

}
