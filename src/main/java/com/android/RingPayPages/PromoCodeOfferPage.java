package com.android.RingPayPages;

import org.openqa.selenium.By;

public class PromoCodeOfferPage {

//  Offer Text
	public static By objOffer = By.xpath("//*[@text='Offer']");
//  Approval Limit Amount
	public static By objApprovalLimitAmount = By.xpath("(//*[@text='You are eligible for approved limit of']/following-sibling::android.widget.TextView)[1]");
// Fee Details
	public static By objFeeDetails = By.xpath("//*[@text='Fee Details']");
//  Approval Limit Text
	public static By objApprovalLimitText = By.xpath("//*[@text='You are eligible for approved limit of']");
//  Check Box
	public static By objCheckBox = By.xpath("//*[@class='android.widget.CheckBox']");
//  TermsAndCondition
	public static By objTermsAndCondition = By
			.xpath("//*[@resource-id='termsCheck']/following-sibling::android.widget.TextView");
//  Accept Offer Text
	public static By objAcceptOffer = By.xpath("//*[contains(@text,'Accept')]");
	
	//Upi Id
	public static By objUpiIdMerchant = By.xpath("(//*[@text='Amount']/following-sibling::android.widget.TextView)[3]");
	
	//Amount
	public static By objAmount = By.xpath("(//*[@text='Amount']/following-sibling::android.widget.TextView)[1]");

	//Repayment Date Text
	public static By objRepaymentDateText = By.xpath("(//*[@text='Amount']/following-sibling::android.widget.TextView)[4]");
	
	//Repayment date
	public static By objrepaymentDate = By.xpath("(//*[@text='Amount']/following-sibling::android.widget.TextView)[5]");
	
	//=======================================================================================================================
	//offer page
	public static By objMerchantOfferHeader = By.xpath("//*[contains(@text,'Woohoo')]");
	public static By objPayingType = By.xpath("//*[contains(@text,'Paying')]");
	public static By objUPIId = By.xpath("//*[contains(@text,'Paying')]/(following-sibling::android.widget.TextView)[1]");
	public static By objMerchantPayAmt = By.xpath("//*[contains(@text,'Paying')]/(following-sibling::android.widget.TextView)[2]");
	public static By objRBIMsg = By.xpath("(//*[@resource-id='nbfcView']/android.widget.TextView)[1]");
	public static By objRBIMg2 = By.xpath("(//*[@resource-id='nbfcView']/android.widget.TextView)[2]");
	public static By objFeeDetail = By.xpath("//*[contains(@text,'Fee Details')]");
	public static By objFeeDetailPage = By.xpath("//*[contains(@text,'Bill')]");
	public static By objFeeDetailsCrossBtn = By.xpath("//*[contains(@text,'Fee Details')]/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/descendant::android.widget.ImageView");
	public static By objToastMsg = By.xpath("//*[@class='android.widget.Toast']");
	public static By objCheckInternetConnectionPage = By.xpath("//*[contains(@text,' Check')]");
	public static By objLocationPopup = By.xpath("//*[contains(@text,'Location')]");
	public static By objLocationAllowBtn = By.xpath("//*[contains(@text,'Allow')]");
	public static By objLocationNotNowBtn = By.xpath("//*[contains(@text,'Not')]");
	public static By objLocationOkBtn = By.xpath("//*[contains(@text,'OK')]");
	public static By objEnableLocation = By.xpath("//*[contains(@text,'Enable Location')]");
	public static By objEnableLocBtn = By.xpath("//*[contains(@text,'Enable Location')]/parent::android.view.ViewGroup");
	public static By objAcceptAndPayBtn = By.xpath("//*[contains(@text,'Accept')]/parent::android.view.ViewGroup");
	public static By objUnlockRingLimit = By.xpath("//*[contains(@text,'You’ve unlocked a RING limit')]");
	public static By objApprovedRinglimitAmt = By.xpath("(//*[@resource-id='offerCardMerchantAmountView']/android.widget.TextView)[2]");
	public static By objGoToHomePage = By.xpath("//*[@text='Go to Homepage']");
	public static By objCloseBtn = By.xpath("//*[@text='Close']");
}
