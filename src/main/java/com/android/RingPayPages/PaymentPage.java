package com.android.RingPayPages;

import org.openqa.selenium.By;

public class PaymentPage {

    public static By objUseCreditLimitText = By.xpath("//*[contains(@text,'Use your credit limit to complete this payment.')]");

    public static By objNumericKeyboardDone = By.xpath("//*[@text='Done']");

    public static By objMerchantName = By.xpath("(//android.widget.TextView)[4]");

    public static By objUpiID = By.xpath("(//android.widget.TextView)[5]");

    public static By objRupeesLogo = By.xpath("//*[@text='₹']");

    public static By objTransactionFeeMsg = By.xpath("(//android.widget.TextView)[6]");

    public static By objDisabledPayNowButton = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]]/*/*[@class='android.view.ViewGroup' and ./*[@text='Pay Now']])[2]");

    public static By objAmountTextField = By.xpath("//android.widget.EditText");


    public static By objTransactionMsg = By.xpath("(//android.widget.TextView)[8]");

    public static By objPayNowBtn = By.xpath("//*[@text='Pay Now']");

    public static By objConfirmPayment = By.xpath("//*[@text='Confirm Payment']");

    public static By objEnterTransactionTxt = By.xpath("//*[@text='Enter Transaction Security PIN']");

    public static By objEditTransactionPin = By.xpath("//*[@class='android.widget.EditText']");

    public static By objForgotPin = By.xpath("//*[@text='Forgot PIN?']");

    public static By objContinue = By.xpath("//*[@text='Continue']");

    public static By objIncorrectPin = By.xpath("//*[@text='Incorrect PIN']");

    public static By objChangePin = By.xpath("//*[@text='Change Pin']");

    public static By objEnterNewPin = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and (./preceding-sibling::* | ./following-sibling::*)[./*[./*[@text='Continue']]]]]/*[@class='android.widget.EditText'])[1]");

    public static By objReEnterNewPin = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and (./preceding-sibling::* | ./following-sibling::*)[./*[./*[@text='Continue']]]]]/*[@class='android.widget.EditText'])[2]");

    public static By objEnterOtp = By.xpath("//*[@resource-id='text-input-outlined']");

    public static By objPleaseEnterOtp = By.xpath("//*[@text='Please enter OTP']");

    public static By objInvalidOtpMsg = By.xpath("//*[@text='Invalid Otp, Please enter correct otp.']");

    public static By objEnterSamePin = By.xpath("//*[@text='Please enter same PIN in both fields']");

    public static By objPaymentDone =By.xpath("//*[@text='Payment Done!']");

    public static By objGreenTickMark = By.xpath("//*[@text='Payment Done!']//preceding-sibling::android.view.ViewGroup");

    public static By objPaymentReceipt = By.xpath("//*[@text='Payment Receipt']");

    public static By objMerchantNameReceipt = By.xpath("(//*[@text='Payment Receipt']/following-sibling::android.widget.TextView)[1]");

    public static By objTaxDateAndTime = By.xpath("(//*[@text='Payment Receipt']/following-sibling::android.widget.TextView)[2]");

    public static By objShareReceipt = By.xpath("//*[@text='Share Receipt']");

    public static By objAvailableLimit = By.xpath("//*[@text='Available Limit']");

    public static By ObjAvailableLimitAmount = By.xpath("(//*[@text='Available Limit']/following-sibling::android.widget.TextView)[1]");

    public static By objGoToHomePage = By.xpath("//*[@text='Go to Homepage']");

    public static By objSeekBar = By.xpath("//*[@class='android.widget.SeekBar']");

    public static By objPaymentFailed = By.xpath("//*[@text='Your payment has failed']");

    public static By objOkGotIt = By.xpath("//*[@text='Ok, Got it']");

    public static By objPayNowBtn2 = By.xpath("(//*[@text='Pay Now'])[2]");
    
    public static By enterAmount=By.xpath("//*[@class='android.widget.EditText']");

    public static By arrowBtn=By.xpath("//*[@resource-id='circularButtonFull']");
    
    public static By selectExistingAcc=By.xpath("//*[@class='android.view.ViewGroup' and ./*[@class='android.widget.ImageView']]");
    
  
}
