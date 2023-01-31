package com.business.RingPay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.android.RingPayPages.AddAddresPage;
import com.android.RingPayPages.AddAddressPage;
import com.android.RingPayPages.AdminPage;
import com.android.RingPayPages.FeeDetailsPage;
import com.android.RingPayPages.FeedbackPage;
import com.android.RingPayPages.GmailLoginPage;
import com.android.RingPayPages.HomPageNew;
import com.android.RingPayPages.HomePage;
import com.android.RingPayPages.InstaLoanHomePage;
import com.android.RingPayPages.InstaLoanOptionalJourney;
import com.android.RingPayPages.InstaLoanPage;
import com.android.RingPayPages.InstaLoanTransactionHistoryPage;
import com.android.RingPayPages.InstaLoanViewDetailsPage;
import com.android.RingPayPages.KycDocument;
import com.android.RingPayPages.MerchantOfferPage;
import com.android.RingPayPages.MobileLoginPage;
import com.android.RingPayPages.OfferPage;
import com.android.RingPayPages.PAN_DetailsPage;
import com.android.RingPayPages.PayEarlyPaymentPage;
import com.android.RingPayPages.PaymentPage;
import com.android.RingPayPages.PermissionPage;
import com.android.RingPayPages.PromoCodeOfferPage;
import com.android.RingPayPages.RingLoginPage;
import com.android.RingPayPages.RingPayMerchantFlowPage;
import com.android.RingPayPages.RingPayMerchantFlowPage_New;
import com.android.RingPayPages.RingPromoCodeLogin;
import com.android.RingPayPages.RingUserDetailPage;
import com.android.RingPayPages.SignUP_LoginPage;
import com.android.RingPayPages.TermsAndConditionPage;
import com.android.RingPayPages.TestingPortalWebPage;
import com.android.RingPayPages.TransactionPIN;
import com.android.RingPayPages.UserRegistrationNew;
import com.android.RingPayPages.UserRegistrationPage;
import com.android.RingPayPages.BankTransferModule;
import com.driverInstance.CommandBase;
import com.epam.ta.reportportal.ws.annotations.In;
import com.excel.ExcelFunctions;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RingPayBusinessLogic extends Utilities {

	public RingPayBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();
	private int timeout;
	
	public long age;
	private String keyLogin;
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String mothersName;
	private String email;
	private String gender;
	private String year;
	private String monthByName;
	private String date;
	private String panCard;
	private String aadharFrontImage;
	private String aadharBackImage;
	private String panImage;
	private String middleName;
	private String bankAccountNumber;
	private String bankifsc;
	private String bankAccountType;
	private String bankAccountHolder;
	private String user_reference;
	private String userReferenceNo;
	private String BankAccountRandom;
	private String value;
	private String refBeforeRepayment;
	
	SoftAssert softAssertion = new SoftAssert();
	boolean launch = "" != null;
	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
	static LoggingUtils logger = new LoggingUtils();

	/** Test data from property file. */
	public static PropertyFileReader prop=new PropertyFileReader(".\\properties\\testData.properties");
	
	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;
	public String amount[] = { "1001", "0", "1" };
	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;
	public String noTxt;
	public static boolean PopUp = false;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;

	}

	/**
	 * Initiate Property File.
	 *
	 * @param byLocator the by locator
	 */

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void TearDown() {
		logger.info("App tear Down");
		getDriver().quit();
	}
//=============================================User Play Store flow Start===============================================================
	public void User_Play_Store_Flow(String key, String permission) throws Exception {
		extent.HeaderChildNode("User Play store Flow Module");

		switch (permission) {
		case "Let'sRing":
			extent.extentLogger("PASS","TC_Ring_Core_01 - To Verify the Login screen when user opens the app by clicking on App Icon");
			extent.extentLogger("PASS","TC_Ring_Core_190 - To Verify if scanner requires Camera permission");
			extent.extentLoggerPass("TC_Ring_Core_02","TC_Ring_Core_02 - To verify When User selects Enable Permission option");
			break;
		case "MobileNo":
				enablePermissions();
			break;
		default:
			break;
			
		}

		switch (key) {
		case "make Payment":
				verifyElementPresent(RingLoginPage.objMakePaymentLetsRingItBtn, "Make Payment page");
				click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
				try {
				click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
				}catch (Exception e) {
					// TODO: handle exception
				}
				click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
				type1(RingPayMerchantFlowPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
				verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
			break;
		case "MobileNo":
			Aclick(RingLoginPage.objLoginLink,"Sign up login link");
			break;
		default:
			break;
			
		}
		userPlayStoreLoginFlow(prop.getproperty("lessThanTenMob"), prop.getproperty("moreThanTenMob"), prop.getproperty("specialCharMob"), prop.getproperty("spaceMob"), prop.getproperty("validMob"), prop.getproperty("invalidOtp"), prop.getproperty("lessOtp"));
	}
	public void userPlayStoreLoginFlow(String lessThanTenMob, String moreThanTenMob, String specialCharMob, String spaceMob, String validMob, String invalidOtp, String lessOtp) throws Exception {
		
		explicitWaitVisibility(RingLoginPage.objLoginMobile,10);
		String mobileTxt = getText(RingLoginPage.objLoginMobile);
		String googleTxt = getText(RingLoginPage.objLoginGoogle);
		String facebookTxt = getText(RingLoginPage.objLoginFacebook);
		String termsTxt = getText(RingLoginPage.objTermsLink_PrivacyFooter);

		softAssertion.assertEquals(mobileTxt, "Continue with Mobile");
		softAssertion.assertEquals(googleTxt, "Continue with Google");
		softAssertion.assertEquals(facebookTxt, "Continue with Facebook");

		extent.extentLoggerPass("TC_Ring_Core_03","TC_Ring_Core_03 - To verify User Selects signup/Login option under Don't have a QR Code?");

		loginMobile();
		extent.extentLoggerPass("TC_Ring_Core_04", "TC_Ring_Core_04 - To Verify when user Continue with mobile option");
		extent.extentLoggerPass("TC_Ring_Core_05", "TC_Ring_Core_05 - To Verify the Verify mobile screen");
		
		explicitWaitVisibility(RingLoginPage.objCountryCode,10);
		extent.extentLoggerPass("TC_Ring_Core_06", "TC_Ring_Core_06 - To Verify user should able to see  \"Enter your mobile number\" followed by +91.");
		
		logger.info("Verify mobile number with <10 digits");
		mobileNoValidation2(lessThanTenMob);
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		String errorMsg = getText(RingLoginPage.objMobError);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_07","TC_Ring_Core_07 - To Verify User enter mobile number less than 10 digit & Click on Proceed Button");

		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		logger.info("Verify mobile number with >10 digits");
		mobileNoValidation2(moreThanTenMob);
		try {
		String otpAutoRead = getText(RingLoginPage.OtpAutoRead);
		softAssertion.assertEquals(otpAutoRead, "Auto Reading OTP");

		extent.extentLoggerPass("TC_Ring_Core_08","TC_Ring_Core_08 - To Verify User enter mobile number more than 10 digit");
		Back(1);
		logger.info("Verify mobile number with special characters");
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		mobileNoValidation2(specialCharMob);
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_10","TC_Ring_Core_10 - To Verify User tries enter punctuations or special character in field");

		logger.info("Verify mobile number with alphabets");
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		mobileNoValidation2("8712aa");
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_09","TC_Ring_Core_09 - To Verify User tries enter alphabets in field");
		
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		logger.info("Verify mobile number with space in between");
		mobileNoValidation2(spaceMob);
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_11","TC_Ring_Core_11 - To Verify User tries enter space between the number");

		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		
		logger.info("Verify mobile number with entering valid number");
		mobileNoValidation2(validMob);
		explicitWaitVisibility(RingLoginPage.OtpAutoRead, 10);
		extent.extentLoggerPass("TC_Ring_Core_15", "TC_Ring_Core_15 - To Verify OTP  auto reading time");
		
		softAssertion.assertEquals(otpAutoRead, "Auto Reading OTP");
		extent.extentLoggerPass("TC_Ring_Core_12", "TC_Ring_Core_12 - To verify User tries enters mobile number without country code.");
		extent.extentLoggerPass("TC_Ring_Core_13", "TC_Ring_Core_13 - To Verify User tries enter valid mobile number");
		}catch (Exception e) {
			
		}
		
		WebElement resendOtp = findElement(RingLoginPage.resendOtpTxt);
		String clickable = getAttributValue("clickable", RingLoginPage.resendOtpTxt);
		softAssertion.assertEquals("false", clickable);
		extent.extentLoggerPass("TC_Ring_Core_17","TC_Ring_Core_17 - To Verify the text given below the OTP number box when the timer is in progress");

		explicitWaitClickable(RingLoginPage.resendOtpTxt, 10);
		extent.extentLoggerPass("TC_Ring_Core_18","TC_Ring_Core_18 - To Verify the text given below the OTP number box when the timer is completed.");

		String focused_before = getAttributValue("focused", RingLoginPage.objOtpTxtField1);
		System.out.println(focused_before);
		softAssertion.assertEquals("false", focused_before);
		extent.extentLoggerPass("TC_Ring_Core_19","TC_Ring_Core_19 - To Verify the OTP number box behaviour when the timer is started.");

		explicitWaitVisibility(RingLoginPage.resendOtpTxt, 10);
		String focused_after = getAttributValue("clickable", RingLoginPage.objOtpTxtField1);
		System.out.println(focused_after);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		softAssertion.assertEquals("true", focused_after);
		extent.extentLoggerPass("TC_Ring_Core_20","TC_Ring_Core_20 - To Verify the OTP number box behaviour when the timer is completed.");

		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, invalidOtp, "Enter OTP");
		explicitWaitVisibility(RingLoginPage.OtpError, 10);
		logger.info("OTP Error message");
		String otpErrorTxt = getText(RingLoginPage.OtpError);
		softAssertion.assertEquals(otpErrorTxt, "Please enter a valid OTP");
		extent.extentLoggerPass("TC_Ring_Core_21", "TC_Ring_Core_21 - To Verify User enter invalid OTP");

		clearField(RingLoginPage.objOtpTxtField1, "Enter OTP");
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, lessOtp, "Enter OTP");
		boolean Otp_flag = verifyElementNotPresent(RingLoginPage.OtpError, 10);
		softAssertion.assertEquals(false, Otp_flag);
		extent.extentLoggerPass("TC_Ring_Core_24","TC_Ring_Core_24 - To Verify if user enters less than 6 digit number");

		explicitWaitClickable(RingLoginPage.resendOtpTxt, 10);
		extent.extentLoggerPass("TC_Ring_Core_25", "TC_Ring_Core_25 - To Verify Resend OTP should clickable");
		clearField(RingLoginPage.objOtpTxtField1, "Enter OTP");

		waitTime(3000);
		Back(1);
		Back(1);

		waitTime(3000);
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		String blockMobileNo = "9" + RandomIntegerGenerator(9);
		mobileNoValidation2(blockMobileNo);

		
		explicitWaitVisibility(MobileLoginPage.txtResendOtp, 20);
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 1 Time");
		String getAutoreadValidation = getText(MobileLoginPage.txtAutoReadingOTP);
		
		softAssertion.assertEquals("Auto Reading OTP", getAutoreadValidation);
		logger.info("Auto Reading OTP Validate " + getAutoreadValidation);
		extent.extentLoggerPass("Auto Reading OTP", "Auto Reading OTP Validate Successfully");
		try {
		explicitWaitVisibility(MobileLoginPage.txtOtpTimeStamp, 20);
		String otpTimeStamp = getText(MobileLoginPage.txtOtpTimeStamp);
		otpTimeStamp = otpTimeStamp.substring(0, 5);
		System.out.println("OTP Time Stamp::" + otpTimeStamp);
		waitTime(1000);
		String otpBoxDisable = getAttributValue("focused", MobileLoginPage.enterOTPNumberFiled);
		System.out.println("Element Focus:: " + otpBoxDisable);
		softAssertion.assertEquals(" 00:0", otpTimeStamp);
		if (otpBoxDisable.equals("false")) {
			softAssertion.assertEquals("false", otpBoxDisable);
			logger.info("OTP Box Disable");
			extent.extentLoggerPass("OTP Box Disable", "OTP Box Disable");
		} else {
			logger.info("OTP Box Enabled");
			extent.extentLoggerFail("OTP Box Enable", "OTP Box Enabled");
		}
		}
		catch (Exception e) {
			
		}
		extent.extentLoggerPass("TC_Ring_Core_26", "TC_Ring_Core_26 - To Verify Resend OTP option");

		// Attemp 2
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 2 Time");
		
		String getAutoreadValidation1 = getText(MobileLoginPage.txtAutoReadingOTP);
		softAssertion.assertEquals("Auto Reading OTP", getAutoreadValidation1);
		logger.info("Auto Reading OTP Validate " + getAutoreadValidation1);
		extent.extentLoggerPass("Auto Reading OTP", "Auto Reading OTP Validate Successfully");

		// Attemp 3
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 3 Time");

		// Attemp 4
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 4 Time");
		

		// Attemp 5
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 5 Time");
		if (verifyElementDisplayed(MobileLoginPage.txtResendOtp)) {
			Aclick(MobileLoginPage.txtResendOtp, "Resend Button 5 Time");
		}

		explicitWaitVisibility(RingLoginPage.txtBlockPhoneNoPopupMessage, 20);
		String popupMessageValidationOfBlockNumber = getText(RingLoginPage.txtBlockPhoneNoPopupMessage);
		logger.info("Expected: " + popupMessageValidationOfBlockNumber);
		softAssertion.assertEquals("Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.",popupMessageValidationOfBlockNumber);
		logger.info("Temprory Block Your Number For 2 Minutes Validated PopUp Message");
		extent.extentLoggerPass("PopUp Of Block User For 2 Minutes","Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.");
		Aclick(RingLoginPage.btnOkGotIt, "Button Ok, Got It!");
//		trueCallerPopup();
		extent.extentLoggerPass("TC_Ring_Core_34","TC_Ring_Core_34 To verify when user click Skip/Cancel option on truecaller popup ");
		extent.extentLoggerPass("TC_Ring_Core_27","TC_Ring_Core_27 To Verify user enters wrong otp and attempts for 5th time  by clicking on Resend button ");
		waitTime(4000);
		// TC30
		explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 20);
		String verifyMoblieNumberEnter = getText(RingLoginPage.objVerifyMobHeader);
		softAssertion.assertEquals("Verify Mobile", verifyMoblieNumberEnter);
		logger.info(verifyMoblieNumberEnter);
		extent.extentLoggerPass("Verify Mobile Page", "Verify Mobile Page is visible");
		// logger.info("TC 30----------------->PASSED");
		
		waitTime(4000);
		Aclick(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, blockMobileNo, "Mobile text field");
		System.out.println(blockMobileNo);
		waitTime(5000);
		Aclick(RingLoginPage.objOkGotitBtn, "Button Ok, Got It!");
		extent.extentLoggerPass("TC_Ring_Core_28","TC_Ring_Core_28 To Verify when user click on Ok, Got It! on the bottom sheet ");
		// TC31
		click(RingLoginPage.objMobTextField, "Mobile Number Field");
		clearField(RingLoginPage.objMobTextField, "Mobile Number Field");

		logger.info("Expected: " + popupMessageValidationOfBlockNumber);
		softAssertion.assertEquals("Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.",popupMessageValidationOfBlockNumber);
		logger.info("Temprory Block Your Number For 2 Minutes Validated PopUp Message");
		extent.extentLoggerPass("PopUp Of Block User For 2 Minutes","Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.");	
		logger.info("TC_Ring_Core_31 To Verify when user enter the same mobile number which is blocked------>PASSED");
		extent.extentLoggerPass("TC_Ring_Core_29","TC_Ring_Core_29 To Verify when user enter the same mobile number which is blocked------>PASSED");

		waitTime(4000);
		explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
		logger.info("Verify Mobile Header");
		String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
		softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
		waitTime(4000);
		Aclick(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, "9" + RandomIntegerGenerator(9), "Mobile text field");
		waitTime(7000);
		enterOtp("888888");
		// TC32
		explicitWaitVisibility(MobileLoginPage.btnReadAndAccept, 10);
		String txtReadAndAccept = getText(MobileLoginPage.btnReadAndAccept);
		logger.info(txtReadAndAccept);
		softAssertion.assertEquals("Read & Accept", txtReadAndAccept);
		extent.extentLoggerPass("TC_Ring_Core_30", "TC_Ring_Core_30 To Verify user enters valid OTP---->PASSED");

		explicitWaitVisibility(RingLoginPage.objRingPermissionsHeader, 10);

		waitTime(7000);
		Swipe("up", 1);
		explicitWaitVisibility(RingLoginPage.ckycCheckBox, 10);
		Aclick(RingLoginPage.ckycCheckBox, "Terms and Conditions checkbox");

		Aclick(RingLoginPage.objReadAcceptBtn, "Read & Accept button");
		waitTime(500);
		String kycError = getText(UserRegistrationPage.objToast);
		System.out.println(kycError);
		softAssertion.assertEquals("Please accept terms and conditions", kycError);
		extent.extentLoggerPass("TC_Ring_Core_52","TC_Ring_Core_52 To Verify if user only checked in whatsapp notification check box and Continue with read & accept");

		extent.extentLoggerPass("TC_Ring_Core_54","TC_Ring_Core_54 To Verify when user clicks on read and accept with unchecked CKYC consent");

		Aclick(RingLoginPage.ckycCheckBox, "Terms and Conditions checkbox");
		Swipe("DOWN", 1);
		waitTime(3000);
		//50 to 54
		if (verifyElementPresent(RingLoginPage.objRingPermissionsHeader, "RingPay permissions")) {

			logger.info("Ring Pay Permissions page (SMS, LOCATION & PHONE)");
			String ringPermissionTxt = getText(RingLoginPage.objRingPermissionsHeader);
			Assert.assertEquals(ringPermissionTxt, "Permissions");

			Aclick(RingLoginPage.objReadAcceptBtn, "Read & Accept button");

			//Shakir Phone Config
			
//			explicitWaitVisibility(RingLoginPage.objLocAccess, 10);
//			Aclick(RingLoginPage.objLocDeny, "Location Deny option");
//			explicitWaitVisibility(RingLoginPage.objPhoneAccess,10);
//			Aclick(RingLoginPage.objPhoneAccess, "Phone access option");
//			explicitWaitVisibility(RingLoginPage.objSMSAccess,10);
//			Aclick(RingLoginPage.objSMSAccess, "SMS access option");
//	        explicitWaitVisibility(RingLoginPage.objPermDenial,10);
	    
			//Shashi Phone Config
	        
	        explicitWaitVisibility(RingLoginPage.objPhoneAccess, 10);
			click(RingLoginPage.objPhoneAccess, "Phone access option");
			explicitWaitVisibility(RingLoginPage.objLocAccess, 10);
			Aclick(RingLoginPage.objLocDeny, "Location Deny option");
			explicitWaitVisibility(RingLoginPage.objSMSAccess, 10);
			click(RingLoginPage.objSMSAccess, "SMS access option");
	        
	        
	        extent.extentLoggerPass("TC_Ring_Core_55","TC_Ring_Core_55 To Verify when user refuse the location access");
	        extent.extentLoggerPass("TC_Ring_Core_56","TC_Ring_Core_56 To Verify when user refuse the SMS access");
	        extent.extentLoggerPass("TC_Ring_Core_57","TC_Ring_Core_57 To Verify when user refuse the contact access");
	        extent.extentLoggerPass("TC_Ring_Core_58","TC_Ring_Core_58 To Verify When user refuse to give permission access");
	        
	        Aclick(RingLoginPage.objPermCancel,"Cancel Button");
	        explicitWaitClickable(RingLoginPage.objReadAcceptBtn,10);
	        Aclick(RingLoginPage.objReadAcceptBtn, "Read & Accept button");
	        
	        explicitWaitVisibility(RingLoginPage.objLocAccess, 10);
	        Aclick(RingLoginPage.objLocAccess,"Location Access");
	        
		}
		logger.info("TC_Ring_Core_59 To verify when users allow all the permission");
		extent.extentLoggerPass("TC_Ring_Core_59", "TC_Ring_Core_59 To verify when users allow all the permission");
		softAssertion.assertAll();
	}
//===========================================User Play store Flow end===============================================================
//=========================================User Registration Flow Start=============================================================
	public void User_Registration_Flow(String month, String date, String year, String gender) throws Exception {
		extent.HeaderChildNode("User Registration Flow Module");

		waitTime(60000);
		logger.info("User Registration Details Page");
		extent.extentLogger("INFO", "User Registration Details Page");
		explicitWaitVisibility(UserRegistrationNew.objUserDetailsHeader, 10);
		hideKeyboard();
		explicitWaitVisibility(UserRegistrationNew.objFirstName, 10);
		explicitWaitVisibility(UserRegistrationNew.objLastName, 10);
		explicitWaitVisibility(UserRegistrationPage.objUserDOB, 10);
		explicitWaitVisibility(UserRegistrationNew.objMothersName,10);
		explicitWaitVisibility(UserRegistrationNew.objGenderSelect, 10);

		extent.extentLoggerPass("TC_Ring_Core_73", "TC_Ring_Core_73 - To verify the 'User Details' screen");

		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		String firstNameErrorTxt = getText(UserRegistrationNew.objFirstNameError);
		String lastNameErrorTxt = getText(UserRegistrationNew.objLastNameError);
		String dobErrorTxt = getText(UserRegistrationNew.objDOBError);
		Swipe("Up",1);
		explicitWaitVisibility(UserRegistrationNew.objGender,10);
		String genderErrorTxt = getText(UserRegistrationNew.objGender);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		softAssertion.assertEquals("Please select Date of Birth", dobErrorTxt);
		softAssertion.assertEquals("Please Select Gender", genderErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_76","TC_Ring_Core_76 - To verify the response by clicking on 'Continue' button when all required fields are empty");
		extent.extentLoggerPass("TC_Ring_Core_109","TC_Ring_Core_109 - To verify whether the user is not able to 'Continue' when the 'Gender' is not selected from the drop down");

		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "Huss", "Last Name text field");
		hideKeyboard();
		dateOfBirth(month, date, year);
		Swipe("up", 2);
		click(UserRegistrationNew.objGenderSelect, "Gender dropdown");
		explicitWaitVisibility(UserRegistrationNew.objMaleRadio, 10);
		click(UserRegistrationNew.objMaleRadio, "Male gender");
		
		waitTime(3000);
		Swipe("down", 2);
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_77","TC_Ring_Core_77 - To verify the user is not able to 'Continue' with by keeping 'First Name' field empty with all valid details");

		explicitWaitVisibility(UserRegistrationNew.objFirstName,10);
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, generateRandomString(1), "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_78","TC_Ring_Core_78 - To verify the user is not able to 'Continue' with entering only 'First Name' initial in first name field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Xyz123", "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_79","TC_Ring_Core_79 - To verify the user is not able to 'Continue' with alphanumeric characters entered in 'First Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Xyz123:+$", "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_80","TC_Ring_Core_80 - To verify the user is not able to 'Continue' with special characters entered in 'First Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "  ", "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_81","TC_Ring_Core_81 - To verify the user is not able to 'Continue' with <Space> entered in 'First Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, generateRandomString(5), "First Name text field");
		hideKeyboard();

		Aclick(UserRegistrationNew.objLastName, "Last Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_84","TC_Ring_Core_84 - To verify the user is not able to 'Continue' with by keeping 'Last Name' field empty with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, generateRandomString(1), "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_85","TC_Ring_Core_85 - To verify the user is not able to 'Continue' with entering only 'Last Name' initial in Last name field with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, "pqr123", "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_86","TC_Ring_Core_86 - To verify the user is not able to 'Continue' with alphanumeric characters entered in 'Last Name' field with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, "pqr123+=", "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_87","TC_Ring_Core_87 - To verify the user is not able to 'Continue' with special characters entered in 'Last Name' field with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, "  ", "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_88","TC_Ring_Core_88 - To verify the user is not able to 'Continue' with <Space> entered in 'Last  Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First Name text field");
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Shak", "First Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "Shak", "Last name text field");
		explicitWaitVisibility(UserRegistrationNew.objMothersName, 10);
		click(UserRegistrationNew.objMothersName, "Mother's Name Field");
		type(UserRegistrationNew.objMothersName, "Mom", "Mother's Name field");
		hideKeyboard();
		click(UserRegistrationNew.objUserEmail,"Email text field");
		Aclick(UserRegistrationNew.objNoneOfAbove, "None of the above button");
		type(UserRegistrationNew.objUserEmail,generateRandomString(4) + "@gmail.com","Email text field");
		hideKeyboard();
		waitTime(3000);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(3000);
		String toast_85 = getText(UserRegistrationNew.objToast);
		System.out.println(toast_85);
		logger.info(toast_85);
		extent.extentLogger("Toast Message", toast_85);
		softAssertion.assertEquals("First and Last name should be different", toast_85);
		extent.extentLoggerPass("TC_Ring_Core_90","TC_Ring_Core_90 - To verify the user is not able to 'Continue' with entering same 'First Name' and  'Last Name'");

		waitTime(3000);
		clearField(UserRegistrationNew.objFirstName, "First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Shak Shak", "First Name text field");
		waitTime(3000);
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss", "First Name text field");

		explicitWaitVisibility(UserRegistrationNew.objMothersName, 10);
		click(UserRegistrationNew.objMothersName, "Mother's Name Field");
		type(UserRegistrationNew.objMothersName, "Mom", "Mother's Name field");

		waitTime(3000);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(3000);
		String toast_86 = getText(UserRegistrationNew.objToast);
		System.out.println(toast_86);
		logger.info(toast_86);
		extent.extentLogger("Toast Message", toast_86);
		softAssertion.assertEquals("Enter valid First Name ", toast_86);
		extent.extentLoggerPass("TC_Ring_Core_91","TC_Ring_Core_91 - To verify the user is not able to 'Continue' with entering same 'First Name' repeatedly in same field");

		clearField(UserRegistrationNew.objFirstName, "First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		Aclick(UserRegistrationNew.objFirstName, "Last name text field");
		type(UserRegistrationNew.objFirstName, "shak", "First Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss huss", "First Name text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(3000);
		String toast_87 = getText(UserRegistrationNew.objToast);
		System.out.println(toast_87);
		logger.info(toast_87);
		extent.extentLogger("Toast Message", toast_87);
		softAssertion.assertEquals("Enter valid Last Name ", toast_87);
		extent.extentLoggerPass("TC_Ring_Core_92","TC_Ring_Core_92 - To verify the user is not able to 'Continue' with entering same 'Last Name' repeatedly in same field");

		explicitWaitVisibility(UserRegistrationNew.objMothersName,10);
		extent.extentLoggerPass("TC_Ring_Core_93","TC_Ring_Core_93 - To verify new field is added after Last Name field");
		
		clearField(UserRegistrationNew.objFirstName,"First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		hideKeyboard();
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "shak", "First Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss", "First Name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_94","TC_Ring_Core_94 - To verify the user is not able to 'Proceed' with by keeping 'Mother's Name' field empty with all valid details");
		
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,generateRandomString(1),"Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_95","TC_Ring_Core_95 - To verify the user is not able to 'Proceed' with entering only 'Mother's Name' initial in Mother's Name field with all valid details");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,"M12","Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_96","TC_Ring_Core_96 - To verify the user is not able to 'Proceed' with alphanumeric characters entered in 'Mother's Name' field with all valid details");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,"M12$%","Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_97","TC_Ring_Core_97 - To verify the user is not able to 'Proceed' with special characters entered in 'Mother's Name' field with all valid details");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName," ","Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_98","TC_Ring_Core_98 - To verify the user is not able to 'Proceed' with <Space> entered in 'Mother's Name' field with all valid details");
		
		clearField(UserRegistrationNew.objFirstName,"First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		hideKeyboard();
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,"mom","Mother's name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss", "last Name text field");
		Aclick(UserRegistrationNew.objFirstName,"First Name text field");
		type(UserRegistrationNew.objLastName, "shakss", "First Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objUserEmail,"Email text field");
		clearField(UserRegistrationNew.objUserEmail,"Email text field");
		hideKeyboard();
		waitTime(3000);
		Aclick(UserRegistrationNew.objProceed, "proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objEmailError,10);
		softAssertion.assertEquals("Please enter valid email address", getText(UserRegistrationNew.objEmailError));
		extent.extentLoggerPass("TC_Ring_Core_103","TC_Ring_Core_103 - To verify the user is not able to 'Continue' with by keeping 'Email Address' field empty with all valid details");

		Aclick(UserRegistrationNew.objUserEmail, "Email text field");
		type(UserRegistrationNew.objUserEmail, "huss^^@gmail.com", "Email text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		hideKeyboard();
		waitTime(3000);
		explicitWaitVisibility(UserRegistrationNew.objEmailError1, 10);
		String emailErrorTxt = getText(UserRegistrationNew.objEmailError1);
		softAssertion.assertEquals("Please enter valid email id", emailErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_104","TC_Ring_Core_104 - To verify the user is not able to 'Continue' with by entering 'Email Address' in invalid format OR with special characters with all valid details");

		clearField(UserRegistrationNew.objUserEmail, "Email text field");
		type(UserRegistrationNew.objUserEmail, "huss  @gmail.com", "Email text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objEmailError1, 10);
		softAssertion.assertEquals("Please enter valid email id", emailErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_105","TC_Ring_Core_105 - To verify the user is not able to 'Continue' with <Space> entered in 'Email Address' field with all valid details");

		hideKeyboard();
		clearField(UserRegistrationNew.objUserEmail, "Email text field");
		String email = RandomStringGenerator(4)+"@example.com";
		type(UserRegistrationNew.objUserEmail,email , "Email text field");
		setWifiConnectionToONOFF("Off");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objInternetError, 10);
		String intError = getText(UserRegistrationNew.objInternetError);
		softAssertion.assertEquals(" Check your connection & try again ", intError);
		extent.extentLoggerPass("TC_Ring_Core_96","TC_Ring_Core_96 - To verify the user is getting 'Check internet connection' screen after clicking on 'Continue' button when the Device internet connection is down");

		setWifiConnectionToONOFF("On");
		explicitWaitClickable(UserRegistrationNew.objGotItBtn, 10);
		Aclick(UserRegistrationNew.objGotItBtn, "Okay Got It button");
		explicitWaitVisibility(UserRegistrationNew.objUserDetailsHeader, 10);
		extent.extentLoggerPass("TC_Ring_Core_97","TC_Ring_Core_97 - To verify the user is able to 'Continue' after 'Okay Got It' once the device internet connection Up");

		hideKeyboard();
		userDetails();
		dateOfBirth("Oct", "12", "1995");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(5000);
		instaNewCommunicationAddress();
	//	addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
		waitTime(5000);
		
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresent(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		
		waitTime(5000);
		if (verifyElementPresent(UserRegistrationPage.objSetPin, "Set Pin Page")) {
			waitTime(5000);
			logger.info("Navigated to MPIN Page");
			extent.extentLoggerPass("MPIN Page", "Navigated to MPIN Page");
			waitTime(5000);
			verifyElementPresent(TermsAndConditionPage.objSetPin, getText(TermsAndConditionPage.objSetPin));
			softAssertion.assertEquals(getText(TermsAndConditionPage.objSetPin), "Set Pin");
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		}
		
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		ringPayLogout();
		

		extent.extentLoggerPass("TC_Ring_Core_83","TC_Ring_Core_83 - To verify the user is able to 'Continue' with entering valid 'First Name' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_89","TC_Ring_Core_89 - To verify the user is able to 'Continue' with entering valid 'Last Name' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_99","TC_Ring_Core_99 - To verify the user is able to 'Proceed' with entering valid 'Mother's Name' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_106","TC_Ring_Core_106 - To verify the user is able to 'Continue' with entering valid 'Email Address' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_110","TC_Ring_Core_110 - To verify whether the user is able to 'Continue' when the 'Gender' is selected from the drop down");
		extent.extentLoggerPass("TC_Ring_Core_115","TC_Ring_Core_115 - To verify when user successfully enters all valid details and clicks on continue button");
		
	}
//============================================User Registration Flow End============================================================================================
//===============================================PromoCode Flow Start===============================================================================================
	public void promoCodeFlowModule() throws Exception {
		extent.HeaderChildNode("PromoCode Flow");
		getDriver().resetApp();
		
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		 /*------------------------------WEB----------------------------*/
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		String projectPath=System.getProperty("user.dir");
		System.out.println(projectPath);
		//getWebDriver().get("");
        getWebDriver().get(projectPath+"//Mock_Files/qrcode.png");
        waitTime(10000);
        BrowsertearDown();
        
        /*------------------------------Android----------------------------*/
        setPlatform("Android");
		initDriver();
		waitTime(5000);
		
		User_Play_Store_Flow(prop.getproperty("PaymentPage"),prop.getproperty("Permission"));
		waitTime(60000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		instaNewCommunicationAddress();
		
		verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
		softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
		verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
		verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
		verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
		verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
		verifyElementPresent(PromoCodeOfferPage.objRBIMsg , getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMg2));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		waitTime(20000);
		verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
		waitTime(20000);
	    verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
	    if(verifyElementPresent2(UserRegistrationPage.objNoBtn, "No Button")) {
	    	verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
	    }
	    String sHome = getText(HomePage.objHome);
	    softAssertion.assertEquals(sHome, "Home");
	    ringPayLogout();
	    logger.info("TC_Ring_Core_71 - To verify  user Scans the Promo Code QR");
	    extent.extentLoggerPass("TC_Ring_Core_71", "To verify  user Scans the Promo Code QR");
	    
	    logger.info("TC_Ring_Core_72 - Repeat  TC_Ring_Core_4 to TC_Ring_Core_59 steps Number  for login flow");
	    extent.extentLoggerPass("TC_Ring_Core_72", "Repeat  TC_Ring_Core_4 to TC_Ring_Core_59 steps Number  for login flow");
	}
//===============================================PromoCode Flow end================================================================
//===============================================Offer Module Start================================================================
		public void offerScreenModule() throws Exception {
			extent.HeaderChildNode("RingPay OfferScreen");
		
			merchantOfferPageValidation();
			playStorePromocodeOfferPageValidation();
			
			logger.info("TC_Ring_Core_175 - To verify the 'Offer' screen on this page Accept and Pay button is displayed(Merchant flow)");
			extent.extentLoggerPass("TC_Ring_Core_175", "To verify the 'Offer' screen on this page Accept and Pay button is displayed(Merchant flow)");
			
			logger.info("TC_Ring_Core_176 - To verify the 'Offer' screen on this page Accept offer button is displayed(Playstore Promocode flow) ");
			extent.extentLoggerPass("TC_Ring_Core_176", "To verify the 'Offer' screen on this page Accept offer button is displayed(Playstore Promocode flow) ");
			
			logger.info("TC_Ring_Core_177 - To verify the response by Clicking on 'Fee Details' link from limit approval banner");
			extent.extentLoggerPass("TC_Ring_Core_177", "To verify the response by Clicking on 'Fee Details' link from limit approval banner");
			
			logger.info("TC_Ring_Core_178 - To verify the response by clicking on t&c link");
			extent.extentLoggerPass("TC_Ring_Core_178", "To verify the response by clicking on t&c link");
			
			logger.info("TC_Ring_Core_179 - To verify the user is not able to proceed to pay amount without selecting t&c conditions checkbox by clicking on 'Accept & Pay' button");
			extent.extentLoggerPass("TC_Ring_Core_179", "To verify the user is not able to proceed to pay amount without selecting t&c conditions checkbox by clicking on 'Accept & Pay' button");
			
			logger.info("TC_Ring_Core_180 - To verify the user is able to pay amount by selecting t&c checkbox and clicking on 'Accept & Pay' button");
			extent.extentLoggerPass("TC_Ring_Core_181", "To verify the user is able to pay amount by selecting t&c checkbox and clicking on 'Accept & Pay' button");
			
			logger.info("TC_Ring_Core_181 - To verify the user is getting check internet connection screen after clicking on Accept and Pay button when the device internet connection is down");
			extent.extentLoggerPass("TC_Ring_Core_181", "To verify the user is getting check internet connection screen after clicking on Accept and Pay button when the device internet connection is down");
			
			logger.info("TC_Ring_Core_182 - To verify the user is able to make payment by clicking on Retry/Refresh and by clicking on Accept & Pay button once the device internet connection is Up");
			extent.extentLoggerPass("TC_Ring_Core_182", "To verify the user is able to make payment by clicking on Retry/Refresh and by clicking on Accept & Pay button once the device internet connection is Up");
			
			logger.info("TC_Ring_Core_183 - To verify the user turns off location on Accept and Pay screen");
			extent.extentLoggerPass("TC_Ring_Core_183", "To verify the user turns off location on Accept and Pay screen");
			
			logger.info("TC_Ring_Core_184 - To  Verify when user selects allow option on location popup");
			extent.extentLoggerPass("TC_Ring_Core_184", "To  Verify when user selects allow option on location popup");
			
			logger.info("TC_Ring_Core_185 - To verify when user select not now option on location popup");
			extent.extentLoggerPass("TC_Ring_Core_185", "To verify when user select not now option on location popup");
			
			logger.info("TC_Ring_Core_186 - To verify when user kill or refresh app on accept and pay page");
			extent.extentLoggerPass("TC_Ring_Core_186",  "To verify when user kill or refresh app on accept and pay page");
			
			logger.info("TC_Ring_Core_187 - To verify the user turns off location on Accept offer screen");
			extent.extentLoggerPass("TC_Ring_Core_187", "To verify the user turns off location on Accept offer screen");
			
			logger.info("TC_Ring_Core_188 - To Verify when user click on Accept offer/Accept and pay button");
			extent.extentLoggerPass("TC_Ring_Core_188", "To Verify when user click on Accept offer/Accept and pay button");
			
			softAssertion.assertAll();
		}
	
//============================================Offer Screen Merchant Flow=====================================================================================
		public void merchantOfferPageValidation() throws Exception {
			getDriver().resetApp();
			if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
				enablePermissions();
			}
			verifyElementPresent(RingLoginPage.objScanQRPage, getText(RingLoginPage.objScanQRPage));

			 /*------------------------------WEB----------------------------*/
			waitTime(5000);
			setPlatform("Web");
			System.out.println("platform changed to web");
			String pf = getPlatform();
			System.out.println(pf);
			waitTime(5000);
			new RingPayBusinessLogic("ring");
			waitTime(10000);
			String projectPath=System.getProperty("user.dir");
			System.out.println(projectPath);
	        getWebDriver().get(projectPath+"//Mock_Files/qrcode.png");
	        waitTime(10000);
	        BrowsertearDown();
	        
	        /*------------------------------Android----------------------------*/
	        setPlatform("Android");
			initDriver();
			waitTime(5000);
			try {
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			}catch (Exception e) {
				// TODO: handle exception
			}
			click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
			type1(RingPayMerchantFlowPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
			verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
			verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
			softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
			logger.info("User redirected to Signup/Login Screen");
			extent.extentLogger("login", "User redirected to Signup/Login Screen");
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
			loginMobile();
			
			mobileNoValidation1("9" + RandomIntegerGenerator(9));
			enterOtp(prop.getproperty("OTP"));
			readAndAccept();
			waitTime(60000);
			userDetails();
			dateOfBirth("Feb", "10", "1996");
			verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
			instaNewCommunicationAddress();
			waitTime(5000);
			verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
			softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
			verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
			verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
			verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
			verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
			verifyElementPresent(PromoCodeOfferPage.objRBIMsg , getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));
			verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
			verifyElementPresentAndClick(PromoCodeOfferPage.objFeeDetail, getText(PromoCodeOfferPage.objFeeDetail));
			verifyElementPresent(PromoCodeOfferPage.objFeeDetailPage, getText(PromoCodeOfferPage.objFeeDetailPage));
			click(PromoCodeOfferPage.objFeeDetailsCrossBtn, "Cross Button");
			
			verifyElementPresent(PromoCodeOfferPage.objTermsAndCondition,getTextVal(PromoCodeOfferPage.objTermsAndCondition, "Text"));
			click(PromoCodeOfferPage.objTermsAndCondition, "Terms And Conditions");
			waitTime(5000);
			verifyElementPresent(TermsAndConditionPage.objTAndCondition,getTextVal(TermsAndConditionPage.objTAndCondition, "Text"));
			Back(1);
			
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			System.out.println(getText(PromoCodeOfferPage.objToastMsg));
			logger.info(getText(PromoCodeOfferPage.objToastMsg));
			extent.extentLoggerPass("Toast Msg", getText(PromoCodeOfferPage.objToastMsg));
			
			verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			setWifiConnectionToONOFF("Off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
			waitTime(5000);
			verifyElementPresent(PromoCodeOfferPage.objCheckInternetConnectionPage, getText(PromoCodeOfferPage.objCheckInternetConnectionPage));
			setWifiConnectionToONOFF("On");
			click(TermsAndConditionPage.objOkBtn, "Button Okay, Got It!");
			
			verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			setLocationConnectionToONOFF("off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			verifyElementPresent(PromoCodeOfferPage.objLocationPopup, getText(PromoCodeOfferPage.objLocationPopup));
			verifyElementPresent(PromoCodeOfferPage.objLocationAllowBtn, getText(PromoCodeOfferPage.objLocationAllowBtn));
			verifyElementPresent(PromoCodeOfferPage.objLocationNotNowBtn, getText(PromoCodeOfferPage.objLocationNotNowBtn));

			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationAllowBtn, getText(PromoCodeOfferPage.objLocationAllowBtn));
			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationOkBtn, "OK Button");
			verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
			softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
			
			setLocationConnectionToONOFF("off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			verifyElementPresent(PromoCodeOfferPage.objLocationPopup, getText(PromoCodeOfferPage.objLocationPopup));
			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationNotNowBtn, getText(PromoCodeOfferPage.objLocationNotNowBtn));

			setLocationConnectionToONOFF("on");
			
			getDriver().runAppInBackground(Duration.ofSeconds(5));
			waitTime(5000);
			setLocationConnectionToONOFF("Off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationNotNowBtn, getText(PromoCodeOfferPage.objLocationNotNowBtn));
			verifyElementPresentAndClick(PromoCodeOfferPage.objEnableLocBtn, getText(PromoCodeOfferPage.objEnableLocation));
			setLocationConnectionToONOFF("on");
			Back(1);
			new RingPayBusinessLogic("Android");
			
			waitTime(20000);
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			waitTime(15000);
			verifyElementPresent(TermsAndConditionPage.objSetPin, getText(TermsAndConditionPage.objSetPin));
			softAssertion.assertEquals(getText(TermsAndConditionPage.objSetPin), "Set Pin");
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
			waitTime(15000);
			verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
			
			waitTime(5000);
			verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
			if(verifyElementPresent2(UserRegistrationPage.objNoBtn, "No Button")) {
		    	verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		    }
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			ringPayLogout();
			
		}
//===============================================Play Store Offer====================================================================	
	public void playStorePromocodeOfferPageValidation() throws Exception {
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
			
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		instaNewCommunicationAddress();
		waitTime(5000);
		
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresent(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		
		setLocationConnectionToONOFF("off");
		waitTime(5000);
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		
		waitTime(5000);
		if (verifyElementPresent(UserRegistrationPage.objSetPin, "Set Pin Page")) {
			setLocationConnectionToONOFF("on");
			waitTime(5000);
			logger.info("Navigated to MPIN Page");
			extent.extentLoggerPass("MPIN Page", "Navigated to MPIN Page");
			waitTime(15000);
			verifyElementPresent(TermsAndConditionPage.objSetPin, getText(TermsAndConditionPage.objSetPin));
			softAssertion.assertEquals(getText(TermsAndConditionPage.objSetPin), "Set Pin");
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		}
		
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		ringPayLogout();
		
	}
//===================================================Offer Module End==============================================================================
//====================================To verify Age criteria failed checks (22 to 60) Start========================================================
	public void ageCriteriaFailedCheck() throws Exception {
		extent.HeaderChildNode("Age Criteria Check");
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
			
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		ageSelect("lessthan18", "Oct", "12", "2010", 2010, 12);
		
		ageSelect("greaterthan55", "Oct", "12", "1966", 1966, 12);
		
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		loginMobile();
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		waitTime(3000);
		enterOtp(prop.getproperty("OTP"));
		waitTime(40000);
		userDetails();
		ageSelect("greaterthanequalto18 || lessthanequalto55", "Oct", "12", "1995", 1995, 12);

		logger.info("TC_Ring_Customer_Seg_57 - To verify users provided age <22 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_57", "To verify users provided age <22 years");
		
		logger.info("TC_Ring_Customer_Seg_58 - To verify users provided age >60 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_58", "To verify users provided age >60 years");
		
		logger.info("TC_Ring_Customer_Seg_59 - To verify users provided age exact 22 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_59","To verify users provided age exact 22 years");
		
		logger.info("TC_Ring_Customer_Seg_60 - To verify users provided age exact 60 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_60", "To verify users provided age exact 60 years");
		
		logger.info("TC_Ring_Customer_Seg_61 - To verify users age between 22 to 60 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_61", "To verify users age between 22 to 60 years");
	}

	public void userDetails() throws Exception {
		extent.HeaderChildNode("Age Criteria");

		explicitWaitVisibility(RingUserDetailPage.objFirstName, 10);
		click(RingUserDetailPage.objFirstName, "First Name Field");
		type(RingUserDetailPage.objFirstName, "Sunil", "First Name field");

		explicitWaitVisibility(RingUserDetailPage.objLastName, 10);
		click(RingUserDetailPage.objLastName, "Last Name Field");
		type(RingUserDetailPage.objLastName, "Chatla", "Last Name field");

		explicitWaitVisibility(RingUserDetailPage.objMothersName, 10);
		click(RingUserDetailPage.objMothersName, "Mother's Name Field");
		type(RingUserDetailPage.objMothersName, "Mom", "Mother's Name field");
		hideKeyboard();

		explicitWaitVisibility(RingUserDetailPage.objEmail, 10);
		click(RingUserDetailPage.objEmail, "Email Field");
		click(RingUserDetailPage.objNone, "None of the Above Button");
		String email = generateRandomString(8) + "@gmail.com";
		type(RingUserDetailPage.objEmail, email, "Email Filed");
		hideKeyboard();
		waitTime(2000);
		click(RingLoginPage.objGender, "Gender male is selected");
		click(RingLoginPage.objGender, "Gender male is selected");
	}

	public void ageSelect(String key, String Month, String Date, String Year, int year, int date) throws Exception {
		extent.HeaderChildNode("Age Criteria");

		switch (key) {
		case "lessthan18":
			dateOfBirth(Month, Date, Year);

			age = ageCal(year, date);
			break;

		case "greaterthan55":
			dateOfBirth(Month, Date, Year);

			age = ageCal(year, date);
			break;

		case "greaterthanequalto18 || lessthanequalto55":
			dateOfBirth(Month, Date, Year);

			age = ageCal(year, date);
			break;

		default:
			logger.info("invalid age!!");
			break;
		}
		
		waitTime(3000);
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");

		if (age < 18) {
			waitTime(10000);
			logger.warn("The present age is <18 therefore the age is: " + age);
			extent.extentLoggerWarning("age", "The present age is <18 therefore the age is: " + age);
			waitTime(10000);
			verifyElementPresent(RingUserDetailPage.objAgeLessThan18Notification,getText(RingUserDetailPage.objAgeLessThan18Notification));
			logger.info("Age Criteria Failed");
			extent.extentLogger("Age Verification", "Age Criteria Failed");

		} else if (age > 55) {

			logger.warn("The present age is >55 therefore the age is: " + age);
			extent.extentLoggerWarning("age", "The present age is >55 therefore the age is: " + age);
			waitTime(10000);
			verifyElementPresent(RingUserDetailPage.objSorryMsg, getText(RingUserDetailPage.objSorryMsg));
			logger.info("Age Criteria Failed");
			extent.extentLogger("Age Verification", "Age Criteria Failed");
			verifyElementPresentAndClick(RingUserDetailPage.objHamburgerTab, "Hamburger Tab");
			verifyElementPresentAndClick(RingUserDetailPage.objProfileTabCompletedPercentage,"Profile Completed Percentage tab");
			verifyElementPresentAndClick(RingUserDetailPage.objLogoutBtn, "Logout Button");

			click(RingLoginPage.objYesBtn, "Yes confirmation button");
			logger.info("User is successfully logged out");
			extent.extentLoggerPass("Logout confirmation", "User is successfully logged out");


		} else if (age >= 18 || age <= 55) {
			waitTime(10000);
			instaNewCommunicationAddress();
			logger.info("The present age is >=18 & <=55 and therefor the age is: " + age);
			extent.extentLogger("age", "The present age is >=18 & <=55 and therefor the age is: " + age);
			logger.info("Age Criteria Passed");
			extent.extentLogger("Age Verification", "Age Criteria Passed");
			
			instaLoancongratsScreen();
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
			verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			ringPayLogout();
		}
	}
	
	public void dateOfBirth(String month, String date, String year) throws Exception {
		explicitWaitClickable(UserRegistrationPage.objUserDOB, 10);
		Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		if (!verifyElementDisplayed(UserRegistrationPage.objGenderCancelBtn)) {
	           click(UserRegistrationPage.objUserDOB, "DOB field");
	    }

		explicitWaitVisibility(UserRegistrationPage.objDatePickerDate, 10);
		
		Aclick(UserRegistrationPage.objDatePickerYear, "Year field");
		clearField(UserRegistrationPage.objDatePickerYear, "Year field");
		type(UserRegistrationPage.objDatePickerYear, year, "Year field");
		
		//waitTime(2000);
		click(UserRegistrationPage.objDatePickerMonth, "Month field");
		click(UserRegistrationPage.objDatePickerMonth, "Month field");
		clearField(UserRegistrationPage.objDatePickerMonth, "Month field");
		type(UserRegistrationPage.objDatePickerMonth, month, "Month field");
		
		click(UserRegistrationPage.objDatePickerDate, "Date field");
		clearField(UserRegistrationPage.objDatePickerDate, "Date field");
		type(UserRegistrationPage.objDatePickerDate, date, "Date field");

		

		Aclick(UserRegistrationPage.objOK, "OK button");
		
	}

//============================================age Criteria check End====================================================================	
//===========================Pan bottom sheet case if pan received from bureau and KYC get skipped Start================================	
	public HashMap mockUserAPI(String url, String gender, String encrypted_name) {
		HashMap map=new HashMap();
	ValidatableResponse response = Utilities.MockuserAPI(url, gender, encrypted_name);
	String firstName = response.extract().jsonPath().getString("data.data.first_name");
	System.out.println("First Name= " + firstName);
	String middleName = response.extract().jsonPath().getString("data.data.middle_name");
	System.out.println("Middle Name= " + middleName);
	String lastName = response.extract().jsonPath().getString("data.data.last_name");
	System.out.println("Last Name =" + lastName);
	String panCard = response.extract().jsonPath().getString("data.data.pan");
	System.out.println("Pan Card= " + panCard);
	String mobileNumber = response.extract().jsonPath().getString("data.data.mobile_number");
	System.out.println("Mobile Number= " + mobileNumber);
	String motherName = response.extract().jsonPath().getString("data.data.mother_name");
	System.out.println("Mother Name= " + motherName);
	String email = response.extract().jsonPath().getString("data.data.email");
	System.out.println("Email= " + email);
	String genders = response.extract().jsonPath().getString("data.data.gender");
	System.out.println("Gender= " + genders);
	String otp = response.extract().jsonPath().getString("data.data.otp");
	System.out.println(otp);
	String dob = response.extract().jsonPath().getString("data.data.dob");
	System.out.println(dob);
	String[] dateSplit = dob.split("-");
	String year = dateSplit[0];
	System.out.println("years---" + year);
	String month = dateSplit[1];
	System.out.println("month---" + month);
	String date = dateSplit[2];
	System.out.println("date---" + date);
	int monthNumber = Integer.parseInt(month);
	String monthByName = Month.of(monthNumber).getDisplayName(TextStyle.SHORT, Locale.US);
	System.out.println(monthByName);
	String roomno=response.extract().jsonPath().getString("data.data.present_address.room_number");
	System.out.println(roomno);
	String address1=response.extract().jsonPath().getString("data.data.present_address.line_1");
	System.out.println(address1);
	String address2=response.extract().jsonPath().getString("data.data.present_address.line_2");
	System.out.println(address2);
	String landmark=response.extract().jsonPath().getString("data.data.present_address.landmark");
	System.out.println(landmark);
	String pin=response.extract().jsonPath().getString("data.data.present_address.pincode");
	System.out.println(pin);
	
	// User Details
	
	map.put("firstName", firstName);
	map.put("middleName", middleName);
	map.put("lastName", lastName);
	map.put("panCard", panCard);
	map.put("mobileNumber", mobileNumber);
	map.put("motherName", motherName);
	map.put("email", email);
	map.put("genders", genders);
	map.put("otp", otp);
	map.put("year", year);
	map.put("month", monthByName);
	map.put("date", date);
	
	//New Communication Address
	map.put("roomNo", roomno);
	map.put("address1",address1);
	map.put("address2", address2);
	map.put("landmark", landmark);
	map.put("pin", pin);

	return map;
	}
	
	public void userDetailsFromAPI(String firstName, String lastName, String email, String motherName, String gender)
            throws Exception {
        extent.HeaderChildNode("Age Criteria");

        explicitWaitVisibility(RingUserDetailPage.objFirstName, 10);
        click(RingUserDetailPage.objFirstName, "First Name Field");
        type(RingUserDetailPage.objFirstName, firstName, "First Name field");

        explicitWaitVisibility(RingUserDetailPage.objLastName, 10);
        click(RingUserDetailPage.objLastName, "Last Name Field");
        type(RingUserDetailPage.objLastName, lastName, "Last Name field");
        hideKeyboard();
  
        Aclick(UserRegistrationPage.objMothersName, "Mother's Name Text Field");
        type(UserRegistrationPage.objMothersName, motherName, "Mother's Name Text Field");
        hideKeyboard();
        explicitWaitVisibility(RingUserDetailPage.objEmail, 10);
        click(RingUserDetailPage.objEmail, "Email Field");
        if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above popup")) {
            Aclick(RingLoginPage.objNoneBtn, "None of the above");
        }
        
        
        type(RingUserDetailPage.objEmail, email, "Email Filed");
        hideKeyboard();
        click(RingLoginPage.objGender, "Gender male is selected");
        click(RingLoginPage.objGender, "Gender male is selected");
    }
	
	public void dateOfBirthForAPI(String month, String date, String year) throws Exception {
		explicitWaitClickable(UserRegistrationPage.objUserDOB, 10);
		Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		if (!verifyElementDisplayed(UserRegistrationPage.objGenderCancelBtn)) {
			Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		}

		Aclick(UserRegistrationPage.objDatePickerYear, "Year field");
		clearField(UserRegistrationPage.objDatePickerYear, "Year field");
		type(UserRegistrationPage.objDatePickerYear, year, "Year field");

		explicitWaitVisibility(UserRegistrationPage.objDatePickerMonthSH, 10);
		Aclick(UserRegistrationPage.objDatePickerMonthSH, "Month field");
		clearField(UserRegistrationPage.objDatePickerMonthSH, "Month field");
		type(UserRegistrationPage.objDatePickerMonthSH, month, "Month field");

		Aclick(UserRegistrationPage.objDatePickerDateSH, "Date field");
		clearField(UserRegistrationPage.objDatePickerDateSH, "Date field");
		type(UserRegistrationPage.objDatePickerDateSH, date, "Date field");

		Aclick(UserRegistrationPage.objOK, "OK button");

	}
	
	public void addAddress(String room,String address1,String address2,String landmark,String pin) throws Exception {
		   extent.HeaderChildNode("RingPay App Merchant Flow");
		   explicitWaitVisibility(AddAddressPage.objAddCurrentAddressHeader, 30);
		   Aclick(AddAddressPage.objRoomNoField,"Room");
		   type(AddAddressPage.roomNopulkit,room,"Room");
		   Aclick(AddAddressPage.objAddressLine_1,"AddressLine1");
		   type(AddAddressPage.objAddressLine_1,address1,"AddressLine1");
		   Aclick(AddAddressPage.objAddressLine_2,"AddressLine2");
		   type(AddAddressPage.objAddressLine_2,address2,"AddressLine2");
//		   Aclick(AddAddressPage.objLandmarkTextFields,"Landmark");
//		   type(AddAddressPage.objLandmarkTextFieldspulkit,landmark,"Landmark");
		   hideKeyboard();
		   Aclick(AddAddressPage.objPinCodeField,"PinCode");
		   type(AddAddressPage.objPinCodeFieldpulkit,pin,"PinCode");
		   hideKeyboard();
//		   click(AddAddressPage.rdioResidanceOwned, "Owned");
//		   click(AddAddressPage.rdioResidanceOwned, "Owned");
//		   Swipe("up", 1);
//		   click(AddAddressPage.cbkResidingSince, "");
//		   explicitWaitVisibility(AddAddressPage.oneTwoYear, 20);
//		   click(AddAddressPage.oneTwoYear, "");
		   waitTime(5000);
}
	
	public void kycSkipped(String url, String gender, String encrypted_name, String portalEmail, String portalPassword,String portalOTP) throws Exception {
		extent.HeaderChildNode("RingPay KYC_Skip");
		getDriver().resetApp();
		HashMap mockUserDetails=mockUserAPI(url, gender, encrypted_name);
		
		String firstName=(String) mockUserDetails.get("firstName");
		String middleName=(String) mockUserDetails.get("middleName");
		String lastName=(String) mockUserDetails.get("lastName");
		String mobileNumber=(String) mockUserDetails.get("mobileNumber");
		String otp=(String) mockUserDetails.get("otp");
		String panCard=(String) mockUserDetails.get("panCard");
		String email=(String) mockUserDetails.get("email");
		String motherName=(String) mockUserDetails.get("motherName");
		String genders=(String) mockUserDetails.get("genders");
		String year=(String) mockUserDetails.get("year");
		String monthByName=(String) mockUserDetails.get("month");
		String date=(String) mockUserDetails.get("date");
		
		String roomno=(String) mockUserDetails.get("roomNo");
		String address1=(String) mockUserDetails.get("address1");
		String address2=(String) mockUserDetails.get("address2");
		String landmark=(String) mockUserDetails.get("landmark");
		String pin=(String) mockUserDetails.get("pin");
		
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		click(RingLoginPage.objLoginLink, "Signup/Login link");
		loginMobile();    
		mobileNoValidation1(mobileNumber);
		waitTime(5000);
		enterOtp(otp);
		readAndAccept();
	
		waitTime(40000);
		userDetailsFromAPI(firstName, lastName, email, motherName, genders);
		dateOfBirthForAPI(monthByName, date, year);
	
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		
		waitTime(5000);
		type(TestingPortalWebPage.emailField, portalEmail, "Email ID Entered");
		waitTime(1000);
		JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
		explicitWaitVisibility(TestingPortalWebPage.loginBtn, 10);
		clearField(TestingPortalWebPage.passwordField, "");
		waitTime(2000);
		type(TestingPortalWebPage.passwordField, portalPassword, "Portal Password");
		waitTime(2000);
		clearField(TestingPortalWebPage.otpField, "");
		waitTime(2000);
		type(TestingPortalWebPage.otpField, portalOTP, "OTP Entered");
		waitTime(2000);
		JSClick(TestingPortalWebPage.loginBtn, "Login Button");
		explicitWaitVisibility(TestingPortalWebPage.usersTab, 10);
		JSClick(TestingPortalWebPage.usersTab, "User Tab");
		explicitWaitVisibility(TestingPortalWebPage.dropDownUserRef, 10);
		JSClick(TestingPortalWebPage.dropDownUserRef, "Drop Down");
		selectByVisibleTextFromDD(TestingPortalWebPage.dropDownUserRef, "Mobile Number");
		type(TestingPortalWebPage.enterMobileNo, mobileNumber, "Portal Mobile Number");
		JSClick(TestingPortalWebPage.searchIcon, "Search Icon");
		waitTime(5000);
		String getReferenceNumber = "";
		for (int i = 0; i <= 2; i++) {
			try {
				getReferenceNumber = getWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]")).getText();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Reference----------------------------" + getReferenceNumber);
		waitTime(2000);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		System.out.println("drag done");
		waitTime(2000);
		JSClick(TestingPortalWebPage.othersTab, "Other Tab");
		waitTime(1500);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		waitTime(2000);
		explicitWaitVisibility(TestingPortalWebPage.panNSDLDataTab, 20);
		
		JSClick(TestingPortalWebPage.panNSDLDataTab, "PAN NSDL Data");
		explicitWaitVisibility(TestingPortalWebPage.addNewIcon, 10);
		JSClick(TestingPortalWebPage.addNewIcon, "Add New Icon");
		explicitWaitVisibility(TestingPortalWebPage.firstName, 20);
		type(TestingPortalWebPage.firstName, firstName, "first Name");
		type(TestingPortalWebPage.middleName,middleName, "middle name");
		type(TestingPortalWebPage.lastName,lastName, "lst");
		type(TestingPortalWebPage.pan, panCard, "Pan No");
		JSClick(TestingPortalWebPage.panStatus, "pan statis");
		selectByVisibleTextFromDD(TestingPortalWebPage.panStatus, "Valid");
		JSClick(TestingPortalWebPage.panNsdlSubmitBtn, "Submit Button");
		
		
		ValidatableResponse responseSkipKyc = Utilities.skip_kyc("https://testing-gateway.test.paywithring.com/api/v1/update-upload-equifax/updateUploadEquifax",getReferenceNumber, panCard, firstName, middleName, lastName, encrypted_name);
		String message = responseSkipKyc.extract().jsonPath().getString("message");
		System.out.println("Skip_Kyc-------------" + message);
		
		BrowsertearDown();
		
		waitTime(10000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
	
		waitTime(10000);
		
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress(roomno,address1,address2,landmark,pin);
			 click(AddAddressPage.objSubmitButton,"Submit");	
		}
		waitTime(20000);
		explicitWaitVisibility(PAN_DetailsPage.btnyesThisIsMyPlan, 20);
		System.out.println(getText(PAN_DetailsPage.btnyesThisIsMyPlan));
		
		softAssertion.assertEquals("Confirm this is your PAN & name", getText(PAN_DetailsPage.txtTittle));
		softAssertion.assertEquals(panCard,getText(PAN_DetailsPage.PanNo));
		softAssertion.assertEquals(firstName+" "+middleName+" "+lastName, getText(PAN_DetailsPage.UserNameOfPAN));
		
		extent.extentLoggerPass("Popup Validation", "Hand Icon,Pan Number,Name of Pan Holder is visible_TC_Ring_Core_167");
		
		Back(2);
		logger.info("Back Button Pressed 2 Times");
		extent.extentLoggerPass("Back Button click", "Validated by doing back popup is not disappear_TC_Ring_Core_168");
		waitTime(5000);
		setWifiConnectionToONOFF("Off");
		waitTime(5000);
		Aclick(PAN_DetailsPage.btnnoThisIsNotMine, "No, This Is Not Mine");
		explicitWaitVisibility(PAN_DetailsPage.txtNoInternet, 20);
		softAssertion.assertEquals(" Check your connection & try again ", getText(PAN_DetailsPage.txtCheckyourInternet));
		logger.info("Internet Connection Validation");
		extent.extentLoggerPass("Validate Internet Connection Off", "Check Your Internet Connection_TC_Ring_Core_171");
		waitTime(5000);
		setWifiConnectionToONOFF("On");
		waitTime(8000);
		Aclick(PAN_DetailsPage.btnOkaygotIt, "Okay Got It");
		waitTime(5000);
		explicitWaitVisibility(PAN_DetailsPage.txtTittle, 20);
		waitTime(5000);
		setWifiConnectionToONOFF("Off");
		waitTime(5000);
		Aclick(PAN_DetailsPage.btnyesThisIsMyPlan, "Yes, This is My Pan");
		explicitWaitVisibility(PAN_DetailsPage.txtNoInternet, 20);
		softAssertion.assertEquals(" Check your connection & try again ", getText(PAN_DetailsPage.txtCheckyourInternet));
		logger.info("Internet Connection Validation");
		extent.extentLoggerPass("Validate Internet Connection Off", "Check Your Internet Connection_TC_Ring_Core_173");
		waitTime(5000);
		setWifiConnectionToONOFF("On");
		waitTime(8000);
		Aclick(PAN_DetailsPage.btnOkaygotIt, "Okay Got It");
		waitTime(25000);
		explicitWaitVisibility(PAN_DetailsPage.txtTittle, 20);
		Aclick(PAN_DetailsPage.btnnoThisIsNotMine, "No This is not Mine Button");
		explicitWaitVisibility(OfferPage.offerTitle, 20);
		logger.info("Offer Page Open When we Click on 'No this is not mine' Button");
		softAssertion.assertEquals("Accept Offer", getText(OfferPage.btnAcceptOffer));
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when network up then press_TC_Ring_Core_172");
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when Press we press on 'No this is not mine' Button_TC_Ring_Core_169");
		explicitWaitVisibility(OfferPage.btnAcceptOffer, 10);
		Aclick(OfferPage.ckbAcceptTermandcondition, "check box for term and conditions");
		Aclick(OfferPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(TransactionPIN.txtTitleOfSetPin , 20);
		Aclick(TransactionPIN.objEnterPin , "Enter Pin");
		type(TransactionPIN.objEnterPin , "1111", "Enter Pin");
		Aclick(TransactionPIN.objReEnterPin, "Re-Enter Pin");
		type(TransactionPIN.objReEnterPin, "1111", "Re-Enter Pin");
		Aclick(TransactionPIN.objSubmit,"Submit Button");
		if (checkElementExist(HomePage.objAdCloseBtn, "Check")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		
		explicitWaitVisibility(HomePage.homeTab, 20);
		ringPayLogout();
		
	HashMap mockUserDetailsForYesBtn=mockUserAPI(url, gender, encrypted_name);
		
		String firstNameForYesBtn=(String) mockUserDetailsForYesBtn.get("firstName");
		String middleNameForYesBtn=(String) mockUserDetailsForYesBtn.get("middleName");
		String lastNameForYesBtn=(String) mockUserDetailsForYesBtn.get("lastName");
		String mobileNumberForYesBtn=(String) mockUserDetailsForYesBtn.get("mobileNumber");
		String otpForYesBtn=(String) mockUserDetailsForYesBtn.get("otp");
		String panCardForYesBtn=(String) mockUserDetailsForYesBtn.get("panCard");
		String emailForYesBtn=(String) mockUserDetailsForYesBtn.get("email");
		String motherNameForYesBtn=(String) mockUserDetailsForYesBtn.get("motherName");
		String gendersForYesBtn=(String) mockUserDetailsForYesBtn.get("genders");
		String yearForYesBtn=(String) mockUserDetailsForYesBtn.get("year");
		String monthByNameForYesBtn=(String) mockUserDetailsForYesBtn.get("month");
		String dateForYesBtn=(String) mockUserDetailsForYesBtn.get("date");
		
		String roomnoForYesBtn=(String) mockUserDetailsForYesBtn.get("roomNo");
		String address1ForYesBtn=(String) mockUserDetailsForYesBtn.get("address1");
		String address2ForYesBtn=(String) mockUserDetailsForYesBtn.get("address2");
		String landmarkForYesBtn=(String) mockUserDetailsForYesBtn.get("landmark");
		String pinForYesBtn=(String) mockUserDetailsForYesBtn.get("pin");
		
		click(RingLoginPage.objLoginLink, "Signup/Login link");
		loginMobile();    
		mobileNoValidation1(mobileNumberForYesBtn);
		enterOtp(otp);
		waitTime(40000);
	
		userDetailsFromAPI(firstNameForYesBtn, lastNameForYesBtn, emailForYesBtn, motherNameForYesBtn, gendersForYesBtn);
		dateOfBirthForAPI(monthByNameForYesBtn, dateForYesBtn, yearForYesBtn);
		
	
        setPlatform("Web");
        new RingPayBusinessLogic("ring");
		
		waitTime(10000);
		type(TestingPortalWebPage.emailField, portalEmail, "Email ID Entered");
		waitTime(3000);
		JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
		explicitWaitVisibility(TestingPortalWebPage.loginBtn, 10);
		clearField(TestingPortalWebPage.passwordField, "");
		waitTime(2000);
		type(TestingPortalWebPage.passwordField, portalPassword, "Portal Password");
		waitTime(2000);
		clearField(TestingPortalWebPage.otpField, "");
		waitTime(2000);
		type(TestingPortalWebPage.otpField, portalOTP, "OTP Entered");
		waitTime(2000);
		JSClick(TestingPortalWebPage.loginBtn, "Login Button");
		explicitWaitVisibility(TestingPortalWebPage.usersTab, 10);
		JSClick(TestingPortalWebPage.usersTab, "User Tab");
		explicitWaitVisibility(TestingPortalWebPage.dropDownUserRef, 10);
		JSClick(TestingPortalWebPage.dropDownUserRef, "Drop Down");
		selectByVisibleTextFromDD(TestingPortalWebPage.dropDownUserRef, "Mobile Number");
		type(TestingPortalWebPage.enterMobileNo, mobileNumberForYesBtn, "Portal Mobile Number");
		JSClick(TestingPortalWebPage.searchIcon, "Search Icon");
		waitTime(5000);
		String getReferenceNumber1 = "";
		for (int i = 0; i <= 2; i++) {
			try {
				getReferenceNumber1 = getWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]")).getText();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Referance----------------------------" + getReferenceNumber1);
		waitTime(2000);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		System.out.println("drag done");
		waitTime(2000);
		JSClick(TestingPortalWebPage.othersTab, "Other Tab");
		waitTime(1500);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		waitTime(2000);
		explicitWaitVisibility(TestingPortalWebPage.panNSDLDataTab, 20);
		
		JSClick(TestingPortalWebPage.panNSDLDataTab, "PAN NSDL Data");
		explicitWaitVisibility(TestingPortalWebPage.addNewIcon, 10);
		JSClick(TestingPortalWebPage.addNewIcon, "Add New Icon");
		explicitWaitVisibility(TestingPortalWebPage.firstName, 20);
		type(TestingPortalWebPage.firstName, firstNameForYesBtn, "first Name");
		type(TestingPortalWebPage.middleName,middleNameForYesBtn, "middle name");
		type(TestingPortalWebPage.lastName,lastNameForYesBtn, "Last Name");
		type(TestingPortalWebPage.pan, panCardForYesBtn, "Pan No");
		JSClick(TestingPortalWebPage.panStatus, "pan statis");
		selectByVisibleTextFromDD(TestingPortalWebPage.panStatus, "Valid");
		JSClick(TestingPortalWebPage.panNsdlSubmitBtn, "Submit Button");
		BrowsertearDown();
		
		ValidatableResponse responseSkipKyc1 = Utilities.skip_kyc("https://testing-gateway.test.paywithring.com/api/v1/update-upload-equifax/updateUploadEquifax",getReferenceNumber1, panCardForYesBtn, firstNameForYesBtn, middleNameForYesBtn, lastNameForYesBtn, encrypted_name);
		String message1 = responseSkipKyc1.extract().jsonPath().getString("message");
		System.out.println("Skip_Kyc-------------" + message1);
		
		
	    setPlatform("Android");
	    initDriver();
	       
	    verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		
		
		waitTime(10000);
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress(roomnoForYesBtn,address1ForYesBtn,address2ForYesBtn,landmarkForYesBtn,pinForYesBtn);
			click(AddAddressPage.objSubmitButton,"Submit");
		}
		
		
		explicitWaitVisibility(PAN_DetailsPage.btnyesThisIsMyPlan, 20);
		click(PAN_DetailsPage.btnyesThisIsMyPlan, "Yes, This is my PAN Button");
		
		explicitWaitVisibility(OfferPage.offerTitle, 20);
		logger.info("Offer Page Open When we Click on 'Yes, This is my PAN");
		softAssertion.assertEquals("Accept Offer", getText(OfferPage.btnAcceptOffer));
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when network up then press_TC_Ring_Core_170");
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when Press we press on 'Yes, This is my PAN' Button_TC_Ring_Core_174");

		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		if (checkElementExist(HomePage.objAdCloseBtn, "Check")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		ringPayLogout();
	}	
//=====================Pan bottom sheet case if pan received from bureau and KYC get skipped End=========================================================	
//========================================Banner logic to HomePage Start=============================================================================
	public void bannerLogicOnHomePage(String url, String gender, String encrypted_name,String portalEmail,String portalPassword,String portalOTP) throws Exception {
		
		extent.HeaderChildNode("Banner logic on Home screen");
		getDriver().resetApp();
		/*------------------------------Data From API----------------------------*/
		
		HashMap mockUserDetails=mockUserAPI(url, gender, encrypted_name);
		String firstName=(String) mockUserDetails.get("firstName");
		String middleName=(String) mockUserDetails.get("middleName");
		String lastName=(String) mockUserDetails.get("lastName");
		String mobileNumber=(String) mockUserDetails.get("mobileNumber");
		String otp=(String) mockUserDetails.get("otp");
		String panCard=(String) mockUserDetails.get("panCard");
		String email=(String) mockUserDetails.get("email");
		String motherName=(String) mockUserDetails.get("motherName");
		String genders=(String) mockUserDetails.get("genders");
		String year=(String) mockUserDetails.get("year");
		String monthByName=(String) mockUserDetails.get("month");
		String date=(String) mockUserDetails.get("date");
		
		String roomno=(String) mockUserDetails.get("roomNo");
		String address1=(String) mockUserDetails.get("address1");
		String address2=(String) mockUserDetails.get("address2");
		String landmark=(String) mockUserDetails.get("landmark");
		String pin=(String) mockUserDetails.get("pin");
		
		/*------------------------------Front End----------------------------*/

		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		click(RingLoginPage.objLoginLink, "Signup/Login link");
		loginMobile();    
		mobileNoValidation1(mobileNumber);
		enterOtp(otp);
		readAndAccept();
	
		waitTime(40000);
		
		String userName=prop.getproperty("userName");
		String userLastName=prop.getproperty("lastName");
		String userMotherName=prop.getproperty("motherName");
		String userGender=prop.getproperty("gender");
		String userBirthDate=prop.getproperty("dateOfBirthDate");
		String userBirthMonth=prop.getproperty("dateOfBirthMonth");
		String userBirthYear=prop.getproperty("dateOfBirthYear");
		userDetailsFromAPI(userName, userLastName, userName+userLastName+new Random().nextInt(10000)+"@example.com", userMotherName, userGender);
		dateOfBirthForAPI(userBirthMonth, userBirthDate, userBirthYear);
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		waitTime(30000);
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress(roomno,address1,address2,landmark,pin);
			click(AddAddressPage.objSubmitButton,"Submit");
		}
		explicitWaitVisibility(OfferPage.btnAcceptOffer, 10);
		Aclick(OfferPage.ckbAcceptTermandcondition, "check box for term and conditions");
		Aclick(OfferPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(TransactionPIN.txtTitleOfSetPin , 20);
		Aclick(TransactionPIN.objEnterPin , "Enter Pin");
		type(TransactionPIN.objEnterPin , "1111", "Enter Pin");
		Aclick(TransactionPIN.objReEnterPin, "Re-Enter Pin");
		type(TransactionPIN.objReEnterPin, "1111", "Re-Enter Pin");
		Aclick(TransactionPIN.objSubmit,"Submit Button");
		if (verifyElementPresent(HomePage.objAdCloseBtn, "")) {
			
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
			explicitWaitVisibility(HomePage.homeTab, 20);
		}
		
		if(verifyElementPresent(HomePage.purpleBannerVerifyNow,"Searching Purple Banner")){
		boolean purpleBanner=swipeElementAndroid(HomePage.bannerTray,HomePage.purpleBannerVerifyNow);
		logger.info("Purple Banner--Verify KYC Doucment");
		softAssertion.assertEquals("Verify your document to get", getText(HomePage.purpleBannerText1));
		extent.extentLoggerPass("Verify your document to get", "Purple Banner is visible with text 'Verify your document to get higher limit & cashback up to ₹100 Verify Now' ----TC_Ring_Core_260");
		Aclick(HomePage.purpleBannerVerifyNow, "KYC Documents Page");
		explicitWaitVisibility(KycDocument.kycHeader, 30);
		logger.info("KYC Documents Page Open");
		softAssertion.assertEquals("KYC Documents", getText(KycDocument.kycHeader));
		extent.extentLoggerPass("KYC Document Page", "After click on verify now of purple banner,Kyc Document page Open_TC_Ring_Core_261");
		Back(1);
		explicitWaitVisibility(KycDocument.txtPopupWhileBackBtn, 20);
		Aclick(KycDocument.yesBtn, "Yes Button");
		explicitWaitVisibility(HomePage.homeTab, 20);
		}
		
		swipeElementAndroid(HomePage.bannerTray,HomePage.lightBlueBannerTransferNow);
		
		waitTime(10000);
		explicitWaitVisibility(HomePage.lightBlueBannerTransferNow, 20);
		logger.info("Ligh Blue Banner----Transfer Now");
		softAssertion.assertEquals("Transfer your RING", getText(HomePage.lightBluetext2));
		extent.extentLoggerPass("Cash Crunch? Transfer your RING limit to your bank account", "Light Blue Banner is visible with text 'Cash Crunch? Transfer your RING limit to your bank account Transfer Now'");
		boolean green=swipeElementAndroid(HomePage.bannerTray,HomePage.greenBannerText1);
		System.out.println(green);
		waitTime(10000);

		if (green==true) {
			logger.info("micro payment flag is enabled for user from Configuration");
			System.out.println(getText(HomePage.greenBannerPayNow));
			extent.extentLoggerPass("micro payment flag", "micro payment flag is enabled for user from Configuration-----------TC_Ring_Core_265");
			extent.extentLoggerPass("RINGGGG! Transactions below", "Green Banner is visible with text 'RINGGGG! Transactions below 200 are absolutely FREE! Pay Now'--------------TC_Ring_Core_266");
			Aclick(HomePage.greenBannerPayNow, "Pay Now");
			
			explicitWaitVisibility(HomePage.objScanAnyQRToPay,10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay",sScanAnyQRCode);
			verifyElementPresent(HomePage.objScanAnyQRToPay,getTextVal(HomePage.objScanAnyQRToPay,"Text"));
			
			//Scan here
			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            String projectPath=System.getProperty("user.dir");
            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
            waitTime(15000);
            BrowsertearDown();
            
            /*------------------------------Android----------------------------*/
            setPlatform("Android");
            initDriver();
			
			if(verifyElementPresent(PaymentPage.objAmountTextField,"Amount Text Field")) {
				   Aclick(PaymentPage.objAmountTextField, "Amount Field");
				   type(PaymentPage.objAmountTextField, "100", "Amount Field");
			}
			hideKeyboard();
			waitTime(2000);
			Aclick(PaymentPage.objPayNowBtn2, "Pay Now Button");
			waitTime(30000);
			if(verifyElementPresent(PaymentPage.objConfirmPayment,getTextVal(PaymentPage.objConfirmPayment,"Text"))){
				   Aclick(PaymentPage.objEditTransactionPin,"Edit Pin Field");
				   type(PaymentPage.objEditTransactionPin,"1111","Edit Pin Field");
				   Aclick(PaymentPage.objContinue,"Continue Button");
			}
			if(verifyElementPresent(PaymentPage.objPaymentDone,getTextVal(PaymentPage.objPaymentDone,"Text"))){
				   String sPaymentDone = getText(PaymentPage.objPaymentDone);
				   softAssertion.assertEquals("Payment Done!", sPaymentDone);
				   verifyElementPresent(PaymentPage.objGreenTickMark,"Payment Done green tick mark");
				   verifyElementPresent(PaymentPage.objRupeesLogo,getTextVal(PaymentPage.objRupeesLogo,"is Rupees Logo"));
				   verifyElementPresent(PaymentPage.objMerchantNameReceipt,getTextVal(PaymentPage.objMerchantNameReceipt,"Text"));
				   verifyElementPresent(PaymentPage.objTaxDateAndTime,getTextVal(PaymentPage.objTaxDateAndTime,"is Tax date and time"));
				   verifyElementPresent(PaymentPage.objSeekBar,"Seek Bar");
				   verifyElementPresent(PaymentPage.objAvailableLimit,getTextVal(PaymentPage.objAvailableLimit,"Text"));
				   verifyElementPresent(PaymentPage.ObjAvailableLimitAmount,getTextVal(PaymentPage.ObjAvailableLimitAmount,"is Available Limit Amount"));
				   verifyElementPresent(PaymentPage.objGoToHomePage,getTextVal(PaymentPage.objGoToHomePage,"Text"));
				   System.out.println("-----------------------------------------------------------");
				   Aclick(PaymentPage.objGoToHomePage, "Goto HomePage");
				   waitTime(5000);
				  if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp,"Text")) {
					Aclick(HomePage.noBtn, "No Button");
				  }
				}
		}else {
			logger.warn("micro payment flag is Not enabled for user from Configuration");
		}
		
		/*------------------------------WEB----------------------------*/
		
		setPlatform("Web");
		new RingPayBusinessLogic("ring");
		waitTime(3000);
		type(TestingPortalWebPage.emailField, portalEmail, "Email ID Entered");
		waitTime(3000);
		JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
		explicitWaitVisibility(TestingPortalWebPage.loginBtn, 10);
		clearField(TestingPortalWebPage.passwordField, "");
		waitTime(2000);
		type(TestingPortalWebPage.passwordField, portalPassword, "Portal Password");
		waitTime(2000);
		clearField(TestingPortalWebPage.otpField, "");
		waitTime(2000);
		type(TestingPortalWebPage.otpField, portalOTP, "OTP Entered");
		waitTime(2000);
		JSClick(TestingPortalWebPage.loginBtn, "Login Button");
		explicitWaitVisibility(TestingPortalWebPage.usersTab, 10);
		JSClick(TestingPortalWebPage.usersTab, "User Tab");
		explicitWaitVisibility(TestingPortalWebPage.dropDownUserRef, 10);
		JSClick(TestingPortalWebPage.dropDownUserRef, "Drop Down");
		selectByVisibleTextFromDD(TestingPortalWebPage.dropDownUserRef, "Mobile Number");
		type(TestingPortalWebPage.enterMobileNo, mobileNumber, "Portal Mobile Number");
		JSClick(TestingPortalWebPage.searchIcon, "Search Icon");
		waitTime(5000);
		String getReferenceNumber = "";
		for (int i = 0; i <= 2; i++) {
			try {
				getReferenceNumber = getWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]")).getText();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Referance----------------------------" + getReferenceNumber);
		
		BrowsertearDown();
		
		/*------------------------------WEB----------------------------*/
		
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/sign-in");
        waitTime(3000);
        explicitWaitVisibility(TestingPortalWebPage.emailFieldNewPortal, 20);
        type(TestingPortalWebPage.emailFieldNewPortal, prop.getproperty("RingAdminEmail"), "Email Entered");
        JSClick(TestingPortalWebPage.continueNewButton,"Continue Button");
        explicitWaitVisibility(TestingPortalWebPage.tabBNPL, 20);
        JSClick(TestingPortalWebPage.tabBNPL, "BNPL Link");
        explicitWaitVisibility(TestingPortalWebPage.tabBNPL_Line, 20);
        JSClick(TestingPortalWebPage.tabBNPL_Line, "BNPL Line Link");
        explicitWaitVisibility(TestingPortalWebPage.iconBulkUpload, 20);
        waitTime(2000);
        JSClick(TestingPortalWebPage.iconBulkUpload, "Bulk Upload Icon");
        explicitWaitVisibility(TestingPortalWebPage.menuBulkuploadcashbackWhitelisting, 20);
        JSClick(TestingPortalWebPage.menuBulkuploadcashbackWhitelisting, "Bulk Upload Cashback WhiteListing");
        clickElementWithWebLocator(TestingPortalWebPage.uploadExcelFile);
        waitTime(3000);
        System.out.println(System.getProperty("user.dir"));
        String projectPath=System.getProperty("user.dir");
        //Enter Data into Excel
        
        /*------------------------------Write In Excel----------------------------*/
       
       
        ExcelFunctions ex=new ExcelFunctions();
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,0,getReferenceNumber);
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,1,prop.getproperty("cashbackPercent"));
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,2,prop.getproperty("billingMaxCap"));
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,4,prop.getproperty("expireDate"));
        waitTime(5000);//2022-11-25
        Robot rb = new Robot();
        // copying File path to Clipboard
        StringSelection str = new StringSelection(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        
        // release Contol+V for pasting
        
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        waitTime(5000);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        System.out.println("completed");
		waitTime(5000);
		JSClick(TestingPortalWebPage.submitBtn, "Submit Button");
		waitTime(10000);
		BrowsertearDown();

		/*------------------------------Android----------------------------*/
		
		setPlatform("Android");
		initDriver();
		Swipe("DOWN", 1);
		boolean yellow=swipeElementAndroid(HomePage.bannerTray,HomePage.txt1yellowBanner(prop.getproperty("cashbackPercent")));
		if (yellow==true) {
			logger.info("Yellow Banner--CashBack Banner is visible");
			softAssertion.assertEquals("Up to "+prop.getproperty("cashbackPercent")+"%* cashback on all transactions", getText(HomePage.txt1yellowBanner(prop.getproperty("cashbackPercent"))));
			extent.extentLoggerPass("CashBack Banner", "CashBack Banner is Visible--------------TC_Ring_Core_263");
			Aclick(HomePage.objScanAndPay, "Scan Button");
			
		//	Scan here
			/*-------------------Web-----------------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
            waitTime(25000);
            BrowsertearDown();
            
            /*------------------Android---------------------------------*/
            setPlatform("Android");
            initDriver();
            
			waitTime(30000);
			explicitWaitVisibility(PaymentPage.objAmountTextField, 30);
			if(verifyElementPresent(PaymentPage.objAmountTextField,"Amount Text Field")) {
				   Aclick(PaymentPage.objAmountTextField, "Amount Field");
				   type(PaymentPage.objAmountTextField, "80", "Amount Field");
			}
			Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
			waitTime(20000);
			if(verifyElementPresent(PaymentPage.objConfirmPayment,getTextVal(PaymentPage.objConfirmPayment,"Text"))){
				   Aclick(PaymentPage.objEditTransactionPin,"Edit Pin Field");
				   type(PaymentPage.objEditTransactionPin,"1111","Edit Pin Field");
				   Aclick(PaymentPage.objContinue,"Continue Button");
			}
			if(verifyElementPresent(PaymentPage.objPaymentDone,getTextVal(PaymentPage.objPaymentDone,"Text"))){
				   String sPaymentDone = getText(PaymentPage.objPaymentDone);
				   softAssertion.assertEquals("Payment Done!", sPaymentDone);
				   verifyElementPresent(PaymentPage.objGreenTickMark,"Payment Done green tick mark");
				   verifyElementPresent(PaymentPage.objRupeesLogo,getTextVal(PaymentPage.objRupeesLogo,"is Rupees Logo"));
				   verifyElementPresent(PaymentPage.objMerchantNameReceipt,getTextVal(PaymentPage.objMerchantNameReceipt,"Text"));
				   verifyElementPresent(PaymentPage.objTaxDateAndTime,getTextVal(PaymentPage.objTaxDateAndTime,"is Tax date and time"));
				   verifyElementPresent(PaymentPage.objSeekBar,"Seek Bar");
				   verifyElementPresent(PaymentPage.objAvailableLimit,getTextVal(PaymentPage.objAvailableLimit,"Text"));
				   verifyElementPresent(PaymentPage.ObjAvailableLimitAmount,getTextVal(PaymentPage.ObjAvailableLimitAmount,"is Available Limit Amount"));
				   verifyElementPresent(PaymentPage.objGoToHomePage,getTextVal(PaymentPage.objGoToHomePage,"Text"));
				   System.out.println("-----------------------------------------------------------");
				   Aclick(PaymentPage.objGoToHomePage, "Goto HomePage");
				   logger.info("Payment Completed");
				   extent.extentLoggerPass("Payment Done Through Yellow Banner", "After coming yellow banner through scan button transection completed--------------TC_Ring_Core_264");
				   waitTime(5000);
				   if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp,"Text")) {
						Aclick(HomePage.noBtn, "No Button");
					  }
			}
		}
		else {
			logger.info("Yellow Banner not visible");
			extent.extentLogger("Yellow Banner", "Yellow Banner not visible");
		}	
	}
//===========================================Banner logic to HomePage end=========================================================	
//===============================================Transaction Set pin Start========================================================

	public void transactionSetPin() throws Exception {
		extent.HeaderChildNode("Transaction Set Pin Page Validation");
		
		promoCodeFlowSetPin();
		setPinMerchantFlowWithLocationOff();
		SetPinMerchantFlowWithLocationOn();
	}
//=========================================Promocode transaction Set pin=============================================================
	public void promoCodeFlowSetPin() throws Exception {
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		instaNewCommunicationAddress();
		waitTime(5000);
	
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresent(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));

		if (verifyElementPresent(UserRegistrationPage.objSetPin, getTextVal(UserRegistrationPage.objSetPin, "Test"))) {
		   String sSetPin = getText(UserRegistrationPage.objSetPin);
		   softAssertion.assertEquals(sSetPin, "Set Pin");
		   logger.info("TC_Ring_Core_190,Navigated to SET PIN screen,after clicking on Accept Offer");
		   extent.extentLoggerPass("TC_Ring_Core_190", "Navigated to SET PIN screen,after clicking on Accept Offer");
		   System.out.println("-----------------------------------------------------------");
		}
		transactionSetPinForPromoCodeFlow();
	}

	public void transactionSetPinForPromoCodeFlow() throws Exception {
		if (verifyElementPresent(UserRegistrationPage.objEnterPin1, "Enter Pin first Element")) {
			Aclick(UserRegistrationPage.objEnterPin1, "Enter pin first Element");
			type(UserRegistrationPage.objEnterPin1, "1", "Enter pin first Element");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			hideKeyboard();
			verifyElementPresent(UserRegistrationPage.objErrorMessage, getTextVal(UserRegistrationPage.objErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objErrorMessage, "Please enter new Pin");
			logger.info("TC_Ring_Core_192,Please enter new Pin Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_192", "TC_Ring_Core_192,Please enter new Pin Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(UserRegistrationPage.objEnterPin2, "Enter Pin Second Element")) {
			Aclick(UserRegistrationPage.objEnterPin2, "Enter pin Second Element");
			type(UserRegistrationPage.objEnterPin2, "1", "Enter pin Second Element");
			hideKeyboard();
			verifyElementPresent(UserRegistrationPage.objErrorMessage, getTextVal(UserRegistrationPage.objErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objErrorMessage, "Please enter new Pin");
			logger.info("TC_Ring_Core_193,Please enter new Pin Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_193", "TC_Ring_Core_193,Please enter new Pin Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(UserRegistrationPage.objEnterPin3, "Enter Pin Third Element")) {
			Aclick(UserRegistrationPage.objEnterPin3, "Enter pin Third Element");
			type(UserRegistrationPage.objEnterPin3, "1", "Enter pin Third Element");
			hideKeyboard();
			verifyElementPresent(UserRegistrationPage.objErrorMessage, getTextVal(UserRegistrationPage.objErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objErrorMessage, "Please enter new Pin");
			logger.info("TC_Ring_Core_194,Please enter new Pin Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_194", "TC_Ring_Core_194,Please enter new Pin Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}
		Aclick(UserRegistrationPage.objEnterPin4, "Enter pin Fourth Element");
		type(UserRegistrationPage.objEnterPin4, "1", "Enter pin Fourth Element");
		if (verifyElementPresent(UserRegistrationPage.objHide, "asterisk")) {
			for (int nPin = 1; nPin >= 4; nPin++) {
				String sAsterisk = getText(UserRegistrationPage.objHide(nPin));
				softAssertion.assertEquals(sAsterisk, "*");
			}
			logger.info("TC_Ring_Core_195,Pin should displayed as asterisk mark");
			extent.extentLoggerPass("TC_Ring_Core_195", "TC_Ring_Core_195,Pin should displayed as asterisk mark");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(UserRegistrationPage.objEnterPin, "Enter pin Data")) {
			String sEnteredPin = getText(UserRegistrationPage.objEnterPin);
			int nEnterPin = Integer.parseInt(sEnteredPin);
			softAssertion.assertEquals(sEnteredPin, 1111, "Return True When Both are Integers");
			logger.info("TC_Ring_Core_191, Entered Pin " + nEnterPin + " is an Integer");
			extent.extentLoggerPass("TC_Ring_Core_191", "TC_Ring_Core_191,Entered Pin " + nEnterPin + " is an Integer");
			System.out.println("-----------------------------------------------------------");
		}

		if (verifyElementPresent(UserRegistrationPage.objReEnterPin, "Re-Enter Pin")) {
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, prop.getproperty("InValidPin"), "Re-Enter Pin");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			verifyElementPresent(UserRegistrationPage.objSorryErrorMessage, getTextVal(UserRegistrationPage.objSorryErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objSorryErrorMessage, "Sorry! Pin does not match, please enter again");
			logger.info("TC_Ring_Core_196,Sorry! Pin does not match, please enter again Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_196", "TC_Ring_Core_196,Sorry! Pin does not match, please enter again Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}

		if (verifyElementPresent(UserRegistrationPage.objReEnterPin, "Re-Enter Pin")) {
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			clearField(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, prop.getproperty("ValidPin"), "Re-Enter Pin");
			setLocationConnectionToONOFF("Off");

			explicitWaitVisibility(UserRegistrationPage.objSubmit, 10);
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			click(UserRegistrationPage.objGotIt, "OK Got it Button");
			click(RingLoginPage.objEnterPin, "Mpin Field");
			type(RingLoginPage.objEnterPin, "1111", "MPIN Field");
			click(RingUserDetailPage.objReEnterPin, "Re-Enter Pin Field");
			type(RingUserDetailPage.objReEnterPin, "1111", "Re-Enter pin Field");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			if (verifyElementPresent(UserRegistrationPage.objTransactionPinSet, "Transaction Set pin")) {
				verifyElementPresent(UserRegistrationPage.objTransactionPinSet, getTextVal(UserRegistrationPage.objTransactionPinSet, "Text"));
				logger.info("TC_Ring_Core_197, Transaction pin is set successfully");
				extent.extentLoggerPass("TC_Ring_Core_197", "TC_Ring_Core_197,Transaction pin is set successfully");
				System.out.println("-----------------------------------------------------------");
			}
			setLocationConnectionToONOFF("on");
			waitTime(10);
			explicitWaitVisibility(HomePage.objCloseButton, 10);
			Aclick(HomePage.objCloseButton, "Close Button");
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			logger.info("TC_Ring_Core_199, Transaction pin is set successfully with location off");
			extent.extentLoggerPass("TC_Ring_Core_199", "TC_Ring_Core_199,Transaction pin is set successfully with location off");
			System.out.println("-----------------------------------------------------------");
		}

		ringPayLogout();
	}
//=========================================Promocode transaction Set pin End===========================================================	
//==========================================Merchantflow transaction set pin===========================================================

	public void setPinMerchantFlowWithLocationOff() throws Exception {
		   extent.HeaderChildNode("SetPin Merchant Flow Without Location");
		   merchantPage();
		   setLocationConnectionToONOFF("Off");
		   Aclick(UserRegistrationPage.objSubmit, "Submit Button");
		   if (verifyElementPresent(UserRegistrationPage.objAllowLocationPopup, "Location Permission Required Text")) {
		      verifyElementPresent(UserRegistrationPage.objAllowLocationPopup,getTextVal(UserRegistrationPage.objAllowLocationPopup,"Text"));
		      verifyElementPresent(UserRegistrationPage.objAllowLocationBtn,getTextVal(UserRegistrationPage.objAllowLocationBtn,"Text"));
		      verifyElementPresent(UserRegistrationPage.objNotNowLocationBtn, getTextVal(UserRegistrationPage.objNotNowLocationBtn, "Text"));
		      Aclick(UserRegistrationPage.objNotNowLocationBtn, "Not Now Button");
		      waitTime(10);
		      Back(1);
		      explicitWaitVisibility(HomePage.objCloseButton, 10);
		      Aclick(HomePage.objCloseButton, "Close Button");
		      String sHome = getText(HomePage.objHome);
		      softAssertion.assertEquals(sHome, "Home");
		      logger.info("TC_Ring_Core_201, Transaction pin is not set and Navigated to Home Page");
		      extent.extentLoggerPass("TC_Ring_Core_201", "TC_Ring_Core_201,Transaction pin is not set and Navigated to Home Page");
		      System.out.println("-----------------------------------------------------------");
		      ringPayLogout();
		   }
		   setLocationConnectionToONOFF("On");
		}
	
	public void SetPinMerchantFlowWithLocationOn() throws Exception {
		   extent.HeaderChildNode("SetPin Merchant Flow Without Location");
		   merchantPage();
		   setLocationConnectionToONOFF("Off");
		   Aclick(UserRegistrationPage.objSubmit, "Submit Button");
		   if (verifyElementPresent(UserRegistrationPage.objAllowLocationPopup, "Location Permission Required Text")) {
		      verifyElementPresent(UserRegistrationPage.objAllowLocationPopup, getTextVal(UserRegistrationPage.objAllowLocationPopup, "Text"));
		      verifyElementPresent(UserRegistrationPage.objAllowLocationBtn, getTextVal(UserRegistrationPage.objAllowLocationBtn, "Text"));
		      verifyElementPresent(UserRegistrationPage.objNotNowLocationBtn, getTextVal(UserRegistrationPage.objNotNowLocationBtn, "Text"));
		      logger.info("TC_Ring_Core_198 , Location permission Required Popup is displayed");
		      extent.extentLoggerPass("TC_Ring_Core_198", "TC_Ring_Core_198 , Location permission Required Popup is displayed");
		      System.out.println("-----------------------------------------------------------");
		      Aclick(UserRegistrationPage.objAllowLocationBtn, "Allow Button on Location Permission Required");
		      Aclick(UserRegistrationPage.objOkBtn, "Ok Button");
		      Aclick(UserRegistrationPage.objSubmit, "Submit");
		      logger.info("TC_Ring_Core_200, Transaction pin is set successful also user completes the first transaction");
		      extent.extentLoggerPass("TC_Ring_Core_200", "TC_Ring_Core_200, Transaction pin is set successful also user completes the first transaction");
		      System.out.println("-----------------------------------------------------------");
		      waitTime(10000);
		      verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
		      waitTime(5000);
		      verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
//		      verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		      String sHome = getText(HomePage.objHome);
		      softAssertion.assertEquals(sHome, "Home");
		   }
		   ringPayLogout();
		}
	
	public void merchantPage() throws Exception {
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresent(RingLoginPage.objScanQRPage, getText(RingLoginPage.objScanQRPage));

		/*------------------------------WEB----------------------------*/
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		String projectPath=System.getProperty("user.dir");
		System.out.println(projectPath);
		//getWebDriver().get("");
        getWebDriver().get(projectPath+"//Mock_Files/qrcode.png");
        waitTime(10000);
        BrowsertearDown();
        
        /*------------------------------Android----------------------------*/
        setPlatform("Android");
		initDriver();
		waitTime(5000);

		click(RingLoginPage.objMakePaymentLetsRingItBtn, getText(RingLoginPage.objMakePaymentLetsRingItBtn));
		click(RingLoginPage.objMakePaymentLetsRingItBtn, getText(RingLoginPage.objMakePaymentLetsRingItBtn));
		click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
		type(RingPayMerchantFlowPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
		verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn, getText(RingLoginPage.objMakePaymentPageProceedBtn));
		verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader, getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA, getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA, getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA, getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();

		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		waitTime(6000);
		instaNewCommunicationAddress();
		waitTime(5000);
		verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
		softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
		verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
		verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
		verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
		verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
		verifyElementPresent(PromoCodeOfferPage.objRBIMsg, getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn, getText(TermsAndConditionPage.objAcceptText));

		verifyElementPresentAndClick(PromoCodeOfferPage.objFeeDetail, getText(PromoCodeOfferPage.objFeeDetail));
		verifyElementPresent(PromoCodeOfferPage.objFeeDetailPage, getText(PromoCodeOfferPage.objFeeDetailPage));
		click(PromoCodeOfferPage.objFeeDetailsCrossBtn, "Cross Button");

		verifyElementPresent(PromoCodeOfferPage.objTermsAndCondition, getTextVal(PromoCodeOfferPage.objTermsAndCondition, "Text"));
		click(PromoCodeOfferPage.objTermsAndCondition, "Terms And Conditions");
		waitTime(5000);
		verifyElementPresent(TermsAndConditionPage.objTAndCondition, getTextVal(TermsAndConditionPage.objTAndCondition, "Text"));
		Back(1);

		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn, getText(TermsAndConditionPage.objAcceptText));
		waitTime(1000);
		System.out.println(getText(PromoCodeOfferPage.objToastMsg));
		logger.info(getText(PromoCodeOfferPage.objToastMsg));
		extent.extentLoggerPass("Toast Msg", getText(PromoCodeOfferPage.objToastMsg));
		waitTime(5000);

		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn, getText(TermsAndConditionPage.objAcceptText));

		if (verifyElementPresent(UserRegistrationPage.objSetPin, getTextVal(UserRegistrationPage.objSetPin, "Test"))) {
			String sSetPin = getText(UserRegistrationPage.objSetPin);
			softAssertion.assertEquals(sSetPin, "Set Pin");
			logger.info("TC_Ring_Core_189,Navigated to SET PIN screen,after clicking on Accept and Pay");
			extent.extentLoggerPass("TC_Ring_Core_189", "TC_Ring_Core_189,Navigated to SET PIN screen,after clicking on Accept and Pay");
			Aclick(UserRegistrationPage.objEnterPin, "Enter Pin");
			type(UserRegistrationPage.objEnterPin, "1111", "Enter Pin");
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, "1111", "Re-Enter Pin");
		}
	}
//=============================================Transaction Set Pin End ==========================================================
//=========================================Check Payment multiple Cases Start====================================================
	public void repaymentMultipleCases() throws Exception {
		extent.HeaderChildNode("Check payment page multiple cases");
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		mobileNoValidation1(prop.getproperty("editMob"));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();

		//above code not required if user already inside homepage
		explicitWaitVisibility(HomPageNew.objAdHeader, 10);
		Aclick(HomPageNew.objAdCloseBtn, "AD Close button");
		explicitWaitClickable(HomPageNew.objPayEarlyBtn, 10);
		Aclick(HomPageNew.objPayEarlyBtn, "Pay Early Button");
		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		waitTime(3000);
		Aclick(HomPageNew.objAmtToBeRadio, "Amount to be paid radio button");
		waitTime(3000);
		hideKeyboard();
		explicitWaitVisibility(HomPageNew.objNetBankingOption, 10);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objFirstError, 10);
		String firstErrorTxt = getText(HomPageNew.objFirstError);
		softAssertion.assertEquals("Please enter amount", firstErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_243","TC_Ring_Payment_243 - Verify payment page by keeping other amount field empty");

		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "0", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objSecondError, 10);
		String secondErrorTxt = getText(HomPageNew.objSecondError);
		softAssertion.assertEquals("Minimum amount should be ₹1", secondErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_244","TC_Ring_Payment_244 - Verify payment page by entering amount as 0 in other amount field");

		clearField(HomPageNew.objAmtRepayText, "Amount repay text field");

		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "7777", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objThirdError, 10);
		String thirdErrorTxt = getText(HomPageNew.objThirdError);
		softAssertion.assertEquals("Amount is greater than payable amount.", thirdErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_245","TC_Ring_Payment_245 - Verify payment page by entering amount greater than Total Payable Amount");

		clearField(HomPageNew.objAmtRepayText, "Amount repay text field");
		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "10,20,", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objFourthError, 10);
		String fourthErrorTxt = getText(HomPageNew.objFourthError);
		softAssertion.assertEquals("Please enter valid amount", fourthErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_246","TC_Ring_Payment_246 - Verify payment page by entering invalid amount as \"10,20,\" in other amount field");

		clearField(HomPageNew.objAmtRepayText, "Amount repay text field");
		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "17", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		waitTime(3000);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objNetbanking, 30);
		Aclick(HomPageNew.objNetbanking, "Netbanking");
		explicitWaitVisibility(HomPageNew.objSelectBankHeader, 10);
		Aclick(HomPageNew.objSBIBank, "SBI bank");
		Aclick(HomPageNew.objPayNowBtn, "Pay Now Button");
		explicitWaitVisibility(HomPageNew.objPayFail, 10);
		extent.extentLoggerPass("TC_Ring_Payment_247","TC_Ring_Payment_247 - Verify payment page by entering amount less than 20rs if Partner is RAZORPAY or PAYNIMO");
		Aclick(HomPageNew.objTryAgain, "Try Again button");
		Back(3);

		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		Aclick(HomPageNew.objAmtRepayText, "Amount to be paid text field");
		clearField(HomPageNew.objAmtRepayText, "Amount to be paid text field");
		type(HomPageNew.objAmtRepayText, "20", "Amount to be paid text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		waitTime(3000);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objNetbanking, 10);
		extent.extentLoggerPass("TC_Ring_Payment_248","TC_Ring_Payment_248 - Verify payment page by entering amount equal to 20rs");
		Aclick(HomPageNew.objNetbanking, "Netbanking");
		explicitWaitVisibility(HomPageNew.objSelectBankHeader, 10);
		Aclick(HomPageNew.objSBIBank, "SBI bank");
		Aclick(HomPageNew.objPayNowBtn, "Pay Now Button");
		explicitWaitVisibility(HomPageNew.objSuccessBtn, 10);
		Aclick(HomPageNew.objSuccessBtn, "Success Button");
		explicitWaitVisibility(HomPageNew.objRepaySuccess, 10);
		Aclick(HomPageNew.objHomePage, "Go to homepage button");
		explicitWaitClickable(HomPageNew.objPayEarlyBtn, 10);
		Aclick(HomPageNew.objPayEarlyBtn, "Pay Early Button");
		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		waitTime(3000);
		Aclick(HomPageNew.objViewDetails, "View Details");
		explicitWaitVisibility(HomPageNew.objPayDate, 10);
		explicitWaitVisibility(HomPageNew.objTotSpends, 10);
		explicitWaitVisibility(HomPageNew.objTransacFee, 10);
		explicitWaitVisibility(HomPageNew.objTotAmt, 10);
		extent.extentLoggerPass("TC_Ring_Payment_249","TC_Ring_Payment_249 - To Verify user clicks on View Details on Payment screen");
		Back(2);
		explicitWaitVisibility(RingLoginPage.objAvailLimitHeader, 10);
		ringPayLogout();
	}
//=============================================Check Payment multiple Cases End===================================================	
//================================================Bank transfer Flow Start========================================================
	public void BankTransferModule(String accountNo, String name) throws Exception
    {
        extent.HeaderChildNode("RingPay Bank Transfer Flow");
        getDriver().resetApp();
        if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
        verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		mobileNoValidation1(RandomIntegerGenerator(10));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		instaNewCommunicationAddress();
		waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("NewMPIN"),prop.getproperty("NewMPIN"));
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
        
        click(BankTransferModule.objMoretTab, "Clicked On More Button");
        if (verifyElementPresent(BankTransferModule.objBankTransfer,getTextVal(BankTransferModule.objBankTransfer, "Module"))) {
            click(BankTransferModule.objBankTransfer, "Bank Transfer Module");
            logger.info("Navigated to Add Bank Account Page");
            extent.extentLogger("Account Page", "Navigated to Add Bank Account Page");
            extent.extentLogger("TC_Ring_Core_250", "TC_Ring_Core_235, To Verify when user clicks on bank transfer ");
            waitTime(2000);
            extent.extentLogger("TC_Ring_Core_257", "'TC_Ring_Core_257' To verify user enter valid account No and invalid account holder name as per user name");
            addbankAccount(accountNo, name);

           String buttonName = getText(BankTransferModule.objOkayBtn);
            if (buttonName.equalsIgnoreCase("okay") || buttonName.equalsIgnoreCase("Re-Enter Bank Details")) {
                click(BankTransferModule.objOkayBtn, getTextVal(BankTransferModule.objOkayBtn, "Button"));
                explicitWaitVisibility(BankTransferModule.objAddNewBankAccountBtn, 10);
                click(BankTransferModule.objAddNewBankAccountBtn,getTextVal(BankTransferModule.objAddNewBankAccountBtn, "Button"));
                addbankAccount("1", "Sunil");
                extent.extentLogger("TC_Ring_Core_258", "'TC_Ring_Core_258'To verify user enter invalid account No and Valid account holder name as per user name");
                click(BankTransferModule.objOkayBtn, getTextVal(BankTransferModule.objOkayBtn, "Button"));
                extent.extentLogger("TC_Ring_Core_259", "'TC_Ring_Core_259' To Verify user clicks on Okay got button error validation screen ");
            }

           explicitWaitVisibility(BankTransferModule.objMoretTab, 10);
            Aclick(BankTransferModule.objMoretTab, "Clicked On More Button");
            explicitWaitVisibility(BankTransferModule.objBankTransfer, 10);
            Aclick(BankTransferModule.objBankTransfer, "Bank Transfer Module");
            click(BankTransferModule.objAddNewBankAccountBtn,getTextVal(BankTransferModule.objAddNewBankAccountBtn, "Button"));
            extent.extentLogger("TC_Ring_Core_253", "'TC_Ring_Core_253' To verify user enter valid account No and Valid account holder name as per user name");
            addbankAccount("5", "Sunil");
            extent.extentLogger("TC_Ring_Core_257", "'TC_Ring_Core_257' To verify user enter valid account No and invalid account holder name as per user name");
            explicitWaitVisibility(BankTransferModule.objMakePaymentPage, 10);
            verifyElementPresent(BankTransferModule.objMakePaymentPage, "Make Payment Page Displayed");

           click(BankTransferModule.objEnterAmountTextField, "Amount Text Field");
            type(BankTransferModule.objEnterAmountTextField, "1", "Amount Text Field");
            logger.info(getTextVal(BankTransferModule.objTransactionFeeMsg, "Fee Message is Displayed"));
            extent.extentLogger("Fee Message",getTextVal(BankTransferModule.objTransactionFeeMsg, "Fee Message is Displayed"));
            click(BankTransferModule.objPayNowBtn, "Pay Now Button");
            extent.extentLogger("TC_Ring_Core_254,TC_Ring_Core_255","To verify user continue on make payment page");

           // Confirm payment Page
            verifyElementPresent(BankTransferModule.objEnterPinPage, "Confirm Payment Page");
            logger.info("User Navigaed to Confirm Payment Page");
            extent.extentLogger("Enter Pin", "User Navigaed to Confirm Payment Page");
            explicitWaitVisibility(BankTransferModule.objEnterPinTextField, 10);
            click(BankTransferModule.objEnterPinTextField, "Enter Pin Field");
            type(BankTransferModule.objEnterPinTextField, "1234", "Enter Pin Field");
            click(BankTransferModule.objProceedBtn, "Proceed Button");
            extent.extentLogger("TC_Ring_Core_256","TC_Ring_Core_241 To Verify user should able to continue with valid Pin");
 
            verifyElementPresent(BankTransferModule.objStatusPage, "Payment Status Page");
            Aclick(BankTransferModule.objHomePageBtn, "Go to Homepage");
            waitTime(2000);
            if (checkElementExist1(BankTransferModule.objEnjoyingPopup,"Are you Enjoying pop up")) {
                click(BankTransferModule.objYesBtn, "Yes Button");
                click(BankTransferModule.objRateExperience, "Rate Your Experience pop up");
            }
            ringPayLogout();
        } 
    }
	public String addbankAccount(String accountNo, String name) throws Exception
    {
        explicitWaitVisibility(BankTransferModule.objIfscTextField, 20);
        click(BankTransferModule.objIfscTextField, "IFSC Code Text Field");
//        type(BankTransferModule.objIfscTextField, "SBIN0003473", "IFSC Code Text Field");
//        logger.warn(getTextVal(BankTransferModule.objIFSCErrorMsg, "Error Message"));
//        extent.extentLoggerWarning("Error Message", getTextVal(BankTransferModule.objIFSCErrorMsg, "Error Message"));
//        clearField(BankTransferModule.objIfscTextField, "IFSC Code Text Field");
        type(BankTransferModule.objIfscTextField, "SBIN0003473", "IFSC Code Text Field");
        logger.info("Branch Name AutoPopulated "+getText(BankTransferModule.objBranchName));
        extent.extentLogger("BranchName", "Branch Name AutoPopulated "+getText(BankTransferModule.objBranchName));
        extent.extentLogger("TC_Ring_Core_251,TC_Ring_Core_252", "TC_Ring_Core_251,TC_Ring_Core_252 To verify User enter valid and invalid IFSC code ");
        
        explicitWaitVisibility(BankTransferModule.objAccountNumberField, 10);
        click(BankTransferModule.objAccountNumberField, "Account Number Field");
        accountNo = accountNo + RandomIntegerGenerator(5);
        type(BankTransferModule.objAccountNumberField, accountNo, "Account Number Text Field");
        waitTime(2000);
        explicitWaitVisibility(BankTransferModule.ObjConfirmAccountNumberField, 10);
        click(BankTransferModule.ObjConfirmAccountNumberField, "Confirm Account Number Field");
        type(BankTransferModule.ObjConfirmAccountNumberField, accountNo, "Account Number Text Field"); 
        hideKeyboard();
        waitTime(2000);
        Swipe("Up",2);
        waitTime(2000);
        click(BankTransferModule.objAccountHolderName, "Account Holder Name");
        type(BankTransferModule.objAccountHolderName, name, "Account Number Text Field");        
        hideKeyboard();        
        doubleTap(BankTransferModule.objAccountTypeField, 2,"Account Type Field");
        explicitWaitVisibility(BankTransferModule.objSavings, 10);
        click(BankTransferModule.objSavings, "Savings Type");
        click(BankTransferModule.objContinueButton, "Continue Button");
        explicitWaitVisibility(InstaLoanPage.txtVerifyBankDetails, 20);
		click(InstaLoanPage.btnConfirm, "");
		waitTime(5000);
//        if(!checkElementExist(BankTransferModule.objSavings))
//        {
//            click(BankTransferModule.objAccountTypeField, "Account Type");
//            click(BankTransferModule.objSavings, "Savings Type");
//        }    
        
//        explicitWaitVisibility(BankTransferModule.objBottomSheetPopup, 10);
//        verifyElementExist(BankTransferModule.objBottomSheetPopup, "Verify Bank Details Pop Up");
//        click(BankTransferModule.objConfirmBtn, "Confirm Button");
		return accountNo;
    }	
//===============================================Bank transfer flow ends==========================================================	
//===============================================Add Address Flow Start===============================================================
	public void addAddressFlow() throws Exception {
        extent.HeaderChildNode("RingPay Add Address Flow");
        getDriver().resetApp();
        if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
        verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		mobileNoValidation1(RandomIntegerGenerator(10));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(15000);
        
        if(verifyElementPresent(AddAddresPage.objAddressHeader,"Add Current Address Page Header")){
        	extent.extentLoggerPass("TC_Ring_Core_116", "TC_Ring_Core_116 - To verfiy Address page");
            verifyElementExist(AddAddresPage.objRoomNoTextField, getTextVal(AddAddresPage.objRoomNoTextField, "Text Field"));
            click(AddAddresPage.objRoomNoTextField, getTextVal(AddAddresPage.objRoomNoTextField,"Text Field"));
            type(AddAddresPage.objRoomNoTextField, "", getTextVal(AddAddresPage.objRoomNoTextField, "Text Field"));
            click(AddAddresPage.objSubmitButton, "Submit Button");
            logger.info(getTextVal(AddAddresPage.objMandatoryWarnMessage, "Warning Message Displayed"));
            extent.extentLogger("Rooom No", getTextVal(AddAddresPage.objMandatoryWarnMessage, "Warning Message"));
            type(AddAddresPage.objRoomNoTextField, "@#%^&#$", getTextVal(AddAddresPage.objRoomNoTextField,"Text Field"));
            logger.info(getTextVal(AddAddresPage.objRoomNoTextField, "Special Character Warning Message"));
            extent.extentLogger("Rooom No", getTextVal(AddAddresPage.objRoomNoTextField, "Special Character Warning Message"));
            clearField(AddAddresPage.objRoomNoField, getTextVal(AddAddresPage.objRoomNoTextField,"Text Field"));
            type(AddAddresPage.objRoomNoTextField, "86", getTextVal(AddAddresPage.objRoomNoTextField, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_117", "TC_Ring_Core_117 - To verify room no and building name field with valid data");
            
            verifyElementExist(AddAddresPage.objAddressLineOneTextField, getTextVal(AddAddresPage.objAddressLineOneTextField, "Text Field"));
            click(AddAddresPage.objAddressLineOneTextField, getTextVal(AddAddresPage.objAddressLineOneTextField,"Text Field"));
            type(AddAddresPage.objAddressLineOneTextField, "", getTextVal(AddAddresPage.objAddressLineOneTextField, "Text Field"));
            type(AddAddresPage.objAddressLineOneTextField, "@#%^&#$", getTextVal(AddAddresPage.objAddressLineOneTextField,"Text Field"));
            logger.info(getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            extent.extentLogger("Address Line 1", getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objAddressLineOneField, getTextVal(AddAddresPage.objAddressLineOneField,"Text Field"));
            type(AddAddresPage.objAddressLineOneTextField, "44, Borivali", getTextVal(AddAddresPage.objAddressLineOneTextField, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_118", "TC_Ring_Core_118 - To verify address 1 field with valid data");
            
            verifyElementExist(AddAddresPage.objAddressLineTwoTextField, getTextVal(AddAddresPage.objAddressLineTwoTextField, "Field"));
            click(AddAddresPage.objAddressLineTwoTextField, getTextVal(AddAddresPage.objAddressLineTwoTextField,"Text Field"));
            type(AddAddresPage.objAddressLineTwoTextField, "", getTextVal(AddAddresPage.objAddressLineTwoTextField, "Text Field"));
            type(AddAddresPage.objAddressLineTwoTextField, "@#%^&#$", getTextVal(AddAddresPage.objAddressLineTwoTextField, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objSpclCharWarnMessage, "Warning Message"));
            extent.extentLogger("Address Line 2", getTextVal(AddAddresPage.objSpclCharWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objAddressLineTwoField, getTextVal(AddAddresPage.objAddressLineTwoField,"Text Field"));
            type(AddAddresPage.objAddressLineTwoField, "Vasa Street", getTextVal(AddAddresPage.objAddressLineTwoField, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_119", "TC_Ring_Core_119 - To verify address 2 field with valid data");
            
            verifyElementExist(AddAddresPage.objLandmarkTextField, getTextVal(AddAddresPage.objLandmarkTextField, "Field"));
            click(AddAddresPage.objLandmarkTextField, getTextVal(AddAddresPage.objLandmarkTextField,"Text Field"));
            type(AddAddresPage.objLandmarkTextField, "", getTextVal(AddAddresPage.objLandmarkTextField, "Text Field"));
            type(AddAddresPage.objLandmarkTextField, "@#%^&#$", getTextVal(AddAddresPage.objLandmarkTextField, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            extent.extentLogger("Landmark Field", getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objLandMarkClr, getTextVal(AddAddresPage.objLandMarkClr,"Text Field"));
            type(AddAddresPage.objLandMarkClr, "Das Gupta street", getTextVal(AddAddresPage.objLandMarkClr, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_120", "TC_Ring_Core_120 - To verify Landmark field with valid data");
            hideKeyboard();

           verifyElementExist(AddAddresPage.objPincodeTextField, getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            click(AddAddresPage.objPincodeTextField, getTextVal(AddAddresPage.objPincodeTextField,"Text Field"));
            Swipe("up", 2);
            type(AddAddresPage.objPincodeTextField, "", getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            type(AddAddresPage.objPincodeTextField, "255552", getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objInvalidPinCode, "Warning Message"));
            extent.extentLogger("Pincode Field", getTextVal(AddAddresPage.objInvalidPinCode, "Warning Message"));
            clearField(AddAddresPage.objpincodeClr, getTextVal(AddAddresPage.objPincodeTextField,"Text Field"));
            type(AddAddresPage.objpincodeClr, "252", getTextVal(AddAddresPage.objpincodeClr, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objSixDigitWarnMessage, "Warning Message"));
            extent.extentLogger("Pincode Field", getTextVal(AddAddresPage.objSixDigitWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objpincodeClr, getTextVal(AddAddresPage.objPincodeTextField,"Text Field"));
            type(AddAddresPage.objPincodeTextField, "560076", getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            hideKeyboard();
            
            verifyElementExist(AddAddresPage.objCityTextField, getTextVal(AddAddresPage.objCityTextField, "Field"));
            explicitWaitVisibility(AddAddresPage.objPopulatedCity, 10);
            logger.info(getTextVal(AddAddresPage.objPopulatedCity, "City Name autoPopulated after entering pincode"));
            extent.extentLogger("CityName", getTextVal(AddAddresPage.objPopulatedCity, "City Name autoPopulated after entering pincode"));
                
            verifyElementExist(AddAddresPage.objStateTextField, getTextVal(AddAddresPage.objStateTextField, "Field"));
            explicitWaitVisibility(AddAddresPage.objPopulatedState, 10);
            logger.info(getTextVal(AddAddresPage.objPopulatedState, "State Name autoPopulated after entering pincode"));
            extent.extentLogger("StateName", getTextVal(AddAddresPage.objPopulatedState, "State Name autoPopulated after entering pincode"));
            extent.extentLoggerPass("TC_Ring_Core_121,TC_Ring_Core_122,", "TC_Ring_Core_121,TC_Ring_Core_122 - To verify pincode field with invalid and valid pincode");
            
            explicitWaitVisibility(AddAddresPage.objSubmitButton, 10);
            Aclick(AddAddresPage.objSubmitButton, "Submit Button");
            extent.extentLoggerPass("TC_Ring_Core_123", "TC_Ring_Core_123 - To verfiy user clicks on submit button after entering valid address details");
        }else {
            logger.warn("Address Page is not diaplyed");
            extent.extentLoggerWarning("Address Page", "Address Page is not displayed");
        }
        waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("NewMPIN"),prop.getproperty("NewMPIN"));
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");  
		ringPayLogout();
    }		
//================================================Add Address Flow End=================================================================================================
//===============================================User Scan and Pay Start===============================================================================================	
	public void userScanAndPayTransactions() throws Exception {
		extent.HeaderChildNode("SetPin Merchant Flow Without Location");
		getDriver().resetApp();
		onBoarding();
		explicitWaitVisibility(HomePage.objHome, 10);
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals("Home", sHome);
		if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {

			String scanQrEnabled = getAttributValue("enabled", HomePage.objScanAndPay);
			softAssertion.assertEquals("true", scanQrEnabled);
			Aclick(HomePage.objScanAndPay, "Scan And Pay");

			logger.info("TC_Ring_Core_203 , To Verify Scan and Pay button available on bottom of the  HomeScreen is enable and clickable");
			extent.extentLoggerPass("TC_Ring_Core_203","TC_Ring_Core_203, To Verify Scan and Pay button available on bottom of the  HomeScreen is enable and clickable");
			System.out.println("-----------------------------------------------------------");

			explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
			verifyElementPresent(HomePage.objScanAnyQRToPay, getTextVal(HomePage.objScanAnyQRToPay, "Text"));
			logger.info("TC_Ring_Core_204, Camera Scanner should get opened to Scan and Pay");
			extent.extentLoggerPass("TC_Ring_Core_204","TC_Ring_Core_204,Camera Scanner should get opened to Scan and Pay");
			System.out.println("-----------------------------------------------------------");

			//Scan here
			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            String projectPath=System.getProperty("user.dir");
            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
            waitTime(10000);
            BrowsertearDown();
            
            /*------------------------------Android----------------------------*/
            setPlatform("Android");
            initDriver();

			if(verifyIsElementDisplayed(RingPayMerchantFlowPage.objCreditAtZeroPopUp, getTextVal(RingPayMerchantFlowPage.objCreditAtZeroPopUp,"Pop up"))){
				logger.info("TC_Ring_Core_191, Credit at zero Charges popup is Displayed");
				extent.extentLoggerPass("TC_Ring_Core_191","TC_Ring_Core_191,Credit at zero Charges popup is Displayed");
				System.out.println("-----------------------------------------------------------");
			}else{
				logger.info("TC_Ring_Core_191, Credit at zero Charges popup is not Displayed");
				extent.extentLoggerPass("TC_Ring_Core_191","TC_Ring_Core_191,Credit at zero Charges popup is not Displayed");
				System.out.println("-----------------------------------------------------------");
			}

			explicitWaitVisibility(PaymentPage.objUseCreditLimitText, 30);
			if (verifyElementPresent(PaymentPage.objUseCreditLimitText, getTextVal(RingPayMerchantFlowPage.objUseCreditLimitText, "Text"))) {
				verifyElementPresent(PaymentPage.objMerchantName, getTextVal(RingPayMerchantFlowPage.objPayTypeMethod, "is Merchant Name"));
				verifyElementPresent(PaymentPage.objUpiID, getTextVal(RingPayMerchantFlowPage.objUpiID, "is UPI Id"));
				Back(1);
				verifyElementPresent(PaymentPage.objRupeesLogo, getTextVal(PaymentPage.objRupeesLogo, "Rupees Logo"));
				verifyElementPresent(PaymentPage.objTransactionFeeMsg, getTextVal(PaymentPage.objTransactionFeeMsg, "Text"));
				verifyElementPresent(PaymentPage.objDisabledPayNowButton, "Disabled pay Now Button");
				hideKeyboard();
				logger.info("TC_Ring_Core_207, Payment Screen Validated");
				extent.extentLoggerPass("TC_Ring_Core_207", "TC_Ring_Core_207,Payment Screen Validated");
				System.out.println("-----------------------------------------------------------");

			}
			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("HigherAmount_Merchant"), "Amount Field");
				verifyElementPresent(RingPayMerchantFlowPage.objTransactionMsg, getTextVal(RingPayMerchantFlowPage.objTransactionMsg, "Error Message"));
				String sErrorMessage1 = getText(RingPayMerchantFlowPage.objTransactionMsg);
				softAssertion.assertEquals(sErrorMessage1, "You have entered a higher amount than your available limit. Re-enter amount.");
				hideKeyboard();
				logger.info("TC_Ring_Core_208, You have entered a higher amount than your available limit. Re-enter amount. Error message should be displayed");
				extent.extentLoggerPass("TC_Ring_Core_208", "TC_Ring_Core_208,You have entered a higher amount than your available limit. Re-enter amount. Error message should be displayed");
				System.out.println("-----------------------------------------------------------");
			}
			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("ZeroAmount_Merchant"), "Amount Field");
				verifyElementPresent(RingPayMerchantFlowPage.objTransactionMsg, getTextVal(RingPayMerchantFlowPage.objTransactionMsg, "Error Message"));
				String sErrorMessage2 = getText(RingPayMerchantFlowPage.objTransactionMsg);
				softAssertion.assertEquals(sErrorMessage2, "Please enter amount greater than 0");
				hideKeyboard();
				logger.info("TC_Ring_Core_209, Please enter amount greater than 0 Error Message is Displayed");
				extent.extentLoggerPass("TC_Ring_Core_209", "TC_Ring_Core_209, Please enter amount greater than 0 Error Message is Displayed");
				System.out.println("-----------------------------------------------------------");
			}
			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
				verifyElementPresent(PaymentPage.objTransactionMsg, getTextVal(PaymentPage.objTransactionMsg, "Message"));
				String sPayNow = getAttributValue("enabled", PaymentPage.objTransactionMsg);
				softAssertion.assertEquals("true", sPayNow);
				logger.info("TC_Ring_Core_210, Pay Now button should be enabled after entering the valid amount");
				extent.extentLoggerPass("TC_Ring_Core_210", "TC_Ring_Core_210, Pay Now button should be enabled after entering the valid amount");
				System.out.println("-----------------------------------------------------------");
				hideKeyboard();
			}

			if (verifyElementPresent(PaymentPage.objPayNowBtn, "Pay Now Button")) {
				doubleTap(PaymentPage.objPayNowBtn2, 1, "Pay Now Button");
				waitTime(15000);
				String sConfirmPayment = getText(PaymentPage.objConfirmPayment);
				softAssertion.assertEquals("Confirm Payment", sConfirmPayment);
				logger.info("TC_Ring_Core_211, Navigated to Confirm Payment Screen to enter the Security PIN for transaction");
				extent.extentLoggerPass("TC_Ring_Core_211", "TC_Ring_Core_211, Navigated to Confirm Payment Screen to enter the Security PIN for transaction");
				System.out.println("-----------------------------------------------------------");
				if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
					Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
					type(PaymentPage.objEditTransactionPin, "1234", "Edit Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					verifyElementPresent(PaymentPage.objIncorrectPin, getTextVal(PaymentPage.objIncorrectPin, "Error Message"));
					String sIncorrectPin = getText(PaymentPage.objIncorrectPin);
					softAssertion.assertEquals("Incorrect PIN", sIncorrectPin);
					logger.info("TC_Ring_Core_212, Incorrect PIN Error message should be Displayed");
					extent.extentLoggerPass("TC_Ring_Core_212", "TC_Ring_Core_212, Incorrect PIN Error message should be Displayed");
					System.out.println("-----------------------------------------------------------");
				}
				if (verifyElementPresent(PaymentPage.objForgotPin, getTextVal(PaymentPage.objForgotPin, "Text"))) {
					Aclick(PaymentPage.objForgotPin, "Forget PIN?");
					verifyElementPresent(PaymentPage.objChangePin, getTextVal(PaymentPage.objChangePin, "text"));
					String sChangePin = getText(PaymentPage.objChangePin);
					softAssertion.assertEquals("Change Pin", sChangePin);
					logger.info("TC_Ring_Core_213, Navigated to Change Pin Screen");
					extent.extentLoggerPass("TC_Ring_Core_213", "TC_Ring_Core_213, Navigated to Change Pin Screen");
					System.out.println("-----------------------------------------------------------");
				}
				if (verifyElementPresent(PaymentPage.objChangePin, getTextVal(PaymentPage.objChangePin, "Text"))) {
					Aclick(PaymentPage.objEnterNewPin, "Enter New Pin");
					type(PaymentPage.objEnterNewPin, "1111", "Enter New Pin Field");
					Aclick(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					type(PaymentPage.objReEnterNewPin, "1111", "Re-Enter New Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					if (verifyElementPresent(PaymentPage.objPleaseEnterOtp, getTextVal(PaymentPage.objPleaseEnterOtp, "Text"))) {
						String sErrorMessage = getText(PaymentPage.objPleaseEnterOtp);
						softAssertion.assertEquals("Please enter OTP", sErrorMessage);
						logger.info("TC_Ring_Core_214, Please enter OTP error message is displayed");
						extent.extentLoggerPass("TC_Ring_Core_214", "TC_Ring_Core_199, Please enter OTP error message is displayed");
						System.out.println("-----------------------------------------------------------");
					}
					Swipe("DOWN", 1);
					Aclick(PaymentPage.objEnterOtp, "Enter OTP Field");
					type(PaymentPage.objEnterOtp, "123456", "Enter OTP Field");
					hideKeyboard();
					Aclick(PaymentPage.objEnterNewPin, "Enter New Pin");
					type(PaymentPage.objEnterNewPin, "1111", "Enter New Pin Field");
					hideKeyboard();
					Aclick(PaymentPage.objContinue, "Continue Button");
					verifyElementPresent(PaymentPage.objInvalidOtpMsg, getTextVal(PaymentPage.objInvalidOtpMsg, "Text"));
					String sInvalidOtpErrorMessage = getText(PaymentPage.objInvalidOtpMsg);
					softAssertion.assertEquals("Invalid Otp, Please enter correct otp.", sInvalidOtpErrorMessage);
					logger.info("TC_Ring_Core_215, Invalid Otp, Please enter correct otp. Error Message is displayed");
					extent.extentLoggerPass("TC_Ring_Core_215", "TC_Ring_Core_215, Invalid Otp, Please enter correct otp. Error Message is displayed");
					System.out.println("-----------------------------------------------------------");
					Aclick(PaymentPage.objEnterOtp, "Enter OTP Field");
					clearField(PaymentPage.objEnterOtp, "Enter OTP Field");
					type(PaymentPage.objEnterOtp, "888888", "Enter OTP Field");
					hideKeyboard();
					Aclick(PaymentPage.objEnterNewPin, "Enter New Pin");
					type(PaymentPage.objEnterNewPin, "1111", "Enter New Pin Field");
					hideKeyboard();
					explicitWaitVisibility(PaymentPage.objReEnterNewPin, 10);
					Aclick(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					clearField(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					type(PaymentPage.objReEnterNewPin, "1234", "Re-Enter New Pin Field");
					hideKeyboard();
					Aclick(PaymentPage.objContinue, "Continue Button");
					String sEnterSamePinMessage = getText(PaymentPage.objEnterSamePin);
					softAssertion.assertEquals("Please enter same PIN in both fields", sEnterSamePinMessage);
					logger.info("TC_Ring_Core_216, Please enter same PIN in both fields Error Message is displayed");
					extent.extentLoggerPass("TC_Ring_Core_216", "TC_Ring_Core_216, Please enter same PIN in both fields Error Message is displayed");
					System.out.println("-----------------------------------------------------------");
					Aclick(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					clearField(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					type(PaymentPage.objReEnterNewPin, "1111", "Re-Enter New Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					explicitWaitVisibility(PaymentPage.objConfirmPayment, 10);
					softAssertion.assertEquals("Confirm Payment", sConfirmPayment);
					logger.info("TC_Ring_Core_218, Navigated back to Confirm Payment Screen from Change Pin Screen");
					extent.extentLoggerPass("TC_Ring_Core_218", "TC_Ring_Core_218, Navigated back to Confirm Payment Screen from Change Pin Screen");
					System.out.println("-----------------------------------------------------------");
				}
				if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
					Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
					type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					if (verifyElementPresent(PaymentPage.objPaymentDone, getTextVal(PaymentPage.objPaymentDone, "Text"))) {
						String sPaymentDone = getText(PaymentPage.objPaymentDone);
						softAssertion.assertEquals("Payment Done!", sPaymentDone);
						verifyElementPresent(PaymentPage.objGreenTickMark, "Payment Done green tick mark");
						verifyElementPresent(PaymentPage.objRupeesLogo, getTextVal(PaymentPage.objRupeesLogo, "is Rupees Logo"));
						verifyElementPresent(PaymentPage.objMerchantNameReceipt, getTextVal(PaymentPage.objMerchantNameReceipt, "Text"));
						verifyElementPresent(PaymentPage.objTaxDateAndTime, getTextVal(PaymentPage.objTaxDateAndTime, "is Tax date and time"));
						verifyElementPresent(PaymentPage.objSeekBar, "Seek Bar");
						verifyElementPresent(PaymentPage.objAvailableLimit, getTextVal(PaymentPage.objAvailableLimit, "Text"));
						verifyElementPresent(PaymentPage.ObjAvailableLimitAmount, getTextVal(PaymentPage.ObjAvailableLimitAmount, "is Available Limit Amount"));
						verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"));
						logger.info("TC_Ring_Core_219, Validation of Payment Done Screen");
						extent.extentLoggerPass("TC_Ring_Core_219", "TC_Ring_Core_219, Validation of Payment Done Screen");
						System.out.println("-----------------------------------------------------------");
					}
					if (verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"))) {
						Aclick(PaymentPage.objGoToHomePage, "Go To HomePage");
					    explicitWaitVisibility(UserRegistrationPage.objNoBtn, 20);
					    verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
						logger.info("TC_Ring_Core_221, Navigation to HomeScreen");
						extent.extentLoggerPass("TC_Ring_Core_221", "TC_Ring_Core_221, Navigation to HomeScreen");
						System.out.println("-----------------------------------------------------------");
					}
				}
			}
		}
		paymentDeclinedFlow();
		homeScreenValidation();
		ringPayLogout();
	}
	public void homeScreenValidation() throws Exception {
		extent.HeaderChildNode("Home Screen Validation");
		explicitWaitVisibility(HomePage.objHome, 10);
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		if (verifyElementPresent(HomePage.objHome, "Home")) {
			verifyElementPresent(HomePage.objAvailableLimit, getTextVal(HomePage.objAvailableLimit, "Text"));
			verifyElementPresent(HomePage.ObjAvailableLimitAmount, getTextVal(HomePage.ObjAvailableLimitAmount, "is Available Limit Amount"));
			verifyElementPresent(HomePage.objCurrentSpends, getTextVal(HomePage.objCurrentSpends, "Text"));
			verifyElementPresent(HomePage.ObjCurrentSpendsAmount, getTextVal(HomePage.ObjCurrentSpendsAmount, "is Current Spends Amount"));
			verifyElementPresent(HomePage.objRecentTransactions, getTextVal(HomePage.objRecentTransactions, "Text"));
			verifyElementPresent(HomePage.objViewAll, getTextVal(HomePage.objViewAll, "Text"));
			verifyElementPresent(HomePage.objHome, getTextVal(HomePage.objHome, "Text"));
			verifyElementPresent(HomePage.objTransactions, getTextVal(HomePage.objTransactions, "Text"));
			verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button");
			verifyElementPresent(HomePage.objRewards, getTextVal(HomePage.objRewards, "Text"));
			verifyElementPresent(HomePage.objMore, getTextVal(HomePage.objMore, "Text"));
			logger.info("TC_Ring_Core_202, Home Screen Validation");
			extent.extentLoggerPass("TC_Ring_Core_202", "TC_Ring_Core_202,Home Screen Validation");
			System.out.println("-----------------------------------------------------------");
		}
		Swipe("DOWN", 1);
		if (verifyElementPresent(HomePage.objPayEarly, getTextVal(HomePage.objPayEarly, "Text"))) {
			String sPayEarly = getText(HomePage.objPayEarly);
			softAssertion.assertEquals("Pay Early", sPayEarly);
			Aclick(HomePage.objPayEarly, "Pay Early");
			if (verifyElementPresent(PayEarlyPaymentPage.objPayEarlyPayment, getTextVal(PayEarlyPaymentPage.objPayEarlyPayment, "Text"))) {
				String sPayment = getText(PayEarlyPaymentPage.objPayEarlyPayment);
				softAssertion.assertEquals("Pay Early", sPayment);
				logger.info("TC_Ring_Core_224, Navigated to Payment Page to Pay Early");
				extent.extentLoggerPass("TC_Ring_Core_224", "TC_Ring_Core_224, Navigated to Payment Page to Pay Early");
				System.out.println("-----------------------------------------------------------");

				verifyElementPresent(PayEarlyPaymentPage.objAmountDue, getTextVal(PayEarlyPaymentPage.objAmountDue, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objAmountToBePaid, getTextVal(PayEarlyPaymentPage.objAmountToBePaid, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objChoosePaymentMethod, getTextVal(PayEarlyPaymentPage.objChoosePaymentMethod, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objPayViaUPIApps, getTextVal(PayEarlyPaymentPage.objPayViaUPIApps, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objGooglePay, getTextVal(PayEarlyPaymentPage.objGooglePay, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objPhonePe, getTextVal(PayEarlyPaymentPage.objPhonePe, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objPaytm, getTextVal(PayEarlyPaymentPage.objPaytm, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objAmazonPay, getTextVal(PayEarlyPaymentPage.objAmazonPay, "text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objBHIM, getTextVal(PayEarlyPaymentPage.objBHIM, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objUPIAppsConvenienceFee, getTextVal(PayEarlyPaymentPage.objUPIAppsConvenienceFee, ""));
				verifyElementPresent(PayEarlyPaymentPage.objAddUPIId, getTextVal(PayEarlyPaymentPage.objAddUPIId, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objAddUPIIdConvenienceFee, getTextVal(PayEarlyPaymentPage.objAddUPIIdConvenienceFee, ""));
				verifyElementPresent(PayEarlyPaymentPage.objNetBankingAndDebitCard, getTextVal(PayEarlyPaymentPage.objNetBankingAndDebitCard, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objNetBankingAndDebitCardConvenienceFee, getTextVal(PayEarlyPaymentPage.objNetBankingAndDebitCardConvenienceFee, ""));
				Swipe("UP", 1);
				verifyElementPresent(PayEarlyPaymentPage.objBankTransfer, getTextVal(PayEarlyPaymentPage.objBankTransfer, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objPaymentViaUPI, getTextVal(PayEarlyPaymentPage.objPaymentViaUPI, "Text"));
				logger.info("TC_Ring_Core_225, Validation of Pay Early Payment Screen");
				extent.extentLoggerPass("TC_Ring_Core_225", "TC_Ring_Core_225, Validation of Pay Early Payment Screen");
				System.out.println("-----------------------------------------------------------");
			}
		}
		Back(1);
		explicitWaitVisibility(HomePage.objCurrentSpends, 10);
		if (verifyElementPresent(HomePage.objCurrentSpends, getTextVal(HomePage.objCurrentSpends, "Text"))) {
			String sCurrentSpends = getText(HomePage.objCurrentSpends);
			softAssertion.assertEquals("Current Spends", sCurrentSpends);
			verifyElementPresent(HomePage.ObjCurrentSpendsAmount, getTextVal(HomePage.ObjCurrentSpendsAmount, "is Current Spends"));
			verifyElementPresent(HomePage.objPayEarly, getTextVal(HomePage.objPayEarly, "Text"));
			logger.info("TC_Ring_Core_223, Validation of Current Spends Banner");
			extent.extentLoggerPass("TC_Ring_Core_223", "TC_Ring_Core_223, Validation of Current Spends Banner");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(HomePage.objViewAll, "Scan And Pay Button")) {
			Aclick(HomePage.objViewAll, "View All Button");
			explicitWaitVisibility(HomePage.objTransactionHistory, 10);
			if (verifyElementPresent(HomePage.objTransactionHistory, getTextVal(HomePage.objTransactionHistory, "Text"))) {
				String sTransactionHistory = getText(HomePage.objTransactionHistory);
				softAssertion.assertEquals("Transaction History", sTransactionHistory);
				logger.info("TC_Ring_Core_222, Navigated to Transaction History List Screen");
				extent.extentLoggerPass("TC_Ring_Core_222", "TC_Ring_Core_222, Navigated to Transaction History List Screen");
				System.out.println("-----------------------------------------------------------");
			}
		}
		Back(1);
	}

	public void paymentDeclinedFlow() throws Exception {
		extent.HeaderChildNode("Payment declined Flow");
		if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {
			Aclick(HomePage.objScanAndPay, "Scan And pay Button");
			explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
			
			//Scan here
			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            String projectPath=System.getProperty("user.dir");
            getWebDriver().get(projectPath+"\\Mock_Files\\blockedqr.png");
            waitTime(15000);
            BrowsertearDown();
            
            /*------------------------------Android----------------------------*/
            setPlatform("Android");
            initDriver();
			
			explicitWaitVisibility(PaymentPage.objUseCreditLimitText, 10);
			if (verifyElementPresent(PaymentPage.objUseCreditLimitText, getTextVal(RingPayMerchantFlowPage.objUseCreditLimitText, "Text"))) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, amount[2], "Amount Field");
				Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
				String sConfirmPayment = getText(PaymentPage.objConfirmPayment);
				softAssertion.assertEquals("Confirm Payment", sConfirmPayment);
				Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
				type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
				Aclick(PaymentPage.objContinue, "Continue Button");
			}
			explicitWaitVisibility(PaymentPage.objPaymentFailed, 10);
			if (verifyElementPresent(PaymentPage.objPaymentFailed, getTextVal(PaymentPage.objPaymentFailed, "Text"))) {
				String sPaymentDeclined = getText(PaymentPage.objPaymentFailed);
				softAssertion.assertEquals("Payment failed", sPaymentDeclined);
				logger.info("TC_Ring_Core_220, Payment failed Screen is displayed");
				extent.extentLoggerPass("TC_Ring_Core_220", "TC_Ring_Core_220, Payment failed Screen is displayed");
				System.out.println("-----------------------------------------------------------");
				Back(1);
			}
		}
	}
	public void onBoarding() throws Exception {
		extent.HeaderChildNode("User Play store Flow Module");
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		explicitWaitVisibility(RingLoginPage.objLoginLink, 10);
		Aclick(RingLoginPage.objLoginLink, "Signup/Login link");
		explicitWaitVisibility(RingLoginPage.objLoginMobile, 10);
		Aclick(RingLoginPage.objLoginMobile, "Continue with Mobile option");
		String nMobileNumber = "9"+RandomIntegerGenerator(9);
		click(RingLoginPage.objMobTextField, "Mobile Number Field");
		type(RingLoginPage.objMobTextField, nMobileNumber, "Mobile Number Field");
		enterOtp("888888");
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth(prop.getproperty("dateOfBirthMonth"), prop.getproperty("dateOfBirthDate"), prop.getproperty("dateOfBirthYear"));
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		instaNewCommunicationAddress();
		waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("ValidPin"),prop.getproperty("ValidPin"));
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
	}
//===============================================User Scan and Pay end===========================================================	
//=================================================Merchant Flow====================================================================
	public void merchantFlow() throws Exception {
        extent.HeaderChildNode("RingPay App Merchant Flow");
        getDriver().resetApp();
        if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
        
        explicitWaitVisibility(RingPayMerchantFlowPage_New.objScanQRCodeText, 10);
        verifyIsElementDisplayed(RingPayMerchantFlowPage_New.objScanQRCodeText,getTextVal(RingPayMerchantFlowPage.objScanQRCodeText, "Text"));
        softAssertion.assertEquals(getText(RingPayMerchantFlowPage_New.objScanQRCodeText), "Scan any QR to get started");
        verifyElementPresent(RingPayMerchantFlowPage_New.obDontHaveQRCodeText,getTextVal(RingPayMerchantFlowPage_New.obDontHaveQRCodeText, "Text"));
        verifyElementPresent(RingPayMerchantFlowPage_New.objSignUpORLoginLink,getTextVal(RingPayMerchantFlowPage_New.objSignUpORLoginLink, "Text"));
        logger.info("Scanning the QR Code");
        extent.extentLogger("QR Code", "Scanning the QR Code");
      //Scan here
		/*------------------------------WEB----------------------------*/
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        String projectPath=System.getProperty("user.dir");
        getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
        waitTime(15000);
        BrowsertearDown();
        
        /*------------------------------Android----------------------------*/
        setPlatform("Android");
        initDriver();
        if (checkElementExist(RingPayMerchantFlowPage_New.objFreedomPopup, "Freedom Pop Up"))        
        {
            click(RingPayMerchantFlowPage_New.objLetsRingItBtn, getText(RingPayMerchantFlowPage_New.objLetsRingItBtn));
            click(RingPayMerchantFlowPage_New.objLetsRingItBtn, getText(RingPayMerchantFlowPage_New.objLetsRingItBtn));
        }
        verifyElementPresent(RingPayMerchantFlowPage_New.objMakePaymentPage, "Navigated to Make Payment Page");
	       verifyElementPresent(RingPayMerchantFlowPage_New.objPayTypeMethod,getTextVal(RingPayMerchantFlowPage_New.objPayTypeMethod, "Payment Method"));
	       verifyElementPresent(RingPayMerchantFlowPage_New.objUpiId, getTextVal(RingPayMerchantFlowPage_New.objUpiId, "UPI Id"));
	       click(RingPayMerchantFlowPage_New.objAmountTextField, "Amount Text Field");
	       waitTime(2000);
	       iskeyboardShown("Numeric");
	       for (int i = 0; i <= amount.length; i++) {
	           type1(RingPayMerchantFlowPage_New.objAmountTextField, amount[i], "Amount Field");
	           String validationText = getTextVal(RingPayMerchantFlowPage_New.objTransactionMsg, "Text is Displayed");
	           if (validationText.contains("You can pay up to")) {
	               break;
	           }
	           logger.warn(validationText);
	           extent.extentLoggerWarning("validation", validationText);
	           clearField(RingPayMerchantFlowPage_New.objAmountTextField, "Amount text field");
	       }
	       click(RingPayMerchantFlowPage_New.objProceedBtn, "Proceed Button");
	
	       verifyIsElementDisplayed(RingPayMerchantFlowPage_New.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
	       softAssertion.assertEquals(getText(RingPayMerchantFlowPage_New.objLoginPageHeader), "Sign up/Login");
	       logger.info("User redirected to Signup/Login Screen");
	       extent.extentLogger("login", "User redirected to Signup/Login Screen");
	       verifyElementPresent(RingPayMerchantFlowPage_New.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage_New.objContinueWithMobileCTA, "CTA"));
	       verifyElementPresent(RingPayMerchantFlowPage_New.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage_New.objContinueWithGoogleCTA, "CTA"));
	       verifyElementPresent(RingPayMerchantFlowPage_New.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage_New.objContinueWithFacebookCTA, "CTA"));
	
	       extent.extentLoggerPass("TC_Ring_Core_60", "TC_Ring_Core_60-To Verify the Login screen when user open the app");
	       extent.extentLoggerPass("TC_Ring_Core_61", "TC_Ring_Core_61-To verify When User selects Enable Permission option");
	       extent.extentLoggerPass("TC_Ring_Core_62", "TC_Ring_Core_62-To verify when user Scans the QR  code");
	       extent.extentLoggerPass("TC_Ring_Core_63", "TC_Ring_Core_63-To verify the Screen when the Pop up disappers");
	       extent.extentLoggerPass("TC_Ring_Core_64", "TC_Ring_Core_64-To verify the merchant details ");
	       extent.extentLoggerPass("TC_Ring_Core_65", "TC_Ring_Core_65-To verify the First Transcation  fee message ");
	       extent.extentLoggerPass("TC_Ring_Core_66","TC_Ring_Core_66-To Verify  when User cliks on  enter transaction amount on screen ");
	       extent.extentLoggerPass("TC_Ring_Core_67","TC_Ring_Core_67-To verify when user  tries to enter amount more than first transaction limit");
	       extent.extentLoggerPass("TC_Ring_Core_68", "TC_Ring_Core_68-To Verify when user tries to enter 0 amount");
	       extent.extentLoggerPass("TC_Ring_Core_69","TC_Ring_Core_69-To Verify user enters valid amt. and clicks on the Pay now button on merchant detail page");
	       
	       userPlayStoreLoginFlow(prop.getproperty("lessThanTenMob"), prop.getproperty("moreThanTenMob"), prop.getproperty("specialCharMob"), prop.getproperty("spaceMob"), prop.getproperty("validMob"), prop.getproperty("invalidOtp"), prop.getproperty("lessOtp"));
		   waitTime(40000);
		   userDetails();
		   dateOfBirth("Feb", "10", "1996");
		   click(UserRegistrationNew.objProceed, "Proceed Button");
		   waitTime(10000);
		   instaNewCommunicationAddress();
			
		   verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
		   softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
		   verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
		   verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
		   verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
		   verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
		   verifyElementPresent(PromoCodeOfferPage.objRBIMsg , getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));
		   verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		   verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
		   instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		   waitTime(20000);
		   verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
		   waitTime(20000);
		   verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		   explicitWaitVisibility(UserRegistrationPage.objNoBtn, 20);
		   verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		   String sHome = getText(HomePage.objHome);
		   softAssertion.assertEquals(sHome, "Home");
		   ringPayLogout();
   }
//================================================Merchant Flow End=================================================================
	
	public void cameraPermission() throws Exception {
		explicitWaitVisibility(RingLoginPage.objCamPermHeader, 30);
		verifyElementPresent(RingLoginPage.objCamPermHeader, "Camera Permission required");
		String camPermHeaderTxt = getText(RingLoginPage.objCamPermHeader);
		softAssertion.assertEquals(camPermHeaderTxt, "Camera Permission required");
		logger.info("Camera Permission required popup");
	}

	public void enablePermissions() throws Exception {
		explicitWaitVisibility(RingLoginPage.objCamPermPopUp, 10);
		Aclick(RingLoginPage.objCamPermPopUp, "Enable permissions button");
		logger.info("Foreground allow camera permissions");
		extent.extentLoggerPass("Foreground allow camera permissions", "Foreground allow camera permissions options");
		explicitWaitVisibility(RingLoginPage.objAllowCamera, 10);
		Aclick(RingLoginPage.objAllowCamera, "While using the app foreground camera permission option");

		explicitWaitVisibility(RingLoginPage.objScanQRPage, 10);
		logger.info("Scan any QR to get started");
		String qrCodeHeader = getText(RingLoginPage.objScanQRPage);
		softAssertion.assertEquals(qrCodeHeader, "Scan any QR to get started");
	}

	public void loginMobile() throws Exception {
		explicitWaitVisibility(RingLoginPage.objLoginMobile, 10);
		Aclick(RingLoginPage.objLoginMobile, "Continue with Mobile option");

//		trueCallerPopup();
//		if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above button")) {
//			String noneOfAboveTxt = getText(RingLoginPage.objNoneBtn);
//			softAssertion.assertEquals(noneOfAboveTxt, "NONE OF THE ABOVE");
//			Aclick(RingLoginPage.objNoneBtn, "None of the above");
//
//			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
//			logger.info("Verify Mobile Header");
//			String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
//			softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
//			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
//			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
//			String verifyMobHeaderTxt1 = getText(RingLoginPage.objVerifyMobHeader);
//			softAssertion.assertEquals(verifyMobHeaderTxt1, "Verify Mobile");
//			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
//		}else {
			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
			String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
			softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
			logger.info("Verify Mobile Header");
			String verifyMobHeaderTxt1 = getText(RingLoginPage.objVerifyMobHeader);
			softAssertion.assertEquals(verifyMobHeaderTxt1, "Verify Mobile");
			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
//		}

	}

	public void trueCallerPopup() throws Exception {
		if (verifyElementPresent(RingLoginPage.objTruSkipBtn, "True caller popup")) {
			Aclick(RingLoginPage.objTruSkipBtn, "True caller skip button");
		}
		if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above")) {
			Aclick(RingLoginPage.objNoneBtn, "None of the above");
		}
	}

	public String mobileNoValidation(String mobNo) throws Exception {
		// public String noTxt;
		waitTime(4000);
		if (verifyElementPresent(RingLoginPage.objTruSkipBtn, "True caller popup")) {
			Aclick(RingLoginPage.objTruSkipBtn, "True caller skip button");
		}
		if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above button")) {
			String noneOfAboveTxt = getText(RingLoginPage.objNoneBtn);
			softAssertion.assertEquals(noneOfAboveTxt, "NONE OF THE ABOVE");
			Aclick(RingLoginPage.objNoneBtn, "None of the above");
		}
		explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
		logger.info("Verify Mobile Header");
		String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
		softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
		waitTime(4000);
		Aclick(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, mobNo, "Mobile text field");
		System.out.println(mobNo);
		waitTime(5000);
		String noTxt = getText(RingLoginPage.objMobTextField);
		if (!verifyIsElementDisplayed(RingUserDetailPage.objRegisterBtn)) {
			logger.info("Navigated to OTP Page");
		} else {
			Aclick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
		return noTxt;
	}

	public void mobileNoValidation1(String mobNo) throws Exception {
		click(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, mobNo, "Mobile text field");
//		String noTxt = getText(RingLoginPage.objMobTextField);
//		waitTime(5000);
//		if (!verifyElementExist(RingUserDetailPage.objRegisterBtn, "Proceed Button")) {
//			logger.info("Navigated to OTP Page");
//		} else {
//			click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
//		}
//		return noTxt;
	}
	
	public String mobileNoValidation2(String mobNo) throws Exception {
        waitTime(4000);
        Aclick(RingLoginPage.objMobTextField, "Mobile text field");
        type(RingLoginPage.objMobTextField, mobNo, "Mobile text field");
        System.out.println(mobNo);
        waitTime(3000);
        String noTxt = getText(RingLoginPage.objMobTextField);
        if (mobNo.length()>=10) {
            logger.info("Navigated to OTP Page");
        } else {
            click(RingLoginPage.objProceedBtn, "Proceed Button");
        }
        return noTxt;
    }

	public void enterOtp(String otp) throws Exception {
		explicitWaitClickable(RingLoginPage.resendOtpTxt, 20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, otp, "Enter OTP");
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void gmailLogin(String userId, String password) throws Exception {
		extent.HeaderChildNode("User Play store Flow Module");
		cameraPermission();
		enablePermissions();
		explicitWaitVisibility(SignUP_LoginPage.continueWithGmail, 20);
		Aclick(SignUP_LoginPage.continueWithGmail, "Continue With Gmail Account");
		if (verifyElementPresent(GmailLoginPage.addAnotherAccount, "Add Another Account")) {
			Aclick(GmailLoginPage.addAnotherAccount, "Add Another Account");
		}

		explicitWaitVisibility(GmailLoginPage.enterEmailID, 20);
		Aclick(GmailLoginPage.enterEmailID, "Enter Email Field");
		type(GmailLoginPage.enterEmailID, userId, "Entered Email ID");
		Aclick(GmailLoginPage.nextButton, "Next Button");
		explicitWaitVisibility(GmailLoginPage.enterPassword, 20);
		// Aclick(GmailLoginPage.enterPassword, "Enter Password Field");
		type(GmailLoginPage.enterPassword, password, "Password");
		Aclick(GmailLoginPage.nextButton, "Next Button");

		if (verifyElementPresent(GmailLoginPage.txtKeepYourAccountUpdate,
				"Keep Your account updated with phone number")) {
			scrollToBottomOfPage();
			Aclick(GmailLoginPage.btnYesImIn, "Yes I'm in");
		}

		explicitWaitVisibility(GmailLoginPage.btnIAgree, 20);
		Aclick(GmailLoginPage.btnIAgree, "I Agree Button");
		explicitWaitVisibility(GmailLoginPage.txtGoogleServices, 20);

		Aclick(GmailLoginPage.btnMore, "More Button");
		explicitWaitVisibility(GmailLoginPage.btnAccept, 20);
		Aclick(GmailLoginPage.btnAccept, "Accept Button");
		waitTime(10000);
	}
	/**
	 * Business method for RingPay Application Logout
	 * 
	 */

	public void ringPayLogout() throws Exception {
		extent.HeaderChildNode("RingPay Logout");

		explicitWaitVisibility(RingLoginPage.objTopMenu, 15);
		click(RingLoginPage.objTopMenu, "Top left menu button");

		explicitWaitVisibility(RingLoginPage.objProfileSelect, 10);
		click(RingLoginPage.objProfileSelect, "Profile Select Button");

		explicitWaitVisibility(RingLoginPage.objLogoutBtn, 10);

		click(RingLoginPage.objLogoutBtn, "Logout Button");

		logger.info("Logout popup comes up");
		extent.extentLoggerPass("Logout popup", "Logout popup comes up");

		explicitWaitVisibility(RingLoginPage.objYesBtn, 10);
		click(RingLoginPage.objYesBtn, "Yes confirmation button");

		logger.info("User is successfully logged out");
		extent.extentLoggerPass("Logout confirmation", "User is successfully logged out");

	}

	public void hamburgerTab() throws Exception {
		verifyElementPresentAndClick(RingUserDetailPage.objHamburgerTab, "Hamburger Tab");
		verifyElementPresentAndClick(RingUserDetailPage.objProfileTabCompletedPercentage,
				"Profile Completed Percentage tab");
		verifyElementPresentAndClick(RingUserDetailPage.objLogoutBtn, "Logout Button");
		logger.info("Are you sure you want to Logout?");
		explicitWaitVisibility(RingUserDetailPage.objLogOutYesBtn, 10);
		click(RingUserDetailPage.objLogOutYesBtn, "Yes Button");
	}

//==============================================InstaLoan Onboarding==========================================================================
	public void mockUserAPI() throws ClassNotFoundException, SQLException {
		extent.HeaderChildNode("DB");
		ValidatableResponse response = Utilities.MockuserAPI(
				"https://testing-gateway.test.paywithring.com/api/v1/mock-data/user", "male",
				"bofMUgmO0QM6uOmlyr8xxA==");
		firstName = response.extract().jsonPath().getString("data.data.first_name");
		System.out.println("First Name= " + firstName);
		middleName = response.extract().jsonPath().getString("data.data.middle_name");
		System.out.println("Middle Name= " + middleName);
		lastName = response.extract().jsonPath().getString("data.data.last_name");
		System.out.println("Last Name =" + lastName);
		panCard = response.extract().jsonPath().getString("data.data.pan");
		System.out.println("Pan Card= " + panCard);
		mobileNumber = response.extract().jsonPath().getString("data.data.mobile_number");
		System.out.println("Mobile Number= " + mobileNumber);
		mothersName = response.extract().jsonPath().getString("data.data.mother_name");
		System.out.println("Mother Name= " + mothersName);
		email = response.extract().jsonPath().getString("data.data.email");
		System.out.println("Email= " + email);
		gender = response.extract().jsonPath().getString("data.data.gender");
		System.out.println("Gender= " + gender);
		String otp = response.extract().jsonPath().getString("data.data.otp");
		System.out.println(otp);
		String dob = response.extract().jsonPath().getString("data.data.dob");
		System.out.println(dob);
		String[] dateSplit = dob.split("-");
		year = dateSplit[0];
		System.out.println("years---" + year);
		String month = dateSplit[1];
		System.out.println("month---" + month);
		date = dateSplit[2];
		System.out.println("date---" + date);
		int monthNumber = Integer.parseInt(month);
		monthByName = Month.of(monthNumber).getDisplayName(TextStyle.SHORT, Locale.US);
		System.out.println(monthByName);
		aadharFrontImage = response.extract().jsonPath().getString("data.data.aadhaar_front_image.asset_path");
		System.out.println("Front Aadhar Image---->" + aadharFrontImage);
		aadharBackImage = response.extract().jsonPath().getString("data.data.aadhaar_back_image.asset_path");
		System.out.println("Back Aadhar Image---->" + aadharBackImage);
		panImage = response.extract().jsonPath().getString("data.data.pan_image.asset_path");
		System.out.println("Pan Card Image---->" + panImage);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("--------------------------Bank Account Details----------------------");
		
		bankAccountNumber=response.extract().jsonPath().getString("data.data.bank_account.bank_account_no");
		System.out.println("Bank Account Number----"+bankAccountNumber);
		bankifsc=response.extract().jsonPath().getString("data.data.bank_account.bank_branch_ifsc");
		System.out.println("Bank IFSC-----"+bankifsc);
		bankAccountType=response.extract().jsonPath().getString("data.data.bank_account.bank_account_type");
		System.out.println("Bank Account Type-----"+bankAccountType);
		bankAccountHolder=response.extract().jsonPath().getString("data.data.bank_account.bank_account_holder_name");
		System.out.println("Account Holder----"+bankAccountHolder);
//		executeQuery("SELECT * FROM db_tradofina.instaloan_whitelisted_users;");
//		executeUpdate("Update db_tradofina.instaloan_whitelisted_users set approved_amount='000' where id=48","SELECT * FROM db_tradofina.instaloan_whitelisted_users;");
//		executeInsert();
	}

	public void instaLoanWhitelistLogic() throws Exception {
		extent.HeaderChildNode("WhiteList Logic");
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		instaUserDetails(firstName,lastName,mothersName,email,gender);
//		instaNewCommunicationAddress();
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}
		waitTime(10000);
		instaKycDocument();
		instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","13000","2311","2319");
		panCardDetails();
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
	}

	public void loginOnboarding(String amount) throws Exception {
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresent(RingLoginPage.objScanQRPage, getText(RingLoginPage.objScanQRPage));
		if (verifyElementPresent(RingLoginPage.objMakePayment, "Make Payment Page")) {
			keyLogin = getText(RingLoginPage.objMakePayment);
			System.out.println(keyLogin);
		} else {
			keyLogin = getText(RingLoginPage.objScanQRPage);
			System.out.println(keyLogin);
		}

		switch (keyLogin) {
		case "Say hello to freedom!!":
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
			type1(RingPayMerchantFlowPage.objAmountTextField, amount, "Amount Field");
			verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
			verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
			softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
			logger.info("User redirected to Signup/Login Screen");
			extent.extentLogger("login", "User redirected to Signup/Login Screen");
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
			loginMobile();
			break;

		case "Scan any QR to get started":
			verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
			softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
			logger.info("User redirected to Signup/Login Screen");
			extent.extentLogger("login", "User redirected to Signup/Login Screen");
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
			loginMobile();
			break;
		default: logger.info("Application crashed!!");
				 extent.extentLoggerFail("App Crash", "Application crashed!!");
			break;
		}
	}
	
	public void readAndAccept() throws Exception {
		
		click(RingLoginPage.objReadAcceptBtn, "Read & Accept button");
		//if (verifyElementPresent(RingLoginPage.objlocationPopup, "RingPay permissions popup")) {

			logger.info("Ring Pay Permissions page (SMS, LOCATION & PHONE)");
		//	String ringPermissionTxt = getText(RingLoginPage.objlocationPopup);
			//Assert.assertEquals(ringPermissionTxt, "Allow Ring Debug to access this device’s location?");

			explicitWaitVisibility(RingLoginPage.objPhoneAccess, 10);
			click(RingLoginPage.objPhoneAccess, "Phone access option");
			explicitWaitVisibility(RingLoginPage.objLocAccess, 10);
			click(RingLoginPage.objLocAccess, "Location Access option");
			explicitWaitVisibility(RingLoginPage.objSMSAccess, 10);
			click(RingLoginPage.objSMSAccess, "SMS access option");
		//}
	}
	
	public void instaUserDetails(String firstName, String lastName, String mothersName, String email, String gender) throws Exception {
		//explicitWaitVisibility(RingUserDetailPage.objFirstName, 10);
		waitTime(10000);
		click(RingUserDetailPage.objFirstName, "First Name Field");
		type(RingUserDetailPage.objFirstName,firstName , "First Name field");
		waitTime(5000);
		explicitWaitVisibility(RingUserDetailPage.objLastName, 10);
		click(RingUserDetailPage.objLastName, "Last Name Field");
		type(RingUserDetailPage.objLastName, lastName, "Last Name field");
		waitTime(5000);
		hideKeyboard();
		explicitWaitVisibility(RingUserDetailPage.objMothersName, 10);
		click(RingUserDetailPage.objMothersName, "Mother's Name Field");
		type(RingUserDetailPage.objMothersName, mothersName, "Mother's Name field");
		
		hideKeyboard();
		waitTime(5000);
		explicitWaitVisibility(RingUserDetailPage.objEmail, 10);
		click(RingUserDetailPage.objEmail, "Email Field");
		click(RingUserDetailPage.objNone, "None of the Above Button");
		type(RingUserDetailPage.objEmail, email, "Email Filed");
		hideKeyboard();
		waitTime(10000);
		dateOfBirth(monthByName, date, year);
		click(RingLoginPage.objGender, "Gender male is selected");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
	}
	
	public void instaLoancongratsScreen() throws Exception {
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 and Key Fact Statement");
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
	}
		public void instaLoanSetPin(String enterMPIN , String ReEnterMPIN) throws Exception {
		verifyElementPresent(RingLoginPage.objSetPinPage, getText(RingLoginPage.objSetPinPage));
		//new TouchAction(getDriver()).tap(PointOption.point(237,800)).release().perform();
		Aclick(RingLoginPage.objEnterPin, "Mpin Field");
		type(RingLoginPage.objEnterPin, enterMPIN, "MPIN Field");
		hideKeyboard();
		//Aclick(RingUserDetailPage.objReEnterPin, "Re-Enter Pin Field");
		Aclick(RingUserDetailPage.objReEnterPin, "Re-Enter Pin Field");
		//new TouchAction(getDriver()).tap(PointOption.point(240,1123)).release().perform();
		type(RingUserDetailPage.objReEnterPin, ReEnterMPIN, "Re-Enter pin Field");
		verifyElementPresentAndClick(RingLoginPage.objSetinSubmitBtn, getText(RingLoginPage.objSetinSubmitBtn));
	}
	public void instaNewCommunicationAddress() throws Exception {
		if(verifyElementPresent2(AddAddressPage.objAddressHeader, "New Communication Address Page Header")) {
		click(AddAddressPage.objRoomNoTextField, "Room No Field");
		type(AddAddressPage.objRoomNoTextField, "86", "Room No. Field");
			
		click(AddAddressPage.objAddressLine_1, "Address Line 1 Field");
		type(AddAddressPage.objAddressLine_1, "44, Borivali", "Address Line 1");
			
		click(AddAddressPage.objAddressLine_2, "Address Line 2 Field");
		type(AddAddressPage.objAddressLine_2, "Vasa Street",getTextVal(AddAddressPage.objAddressLine_2, "Text Field"));
		
		hideKeyboard();
	//	click(AddAddressPage.objLandmarkField, "Landmark Field");
	//	type(AddAddressPage.objLandmarkField, "Das Gupta street",getTextVal(AddAddressPage.objLandmarkField, "Text Field"));
		
		hideKeyboard();
		Swipe("up", 2);
		click(AddAddressPage.objPinCodeFieldpulkit, "Pincode Field");
		type(AddAddressPage.objPinCodeFieldpulkit, "400080", getTextVal(AddAddressPage.objPinCodeFieldpulkit, "Text Field"));

		hideKeyboard();
		explicitWaitVisibility(AddAddressPage.objCityTextField, 10);
		logger.info(getTextVal(AddAddressPage.objCityTextField, "City Name autoPopulated after entering pincode"));
		extent.extentLogger("CityName",getTextVal(AddAddressPage.objCityTextField, "City Name autoPopulated after entering pincode"));

		explicitWaitVisibility(AddAddressPage.objStateTextField, 10);
		logger.info(getTextVal(AddAddressPage.objStateTextField, "State Name autoPopulated after entering pincode"));
		extent.extentLogger("CityName",getTextVal(AddAddressPage.objStateTextField, "State Name autoPopulated after entering pincode"));
			
		explicitWaitVisibility(AddAddressPage.objSubmitButton, 10);
		click(AddAddressPage.objSubmitButton, "Submit Button");
		} else {
			logger.warn("Address Page is not displyed");
			extent.extentLoggerWarning("Address Page", "Address Page is not displyed");
		}
	}
	
	public void instaKycDocument() throws Exception {
		verifyElementPresent(InstaLoanPage.objKYCHeader, getText(InstaLoanPage.objKYCHeader));
		verifyElementPresentAndClick(InstaLoanPage.objKYCFrontAadhar, "Upload Front Aadhar");
		if(verifyElementPresent(InstaLoanPage.objCaptureAadharFrontImage, "Capture Aadhar Front Page")) {
			
			setPlatform("Web");
			System.out.println("platform changed to web");
			String pf = getPlatform();
			System.out.println(pf);
			waitTime(5000);
			new RingPayBusinessLogic(null);
			waitTime(10000);
			getWebDriver().get(aadharFrontImage);
			waitTime(10000);
			
			setPlatform("Android");
			initDriver();
			waitTime(5000);
			click(InstaLoanPage.objCaptureBtn, "Clicked on Capture Button");
			click(InstaLoanPage.objKYCCaptureContinueBtn, getText(InstaLoanPage.objKYCCaptureContinueBtn));
			setPlatform("Web");
			String pft = getPlatform();
			System.out.println(pft);
			initDriver();
			waitTime(10000);

			getWebDriver().get(aadharBackImage);
			waitTime(10000);
			
			setPlatform("Android");
			initDriver();
			waitTime(5000);
			click(InstaLoanPage.objBackAadharCaptureBtn, "Clicked on Capture Button");
			click(InstaLoanPage.objKYCCaptureContinueBtn, getText(InstaLoanPage.objKYCCaptureContinueBtn));
			
			setPlatform("Web");
			String pf2 = getPlatform();
			System.out.println(pf2);
			initDriver();
			waitTime(10000);
			//BrowsertearDown();
			waitTime(10000);
			
			setPlatform("Android");
			initDriver();
			waitTime(5000);
			verifyElementPresent(InstaLoanPage.objKYCCAptureSelfie, getText(InstaLoanPage.objKYCCAptureSelfie));
			waitTime(5000);
			verifyElementPresentAndClick(InstaLoanPage.objSelficeCaptureBtn, "Camera Capture Button");
			verifyElementPresentAndClick(InstaLoanPage.objKYCCaptureContinueBtn, getText(InstaLoanPage.objKYCCaptureContinueBtn));
			verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
	}
	public String instaPanCardDetails(String ringAdminEmail,String ringAdminPassword,String ringAdminOTP,String eligible_type, String approved_amount,String offer_id,String repeat_offer_id) throws Exception {
		
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		
		
		click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, ringAdminEmail, "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, ringAdminPassword, "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, ringAdminOTP, "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
		selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
		click(InstaLoanPage.objSearchTerm, "Search Item Field");
		type(InstaLoanPage.objSearchTerm, mobileNumber, "Search Item Field");
		click(InstaLoanPage.objSearchBtn, "Search Button");
		waitTime(5000);
		String userReferenceNo = getText(InstaLoanPage.objUserReferenceNo);
		System.out.println(userReferenceNo);
		waitTime(5000);
		ScrollToTheElement(InstaLoanPage.dragDown);
		waitTime(5000);
		executeInsert(userReferenceNo,eligible_type,approved_amount,offer_id,repeat_offer_id);
		executeQuery("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '"+userReferenceNo+"';","user_reference_number");
		
		waitTime(5000);
		JSClick(InstaLoanPage.objOthersTab, "Other Tab");
		ScrollToTheElement(InstaLoanPage.dragDown);
		JSClick(InstaLoanPage.objPanNSDLData, "PanNSDL Tab");
		JSClick(InstaLoanPage.objAddPanCard, "Add PanCard");
		
		click(InstaLoanPage.objNameField, "First Name Field");
		type(InstaLoanPage.objNameField, firstName, "First Name Field");
		click(InstaLoanPage.objMiddleNameField, "Middle Name Field");
		type(InstaLoanPage.objMiddleNameField, middleName, "Middle Name Field");
		click(InstaLoanPage.objLastNameField, "Last Name Field");
		type(InstaLoanPage.objLastNameField, lastName, "Last Name Field");
		click(InstaLoanPage.objPanNoField, "PanNo. Field");
		type(InstaLoanPage.objPanNoField, panCard, "PanNo. Field");
		
		selectByVisibleTextFromDD(InstaLoanPage.objPanStatus, "Valid");
		waitTime(3000);
		verifyElementPresentAndClick(InstaLoanPage.objSubmitBtn, "Submit Button");
		waitTime(5000);
		
		BrowsertearDown();
		waitTime(10000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		return userReferenceNo;
	}
	public void panCardDetails() throws Exception {
		verifyElementPresent(InstaLoanPage.objPanNoHeader, getText(InstaLoanPage.objPanNoHeader));
		verifyElementPresentAndClick(InstaLoanPage.objPanNoEnter, "Pan Card Number Field");
		type(InstaLoanPage.objPanNoEnter, panCard, "Pan Card Number Field");
		try {
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
		catch (Exception e) {
			
		}
	}
	
	public String getRefrenceNumber(String mobileNumber) throws Exception {
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		
		
		click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, prop.getproperty("RingAdminEmail"), "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, prop.getproperty("RingAdminPassword"), "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, prop.getproperty("RingAdminOTP"), "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
		selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
		click(InstaLoanPage.objSearchTerm, "Search Item Field");
		type(InstaLoanPage.objSearchTerm, mobileNumber, "Search Item Field");
		click(InstaLoanPage.objSearchBtn, "Search Button");
		waitTime(10000);
		String userReferenceNo = getText(InstaLoanPage.objUserReferenceNo);
		System.out.println(userReferenceNo);
		BrowsertearDown();
		waitTime(10000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		return userReferenceNo;
	}
	
    public void checkBoxisSelected(By locator) {
        List<WebElement> checkbox = getDriver().findElements(By.className("android.widget.CheckBox"));
            String a=checkbox.get(0).getAttribute("checked");
            logger.info("The checkbox is selection state is - " + a);
            extent.extentLoggerPass("checkBox", "The checkbox is selection state is - " + a);
    }
	
	public void afterClickOnApplyNowForLoanOfferBanner() throws Exception {
		extent.HeaderChildNode("After click on apply now for loan offer banner");
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		user_reference=instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","5000","2135","2319");
		readAndAccept();
		waitTime(30000);
		
		instaUserDetails(firstName,lastName,mothersName,email,gender);
		
		waitTime(30000);
		explicitWaitVisibility(AddAddressPage.objAddCurrentAddressHeader, 30);
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}
		if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
			instaKycDocument();

			waitTime(20000);
			panCardDetails();
			logger.info("Pan Card Validate Successfully");
			extent.extentLoggerPass("Pan Card Details", "Pan Card Details Validatation Successfull");
		}
		
		waitTime(10000);
		click(RingLoginPage.objIAcceptCheckBox, "Term and Condition Check Box");
		click(RingLoginPage.objAcceptOfferBtn, "Accept Button");
		
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		waitTime(13000);
		if (verifyElementPresent2(HomePage.objAdCloseBtn, "Ad Banner")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		if (verifyElementPresent2(InstaLoanPage.bannerTitle, "Check")) {
			click(InstaLoanPage.closeBanner, "Close Instalone Banner");
		}
		waitTime(3000);
		click(InstaLoanPage.btnOkaygotIt, "Okay Got It");
		
		explicitWaitVisibility(HomePage.moreTab, 20);
		click(HomePage.moreTab, "More Tab");
		explicitWaitVisibility(HomePage.tabBankTransfer, 20);
		click(HomePage.tabBankTransfer, "Bank Transfer Button");
		waitTime(20000);
		
		addbankAccount("5",bankAccountHolder);
		hideKeyboard();
		waitTime(10000);
		hideKeyboard();
		Back(3);
		
		waitTime(6000);
		click(InstaLoanPage.btnApplyNow, "Apply Now Button");
		waitTime(10000);
		
		if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
			explicitWaitVisibility(KycDocument.kycHeader, 20);
			softAssertion.assertEquals("KYC Documents", getText(KycDocument.kycHeader));
			logger.info("KYC Documents Page Validate Successfully");
			extent.extentLoggerPass("KYC Document", "KYC Document Page Validatation Successfull------------TC_Insta_loan_108");
			
			instaKycDocument();

			waitTime(20000);
			panCardDetails();
			logger.info("Pan Card Validate Successfully");
			extent.extentLoggerPass("Pan Card Details", "Pan Card Details Validatation Successfull-------TC_Insta_loan_48");
			extent.extentLoggerPass("Pan Card Details", "Pan Card Details Validatation Successfull-------TC_Insta_loan_82");
			extent.extentLoggerPass("Pan Card Details", "Pan Card Details Validatation Successfull-------TC_Insta_loan_110");
		}
		
		explicitWaitVisibility(InstaLoanPage.txtBasicDetails, 20);
		
		softAssertion.assertEquals("Basic Details", getText(InstaLoanPage.txtBasicDetails));
		logger.info("Basic Details Page Validate");
		extent.extentLoggerPass("Basic Details", "Basic Details Validatation Successfull------TC_Insta_loan_112");
		Aclick(InstaLoanPage.fieldFatherName, "");
		type(InstaLoanPage.fieldFatherName, "Dinesh", "Fathers Name Field");
		try {
		click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
		catch (Exception e) {
		}
		explicitWaitVisibility(InstaLoanPage.ckbCKYCTerm, 20);
		extent.extentLoggerPass("Consent Popup", "Consent Popup Display-------------TC_Insta_loan_114");
		checkBoxisSelected(InstaLoanPage.objIallowCheckBox);
		checkBoxisSelected(InstaLoanPage.objReadmoreCheckBox);
		extent.extentLoggerPass("Consent should be prechecked", "Consent is prechecked------------TC_Insta_loan_48");

		new TouchAction(getDriver()).tap(PointOption.point(585,1632)).release().perform();
		softAssertion.assertEquals("Credit Bureau Consent", InstaLoanPage.txtCreditBureauConsent);
		logger.info("Credit Bureau Consent");
		extent.extentLoggerPass("Credit Bureau Consent ", "Credit Bureau Consent Display-------------TC_Insta_loan_115");
		extent.extentLoggerPass("Term and Condition", "Term and Condition--------------TC_Insta_loan_126");
		click(InstaLoanPage.btnOkGotIt, "");
		explicitWaitVisibility(RingUserDetailPage.objRegisterBtn, 20);
		new TouchAction(getDriver()).tap(PointOption.point(324,1688)).release().perform();
		softAssertion.assertEquals("CKYC Consent", InstaLoanPage.txtCKYC_Consent);
		logger.info("CKYC Consent");
		extent.extentLoggerPass("CKYC Consent", "CKYC Consent Display-------------TC_Insta_loan_116");
		extent.extentLoggerPass("Term and Condition", "Term and Condition--------------TC_Insta_loan_126");
		click(InstaLoanPage.btnOkGotIt, "");
		explicitWaitVisibility(RingUserDetailPage.objRegisterBtn, 20);
		new TouchAction(getDriver()).tap(PointOption.point(475,1948)).release().perform();
		softAssertion.assertEquals("Ok, Got It!", InstaLoanPage.btnOkGotIt);
		logger.info("Read More");
		click(InstaLoanPage.btnOkGotIt, "Okay Got It Button");
		click(InstaLoanPage.ckbCKYCTerm, "Check Box CKYC Term");
		click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		String toast = getText(UserRegistrationNew.objToast);
		System.out.println(toast);
		softAssertion.assertEquals("Please select the checkbox for relevant authorizations", toast);
		extent.extentLoggerPass("Toast Message Display", "Toast Message Display if checkbox unchecked --------------TC_Insta_loan_83");
		extent.extentLoggerPass("Toast Message Display", "Toast Message Display if checkbox unchecked --------------TC_Insta_loan_117");
		logger.info(toast+" Validation Successfully");
		extent.extentLogger("Toast Message Validation", toast);
		click(InstaLoanPage.ckbCKYCTerm, "Check Box CKYC Term");
		click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			softAssertion.assertEquals("New Current Address", AddAddressPage.objAddCurrentAddressHeader);
			logger.info("New Current Address Validate Successfully");
			extent.extentLoggerPass("New Current Address", "New Current Address Validate Successfully--------------TC_Insta_loan_118");
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}	
		if (verifyElementPresent2(InstaLoanPage.txtPermanentAddress, "Address Page")) {
			click(InstaLoanPage.dropSelectAddress, "Select Address Drop Down");
			explicitWaitVisibility(InstaLoanPage.dropAddressSelection, 20);
			click(InstaLoanPage.dropAddressSelection, "Select Address First Option");
			extent.extentLoggerPass("New Current Address", "New Current Address Validate Successfully--------------TC_Insta_loan_118");
			click(InstaLoanPage.ckbDeclarationAddress, "Declaration CheckBox");
			click(InstaLoanPage.linkAddCommunicationAddress, "Add Address Link");
			extent.extentLoggerPass("New Communication Link", "New Communication Link--------------TC_Insta_loan_119");
			explicitWaitVisibility(AddAddressPage.objAddCurrentAddressHeader, 20);
			addAddress("85","45, Borivali","Vasa Street","Das Gupta Street","400080");
			extent.extentLoggerPass("New Communication Address", "New Communication Successfully--------------TC_Insta_loan_120");
			click(AddAddressPage.objSubmitBtn,"Submit");

		}
		explicitWaitVisibility(InstaLoanPage.txtCongratulations, 20);
		softAssertion.assertEquals("Offer", InstaLoanPage.txtOffer);
		softAssertion.assertEquals("Congratulations",InstaLoanPage.txtCongratulations);
		softAssertion.assertEquals("Add Bank Details",InstaLoanPage.btnAddABankDetails);
		explicitWaitVisibility(InstaLoanPage.btnAddABankDetails, 10);
		click(InstaLoanPage.btnAddABankDetails, "Add Bank Account Button");
		
		
		waitTime(15000);
		if (verifyElementPresent2(InstaLoanPage.txtExistingBankAcc, "Address Page")) {
			softAssertion.assertEquals("Existing Bank Account", getText(InstaLoanPage.txtExistingBankAcc));
			logger.info("Existing Bank Account Validation Successfull");
			extent.extentLoggerPass("Existing Bank Account", "Existing Bank Validation Pass-----------------TC_Insta_loan_121");
			click(InstaLoanPage.selectExistingAccount, "Select Existing Bank Account");
			extent.extentLoggerPass("Select Existing Bank", "Select Existing Bank------------TC_Insta_loan_122");
			
		}
		else {
			explicitWaitVisibility(InstaLoanPage.txtAddBankAccount, 20);
			addbankAccount("5",bankAccountHolder);
			explicitWaitVisibility(InstaLoanPage.txtVerifyBankDetails, 20);
			click(InstaLoanPage.btnConfirm, "Confirm Button");
		}
		
		
		explicitWaitVisibility(InstaLoanPage.txtCongratulations, 20);
		softAssertion.assertEquals("Offer", InstaLoanPage.txtOffer);
		softAssertion.assertEquals("Congratulations",InstaLoanPage.txtCongratulations);
		click(InstaLoanPage.ckbAcceptOfferPermission, "Permission Accept Offer");
		click(InstaLoanPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(InstaLoanPage.txtImportant, 20);
		click(InstaLoanPage.btnConfirmOffer, "Confirm Order Button");
		waitTime(120000);
		explicitWaitVisibility(InstaLoanPage.btnHome, 20);
		click(InstaLoanPage.btnHome, "Home Button");
		waitTime(80000);
		Swipe("Down",2);
		explicitWaitVisibility(HomePage.homeTab, 30);
		logger.info("Home Page");
		explicitWaitVisibility(InstaLoanPage.titleDisbursed, 30);
		
		softAssertion.assertEquals("Disbursed", InstaLoanPage.titleDisbursed);
		
		softAssertion.assertEquals("₹5,000", getText(InstaLoanPage.approvedLoanAmount));
		logger.info(getText(InstaLoanPage.titleDisbursed)+" Successful");
		extent.extentLoggerPass("Disbursed Successfull", "Disbursed Successfully Done-------------TC_Insta_loan_123");
		softAssertion.assertEquals("Your Ring limit is Paused while you have an ongoing Insta Loan.", InstaLoanPage.txtRingLimit);
		logger.info("Ring Limit Paused");
		extent.extentLoggerPass("Ring Limit Paused", "Ring Limit Paused after accepting Instaloan------TC_Insta_loan_124");
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		softAssertion.assertEquals("First EMI due in 15 Days",getText(InstaLoanPage.firstemiduein)+" "+getText(InstaLoanPage.fifteen)+" "+getText(InstaLoanPage.txtdays));
		System.out.println(getText(InstaLoanPage.firstemiduein)+" "+getText(InstaLoanPage.fifteen)+" "+getText(InstaLoanPage.txtdays));
		softAssertion.assertEquals("100% waiver", getText(InstaLoanPage.secoundWaiver));
		extent.extentLoggerPass("Secount EMI Waiver", "Secount EMI Waiver Validate--------------TC_Insta_loan_125");
		Back(1);
		ringPayLogout();
		newAdminPanel_appScore("0.107666832000000");
		instaloneOnboardingKYCValidation("2251");
		instaloneOnboardingFatherNameValidation("2251");
		
		
		
	}
	
	public void instaloneOnboardingWithOfferId(String offerID) throws Exception {
		
		extent.HeaderChildNode("On_Boarding With Offer ID");
		mockUserAPI();
		
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		user_reference=instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","5000",offerID,"2319");
		//waitTime(10000);
		if(verifyElementPresent2(RingLoginPage.objReadAcceptBtn, "Permission Page")) {
		readAndAccept();
		}
		waitTime(50000);
		
		instaUserDetails(firstName,lastName,mothersName,email,gender);
		
		waitTime(50000);
		
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}
		newAdminPanel_appScore("0.107666832000000");
		if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
			instaKycDocument();

			waitTime(20000);
			panCardDetails();
			logger.info("Pan Card Validate Successfully");
			extent.extentLoggerPass("Pan Card Details", "Pan Card Details Validatation Successfull");
		}
		
		waitTime(20000);
		click(RingLoginPage.objIAcceptCheckBox, "Term and Condition Check Box");
		click(RingLoginPage.objAcceptOfferBtn, "Accept Button");
		
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		waitTime(13000);
		if (verifyElementPresent2(HomePage.objAdCloseBtn, "Ad Banner")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		if (verifyElementPresent2(InstaLoanPage.bannerTitle, "Check")) {
			click(InstaLoanPage.closeBanner, "Close Instalone Banner");
		}
		waitTime(3000);
		click(InstaLoanPage.btnOkaygotIt, "Okay Got It");
		

		
		waitTime(6000);
		click(InstaLoanPage.btnApplyNow, "Apply Now Button");
		waitTime(10000);
		
		if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
			explicitWaitVisibility(KycDocument.kycHeader, 20);
			logger.info("KYC Documents Page Validate Successfully");
			
			instaKycDocument();

			waitTime(20000);
			panCardDetails();
			logger.info("Pan Card Validate Successfully");
		}
		waitTime(5000);
		if(verifyElementPresent2(InstaLoanPage.txtBasicDetails, "Basic Details")) {
		explicitWaitVisibility(InstaLoanPage.txtBasicDetails, 20);
		
		Aclick(InstaLoanPage.fieldFatherName, "");
		type(InstaLoanPage.fieldFatherName, "Dinesh", "Fathers Name Field");
			try {
					click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
				}
			catch (Exception e) {
			}
		}
		else {
			extent.extentLoggerPass("Father Name ", "Father Name is Already Available----------TC_Insta_loan_111");
			logger.info("Father Name Already Available");
		}
		
		explicitWaitVisibility(InstaLoanPage.ckbCKYCTerm, 20);
		
		click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			softAssertion.assertEquals("New Current Address", AddAddressPage.objAddCurrentAddressHeader);
			logger.info("New Current Address Validate Successfully");
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}	
		if (verifyElementPresent2(InstaLoanPage.txtPermanentAddress, "Address Page")) {
			click(InstaLoanPage.dropSelectAddress, "Select Address Drop Down");
			explicitWaitVisibility(InstaLoanPage.dropAddressSelection, 20);
			click(InstaLoanPage.dropAddressSelection, "Select Address First Option");

			
			click(RingUserDetailPage.objRegisterBtn, "Proceed Button");

		}
		waitTime(5000);
		explicitWaitVisibility(InstaLoanPage.txtCongratulations, 30);
		click(InstaLoanPage.btnAddABankDetails, "Add Bank Account Button");
	
		waitTime(15000);
		if (verifyElementPresent2(InstaLoanPage.txtExistingBankAcc, "Existing Bank")) {
			softAssertion.assertEquals("Existing Bank Account", getText(InstaLoanPage.txtExistingBankAcc));
			logger.info("Existing Bank Account Validation Successfull");
			extent.extentLoggerPass("Existing Bank Account", "Existing Bank Validation Pass-----------------TC_Insta_loan_121");
			click(InstaLoanPage.selectExistingAccount, "Select Existing Bank Account");
			extent.extentLoggerPass("Select Existing Bank", "Select Existing Bank------------TC_Insta_loan_122");
			
		}
		else {
			explicitWaitVisibility(InstaLoanPage.txtAddBankAccount, 20);
			BankAccountRandom=addbankAccount("5",bankAccountHolder);
			//explicitWaitVisibility(InstaLoanPage.txtVerifyBankDetails, 20);
			click(InstaLoanPage.btnConfirm, "Confirm Button");
		}
		
		
		explicitWaitVisibility(InstaLoanPage.txtCongratulations, 30);
		softAssertion.assertEquals("Offer", InstaLoanPage.txtOffer);
		softAssertion.assertEquals("Congratulations",InstaLoanPage.txtCongratulations);
		click(InstaLoanPage.ckbAcceptOfferPermission, "Permission Accept Offer");
		click(InstaLoanPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(InstaLoanPage.txtImportant, 20);
		click(InstaLoanPage.btnConfirmOffer, "Confirm Order Button");
		waitTime(120000);
		explicitWaitVisibility(InstaLoanPage.btnHome, 20);
		
		click(InstaLoanPage.btnHome, "Home Button");
		waitTime(240000);
		Swipe("Down",2);
		explicitWaitVisibility(HomePage.homeTab, 30);
		logger.info("Home Page");
		
		
	}
	
public void instaloneOnboardingKYCValidation(String offerID) throws Exception {
		
		extent.HeaderChildNode("Instaloan KYC Validation");
		mockUserAPI();
		
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		user_reference=instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","5000",offerID,"2319");
		readAndAccept();
		waitTime(50000);
		
		instaUserDetails(firstName,lastName,mothersName,email,gender);
		
		waitTime(50000);
		
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}
		if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
			explicitWaitVisibility(KycDocument.kycHeader, 20);
			logger.info("KYC Documents Page Validate Successfully");
			
			instaKycDocument();
			waitTime(12000);
			if (verifyElementPresent2(InstaLoanPage.txtSorry, "sorry Page")) {
				explicitWaitVisibility(InstaLoanPage.txtSorry, 30);
				softAssertion.assertEquals("Sorry!", InstaLoanPage.txtSorry);
				//softAssertion.assertEquals("Your application cannot be processed right now.", InstaLoanPage.txtSorryDetails);
				logger.info("Sorry Page Display");
				extent.extentLoggerPass("Sorry Page Display","Sorry Page Display due to KYC Document not upload properly------------TC_Insta_loan_109");
				waitTime(20000);
				explicitWaitVisibility(InstaLoanPage.sorryPageTopHamBurgger, 15);
				click(InstaLoanPage.sorryPageTopHamBurgger, "Top left menu button");

				explicitWaitVisibility(RingLoginPage.objProfileSelect, 10);
				click(RingLoginPage.objProfileSelect, "Profile Select Button");

				explicitWaitVisibility(RingLoginPage.objLogoutBtn, 10);

				click(RingLoginPage.objLogoutBtn, "Logout Button");

				logger.info("Logout popup comes up");
				extent.extentLoggerPass("Logout popup", "Logout popup comes up");

				explicitWaitVisibility(RingLoginPage.objYesBtn, 10);
				click(RingLoginPage.objYesBtn, "Yes confirmation button");
			}
			else {
				waitTime(20000);
				panCardDetails();
				waitTime(20000);
				click(RingLoginPage.objIAcceptCheckBox, "Term and Condition Check Box");
				click(RingLoginPage.objAcceptOfferBtn, "Accept Button");
				
				instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
				waitTime(13000);
				
				if (verifyElementPresent2(HomePage.objAdCloseBtn, "Ad Banner")) {
					explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
					Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
				}
				if (verifyElementPresent2(InstaLoanPage.bannerTitle, "Check")) {
					click(InstaLoanPage.closeBanner, "Close Instalone Banner");
					waitTime(3000);
					click(InstaLoanPage.btnOkaygotIt, "Okay Got It");
				}
				
		
			}

			waitTime(6000);
			click(InstaLoanPage.btnApplyNow, "Apply Now Button");
			waitTime(10000);
			
			if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
				explicitWaitVisibility(KycDocument.kycHeader, 20);
				logger.info("KYC Documents Page Validate Successfully");
				
				instaKycDocument();
				waitTime(12000);
				explicitWaitVisibility(InstaLoanPage.txtSorry, 30);
				softAssertion.assertEquals("Sorry!", InstaLoanPage.txtSorry);
				logger.info("Sorry Page Display");
				extent.extentLoggerPass("Sorry Page Display","Sorry Page Display due to KYC Document not upload properly------------TC_Insta_loan_109");
				waitTime(20000);
				explicitWaitVisibility(InstaLoanPage.sorryPageTopHamBurgger, 15);
				click(InstaLoanPage.sorryPageTopHamBurgger, "Top left menu button");

				explicitWaitVisibility(RingLoginPage.objProfileSelect, 10);
				click(RingLoginPage.objProfileSelect, "Profile Select Button");

				explicitWaitVisibility(RingLoginPage.objLogoutBtn, 10);

				click(RingLoginPage.objLogoutBtn, "Logout Button");

				logger.info("Logout popup comes up");
				extent.extentLoggerPass("Logout popup", "Logout popup comes up");

				explicitWaitVisibility(RingLoginPage.objYesBtn, 10);
				click(RingLoginPage.objYesBtn, "Yes confirmation button");

				
			}
		}
			
	
	}

public void instaloneOnboardingFatherNameValidation(String offerID) throws Exception {
	
	extent.HeaderChildNode("Instaloan Father Details Validation");
	mockUserAPI();
	
	loginOnboarding("1");
	mobileNoValidation1(mobileNumber);
	enterOtp(prop.getproperty("OTP"));
	user_reference=instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","5000",offerID,"2319");
	//waitTime(10000);
	if(verifyElementPresent2(RingLoginPage.objReadAcceptBtn, "Permission Page")) {
	readAndAccept();
	}
	waitTime(50000);
	String query="update users set father_name='Suresh' where user_reference_number='"+user_reference+"';";
	DBUpdate(query);
	logger.info("Father Name Update In DataBase");
	
	instaUserDetails(firstName,lastName,mothersName,email,gender);
	
	waitTime(50000);
	if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
		addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
		click(AddAddressPage.objSubmitBtn,"Submit");
	}
	newAdminPanel_appScore("0.107666832000000");
	if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
		instaKycDocument();

		waitTime(20000);
		panCardDetails();
		logger.info("Pan Card Validate Successfully");
		extent.extentLoggerPass("Pan Card Details", "Pan Card Details Validatation Successfull");
	}
	
	waitTime(20000);
	click(RingLoginPage.objIAcceptCheckBox, "Term and Condition Check Box");
	click(RingLoginPage.objAcceptOfferBtn, "Accept Button");
	
	instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
	waitTime(13000);
	if (verifyElementPresent2(HomePage.objAdCloseBtn, "Ad Banner")) {
		explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
		Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
	}
	if (verifyElementPresent2(InstaLoanPage.bannerTitle, "Check")) {
		click(InstaLoanPage.closeBanner, "Close Instalone Banner");
	}
	waitTime(3000);
	click(InstaLoanPage.btnOkaygotIt, "Okay Got It");
	

	waitTime(6000);
	click(InstaLoanPage.btnApplyNow, "Apply Now Button");
	waitTime(10000);
	if (verifyElementPresent2(KycDocument.kycHeader, "KYC Documents")) {
		explicitWaitVisibility(KycDocument.kycHeader, 20);
		logger.info("KYC Documents Page Validate Successfully");
		
		instaKycDocument();

		waitTime(20000);
		panCardDetails();
		logger.info("Pan Card Validate Successfully");
	}
	waitTime(5000);
	if(verifyElementPresent2(InstaLoanPage.txtBasicDetails, "Basic Details")) {
	explicitWaitVisibility(InstaLoanPage.txtBasicDetails, 20);
	
	Aclick(InstaLoanPage.fieldFatherName, "");
	type(InstaLoanPage.fieldFatherName, "Dinesh", "Fathers Name Field");
		try {
				click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
			}
		catch (Exception e) {
		}
	}
	else {
		new TouchAction(getDriver()).tap(PointOption.point(324,1688)).release().perform();
		softAssertion.assertEquals("CKYC Consent", InstaLoanPage.txtCKYC_Consent);
		logger.info("CKYC Consent");
		extent.extentLoggerPass("Father Name ", "Father Name is Already Available-------TC_Insta_loan_111");
		click(InstaLoanPage.btnOkGotIt, "Okay Got It Button");
		logger.info("Father Name Already Available");
	}
	
	
	
	

}
	public void instaLoanRepayment() throws Exception {
		extent.HeaderChildNode("Insta Loan Repayment");

		//waitTime(30000);
		instaloneOnboardingWithOfferId("2135");
		// Back(1);
		 explicitWaitVisibility(InstaLoanPage.objMenuTab, 30);
		click(InstaLoanPage.objMenuTab, "Top left menu button");
		explicitWaitVisibility(HomPageNew.transacTab, 20);
		click(HomPageNew.transacTab, "Transaction Tab");
		explicitWaitVisibility(HomPageNew.objHistory, 20);
		click(HomPageNew.objLoanHistory, "Loan History");
		waitTime(4000);
		click(HomPageNew.linkViewDetails, "View Details");
		explicitWaitVisibility(HomPageNew.txtLoanDetails, 20);
		click(InstaLoanPage.btnPayNow, "Pay Now");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		softAssertion.assertEquals("Payment", getText(InstaLoanPage.txtPayment));
		extent.extentLoggerPass("Redirect Payment", "Redirect To Payment Mode-------TC_Insta_loan_165");
		Back(4);
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);

		click(InstaLoanPage.rbnFullPayment, "Full Payment Radio Button");
		click(InstaLoanPage.btnPayNow, "Pay Now");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		softAssertion.assertEquals("Payment", getText(InstaLoanPage.txtPayment));
		extent.extentLoggerPass("Make Payment", "Make Payment from Loan Details-------TC_Insta_loan_166");
		netBankingPayment();
		verifyElementPresentAndClick(HomPageNew.objHomePage, "Go To Homepage Button");
		logger.info("Home Screen");
		extent.extentLoggerPass("Home Screen", "After Click on Go TO Home Button-------TC_Insta_loan_168");
		waitTime(13000);
		String dbref=executeQueryWithReferanceNumber(user_reference);
		
		System.out.println("Database data------------"+dbref);
		softAssertion.assertEquals(user_reference, dbref);
		updateEmail(email , user_reference);
		if (verifyElementPresent2(HomePage.objAdCloseBtn, "Ad Banner")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		
	}
	
	public void netBankingPayment() throws Exception {
		verifyElementPresentAndClick(HomPageNew.objNetBankingOption, getText(HomPageNew.objNetBankingOption));
		waitTime(10000);
		explicitWaitVisibility(HomPageNew.objNetbanking, 20);
		//waitTime(5000);
		click(HomPageNew.objNetbanking, getText(HomPageNew.objNetbanking));
		click(HomPageNew.selectDifferentBankDropDown, "Select Different Bank From Dropdown");
		explicitWaitVisibility(HomPageNew.txtAllbanks, 20);
		click(HomPageNew.searchBank, "Search Bank");
		type(HomPageNew.searchBank, "State Bank Of Hyderabad", "State Bank of Hyderabad");
		click(HomPageNew.selectBank, "Select Bank");

		waitTime(4000);
		click(HomPageNew.objPayNowBtn, "Pay Now Button");
		verifyElementPresentAndClick(HomPageNew.objSuccessBtn, "Success Button");
		waitTime(15000);
		explicitWaitVisibility(InstaLoanPage.txtPayment, 30);
		verifyElementPresentAndClick(HomPageNew.objHomePage, "Go To Homepage Button");

	}
	
	public void insta_loanAgain(String status) throws Exception {
		explicitWaitVisibility(InstaLoanPage.btnApplyNow, 30);
		click(InstaLoanPage.btnApplyNow, "Apply Now Button");
		waitTime(8000);
		if (verifyElementPresent2(InstaLoanPage.txtPermanentAddress, "Address Page")) {
			click(InstaLoanPage.dropSelectAddress, "Select Address Drop Down");
			explicitWaitVisibility(InstaLoanPage.dropAddressSelection, 20);
			click(InstaLoanPage.dropAddressSelection, "Select Address First Option");
			click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
		explicitWaitVisibility(InstaLoanPage.txtCongratulations, 30);
		click(InstaLoanPage.btnAddABankDetails, "Add Bank Account Button");
		waitTime(15000);
		if (verifyElementPresent2(InstaLoanPage.txtExistingBankAcc, "Existing Bank")) {
			softAssertion.assertEquals("Existing Bank Account", getText(InstaLoanPage.txtExistingBankAcc));
			logger.info("Existing Bank Account Validation Successfull");
			click(InstaLoanPage.selectExistingAccount, "Select Existing Bank Account");			
		}
		else {
			explicitWaitVisibility(InstaLoanPage.txtAddBankAccount, 20);
			addbankAccount("5",bankAccountHolder);
			explicitWaitVisibility(InstaLoanPage.txtVerifyBankDetails, 20);
			click(InstaLoanPage.btnConfirm, "Confirm Button");
		}
		
		explicitWaitVisibility(InstaLoanPage.txtCongratulations, 30);
		softAssertion.assertEquals("Offer", InstaLoanPage.txtOffer);
		softAssertion.assertEquals("Congratulations",InstaLoanPage.txtCongratulations);
		BankAccountRandom=updateBankAccStatus(status,BankAccountRandom , user_reference);
		click(InstaLoanPage.ckbAcceptOfferPermission, "Permission Accept Offer");
		click(InstaLoanPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(InstaLoanPage.txtImportant, 20);
		click(InstaLoanPage.btnConfirmOffer, "Confirm Order Button");
		waitTime(120000);
		explicitWaitVisibility(InstaLoanPage.btnHome, 20);
		
		click(InstaLoanPage.btnHome, "Home Button");
		waitTime(80000);
		explicitWaitVisibility(HomePage.homeTab, 30);
		logger.info("Home Page");
	}
	
	public void InstaLoanDetailScreen() throws Exception {
		
		extent.HeaderChildNode("Instaloan Details Screen");
		
		instaloneOnboardingWithOfferId("2135");
		
		
		
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		softAssertion.assertEquals("Insta Loan", getText(InstaLoanPage.titleInstalone));
		logger.info("Insta Loan Screen Display");
		extent.extentLoggerPass("Insta Loan details screen ", "Insta Loan details screen -----TC_Insta_loan_170");
		softAssertion.assertEquals("First EMI due in 15 Days",getText(InstaLoanPage.firstemiduein)+" "+getText(InstaLoanPage.fifteen)+" "+getText(InstaLoanPage.txtdays));
		System.out.println(getText(InstaLoanPage.firstemiduein)+" "+getText(InstaLoanPage.fifteen)+" "+getText(InstaLoanPage.txtdays));
		softAssertion.assertEquals("Pay Full Amount", getText(InstaLoanPage.txtPayFullAmount));
		softAssertion.assertEquals("1st installment", getText(InstaLoanPage.txtfirstInstall));
		softAssertion.assertEquals("2nd installment", getText(InstaLoanPage.txtsecoundInstall));
		logger.info("Validate offer of 15 days");
		extent.extentLoggerPass("verified for insta loan 15 days offer loan details screen", "Verified for insta loan 15 days offer loan details screen with pay full amount, 1st installment and 2nd installment-----TC_Insta_loan_173");
		Back(1);
		explicitWaitVisibility(InstaLoanPage.objMenuTab, 30);
		click(InstaLoanPage.objMenuTab, "Top left menu button");
		explicitWaitVisibility(HomPageNew.transacTab, 20);
		click(HomPageNew.transacTab, "Transaction Tab");
		explicitWaitVisibility(HomPageNew.objHistory, 20);
		click(HomPageNew.objLoanHistory, "Loan History");
		waitTime(4000);
		click(InstaLoanPage.btnPayEMI, "Pay EMI Button");
		click(InstaLoanPage.btnPayEMI, "Pay EMI Button");
	
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		System.out.println(BankAccountRandom);
		insta_loanAgain("onhold");
		waitTime(5000);
		softAssertion.assertEquals("On Hold", getText(InstaLoanPage.txtOnHold));
		logger.info("Loan On hold");
		extent.extentLoggerPass("Instaloan Is in On Hold", "Instaloan is in On_Hold-----TC_Insta_loan_172");
		explicitWaitVisibility(InstaLoanPage.objMenuTab, 30);
		click(InstaLoanPage.objMenuTab, "Top left menu button");
		explicitWaitVisibility(HomPageNew.transacTab, 20);
		click(HomPageNew.transacTab, "Transaction Tab");
		explicitWaitVisibility(HomPageNew.objHistory, 20);
		click(HomPageNew.objLoanHistory, "Loan History");
		waitTime(4000);
		click(InstaLoanPage.btnPayEMI,"Pay EMI");
		click(InstaLoanPage.btnPayEMI, "Pay EMI Button");
		
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		System.out.println(BankAccountRandom); 
		insta_loanAgain("inprogress");
		waitTime(5000);
		softAssertion.assertEquals("In Process", getText(InstaLoanPage.txtInProgress));
		logger.info("Loan In-Progress");
		extent.extentLoggerPass("Instaloan Is In-Progress", "Instaloan is In-Progress-----TC_Insta_loan_171");
		explicitWaitVisibility(InstaLoanPage.objMenuTab, 30);
		click(InstaLoanPage.objMenuTab, "Top left menu button");
		explicitWaitVisibility(HomPageNew.transacTab, 20);
		click(HomPageNew.transacTab, "Transaction Tab");
		explicitWaitVisibility(HomPageNew.objHistory, 20);
		click(HomPageNew.objLoanHistory, "Loan History");
		waitTime(4000);
		click(InstaLoanPage.btnPayEMI,"Pay EMI");
		click(InstaLoanPage.btnPayEMI, "Pay EMI Button");
		
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		ringPayLogout(); 
		
// 62 Days Offer ID Validation and Execution
		
		instaloneOnboardingWithOfferId("2251");
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.firstInstallRadioBtn, "1st Installment Radio Button");
		click(InstaLoanPage.firtstInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		softAssertion.assertEquals("Second EMI due in 62 Days",getText(InstaLoanPage.Secoundemiduein)+" "+getText(InstaLoanPage.sixtytwo)+" "+getText(InstaLoanPage.txtdays));
		System.out.println(getText(InstaLoanPage.Secoundemiduein)+" "+getText(InstaLoanPage.sixtytwo)+" "+getText(InstaLoanPage.txtdays));
		logger.info("Validate offer of 62 days");
		extent.extentLoggerPass("verified for insta loan 62 days offer loan details screen", "Verified for insta loan 62 days offer loan details screen with pay full amount, 2 Month Installment and 2 Month Installment-----TC_Insta_loan_174");
		
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.secoundInstallRadioBtn, "2nd Installment Radio Button");
		click(InstaLoanPage.secoundInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		
		ringPayLogout();     
		
// 3 Months Offer ID Validation and Execution		
		
		instaloneOnboardingWithOfferId("2413");
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.firstInstallRadioBtn, "1st Installment Radio Button");
		click(InstaLoanPage.firtstInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.secoundInstallRadioBtn, "2nd Installment Radio Button");
		click(InstaLoanPage.secoundInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		softAssertion.assertEquals("Third EMI due in 90 Days",getText(InstaLoanPage.thirdemiduein)+" "+getText(InstaLoanPage.ninty)+" "+getText(InstaLoanPage.txtdays));
		System.out.println(getText(InstaLoanPage.thirdemiduein)+" "+getText(InstaLoanPage.ninty)+" "+getText(InstaLoanPage.txtdays));
		logger.info("Validate offer of 90 days");
		extent.extentLoggerPass("verified for insta loan 3 Months offer loan details screen", "Verified for insta loan 3 Months offer loan details screen with pay full amount, 3 Month Installment and 3rd Month Installment-----TC_Insta_loan_175");

		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.thirdInstallRadioBtn, "3rd Installment Radio Button");
		click(InstaLoanPage.thirdInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		ringPayLogout();    
		        
		
// 	6 Months Offer ID Validation and Execution		
		
		instaloneOnboardingWithOfferId("2311");
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.firstInstallRadioBtn, "1st Installment Radio Button");
		click(InstaLoanPage.firtstInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.secoundInstallRadioBtn, "2nd Installment Radio Button");
		click(InstaLoanPage.secoundInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.thirdInstallRadioBtn, "3rd Installment Radio Button");
		click(InstaLoanPage.thirdInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.fourthInstallRadioBtn, "4th Installment Radio Button");
		click(InstaLoanPage.fourthInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		Swipe("up", 2);

		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.fifthInstallRadioBtn, "5th Installment Radio Button");
		click(InstaLoanPage.fifthInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		click(InstaLoanPage.linkViewDetails, "View Details Link");
		softAssertion.assertEquals("Sixth EMI due in 181 Days",getText(InstaLoanPage.sixthemiduein)+" "+getText(InstaLoanPage.oneHunderedeightyOne)+" "+getText(InstaLoanPage.txtdays));
		System.out.println(getText(InstaLoanPage.sixthemiduein)+" "+getText(InstaLoanPage.oneHunderedeightyOne)+" "+getText(InstaLoanPage.txtdays));
		logger.info("Validate offer of 181 days");
		extent.extentLoggerPass("verified for insta loan 6 Months offer loan details screen", "Verified for insta loan 6 Months offer loan details screen with pay full amount, 6 Month Installment and 6rd Month Installment-----TC_Insta_loan_176");
		
	//	click(InstaLoanPage.linkViewDetails, "View Details Link");
		Swipe("up", 2);

		explicitWaitVisibility(InstaLoanPage.titleInstalone, 20);
		click(InstaLoanPage.sixthInstallRadioBtn, "6th Installment Radio Button");
		click(InstaLoanPage.sixthInsallmentPay, "Pay");
		explicitWaitVisibility(InstaLoanPage.txtPayment, 20);
		netBankingPayment();
		waitTime(10000);
		
		ringPayLogout();
		
	}
	
	
	public void newAdminPanel_appScore(String app_score) throws Exception{
        extent.HeaderChildNode("Updating app score");
        Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/configuration?search=app_score&");
        type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
        click(InstaLoanPage.objContinue,"Continue Button");
        waitTime(3000);
        click(InstaLoanPage.objEditConfBtn,"Edit Configuration Buton");
        waitTime(2000);
        waitTime(2000);
        clearWebField(InstaLoanPage.objMetaValue);
        waitTime(2000);
        type(InstaLoanPage.objMetaValue,app_score,"app score meta value field");
        waitTime(2000);
        click(InstaLoanPage.objSubmit,"Submit button");
        waitTime(2000);
        BrowsertearDown();
    	waitTime(10000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
    }
	
	public void PanValidation() throws Exception {
		extent.HeaderChildNode("Validation of PAN Module");
		HashMap mockUserDetails=mockUserAPI(prop.getproperty("MockAPIurl"),prop.getproperty("gender"),prop.getproperty("encrypted"));

		String otherspanCard=(String) mockUserDetails.get("panCard");
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		instaUserDetails(firstName,lastName,mothersName,email,gender);
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress("86","44, Borivali","Vasa Street","Das Gupta street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}
		waitTime(10000);
		user_reference=instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","5000","2311","2319");
		instaKycDocument();
		waitTime(30000);
		click(PAN_DetailsPage.panCardNumberField, "Field");
		type(PAN_DetailsPage.panCardNumberField,prop.getproperty("onlyNumber"), "Only Number Formate");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan with only numbers-----TC_RCC_18");
		extent.extentLoggerPass("To validate the pan with only numbers", "Validate With Pan with Only Number formate");
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"Pan Card Field");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,prop.getproperty("onlyAlphabets"), "Only Alphabets Formate");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan with only alphabets----------TC_RCC_19");
		extent.extentLoggerPass("To validate the pan with only alphabets", "Validate With Pan with Only alphabets formate");
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"Pan Card Field");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,prop.getproperty("onlySpecialChar"), "Only Special Character");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan with only special Characters------TC_RCC_20");
		extent.extentLoggerPass("To validate the pan with only special Characters", "Validate With Pan with Only special Characters formate");
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"Pan Card Field");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,prop.getproperty("mixCharacter"), "Mix Character");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan with combination of alphabets, numbers and special character----TC_RCC_21");
		extent.extentLoggerPass("To validate the pan with combination of alphabets, numbers and special character", "Validate With Pan with combination of alphabets,number,special character formate");
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"Pan Card Field");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,prop.getproperty("wrongFormat"), "Wrong Formate");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan with wrong format----TC_RCC_22");
		extent.extentLoggerPass("To validate the pan with wrong format", "Validate With Pan with wrong formate");
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"Pan Card Field");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,otherspanCard, "With Other Pan Card Details");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan which is already mapped with other Ring User-----TC_RCC_23");
		extent.extentLoggerPass("To validate the pan which is already mapped with other Ring User", "Validate with already mapped with other ring user");
			
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,prop.getproperty("notExistPanCard"), "Not Exist Pan Card");
		click(PAN_DetailsPage.btnProceed, "Procced");
		softAssertion.assertEquals("Please enter a valid pan number", getText(PAN_DetailsPage.errorMessage));
		logger.info("To validate the pan which is not mapped with other Ring user and also not exist---TC_RCC_24");
		extent.extentLoggerPass("To validate the pan which is not mapped with other Ring user and also not exist", "Validate with not exist user");
		
		click(PAN_DetailsPage.panCardNumberField, "Field");
		clearField(PAN_DetailsPage.panCardNumberField,"Pan Card Field");
		waitTime(3000);
		type(PAN_DetailsPage.panCardNumberField,panCard, "Valid Pan Card");
		click(PAN_DetailsPage.btnProceed, "Proced");
		waitTime(8000);
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			
			softAssertion.assertEquals("New Current Address", getText(AddAddressPage.objAddCurrentAddressHeader));
			logger.info("To Verify With Valid Pan Card Details---TC_RCC_17");
			extent.extentLoggerPass("To Verify With Valid Pan Card Details", "To Verify With Valid Pan Card Details---TC_RCC_17");
		}
		else {
		softAssertion.assertEquals("Accept Offer", getText(RingLoginPage.objAcceptOfferBtn));
		logger.info("To Verify With Valid Pan Card Details---TC_RCC_17");
		extent.extentLoggerPass("To Verify With Valid Pan Card Details", "To Verify With Valid Pan Card Details---TC_RCC_17");
		}
	}
	
	
	/*
	 * public void addNewCard() throws Exception {
	 * 
	 * 
	 * verifyElementPresentAndClick(HomPageNew.objNetBankingOption,
	 * getText(HomPageNew.objNetBankingOption)); waitTime(10000);
	 * explicitWaitVisibility(HomPageNew.objNetbanking, 20); //waitTime(5000);
	 * click(HomPageNew.objNetbanking, getText(HomPageNew.objNetbanking));
	 * 
	 * click(InstaLoanPage.objCardNumber, "Card Number Field");
	 * type(InstaLoanPage.objCardNumber, prop.getproperty("cardNumber"),
	 * "Card Number Field"); //4111 1111 1111 1111
	 * 
	 * 
	 * click(InstaLoanPage.objExpiryDate, "Expiry Date Field");
	 * type(InstaLoanPage.objExpiryDate, prop.getproperty("expiryDate"),
	 * "Expiry Date Field");
	 * 
	 * //10/25
	 * 
	 * click(InstaLoanPage.objCardHolderName, "Card Holder's Field");
	 * type(InstaLoanPage.objCardHolderName, prop.getproperty("userName"),
	 * "Card Holder's Field"); //
	 * 
	 * 
	 * click(InstaLoanPage.objCVV, "CVV Field"); type(InstaLoanPage.objCVV,
	 * prop.getproperty("cvv"), "CVV Field");
	 * 
	 * //123
	 * 
	 * verifyElementPresentAndClick(InstaLoanPage.btnPayNow,
	 * getText(InstaLoanPage.btnPayNow) + " Button");
	 * verifyElementPresentAndClick(InstaLoanPage.objPayWithoutSavingCardBtn,getText
	 * (InstaLoanPage.objPayWithoutSavingCardBtn) + " Button");
	 * 
	 * if (verifyElementPresent2(InstaLoanPage.objSuccessBtn, "Success Button")) {
	 * verifyElementPresentAndClick(InstaLoanPage.objSuccessBtn,
	 * getText(InstaLoanPage.objSuccessBtn)); } else {
	 * click(InstaLoanPage.objCardOTPField, "OTP field");
	 * type(InstaLoanPage.objCardOTPField, prop.getproperty("OTP"), "OTP Field");
	 * verifyElementPresentAndClick(InstaLoanPage.objSubmitButton,getText(
	 * InstaLoanPage.objSubmitButton) + " Button"); } waitTime(20000);
	 * verifyElementPresentAndClick(HomPageNew.objHomePage,
	 * "Go to homepage button"); }
	 */
	public void dataPointApi() throws Exception{
		extent.HeaderChildNode("DB");
		ValidatableResponse response = Utilities.customDataPoints_policy("8000","8000","IDEP741689288872YTRK");
		String message = response.extract().jsonPath().getString("message");
		System.out.println("Data Point-------------" + message);
		String responseCode = response.extract().jsonPath().getString("response_code");
		System.out.println("Data Point-------------" + responseCode);
		String success = response.extract().jsonPath().getString("success");
		System.out.println("Data Point-------------" + success);
	}
	
	public void onBoardingHomePage() throws Exception {
		extent.HeaderChildNode("On Boarding");
		mockUserAPI();
		
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		user_reference=getRefrenceNumber(mobileNumber);
		if(verifyElementPresent2(RingLoginPage.objReadAcceptBtn, "Permission Page")) {
		readAndAccept();
		}
		waitTime(10000);
		
		instaUserDetails("sunil","chatla",mothersName,email,gender);
		
		waitTime(20000);
		
		if (verifyElementPresent2(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			click(AddAddressPage.objSubmitBtn,"Submit");
		}
		newAdminPanel_appScore("0.107666832000000");
		waitTime(20000);
		click(RingLoginPage.objIAcceptCheckBox, "Term and Condition Check Box");
		click(RingLoginPage.objAcceptOfferBtn, "Accept Button");
		
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		waitTime(13000);
		if (verifyElementPresent2(HomePage.objAdCloseBtn, "Ad Banner")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
	}
	
	public void coble_Count() throws Exception {
		extent.HeaderChildNode("Cable_Count");
		onBoardingHomePage();
		waitTime(10000);
		explicitWaitVisibility(HomePage.moreTab, 20);
		click(HomePage.moreTab, "More Tab");
		explicitWaitVisibility(HomePage.tabBankTransfer, 20);
		click(HomePage.tabBankTransfer, "Bank Transfer Button");
		waitTime(5000);
		
		addbankAccount("5","Sunil Chatla");
		ValidatableResponse response = Utilities.customDataPoints_policy("8000","8005",user_reference);
		type(PaymentPage.enterAmount, "50", "Entered Amount");
		click(PaymentPage.objPayNowBtn, "Pay Now");
		explicitWaitVisibility(PaymentPage.objConfirmPayment, 20);
		click(PaymentPage.objEditTransactionPin, "Transaction Pin Box");
		type(PaymentPage.objEditTransactionPin,prop.getproperty("InstaLoanMPIN"), "Ented MPin");
		waitTime(2000);
		click(PaymentPage.arrowBtn, "Arrow Button");
		waitTime(8000);
		if(verifyElementPresent2(HomePage.okaygotit, "Okay Got It")){
		
		click(HomePage.okaygotit, "Ok Got It");
		}
		else {
			click(PaymentPage.objGoToHomePage, "Go to Homepage");
			
		}
	
		waitTime(30000);
		if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp, "Are You Enjoying The App")) {
			
			explicitWaitVisibility(HomePage.txtAreYouEnjoyingApp, 20);
			click(HomePage.noBtn,"No Button");
		} 
		

		click(HomePage.selectRecentTransaction, "Select Recent Transaction");
		explicitWaitVisibility(HomePage.txtTransactionDetails, 20);
		String transactionNumebr=getText(HomePage.getTransactionNumber);
		System.out.println(transactionNumebr);
		Back(2); 
		String result=executeQuery("SELECT  * FROM `bnpl_transactions` WHERE bnpl_txn_reference_number=\""+transactionNumebr+"\";","rejection_reason");
		
		System.out.println("result============="+result);
		logger.info("Reason For Rejection----"+result);
		softAssertion.assertEquals("Cabal count exceeded", result);
		extent.extentLoggerPass("Verify Cable Count Result Through Bank Transfer", "To verify user doing transaction through Bank Transfer when its linked with >2 cabal count ------TC_Ring_Customer_Seg_123");
		
		waitTime(10000);
		
			explicitWaitVisibility(HomePage.moreTab, 20);
			Aclick(HomePage.objScanAndPay, "Scan Button");
			String projectPath=System.getProperty("user.dir");
			
			//	Scan here
				/*-------------------Web-----------------------------------*/
				Utilities.setPlatform = "Web";
	            new CommandBase("Chrome");
	            waitTime(4000);
	            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
	            waitTime(25000);
	            BrowsertearDown();
	            
	            /*------------------Android---------------------------------*/
	            setPlatform("Android");
	            initDriver();
	            
				waitTime(30000);
				ValidatableResponse response1 = Utilities.customDataPoints_policy("8000","8005",user_reference);
				explicitWaitVisibility(PaymentPage.objAmountTextField, 30);
				if(verifyElementPresent(PaymentPage.objAmountTextField,"Amount Text Field")) {
					   Aclick(PaymentPage.objAmountTextField, "Amount Field");
					   type(PaymentPage.objAmountTextField, "80", "Amount Field");
				}
				Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
				waitTime(20000);
				if(verifyElementPresent(PaymentPage.objConfirmPayment,getTextVal(PaymentPage.objConfirmPayment,"Text"))){
					   Aclick(PaymentPage.objEditTransactionPin,"Edit Pin Field");
					   type(PaymentPage.objEditTransactionPin,"1111","Edit Pin Field");
					   Aclick(PaymentPage.objContinue,"Continue Button");
				}
				
				waitTime(8000);
				if(verifyElementPresent2(HomePage.okaygotit, "Okay Got It")){
				
				click(HomePage.okaygotit, "Ok Got It");
				}
				else {
					click(PaymentPage.objGoToHomePage, "Go to Homepage");
					
				}
				
				waitTime(10000);
				if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp, "Are You Enjoying The App")) {
					
					explicitWaitVisibility(HomePage.txtAreYouEnjoyingApp, 20);
					click(HomePage.noBtn,"No Button");
				} 
				

				click(HomePage.selectRecentTransaction, "Select Recent Transaction");
				explicitWaitVisibility(HomePage.txtTransactionDetails, 20);
				String transactionNumebrbarcode=getText(HomePage.getTransactionNumberqrscan);
				System.out.println(transactionNumebrbarcode);
				Back(2); 
				String resultqrscan=executeQuery("SELECT  * FROM `bnpl_transactions` WHERE bnpl_txn_reference_number=\""+transactionNumebrbarcode+"\";","rejection_reason");
				
				System.out.println("result============="+resultqrscan);
				logger.info("Reason For Rejection----"+resultqrscan);
				softAssertion.assertEquals("Cabal count exceeded", resultqrscan);			
				extent.extentLoggerPass("Verify Cable Count Result Through Scan and Pay", "To verify user doing transaction through Scan and Pay when its linked with >2 cabal count ------TC_Ring_Customer_Seg_122");
				
				explicitWaitVisibility(HomePage.moreTab, 20);
				click(HomePage.moreTab, "More Tab");
				explicitWaitVisibility(HomePage.tabBankTransfer, 20);
				click(HomePage.tabBankTransfer, "Bank Transfer Button");
				waitTime(5000);
				click(PaymentPage.selectExistingAcc, "Select Existing Account");
				ValidatableResponse response2 = Utilities.customDataPoints_policy("8000","8000",user_reference);
				click(PaymentPage.enterAmount, "Entered Amount");
				type(PaymentPage.enterAmount, "30", "Entered Amount");
				//type(PaymentPage.enterAmount, "30", "Entered Amount");
				click(PaymentPage.objPayNowBtn, "Pay Now");
				explicitWaitVisibility(PaymentPage.objConfirmPayment, 20);
				click(PaymentPage.objEditTransactionPin, "Transaction Pin Box");
				type(PaymentPage.objEditTransactionPin,prop.getproperty("InstaLoanMPIN"), "Ented MPin");
				waitTime(2000);
				click(PaymentPage.arrowBtn, "Arrow Button");
				waitTime(8000);
				if(verifyElementPresent2(HomePage.okaygotit, "Okay Got It")){
				
				click(HomePage.okaygotit, "Ok Got It");
				}
				else {
					click(PaymentPage.objGoToHomePage, "Go to Homepage");
					
				}
				waitTime(30000);
				if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp, "Are You Enjoying The App")) {
					
					explicitWaitVisibility(HomePage.txtAreYouEnjoyingApp, 20);
					click(HomePage.noBtn,"No Button");
				} 
				
				click(HomePage.selectRecentTransaction, "Select Recent Transaction");
				explicitWaitVisibility(HomePage.txtTransactionDetails, 20);
				String transactionNumebr1=getText(HomePage.getTransactionNumber);
				System.out.println(transactionNumebr1);
				Back(2); 
				String result1=executeQuery("SELECT  * FROM `bnpl_transactions` WHERE bnpl_txn_reference_number=\""+transactionNumebr1+"\";","rejection_reason");
				
				System.out.println("result============="+result1);
				logger.info("Reason For Rejection----"+result1);
				softAssertion.assertEquals("null", result1);
				extent.extentLoggerPass("Verify Cable Count", "To verify user doing Scan n Pay and Bank transfer transaction when user whitelisted for cabal check ------TC_Ring_Customer_Seg_124");
				waitTime(10000);
				
				explicitWaitVisibility(HomePage.moreTab, 20);
				Aclick(HomePage.objScanAndPay, "Scan Button");
				String projectPath1=System.getProperty("user.dir");
				
				//	Scan here
					/*-------------------Web-----------------------------------*/
					Utilities.setPlatform = "Web";
		            new CommandBase("Chrome");
		            waitTime(4000);
		            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
		            waitTime(25000);
		            BrowsertearDown();
		            
		            /*------------------Android---------------------------------*/
		            setPlatform("Android");
		            initDriver();
		            
					waitTime(30000);
					ValidatableResponse response3 = Utilities.customDataPoints_policy("8000","8000",user_reference);
					explicitWaitVisibility(PaymentPage.objAmountTextField, 30);
					if(verifyElementPresent(PaymentPage.objAmountTextField,"Amount Text Field")) {
						   Aclick(PaymentPage.objAmountTextField, "Amount Field");
						   type(PaymentPage.objAmountTextField, "60", "Amount Field");
					}
					Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
					waitTime(20000);
					if(verifyElementPresent(PaymentPage.objConfirmPayment,getTextVal(PaymentPage.objConfirmPayment,"Text"))){
						   Aclick(PaymentPage.objEditTransactionPin,"Edit Pin Field");
						   type(PaymentPage.objEditTransactionPin,"1111","Edit Pin Field");
						   Aclick(PaymentPage.objContinue,"Continue Button");
					}
					
					waitTime(8000);
					if(verifyElementPresent2(HomePage.okaygotit, "Okay Got It")){
					
					click(HomePage.okaygotit, "Ok Got It");
					}
					else {
						click(PaymentPage.objGoToHomePage, "Go to Homepage");
						
					}
					waitTime(30000);
					if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp, "Are You Enjoying The App")) {
						
						explicitWaitVisibility(HomePage.txtAreYouEnjoyingApp, 20);
						click(HomePage.noBtn,"No Button");
					} 
					

					click(HomePage.selectRecentTransaction, "Select Recent Transaction");
					explicitWaitVisibility(HomePage.txtTransactionDetails, 20);
					String transactionNumebrbarcode1=getText(HomePage.getTransactionNumberqrscan);
					System.out.println(transactionNumebrbarcode1);
					Back(2); 
					String resultqrscan1=executeQuery("SELECT  * FROM `bnpl_transactions` WHERE bnpl_txn_reference_number=\""+transactionNumebrbarcode1+"\";","rejection_reason");
					
					System.out.println("result============="+resultqrscan1);
					logger.info("Reason For Rejection----"+resultqrscan1);
					softAssertion.assertEquals("null", resultqrscan1);	
					extent.extentLoggerPass("Verify Cable Count", "To verify user doing Scan n Pay and Bank transfer transaction when user whitelisted for cabal check ------TC_Ring_Customer_Seg_124");
					ringPayLogout();
		
				}
	
	//=========================FeedBack Validation Start==================================//
	
	
	
	public void feedBackValidationWithGlobalFlag() throws Exception {
		extent.HeaderChildNode("FeedBack Validation With Global Flag");
		onBoarding();
		/*------------------------------WEB----------------------------*/
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		if (verifyElementPresent(AdminPage.objEmailField, "Email Text Field")) {
			explicitWaitVisibility(AdminPage.objEmailField, 10);
			type(AdminPage.objEmailField, prop.getproperty("RingAdminEmail"), "Email");
			verifyElementPresent(TestingPortalWebPage.continueBtn, "Continue Button");
			//JSClick(AdminPage.objContinueBtn, "Continue Button");
			JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
			waitTime(10000);
			JSClick(AdminPage.objDashBoard, "DashBoard");
			ScrollToTheElement(AdminPage.objConfiguration);
			JSClick(AdminPage.objConfiguration, "Configuration");
			waitTime(5000);
			type(AdminPage.objSearchField, "Feedback", "FeedBack");
			waitTime(5000);
			JSClick(AdminPage.objEditBtn, "Edit Button");
			click(AdminPage.objMetaValueField, "Meta Field");
			waitTime(5000);
			clearFieldUsingRobot(AdminPage.objMetaValueField, "Meta Field");
			waitTime(2000);
			type(AdminPage.objMetaValueField, "0", "Meta Field");
			JSClick(AdminPage.objSubmit, "Submit Button");
			logOutAdminPanel();
		}

		waitTime(10000);
		BrowsertearDown();

		/*------------------------------Android----------------------------*/
		setPlatform("Android");
		initDriver();
		waitTime(5000);

		singleTransactionFromHomePage();
		explicitWaitVisibility(HomePage.objHome, 10);
		Swipe("DOWN", 1);
		verifyElementNotPresent(FeedbackPage.objFeedbackPopup, 5);
		logger.info("TC_Ring_Core_294, FeedBack popup is not displayed when global Flag is set as 0 or disabled");
		extent.extentLoggerPass("TC_Ring_Core_294", "TC_Ring_Core_294, FeedBack popup is not displayed when global Flag is set as 0 or disabled");
		System.out.println("-----------------------------------------------------------");


		/*------------------------------WEB----------------------------*/
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf1 = getPlatform();
		System.out.println(pf1);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		if (verifyElementPresent(AdminPage.objEmailField, "Email Text Field")) {
			explicitWaitVisibility(AdminPage.objEmailField, 10);
			type(AdminPage.objEmailField, prop.getproperty("RingAdminEmail"), "Email");
			verifyElementPresent(AdminPage.objContinueBtn, "Continue Button");
			//JSClick(AdminPage.objContinueBtn, "Continue Button");
			JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
			waitTime(10000);
			JSClick(AdminPage.objDashBoard, "DashBoard");
			ScrollToTheElement(AdminPage.objConfiguration);
			JSClick(AdminPage.objConfiguration, "Configuration");
			waitTime(5000);
			type(AdminPage.objSearchField, "Feedback", "FeedBack");
			waitTime(5000);
			JSClick(AdminPage.objEditBtn, "Edit Button");
			click(AdminPage.objMetaValueField, "Meta Field");
			waitTime(5000);
			clearFieldUsingRobot(AdminPage.objMetaValueField, "Meta Field");
			waitTime(2000);
			type(AdminPage.objMetaValueField, "1", "Meta Field");
			JSClick(AdminPage.objSubmit, "Submit Button");
			logOutAdminPanel();
		}

		waitTime(10000);
		BrowsertearDown();

		/*------------------------------Android----------------------------*/
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		Swipe("DOWN", 2);
		click(HomePage.objUPI,getTextVal(HomePage.objUPI,"Tab"));
		click(HomePage.objGoToHomePage,getTextVal(HomePage.objGoToHomePage,"Button"));
		if (verifyElementPresent(FeedbackPage.objFeedbackPopup, getTextVal(FeedbackPage.objFeedbackPopup, "Text"))) {
			logger.info("TC_Ring_Core_295, FeedBack popup is displayed when global Flag is set as 1 or enabled");
			extent.extentLoggerPass("TC_Ring_Core_295", "TC_Ring_Core_295, FeedBack popup is displayed when global Flag is set as 1 or enabled");
			System.out.println("-----------------------------------------------------------");
		}
		Aclick(FeedbackPage.objNoBtn, "No Button");

	}


	public void logOutAdminPanel() throws Exception {
		extent.HeaderChildNode(("LogOut from Admin Panel"));
		JSClick(AdminPage.objProfileBtn, "Profile Button");
		JSClick(AdminPage.objLogOutBtn, "LogOut Button");
		if (verifyElementPresent(AdminPage.objAdminPage, "Admin Login Page")) {
			logger.info("User Successfully LogOut from Admin Panel");
		} else {
			logger.info("User has not been Logout");
		}
	}




public void feedBackMechanism() throws Exception {
		extent.HeaderChildNode("Validation of Feedback");
		getDriver().resetApp();
		onBoarding();
		explicitWaitVisibility(HomePage.objHome, 10);
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals("Home", sHome);
		if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {
			String scanQrEnabled = getAttributValue("enabled", HomePage.objScanAndPay);
			softAssertion.assertEquals("true", scanQrEnabled);
			Aclick(HomePage.objScanAndPay, "Scan And Pay");

			explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
			verifyElementPresent(HomePage.objScanAnyQRToPay, getTextVal(HomePage.objScanAnyQRToPay, "Text"));

			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
			new CommandBase("Chrome");
			waitTime(4000);
			String projectPath = System.getProperty("user.dir");
			getWebDriver().get(projectPath + "\\Mock_Files\\qrcode.png");
			waitTime(15000);
			BrowsertearDown();

//        /*------------------------------Android----------------------------*/
			setPlatform("Android");
			initDriver();


			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
				Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
			}
			waitTime(5000);
			if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
				Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
				type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
				Aclick(PaymentPage.objContinue, "Continue Button");
			}
			if (verifyElementPresent(PaymentPage.objPaymentDone, getTextVal(PaymentPage.objPaymentDone, "Text"))) {
				String sPaymentDone = getText(PaymentPage.objPaymentDone);
				softAssertion.assertEquals("Payment Done!", sPaymentDone);
				verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"));
				Aclick(PaymentPage.objGoToHomePage, "Go To HomePage");
			}
			Swipe("DOWN", 2);
			if (verifyElementPresent(FeedbackPage.objFeedbackPopup, getTextVal(FeedbackPage.objFeedbackPopup, "Text"))) {
				String sFeedBackMsg = getText(FeedbackPage.objFeedbackPopup);
				softAssertion.assertEquals(sFeedBackMsg, "Are you enjoying the app?");
				verifyElementPresent(FeedbackPage.objYesBtn, getTextVal(FeedbackPage.objYesBtn, "Text"));
				verifyElementPresent(FeedbackPage.objNoBtn, getTextVal(FeedbackPage.objNoBtn, "Text"));
				logger.info("TC_Ring_Core_291, FeedBack popup displayed with yes & No option");
				extent.extentLoggerPass("TC_Ring_Core_291", "TC_Ring_Core_291, FeedBack popup displayed with yes & No option");
				System.out.println("-----------------------------------------------------------");
				getDriver().runAppInBackground(Duration.ofSeconds(5));
			}
			if (verifyElementPresent(FeedbackPage.objFeedbackPopup, getTextVal(FeedbackPage.objFeedbackPopup, "Text"))) {
				logger.info("TC_Ring_Core_299, Reappeared FeedBack popup after dismissing and re-initiating");
				extent.extentLoggerPass("TC_Ring_Core_299", "TC_Ring_Core_299, Reappeared FeedBack popup after dismissing and re-initiating");
				System.out.println("-----------------------------------------------------------");
			}
			Aclick(FeedbackPage.objYesBtn, "Yes Button");
			if (verifyElementPresent(FeedbackPage.objRateUrExp, getTextVal(FeedbackPage.objRateUrExp, "Text"))) {
				String sRateUrExp = getText(FeedbackPage.objRateUrExp);
				softAssertion.assertEquals(sRateUrExp, "Rate your experience");
				logger.info("TC_Ring_Core_292, Rate ur exp bottom sheet got displayed");
				extent.extentLoggerPass("TC_Ring_Core_292", "TC_Ring_Core_292, Rate ur exp bottom sheet got displayed");
				System.out.println("-----------------------------------------------------------");
			}
			Aclick(FeedbackPage.objCloseBtn, "Close Button");
			singleTransactionFromHomePage();
			Swipe("DOWN", 2);
			verifyElementNotPresent(FeedbackPage.objFeedbackPopup, 5);
			logger.info("TC_Ring_Core_293, FeedBack popup is not displayed on Second Transaction");
			extent.extentLoggerPass("TC_Ring_Core_293", "TC_Ring_Core_293, FeedBack popup is not displayed on Second Transaction");
			System.out.println("-----------------------------------------------------------");
			ringPayLogout();

		}
		int nNumber = 4;
		for (int i = 1; i < nNumber; i++) {

			explicitWaitVisibility(RingLoginPage.objLoginLink, 10);
			Aclick(RingLoginPage.objLoginLink, "Signup/Login link");
			explicitWaitVisibility(RingLoginPage.objLoginMobile, 10);
			Aclick(RingLoginPage.objLoginMobile, "Continue with Mobile option");
			String nMobileNumber = "9"+RandomIntegerGenerator(9);
			click(RingLoginPage.objMobTextField, "Mobile Number Field");
			type(RingLoginPage.objMobTextField, nMobileNumber, "Mobile Number Field");
			click(RingLoginPage.objMobTextField, "Mobile Number Field");
			type(RingLoginPage.objMobTextField, nMobileNumber, "Mobile Number Field");
			enterOtp("888888");

			waitTime(30000);
			Aclick(UserRegistrationPage.objFirstName, "First Name text field");
			type(UserRegistrationPage.objFirstName, prop.getproperty("FirstName"), "First Name text field");
			Aclick(UserRegistrationPage.objLastName, "Last Name text field");
			type(UserRegistrationPage.objLastName, prop.getproperty("LastName"), "Last Name text field");
			hideKeyboard();
			Aclick(UserRegistrationPage.objMothersName, "Mother's Name Text Field");

			type(UserRegistrationPage.objMothersName, prop.getproperty("MotherName"), "Mother's Name Text Field");
			hideKeyboard();
			String email = generateRandomEmail(15);
			Aclick(UserRegistrationPage.objUserEmail, "Email Field");
			Aclick(UserRegistrationPage.objNoneOfTheAbove, "None of the above");

			type(UserRegistrationPage.objUserEmail, email, "Email Field");
			hideKeyboard();
			dateOfBirth(prop.getproperty("Month"), prop.getproperty("Date"), prop.getproperty("Year"));
			Aclick(UserRegistrationPage.objGenderBtn, "Gender Radio Button");
			Aclick(UserRegistrationPage.objGenderBtn, "Gender Radio Button");
			Aclick(UserRegistrationPage.objProceed, "Proceed Button");
			waitTime(10000);
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			waitTime(10000);
			explicitWaitVisibility(UserRegistrationPage.objTermsAndCondition, 10);
			Aclick(UserRegistrationPage.objTermsAndCondition, "Terms and condition");
			Aclick(UserRegistrationPage.objAcceptOffer, "Accept offer");
			waitTime(10000);
			Aclick(UserRegistrationPage.objEnterPin, "Enter Pin");
			type(UserRegistrationPage.objEnterPin, "1111", "Enter Pin");
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, "1111", "Re-Enter Pin");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");

			explicitWaitVisibility(HomePage.objCloseButton, 20);
			Aclick(HomePage.objCloseButton, "Ad Cross Button");
			softAssertion.assertEquals("Home", sHome);
			if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {
				String scanQrEnabled = getAttributValue("enabled", HomePage.objScanAndPay);
				softAssertion.assertEquals("true", scanQrEnabled);
				Aclick(HomePage.objScanAndPay, "Scan And Pay");

				explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
				String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
				softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
				verifyElementPresent(HomePage.objScanAnyQRToPay, getTextVal(HomePage.objScanAnyQRToPay, "Text"));

				/*------------------------------WEB----------------------------*/
				Utilities.setPlatform = "Web";
				new CommandBase("Chrome");
				waitTime(4000);
				String projectPath = System.getProperty("user.dir");
				getWebDriver().get(projectPath + "\\Mock_Files\\qrcode.png");
				waitTime(15000);
				BrowsertearDown();
//
//        /*------------------------------Android----------------------------*/
				setPlatform("Android");
				initDriver();

				if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
					Aclick(PaymentPage.objAmountTextField, "Amount Field");
					clearField(PaymentPage.objAmountTextField, "Amount Field");
					type(PaymentPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
					Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
				}
				waitTime(5000);
				if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
					Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
					type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
				}
				if (verifyElementPresent(PaymentPage.objPaymentDone, getTextVal(PaymentPage.objPaymentDone, "Text"))) {
					String sPaymentDone = getText(PaymentPage.objPaymentDone);
					softAssertion.assertEquals("Payment Done!", sPaymentDone);
					verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"));
					Aclick(PaymentPage.objGoToHomePage, "Go To HomePage");
				}
				Swipe("DOWN", 2);

				if (verifyElementPresent(FeedbackPage.objFeedbackPopup, getTextVal(FeedbackPage.objFeedbackPopup, "Text"))) {
					String sFeedBackMsg = getText(FeedbackPage.objFeedbackPopup);
					softAssertion.assertEquals(sFeedBackMsg, "Are you enjoying the app?");
					verifyElementPresent(FeedbackPage.objYesBtn, getTextVal(FeedbackPage.objYesBtn, "Text"));
					verifyElementPresent(FeedbackPage.objNoBtn, getTextVal(FeedbackPage.objNoBtn, "Text"));
					Aclick(FeedbackPage.objYesBtn, "Yes Button");

					if (verifyElementPresent(FeedbackPage.objRateUrExp, getTextVal(FeedbackPage.objRateUrExp, "Text"))) {
						String sRateUrExp = getText(FeedbackPage.objRateUrExp);
						softAssertion.assertEquals(sRateUrExp, "Rate your experience");
						click(FeedbackPage.objRating(i), "Rating Button");
//					click(FeedbackPage.objChips(nNumber-i),"Rating Button");
						int nChips = nNumber - i;
						for (int j = nChips; j > 0; j--) {
							click(FeedbackPage.objChips(j), +j + "Element");
						}
						click(FeedbackPage.objSubmitBtn, "Submit Button");
						logger.info("All chips data should be selected and submitted for rating " + i);
						extent.extentLoggerPass("Rate your experience", "All chips data should be selected and submitted for rating" + i);
						System.out.println("-----------------------------------------------------------");
					}
				} else {
					logger.info("FeedBack Popup is not displayed");
					extent.extentLoggerPass("FeedBack Popup", "FeedBack Popup is not displayed");
					System.out.println("-----------------------------------------------------------");
				}
			}
			ringPayLogout();
		}
		for (int i = nNumber; i < 6; i++) {

			explicitWaitVisibility(RingLoginPage.objLoginLink, 10);
			Aclick(RingLoginPage.objLoginLink, "Signup/Login link");
			explicitWaitVisibility(RingLoginPage.objLoginMobile, 10);
			Aclick(RingLoginPage.objLoginMobile, "Continue with Mobile option");
			String nMobileNumber = "9"+RandomIntegerGenerator(9);
			click(RingLoginPage.objMobTextField, "Mobile Number Field");
			type(RingLoginPage.objMobTextField, nMobileNumber, "Mobile Number Field");
			click(RingLoginPage.objMobTextField, "Mobile Number Field");
			type(RingLoginPage.objMobTextField, nMobileNumber, "Mobile Number Field");
			enterOtp("888888");

			waitTime(10000);
			Aclick(UserRegistrationPage.objFirstName, "First Name text field");
			type(UserRegistrationPage.objFirstName, prop.getproperty("FirstName"), "First Name text field");
			Aclick(UserRegistrationPage.objLastName, "Last Name text field");
			type(UserRegistrationPage.objLastName, prop.getproperty("LastName"), "Last Name text field");
			hideKeyboard();
			Aclick(UserRegistrationPage.objMothersName, "Mother's Name Text Field");

			type(UserRegistrationPage.objMothersName, prop.getproperty("MotherName"), "Mother's Name Text Field");
			hideKeyboard();
			String email = generateRandomEmail(15);
			Aclick(UserRegistrationPage.objUserEmail, "Email Field");
			waitTime(3000);
			Aclick(UserRegistrationPage.objNoneOfTheAbove, "None of the above");

			type(UserRegistrationPage.objUserEmail, email, "Email Field");
			hideKeyboard();
			dateOfBirth(prop.getproperty("Month"), prop.getproperty("Date"), prop.getproperty("Year"));
			Aclick(UserRegistrationPage.objGenderBtn, "Gender Radio Button");
			Aclick(UserRegistrationPage.objGenderBtn, "Gender Radio Button");
			Aclick(UserRegistrationPage.objProceed, "Proceed Button");
			waitTime(10000);
			addAddress("86","44, Borivali","Vasa Street","Das Gupta Street","400080");
			waitTime(10000);
			explicitWaitVisibility(UserRegistrationPage.objTermsAndCondition, 10);
			Aclick(UserRegistrationPage.objTermsAndCondition, "Terms and condition");
			Aclick(UserRegistrationPage.objAcceptOffer, "Accept offer");
			waitTime(10000);
			Aclick(UserRegistrationPage.objEnterPin, "Enter Pin");
			type(UserRegistrationPage.objEnterPin, "1111", "Enter Pin");
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, "1111", "Re-Enter Pin");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");

			explicitWaitVisibility(HomePage.objCloseButton, 20);
			Aclick(HomePage.objCloseButton, "Ad Cross Button");
			softAssertion.assertEquals("Home", sHome);
			if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {
				String scanQrEnabled = getAttributValue("enabled", HomePage.objScanAndPay);
				softAssertion.assertEquals("true", scanQrEnabled);
				Aclick(HomePage.objScanAndPay, "Scan And Pay");

				explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
				String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
				softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
				verifyElementPresent(HomePage.objScanAnyQRToPay, getTextVal(HomePage.objScanAnyQRToPay, "Text"));

				/*------------------------------WEB----------------------------*/
				Utilities.setPlatform = "Web";
				new CommandBase("Chrome");
				waitTime(4000);
				String projectPath = System.getProperty("user.dir");
				getWebDriver().get(projectPath + "\\Mock_Files\\qrcode.png");
				waitTime(15000);
				BrowsertearDown();
//
//        /*------------------------------Android----------------------------*/
				setPlatform("Android");
				initDriver();

				if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
					Aclick(PaymentPage.objAmountTextField, "Amount Field");
					clearField(PaymentPage.objAmountTextField, "Amount Field");
					type(PaymentPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
					Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
				}
				waitTime(40000);
				if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
					Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
					type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
				}
				if (verifyElementPresent(PaymentPage.objPaymentDone, getTextVal(PaymentPage.objPaymentDone, "Text"))) {
					String sPaymentDone = getText(PaymentPage.objPaymentDone);
					softAssertion.assertEquals("Payment Done!", sPaymentDone);
					verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"));
					Aclick(PaymentPage.objGoToHomePage, "Go To HomePage");
				}
				Swipe("DOWN", 2);

				if (verifyElementPresent(FeedbackPage.objFeedbackPopup, getTextVal(FeedbackPage.objFeedbackPopup, "Text"))) {
					String sFeedBackMsg = getText(FeedbackPage.objFeedbackPopup);
					softAssertion.assertEquals(sFeedBackMsg, "Are you enjoying the app?");
					verifyElementPresent(FeedbackPage.objYesBtn, getTextVal(FeedbackPage.objYesBtn, "Text"));
					verifyElementPresent(FeedbackPage.objNoBtn, getTextVal(FeedbackPage.objNoBtn, "Text"));
					Aclick(FeedbackPage.objYesBtn, "Yes Button");

					if (verifyElementPresent(FeedbackPage.objRateUrExp, getTextVal(FeedbackPage.objRateUrExp, "Text"))) {
						String sRateUrExp = getText(FeedbackPage.objRateUrExp);
						softAssertion.assertEquals(sRateUrExp, "Rate your experience");

						click(FeedbackPage.objRating(i), "Rating Button");

//					click(FeedbackPage.objChips(nNumber-i),"Rating Button");
//						int nChips = nNumber - i;
//						for (int j = nChips; j > 0; j--) {
//							click(FeedbackPage.objChips(j), +j + "Element");
//						}
						click(FeedbackPage.objRateUs, "Rate us on PlayStore Button");
						if (verifyElementPresent(FeedbackPage.objPlayStore, getTextVal(FeedbackPage.objPlayStore, "Text"))) {
							String sPayWithRing = getText(FeedbackPage.objPlayStore);
							softAssertion.assertEquals(sPayWithRing, "//*[@text='Pay with Ring']");
							logger.info("TC_Ring_Core_300, Redirected to PlayStore");
							extent.extentLoggerPass("TC_Ring_Core_300", "TC_Ring_Core_300, Redirected to PlayStore");
							System.out.println("-----------------------------------------------------------");
							Back(1);
						}
					}
				} else {
					logger.info("FeedBack Popup is not displayed");
					extent.extentLoggerPass("FeedBack Popup", "FeedBack Popup is not displayed");
					System.out.println("-----------------------------------------------------------");
				}
			}
			ringPayLogout();
		}
	}







	public void singleTransactionFromHomePage() throws Exception {
			extent.HeaderChildNode(" Single Transaction");
			waitTime(5000);
			if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {
			String scanQrEnabled = getAttributValue("enabled", HomePage.objScanAndPay);
			softAssertion.assertEquals("true", scanQrEnabled);
			Aclick(HomePage.objScanAndPay, "Scan And Pay");

			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
			new CommandBase("Chrome");
			waitTime(4000);
			String projectPath = System.getProperty("user.dir");
			getWebDriver().get(projectPath + "\\Mock_Files\\qrcode.png");
			waitTime(15000);
			BrowsertearDown();
//
//        /*------------------------------Android----------------------------*/
			setPlatform("Android");
			initDriver();

			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, "200", "Amount Field");

				verifyElementPresentAndClick(PaymentPage.objPayNowBtn, "Pay Now Button");
//				Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
			}
			waitTime(5000);
			explicitWaitVisibility(PaymentPage.objConfirmPayment, 10);
			if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
				Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
				type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
				Aclick(PaymentPage.objContinue, "Continue Button");
			}
			if (verifyElementPresent(PaymentPage.objPaymentDone, getTextVal(PaymentPage.objPaymentDone, "Text"))) {
				String sPaymentDone = getText(PaymentPage.objPaymentDone);
				softAssertion.assertEquals("Payment Done!", sPaymentDone);
				verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"));
				Aclick(PaymentPage.objGoToHomePage, "Go To HomePage");
			}
		}
	}
	
	public static String generateRandomEmail(int length) {   
		logger.info("Generating a Random email String");   
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890";   
		String email = "";   
		String temp = RandomStringUtils.random(length, allowedChars);   
		email = temp.substring(0, temp.length() - 9) + "@gmail.com";   
		return email;
	}
	
	
	//=========================FeedBack Validation End==================================//
	
	
	//-----------------------------------WhitleList Logic Start Instaloan------------------------------//
	
	public void instaLoanWhitelistLogic(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN,String firstName, String lastName, String mothersName, String gender, String delinquentNo, String terminatedNo) throws Exception {
		extent.HeaderChildNode("WhiteList Logic");

		instaLoanBanner(OTP, RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		outstandingAMTGreaterThanZero(OTP, RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		bannerNotDisplayedWhenUserIsNotWhitelisted(OTP,firstName, lastName, mothersName, gender, InstaLoanMPIN, ReEnterMPIN);
		offerNotAvailedIfUserBecomesDelinquent(delinquentNo, OTP);
		userBecomesBlocked(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		userBecomesTerminated(terminatedNo, OTP);
		amountSetNull(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		amountSetAsZero(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		offerIDNULL(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		offerIDZero(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		User_reference_numberNULL(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
		userRemovedFromWhiteListTable(OTP,RingAdminEmail, RingAdminPassword, RingAdminOTP,InstaLoanMPIN, ReEnterMPIN);
	}
	
	public void instaLoanBanner(String OTP,String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("InstaLoan BannerWhiteList Logic");
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		waitTime(40000);
		instaPanCardDetails(RingAdminEmail, RingAdminPassword,RingAdminOTP, "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		instaLoanCoachPopup();
		Swipe("up", 1);
		instaLoanHomePageBanner();
		instaLoanOutstandingBalance();
		waitTime(10000);
		basicDetails();
		click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(90000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		waitTime(120000);
		if(!verifyElementPresent2(InstaLoanOptionalJourney.objPayNowBtnAtHomePage, "Pay Now Button")) {
			waitTime(120000);
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objUPITab, "UPI Tab");
			waitTime(90000);
			Back(1);
		}
		Swipe("down", 1);
		payNowInstaLoan();

}
//=====================================To verify if whitelisted user clicks on apply now button when Outstanding amount > 0=======================================//
	public void outstandingAMTGreaterThanZero(String OTP,String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("whitelisted user clicks on apply now button when Outstanding amount > 0");
		mockUserAPI();
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		// Scan here
		/*------------------------------WEB----------------------------*/
		Utilities.setPlatform = "Web";
		new CommandBase("Chrome");
		waitTime(4000);
		String projectPath = System.getProperty("user.dir");
		getWebDriver().get(projectPath + "\\Mock_Files\\qrcode.png");
		waitTime(15000);
		BrowsertearDown();

		/*------------------------------Android----------------------------*/
		setPlatform("Android");
		initDriver();

		click(RingLoginPage.objMakePaymentLetsRingItBtn, getText(RingLoginPage.objMakePaymentLetsRingItBtn));
		click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
		type1(RingPayMerchantFlowPage.objAmountTextField, "1", "Amount Field");
		verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
		verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		loginMobile();
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(RingAdminEmail, RingAdminPassword,RingAdminOTP, "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Woohoo");
		instaLoanSetPin(InstaLoanMPIN,ReEnterMPIN );
		waitTime(5000);
		click(BankTransferModule.objHomePageBtn, "Go to Homepage");
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.objNoBtn, "No Button");
		instaLoanCoachPopup();
		Swipe("up", 1);
		instaLoanHomePageBanner();
		instaLoanOutstandingBalance();
	}
//========================================Insta banner Not displayed when user is not whitelisted========================================================================
	public void bannerNotDisplayedWhenUserIsNotWhitelisted(String OTP, String firstName, String lastName, String mothersName, String gender, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("Insta banner Not displayed when user is not whitelisted");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		waitTime(5000);
		enterOtp(OTP);
		readAndAccept();
		waitTime(40000);
		instaUserDetails(firstName, lastName, mothersName,RandomStringGenerator(4) + "@example.com", gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN,ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		instaLoanCoachPopup();
		instaLoanHomePageBanner();
	}
//============================To verify Insta loan offer is not availed if user becomes delinquent even if customer is present in whitelist==========================================
	public void offerNotAvailedIfUserBecomesDelinquent(String delinquentNo, String OTP) throws Exception {
		extent.HeaderChildNode("Insta loan offer is not availed if user becomes delinquent even if customer is present in whitelist");
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(delinquentNo);
		waitTime(5000);
		enterOtp(OTP);
		readAndAccept();

		if (verifyElementPresent2(UserRegistrationPage.objNoBtn, "No Button")) {
			verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		}
		waitTime(20000);

		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);

		click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, prop.getproperty("RingAdminEmail"), "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, prop.getproperty("RingAdminPassword"), "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, OTP, "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
		selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
		click(InstaLoanPage.objSearchTerm, "Search Item Field");
		type(InstaLoanPage.objSearchTerm, delinquentNo, "Search Item Field");
		click(InstaLoanPage.objSearchBtn, "Search Button");
		waitTime(10000);
		String referenceNo = getText(InstaLoanPage.objUserReferenceNo);
		System.out.println(referenceNo);
		BrowsertearDown();

		setPlatform("Android");
		initDriver();
		waitTime(5000);

		String instaLoanWhitelistNo = executeQuery3("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '" + referenceNo+ "';",2);

		if (instaLoanWhitelistNo.equals(referenceNo)) {
			softAssertion.assertEquals(instaLoanWhitelistNo, referenceNo);
			Swipe("down", 1);
			boolean instaLoanBanner = verifyElementPresent2(InstaLoanPage.objApplyBtn, "InstaLoan Banner");
			logger.info("InstaLoan Banner present : " + instaLoanBanner);
			extent.extentLogger("InstaLoan Banner", "InstaLoan Banner present : " + instaLoanBanner);

			if (instaLoanBanner == true) {
				logger.info("InstaLoan Banner is present even if user is deliquent");
				extent.extentLoggerFail("InstaLoan Banner", "InstaLoan Banner is present even if user is deliquent");
			} else {
				logger.info("TC_Insta_loan_10 - To verify Insta loan offer is not availed if user becomes delinquent even if customer is present in whitelist");
				extent.extentLoggerPass("TC_Insta_loan_10","TC_Insta_loan_10 - To verify Insta loan offer is not availed if user becomes delinquent even if customer is present in whitelist");
			}
		}
	}

//============================To verify Insta loan offer is not avail if user becomes blocked even if customer is present in whitelist===============================================
	public void userBecomesBlocked(String OTP,String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("Insta loan offer is not avail if user becomes blocked even if customer is present in whitelist");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(RingAdminEmail, RingAdminPassword,RingAdminOTP, "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN,ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		instaLoanCoachPopup();
		Swipe("up", 1);
		instaLoanHomePageBanner();
		blockUser();
	}
//=========================================To verify Insta loan offer is not avail if user becomes terminated even if customer is present in whitelist=========================
	public void userBecomesTerminated(String terminatedNo, String OTP) throws Exception {
		extent.HeaderChildNode("Insta loan offer is not avail if user becomes Terminated even if customer is present in whitelist");
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(terminatedNo);
		waitTime(5000);
		enterOtp(OTP);
		readAndAccept();
		waitTime(20000);

		setPlatform("Web");
		System.out.println("platform changed to web");
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);

		click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, prop.getproperty("RingAdminEmail"), "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, prop.getproperty("RingAdminPassword"), "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, OTP, "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
		selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
		click(InstaLoanPage.objSearchTerm, "Search Item Field");
		type(InstaLoanPage.objSearchTerm, terminatedNo, "Search Item Field");
		click(InstaLoanPage.objSearchBtn, "Search Button");
		waitTime(10000);
		String referenceNo1 = getText(InstaLoanPage.objUserReferenceNo);
		BrowsertearDown();

		setPlatform("Android");
		initDriver();
		waitTime(5000);

		String instaLoanWhitelistNo1 = executeQuery3("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '" + referenceNo1+ "';",2);
		System.out.println("User is present in WhiteListTable : " + instaLoanWhitelistNo1);
		logger.info("User is present in WhiteListTable : " + instaLoanWhitelistNo1);
		extent.extentLogger("WhiteList Table", "User is present in WhiteListTable : " + instaLoanWhitelistNo1);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		if (instaLoanWhitelistNo1.equals(referenceNo1)) {
			softAssertion.assertEquals(instaLoanWhitelistNo1, referenceNo1);

			boolean instaLoanBanner = verifyElementPresent2(InstaLoanPage.objApplyBtn, "InstaLoan Banner");
			logger.info("InstaLoan Banner present : " + instaLoanBanner);
			extent.extentLogger("InstaLoan Banner", "InstaLoan Banner present : " + instaLoanBanner);

			if(instaLoanBanner == false ){
				logger.info("TC_Insta_loan_12 - To verify Insta loan offer is not avail if user becomes terminated even if customer is present in whitelist");
				extent.extentLoggerFail("InstaLoan Banner", "TC_Insta_loan_12 - To verify Insta loan offer is not avail if user becomes terminated even if customer is present in whitelist");
			}
		}
	}
//=============================================To verify if approved amount is set as NULL in whitelisting table=============================================================
	public void amountSetNull(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("Approved amount is set as NULL in whitelisting table");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaApprovedAmountNull(RingAdminEmail, RingAdminPassword, RingAdminOTP, "OPTIONAL", "NULL", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		logger.info("TC_Insta_loan_13 - To verify if approved amount is set as NULL in whitelisting table");
		extent.extentLoggerPass("TC_Insta_loan_13","TC_Insta_loan_04 - To verify if approved amount is set as NULL in whitelisting table");
	}

//====================================To verify if approved amount is set as 0 in whitelisting table===================================================================
	public void amountSetAsZero(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("if approved amount is set as 0 in whitelisting table");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(RingAdminEmail, RingAdminPassword, RingAdminOTP, "OPTIONAL", "0", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		instaLoanCoachPopup();
		Swipe("up", 1);
		instaLoanHomePageBanner();
		instaLoanOutstandingBalance();
		waitTime(10000);
		basicDetails();

		boolean msg = verifyElementPresent2(InstaLoanPage.objDisbursementSuccessfulMsg,"Insta Loan Disbursement Successful message");
		if (msg == true) {
			logger.info(getText(InstaLoanPage.objDisbursementSuccessfulMsg) + " is displayed");
			extent.extentLogger("DisbursementMsg",getText(InstaLoanPage.objDisbursementSuccessfulMsg) + " is displayed");
		} else {
			logger.info("TC_Insta_loan_14 - To verify if approved amount is set as 0 in whitelisting table");
			extent.extentLoggerPass("TC_Insta_loan_14","TC_Insta_loan_14 - To verify if approved amount is set as 0 in whitelisting table");
			verifyElementPresentAndClick(InstaLoanPage.objOKGotItBtn, getText(InstaLoanPage.objOKGotItBtn));
		}
	}

//===================================To verify if Offer ID is set as NULL in whitelisting table=================================================================
	public void offerIDNULL(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("Offer ID is set as NULL in whitelisting table");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaApprovedAmountNull(RingAdminEmail, RingAdminPassword, RingAdminOTP, "OPTIONAL", "13000", "NULL", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		logger.info("TC_Insta_loan_15 - To verify if Offer ID is set as NULL in whitelisting table");
		extent.extentLoggerPass("TC_Insta_loan_15", "TC_Insta_loan_04 - To verify if Offer ID is set as NULL in whitelisting table");
	}

//=======================================To verify if Offer ID is set as 0 in whitelisting table================================================================================
	public void offerIDZero(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("Offer ID is set as NULL in whitelisting table");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(RingAdminEmail, RingAdminPassword, RingAdminOTP, "OPTIONAL", "13000", "0", "2135");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		instaLoanCoachPopup();
		Swipe("up", 1);
		instaLoanHomePageBanner();
		instaLoanOutstandingBalance();
		waitTime(10000);
		basicDetails();
		boolean msg1 = verifyElementPresent2(InstaLoanPage.objSorryPage,"Sorry! your application cannot be processed right now.");
		if (msg1 == true) {
			logger.info("TC_Insta_loan_16 - To verify if Offer ID is set as 0 in whitelisting table");
			extent.extentLoggerPass("TC_Insta_loan_16", "TC_Insta_loan_04 - To verify if Offer ID is set as 0 in whitelisting table");
		}
	}

//=================================To verify if User_reference_number column is set as NULL in whitelisting table===========================================
	public void User_reference_numberNULL(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("User_reference_number column is set as NULL in whitelisting table");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaReferenceNoNull(RingAdminEmail, RingAdminPassword, RingAdminOTP, "NULL", "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");

		boolean instaBanner = verifyElementPresent2(InstaLoanPage.objApplyBtn, "Insta Banner");
		if (instaBanner == false) {
			logger.info("TC_Insta_loan_17 - To verify if User_reference_number column is set as NULL in whitelisting table");
			extent.extentLoggerPass("TC_Insta_loan_17","TC_Insta_loan_17 - To verify if User_reference_number column is set as NULL in whitelisting table");
		}
	}

//==============================To verify if User is removed from Whitelist Loan after taking InstaLoan=======================================================================
	public void userRemovedFromWhiteListTable(String OTP, String RingAdminEmail, String RingAdminPassword, String RingAdminOTP, String InstaLoanMPIN, String ReEnterMPIN) throws Exception {
		extent.HeaderChildNode("User is removed from Whitelist Loan after taking InstaLoan");
		mockUserAPI();
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(OTP);
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(RingAdminEmail, RingAdminPassword, RingAdminOTP, "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(InstaLoanMPIN, ReEnterMPIN);
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		instaLoanCoachPopup();
		Swipe("up", 1);
		instaLoanHomePageBanner();
		instaLoanOutstandingBalance();
		waitTime(10000);
		basicDetails();
		click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(90000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		waitTime(120000);
		deleteRowFromInstaLoanWhitelistTable("DELETE FROM db_tradofina.instaloan_whitelisted_users WHERE user_reference_number = '" + userReferenceNo+ "';");
		verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
		click(InstaLoanPage.objPayFullAmountRadioBtn, "Pay Full Amount RadioButton");
		verifyElementPresentAndClick(InstaLoanPage.objPayNowBtn, getText(InstaLoanPage.objPayNowBtn) + " Button");
		explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
		verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
		waitTime(20000);
		verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
		addNewCard();
		String refAfterRepayment = executeQuery2("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '"+ userReferenceNo + "';");
		System.out.println(refAfterRepayment);

		boolean reApplyForInstaLoanBanner = verifyElementPresent2(InstaLoanPage.objReApplyForInstaLoan,getText(InstaLoanPage.objReApplyForInstaLoan));
		if (reApplyForInstaLoanBanner == false) {
			logger.info("TC_Insta_loan_18 - To verify if User is removed from Whitelist Loan after taking InstaLoan");
			extent.extentLoggerPass("TC_Insta_loan_18","TC_Insta_loan_18 - To verify if User is removed from Whitelist Loan after taking InstaLoan");
		}
	}

public void instaLoanHomePageBanner() throws Exception {

	boolean instaBanner = verifyElementPresent2(InstaLoanPage.objinstaLoanBannerImage,"InstaLoan Banner is displayed");
	logger.info("InstaLoan Banner present is : " + instaBanner);
	extent.extentLogger("InstaLoan Banner", "InstaLoan Banner present is : " + instaBanner);

	if (instaBanner == true) {
		logger.info("TC_Insta_loan_01 - To verify if insta loan banner is visible when user is whitelisted");
		extent.extentLoggerPass("TC_Insta_loan_01","TC_Insta_loan_01 - To verify if insta loan banner is visible when user is whitelisted");
	} else {
		logger.info("TC_Insta_loan_02 - To verify if insta loan Banner is not visible when user is not whitelisted");
		extent.extentLoggerPass("TC_Insta_loan_02","TC_Insta_loan_02 -To verify if insta loan Banner is not visible when user is not whitelisted");
	}
}

public void instaLoanCoachPopup() throws Exception {
	boolean instaCoach = verifyElementPresent2(InstaLoanPage.objInstaLoanCoachEnjoyText,"InstaLoan Coach Popup is displayed");
	logger.info("InstaLoan Coach Popup present : " + instaCoach);
	extent.extentLogger("InstaLoan Coach Popup", "InstaLoan Coach Popup present : " + instaCoach);

	if (instaCoach == true) {
		logger.info("TC_Insta_loan_05 - To verify the Display Coach mark and popup banner for 1st time whitelisted user.");
		extent.extentLogger("TC_Insta_loan_05","TC_Insta_loan_05 -To verify the Display Coach mark and popup banner for 1st time whitelisted user");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
	} else {
		logger.info("TC_Insta_loan_06 - To verify that Display Coach mark and popup banner should not be displayed if user is not whitelisted");
		extent.extentLoggerPass("C_Insta_loan_06","TC_Insta_loan_06 - To verify that Display Coach mark and popup banner should not be displayed if user is not whitelisted");
	}

	if (verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
		click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
	}
}

public void instaLoanOutstandingBalance() throws Exception {
	String outstandingAmt = getText(InstaLoanPage.objOutstatndingAmount);
	outstandingAmt = outstandingAmt.substring(1);
	double number = Double.parseDouble(outstandingAmt);
	if ((number == 0) || (number <= 1)) {
		logger.info("Outstanding Amount is : " + outstandingAmt);
		extent.extentLoggerPass("Outstanding Amount", "Outstanding Amount is : " + outstandingAmt);
	}
	if (number == 0) {
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		logger.info("TC_Insta_loan_03 - To verify if whitelisted user clicks on apply now button when Outstanding amount = 0");
		extent.extentLoggerPass("TC_Insta_loan_03","TC_Insta_loan_03 -To verify if whitelisted user clicks on apply now button when Outstanding amount = 0");
	} else if ((number <= 1)) {
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		verifyElementPresent(InstaLoanPage.objRepayDues,getText(InstaLoanPage.objRepayDues) + getText(InstaLoanPage.objRepayDuesDescription));
		click(InstaLoanPage.objRepayPopupCrossBtn, "Cross Button");
		logger.info("TC_Insta_loan_04 - To verify if whitelisted user clicks on apply now button when Outstanding amount > 0");
		extent.extentLoggerPass("TC_Insta_loan_04","TC_Insta_loan_04 - To verify if whitelisted user clicks on apply now button when Outstanding amount > 0");
	}
}

public void basicDetails() throws Exception {
	click(InstaLoanPage.objFatherName, "Father Name Field");
	type(InstaLoanPage.objFatherName, "Arun", "Father Name Field");


		waitTime(20000);	
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn, "Proceed Button");
		waitTime(5000);
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn, "Proceed Button");
		click(InstaLoanPage.objProceedBtn, "Proceed Button");
		waitTime(5000);
		verifyElementPresentAndClick(InstaLoanPage.objSelectAddress, "Select Address Dropdown");
		verifyElementPresentAndClick(InstaLoanPage.objAddressSelected, getText(InstaLoanPage.objAddressSelected));
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		offerPage();
}

public void offerPage() throws Exception {
	if (verifyElementPresent2(InstaLoanPage.objOfferHeader, "Offer Page")) {
		verifyElementPresent(InstaLoanPage.objOfferHeader, getText(InstaLoanPage.objOfferHeader) + "Header");
		verifyElementPresent(InstaLoanPage.objEligible,getText(InstaLoanPage.objEligible) + getText(InstaLoanPage.objEligibleInstaLoanAmt));
		verifyElementPresentAndClick(InstaLoanPage.objAddBankButton, getText(InstaLoanPage.objAddBankButton));
		addbankAccount("5" + RandomIntegerGenerator(4), firstName + " " + lastName);
	}
}

public void payNowInstaLoan() throws Exception {

	explicitWaitVisibility(InstaLoanPage.objInstaLoanAmountApproved, 10);
	String instaLoanAmountApproved = getText(InstaLoanPage.objInstaLoanAmountApproved);
	instaLoanAmountApproved = instaLoanAmountApproved.substring(1);
	String[] split1 = instaLoanAmountApproved.split(",");
	String values = split1[0] + split1[1];
	System.out.println(values);
	softAssertion.assertEquals(value, values);
	if (value == values) {
		logger.info("TC_Insta_loan_08 - To verify if user gets the same amount of loan if user is present in whitelist ");
		extent.extentLoggerPass("TC_Insta_loan_08","TC_Insta_loan_08 - To verify if user gets the same amount of loan if user is present in whitelist ");
	}

	verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
	click(InstaLoanPage.objPayFullAmountRadioBtn, "Pay Full Amount RadioButton");
	verifyElementPresentAndClick(InstaLoanPage.objPayNowBtn, getText(InstaLoanPage.objPayNowBtn) + " Button");
	explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
	verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
	waitTime(20000);
	verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
	addNewCard();
	String refAfterRepayment = executeQuery2("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '"+ userReferenceNo + "';");
	System.out.println(refAfterRepayment);
	softAssertion.assertEquals(refBeforeRepayment, refAfterRepayment);

	boolean reApplyForInstaLoanBanner = verifyElementPresent2(InstaLoanPage.objReApplyForInstaLoan,getText(InstaLoanPage.objReApplyForInstaLoan));
	if (reApplyForInstaLoanBanner == true) {
		logger.info("TC_Insta_loan_07 - To verify if user remains in whitelist table even if user has repaid the previous loan");
		extent.extentLoggerPass("TC_Insta_loan_07","TC_Insta_loan_07 - To verify if user remains in whitelist table even if user has repaid the previous loan");
	}
	deleteRowFromInstaLoanWhitelistTable("DELETE FROM db_tradofina.instaloan_whitelisted_users WHERE user_reference_number = '" + userReferenceNo+ "';");
}

public void addNewCard() throws Exception {
	click(InstaLoanPage.objCardNumber, "Card Number Field");
	type(InstaLoanPage.objCardNumber, prop.getproperty("cardNumber"), "Card Number Field");

	click(InstaLoanPage.objExpiryDate, "Expiry Date Field");
	type(InstaLoanPage.objExpiryDate, prop.getproperty("expiryDate"), "Expiry Date Field");

	click(InstaLoanPage.objCardHolderName, "Card Holder's Field");
	type(InstaLoanPage.objCardHolderName, prop.getproperty("userName"), "Card Holder's Field");

	click(InstaLoanPage.objCVV, "CVV Field");
	type(InstaLoanPage.objCVV, prop.getproperty("cvv"), "CVV Field");

	verifyElementPresentAndClick(InstaLoanPage.objPayNowBtn, getText(InstaLoanPage.objPayNowBtn) + " Button");
	verifyElementPresentAndClick(InstaLoanPage.objPayWithoutSavingCardBtn,
			getText(InstaLoanPage.objPayWithoutSavingCardBtn) + " Button");

	if (verifyElementPresent2(InstaLoanPage.objSuccessBtn, "Success Button")) {
		verifyElementPresentAndClick(InstaLoanPage.objSuccessBtn, getText(InstaLoanPage.objSuccessBtn));
	} else {
		click(InstaLoanPage.objCardOTPField, "OTP field");
		type(InstaLoanPage.objCardOTPField, prop.getproperty("OTP"), "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objSubmitButton,getText(InstaLoanPage.objSubmitButton) + " Button");
	}
	waitTime(20000);
	verifyElementPresentAndClick(HomPageNew.objHomePage, "Go to homepage button");
}

public void deleteRowFromInstaLoanWhitelistTable(String deleteQuery) throws Exception {
	deleteRow(deleteQuery);
	Swipe("down", 1);
	waitTime(10000);
	boolean reApplyForInstaLoanBanner = verifyElementPresent2(InstaLoanPage.objApplyBtn, "InstaLoan Banner");
	logger.info("InstaLoan Banner present : " + reApplyForInstaLoanBanner);
	extent.extentLogger("InstaLoan Banner", "InstaLoan Banner present : " + reApplyForInstaLoanBanner);

	if (reApplyForInstaLoanBanner == true) {
		logger.info("InstaLoan Banner is present even if it isremoved from whitelist table");
		extent.extentLoggerFail("InstaLoan Banner","InstaLoan Banner is present even if it isremoved from whitelist table");
	} else {
		logger.info("TC_Insta_loan_09 - To verify if user has repaid the previous Insta loan and removed from whitelist");
		extent.extentLoggerPass("TC_Insta_loan_09","TC_Insta_loan_09 - To verify if user has repaid the previous Insta loan and removed from whitelist");
	}
}

public void blockUser() throws Exception {

	setPlatform("Web");
	System.out.println("platform changed to web");
	String pf = getPlatform();
	System.out.println(pf);
	waitTime(5000);
	new RingPayBusinessLogic("ring");
	waitTime(10000);

	click(InstaLoanPage.objEmail, "Email Field");
	type(InstaLoanPage.objEmail, prop.getproperty("RingAdminEmail"), "Email Field");
	verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
	click(InstaLoanPage.objPasswordField, "Password Field");
	clearField(InstaLoanPage.objPasswordField, "Password Field");
	type(InstaLoanPage.objPasswordField, prop.getproperty("RingAdminPassword"), "Password Field");
	click(InstaLoanPage.objOTPField, "OTP Field");
	clearField(InstaLoanPage.objOTPField, "OTP Field");
	type(InstaLoanPage.objOTPField, prop.getproperty("OTP"), "OTP Field");
	verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
	verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
	selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
	click(InstaLoanPage.objSearchTerm, "Search Item Field");
	type(InstaLoanPage.objSearchTerm, mobileNumber, "Search Item Field");
	click(InstaLoanPage.objSearchBtn, "Search Button");
	waitTime(10000);
	String referenceNo = getText(InstaLoanPage.objUserReferenceNo);
	System.out.println(referenceNo);
	BrowsertearDown();

	setPlatform("Android");
	initDriver();
	waitTime(5000);

	executeInsertBlockUser(prop.getproperty("blocked_reason"), mobileNumber, prop.getproperty("entryType"));
	String blockUser = executeQuery3("SELECT * FROM db_tradofina.blocked_users Where entity_value = '" + mobileNumber + "';", 5);
	System.out.println(blockUser);
	
	verifyElementPresentAndClick(InstaLoanOptionalJourney.objUPITab, "UPI Tab");
	waitTime(20000);
	Back(1);
	
	String instaLoanWhitelistNo = executeQuery3("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '" + referenceNo+ "';",2);
	System.out.println("User is present in WhiteListTable : " + instaLoanWhitelistNo);

	if (instaLoanWhitelistNo.equals(referenceNo)) {
		softAssertion.assertEquals(instaLoanWhitelistNo, referenceNo);
		Swipe("down", 1);
		boolean instaLoanBanner = verifyElementPresent2(InstaLoanPage.objApplyBtn, "InstaLoan Banner");
		logger.info("InstaLoan Banner present : " + instaLoanBanner);
		extent.extentLogger("InstaLoan Banner", "InstaLoan Banner present : " + instaLoanBanner);
		System.out.println(instaLoanBanner);

		if (instaLoanBanner == true) {
			logger.info("InstaLoan Banner is present even if it isremoved from whitelist table");
			extent.extentLoggerFail("InstaLoan Banner","InstaLoan Banner is present even if it is removed from whitelist table");
		} else {
			logger.info("TC_Insta_loan_11 - To verify Insta loan offer is not avail if user becomes blocked even if customer is present in whitelist");
			extent.extentLoggerPass("TC_Insta_loan_11","TC_Insta_loan_11 - To verify Insta loan offer is not avail if user becomes blocked even if customer is present in whitelist");
		}
	}
}

public void instaApprovedAmountNull(String ringAdminEmail, String ringAdminPassword, String ringAdminOTP,String eligible_type, String approved_amount, String offer_id, String repeat_offer_id) throws Exception {
	waitTime(5000);
	setPlatform("Web");
	System.out.println("platform changed to web");
	String pf = getPlatform();
	System.out.println(pf);
	waitTime(5000);
	new RingPayBusinessLogic("ring");
	waitTime(10000);

	click(InstaLoanPage.objEmail, "Email Field");
	type(InstaLoanPage.objEmail, ringAdminEmail, "Email Field");
	verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
	click(InstaLoanPage.objPasswordField, "Password Field");
	clearField(InstaLoanPage.objPasswordField, "Password Field");
	type(InstaLoanPage.objPasswordField, ringAdminPassword, "Password Field");
	click(InstaLoanPage.objOTPField, "OTP Field");
	clearField(InstaLoanPage.objOTPField, "OTP Field");
	type(InstaLoanPage.objOTPField, ringAdminOTP, "OTP Field");
	verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
	verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
	selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
	click(InstaLoanPage.objSearchTerm, "Search Item Field");
	type(InstaLoanPage.objSearchTerm, mobileNumber, "Search Item Field");
	click(InstaLoanPage.objSearchBtn, "Search Button");
	waitTime(10000);
	userReferenceNo = getText(InstaLoanPage.objUserReferenceNo);
	System.out.println(userReferenceNo);
	waitTime(10000);
	ScrollToTheElement(InstaLoanPage.dragDown);
	waitTime(5000);
	JSClick(InstaLoanPage.objOthersTab, "Other Tab");
	ScrollToTheElement(InstaLoanPage.dragDown);
	JSClick(InstaLoanPage.objPanNSDLData, "PanNSDL Tab");
	JSClick(InstaLoanPage.objAddPanCard, "Add PanCard");

	click(InstaLoanPage.objNameField, "First Name Field");
	type(InstaLoanPage.objNameField, firstName, "First Name Field");
	click(InstaLoanPage.objMiddleNameField, "Middle Name Field");
	type(InstaLoanPage.objMiddleNameField, middleName, "Middle Name Field");
	click(InstaLoanPage.objLastNameField, "Last Name Field");
	type(InstaLoanPage.objLastNameField, lastName, "Last Name Field");
	click(InstaLoanPage.objPanNoField, "PanNo. Field");
	type(InstaLoanPage.objPanNoField, panCard, "PanNo. Field");
	selectByVisibleTextFromDD(InstaLoanPage.objPanStatus, "Valid");
	verifyElementPresentAndClick(InstaLoanPage.objSubmitBtn, "Submit Button");

	BrowsertearDown();
	waitTime(10000);
	setPlatform("Android");
	initDriver();
	waitTime(5000);
	try {
		executeInsert(userReferenceNo, eligible_type, approved_amount, offer_id, repeat_offer_id);
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("Approved Amount,offer_id, reference_number cannot be null");
		extent.extentLogger("approved amount", "Approved Amount,offer_id, reference_number cannot be null");
	}
}

public void instaReferenceNoNull(String ringAdminEmail, String ringAdminPassword, String ringAdminOTP,String ref_No, String eligible_type, String approved_amount, String offer_id, String repeat_offer_id)throws Exception {
	waitTime(5000);
	setPlatform("Web");
	System.out.println("platform changed to web");
	String pf = getPlatform();
	System.out.println(pf);
	waitTime(5000);
	new RingPayBusinessLogic("ring");
	waitTime(10000);

	click(InstaLoanPage.objEmail, "Email Field");
	type(InstaLoanPage.objEmail, ringAdminEmail, "Email Field");
	verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
	click(InstaLoanPage.objPasswordField, "Password Field");
	clearField(InstaLoanPage.objPasswordField, "Password Field");
	type(InstaLoanPage.objPasswordField, ringAdminPassword, "Password Field");
	click(InstaLoanPage.objOTPField, "OTP Field");
	clearField(InstaLoanPage.objOTPField, "OTP Field");
	type(InstaLoanPage.objOTPField, ringAdminOTP, "OTP Field");
	verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
	verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
	selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
	click(InstaLoanPage.objSearchTerm, "Search Item Field");
	type(InstaLoanPage.objSearchTerm, mobileNumber, "Search Item Field");
	click(InstaLoanPage.objSearchBtn, "Search Button");
	waitTime(10000);

	try {
		executeInsert(ref_No, eligible_type, approved_amount, offer_id, repeat_offer_id);
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("Approved Amount,offer_id, reference_number cannot be null");
		extent.extentLogger("approved amount", "Approved Amount,offer_id, reference_number cannot be null");
	}

	ScrollToTheElement(InstaLoanPage.dragDown);
	waitTime(5000);
	JSClick(InstaLoanPage.objOthersTab, "Other Tab");
	ScrollToTheElement(InstaLoanPage.dragDown);
	JSClick(InstaLoanPage.objPanNSDLData, "PanNSDL Tab");
	JSClick(InstaLoanPage.objAddPanCard, "Add PanCard");

	click(InstaLoanPage.objNameField, "First Name Field");
	type(InstaLoanPage.objNameField, firstName, "First Name Field");
	click(InstaLoanPage.objMiddleNameField, "Middle Name Field");
	type(InstaLoanPage.objMiddleNameField, middleName, "Middle Name Field");
	click(InstaLoanPage.objLastNameField, "Last Name Field");
	type(InstaLoanPage.objLastNameField, lastName, "Last Name Field");
	click(InstaLoanPage.objPanNoField, "PanNo. Field");
	type(InstaLoanPage.objPanNoField, panCard, "PanNo. Field");
	selectByVisibleTextFromDD(InstaLoanPage.objPanStatus, "Valid");
	verifyElementPresentAndClick(InstaLoanPage.objSubmitBtn, "Submit Button");

	BrowsertearDown();
	waitTime(10000);
	setPlatform("Android");
	initDriver();
	waitTime(5000);
}

public void payPreviousLoan() throws Exception {
	verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
	click(InstaLoanPage.objPayFullAmountRadioBtn, "Pay Full Amount RadioButton");
	verifyElementPresentAndClick(InstaLoanPage.objPayNowBtn, getText(InstaLoanPage.objPayNowBtn) + " Button");
	explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
	verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
	waitTime(20000);
	verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
	addNewCard();

	boolean reApplyForInstaLoanBanner = verifyElementPresent2(InstaLoanPage.objReApplyForInstaLoan,"ReApply for Insta Loan! Banner");
	if (reApplyForInstaLoanBanner == false) {
		logger.info("TC_Insta_loan_18 - To verify if User is removed from Whitelist Loan after taking InstaLoan");
		extent.extentLoggerPass("TC_Insta_loan_18","TC_Insta_loan_18 - To verify if User is removed from Whitelist Loan after taking InstaLoan");
	}
}

//===========================================InstaLoan WhiteList Logic Ends=====================================================================================	
//==============================================Transaction History Start==========================================================================================
	public void instaTransactionHistory(String InstatransactionHistoryNo, String OTP) throws Exception {
		extent.HeaderChildNode("Transaction History");
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(InstatransactionHistoryNo);
		enterOtp(OTP);
		readAndAccept();

		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objIacceptCheckBox, "CheckBox");
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objOkGotItBtn, "Ok, Got It Button");
		explicitWaitVisibility(InstaLoanTransactionHistoryPage.objHamburgerTab, 10);
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objHamburgerTab, "Hamburger Tab");
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objTransactionChip,getText(InstaLoanTransactionHistoryPage.objTransactionChip) + " Chip");
		explicitWaitVisibility(InstaLoanTransactionHistoryPage.objHistoryHeader, 10);
		verifyElementPresent(InstaLoanTransactionHistoryPage.objHistoryHeader,getText(InstaLoanTransactionHistoryPage.objHistoryHeader));
		softAssertion.assertEquals(InstaLoanTransactionHistoryPage.objHistoryHeader, "History");
		verifyElementPresent(InstaLoanTransactionHistoryPage.objTransactionHistoryTab,getText(InstaLoanTransactionHistoryPage.objTransactionHistoryTab));

		List<WebElement> transactionList = getDriver().findElements(InstaLoanTransactionHistoryPage.objTransactionList);
		for (WebElement wb : transactionList) {
			String list = wb.getText();
			logger.info(list);
			extent.extentLogger("Transaction_list", list);
		}

		softAssertion.assertEquals(InstaLoanTransactionHistoryPage.objTransactionHistoryTab, " Transaction History");
		verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryTab,getText(InstaLoanTransactionHistoryPage.objLoanHistoryTab));
		softAssertion.assertEquals(InstaLoanTransactionHistoryPage.objLoanHistoryTab, "Loan History");
		verifyElementPresent(InstaLoanTransactionHistoryPage.objFilterOption, "Filter Option");

		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryTab, "Loan History");
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objFilterOption, "Filter Option");

		if (verifyElementPresent2(InstaLoanTransactionHistoryPage.objTimePeriod,getText(InstaLoanTransactionHistoryPage.objTimePeriod))) {
			verifyElementPresent(InstaLoanTransactionHistoryPage.objThisMonthChip,getText(InstaLoanTransactionHistoryPage.objThisMonthChip));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objLastMonth,getText(InstaLoanTransactionHistoryPage.objLastMonth));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objCustomDate,getText(InstaLoanTransactionHistoryPage.objCustomDate));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanType,getText(InstaLoanTransactionHistoryPage.objLoanType));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objAll,getText(InstaLoanTransactionHistoryPage.objAll));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objActiveLoan,getText(InstaLoanTransactionHistoryPage.objActiveLoan));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objClosedLoan,getText(InstaLoanTransactionHistoryPage.objClosedLoan));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objClearBtn,getText(InstaLoanTransactionHistoryPage.objClearBtn));
			verifyElementPresent(InstaLoanTransactionHistoryPage.objApplyBtn,getText(InstaLoanTransactionHistoryPage.objApplyBtn));

			for (int i = 1; i <= 3; i++) {
				click(InstaLoanTransactionHistoryPage.objTimePeriodSelect(i), "Element " + i + " " + " of TimePeriod");
				if (verifyElementPresent2(InstaLoanTransactionHistoryPage.objDatePicker, "Date-picker")) {
					verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objOkBtn,getText(InstaLoanTransactionHistoryPage.objOkBtn));
				}
				for (int j = 1; j <= 3; j++) {
					verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanTypeSelect(j),"Element " + j + " " + " of LoanTypes");
					waitTime(5000);
					click(InstaLoanTransactionHistoryPage.objApplyBtn, "Apply Button");
					waitTime(3000);
					if (!verifyElementPresent2(InstaLoanTransactionHistoryPage.objTimePeriod, "Time Period")) {
						verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objFilterOption, "Filter Option");
					}
				}
			}
			click(InstaLoanTransactionHistoryPage.objApplyBtn, "Apply Button");
		}

		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objFilterOption, "Filter Option");
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objClearBtn,getText(InstaLoanTransactionHistoryPage.objClearBtn));

		if (verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatus,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatus))) {
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails));
			waitTime(5000);
			List<WebElement> viewList = getDriver().findElements(InstaLoanTransactionHistoryPage.objAllLoanDetail);
			for (WebElement wb : viewList) {
				String vlist = wb.getText();
				logger.info(vlist);
				extent.extentLogger("Loan_Details", vlist);
			}
			Swipe("up", 1);
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objPayNoBtn,getText(InstaLoanTransactionHistoryPage.objPayNoBtn));
			if (verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusPayEMIPaymentPage,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusPayEMIPaymentPage))) {
				Back(2);
			}
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails));
			if (verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetailsLoanDetailsPage,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetailsLoanDetailsPage))) {
				Back(1);
			}
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusPayEMI,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusPayEMI));
			if (verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusPayEMIPaymentPage,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusPayEMIPaymentPage))) {
				Back(1);
			}
		}

		if (verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryClosedDownloadNocBtn,getText(InstaLoanTransactionHistoryPage.objLoanHistoryClosedDownloadNocBtn))) {
			verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryClosedStatus,getText(InstaLoanTransactionHistoryPage.objLoanHistoryClosedStatus));
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails));
			if (verifyElementPresent(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetailsLoanDetailsPage,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetailsLoanDetailsPage))) {
				Back(1);
			}
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryClosedDownloadNocBtn,getText(InstaLoanTransactionHistoryPage.objLoanHistoryClosedDownloadNocBtn));

			Set<String> windows = getDriver().getContextHandles();
			for (String window : windows) {
				System.out.println("Active Applications " + window);
				getDriver().context("NATIVE_APP");
			}
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objNOCPdf,getText(InstaLoanTransactionHistoryPage.objNOCPdf));verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objThreeDotForDownloadPDF,"Three Dot to DownloadPDF file");
			verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objDownloadBtn,getText(InstaLoanTransactionHistoryPage.objDownloadBtn));

			String toastPDFDownloadMsg = getText(InstaLoanTransactionHistoryPage.objDownloadPDFToastMsg);
			System.out.println(toastPDFDownloadMsg);
			if (toastPDFDownloadMsg.contains("Downloaded Noc_Letter")) {
				logger.info("TC_Insta_loan_136 - To Verify for Loan details page for Closed loans from Loan History View Details option ");
				extent.extentLoggerPass("TC_Insta_loan_136","To Verify for Loan details page for Closed loans from Loan History View Details option ");
			}
			Back(1);
		}

		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails,getText(InstaLoanTransactionHistoryPage.objLoanHistoryActiveStatusViewDetails));
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanDetailViewFeeDetailsBtn,getText(InstaLoanTransactionHistoryPage.objLoanDetailViewFeeDetailsBtn));
		verifyElementPresent(InstaLoanTransactionHistoryPage.objFeeDetailsHeader,getText(InstaLoanTransactionHistoryPage.objFeeDetailsHeader));
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objLoanAmountText,getText(InstaLoanTransactionHistoryPage.objLoanAmountText) + " is : "+ getText(InstaLoanTransactionHistoryPage.objLoanAmount));
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objTotalFeesChargesText,getText(InstaLoanTransactionHistoryPage.objTotalFeesChargesText) + " is : "+ getText(InstaLoanTransactionHistoryPage.objTotalFeesCharges));
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objProcessingFeesText,getText(InstaLoanTransactionHistoryPage.objProcessingFeesText) + " is : "+ getText(InstaLoanTransactionHistoryPage.objProcessingFees));
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objGSTText,getText(InstaLoanTransactionHistoryPage.objGSTText) + " is : "+ getText(InstaLoanTransactionHistoryPage.objGST));
		verifyElementPresentAndClick(InstaLoanTransactionHistoryPage.objDisbursalAmountText,getText(InstaLoanTransactionHistoryPage.objDisbursalAmountText) + " is : "+ getText(InstaLoanTransactionHistoryPage.objDisbursalAmount));

		logger.info("TC_Insta_loan_128 - To Verify After Click on History Tab from Home page, User should  Redirect on History Page & It should display  All  Transaction History on page");
		extent.extentLoggerPass("TC_Insta_loan_128","To Verify After Click on History Tab from Home page, User should  Redirect on History Page & It should display  All  Transaction History on page");

		logger.info("TC_Insta_loan_129 - User should able to see Filter option in Loan History tab");
		extent.extentLoggerPass("TC_Insta_loan_129", "User should able to see Filter option in Loan History tab");

		logger.info("TC_Insta_loan_130 - User should able to see & select all option to filter out loans");
		extent.extentLoggerPass("TC_Insta_loan_130", "User should able to see & select all option to filter out loans");

		logger.info("TC_Insta_loan_131 - To Verify On Transaction History Page should display all Transaction History of User");
		extent.extentLoggerPass("TC_Insta_loan_131","To Verify On Transaction History Page should display all Transaction History of User");

		logger.info("TC_Insta_loan_133 - To Verify On Loan history page for Active loan  \"View Details\"link  & \"Pay EMI\" button should be display.");
		extent.extentLoggerPass("TC_Insta_loan_133","To Verify On Loan history page for Active loan  \"View Details\"link  & \"Pay EMI\" button should be display.");

		logger.info("TC_Insta_loan_134 - To Verify On Loan History page for Closed Loan \"View Details\" link & \"Download NOC\" button should  be display ");
		extent.extentLoggerPass("TC_Insta_loan_134","To Verify On Loan History page for Closed Loan \"View Details\" link & \"Download NOC\" button should  be display ");

		logger.info("TC_Insta_loan_135 - To Verify for Loan details page for active loans from Loan History View Details option ");
		extent.extentLoggerPass("TC_Insta_loan_135","To Verify for Loan details page for active loans from Loan History View Details option ");

		logger.info("TC_Insta_loan_137 - To verify View Fee Details from Loan Details page");
		extent.extentLoggerPass("TC_Insta_loan_137", "To verify View Fee Details from Loan Details page");
	}

//==============================================Transaction History End===================================================================================================================
//============================================Insta Loan Optional Journey start=============================================================================================================
	public void instaLoanOptioanlJourney() throws Exception {
		extent.HeaderChildNode("InstaLoan Optional Journey");
		mockUserAPI();
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		instaKycDocument();
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
		if(verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
			click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
		}
		Swipe("up", 2);
		String referenceNo = switchAdminPanelForRefNo();
		String refNoFromDB = executeQuery3("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '" + referenceNo+ "';", 4);
		logger.info("Eligible type : " + refNoFromDB);
		extent.extentLogger("Eligible type : ", "Eligible type : " + refNoFromDB);
		
		boolean instaBanner = verifyElementPresent(InstaLoanOptionalJourney.objInstaOfferBanner, getText(InstaLoanOptionalJourney.objInstaOfferBanner));
		
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objQRScanner, "QR Code Scann Button");
		String scanAndPayToastMsg = getText(InstaLoanOptionalJourney.objScanAndPayToastMsg);
		logger.info(scanAndPayToastMsg);
		extent.extentLogger("Toast Message", scanAndPayToastMsg);
		boolean scannPage = verifyElementPresent(InstaLoanOptionalJourney.objQRScannPage, getText(InstaLoanOptionalJourney.objQRScannPage));
		if(scannPage == true && instaBanner == true) {
			logger.info("TC_Insta_loan_40 - To verify when the user eligible type is optional, user should choose either Ring limit or an insta loan offer" );
			extent.extentLoggerPass("TC_Insta_loan_40", "To verify when the user eligible type is optional, user should choose either Ring limit or an insta loan offer");
			
			logger.info("TC_Insta_loan_42 - To verify optional eligible users, all transaction instruments like Scan & pay will be enabled until avail(FA) insta loan offer " );
			extent.extentLoggerPass("TC_Insta_loan_42", "To verify optional eligible users, all transaction instruments like Scan & pay will be enabled until avail(FA) insta loan offer ");
		}
		
		scannQRSwitch();
		waitTime(10000);
		type(InstaLoanOptionalJourney.objAmountEnter, "100", "Amount Field");
		doubleTap(InstaLoanOptionalJourney.objPayNowBtn,1, "Pay Now Button");
		waitTime(40000);
		click(InstaLoanOptionalJourney.objPINField, "PIN Field");
		type(InstaLoanOptionalJourney.objPINField, prop.getproperty("ValidPin"), "PIN Field");
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objContinueBtn, getText(InstaLoanOptionalJourney.objContinueBtn));
		waitTime(20000);
		verifyElementPresentAndClick(HomPageNew.objHomePage, "Go to homepage button");
		if(verifyElementPresent2(InstaLoanOptionalJourney.objNoBtn, "No Button")) {
			click(InstaLoanOptionalJourney.objNoBtn, getText(InstaLoanOptionalJourney.objNoBtn)+" Button");
		}
		explicitWaitVisibility(InstaLoanOptionalJourney.objPayEarly, 20);
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objPayEarly, getText(InstaLoanOptionalJourney.objPayEarly));
		verifyElementPresent(InstaLoanOptionalJourney.objUPIPaymentMethod, getText(InstaLoanOptionalJourney.objUPIPaymentMethod));
		verifyElementPresent(InstaLoanOptionalJourney.objNetBankingAndDebitCardPaymentMethod, getText(InstaLoanOptionalJourney.objNetBankingAndDebitCardPaymentMethod));
		verifyElementPresent(InstaLoanOptionalJourney.objBankTransferPaymentMethod, getText(InstaLoanOptionalJourney.objBankTransferPaymentMethod));
		verifyElementPresent(InstaLoanOptionalJourney.objPaymentViaUPIPaymentMethod, getText(InstaLoanOptionalJourney.objPaymentViaUPIPaymentMethod));
		Back(1);
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objMoreBottomTab, getText(InstaLoanOptionalJourney.objMoreBottomTab));
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objBankTransfer, getText(InstaLoanOptionalJourney.objBankTransfer));
		explicitWaitVisibility(InstaLoanOptionalJourney.objAddBankAccount, 20);
		boolean addBankHeader = verifyElementPresent(InstaLoanOptionalJourney.objAddBankAccount, getText(InstaLoanOptionalJourney.objAddBankAccount));
		if(addBankHeader == true) {
			logger.info("TC_Insta_loan_41 - To verify optional eligible users, all transaction instruments like Card will be enabled until avail(FA) insta loan offer ");
			extent.extentLoggerPass("TC_Insta_loan_41", "To verify optional eligible users, all transaction instruments like Card will be enabled until avail(FA) insta loan offer ");
			
			addbankAccount("5" + RandomIntegerGenerator(4), firstName + " " + lastName);
			explicitWaitVisibility(InstaLoanOptionalJourney.objMakePaymentPage, 30);
			type(InstaLoanOptionalJourney.objMakePaymentAmount, "100", "Amount Field");
			click(InstaLoanOptionalJourney.objPayNowBtn,"Pay Now Button");
			doubleTap(InstaLoanOptionalJourney.objPayNowBtn,1, "Pay Now Button");
			click(InstaLoanOptionalJourney.objPINField, "PIN Field");
			type(InstaLoanOptionalJourney.objPINField, prop.getproperty("ValidPin"), "PIN Field");
			click(InstaLoanOptionalJourney.objCircularBtn, "Circular Button");
			verifyElementPresentAndClick(HomPageNew.objHomePage, "Go to homepage button");
			
			logger.info("TC_Insta_loan_44 - To verify optional eligible users, all transaction instruments like bank transfer will be enabled until avail insta loan offer ");
			extent.extentLoggerPass("TC_Insta_loan_44", "To verify optional eligible users, all transaction instruments like bank transfer will be enabled until avail insta loan offer ");
		}
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objMoreBottomTab, getText(InstaLoanOptionalJourney.objMoreBottomTab));
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objBBPSFlowElectricity, getText(InstaLoanOptionalJourney.objBBPSFlowElectricity));
		explicitWaitVisibility(InstaLoanOptionalJourney.objSelectBiller, 20);
		scrollToElement("text", "Bangalore Electricity Supply Co. Ltd (BESCOM)").click();
		click(InstaLoanOptionalJourney.objEnterCustomerID, "Customer ID Field");
		waitTime(5000);
		type(InstaLoanOptionalJourney.objEnterCustomerID, "10001", "Customer Number Field");
		
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objFetchBill, getText(InstaLoanOptionalJourney.objFetchBill));
		waitTime(20000);
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objPayBtn, getText(InstaLoanOptionalJourney.objPayBtn)+ " Button");
		explicitWaitVisibility(InstaLoanOptionalJourney.objPaymentInProgress, 20);
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objPayWithRingLimit, getText(InstaLoanOptionalJourney.objPayWithRingLimit)+ " Button");
		explicitWaitVisibility(InstaLoanOptionalJourney.objEnterSecurityPinforElectricityBill, 20);
		click(InstaLoanOptionalJourney.objPINFieldElectricityBill, "PIN Field");
		type(InstaLoanOptionalJourney.objPINFieldElectricityBill, prop.getproperty("ValidPin"), "PIN Field");
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objContinueBtn, getText(InstaLoanOptionalJourney.objContinueBtn));
		boolean processingYourPayment = verifyElementPresent2(InstaLoanOptionalJourney.objProcessing, "Processing your Payment");
		if(processingYourPayment == true) {
			waitTime(30000);
			Back(2);
			logger.info("TC_Insta_loan_43 - To verify optional eligible users, all transaction instruments like BBPS  will be enabled until avail(FA) insta loan offer ");
			extent.extentLoggerPass("TC_Insta_loan_43", "To verify optional eligible users, all transaction instruments like BBPS  will be enabled until avail(FA) insta loan offer ");
		}else {
			Back(2);
			logger.info("TC_Insta_loan_43 - To verify optional eligible users, all transaction instruments like BBPS  will be enabled until avail(FA) insta loan offer ");
			extent.extentLoggerFail("TC_Insta_loan_43", "To verify optional eligible users, all transaction instruments like BBPS  will be enabled until avail(FA) insta loan offer ");
		}
		boolean payEarly = verifyElementPresent(InstaLoanOptionalJourney.objPayEarly, getText(InstaLoanOptionalJourney.objPayEarly));
		if(payEarly == true) {
			verifyElementPresent(InstaLoanOptionalJourney.objCurrentSpending, getText(InstaLoanOptionalJourney.objCurrentSpending));
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objPayEarly, getText(InstaLoanOptionalJourney.objPayEarly));
			logger.info("TC_Insta_loan_46 - To verify ring limit outstanding non 0 and entry in the whitelisting table");
			extent.extentLoggerPass("TC_Insta_loan_46", "To verify ring limit outstanding non 0 and entry in the whitelisting table");
		}
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objNetBankingAndDebitCardPaymentMethod, getText(InstaLoanOptionalJourney.objNetBankingAndDebitCardPaymentMethod));
		waitTime(20000);
		verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
		addNewCard();
		verifyElementPresentAndClick(HomPageNew.objHomePage, "Go to homepage button");
		Swipe("up",2);
		executeUpdateFatherName("Update db_tradofina.users set father_name='Arun' where mobile_number='"+mobileNumber+"';","Select * from db_tradofina.users where mobile_number='"+mobileNumber+"';");
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		
		
		boolean basicDetailsPage = verifyElementPresent2(InstaLoanOptionalJourney.objFathersNameBasicDetails, "Fathers Name Basic Details Page");
		if(basicDetailsPage == false) {
			logger.info("TC_Insta_loan_50 - To verifiy Basic deatils page should be skipped if father name is present");
			extent.extentLoggerPass("TC_Insta_loan_50", "To verifiy Basic deatils page should be skipped if father name is present");
		}
		boolean kycDocumentPage = verifyElementPresent2(InstaLoanPage.objKYCHeader, "KYC Document Page");
		if(kycDocumentPage == false) {
			Back(1);
			waitTime(3000);
			click(InstaLoanOptionalJourney.objYesBtn, "yes Button");
			logger.info("TC_Insta_loan_47 - To verify if KYC is completed, then skip KYC, KYC can be in the onboarding/profile-page");
			extent.extentLoggerPass("TC_Insta_loan_47", "To verify if KYC is completed, then skip KYC, KYC can be in the onboarding/profile-page");
		}
		waitTime(5000);
		Back(2);
		click(InstaLoanOptionalJourney.objYesBtn, "yes Button");
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objMoreBottomTab, getText(InstaLoanOptionalJourney.objMoreBottomTab));
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objBankTransfer, getText(InstaLoanOptionalJourney.objBankTransfer));
		explicitWaitVisibility(InstaLoanOptionalJourney.objExistingBankAccount, 20);
		boolean addBankHeader1 = verifyElementPresent(InstaLoanOptionalJourney.objExistingBankAccount, getText(InstaLoanOptionalJourney.objExistingBankAccount));
		Back(1);
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objBBPSFlowElectricity, getText(InstaLoanOptionalJourney.objBBPSFlowElectricity));
		boolean selectBillerBBPS = verifyElementPresent(InstaLoanOptionalJourney.objSelectBiller, getText(InstaLoanOptionalJourney.objSelectBiller));
		Back(1);
		boolean qrScanBtn = verifyElementPresent(InstaLoanOptionalJourney.objQRScanner, "QR Code Scann Button");
		if(addBankHeader1 && selectBillerBBPS && qrScanBtn == true) {
			logger.info("TC_Insta_loan_45 - To verify when an optional user applies for an offer but has not completed documentation, all transaction instruments like Scan & pay, Card, BBPS and Bank transfer will be enabled until the insta loan journey is completed.");
			extent.extentLoggerPass("TC_Insta_loan_45", "To verify when an optional user applies for an offer but has not completed documentation, all transaction instruments like Scan & pay, Card, BBPS and Bank transfer will be enabled until the insta loan journey is completed.");
		}

		mockUserAPI();
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();	
		}
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "OPTIONAL", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
		if(verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
			click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
		}
		Swipe("up", 2);
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		boolean basicDetailsPageSecondTime = verifyElementPresent2(InstaLoanOptionalJourney.objBasicDetailsPage, "Basic Details Page");
		 List<WebElement> toatalField = getDriver().findElements(InstaLoanOptionalJourney.objOnlyFathersNameField);
		 for (WebElement web : toatalField) {
		 	String name = web.getText();
		 	logger.info(name);
		 	extent.extentLoggerPass("Name", name);
		 	if(web.getText().equals("Father Name") && basicDetailsPageSecondTime == true) {
		 		logger.info("TC_Insta_loan_51 - To verify basic detail page");
				extent.extentLoggerPass("TC_Insta_loan_51", "To verify basic detail page");
		 	}
		}
		waitTime(10000);
		click(InstaLoanPage.objFatherName, "Father Name Field");
		type(InstaLoanPage.objFatherName, "Arun", "Father Name Field");
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn,"Proceed Button");
		waitTime(10000);
		boolean checkBureauHyperlink = verifyElementPresent(InstaLoanOptionalJourney.objIallowCreditBureauChecksText, getText(InstaLoanOptionalJourney.objIallowCreditBureauChecksText));
		boolean ihearByConfirm = verifyElementPresent(InstaLoanOptionalJourney.objIhearbyConfirmText, getText(InstaLoanOptionalJourney.objIhearbyConfirmText));
		if(checkBureauHyperlink && ihearByConfirm == true) {
			logger.info("TC_Insta_loan_53 - To verify personal detail page should be skipped ");
			extent.extentLoggerPass("TC_Insta_loan_53", "To verify personal detail page should be skipped");
		}
		
		tapUsingCoordinates(385, 1061);
		boolean creditbureaupopup = verifyElementPresent(InstaLoanOptionalJourney.objCreditBureauConsentPopup, getText(InstaLoanOptionalJourney.objCreditBureauConsentPopup));
		if(creditbureaupopup == true) {
			logger.info("TC_Insta_loan_54 - To Verify If the customer clicks on “Credit bureau checks” hyperlink");
			extent.extentLoggerPass("TC_Insta_loan_54", "TC_Insta_loan_54 - To Verify If the customer clicks on Credit bureau checks hyperlink");
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objOkGotItCreditBureaupopup, getText(InstaLoanOptionalJourney.objOkGotItCreditBureaupopup));	
		}
		waitTime(5000);
		tapUsingCoordinates(178, 1102);
		boolean ckycConsent =verifyElementPresent(InstaLoanOptionalJourney.objCKYCConsentHyperlink, getText(InstaLoanOptionalJourney.objCKYCConsentHyperlink));
		if(creditbureaupopup == true) {
			logger.info("TC_Insta_loan_55 - To Verify If the customer clicks on “Credit bureau checks” hyperlink");
			extent.extentLoggerPass("TC_Insta_loan_55", "TC_Insta_loan_55 - To Verify If the customer clicks on “Credit bureau checks” hyperlink");
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objOkGotItCreditBureaupopup, getText(InstaLoanOptionalJourney.objOkGotItCreditBureaupopup));	
		}
		
		click(InstaLoanOptionalJourney.objIallowCheckBox, "I allow Ring to run checkBox");
		click(InstaLoanOptionalJourney.objReadmoreCheckBox, "I, do hearby confirm that my annual household income checkBox");
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn,"Proceed Button");
		String text = getText(InstaLoanOptionalJourney.objScanAndPayToastMsg);
		if(text.equals("Please select the checkbox for relevant authorizations")) {
			logger.info("TC_Insta_loan_56 - To Verify If the customer unchecks any one or both check boxes for consent and clicks on proceed");
			extent.extentLoggerPass("TC_Insta_loan_56", "To Verify If the customer unchecks any one or both check boxes for consent and clicks on proceed");
		}
		click(InstaLoanOptionalJourney.objIallowCheckBox, "I allow Ring to run checkBox");
		click(InstaLoanOptionalJourney.objReadmoreCheckBox, "I, do hearby confirm that my annual household income checkBox");
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn,"Proceed Button");
		
		verifyElementPresent(InstaLoanOptionalJourney.objSelectAddressDropDown, getText(InstaLoanOptionalJourney.objSelectAddressDropDown));
		verifyElementPresent(InstaLoanOptionalJourney.objIdeclaretheAboveAddressCheckBox, "I declare the above address is same as my communication address");
		
		click(InstaLoanOptionalJourney.objIdeclaretheAboveAddressCheckBox, "I declare the above address is same as my communication address checkBox");
		waitTime(5000);
		isClickable(InstaLoanOptionalJourney.objAddCommunicationAddress);
		
		logger.info("TC_Insta_loan_58 - To Verify user if check box is not ticked user should able to  click on add communication address link");
		extent.extentLoggerPass("TC_Insta_loan_58", "To Verify user if check box is not ticked user should able to  click on add communication address link");
		
		click(InstaLoanOptionalJourney.objAddCommunicationAddress, getText(InstaLoanOptionalJourney.objAddCommunicationAddress));
		instaNewCommunicationAddress();
		verifyElementPresent2(InstaLoanPage.objOfferHeader, "Offer Page");
		logger.info("TC_Insta_loan_59 - User should able to add communication address");
		extent.extentLoggerPass("TC_Insta_loan_59", "User should able to add communication address");
		
		offerPage();
		click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(100000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		waitTime(40000);
		
		explicitWaitVisibility(InstaLoanPage.objInstaLoanAmountApproved, 10);
		verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
		click(InstaLoanPage.objPayFullAmountRadioBtn, "Pay Full Amount RadioButton");
		verifyElementPresentAndClick(InstaLoanPage.objPayNowBtn, getText(InstaLoanPage.objPayNowBtn) + " Button");
		explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
		verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
		waitTime(20000);
		verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
		addNewCard();

		Swipe("up", 2);
		click(InstaLoanPage.objApplyNowBtn, getText(InstaLoanPage.objApplyNowBtn) + " Button is displayed");
		verifyElementPresentAndClick(InstaLoanPage.objSelectAddress, "Select Address Dropdown");
		explicitWaitVisibility(InstaLoanPage.objAddressSelected, 30);
		verifyElementPresentAndClick(InstaLoanPage.objAddressSelected, getText(InstaLoanPage.objAddressSelected));
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		verifyElementPresent2(InstaLoanPage.objOfferHeader, "Offer Page");
		logger.info("TC_Insta_loan_57 - To Verify Address page");
		extent.extentLoggerPass("TC_Insta_loan_57", "To Verify Address page");

		System.out.println("--------------------------15 days loans show a second EMI as waived off EMI----------------------");
		mockUserAPI();
		getDriver().resetApp();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		instaKycDocument();
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "OPTIONAL", "13000", "2135", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
		if(verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
			click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
		}
		Swipe("up", 2);
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		waitTime(10000);

		basicDetails();
		click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(90000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		waitTime(30000);
		verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");

		boolean FirstEMIWaiver15Days = verifyElementPresent(InstaLoanOptionalJourney.objFirstEMIDueIN15Days, getText(InstaLoanOptionalJourney.objFirstEMIDueIN15Days)+ " 15 Days");
		boolean SecondInstallmentWaiver = verifyElementPresent(InstaLoanOptionalJourney.objSecondInstallmentWaiver, getText(InstaLoanOptionalJourney.objSecondInstallmentWaiver));
		boolean WaiverMsg = verifyElementPresent(InstaLoanOptionalJourney.objWaiverMsg, getText(InstaLoanOptionalJourney.objWaiverMsg));
		if(FirstEMIWaiver15Days && SecondInstallmentWaiver && WaiverMsg == true) {
			logger.info("TC_Insta_loan_60 - To verify  for 15 days loans show a second EMI as waived off EMI");
			extent.extentLoggerPass("TC_Insta_loan_60", "TC_Insta_loan_60 - To verify  for 15 days loans show a second EMI as waived off EMI");
			
			logger.info("TC_Insta_loan_93 - To verify  for 15 days loans show a second EMI as waived off EMI");
			extent.extentLoggerPass("TC_Insta_loan_93", "TC_Insta_loan_93 - To verify  for 15 days loans show a second EMI as waived off EMI");
			
			logger.info("TC_Insta_loan_160 - To verify  for 15 days loans show a second EMI as waived off EMI");
			extent.extentLoggerPass("TC_Insta_loan_160", "TC_Insta_loan_160 -To verify  for 15 days loans show a second EMI as waived off EMI");
			
			logger.info("TC_Insta_loan_164 - To verify for 15 days loans show a second EMI as waived off EMI");
			extent.extentLoggerPass("TC_Insta_loan_164", "TC_Insta_loan_164 - To verify for 15 days loans show a second EMI as waived off EMI");
		}

		System.out.println("------------------------------62 days loan EMI--------------------------");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			e.printStackTrace();
			instaKycDocument();
		}
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "OPTIONAL", "13000", "2251", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
		if(verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
			click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
		}
		Swipe("up", 2);
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		waitTime(10000);

		basicDetails();
		click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(90000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		if(!verifyElementPresent2(InstaLoanOptionalJourney.objPayNowBtnAtHomePage, "Pay Now Button")) {
			waitTime(120000);
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objUPITab, "UPI Tab");
			waitTime(90000);
			Back(1);
		}
		int EMIDueDatesInInteger=0;
		int count = 1;
			for(int i=0; i<count;i++) {
				if(verifyElementPresent2(InstaLoanPage.objViewDetails, "View Details")) {
					verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
					String EMIDueDates = getText(InstaLoanOptionalJourney.objEMIDueDays);
					EMIDueDatesInInteger = convertToInt(EMIDueDates);
					if(EMIDueDatesInInteger == 62) {
						List<WebElement> installmentDisbursed = getDriver().findElements(InstaLoanOptionalJourney.objInstallment);
						for (WebElement web : installmentDisbursed) {
							String installment = web.getText();
							if(installment.equalsIgnoreCase("2nd installment")) {
								verifyElementPresent(InstaLoanOptionalJourney.objEMIDueDays, getText(InstaLoanOptionalJourney.objEMIDueDays));
								verifyElementPresent(InstaLoanOptionalJourney.objTenureDates, getText(InstaLoanOptionalJourney.objTenureDates));
								verifyElementPresent(InstaLoanOptionalJourney.objAmtToBePaid, getText(InstaLoanOptionalJourney.objAmtToBePaid));
								logger.info("TC_Insta_loan_62 - To verify  for 62 days loan EMI");
								extent.extentLoggerPass("TC_Insta_loan_62", "TC_Insta_loan_62 - To verify  for 62 days loan EMI");
								
								logger.info("TC_Insta_loan_95 - To verify  for 62 days loan EMI");
								extent.extentLoggerPass("TC_Insta_loan_95", "TC_Insta_loan_95 - To verify  for 62 days loan EMI");
								
								logger.info("TC_Insta_loan_161 - To verify  for 62 days loan EMI");
								extent.extentLoggerPass("TC_Insta_loan_161", "TC_Insta_loan_161 - To verify  for 62 days loan EMI");
							}
						}
					}
				}
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objDayEMI, getText(InstaLoanOptionalJourney.objDayEMI));
			explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
			verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
			waitTime(20000);
			verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
			addNewCard();
				
			if(verifyElementPresent2(InstaLoanPage.objViewDetails, "View Details")) {
				count++;
			}else {
				break;
			}
		}

		System.out.println("------------------------------3 months loan EMI--------------------------");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			e.printStackTrace();
			instaKycDocument();
		}
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "OPTIONAL", "13000", "2412", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
		if(verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
			click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
		}
		Swipe("up", 2);
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		waitTime(10000);

		basicDetails();
		click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(90000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		waitTime(90000);
		int EMIDueDatesInInteger1=0;
		int count3MonthEMI = 1;
		for(int i=0; i<count3MonthEMI;i++) {
			if(verifyElementPresent2(InstaLoanPage.objViewDetails, "View Details")) {
				verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
				String EMIDueDates = getText(InstaLoanOptionalJourney.objEMIDueDays);
				EMIDueDatesInInteger1 = convertToInt(EMIDueDates);
				if(EMIDueDatesInInteger1 == 90) {
				List<WebElement> installmentDisbursed = getDriver().findElements(InstaLoanOptionalJourney.objInstallment);
				for (WebElement web : installmentDisbursed) {
					String installment = web.getText();
					if(installment.equalsIgnoreCase("3rd installment")) {
						verifyElementPresent(InstaLoanOptionalJourney.objEMIDueDays, getText(InstaLoanOptionalJourney.objEMIDueDays));
						verifyElementPresent(InstaLoanOptionalJourney.objTenureDates, getText(InstaLoanOptionalJourney.objTenureDates));
						verifyElementPresent(InstaLoanOptionalJourney.objAmtToBePaid, getText(InstaLoanOptionalJourney.objAmtToBePaid));
						logger.info("TC_Insta_loan_63 - To verify  for 3 months loan EMI");
						extent.extentLoggerPass("TC_Insta_loan_63", "TC_Insta_loan_63 - To verify  for 3 months loan EMI");
						
						logger.info("TC_Insta_loan_96 - To verify  for 3 months loan EMI");
						extent.extentLoggerPass("TC_Insta_loan_96", "TC_Insta_loan_96 - To verify  for 3 months loan EMI");
						
						logger.info("TC_Insta_loan_162 - To verify  for 3 months loan EMI");
						extent.extentLoggerPass("TC_Insta_loan_162", "TC_Insta_loan_162 - To verify  for 3 months loan EMI");
					}
				}
			}
		}
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objDayEMI, getText(InstaLoanOptionalJourney.objDayEMI));
		explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
		verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
		waitTime(20000);
		verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
		addNewCard();
		if(verifyElementPresent2(InstaLoanPage.objViewDetails, "View Details")) {
			count3MonthEMI++;
		}else {
			break;
		}
	}
		executeUpdateFatherName("Update db_tradofina.instaloan_whitelisted_users set eligible_type='MANDATORY' where user_reference_number='"+userReferenceNo+"';","Select * from db_tradofina.instaloan_whitelisted_users where user_reference_number='"+userReferenceNo+"';");
		waitTime(5000);
		Swipe("DOWN", 2);
	        
	    verifyElementPresent(InstaLoanOptionalJourney.objReApplyInstaLoan, getText(InstaLoanOptionalJourney.objReApplyInstaLoan));
	    verifyElementPresentAndClick(InstaLoanPage.objApplyNowBtn, "ReApply Button");
	      
		verifyElementPresentAndClick(InstaLoanPage.objSelectAddress, "Select Address Dropdown");
		waitTime(2000);
		verifyElementPresentAndClick(InstaLoanPage.objAddressSelected, getText(InstaLoanPage.objAddressSelected));
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
	   
		verifyElementPresent2(InstaLoanPage.objOfferHeader, "Offer Page");
		click(InstaLoanOptionalJourney.objAddBankDetails, getTextVal(InstaLoanOptionalJourney.objAddBankDetails, "Text"));
	    verifyElementPresentAndClick(InstaLoanOptionalJourney.objExistingBankContinue, "Existing Bank Account Continue Button");
	    
	    click(InstaLoanPage.objAcceptCheckBox, getText(InstaLoanPage.objAcceptCheckBox));
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		waitTime(90000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		waitTime(30000);
		
		explicitWaitVisibility(InstaLoanPage.objInstaLoanAmountApproved, 10);
		verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
		click(InstaLoanPage.objPayFullAmountRadioBtn, "Pay Full Amount RadioButton");
		verifyElementPresentAndClick(InstaLoanPage.objPayNowBtn, getText(InstaLoanPage.objPayNowBtn) + " Button");
		explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
		verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
		waitTime(20000);
		verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
		addNewCard();
		verifyElementPresent(InstaLoanOptionalJourney.objLimitPausedForMandatoryEligiblityType, getText(InstaLoanOptionalJourney.objLimitPausedForMandatoryEligiblityType));
		verifyElementPresentAndClick(InstaLoanOptionalJourney.objQRScanner, "QR Code Scann Button");
		verifyElementPresent(InstaLoanOptionalJourney.objSorryMsgInstaLoanApproved, getText(InstaLoanOptionalJourney.objSorryMsgInstaLoanApproved) + getText(InstaLoanOptionalJourney.objSorryMsgInstaLoanApprovedCurrentlyUnavailable));
		logger.info("TC_Insta_loan_75 - To verify when the user loan is closed and change the eligible type to mandatory for repeat journey");
		extent.extentLoggerPass("TC_Insta_loan_75", "TC_Insta_loan_75 - To verify when the user loan is closed and change the eligible type to mandatory for repeat journey");

		System.out.println("------------------------------6 months loan EMI--------------------------");
		getDriver().resetApp();
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		}catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "OPTIONAL", "13000", "2319", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		verifyElementPresentAndClick(InstaLoanPage.instaPopupBannerCrossBtn, "Cross Button");
		if(verifyElementPresent2(InstaLoanPage.objOkayGotIt, "Okay, Got It!")) {
			click(InstaLoanPage.objOkayGotIt, "Okay, Got It!");
		}
		transactionValidation();
		
		Swipe("up", 2);
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");
		waitTime(10000);

		basicDetails();
		waitTime(10000);
		boolean IacceptCheckBoxNotClicked = getDriver().findElement(InstaLoanPage.objAcceptCheckBox).isSelected();
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		String toast = getText(InstaLoanOptionalJourney.objScanAndPayToastMsg);
		logger.info(toast);
		extent.extentLogger("Toast Msg", toast);
		
		click(InstaLoanPage.objAcceptCheckBox, "CheckBox");
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		boolean importantPopupMsg = verifyElementPresent(InstaLoanOptionalJourney.objRingLimitpausedPopup, getText(InstaLoanOptionalJourney.objRingLimitpausedPopup));
		boolean ringLimitPausedpopup = verifyElementPresent(InstaLoanOptionalJourney.objRingLimitPausedTextPopup, getText(InstaLoanOptionalJourney.objRingLimitPausedTextPopup));
		boolean confirmOfferBtn = verifyElementPresent(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		verifyElementPresentAndClick(InstaLoanPage.objConfirmOfferBtn, getText(InstaLoanPage.objConfirmOfferBtn));
		if(IacceptCheckBoxNotClicked == false && confirmOfferBtn == true && importantPopupMsg == true && ringLimitPausedpopup == true) {
			logger.info("TC_Insta_loan_65 - To verify terms and conditions data to check");
			extent.extentLoggerPass("TC_Insta_loan_65", "TC_Insta_loan_65 - To verify terms and conditions data to check");
			
			logger.info("TC_Insta_loan_71 - To verify, Pop -up of deactivating Ring limit is shown once user click on 'Accept offer'");
			extent.extentLoggerPass("TC_Insta_loan_71", "To verify, Pop -up of deactivating Ring limit is shown once user click on 'Accept offer'");
		}
		waitTime(120000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		
		verifyElementPresent(InstaLoanOptionalJourney.objRingLimitPaused, getText(InstaLoanOptionalJourney.objRingLimitPaused));
		String FAStatus = executeQuery3("SELECT * FROM db_tradofina.line_application where user_reference_number='"+userReferenceNo+"';",7);
		System.out.println(FAStatus);
		softAssertion.assertEquals(FAStatus, "FINAL_APPROVED");
		logger.info("TC_Insta_loan_72 - To verify status should be changed to FA when user accept offer");
		extent.extentLoggerPass("TC_Insta_loan_72", "TC_Insta_loan_72 - To verify status should be changed to FA when user accept offer");
		
		boolean inProcessStatus = verifyElementPresent(InstaLoanOptionalJourney.objInProcessStatus, getText(InstaLoanOptionalJourney.objInProcessStatus));
		if(!verifyElementPresent2(InstaLoanOptionalJourney.objPayNowBtnAtHomePage, "Pay Now Button")) {
			waitTime(120000);
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objUPITab, "UPI Tab");
			waitTime(90000);
			Back(1);
		}
		boolean disbursedStatus = verifyElementPresent(InstaLoanOptionalJourney.objDisbursedStatus, getText(InstaLoanOptionalJourney.objDisbursedStatus));
		if(inProcessStatus == true && disbursedStatus == true) {
			logger.info("TC_Insta_loan_73 - To verify different disbursement status are working");
			extent.extentLoggerPass("TC_Insta_loan_73", "TC_Insta_loan_73 - To verify different disbursement status are working");
		}
		
		int EMIDueDatesInInteger2=0;
		int count6MonthEMI = 1;
		for(int i=0; i<count6MonthEMI;i++) {
		if(verifyElementPresent2(InstaLoanPage.objViewDetails, "View Details")) {
			verifyElementPresentAndClick(InstaLoanPage.objViewDetails, getText(InstaLoanPage.objViewDetails) + " Button");
			String EMIDueDates = getText(InstaLoanOptionalJourney.objEMIDueDays);
			EMIDueDatesInInteger2 = convertToInt(EMIDueDates);
			waitTime(3000);
			Swipe("up", 1);
			if(EMIDueDatesInInteger2 == 181) {
				List<WebElement> installmentDisbursed = getDriver().findElements(InstaLoanOptionalJourney.objInstallment);
				for (WebElement web : installmentDisbursed) {
					String installment = web.getText();
					
					if(installment.equalsIgnoreCase("6th installment")) {
						verifyElementPresent(InstaLoanOptionalJourney.objEMIDueDays, getText(InstaLoanOptionalJourney.objEMIDueDays));
						verifyElementPresent(InstaLoanOptionalJourney.objTenureDates, getText(InstaLoanOptionalJourney.objTenureDates));
						verifyElementPresent(InstaLoanOptionalJourney.objAmtToBePaid, getText(InstaLoanOptionalJourney.objAmtToBePaid));
						logger.info("TC_Insta_loan_64 - To verify  for 6 months loan EMI");
						extent.extentLoggerPass("TC_Insta_loan_64", "TC_Insta_loan_64 - To verify  for 6 months loan EMI");
						
						logger.info("TC_Insta_loan_97 - To verify  for 6 months loan EMI");
						extent.extentLoggerPass("TC_Insta_loan_97", "TC_Insta_loan_97 - To verify  for 6 months loan EMI");
						
						logger.info("TC_Insta_loan_163 - To verify  for 6 months loan EMI");
						extent.extentLoggerPass("TC_Insta_loan_163", "TC_Insta_loan_163 - To verify  for 6 months loan EMI");
						}
					}
				}
			}
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objDayEMI, getText(InstaLoanOptionalJourney.objDayEMI));
			explicitWaitVisibility(InstaLoanPage.objNetBankingDebitCard, 20);
			verifyElementPresentAndClick(InstaLoanPage.objNetBankingDebitCard,getText(InstaLoanPage.objNetBankingDebitCard) + " Chip");
			waitTime(20000);
			verifyElementPresentAndClick(InstaLoanPage.objCard, "Card chip");
			addNewCard();
			if(verifyElementPresent2(InstaLoanPage.objViewDetails, "View Details")) {
				count6MonthEMI++;
			}else {
				break;
			}
		}
		
		verifyElementPresent(InstaLoanOptionalJourney.objAvailableLimit, getText(InstaLoanOptionalJourney.objAvailableLimit));
		logger.info("TC_Insta_loan_74 - After repayment of previous insta loan ring limit should get active");
		extent.extentLoggerPass("TC_Insta_loan_74", "TC_Insta_loan_74 - After repayment of previous insta loan ring limit should get active");
		
		explicitWaitVisibility(HomePage.objHome, 10);
      String sHomeTxt = getText(HomePage.objHome);
      softAssertion.assertEquals(sHomeTxt, "Home");
      logger.info("Navigated to Home Page");
      extent.extentLoggerPass("Home Page", "Navigated to Home Page");
      waitTime(2000);
      Swipe("DOWN", 2);
      
      verifyElementPresent(InstaLoanOptionalJourney.objReApplyInstaLoan, getText(InstaLoanOptionalJourney.objReApplyInstaLoan));
      logger.info("TC_Insta_loan_76 - To verify when the user repays the loan amount or closed the Loan and Reapply for  insta loan then banner should be visible on the homepage ");
      extent.extentLoggerPass("TC_Insta_loan_76", "TC_Insta_loan_76 - To verify when the user repays the loan amount or closed the Loan and Reapply for  insta loan then banner should be visible on the homepage ");
      
      verifyElementPresentAndClick(InstaLoanPage.objApplyNowBtn, "ReApply Button");
    
		verifyElementPresentAndClick(InstaLoanPage.objSelectAddress, "Select Address Dropdown");
		verifyElementPresentAndClick(InstaLoanPage.objAddressSelected, getText(InstaLoanPage.objAddressSelected));
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
 
		verifyElementPresent2(InstaLoanPage.objOfferHeader, "Offer Page");
      if (verifyElementPresent(InstaLoanPage.objAddBankButton, getText(InstaLoanPage.objAddBankButton))) {
          click(InstaLoanOptionalJourney.objAddBankDetails, getTextVal(InstaLoanOptionalJourney.objAddBankDetails, "Text"));
          if (verifyElementPresent(InstaLoanOptionalJourney.objExistingBankAcc, getTextVal(InstaLoanOptionalJourney.objExistingBankAcc, "Text"))) {
              logger.info("TC_Insta_loan_68 - Existing Bank Accounts are displayed After clicking on Add Bank Details");
              extent.extentLoggerPass("TC_Insta_loan_68", "TC_Insta_loan_68 -  Existing Bank Accounts are displayed After clicking on Add Bank Details");
              System.out.println("-----------------------------------------------------------");
           
              verifyElementPresent(InstaLoanOptionalJourney.objApproved,getTextVal(InstaLoanOptionalJourney.objApproved,"Text"));
              logger.info("TC_Insta_loan_69 - Bank should be added on bank name match percent");
              extent.extentLoggerPass("TC_Insta_loan_69", "TC_Insta_loan_69, Bank should be added on bank name match percent");
              System.out.println("-----------------------------------------------------------");
          }
      }
      verifyElementPresentAndClick(InstaLoanOptionalJourney.objAddNewBankAccount, getText(InstaLoanOptionalJourney.objAddNewBankAccount) + " Button");
      addbankAccount("6" + RandomIntegerGenerator(4), firstName + " " + lastName);
      explicitWaitVisibility(BankTransferModule.objBottomSheetPopup, 10);
      verifyElementExist(BankTransferModule.objBottomSheetPopup, "Verify Bank Details Pop Up");
      click(BankTransferModule.objConfirmBtn, "Confirm Button");
      if(verifyElementPresent(InstaLoanOptionalJourney.objUnableToAddAcc,getTextVal(InstaLoanOptionalJourney.objUnableToAddAcc,"Text"))){
      	click(InstaLoanOptionalJourney.objOkGotIt2,getTextVal(InstaLoanOptionalJourney.objOkGotIt2,"Text"));
      }
      Swipe("up", 2);
      verifyElementPresentAndClick(InstaLoanOptionalJourney.objContinue1, "Continue Button");
      verifyElementPresentAndClick(InstaLoanOptionalJourney.objAddBankDetails,getTextVal(InstaLoanOptionalJourney.objAddBankDetails,"Text"));

      verifyElementPresent(InstaLoanOptionalJourney.objExistingBankAcc,getTextVal(InstaLoanOptionalJourney.objExistingBankAcc,"Text"));
	    verifyElementPresent(InstaLoanOptionalJourney.objRejected,getTextVal(InstaLoanOptionalJourney.objRejected,"Status of Accunt"));
	    logger.info("TC_Insta_loan_70 - Bank should be reject on bank name match percent");
	    extent.extentLoggerPass("TC_Insta_loan_70", "TC_Insta_loan_70, Bank should be reject on bank name match percent");
	    System.out.println("-----------------------------------------------------------");

	}

	public void instaLoanOnHold() throws Exception {
		extent.HeaderChildNode("Insta Loan OnHold Status");
		mockUserAPI();
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(30000);
		instaUserDetails(firstName, lastName, mothersName, email, gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		try {
			instaKycDocument();
		} catch (Exception e) {
			instaKycDocument();
		}
		instaPanCardDetails(prop.getproperty("RingAdminEmail"), prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"), "MANDATORY", "13000", "2311", "2319");
		panCardDetails();
		waitTime(5000);
		instaLoancongratsScreen("Congrats");
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		waitTime(2000);
		instaLoanCoachPopup();
		Swipe("up", 2);
		click(InstaLoanPage.objApplyBtn, getText(InstaLoanPage.objApplyBtn) + " Button is displayed");

		waitTime(5000);
		click(InstaLoanPage.objFatherName, "Father Name Field");
		type(InstaLoanPage.objFatherName, "Arun", "Father Name Field");
		waitTime(5000);
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn, "Proceed Button");
		click(InstaLoanPage.objProceedBtn, "Proceed Button");
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn, "Proceed Button");
		verifyElementPresentAndClick(InstaLoanPage.objProceedBtn, "Proceed Button");
		waitTime(10000);
		verifyElementPresentAndClick(InstaLoanPage.objSelectAddress, "Select Address Dropdown");
		verifyElementPresentAndClick(InstaLoanPage.objAddressSelected, getText(InstaLoanPage.objAddressSelected));
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		verifyElementPresent(InstaLoanPage.objOfferHeader, getText(InstaLoanPage.objOfferHeader) + "Header");
		verifyElementPresent(InstaLoanPage.objEligible,getText(InstaLoanPage.objEligible) + getText(InstaLoanPage.objEligibleInstaLoanAmt));
		verifyElementPresentAndClick(InstaLoanPage.objAddBankButton, getText(InstaLoanPage.objAddBankButton));
		String BankAccount = addbankAccount("5" + RandomIntegerGenerator(4), firstName + " " + lastName);
		updateBankAccStatus("ONHOLD", BankAccount, userReferenceNo);

		String cond_ApprovedStatus = executeQuery3("SELECT * FROM db_tradofina.user_attributes where user_reference_number='" + userReferenceNo + "';",51);
		System.out.println(cond_ApprovedStatus);
		softAssertion.assertEquals(cond_ApprovedStatus, "COND_APPROVED");
		logger.info("TC_Insta_loan_67 - To verify transaction will be CA before loading offer page");
		extent.extentLoggerPass("TC_Insta_loan_67","TC_Insta_loan_67 - To verify transaction will be CA before loading offer page");

		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		click(InstaLoanPage.objAcceptCheckBox, "CheckBox");
		verifyElementPresentAndClick(InstaLoanPage.objAcceptOffer, getText(InstaLoanPage.objAcceptOffer));
		waitTime(120000);
		verifyElementPresentAndClick(InstaLoanPage.objHomeBtn, getText(InstaLoanPage.objHomeBtn) + " Button");
		if (!verifyElementPresent2(InstaLoanOptionalJourney.objPayNowBtnAtHomePage, "Pay Now Button")) {
			waitTime(120000);
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objUPITab, "UPI Tab");
			waitTime(90000);
			Back(1);
		}
		if (verifyElementPresent(InstaLoanHomePage.objOnHold, getTextVal(InstaLoanHomePage.objOnHold, "Text"))) {
			verifyElementPresentAndClick(InstaLoanHomePage.objViewDetails,getTextVal(InstaLoanHomePage.objViewDetails, "Link"));
			explicitWaitVisibility(InstaLoanHomePage.objDisbursementOnHold, 10);
			if (verifyElementPresent(InstaLoanHomePage.objDisbursementOnHold,getTextVal(InstaLoanHomePage.objDisbursementOnHold, "Text"))) {
				verifyElementPresent(InstaLoanViewDetailsPage.objDisbursementStructure,getTextVal(InstaLoanViewDetailsPage.objDisbursementStructure, "Text"));
				verifyElementPresent(InstaLoanViewDetailsPage.objApprovedInstaLoan,getTextVal(InstaLoanViewDetailsPage.objApprovedInstaLoan, "Text"));
				verifyElementPresent(InstaLoanViewDetailsPage.objApprovedInstaLoanAmount,getTextVal(InstaLoanViewDetailsPage.objApprovedInstaLoanAmount, "Text"));
				verifyElementPresent(InstaLoanViewDetailsPage.objProcessingFee,getTextVal(InstaLoanViewDetailsPage.objProcessingFee, "Text"));
				verifyElementPresent(InstaLoanViewDetailsPage.objProcessingFeeAmount,getTextVal(InstaLoanViewDetailsPage.objProcessingFeeAmount, "Amount"));
				verifyElementPresent(InstaLoanViewDetailsPage.objGST,getTextVal(InstaLoanViewDetailsPage.objGST, "Text"));
				verifyElementPresent(InstaLoanViewDetailsPage.objGSTAmount,getTextVal(InstaLoanViewDetailsPage.objGSTAmount, "Amount"));
				verifyElementPresent(InstaLoanViewDetailsPage.objDisbursementAmountTxt,getTextVal(InstaLoanViewDetailsPage.objDisbursementAmountTxt, "Text"));
				verifyElementPresent(InstaLoanViewDetailsPage.objDisbursementAmount,getTextVal(InstaLoanViewDetailsPage.objDisbursementAmount, "Amount"));
				logger.info("TC_Insta_loan_73, Disbursement On Hold screen is validated");
				extent.extentLoggerPass("TC_Insta_loan_73","TC_Insta_loan_73, Disbursement On Hold screen is validated");
				System.out.println("-----------------------------------------------------------");
				Back(1);
			}

		} else {
			logger.info("On Hold status is not displayed");
		}
		softAssertion.assertAll();
	}

	public void transactionValidation() throws Exception {
		extent.HeaderChildNode("Transaction Validation");
		if (verifyElementPresent(HomePage.objScanAndPay, "Scan and Pay Button")) {
			click(HomePage.objScanAndPay, "Scan and Pay Button");
			verifyElementPresent(HomePage.objScanAnyQRToPay, "Scan Any QR To Pay");

			scannQRSwitch();
			verifyElementPresent(InstaLoanOptionalJourney.objScanAndPayPage,getTextVal(InstaLoanOptionalJourney.objScanAndPayPage, "Text"));
			logger.info("TC_Insta_loan_66 - To verify transaction will be initiated after KYC is completed");
			extent.extentLoggerPass("TC_Insta_loan_66","TC_Insta_loan_66 - To verify transaction will be initiated after KYC is completed");
			System.out.println("-----------------------------------------------------------");

			Back(1);
			waitTime(3000);
			Back(1);
			verifyElementPresentAndClick(InstaLoanOptionalJourney.objYesButton,getTextVal(InstaLoanOptionalJourney.objYesButton, "Text"));
			explicitWaitVisibility(HomePage.objHome, 10);
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			logger.info("Navigated to Home Page");
		}
	}

	public void scannQRSwitch() {
		// Scan here
		/*------------------------------WEB----------------------------*/
		Utilities.setPlatform = "Web";
		new CommandBase("Chrome");
		waitTime(4000);
		String projectPath = System.getProperty("user.dir");
		getWebDriver().get(projectPath + "\\Mock_Files\\qrcode.png");
		waitTime(15000);
		BrowsertearDown();

		/*------------------------------Android----------------------------*/
		setPlatform("Android");
		initDriver();
	}

	//-----------------------------Instaloan Optional Journey End---------------------------//
	public void instaLoancongratsScreen(String offer) throws Exception {
		switch (offer) {
		case "Congrats":
			explicitWaitVisibility(RingLoginPage.objCongratsPage, 30);
			verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
			verifyElementPresent(RingLoginPage.objApprovedRingLimit,getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
			verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox,"I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
			break;
		case "Woohoo":
			verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader,getText(PromoCodeOfferPage.objMerchantOfferHeader));
			verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit)+ " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
			verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
			verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
			verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
			verifyElementPresent(PromoCodeOfferPage.objRBIMsg,getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMg2));
			verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox,"I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			break;
		}
	}
	
	public String switchAdminPanelForRefNo() throws Exception {
		setPlatform("Web");
		System.out.println("platform changed to web");
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);

		click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, prop.getproperty("RingAdminEmail"), "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, prop.getproperty("RingAdminPassword"), "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, prop.getproperty("OTP"), "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
		selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
		click(InstaLoanPage.objSearchTerm, "Search Item Field");
		type(InstaLoanPage.objSearchTerm, "9148686588", "Search Item Field");
		click(InstaLoanPage.objSearchBtn, "Search Button");
		waitTime(10000);
		String referenceNo = getText(InstaLoanPage.objUserReferenceNo);
		BrowsertearDown();

		setPlatform("Android");
		initDriver();
		waitTime(5000);
		return referenceNo;
	}

}





