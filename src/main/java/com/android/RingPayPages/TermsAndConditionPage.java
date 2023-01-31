package com.android.RingPayPages;

import org.openqa.selenium.By;

public class TermsAndConditionPage {

//  Terms and Condition Text in Web Page
	public static By objTAndCondition = By.xpath("//*[@text='Terms And Conditions']");
//  Terms Of Service
	public static By objTermsOfService = By.xpath("//*[contains(@text,'Terms')]");
	
	//Accept Btn
	public static By objAcceptAndPayBtn = By.xpath("//*[contains(@text,'Accept')]/parent::android.view.ViewGroup");
	public static By objAcceptText = By.xpath("//*[contains(@text,'Accept')]");
	
	//Toast msg
	public static By objToastMsg = By.xpath("//android.widget.Toast");
	
	//Okay Got It
	public static By objOkBtn = By.xpath("//*[@text='Okay Got it']");
	
	//GPS Not Now Button
	public static By objNotNowBtn = By.xpath("//*[@text='Not Now']");
	
	//GPS Allow Button
	public static By objGpsAllowBtn = By.xpath("//*[@text='Allow']");
	
	//Set Pin header
	public static By objSetPin = By.xpath("//*[@text='Set Pin']");
	
	//Promocode
	public static By objEligibleMsg = By.xpath("//*[@text='You are eligible for approved limit of']");
	
	public static By objEligibleAmt = By.xpath("(//*[@text='You are eligible for approved limit of']/following-sibling::android.widget.TextView)[1]");
	
	public static By objFirstBillBannerMsg = By.xpath("//*[@text='First bill will be generated 15 days after your first transaction.']");
	
	public static By objIAcceptMsg = By.xpath("//*[@text='I accept the Ring’s Terms & Conditions and IT Act 2000. ']");
}
