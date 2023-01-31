package com.android.RingPayPages;

import org.openqa.selenium.By;

public class InstaLoanPage {

	//Admin panel login page
	public static By objEmail = By.xpath("//input[@name='email']");
	public static By objContinueBtn = By.xpath("//button[text()='Continue']");
	public static By objPasswordField = By.xpath("//input[@name='password']");
	public static By objOTPField = By.xpath("//input[@name='otp']");
	public static By objLoginBtn = By.xpath("//button[text()='Log In']");
	
	//Admin Panel Home Page User Tab
	public static By objUserTab = By.xpath("//span[text()='Users ']");
	public static By objSearchUser = By.xpath("//select[@id='search_cond']");
	public static By objSearchTerm = By.xpath("//input[@name='search_term']");
	public static By objSearchBtn = By.xpath("//button[@name='submit']");
	public static By objUserReferenceNo = By.xpath("(//th[text()='User Reference Number']/parent::tr/parent::thead/following-sibling::tbody/descendant::td)[1]");
	
	//Admin Panel Pan NSDL Tab
	public static By objScrollToPayment = By.xpath("//span[text()='Payment']");
	public static By dragfrom=By.xpath("//div[@class='mCSB_dragger_bar']");
	public static By dragDown=By.xpath("//a[@class='mCSB_buttonDown']");
	public static By objOthersTab = By.xpath("//span[text()='Others']");
	public static By objPanNSDLData = By.xpath("//span[text()='Pan Nsdl Data']");
	public static By objPanNoSerach = By.xpath("//input[@placeholder='Pan Number']");
	public static By objPanStatusSelect = By.xpath("//select[@id='pan_status']");
	public static By objAddPanCard = By.xpath("//a[@class='btn btn-success btn-xs heading-btn']");
	public static By objNameField = By.xpath("//input[@name='first_name']");
	public static By objMiddleNameField = By.xpath("//input[@name='middle_name']");
	public static By objLastNameField = By.xpath("//input[@name='last_name']");
	public static By objPanNoField = By.xpath("//input[@name='pan']");
	public static By objPanStatus = By.xpath("//select[@name='pan_status']");
	public static By objSubmitBtn = By.xpath("//input[@type='submit']");
	
	public static By btnOkaygotIt=By.xpath("//*[@text='Okay, Got It!']"); 
	
	//KYC Document
	public static By objKYCHeader = By.xpath("//*[@text='KYC Documents']");
	public static By objKYCFrontAadhar = By.xpath("(//*[@text='Front']/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup)[1]");
	public static By objKYCBackAadhar = By.xpath("(//*[@text='Back']/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup)[3]");
	public static By objCaptureAadharFrontImage = By.xpath("//*[@text='Capture Aadhaar Front']");
	public static By objCaptureBtn = By.xpath("(//*[@text='Capture Aadhaar Front']/ancestor::android.view.ViewGroup/following-sibling::android.widget.ScrollView/descendant::android.view.ViewGroup)[7]");
	public static By objBackAadharCaptureBtn = By.xpath("(//*[@text='Capture Aadhaar Back']/ancestor::android.view.ViewGroup/following-sibling::android.widget.ScrollView/descendant::android.view.ViewGroup)[7]");
	public static By objKYCCaptureContinueBtn = By.xpath("//*[@text='Continue']");
	public static By objKYCCAptureSelfie = By.xpath("//*[@text='Capture Selfie']");
	public static By objSelficeCaptureBtn = By.xpath("//*[@text='Place your face within the circle']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup");
	
	//Pan card number
	public static By objPanNoEnter = By.xpath("//*[@text='Pan Card Number']/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
	public static By objPanNoHeader = By.xpath("//*[@text='Pan Card Number']");
	
	//Instalone Banner
	public static By closeBanner=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]");
	public static By bannerTitle=By.xpath("//*[@text='Insta Loan']");
	
	//new offer alert
	public static By btnApplyNow=By.xpath("//*[@text and ./parent::*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]]");
	
	//Basic Details
	public static By txtBasicDetails=By.xpath("//*[@text='Basic Details']");
	
	public static By fieldFatherName=By.xpath("//*[@class='android.widget.EditText']");
	
	//Personal Details/term and conditions
	public static By textCKYCTerm=By.xpath("//*[@text='I allow Ring to run credit bureau checks and provide \r\n"
			+ " consent for CKYC']");
	
