package com.tutorialninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.utilities.XLUtility;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegistrationPage;

public class RegisterTest extends Base {

	public WebDriver driver;
	HomePage hp;
	RegistrationPage registrationpage;

	@BeforeMethod
	public void setup() throws IOException {
		driver = initializedriver();
		hp = new HomePage(driver);
		hp.clickmyacc();
		registrationpage = hp.cliclregister();
	}

	@Test(priority = 1, dataProvider = "registrationData")
	public void verifyregistringwithallfields(String fname, String lname, String email, String tele, String pass,
			String cpass) throws IOException, InterruptedException {

		registrationpage.registeringdetails(fname, lname, email + randomstring(), tele, pass, cpass);
		Assert.assertEquals(registrationpage.accountCreated(), driver.getTitle(), "Registring Successful");
		registrationpage.continueAfterRegister();
		Assert.assertTrue(registrationpage.newMyaccountWindow().contains("Edit your account information"));

	}

	@Test(priority = 3, dataProvider = "registrationData")
	public void verifyoptionNOselectedforNewsletter(String fname, String lname, String email, String tele, String pass,
			String cpass) throws IOException {

		registrationpage.registeringdetails(fname, lname, email + randomstring(), tele, pass, cpass);
		Assert.assertEquals(registrationpage.accountCreated(), driver.getTitle(), "Registring Successful");
		registrationpage.continueAfterRegister();
		Assert.assertTrue(registrationpage.newMyaccountWindow().contains("Edit your account information"));
		registrationpage.newsletterrightcolumn();
		Assert.assertTrue(registrationpage.newsletterradiobtn());

	}

	@Test(priority = 2, dataProvider = "registrationData")
	public void verfiyexistingemailAddress(String fname, String lname, String email, String tele, String pass,
			String cpass) throws IOException, InterruptedException {

		registrationpage.registeringdetails(fname, lname, email, tele, pass, cpass);

		try {
			Assert.assertEquals(registrationpage.accountCreated(), driver.getTitle(), "Registring Successful");
		} catch (Exception e) {
			System.out.println("Warning Message: Email Already Registered");
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "registrationData")
	public Object[][] getData() throws IOException {
		String path = "./TestData/TestData.xlsx";
		XLUtility xl = new XLUtility(path);
		int rows = xl.getRowCount("register");
		int cols = xl.getCellCount("register", 1);
		Object registrationData[][] = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				registrationData[i][j] = xl.getCellData("register", i + 1, j);
			}
		}
		return registrationData;
	}

}
