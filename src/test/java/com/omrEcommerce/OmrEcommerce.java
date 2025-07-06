package com.omrEcommerce;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OmrEcommerce {
	public String orderNum;

	private void search() throws InterruptedException, AWTException {
		WebDriver driver = new EdgeDriver();

		driver.get("https://www.omrbranch.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement txtUser = driver.findElement(By.id("email"));
		txtUser.sendKeys("chakri654654@gmail.com");

		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.sendKeys("Sambana@123");

		WebElement btnLogin = driver.findElement(By.xpath("//button[@value='login']"));
		btnLogin.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement cartIcon = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-shopping-cart']")));
		cartIcon.click();

		while (true) {

			List<WebElement> closeIcons = driver.findElements(By.xpath("//i[@class='fa fa-close']"));

			if (closeIcons.isEmpty()) {
				break;
			}
			for (int i = 0; i < closeIcons.size(); i++) {
				try {
//					Thread.sleep(2000);
					// Refresh the list again here to avoid stale elements
					List<WebElement> refreshedIcons = driver.findElements(By.xpath("//i[@class='fa fa-close']"));
					if (i >= refreshedIcons.size()) {
						break; // Avoid IndexOutOfBounds
					}
					WebElement element = refreshedIcons.get(i);
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();

					// Wait for the item to disappear
					wait.until(ExpectedConditions.stalenessOf(element));

					WebElement cartIcon1 = wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-shopping-cart']")));
					cartIcon1.click();

				} catch (StaleElementReferenceException e) {
					System.out.println("Stale element at index " + i + ". Retrying...");
					i--;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		try {
			WebElement closeBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Close ']")));
			closeBtn.click();

		} catch (Exception e) {
			System.out.println("Cart already empty or popup not present");

			WebElement closeBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Close ']")));
			closeBtn.click();
		}

		WebElement txtSearch = driver.findElement(By.id("search"));
		txtSearch.sendKeys("fruit");

		WebElement btnSearch = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		btnSearch.click();

		WebElement btnAdd = driver.findElement(By.xpath(
				"//a[@class='hover1 font16 fontsemibold colorWhite bgTheme px-4 py-1 radius50 dyna_btn addBtn-37']"));
		btnAdd.click();

		WebElement btnAddQuantity = driver.findElement(By.xpath("(//button[text()='Add'])[18]"));
		btnAddQuantity.click();

		WebElement btnCross = driver
				.findElement(By.xpath("(//img[@src='https://www.omrbranch.com/front/images/cross.png'])[11]"));
		btnCross.click();

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement btnAddToCart = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.omrbranch.com/my-cart']")));
		Thread.sleep(500);
		btnAddToCart.click();

//		WebElement btnAddToCart1 = driver.findElement(By.xpath("//a[@href='https://www.omrbranch.com/my-cart']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnAddToCart1);

//		 WebElement btnAddToCart2 =
//		driver.findElement(By.xpath("//a[@href='https://www.omrbranch.com/my-cart']")); 
//		  btnAddToCart2.click();

		WebElement txtAddress = driver.findElement(By.xpath("//div[@data-toggle='modal']"));
		txtAddress.click();

		WebElement txtAddressType = driver.findElement(By.id("address_type"));
		Select select = new Select(txtAddressType);
		select.selectByIndex(3);

		WebElement txtFirstName = driver.findElement(By.xpath("(//input[@name='first_name'])[1]"));
		txtFirstName.sendKeys("Ganesh");

		WebElement txtLastName = driver.findElement(By.name("last_name"));
		txtLastName.sendKeys("Ganesh");

		WebElement txtContact = driver.findElement(By.name("mobile"));
		txtContact.sendKeys("9876543210");

		WebElement txtHouseNo = driver.findElement(By.name("apartment"));
		txtHouseNo.sendKeys("6-41");

		WebElement txtAddressLine = driver.findElement(By.name("address"));
		txtAddressLine.sendKeys("sri guru pg, thoraipakkam");

		WebElement btnState = driver.findElement(By.name("state"));
		Select selectState = new Select(btnState);
		selectState.selectByVisibleText("Tamil Nadu");

		WebElement btnCity = driver.findElement(By.name("city"));
		Select selectCity = new Select(btnCity);
		selectCity.selectByVisibleText("Chennai");

		WebElement txtZipCode = driver.findElement(By.name("zipcode"));
		txtZipCode.sendKeys("530065");

		WebElement btnSave = driver.findElement(By.xpath("(//button[text()='Save'])[3]"));
		btnSave.click();

		Thread.sleep(3000);
		WebElement dropdownBox = driver.findElement(By.id("payment_type"));

		Select selectPayment = new Select(dropdownBox);
		selectPayment.selectByVisibleText("Debit Card");

		WebElement rdoVisa = driver.findElement(By.xpath("//label[@for=\"visa_card\"]"));
		rdoVisa.click();

		WebElement txtCardNumber = driver.findElement(By.xpath("//input[@name='card_no']"));
		txtCardNumber.sendKeys("5555555555552222");

		WebElement ddnMonth = driver.findElement(By.id("month"));
		Select selectMonth = new Select(ddnMonth);
		selectMonth.selectByVisibleText("July");

		WebElement ddnYear = driver.findElement(By.id("year"));
		Select selectYear = new Select(ddnYear);
		selectYear.selectByVisibleText("2030");

		WebElement txtCvv = driver.findElement(By.name("cvv"));
		txtCvv.sendKeys("666");

		WebElement btnPlaceOrder = driver.findElement(By.id("placeOrder"));
		btnPlaceOrder.click();

		// Robot robot = new Robot(); // robot.keyPress(KeyEvent.VK_DOWN); //
//		robot.keyRelease(KeyEvent.VK_DOWN); // robot.keyPress(KeyEvent.VK_ENTER); //
//		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	private void menu() throws InterruptedException {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-gpu");
//		options.addArguments("--disable-software-rasterizer");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--no-sandbox");
//		options.addArguments("--remote-allow-origins=*");

//		WebDriver driver = new ChromeDriver(options);
		WebDriver driver = new EdgeDriver();

		driver.get("https://www.omrbranch.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		WebElement txtUser = driver.findElement(By.id("email"));
		txtUser.sendKeys("chakri654654@gmail.com");

		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.sendKeys("Sambana@123");

		WebElement btnLogin = driver.findElement(By.xpath("//button[@value='login']"));
		btnLogin.click();

		WebElement btnGrocery = driver.findElement(By.xpath("//h3[contains(text(),'Grocery')]"));
		btnGrocery.click();

		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		WebElement cartIcon = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-shopping-cart']")));
		cartIcon.click();

		while (true) {

			List<WebElement> closeIcons = driver.findElements(By.xpath("//i[@class='fa fa-close']"));

			if (closeIcons.isEmpty()) {
				break;
			}
			for (int i = 0; i < closeIcons.size(); i++) {
				try {
//					Thread.sleep(2000);
					// Refresh the list again here to avoid stale elements
					List<WebElement> refreshedIcons = driver.findElements(By.xpath("//i[@class='fa fa-close']"));
					if (i >= refreshedIcons.size()) {
						break; // Avoid IndexOutOfBounds
					}
					WebElement element = refreshedIcons.get(i);
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();

					// Wait for the item to disappear
					wait.until(ExpectedConditions.stalenessOf(element));

					WebElement cartIcon1 = wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-shopping-cart']")));
					cartIcon1.click();

				} catch (StaleElementReferenceException e) {
					System.out.println("Stale element at index " + i + ". Retrying...");
					i--;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		

		try {
			WebElement closeBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Close ']")));
			closeBtn.click();

		} catch (Exception e) {
			System.out.println("Cart already empty or popup not present");

			WebElement closeBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Close ']")));
			closeBtn.click();
		}*/
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
				WebElement ddnCategories = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='CATEGORIES ']")));
		ddnCategories.click();

		WebElement ddnGrocery = driver.findElement(By.xpath("//a[text()='Grocery']"));
		ddnGrocery.click();

		WebElement btnFruiAndNuts = driver
				.findElement(By.xpath("//a[@href='https://www.omrbranch.com/category/groceries/product/4']"));
		btnFruiAndNuts.click();

		WebElement btnAdd = driver.findElement(By.xpath("(//a[text()='Add'])[1]"));
		btnAdd.click();

		WebElement btnAddQuantity = driver.findElement(By.id("cart-53"));
		btnAddQuantity.click();
		WebElement btnCross = driver
				.findElement(By.xpath("(//img[@src='https://www.omrbranch.com/front/images/cross.png'])[1]"));
		btnCross.click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement btnAddToCart = wait1.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.omrbranch.com/my-cart']")));
		Thread.sleep(500);
		btnAddToCart.click();

//		WebElement btnAddToCart = driver.findElement(By.xpath("//a[@href='https://www.omrbranch.com/my-cart']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnAddToCart);

//		WebElement btnAddToCart = driver.findElement(By.xpath("//a[@href='https://www.omrbranch.com/my-cart']"));
//		btnAddToCart.click();

		WebElement txtAddress = driver.findElement(By.xpath("//div[@data-toggle='modal']"));
		txtAddress.click();

		WebElement txtAddressType = driver.findElement(By.id("address_type"));
		Select select = new Select(txtAddressType);
		select.selectByIndex(3);

		WebElement txtFirstName = driver.findElement(By.xpath("(//input[@name='first_name'])[1]"));
		txtFirstName.sendKeys("Ganesh");

		WebElement txtLastName = driver.findElement(By.name("last_name"));
		txtLastName.sendKeys("Ganesh");

		WebElement txtContact = driver.findElement(By.name("mobile"));
		txtContact.sendKeys("9876543210");

		WebElement txtHouseNo = driver.findElement(By.name("apartment"));
		txtHouseNo.sendKeys("6-41");

		WebElement txtAddressLine = driver.findElement(By.name("address"));
		txtAddressLine.sendKeys("sri guru pg, thoraipakkam");

		WebElement btnState = driver.findElement(By.name("state"));
		Select selectState = new Select(btnState);
		selectState.selectByVisibleText("Tamil Nadu");

		WebElement btnCity = driver.findElement(By.name("city"));
		Select selectCity = new Select(btnCity);
		selectCity.selectByVisibleText("Chennai");

		WebElement txtZipCode = driver.findElement(By.name("zipcode"));
		txtZipCode.sendKeys("530065");

		WebElement btnSave = driver.findElement(By.xpath("(//button[text()='Save'])[3]"));
		btnSave.click();

		Thread.sleep(3000);
		WebElement dropdownBox = driver.findElement(By.id("payment_type"));

		Select selectPayment = new Select(dropdownBox);
		selectPayment.selectByVisibleText("Debit Card");

		WebElement rdoVisa = driver.findElement(By.xpath("//label[@for=\"visa_card\"]"));
		rdoVisa.click();

		WebElement txtCardNumber = driver.findElement(By.xpath("//input[@name='card_no']"));
		txtCardNumber.sendKeys("5555555555552222");

		WebElement ddnMonth = driver.findElement(By.id("month"));
		Select selectMonth = new Select(ddnMonth);
		selectMonth.selectByVisibleText("June");

		WebElement ddnYear = driver.findElement(By.id("year"));
		Select selectYear = new Select(ddnYear);
		selectYear.selectByVisibleText("2028");

		WebElement txtCvv = driver.findElement(By.name("cvv"));
		txtCvv.sendKeys("555");

		WebElement btnPlaceOrder = driver.findElement(By.id("placeOrder"));
		btnPlaceOrder.click();

		WebElement txtUser1 = driver.findElement(By.id("email"));
		txtUser1.sendKeys("chakri654654@gmail.com");

		WebElement txtPassword1 = driver.findElement(By.id("pass"));
		txtPassword1.sendKeys("Sambana@123");

		WebElement btnLogin1 = driver.findElement(By.xpath("//button[@value='login']"));
		btnLogin1.click();

//		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(60));

		WebElement ddnProfile = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='username']")));
		ddnProfile.click();

//		WebElement btnMyAccount = driver.findElement(By.xpath("//a[text()='My Account']"));
//		btnMyAccount.click();

		WebElement brnGrocerySettings = driver.findElement(By.xpath("//a[text()='Grocery Settings']"));
		brnGrocerySettings.click();

		WebElement btnOrders = wait.until(ExpectedConditions.elementToBeClickable(By.id("v-pills-orders-tab")));
		btnOrders.click();

		WebElement btnMoreDetails = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//a[@class='font16 colorTheme border1 radius50 px-3 py-1'])[1]")));
		btnMoreDetails.click();

		WebElement id = driver.findElement(By.xpath("(//span[@class=\"font16 fontNormal color36\"])[1]"));
		String orderNum = id.getText();
		System.out.println(orderNum);

	}

	public static void main(String[] args) throws InterruptedException, AWTException {
		OmrEcommerce omr = new OmrEcommerce();
		omr.menu();
		// omr.search();

	}
}