	public static By txtConfirmation=By.xpath("//*[@text='I, do hereby confirm that my annual household income,\r\n"
			+ " that is to say, the annual income of my family unit, (i.e..\r\n"
			+ ",husband/ wife, and unmarried children, is more than \r\n"
			+ "₹ 3,00,000/- …Read more']");
	
	public static By ckbCKYCTerm=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@class='android.widget.CheckBox'])[1]");
	
	public static By ckbConfirmation=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@class='android.widget.CheckBox'])[2]");
	
	public static By txtCKYC_Consent=By.xpath("//*[@text='CKYC Consent']");
	
	public static By txtCreditBureauConsent=By.xpath("//*[@text='Credit Bureau Consent']");
	
	public static By btnOkGotIt=By.xpath("//*[@text='Ok, Got It!']");
	
	// Permentent Address
	public static By txtPermanentAddress=By.xpath("//*[@text='Permanent Address']");
	
	public static By dropSelectAddress=By.xpath("//*[@text='Select Address']");
	
	public static By ckbDeclarationAddress=By.xpath("//*[@class='android.widget.CheckBox']");
	
	public static By txtDeclarationAddress=By.xpath("//*[@text='I declare the above address is same as my communication \r\n"
			+ "address']");
	
	public static By dropAddressSelection=By.xpath("//*[@text and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]]]]");
	
	//Add Communication Address
	
	public static By linkAddCommunicationAddress=By.xpath("//*[@text='Add Communication Address']");
	
	//offer
	public static By txtOffer=By.xpath("//*[@text='Offer']");
	
	public static By txtCongratulations=By.xpath("//*[@text='Congratulations']");
	
	public static By instaloanValidation=By.xpath("//*[@text='You are eligible for Insta Loan of']");
	
	public static By btnAddABankDetails=By.xpath("//*[@text='Add Bank Details']");
	
	
	
	//Add Bank Account
	
	public static By txtAddBankAccount=By.xpath("//*[@text='Add Bank Account']");
	
	public static By fieldIFSC_Code=By.xpath("//*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text='IFSC Code']]");
	
	public static By fieldAccountNumber=By.xpath("//*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text='Account Number']]");
	
	public static By fieldConfirmAccountNumber=By.xpath("//*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text='Confirm Account Number']]");
	
	public static By fieldAccountHolderName=By.xpath("//*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text='Account Holder Name']]");
	
	public static By dropAccountType=By.xpath("//*[@text='Account Type']");
	
	public static By savingAccountType=By.xpath("//*[@text='SAVINGS']");
	
	public static By btnContinue=By.xpath("//*[@text='Continue']");
	
	//Verify Bank Details
	
	public static By txtVerifyBankDetails=By.xpath("//*[@text='Verify Bank Details']");
	
	public static By btnConfirm=By.xpath("//*[@text='Confirm']");
	
	public static By accountNumber=By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@class='android.view.ViewGroup'])[3]/*[@text])[1]");
	
	public static By verifyIifscCode=By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@class='android.view.ViewGroup'])[3]/*[@text])[2]");
	
	public static By accountHolderName=By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@class='android.view.ViewGroup'])[3]/*[@text])[3]");
	
	//Existing Bank Account
	
	public static By txtExistingBankAcc=By.xpath("//*[@text='Existing Bank Account']");
	
	public static By btnAddNewBankAccount=By.xpath("//*[@text='Add New Bank Account']");
	
	public static By selectExistingAccount=By.xpath("//*[@class='android.view.ViewGroup' and ./*[@class='android.widget.ImageView']]");
	
	//Offer Page
	public static By ckbAcceptOfferPermission=By.xpath("//*[@class='android.widget.CheckBox']");
	
	public static By btnAcceptOffer=By.xpath("//*[@text='Accept Offer']");
	
	public static By txtImportant=By.xpath("//*[@text='IMPORTANT']");
	
	public static By btnConfirmOffer=By.xpath("//*[@text='Confirm Offer']");
	
	public static By btnHome=By.xpath("//*[@text='Home']");
	
	public static By txtDisbursementSuccessful=By.xpath("//*[@text='Insta Loan Disbursement Successful']");
	
	public static By btnPayNow=By.xpath("//*[@text='Pay Now']");
	
	// limit Paused
	public static By txtRingLimit=By.xpath("//*[@text='Your Ring limit is Paused while you have an ongoing Insta Loan.']");

	public static By txtLimitPaused=By.xpath("//*[@text='Limit Paused' and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='Your Ring limit is Paused while you have an ongoing Insta Loan.']]]");

	//Instaloane Banner
	
	public static By titleDisbursed=By.xpath("//*[@text and ./parent::*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text]] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]");
	
	public static By txtInstaLoanAmount=By.xpath("//*[@text='Insta Loan Amount']");
	
	public static By approvedLoanAmount=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@resource-id='instaLoanBannerReApply']]/*[@text])[3]");

// Insta Loan
	
	public static By titleInstalone=By.xpath("//*[@text='Insta Loan']");
	
	public static By txtRepaymentSchedule=By.xpath("//*[@text='Repayment Schedule']");
	
	public static By linkViewDetails=By.xpath("//*[@text='View Details']");
	
	// Pre-check
	public static By objIallowCheckBox = By.xpath("//*[contains(@text,'I allow')]/preceding-sibling::android.widget.CheckBox");
    public static By objReadmoreCheckBox = By.xpath("//*[contains(@text,'Read more')]/(preceding-sibling::android.widget.CheckBox)[2]");
	
	//Payment Xpath
	
	public static By rbnFullPayment=By.xpath("//*[@text='Pay Full Amount']/preceding-sibling::*[@class='android.widget.RadioButton']");

	public static By txtPayment=By.xpath("//*[@text='Payment']");
	
	public static By objMenuTab=By.xpath("//*[@text='Available Limit' or @text='Limit Paused']/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup");
	
	//offer Id verification
	
	public static By secoundWaiver=By.xpath("//*[@text='100% waiver']");
	
	public static By secound_waiver=By.xpath("//*[@text='Pay first installment on time to get 100% waiver on 2nd Installment']");

	public static By firstemiduein=By.xpath("//*[@text='First EMI due in']");
	public static By fifteen=By.xpath("//*[@text='15']");
	public static By txtdays=By.xpath("//*[@text='Days']");
	
	public static By txtOnHold=By.xpath("//*[@class='android.widget.ImageView' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@text='On Hold']]");

	public static By btnPayEMI=By.xpath("//*[@text='Pay EMI']");
	
	public static By txtInProgress=By.xpath("//*[@text='In Process']");
	
	public static By txtPayFullAmount=By.xpath("//*[@text='Pay Full Amount']");
	
	public static By txtfirstInstall=By.xpath("//*[@text='1st installment']");
	
	public static By txtsecoundInstall=By.xpath("//*[@text='2nd installment']");
	
	//First Installment XPath
	
	public static By firstInstallRadioBtn=By.xpath("//*[@text='1st installment']/parent::*[@class='android.view.ViewGroup']/android.widget.RadioButton/android.view.ViewGroup");
	
	public static By firtstInsallmentPay=By.xpath("//*[@text='1st installment']/parent::*[@class='android.view.ViewGroup']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	//Secound Installment XPath
	public static By secoundInstallRadioBtn=By.xpath("//*[@text='2nd installment']/parent::*[@class='android.view.ViewGroup']/android.widget.RadioButton/android.view.ViewGroup");
	
	public static By secoundInsallmentPay=By.xpath("//*[@text='2nd installment']/parent::*[@class='android.view.ViewGroup']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	public static By Secoundemiduein=By.xpath("//*[@text='Second EMI due in']");
	public static By sixtytwo=By.xpath("//*[@text='62']");
	
	//Third Installment XPath
	public static By thirdInstallRadioBtn=By.xpath("//*[@text='3rd installment']/parent::*[@class='android.view.ViewGroup']/android.widget.RadioButton/android.view.ViewGroup");
		
	public static By thirdInsallmentPay=By.xpath("//*[@text='3rd installment']/parent::*[@class='android.view.ViewGroup']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	public static By thirdemiduein=By.xpath("//*[@text='Third EMI due in']");
	public static By ninty=By.xpath("//*[@text='90']");
	
	//fourth Installment XPath
	public static By fourthInstallRadioBtn=By.xpath("//*[@text='4th installment']/parent::*[@class='android.view.ViewGroup']/android.widget.RadioButton/android.view.ViewGroup");
	
	public static By fourthInsallmentPay=By.xpath("//*[@text='4th installment']/parent::*[@class='android.view.ViewGroup']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	public static By fourthemiduein=By.xpath("//*[@text='Fourth EMI due in']");
	public static By oneHunderedtwenty=By.xpath("//*[@text='120']");
	
	//Fifth Installment XPath
	public static By fifthInstallRadioBtn=By.xpath("//*[@text='5th installment']/parent::*[@class='android.view.ViewGroup']/android.widget.RadioButton/android.view.ViewGroup");
	
	public static By fifthInsallmentPay=By.xpath("//*[@text='5th installment']/parent::*[@class='android.view.ViewGroup']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	public static By fifthemiduein=By.xpath("//*[@text='Fifth EMI due in']");
	public static By oneHunderedfiftyone=By.xpath("//*[@text='151']");
	
	//Sixth Installment XPath
	public static By sixthInstallRadioBtn=By.xpath("//*[@text='6th installment']/parent::*[@class='android.view.ViewGroup']/android.widget.RadioButton/android.view.ViewGroup");
	
	public static By sixthInsallmentPay=By.xpath("//*[@text='6th installment']/parent::*[@class='android.view.ViewGroup']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	public static By sixthemiduein=By.xpath("//*[@text='Sixth EMI due in']");
	public static By oneHunderedeightyOne=By.xpath("//*[@text='181']");	
		
		
		
	
	//App score config
	public static By objEditConfBtn = By.xpath("//*[@class=\"MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-containedSizeSmall MuiButton-sizeSmall\"]");
	public static By objMetaValue = By.id("meta_value");
	public static By objSubmit = By.xpath("(//button[@type=\"submit\"])[2]");
	public static By objUserRefNo = By.xpath("(//tr[@class=\"MuiTableRow-root\"]//td)[1]");
	public static By objSearchRef = By.id("outlined-error-helper-text");
	public static By objSearch = By.xpath("(//span[text()=\"Search\"])[2]");
	public static By objContinue = By.xpath("//*[@type='submit']");

	//Sorry Page Validation
	
	public static By txtSorry=By.xpath("//*[@text='Sorry!']");
	public static By txtSorryDetails=By.xpath("//*[@text='Your application cannot be processed\r\n"
			+ "right now.']");
	
	public static By sorryPageTopHamBurgger=By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]]]");

	
	//outstanding amount instaLoan
		public static By objOutstatndingAmount = By.xpath("//*[@text='Current Spends']/following-sibling::android.widget.TextView");
		public static By objApplyBtn = By.xpath("//*[contains(@text,'Apply')]");
		public static By objYesBtn = By.xpath("//*[@text='YES']");
		public static By objRepayDues = By.xpath("//*[@text='Repay Dues']");
		public static By objRepayDuesDescription = By.xpath("//*[@text='Please repay your existing dues to avail Insta Loan offer']");
		public static By objRepayPopupCrossBtn = By.xpath("//*[@text='Repay Dues']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/(descendant::android.view.ViewGroup)[2]");
		public static By objNoBtn = By.xpath("//*[@text='No']");
		
		//Basic Details
		public static By objFatherName = By.xpath("//*[@text='Father Name']/preceding-sibling::android.widget.EditText");
		public static By objRelationShipWithYou = By.xpath("//*[@text='Relationship With You']/preceding-sibling::android.view.ViewGroup/descendant::android.widget.ImageView");
		public static By objSelectRelation = By.xpath("//*[@text='Father']");
		
		//personal details
		public static By objEmploymentStatus = By.xpath("//*[@text='Salaried']/preceding-sibling::android.widget.RadioButton/descendant::android.view.ViewGroup");
		public static By objMaritalStatus = By.xpath("(//*[@text='Unmarried']/preceding-sibling::android.widget.RadioButton/descendant::android.view.ViewGroup)[4]");
		public static By objAnualIncome = By.xpath("//*[@text='5-10 Lakhs']");
		
		public static By objPurposeofYourLoan = By.xpath("//*[@text='Purpose of Your Loan']/following-sibling::android.widget.ImageView");
		public static By objPurposeOfYourLoan = By.xpath("//*[@text='Medical Expenses']");
	
	
	  //===========================	//Offer Page==============================
		public static By objOfferHeader = By.xpath("//*[@text='Offer']");
		public static By objEligible = By.xpath("//*[@text='You are eligible for Insta Loan of']");
		public static By objEligibleInstaLoanAmt = By.xpath("//android.widget.TextView[@text='You are eligible for Insta Loan of']/(following-sibling::android.widget.TextView)[1]");
		public static By objAddBankButton = By.xpath("//*[@text='Add Bank Details']");
		public static By objAcceptCheckBox = By.xpath("//*[@text='I accept the Terms & Conditions ,  Key Fact Statement  and IT Act 2000']/preceding-sibling::android.widget.CheckBox");
		public static By objAcceptOffer = By.xpath("//*[@text='Accept Offer']");
		public static By objConfirmOfferBtn = By.xpath("//*[@text='Confirm Offer']");
		public static By objHomeBtn = By.xpath("//*[@text='Home']");
	//===============================Pay Now Insta Loan=========================
		public static By objPayNowBtn = By.xpath("//*[@text='Pay Now']");
		public static By objViewDetails = By.xpath("//*[@text='View Details']");
		public static By objPayFullAmountRadioBtn = By.xpath("//*[@text='Pay Full Amount']/preceding-sibling::android.widget.RadioButton/child::android.view.ViewGroup");
		public static By objAmountToBePaidRadioBtn = By.xpath("(//*[@text='Amount to be paid']/preceding-sibling::android.widget.RadioButton/descendant::android.view.ViewGroup)[3]");
		public static By objAmtPaidField = By.xpath("//*[@text='Amount to be paid']/preceding-sibling::android.widget.RadioButton/descendant::android.view.ViewGroup/child::android.view.ViewGroup");
		public static By objAmountDue = By.xpath("(//*[@text='View Details']/preceding-sibling::android.widget.TextView)[2]");
		public static By objNetBankingDebitCard = By.xpath("//*[@text='Net Banking & Debit Card']");
		public static By objCard = By.xpath("//*[@text='Cards, UPI & More']/following-sibling::android.widget.ListView/descendant::android.view.View[@text='Card Visa, MasterCard, RuPay, and Maestro']");
		
	//=================================Add New Card==============================
		public static By objCardNumber = By.xpath("//*[@resource-id='card_number']");
		public static By objExpiryDate = By.xpath("//*[@resource-id='card_expiry']");
		public static By objCVV = By.xpath("//*[@resource-id='card_cvv']");
		public static By objCardHolderName = By.xpath("//*[@resource-id='card_name']");
		public static By objSaveCardField = By.xpath("//*[@resource-id='should-save-card']");
		public static By objPayWithoutSavingCardBtn = By.xpath("//*[@text='Pay without Saving Card']");
		public static By objCardOTPField = By.xpath("//*[@resource-id='submit-action']/preceding-sibling::android.widget.EditText");
		public static By objSubmitButton = By.xpath("//*[@resource-id='submit-action']");
		public static By objReApplyForInstaLoan = By.xpath("//*[@text='ReApply for Insta Loan!']");
		public static By objSuccessBtn = By.xpath("//*[@text='Success']");
		public static By objInstaLoanAmountApproved = By.xpath("//*[@text='Insta Loan Amount']/(following-sibling::android.widget.TextView)[1]");
		public static By objDisbursementSuccessfulMsg = By.xpath("//*[@text='Insta Loan Disbursement Successful']");
		public static By objDisbursementAmount = By.xpath("//*[@text='Insta Loan Disbursement Successful']/following-sibling::android.widget.TextView");
		public static By objSorryPage = By.xpath("//*[@text='Sorry!']");
		
		//============================Select Adderess=============================
		public static By objSelectAddress = By.xpath("//*[@text='Select Address']");
//		public static By objAddressSelected = By.xpath("//*[@text='86 44, Borivali Vasa Street 400080']");
		public static By objAddressSelected = By.xpath("//*[@text='Permanent Address']/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/(descendant::android.widget.TextView)[2]");
		public static By objProceedBtn = By.xpath("//*[@text='Proceed']/parent::android.view.ViewGroup");
		public static By objOKGotItBtn = By.xpath("//*[@text='Ok Got It']");
		public static By objApplyNowBtn = By.xpath("//*[@text='Apply Now']");

		//App Home Screen
		public static By objinstaLoanBanner = By.xpath("(//*[@text='New Offer Alert!']/following-sibling::android.widget.TextView)[1]");
		public static By objinstaLoanBannerImage = By.xpath("//*[@text='New Offer Alert!']/preceding-sibling::android.widget.ImageView");
		public static By instaPopupBannerCrossBtn = By.xpath("//*[@text='Insta Loan']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/(descendant::android.view.ViewGroup)[1]"); 
		public static By objOkayGotIt = By.xpath("//*[@text='Okay, Got It!']");
		
		//InstaLoan Coach popup
		public static By objInstaLoanCoachEnjoyText = By.xpath("//*[@text='Now enjoy a personal loan']");
	
}































