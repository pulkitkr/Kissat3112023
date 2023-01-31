package com.android.RingPayPages;

import org.openqa.selenium.By;

public class InstaLoanViewDetailsPage {

    public static By objFirstEMI = By.xpath("//*[@text='First EMI due in']");

    public static By objFirstEMIDueInDays = By.xpath("(//*[@text='First EMI due in']/following-sibling::android.widget.TextView)[1]");

    public static By objPayFullAmountTxt = By.xpath("//*[@text='Pay Full Amount']");

    public static By objPayFullAmountValue = By.xpath("//*[@text='Pay Full Amount']/following-sibling::android.widget.TextView");

    public static By objFirstInstallment = By.xpath("//*[@text='1st installment']");

    public static By objSecondInstallment = By.xpath("//*[@text='2nd installment']");

    public static By objThirdInstallment = By.xpath("//*[@text='3rd installment']");

    public static By objFourthInstallment = By.xpath("//*[@text='4th installment']");

    public static By objFifthInstallment = By.xpath("//*[@text='5th installment']");

    public static By objSixthInstallment = By.xpath("//*[@text='6th installment']");

    public static By objPayBeforeDate = By.xpath("(//*[@text='1st installment']/following-sibling::android.widget.TextView)[1]");

    public static By objFirstEMIAmount = By.xpath("(//*[@text='1st installment']/following-sibling::android.widget.TextView)[2]");

    public static By objPayNow = By.xpath("//*[@text='Pay Now']");

    public static By objPayAmount = By.xpath("(//*[@text='1st installment']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView)[2]");

    public static By objPayAmount1 = By.xpath("//*[contains(@text,'Pay ₹')]");


    public static By objInstaLoan = By.xpath("//*[@text='Insta Loan']");


//    public static By objInProcess = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and ./parent::*[./parent::*[./parent::*[./parent::*[@class='android.widget.ScrollView']]]]]]/*[@class='android.view.ViewGroup'])[1]/*[@class='android.widget.ImageView'])[1]");

    public static By objInProcess = By.xpath("//*[@text='In Process']");

    public static By objDisbursementInProcess = By.xpath("//*[@text='Disbursement in Process']");

    public static By objInProcessAmount = By.xpath("//*[@text='Disbursement in Process']/following-sibling::android.widget.TextView");

    public static By objDisbursementStructure = By.xpath("//*[@text='Disbursement structure']");

    public static By objApprovedInstaLoan = By.xpath("//*[@text='Approved Insta Loan']");

    public static By objApprovedInstaLoanAmount = By.xpath("//*[@text='Approved Insta Loan']/following-sibling::android.widget.TextView");

    public static By objProcessingFee = By.xpath("//*[@text='Processing fee']");

    public static By objProcessingFeeAmount = By.xpath("//*[@text='Processing fee']/following-sibling::android.widget.TextView");

    public static By objActivationFee =By.xpath("//*[@text='One Time Activation Fee']");

    public static By objActivationFeeAmount = By.xpath("//*[@text='One Time Activation Fee']/following-sibling::android.widget.TextView");

    public static By objGST = By.xpath("//*[@text='GST']");

    public static By objGSTAmount =By.xpath("//*[@text='GST']/following-sibling::android.widget.TextView");

    public static By objDisbursementAmountTxt = By.xpath("//*[@text='Disbursement Amount']");

    public static By objDisbursementAmount = By.xpath("//*[@text='Disbursement Amount']/following-sibling::android.widget.TextView");

    public static By objLimitPaused = By.xpath("(//*[@text='Limit Paused'])[2]");

    public static By objLimitPausedMsg = By.xpath("((//*[@text='Limit Paused'])[2]/following-sibling::android.view.ViewGroup)[2]/descendant::android.widget.TextView");

}
