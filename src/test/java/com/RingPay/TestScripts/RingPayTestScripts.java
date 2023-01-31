package com.RingPay.TestScripts;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utility.Utilities;
import com.android.RingPayPages.RingLoginPage;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;

//Comment
public class RingPayTestScripts{

	private com.business.RingPay.RingPayBusinessLogic ringPayBusiness;
	
	@BeforeTest
	public void Before() throws InterruptedException {
		Utilities.relaunch = true;
		ringPayBusiness = new com.business.RingPay.RingPayBusinessLogic("ring");
	}
	
	
	 
	@Test(priority = 0)
	@Parameters({"SignUpBtn","permission"})
    public void  User_Playstore_Flow(String SignUpBtn, String permission) throws Exception {
		ringPayBusiness.User_Play_Store_Flow(SignUpBtn, permission);
	} 
	
	@Test(dependsOnMethods = "User_Playstore_Flow") 
	@Parameters({"Month","Date","Year","Gender"})
	public void userRegistrationFlow(String month, String date, String year,String gender) throws Exception {
		ringPayBusiness.User_Registration_Flow(month,date,year,gender);
	} 
	
	
	@Test(priority = 2)
	public void promocodeFlow() throws Exception {
		ringPayBusiness.promoCodeFlowModule();
	} 
	
	@Test(priority=3)
	public void offerFlow() throws Exception {
		ringPayBusiness.offerScreenModule();
	}
	
	@Test(priority=4)
	public void ageCheck() throws Exception {
		ringPayBusiness.ageCriteriaFailedCheck();
	}
	
	@Test(priority = 5)
    @Parameters({"baseURLMockUser","gender","encrypted_name","portalEmail","portalPassword","portalOTP"})
    public void WebRingApp(String url,String gender,String encrypted_name,String portalEmail,String portalPassword,String portalOTP) throws Exception {
		ringPayBusiness.kycSkipped(url,gender,encrypted_name,portalEmail,portalPassword,portalOTP);
    }
	
	 @Test(priority = 6)
	 @Parameters({"baseURLMockUser","gender","encrypted_name","portalEmail","portalPassword","portalOTP"})
	 public void WebRingApp1(String url,String gender,String encrypted_name,String portalEmail,String portalPassword,String portalOTP) throws Exception {
		 ringPayBusiness.bannerLogicOnHomePage(url,gender,encrypted_name,portalEmail,portalPassword,portalOTP);
	 }
	
	 @Test(priority = 7)
	 public void transactionSetPin() throws Exception {
		 ringPayBusiness.transactionSetPin();
	 }
	 
	 @Test(priority = 8)
	 public void repaymentmultipleCase() throws Exception {
		 ringPayBusiness.repaymentMultipleCases();
	 }
	 
	 @Test(priority = 9)
	 public void bankTransferFlow() throws Exception{
		 ringPayBusiness.BankTransferModule("5", "Akash");
	 }
	 
	@Test(priority = 10)
	public void addAddresFlow() throws Exception {
		ringPayBusiness.addAddressFlow();
	}
	
	@Test(priority = 11)
	public void userScanAndPayFlow() throws Exception {
		ringPayBusiness.userScanAndPayTransactions();
	}
	
	@Test(priority = 12)
	public void merchantFlow() throws Exception {
		ringPayBusiness.merchantFlow();
	}
	
	
	@Test(priority = 7)
	public void ringPayLogin() throws Exception {
		ringPayBusiness.mockUserAPI();
	} 
	
/*
	@Test(priority = 1)
	public void ringInstaWhitelist() throws Exception{
		//ringPayBusiness.instaLoanWhitelistLogic();
		//ringPayBusiness.instaLoanRepayment();
		//ringPayBusiness.afterClickOnApplyNowForLoanOfferBanner();
		//ringPayBusiness.InstaLoanDetailScreen();
		//ringPayBusiness.PanValidation();
		//ringPayBusiness.dataPointApi();
	//	ringPayBusiness.InstaLoanDetailScreen();
		ringPayBusiness.feedBackValidationWithGlobalFlag();
	}
	*/
	
	@AfterTest
	public void ringAppQuit() throws Exception{
		ringPayBusiness.TearDown();
	}
}
