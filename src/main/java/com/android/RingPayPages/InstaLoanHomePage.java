package com.android.RingPayPages;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.net.PortUnreachableException;

public class InstaLoanHomePage {

    public static By objLimitPaused = By.xpath("(//*[@text='Limit Paused'])[1]");

    public static By objLimitPausedAmount = By.xpath("((//*[@text='Limit Paused'])[1]/following-sibling::android.widget.TextView)[1]");

    public static By objInstaLoanAmountTxt = By.xpath("//*[@text='Insta Loan Amount']");

    public static By objInstaLoanAmountValue =By.xpath("(//*[@text='Insta Loan Amount']/following-sibling::android.widget.TextView)[1]");

    public static By objDueDate = By.xpath("(//*[@text='Due Date: ']/following-sibling::android.widget.TextView)[1]");

    public static By objDueDateTxt = By.xpath("//*[@text='Due Date: ']");

    public static By objEmiAmount = By.xpath("(//*[@text='EMI Amt: ']/following-sibling::android.widget.TextView)[1]");

    public static By objEmiAmountTxt = By.xpath("//*[@text='EMI Amt: ']");

    public static By objDisbursed = By.xpath("//*[@text='Disbursed']");

    public static By objViewDetails = By.xpath("//*[@text='View Details']");

    public static By objPayNow = By.xpath("//*[@text='Pay Now']");

    public static By objDisbursedLoanBanner = By.xpath("//*[@text='Insta Loan Amount']/parent::android.view.ViewGroup");

    public static By objLimitPausedBanner = By.xpath("(//*[@text='Limit Paused']/parent::android.view.ViewGroup)[2]");

    public static By objLimitPausedMsg = By.xpath("//*[@text='Limit Paused']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView");

    public static By objLimitPausedPopup = By.xpath("//*[@text='Limit is Paused!']");

    public static By objOkGotIt1 = By.xpath("//*[@text='Okay, Got It!']");

    public static By objOkGotIt = By.xpath("//*[@text='Ok, Got It']");

    public static By objOkGotIt2 = By.xpath("//*[@text='Ok Got It']");

    public static By objPayment = By.xpath("//*[@text='Payment']");

    public static By objBankTransfer = By.xpath("//*[@text='Bank Transfer']");

    public static By objDueInDays = By.xpath("//*[@text='Insta Loan Amount']/preceding-sibling::android.view.ViewGroup/descendant::android.widget.TextView");

    public static By objElectricity = By.xpath("//*[@text='Electricity']");

    public static By objInstaLoanBannerCloseBtn = By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@text='Permanent Address']]]");

    public static By objInstaOkGotIt = By.xpath("//*[@text='Okay, Got It!']");

    public static By objProceedBtn = By.xpath("//*[@text='Proceed']");

    public static By objCommunicationAddressBackArrow = By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@text='Permanent Address']]]");


//======================================= Two Steps Away ====================================================//
    public static By objTwoSteps = By.xpath("//*[@text='Just 2 steps ahead']");

    public static By objMsg1 = By.xpath("(//*[@text='Just 2 steps ahead']/following-sibling::android.widget.TextView)[1]");

    public static By objMsg2 = By.xpath("(//*[@text='Just 2 steps ahead']/following-sibling::android.widget.TextView)[2]");

    public static By objContinue = By.xpath("//*[@text='Just 2 steps ahead']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView");

    public static By objSelectAddress = By.xpath("//*[@text='Select Address']");

    public static By objAddress = By.xpath("//*[@text='86 44, Borivali Vasa Street 400080']");

    public static By objAddress1 = By.xpath("//*[@text='Insta Loan']/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/(descendant::android.widget.TextView)[3]");

    public static By objOffer = By.xpath("//*[@text='Offer']");

    public static By objOfferBackArrow = By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and ./*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@text='Offer']]");

//============================================== One Step Away =================================================//

    public static By objOneStep = By.xpath("//*[@text='Just 1 step ahead']");

    public static By objMsgg1 = By.xpath("(//*[@text='Just 1 step ahead']/following-sibling::android.widget.TextView)[1]");

    public static By objMsgg2 = By.xpath("(//*[@text='Just 1 step ahead']/following-sibling::android.widget.TextView)[2]");

    public static By objContinue1 = By.xpath("//*[@text='Just 1 step ahead']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView");

    public static By objAddBankDetails = By.xpath("//*[@text='Add Bank Details']");
    //*[@text='Add Bank Details']

//=================================== Offer Page ===========================================================//

    public static By objCheckBox = By.xpath("//*[@class='android.widget.CheckBox']");

    public static By objAcceptOffer = By.xpath("//*[@text='Accept Offer']");

    public static By objConfirmOffer = By.xpath("//*[@text='Confirm Offer']");

    public static By objInstaLoanDisbursement = By.xpath("//*[@text='Insta Loan Disbursement Successful']");

    public static By objInstaLoanAmount = By.xpath("(//*[@text='Insta Loan Disbursement Successful']/following-sibling::android.widget.TextView)[1]");

    public static By objInstaLoanOnWay = By.xpath("//*[@text='Your Insta Loan is on its way!']");
    public static By objHome = By.xpath("//*[@text='Home']");

//======================= For On Hold Status ===================================//

    public static By objOnHold = By.xpath("//*[@text='On Hold']");

    public static By objDisbursementOnHold = By.xpath("//*[@text='Disbursement on Hold']");

    public static By objDisbursementOnHoldAmount = By.xpath("//*[@text='Disbursement on Hold']/following-sibling::android.widget.TextView");

    public static By objReApplyInstaLoan = By.xpath("//*[@text='ReApply for Insta Loan!']");

    public static By objSorry = By.xpath("//*[@text='Sorry']");

    public static By objNewOfferAlert = By.xpath("//*[@text='New Offer Alert!']");

    public static By objPermanentAddress = By.xpath("//*[@text='Permanent Address']");

//============================================================================================================//

    public static By objSorryMsg1 = By.xpath("//*[@text='Sorry!']");

    public static By objSorryMsg2 = By.xpath("//*[@text='This mode of payment is currently unavailable']");

    public static By objExistingBankAcc = By.xpath("//*[@text='Existing Bank Account']");

    public static By objExistingBank = By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]");

    public static By objRejected = By.xpath("//*[@text='Rejected']");

    public static By objApproved = By.xpath("//*[@text='Approved']");



}
