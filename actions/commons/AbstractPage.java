package commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.zingpoll.AbstractPageUI;

public abstract class AbstractPage {
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getAlertText(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeysToAlertText(WebDriver driver, String text) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());

	}

	public void swicthToWindowById(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void swicthToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public WebElement find(WebDriver driver, String xpathValue) {
		return driver.findElement(byXpath(xpathValue));
	}

	public List<WebElement> finds(WebDriver driver, String xpathValue) {
		return driver.findElements(byXpath(xpathValue));
	}

	public By byXpath(String xpathValue) {
		return By.xpath(xpathValue);
	}

	public void clickToElement(WebDriver driver, String xpathValue) {
		find(driver, xpathValue).click();
	}

	public String getDynamicLocator(String value, String... dynamicValue) {
		return String.format(value, (Object[]) dynamicValue);
	}

	public void clickToElement(WebDriver driver, String locator, String... dynamicValue) {
		find(driver, getDynamicLocator(locator, dynamicValue)).click();
	}

	public void sendKeysToElement(WebDriver driver, String xpathValue, String text) {
		element = find(driver, xpathValue);
		element.clear();
		element.sendKeys(text);
	}

	public void sendKeysToElement(WebDriver driver, String locator, String text, String... dynamicValue) {
		element = find(driver, getDynamicLocator(locator, dynamicValue));
		element.clear();
		element.sendKeys(text);
	}

	public void selectItemInDropdown(WebDriver driver, String xpathValue, String itemValue) {
		select = new Select(find(driver, xpathValue));
		select.selectByVisibleText(itemValue);
	}

	public String getSelectedItemTextInDropdown(WebDriver driver, String xpathValue, String itemValue) {
		select = new Select(find(driver, xpathValue));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		// 1 - Click vào thẻ (cha) để xổ ra tất cả các item con
		find(driver, parentLocator).click();
		sleepInSecond(1);

		// 2 - Chờ cho tất cả các item con được load ra
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

		// Đưa tất cả các item trong dropdown vào 1 list để kiểm tra
		elements = finds(driver, childItemLocator);

		// 3 - Chạy qua tất cả các giá trị đang có trong list
		for (WebElement item : elements) {
			// 4 - Kiểm tra xem text của các giá trị có item nào bằng vs text mong muốn ko
			if (item.getText().equals(expectedItem)) {
				// 5 - Scroll xuống đến đúng item này
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				// 6 - Click vào cái item này
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void selectMultiItemsInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String[] expectedItems) {
		// 1 - Click vào thẻ (cha) để xổ ra tất cả các item con
		find(driver, parentLocator).click();
		sleepInSecond(1);

		// 2 - Chờ cho tất cả các item con được load ra
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

		// Đưa tất cả các item trong dropdown vào 1 list để kiểm tra
		elements = finds(driver, childItemLocator);

		// 3 - Chạy qua tất cả các giá trị đang có trong list
		int i = 0;
		for (WebElement item : elements) {
			// 3a -Chạy qua các giá trị expectedItems
			for (String expectedItem : expectedItems) {
				// 4 - Kiểm tra xem text của các giá trị có item nào bằng vs text mong muốn ko
				if (item.getText().equals(expectedItem)) {
					// 5 - Scroll xuống đến đúng item này
					jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);

					// 6 - Click vào cái item này
					item.click();
					sleepInSecond(1);
					i++;
				}
				// 7 - Check số items được select = số expected items

				if (i == expectedItems.length) {
					break;
				}
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String xpathValue, String attributename) {
		return find(driver, xpathValue).getAttribute(attributename);
	}

	public String getElementText(WebDriver driver, String locator, String... dynamicValue) {
		return find(driver, getDynamicLocator(locator, dynamicValue)).getText();
	}

	public String getElementText(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).getText();
	}

	public int countElementNumber(WebDriver driver, String xpathValue) {
		return finds(driver, xpathValue).size();

	}

	public int countElementNumber(WebDriver driver, String locator, String... dynamicValue) {
		return finds(driver, getDynamicLocator(locator, dynamicValue)).size();

	}

	public void checkToCheckbox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValue) {
		return find(driver, getDynamicLocator(locator, dynamicValue)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isSelected();
	}

	public void switchToFrame(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		driver.switchTo().frame(element);

	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doubleClickToElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClickToElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void drapAndDrop(WebDriver driver, String xpathValueSoure, String xpathValueTarget) {
		element = find(driver, xpathValueSoure);
		element1 = find(driver, xpathValueTarget);
		action = new Actions(driver);
		action.dragAndDrop(element, element1).build().perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String xpathValue, Keys key) {
		element = find(driver, xpathValue);
		action = new Actions(driver);
		action.sendKeys(element, key).perform();
	}

	public Object executeJavascriptToBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);

	}

	public Object executeJavascriptToElement(WebDriver driver, String javaSript, String xpathValue) {
		element = find(driver, xpathValue);
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript, element);

	}

	public void scrollToBottomPage(WebDriver driver) {
		executeJavascriptToBrowser(driver, "window.scrollBy(0,document.body.scrollHeight)");

	}

	public void scrollToElement(WebDriver driver, String xpathValue) {
		executeJavascriptToElement(driver, "arguments[0].scrollIntoView(true);", xpathValue);

	}

	public void highlightElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");

	}

	public void clickToElementByJS(WebDriver driver, String xpathValue) {
		executeJavascriptToElement(driver, "arguments[0].click();", xpathValue);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... dynamicValue) {
		executeJavascriptToElement(driver, "arguments[0].click();", getDynamicLocator(locator, dynamicValue));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathValue, String value) {
		executeJavascriptToElement(driver, "arguments[0].setAttribute('value', '" + value + "')", xpathValue);
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove) {
		executeJavascriptToElement(driver, "arguments[0].removeAttribute('" + attributeRemove + "');", xpathValue);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) executeJavascriptToBrowser(driver, "return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public boolean isImageLoaded(WebDriver driver, String xpathValue) {
		return (Boolean) executeJavascriptToElement(driver, "return arguments[0].complete && " + "typeof arguments[0].naturalWidth != \"undefined\" && " + "arguments[0].naturalWidth > 0", xpathValue);
	}

	public boolean isImageLoaded(WebDriver driver, String locator, String... dynamicValue) {
		System.out.println(getDynamicLocator(locator, dynamicValue).toString());
		return (Boolean) executeJavascriptToElement(driver, "return arguments[0].complete && " + "typeof arguments[0].naturalWidth != \"undefined\" && " + "arguments[0].naturalWidth > 0", getDynamicLocator(locator, dynamicValue));
	}

	public void waitElementPresence(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(xpathValue)));

	}

	public void waitElementVisible(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitElementsVisible(WebDriver driver, String xpathValue) {
		elements = finds(driver, xpathValue);
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	public void waitElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		element = find(driver, getDynamicLocator(locator, dynamicValue));
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitElementClickable(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitElementClickable(WebDriver driver, String locator, String... dynamicValue) {
		element = find(driver, getDynamicLocator(locator, dynamicValue));
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitElementInvisible(WebDriver driver, String xpathValue) {
		element = find(driver,xpathValue);
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));

	}

	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

	}

	public void uploadMultipleFiles(WebDriver driver, String... filenames) {
		String filePath = GlobalConstants.ROOT_FOLDER + getDirectorySlash("uploadFiles");
		String fileFullPath = "";
		for (String filename : filenames) {
			fileFullPath = fileFullPath + filePath + filename + "\n";
		}
		fileFullPath = fileFullPath.trim();
		// waitElementVisible(driver, AbstractPageUI.UPLOAD_FILE_TYPE);
		System.out.println(fileFullPath);
//		sendKeysToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fileFullPath);

		element = find(driver, AbstractPageUI.UPLOAD_FILE_TYPE);

		element.sendKeys(fileFullPath);

	}

	public String getDirectorySlash(String folderName) {
		if (isMac()) {
			return "/" + folderName + "/";
		} else {
			return "\\" + folderName + "\\";
		}
	}

	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean waitForJStoLoad(WebDriver driver) {

		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public int randomNumber(WebDriver driver) {
		Random num = new Random();
		return num.nextInt();
	}

	public void attachImageToOptionFromYourPicture(WebDriver driver,String optionImageFile) {
		waitElementVisible(driver, AbstractPageUI.PICTURE_POPUP);
		uploadMultipleFiles(driver, optionImageFile);
		waitElementClickable(driver, AbstractPageUI.SAVE_BUTTON);
		clickToElement(driver, AbstractPageUI.SAVE_BUTTON);
		waitForJStoLoad(driver);		
	}
	
	public void attachImageToOptionFromInternetPicture(WebDriver driver,String optionText) {
		waitElementVisible(driver, AbstractPageUI.PICTURE_POPUP);		
		waitElementVisible(driver, AbstractPageUI.INTERNET_SEARCH_TEXTBOX);
		sendKeysToElement(driver, AbstractPageUI.INTERNET_SEARCH_TEXTBOX, optionText);		
		clickToElement(driver, AbstractPageUI.SEARCH_BUTTON);		
		waitElementInvisible(driver, AbstractPageUI.LOADING_ICON);
		clickToElement(driver, AbstractPageUI.SEARCH_RESULT_IMAGE);
		waitElementClickable(driver, AbstractPageUI.SAVE_BUTTON);
		clickToElement(driver, AbstractPageUI.SAVE_BUTTON);
		waitForJStoLoad(driver);
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String xpathValue) {
		overrideGlobalTimeout(driver,2);
		elements = finds(driver, xpathValue);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		
		if(elements.size()==0) {
			return true;
		}else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDateSortedByDescending(WebDriver driver, String xpathValue) {
		
		ArrayList<Date> arraylist = new ArrayList<Date>();
		
		elements = finds(driver, xpathValue);
		
		for (WebElement element : elements) {
			arraylist.add(convertStringToDate(element.getText()));
		}		
		System.out.println("--- Date not sort----");		
		for (Date date : arraylist) {

			System.out.println(date);
		}		
		ArrayList<Date> sortedlist = new ArrayList<Date>();		
		for (Date child : arraylist) {
			sortedlist.add(child);
		}			
		Collections.sort(sortedlist);
		Collections.reverse(sortedlist);
				
		System.out.println("--- Date sorted----");		
		for (Date date : sortedlist) {

			System.out.println(date);
		}		
		return arraylist.equals(sortedlist);
	}
	
	public Date convertStringToDate(String dateInString) {
		System.out.println(dateInString);
		SimpleDateFormat formatter=new SimpleDateFormat("HH:mm MM/dd/yyyy");
		Date date=null;
		try {
			date=formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		return date;
	}
	
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private Alert alert;
	private List<WebElement> elements;
	private WebElement element;
	private WebElement element1;
	private Select select;
	private Actions action;
	private String osName = System.getProperty("os.name");

}
