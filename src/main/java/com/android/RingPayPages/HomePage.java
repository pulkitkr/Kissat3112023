package com.android.RingPayPages;

import org.openqa.selenium.By;

public class HomePage {

	public static By objHome = By.xpath("//*[@text='Home']");
	public static By objCloseButton = By.xpath(
			"//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]]");
	public static By objTransactionfailMsg = By.xpath("//*[@text='Are you sure you want to cancel the Transaction?']");

//	===================================================================
	// View All
	public static By viewAllBtn = By.xpath("//*[@text='View All']");

	// Home button
	public static By homeTab = By.xpath("//*[@text='Home']");

	// Transactions tab
	public static By transacTab = By.xpath("//*[@text='Transactions']");

	// Rewards Tab
	public static By rewardsTab = By.xpath("//*[@text='Rewards']");

	// More tab
	public static By moreTab = By.xpath("//*[@text='More']");

	// Ad header
	public static By objAdHeader = By.xpath("//*[@text='You’ve been chosen!']");

	// Ad close button
	public static By objAdCloseBtn = By.xpath(
			"//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]]");

	// Scan qr button
	public static By objScanQrBtn = By.xpath(
			"(((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.FrameLayout' and ./parent::*[./parent::*[./parent::*[@class='android.widget.FrameLayout']]]]]]]]]/*[@class='android.view.ViewGroup'])[2]/*[@class='android.view.ViewGroup'])[2]/*/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]]])[3]");

	// Scan any qr header
	public static By objScanQrHeader = By.xpath("//*[@text='Scan any QR code to pay']");

	// Money Text Field
	public static By objPaymentField = By.xpath("//*[@class='android.widget.EditText']");

	// Above limit error
	public static By objAboveLimitError = By.xpath(
			"//*[@text='You have entered a higher amount than your available limit. Re-enter the amount.' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Powered by RBI Registered NBFC']]]");

	// Pay Early button
	public static By objPayEarlyBtn = By.xpath("//*[@text='Pay Early']");

	// Repayment page
	public static By objRepaymentHeader = By.xpath("//*[@text='Payment']");

	// Amt to radio btn
	public static By objAmtToBeRadio = By.xpath("(//*[@class='android.widget.RadioButton'])[2]");

	// Amt to be paid text field
	public static By objAmtRepayText = By.xpath("//*[@class='android.widget.EditText']");

	// Net banking & Debit card option
	public static By objNetBankingOption = By.xpath("//*[@text='Net Banking & Debit Card']");

	// Please enter amount error
	public static By objFirstError = By.xpath("//*[@text='Please enter amount']");

	// Min amt error
	public static By objSecondError = By.xpath("//*[@text='Minimum amount should be ₹1']");

	// greater than amt error
	public static By objThirdError = By.xpath("//*[@text='Amount is greater than payable amount.']");

	// Valid amt error
	public static By objFourthError = By.xpath("//*[@text='Please enter valid amount']");

	// Netbanking option
	public static By objNetbanking = By.xpath("//*[@text='Netbanking All Indian banks']");

	// Select bank page
	public static By objSelectBankHeader = By.xpath("//*[@text='Select Bank']");

	// SBI bank
	public static By objSBIBank = By.xpath("//*[@class='android.view.View' and ./*[@text='SBI']]");

	// Pay now button
	public static By objPayNowBtn = By.xpath("//*[@text='Pay Now']");

	// Payment failed header
	public static By objPayFail = By.xpath("//*[@text='Payment failed']");

	// Try again btn
	public static By objTryAgain = By.xpath("//*[@text='Try Again']");

	// Cross button repayment
	public static By objCrossBtn = By
			.xpath("//*[@class='android.widget.Button' and ./parent::*[@id='header-wrapper']]");

	public static By objScanAnyQRToPay = By.xpath("//*[@text='Scan any QR code to pay']");

	/*-------------------------------------------------Banner Logic----------------------------------------------------------------*/

	public static By bannerTray = By.xpath(
			"(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.HorizontalScrollView']]]]/*[@class='android.widget.ImageView'])[1]");

