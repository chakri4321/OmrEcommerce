package com.omrEcommerceWithBaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	static WebDriver driver;
	static Select select;
	static Actions actions;
	static TakesScreenshot screenshot;
	static JavascriptExecutor executor;
	static WebDriverWait wait;

	public static void closeBroswer() {
		driver.quit();
	}

	public static void createCellAndSetData(String sheetName, int rownum, int cellnmum, String data)
			throws IOException {
		File file = new File("C:\\EclipseNewWorkspace\\OmrEcommerce\\Excel\\omrEcommerce.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.createCell(cellnmum);
		cell.setCellValue(data);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}

	public static void updateCellData(String sheetName, int rownum, int cellnmum, String oldData, String newData)
			throws IOException {
		File file = new File("C:\\EclipseNewWorkspace\\OmrEcommerce\\Excel\\omrEcommerce.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnmum);

		String value = cell.getStringCellValue();

		if (value.equals(oldData)) {
			cell.setCellValue(newData);
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);

	}

	public static String getCellData(String sheetName, int rownum, int cellnmum) throws IOException {
		String res = null;

		File file = new File("C:\\EclipseNewWorkspace\\OmrEcommerce\\Excel\\omrEcommerce.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnmum);
		CellType type = cell.getCellType();
		switch (type) {
		case STRING:
			res = cell.getStringCellValue();

			break;

		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
				res = dateFormat.format(dateCellValue);

			} else {
				double numericCellValue = cell.getNumericCellValue();
				long round = Math.round(numericCellValue);
				if (round == numericCellValue) {
					res = String.valueOf(round);
				} else {
					res = String.valueOf(numericCellValue);
				}
			}

			break;

		default:
			break;
		}
		return res;
	}

	public static void switchToFrameById(String frameId) {
		driver.switchTo().frame(frameId);
	}

	public static void scroll(WebElement element) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView()", element);
	}

	public static void dragAndDrop(WebElement source, WebElement dest) {
		actions = new Actions(driver);
		actions.dragAndDrop(source, dest).perform();

	}

	public static void screenshot(String fileName) throws IOException {
		screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(
				"C:\\Users\\Velmurugan\\eclipse-workspace\\FrameworkClass930AM\\images\\" + fileName + ".png"));
	}

	public static void screenshot(String fileName, WebElement element) throws IOException {
		File screenshotAs = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(
				"C:\\Users\\Velmurugan\\eclipse-workspace\\FrameworkClass930AM\\images\\" + fileName + ".png"));
	}

	public static void switchToChildWindow() {
		String windowHandle = driver.getWindowHandle();

		Set<String> windowHandles = driver.getWindowHandles();
		for (String eachWindowId : windowHandles) {
			if (!windowHandle.equals(eachWindowId)) {
				driver.switchTo().window(eachWindowId);
				break;
			}
		}
	}

	// 99%--->value
	public static String getDomPropertyValue(WebElement element) {
		String domProperty = element.getDomProperty("value");
		return domProperty;
	}

	// 1%--?
	public static String getDomPropertyValue(WebElement element, String attributeName) {
		String domProperty = element.getDomProperty(attributeName);
		return domProperty;
	}

	public static void elementVisibilityOf(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driverWait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void implicitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));

	}

	public static void explicitWait(int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

	}

	public static WebElement explicitWaitVisible(int sec, String xpath) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return element;
	}

	public static WebElement explicitWaitClickable(int sec, String xpath) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

	public static WebElement explicitWaitClickableByElement(int sec, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		return webElement;

	}

	public static WebElement explicitWaitPresenceOfElement(int sec, String xpath) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return element;
	}

	public static Boolean explicitWaitForDisappear(WebElement element) {
		Boolean until = wait.until(ExpectedConditions.stalenessOf(element));
		return until;
	}

	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public static void browserLaunch() {
		driver = new EdgeDriver();
	}

	public static void enterApplnUrl(String url) {
		driver.get(url);
	}

	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static void CloseBrowser() {
		driver.quit();
	}

	public static long executionTime() {
		long timeMillis = System.currentTimeMillis();
		return timeMillis;
	}

	public static void elementSendKeysJs(WebElement element, String data) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value','" + data + "')", element);

	}

	public static void elementSendKeysEnter(WebElement element, String data) {
		elementVisibilityOf(element);

		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data, Keys.ENTER);
		}
	}

	public static void elementSendKeys(WebElement element, String data) {
		elementVisibilityOf(element);

		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data);
		}
	}

	public static void elementClick(WebElement element) {
		elementVisibilityOf(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.click();
		}
	}

	public static int getNumber(String res) {
		int number = Integer.parseInt(res);
		return number;
	}

	public static String getApplnTitle() {
		String title = driver.getTitle();
		return title;
	}

	public static WebElement findLocatorById(String attributeValue) {
		WebElement element = driver.findElement(By.id(attributeValue));
		return element;
	}

	public static WebElement findLocatorByName(String attributeValue) {
		WebElement element = driver.findElement(By.name(attributeValue));
		return element;
	}

	public static WebElement findLocatorByClassName(String attributeValue) {
		WebElement element = driver.findElement(By.className(attributeValue));
		return element;
	}

	public static WebElement findLocatorByXpath(String exp) {
		WebElement element = driver.findElement(By.xpath(exp));
		return element;
	}

	public static List<WebElement> findElements(String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		return elements;
	}

	public static String getApplnUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static String elementGetText(WebElement element) {
		elementVisibilityOf(element);
		String text = null;

		if (elementIsDisplayed(element)) {
			text = element.getText();
		}
		return text;
	}

	public static boolean elementIsSelected(WebElement element) {
		elementVisibilityOf(element);
		boolean selected = element.isSelected();
		return selected;
	}

	public static boolean elementIsEnabled(WebElement element) {
		elementVisibilityOf(element);
		boolean enabled = element.isEnabled();
		return enabled;
	}

	public static boolean elementIsDisplayed(WebElement element) {
		elementVisibilityOf(element);
		boolean displayed = element.isDisplayed();
		return displayed;
	}

	public static void selectOptionByText(WebElement element, String data) {
		elementVisibilityOf(element);
		select = new Select(element);
		select.selectByVisibleText(data);
	}

	public static void selectOptionByValue(WebElement element, String value) {
		elementVisibilityOf(element);
		select = new Select(element);
		select.selectByValue(value);
	}

	public static void selectOptionByIndex(WebElement element, int index) {
		elementVisibilityOf(element);
		select = new Select(element);
		select.selectByIndex(index);
	}

}
