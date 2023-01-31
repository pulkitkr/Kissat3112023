package com.android.RingPayPages;

import org.openqa.selenium.By;

public class InstaLoanOptionalJourney {

	
	public static By objQRScanner = By.xpath("(//*[@text='UPI']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup)[3]");
	public static By objScanAndPayToastMsg = By.xpath("//android.widget.Toast");
	public static By objQRScannPage = By.xpath("//*[@text='Scan any QR code to pay']");
	public static By objInstaOfferBanner = By.xpath("//*[@text='New Offer Alert!' or @text='Insta Loan']");
	public static By objAmountEnter = By.xpath("//*[@text='₹']/following-sibling::android.widget.EditText");
	public static By objPayNowBtn = By.xpath("(//*[@text='Pay Now'])[1]");
	public static By objEnterSecurityPin = By.xpath("//*[@text='Enter Transaction Security PIN']");
	public static By objPINField = By.xpath("(//*[@text='Enter Transaction Security PIN']/following-sibling::android.widget.TextView)[1]");
	public static By objContinueBtn = By.xpath("//*[@text='Continue']");
	public static By objPaymentDone = By.xpath("//*[@text='Payment Done!']");
	public static By objPayEarly = By.xpath("//*[@text='Pay Early']");
	public static By objUPIPaymentMethod = By.xpath("//*[@text='Add UPI ID']");
	public static By objNetBankingAndDebitCardPaymentMethod = By.xpath("//*[@text='Net Banking & Debit Card']");
	public static By objBankTransferPaymentMethod = By.xpath("//*[@text='Bank Transfer']");
	public static By objPaymentViaUPIPaymentMethod = By.xpath("//*[@text='Payment via UPI']");
	public static By objMoreBottomTab = By.xpath("//*[@text='More']");
	public static By objBankTransfer = By.xpath("//*[@text='Bank Transfer']");
	public static By objAddBankAccount = By.xpath("//*[@text='Add Bank Account']");
	public static By objBBPSFlowElectricity = By.xpath("//*[@text='Electricity']");
	public static By objSelectBiller = By.xpath("//*[@text='Select your biller']");
	public static By objElectricsConsumerNo = By.xpath("//*[@text='Enter your details']");
	public static By objEnterCustomerID = By.xpath("//*[@resource-id='param1']");
	public static By objFetchBill = By.xpath("//*[@resource-id='fetch-bill-button']");
	public static By objPayAmount = By.xpath("//*[@text='Pay Amount']");
	public static By objPayBtn = By.xpath("//*[@text='Pay']");
	public static By objPaymentInProgress = By.xpath("//*[@text='Payment in Progress']");
	public static By objPayWithRingLimit = By.xpath("//*[@text='Pay With Ring Limit']");
	public static By objProcessing = By.xpath("//*[@text='Processing your Payment']");
	public static By objPaymentSuccessfull = By.xpath("//*[@text='Payment successful!']");
	public static By objNoBtn = By.xpath("//*[@text='No']");
	public static By objMakePaymentPage = By.xpath("//*[@text='Make Payment']");
	public static By objMakePaymentAmount = By.xpath("//*[@text='₹']/following-sibling::android.widget.EditText");
	public static By objCircularBtn = By.xpath("//*[@resource-id='circularButtonFull']");
	public static By objExistingBankAccount = By.xpath("//*[@text='Existing Bank Account']");
	public static By objApprovedStatusProceed = By.xpath("//*[@text='Approved']/preceding-sibling::android.view.ViewGroup");
	public static By objEnterSecurityPinforElectricityBill = By.xpath("//*[@text='Enter Security Pin']");
	public static By objPINFieldElectricityBill = By.xpath("//*[@resource-id='cursor']");
	public static By objYesBtn = By.xpath("//*[@text='YES']");
	public static By objCurrentSpending = By.xpath("//*[@resource-id='bannerDetails']/following-sibling::android.widget.TextView");
	public static By objFathersNameBasicDetails = By.xpath("//*[@text='Father Name']");
	public static By objBasicDetailsPage = By.xpath("//*[@text='Basic Details']");
	public static By objOnlyFathersNameField = By.xpath("//*[@text='Basic Details']/preceding-sibling::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView");
	public static By objContinueStepBtn = By.xpath("//*[@text='Continue']");
	public static By objIallowCreditBureauChecksText = By.xpath("//*[contains(@text,'I allow')]");
	public static By objIhearbyConfirmText = By.xpath("//*[contains(@text,'Read')]");
	public static By objCreditBureauChecksHyperlinks = By.xpath("//*[@text='I allow Ring to run credit bureau checks and provide \r\n"
			+ " consent for CKYC']");
	public static By objCreditBureauConsentPopup = By.xpath("//*[@text='Credit Bureau Consent']");
	public static By objOkGotItCreditBureaupopup = By.xpath("//*[@text='Ok, Got It!']");
	
