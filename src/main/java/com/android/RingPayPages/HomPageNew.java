package com.android.RingPayPages;

import org.openqa.selenium.By;

public class HomPageNew {
	public static By objHome = By.xpath("//*[@text='Home']");
	public static By objCloseButton = By.xpath(
			"//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]]");
	public static By objTransactionfailMsg = By.xpath("//*[@text='Are you sure you want to cancel the Transaction?']");

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

	// Success button
	public static By objSuccessBtn = By.xpath("//*[@text='Success']");

	// Repayment success page
	public static By objRepaySuccess = By.xpath("//*[@text='Your payment was successful']");

	// Go to homepage button
	public static By objHomePage = By.xpath("//*[@text='Go to Homepage']");

	// View Details
	public static By objViewDetails = By.xpath("//*[@text='View Details']");

	// Pay due date
	public static By objPayDate = By.xpath("(//*[@text=\"Payment due date\"]/following-sibling::*)[1]");

	// total spends
	public static By objTotSpends = By.xpath("(//*[@text=\"Total Spends\"]//following-sibling::*)[1]");

	// Transaction fee
	public static By objTransacFee = By.xpath("(//*[@text=\"Transaction Fee\"]//following-sibling::*)[1]");

	// Total amt payable
	public static By objTotAmt = By.xpath("(//*[@text='Total Amount Payable']//following-sibling::*)[2]");
	
	//Loan History
	
	public static By objLoanHistory=By.xpath("//*[@text='Loan History']");
	
	public static By objHistory=By.xpath("//*[@text='History']");
	
	public static By linkViewDetails=By.xpath("//*[@text='View Details']");
	
	public static By txtLoanDetails=By.xpath("//*[@text='Loan Details']");
	
	public static By selectDifferentBankDropDown=By.xpath("//*[@resource-id='nb-elem']");
	
	public static By txtAllbanks=By.xpath("//*[@text='All banks']");
	
	public static By searchBank=By.xpath("//*[@class='android.widget.EditText']");
	
	public static By selectBank=By.xpath("//*[@text='State Bank of Hyderabad State Bank of Hyderabad']");
	
}





