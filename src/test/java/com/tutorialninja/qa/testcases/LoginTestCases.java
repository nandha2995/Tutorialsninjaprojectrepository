package com.tutorialninja.qa.testcases;



import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.utilities.XLUtility;


public class LoginTestCases extends Base{

	public WebDriver driver;
	
	@Test(priority=1,dataProvider="loginData")
	public void VerfiyLoggingWithValidCredentials(String email, String pass) throws IOException
	{
		driver = initializedriver();
		driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
		driver.findElement(By.xpath("//a[contains(text(),(\"Login\"))]")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
	}
	
	
	
	@Test(priority=2,dataProvider="loginData")
	public void VerifyLoggingWithInvalidCredentials(String email, String pass) throws IOException
	{
		driver = initializedriver();
		driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
		driver.findElement(By.xpath("//a[contains(text(),(\"Login\"))]")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(randomstring());
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
	}
	
	@DataProvider(name="loginData")
	public Object[][] getData() throws IOException
	{
		String path =testDataPath;
		XLUtility xl = new XLUtility(path);
		int rows =xl.getRowCount("login");
		int cols = xl.getCellCount("login", 1);
		Object loginData[][] = new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				loginData[i][j]=xl.getCellData("login", i+1, j);
			}
		}
		return loginData;
	
	}
}
