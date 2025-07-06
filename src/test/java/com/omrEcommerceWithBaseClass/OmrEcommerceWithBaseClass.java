package com.omrEcommerceWithBaseClass;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class OmrEcommerceWithBaseClass extends BaseClass {
	@BeforeClass
	public static void beforeClass() {
		browserLaunch();
		enterApplnUrl("https://www.omrbranch.com/");
		maximizeWindow();
		implicitWait(60);
	}

	@AfterClass
	public static void afterClass() {
		// CloseBrowser();
	}

	@Before
	public void before() {

		System.out.println("Initialization time " + executionTime());
	}

	@After
	public void after() {
		System.out.println("Ending time " + executionTime());
	}

	@Test
	public void tc1() throws IOException {
		WebElement userName = findLocatorById("email");
		elementSendKeys(userName, getCellData("Sheet1", 1, 0));

		WebElement password = findLocatorById("pass");
		elementSendKeys(password, getCellData("Sheet1", 1, 1));

		WebElement login = findLocatorByXpath("//button[@value='login']");
		elementClick(login);
	}

	@Test
	public void tc2() throws InterruptedException {
		WebElement cartIcon = explicitWaitClickable(60, "//i[@class='fa fa-shopping-cart']");
		elementClick(cartIcon);

		while (true) {
			List<WebElement> closeIcons = findElements("//i[@class='fa fa-close']");

			if (closeIcons.isEmpty()) {
				break;
			}

			for (int i = 0; i < closeIcons.size(); i++) {
				try {
					List<WebElement> refreshedCloseIcons = findElements("//i[@class='fa fa-close']");
					if (i >= refreshedCloseIcons.size()) {
						break;
					}

					WebElement elementClose = refreshedCloseIcons.get(i);
					WebElement explicitWaitClickableByElement = explicitWaitClickableByElement(60, elementClose);
					elementClick(explicitWaitClickableByElement);

					explicitWaitForDisappear(elementClose);

					WebElement cartIcon1 = explicitWaitClickable(60, "//i[@class='fa fa-shopping-cart']");
					elementClick(cartIcon1);
				} catch (StaleElementReferenceException e) {
					System.out.println("Stale element at index " + i + ". Retrying...");
					i--;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		try {
			WebElement closeBtn = explicitWaitClickable(60, "//a[text()=' Close ']");
			elementClick(closeBtn);
		} catch (Exception e) {
			System.out.println("Cart already empty or popup not present");
			WebElement closeBtn = explicitWaitClickable(60, "//a[text()=' Close ']");
			elementClick(closeBtn);
		}

		WebElement ddnCategories = explicitWaitClickable(60, "//a[text()='CATEGORIES ']");
		elementClick(ddnCategories);

		WebElement ddnGrocery = findLocatorByXpath("//a[text()='Grocery']");
		elementClick(ddnGrocery);

		WebElement btnFruiAndNuts = findLocatorByXpath(
				"//a[@href='https://www.omrbranch.com/category/groceries/product/4']");
		elementClick(btnFruiAndNuts);

		WebElement btnAdd = findLocatorByXpath("(//a[text()='Add'])[1]");
		elementClick(btnAdd);

		WebElement btnAddQuantity = findLocatorById("cart-53");
		elementClick(btnAddQuantity);

		WebElement btnCross = findLocatorByXpath("(//img[@src='https://www.omrbranch.com/front/images/cross.png'])[1]");
		elementClick(btnCross);

	}

	@Test
	public void tc3() throws InterruptedException, IOException {
		WebElement btnGoToCart = explicitWaitClickable(60, "//a[@href='https://www.omrbranch.com/my-cart']");
		Thread.sleep(500);
		elementClick(btnGoToCart);

		WebElement txtAddress = findLocatorByXpath("//div[@data-toggle='modal']");
		elementClick(txtAddress);

		WebElement txtAddressType = findLocatorById("address_type");
		String cellData = getCellData("Sheet1", 1, 2);
		selectOptionByIndex(txtAddressType,getNumber(cellData) );
		
		WebElement txtFirstName = findLocatorByXpath("(//input[@name='first_name'])[1]");
		elementSendKeys(txtFirstName, getCellData("Sheet1", 1, 3));

		WebElement txtLastName = findLocatorByName("last_name");
		elementSendKeys(txtLastName, getCellData("Sheet1", 1, 4));

		WebElement txtContact = findLocatorByName("mobile");
		elementSendKeys(txtContact, getCellData("Sheet1", 1, 5));

		WebElement txtHouseNo = findLocatorByName("apartment");
		elementSendKeys(txtHouseNo, getCellData("Sheet1", 1, 6));

		WebElement txtAddressLine = findLocatorByName("address");
		elementSendKeys(txtAddressLine, getCellData("Sheet1", 1, 7));

		WebElement btnState = findLocatorByName("state");
		selectOptionByText(btnState, getCellData("Sheet1", 1, 8));

		WebElement btnCity = findLocatorByName("city");
		selectOptionByText(btnCity, getCellData("Sheet1", 1, 9));

		WebElement txtZipCode = findLocatorByName("zipcode");
		elementSendKeys(txtZipCode, getCellData("Sheet1", 1, 10));

		WebElement btnSave = findLocatorByXpath("(//button[text()='Save'])[3]");
		elementClick(btnSave);

		Thread.sleep(3000);

		WebElement paymentDropdownBox = findLocatorById("payment_type");
		selectOptionByText(paymentDropdownBox, getCellData("Sheet1", 1, 11));

		WebElement rdoVisa = findLocatorByXpath("//label[@for=\"visa_card\"]");
		elementClick(rdoVisa);

		WebElement txtCardNumber = findLocatorByXpath("//input[@name='card_no']");
		elementSendKeys(txtCardNumber, getCellData("Sheet1", 1, 12));

		WebElement ddnMonth = findLocatorById("month");
		selectOptionByText(ddnMonth, getCellData("Sheet1", 1, 13));

		WebElement ddnYear = findLocatorById("year");
		selectOptionByText(ddnYear, getCellData("Sheet1", 1, 14));

		WebElement txtCvv = findLocatorByName("cvv");
		elementSendKeys(txtCvv, getCellData("Sheet1", 1, 15));

		WebElement btnPlaceOrder = findLocatorById("placeOrder");
		elementClick(btnPlaceOrder);

	}
}
