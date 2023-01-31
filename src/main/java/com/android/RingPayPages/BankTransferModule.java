package com.android.RingPayPages;

import org.openqa.selenium.By;

public class BankTransferModule {
	public static By objMoretTab=By.xpath("//*[contains,(@text='More')]");
    public static By objBankTransfer=By.xpath("//*[contains,(@text='Bank Transfer')]");
    public static By AddBankAccountPage=By.xpath("//*[contains,(@text='Add Bank Account')]");
    public static By objIfscTextField=By.xpath("//*[contains,(@text='IFSC Code')]/preceding-sibling::android.widget.EditText");
    public static By objBranchName=By.xpath("//*[@text='Check IFSC Code']/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
    public static By objIFSCErrorMsg=By.xpath("//*[contains,(@text='Enter Valid IFSC')]");
    public static By objAccountNumberField=By.xpath("//*[contains,(@text='Account Number')]/preceding-sibling::android.widget.EditText");
    public static By ObjConfirmAccountNumberField=By.xpath("//*[contains,(@text='Confirm Account Number')]/preceding-sibling::android.widget.EditText");
    public static By objAccountHolderName=By.xpath("//*[contains,(@text='Account Holder Name')]/preceding-sibling::android.widget.EditText");
    public static By objAccountTypeField=By.xpath("//*[@text='Account Type']/following-sibling::android.widget.ImageView");
    public static By objSavings=By.xpath("//*[@text='Account Type']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/descendant::android.widget.TextView[@text='SAVINGS']");
    public static By objContinueButton=By.xpath("//*[contains,(@text='Continue')]");
    
    public static By objBottomSheetPopup=By.xpath("//*[contains,(@text='Verify Bank Details')]");
    public static By objConfirmBtn=By.xpath("//*[contains,(@text='Confirm')]");
    public static By objMakePaymentPage=By.xpath("//*[contains,(@text='Make Payment')]");
    public static By objEnterAmountTextField=By.xpath("(//*[android.widget.EditText])[1]");
    public static By objTransactionFeeMsg=By.xpath("(//*[@text='Pay Now']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/descendant::android.widget.TextView)[2]");
    public static By objPayNowBtn=By.xpath("//*[contains,(@text='Pay Now')]");
    
    //Enter Pin screen
    public static By objEnterPinPage=By.xpath("//*[contains,(@text='Enter Transaction Security PIN')]");
    public static By objEnterPinTextField=By.xpath("//*[contains,(@text='Enter Transaction Security PIN')]/following-sibling::android.widget.TextView");
    public static By objProceedBtn=By.xpath("//*[contains,(@text='Forgot PIN?')]/parent::android.view.ViewGroup/ancestor::android.widget.ScrollView/following-sibling::android.view.ViewGroup");
    
    //Status Page
    public static By objStatusPage=By.xpath("//*[@text='Your payment is still in process']");
    public static By objHomePageBtn=By.xpath("//*[@text='Go to Homepage']");
    
    
    public static By objBankErrorMsg=By.xpath("//*[@text=' Please try adding another bank that belongs to you. ']");
    public static By objOkayBtn=By.xpath("//*[@text='Okay' or @text='Re-Enter Bank Details' or @text='Ok Got It']");
    public static By objAddNewBankAccountBtn=By.xpath("//*[@text='Add New Bank Account']");
    //pop up
    public static By objEnjoyingPopup=By.xpath("//*[@text='Are you enjoying the app?']");
    public static By objYesBtn=By.xpath("//*[@text='Are you enjoying the app?']/following-sibling::android.view.ViewGroup/descendant::android.widget.TextView[@text='Yes']");
    public static By objRateExperience=By.xpath("//*[@text='Rate your experience']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup");
}
