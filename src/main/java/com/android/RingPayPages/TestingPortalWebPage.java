package com.android.RingPayPages;

import org.openqa.selenium.By;

public class TestingPortalWebPage {
	
	public static By emailField = By.xpath("//input[@name='email']");
	
	public static By continueBtn=By.xpath("//button[text()='Continue']");
	
	public static By passwordField=By.xpath("//input[@name='password']");
	
	public static By otpField=By.xpath("//input[@name='otp']");
	
	public static By loginBtn=By.xpath("//button[text()='Log In']");
	
	public static By usersTab=By.xpath("//li[@data-original-title='Users']/a/span[text()='Users ']");
	
	public static By dropDownUserRef=By.xpath("//select[@id='search_cond']");
	///option[text()='User Reference Number']
	public static By selectMobileNo=By.xpath("//select[@id='search_cond']/option[text()='Mobile Number']");
	
	public static By enterMobileNo=By.xpath("//input[@name='search_term']");
	
	public static By searchIcon=By.xpath("//button[@name='submit']");
	
	public static By getUserRefrence=By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]");
	
	/*------------------------------------------------------------Pan NSDL-------------------------------------------------------------*/
	
	public static By othersTab=By.xpath("//span[text()='Others']");
	
	public static By panNSDLDataTab=By.xpath("//li[@ data-controller='pan_nsdl']/a/span[text()='Pan Nsdl Data']");
	
	public static By addNewIcon=By.xpath("//div[@class='heading-elements']/a");
	
	public static By scrollBar=By.xpath("//div[@class='mCSB_draggerRail']");
	//mCSB_dragger_bar
	
	public static By dragDown=By.xpath("//a[@class='mCSB_buttonDown']");
	
	public static By firstName=By.xpath("//input[@name='first_name']");
	
	public static By middleName=By.xpath("//input[@name='middle_name']");
	
	public static By lastName=By.xpath("//input[@name='last_name']");
	
	public static By pan=By.xpath("//input[@name='pan']");
	
	public static By panStatus=By.xpath("//select[@name='pan_status']");
	
	public static By panNsdlSubmitBtn=By.xpath("//input[@type='submit']");
	
/*-----------------------------------------------BNPL LIST---------------------------------------------*/
	
	public static By emailFieldNewPortal=By.xpath("//input[@name='email']");
	
	public static By continueNewButton=By.xpath("//button[@type='submit']");
	
	public static By tabBNPL=By.xpath("//span[text()='BNPL']");
	
	public static By tabBNPL_Line=By.xpath("//span[text()='BNPL Lines']");
	
	public static By iconBulkUpload=By.xpath("//span[text()='Bulk Upload']");
	
	public static By menuBulkuploadcashbackWhitelisting=By.xpath("//li[text()='Bulk upload cashback whitelisting']");
	
	public static By uploadExcelFile=By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root MuiDropzoneArea-icon']");
	
	public static By submitBtn=By.xpath("//span[text()='Submit']");
}

















