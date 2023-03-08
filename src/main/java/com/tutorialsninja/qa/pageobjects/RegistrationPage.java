package com.tutorialsninja.qa.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	
	WebDriver driver;
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH,using="//input[@name='firstname']")
	WebElement firstname;
	@FindBy(how=How.XPATH,using="//input[@name='lastname']")
	WebElement lastname;
	@FindBy(how=How.XPATH,using="//input[@name='email']")
	WebElement email1;
	@FindBy(how=How.XPATH,using="//input[@name='telephone']")
	WebElement telephone;
	@FindBy(how=How.XPATH,using="//input[@name='password']")
	WebElement password;
	@FindBy(how=How.XPATH,using="//input[@name='confirm']")
	WebElement confirmpassword;
	@FindBy(how=How.XPATH,using="//input[@type='checkbox']")
	WebElement agree;
	@FindBy(how=How.XPATH,using="//input[@type='submit']")
	WebElement submit;
	@FindBy(how=How.XPATH,using="//div[@id='content']/h1")
	WebElement accountcreated;
	@FindBy(linkText="Continue")
	WebElement continuepostsuccess;
	@FindBy(linkText="Edit your account information")
	WebElement newmysccountwindow;
	@FindBy(how=How.XPATH,using="//div[@class='list-group'] //a[contains(text(),('Newsletter'))]")
	WebElement newsletterrightcolumn;
	@FindBy(how=How.XPATH,using="//div[@class='col-sm-10']/label[2]/input]")
	WebElement newsletterradiobtn;
	
	
	public   void registeringdetails(String fname, String lname, String email, String tele, String pass, String cpass ) {
		
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		email1.sendKeys(email);
		telephone.sendKeys(tele);
		password.sendKeys(pass);
		confirmpassword.sendKeys(cpass);
		agree.click();
		submit.click();
		
	}
	public String accountCreated() {
		return accountcreated.getText();
	}
	
	public void continueAfterRegister()
	{
		
		 continuepostsuccess.click();
	}
	public String newMyaccountWindow() {
		return newmysccountwindow.getText();
	}
	
	public void newsletterrightcolumn()
	{
		newsletterrightcolumn.click();
	}
	public boolean newsletterradiobtn()
	{
		return newsletterrightcolumn.isSelected();
	}
	
	
}
