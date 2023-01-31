package com.android.RingPayPages;

import org.openqa.selenium.By;

public class InstaLoanTransactionHistoryPage {

	public static By objTransactionChip = By.xpath("//*[@text='Transactions']");
	public static By objHistoryHeader = By.xpath("//*[@text='History']");
	public static By objTransactionHistoryTab = By.xpath("//*[@text=' Transaction History']");
	public static By objTransactionList = By.xpath("//*[@text=' Transaction History']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/descendant::android.widget.TextView");
	public static By objLoanHistoryTab = By.xpath("//*[@text='Loan History']");
	public static By objHamburgerTab = By.xpath("//*[@text='Limit Paused' or @text='Available Limit']/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup");
	public static By objFilterOption = By.xpath("//*[@text='History']/parent::android.view.ViewGroup/(following-sibling::android.view.ViewGroup)[1]");
	public static By objTimePeriod = By.xpath("//*[@text='Time Period']");
	public static By objThisMonthChip = By.xpath("//*[@text='This Month']");
	public static By objLastMonth = By.xpath("//*[@text='Last Month']");
	public static By objCustomDate = By.xpath("//*[@text='Custom Date']");
	public static By objLoanType = By.xpath("//*[@text='Loan Types']");
	public static By objAll = By.xpath("//*[@text='All']");
	public static By objActiveLoan = By.xpath("//*[@text='Active Loan']");
	public static By objClosedLoan = By.xpath("//*[@text='Closed Loan']");
	public static By objClearBtn = By.xpath("//*[@text='Clear']");
	public static By objApplyBtn = By.xpath("//*[@text='Apply']");
	public static By objTimePeriodSelect (int i) {
		return	By.xpath("(//*[@text='Time Period']/following-sibling::android.view.ViewGroup)["+i+"]");
	}
	public static By objLoanTypeSelect (int j) {
		return	By.xpath("(//*[@text='Loan Types']/following-sibling::android.view.ViewGroup)["+j+"]");
	}
	public static By objDatePicker = By.xpath("//*[@resource-id='com.ideopay.user.debug:id/mtrl_picker_title_text']");
	public static By objOkBtn = By.xpath("//*[@resource-id='com.ideopay.user.debug:id/confirm_button']");
	public static By objLoanHistoryActiveStatus = By.xpath("//*[@text='Active']");
	public static By objLoanHistoryActiveStatusViewDetails = By.xpath("//*[@text='View Details']");
	public static By objLoanHistoryActiveStatusPayEMI = By.xpath("//*[@text='Pay EMI']");
	public static By objLoanHistoryActiveStatusViewDetailsLoanDetailsPage = By.xpath("//*[@text='Loan Details']");
	public static By objLoanHistoryActiveStatusPayEMIPaymentPage = By.xpath("//*[@text='Payment']");
	public static By objLoanHistoryClosedStatus = By.xpath("//*[@text='Closed']");
	public static By objLoanHistoryClosedDownloadNocBtn = By.xpath("//*[@text='Download NOC']");
	public static By objNOCPdf = By.xpath("//android.widget.ImageButton/following-sibling::android.widget.TextView");
	public static By objAllLoanDetail = By.xpath("//android.view.ViewGroup/descendant::android.widget.TextView");
	public static By objPayNoBtn = By.xpath("//*[@text='Pay Now']");
	public static By objThreeDotForDownloadPDF = By.xpath("//android.widget.ImageView[@content-desc='More options']");
	public static By objDownloadBtn = By.xpath("//*[@text='Download']");
	public static By objDownloadPDFToastMsg = By.xpath("//android.widget.Toast");
	
	//Loan Details Break up
	public static By objLoanDetailViewFeeDetailsBtn = By.xpath("//*[@text='View Fee Details']");
	public static By objFeeDetailsHeader = By.xpath("//*[@text='Fee Details']");
	public static By objLoanAmountText = By.xpath("//*[@text='Loan Amount']");
	public static By objLoanAmount = By.xpath("//*[@text='Loan Amount']/(following-sibling::android.widget.TextView)[1]");

	public static By objTotalFeesChargesText = By.xpath("//*[@text='Total Fees Charges']");
	public static By objTotalFeesCharges = By.xpath("//*[@text='Total Fees Charges']/(following-sibling::android.widget.TextView)[1]");
	
	public static By objProcessingFeesText = By.xpath("//*[@text='Processing fees']");
	public static By objProcessingFees = By.xpath("//*[@text='Processing fees']/(following-sibling::android.widget.TextView)[1]");
	
	public static By objGSTText = By.xpath("//*[@text='GST']");
	public static By objGST = By.xpath("//*[@text='GST']/(following-sibling::android.widget.TextView)[1]");
	
	public static By objDisbursalAmountText = By.xpath("//*[@text='Disbursal Amount']");
	public static By objDisbursalAmount = By.xpath("//*[@text='Disbursal Amount']/(following-sibling::android.widget.TextView)[1]");
	
	public static By objIacceptCheckBox = By.xpath("//*[@text='I accept the Terms & Conditions']/preceding-sibling::android.widget.CheckBox");
	public static By objOkGotItBtn = By.xpath("//*[@text='Ok, Got It']");
}