	/*-------------------------------------Purple Banner---------------------------------------*/
	public static By purpleBannerText1 = By.xpath("//*[@text='Verify your document to get']");
	public static By purpleBannerText2 = By.xpath("//*[@text='higher limit & cashback up to ₹100']");
	public static By purpleBannerVerifyNow = By.xpath("//*[@text='Verify Now']");

	/*-------------------------------------Light Blue Banner---------------------------------------*/

	public static By lightBlueBannerTransferNow = By.xpath("//*[@text='Transfer Now']");
	public static By lightBluetext1 = By.xpath("//*[@text='Cash Crunch? ']");
	public static By lightBluetext2 = By.xpath("//*[@text='Transfer your RING']");
	public static By lightBluetext3 = By.xpath("//*[@text='limit to your bank account']");

	/*-------------------------------------Green Banner---------------------------------------*/

	public static By greenBannerText1 = By.xpath("//*[@text='RINGGGG! Transactions below']");
	public static By greenBannerText2 = By.xpath("//*[@text='₹200 are absolutely FREE!']");
	public static By greenBannerPayNow = By.xpath("//*[@text='Pay Now']");

	/*-------------------------------------yellow Banner---------------------------------------*/
	public static By yellowBannerText1 = By.xpath(
			"(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.HorizontalScrollView']]]]/*[@text])[1]");
	public static By yellowBannerText2 = By.xpath(
			"(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.HorizontalScrollView']]]]/*[@text])[2]");

	// public static By txt1yellowBanner=By.xpath("//*[@text='Up to 20%* cashback on
	// all transactions']");
	public static By txt1yellowBanner(String cashbackPercentage) {

		return By.xpath("//*[@text='Up to " + cashbackPercentage + "%* cashback on all transactions']");

	}

	// public static By txt2YellowBanner=By.xpath("//*[@text='Hurry! Offer valid
	// until 24th Nov']");
	public static By txt2YellowBanner(String expireDate, String month) {

		return By.xpath("//*[@text='Hurry! Offer valid until " + expireDate + "th " + month + "']");

	}

	public static By objScanAndPay = By.xpath(
			"(//*[contains(@text,'Reward')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup)[9]");

	/*--------------------------------------------Are you enjoying the app?-----------------------------------*/

	public static By txtAreYouEnjoyingApp = By.xpath("//*[@text='Are you enjoying the app?']");
	public static By noBtn = By.xpath("//*[@text='No']");

	public static By objAvailableLimit = By.xpath("//*[@text='Available Limit']");

	public static By ObjAvailableLimitAmount = By
			.xpath("(//*[@text='Available Limit']/following-sibling::android.widget.TextView)[1]");

	public static By objCurrentSpends = By.xpath("//*[@text='Current Spends']");

	public static By ObjCurrentSpendsAmount = By
			.xpath("//*[@text='Current Spends']/following-sibling::android.widget.TextView");

	public static By objRecentTransactions = By.xpath("//*[@text='Recent Transactions']");

	public static By objTransactions = By.xpath("//*[@text='Transactions']");

	public static By objViewAll = By.xpath("//*[@text='View All']");

	public static By objRewards = By.xpath("//*[@text='Rewards']");

	public static By objMore = By.xpath("//*[@text='More']");

	public static By objPayEarly = By.xpath("//*[@text='Pay Early']");
	
	public static By objTransactionHistory = By.xpath("//*[@text='History']");
	
	public static By tabBankTransfer=By.xpath("//*[@class='android.view.ViewGroup' and ./*[@text='Bank Transfer'] and ./*[@class='android.view.ViewGroup' and ./*[@class='android.view.ViewGroup']]]");
	
	public static By selectRecentTransaction=By.xpath("//*[@resource-id='getIcon']");
	
	public static By txtTransactionDetails=By.xpath("//*[@text='Transaction Details']");
	
	public static By getTransactionNumber=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]]/*[@text])[15]");

	public static By okaygotit=By.xpath("//*[@text='Ok Got It']");
	public static By okaygotitqrscan=By.xpath("//*[@text='Ok Got it']");
	
	public static By getTransactionNumberqrscan=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]]/*[@text])[9]");

	
	
	public static By objUPI=By.xpath("//*[@text='UPI']");
	
	public static By objGoToHomePage = By.xpath("//*[@text='Go to Homepage']");
	
	
	
}