	public static By objIallowCheckBox = By.xpath("//*[contains(@text,'I allow')]/preceding-sibling::android.widget.CheckBox");
	public static By objReadmoreCheckBox = By.xpath("//*[contains(@text,'Read more')]/(preceding-sibling::android.widget.CheckBox)[2]");
	public static By objSelectAddressDropDown = By.xpath("//*[@text='Select Address']");
	public static By objIdeclaretheAboveAddressCheckBox = By.xpath("//*[contains(@text,'I declare')]/preceding-sibling::android.widget.CheckBox");
	public static By objAddCommunicationAddress = By.xpath("//*[@text='Add Communication Address']");
	
	//First EMI due in 15 days
	public static By objFirstEMIDueIN15Days = By.xpath("//*[@text='First EMI due in']");
	public static By objSecondInstallmentWaiver = By.xpath("//*[@text='2nd installment']");
	public static By objWaiverMsg = By.xpath("//*[@text='2nd installment']/following-sibling::android.widget.TextView[contains(@text,'waiver')]");
	
	//EMI 60 days
	public static By objDayEMI = By.xpath("//*[contains(@text,'installment')]/following-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup/child::android.widget.TextView[contains(@text,'Pay')]");
	public static By objEMIDueDays = By.xpath("//*[@text='Insta Loan']/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/(descendant::android.widget.TextView)[3]");
	public static By objInstallment = By.xpath("//*[contains(@text,'installment')]");
	public static By objTenureDates = By.xpath("//*[contains(@text,'installment')]/following-sibling::android.widget.TextView[contains(@text,'Pay before')]"); 
	public static By objAmtToBePaid = By.xpath("(//*[contains(@text,'installment')]/following-sibling::android.widget.TextView)[5]");
	public static By objPayNowBtnAtHomePage = By.xpath("//*[@text='Pay Now']");
	public static By objUPITab = By.xpath("//*[@text='UPI']");
	
	public static By objScanAndPayPage = By.xpath("//*[@text='Use your credit limit to complete this payment.']");
	public static By objYesButton = By.xpath("//*[@text='Yes']");
	
	public static By objExistingBankAcc = By.xpath("//*[@text='Existing Bank Account']");
	public static By objAddBankDetails = By.xpath("//*[@text='Add Bank Details']");
	public static By objRingLimitpausedPopup = By.xpath("//*[@text='IMPORTANT']");
	public static By objRingLimitPausedTextPopup = By.xpath("//*[@text='IMPORTANT']/following-sibling::android.widget.TextView");
	public static By objCKYCConsentHyperlink = By.xpath("//*[@text='CKYC Consent']");
	public static By objRejected = By.xpath("//*[@text='Rejected']");
	public static By objApproved = By.xpath("//*[@text='Approved']");
	public static By objUnableToAddAcc = By.xpath("//*[@text=' Unable to add your bank account. ']");
	public static By objOkGotIt2 = By.xpath("//*[@text='Ok Got It']");
	public static By objContinue1 = By.xpath("//*[@text='Just 1 step ahead']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView");
	public static By objAddNewBankAccount = By.xpath("//*[@text='Add New Bank Account']");
	public static By objRingLimitPaused = By.xpath("(//*[@text='Limit Paused']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView)[3]");
	public static By objDisbursedStatus = By.xpath("//*[@text='Disbursed']");
	public static By objInProcessStatus = By.xpath("//*[@text='In Process']");
	public static By objAvailableLimit = By.xpath("//*[@resource-id='headerMsg']");
	public static By objReApplyInstaLoan = By.xpath("//*[@text='ReApply for Insta Loan!']");
	public static By objExistingBankContinue = By.xpath("//*[@text='Approved']/preceding-sibling::android.view.ViewGroup");
	public static By objLimitPausedForMandatoryEligiblityType = By.xpath("(//*[@text='Limit Paused']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView)[3]");
	public static By objSorryMsgInstaLoanApproved = By.xpath("//*[@text='Sorry!']");
	public static By objSorryMsgInstaLoanApprovedCurrentlyUnavailable = By.xpath("//*[@text='This mode of payment is currently unavailable']");
}
