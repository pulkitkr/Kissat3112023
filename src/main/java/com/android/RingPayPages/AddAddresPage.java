package com.android.RingPayPages;

import org.openqa.selenium.By;

public class AddAddresPage {
	public static By objAddressHeader=By.xpath("//*[contains(@text,'New Communication Address')]");
    public static By objRoomNoTextField=By.xpath("//*[contains(@text,'Room/Flat No. & Building Name')]");
    public static By objAddressLineOneTextField=By.xpath("//*[contains(@text,'Address Line 1')]");
    public static By objAddressLineTwoTextField=By.xpath("//*[contains(@text,'Address Line 2 (Optional)')]");
    public static By objLandmarkTextField=By.xpath("//*[contains(@text,'Landmark')]");
    public static By objPincodeTextField=By.xpath("//*[contains(@text,'Pincode')]");
    public static By objCityTextField=By.xpath("//*[contains(@text,'City')]");
    public static By objStateTextField=By.xpath("//*[contains(@text,'State')]");
    public static By objSubmitButton=By.xpath("//*[contains(@text,'Submit')]");
    public static By objSpclCharWarnMessage=By.xpath("//*[contains(@text,'Only following special characters are allowed - , & /')]");
    public static By objMandatoryWarnMessage=By.xpath("//*[contains(@text,'Mandatory field.')]");
    public static By objMinThreeCharWarnMessage=By.xpath("//*[contains(@text,'Minimum 3 character. Only following special characters are allowed - , & /')]");
    public static By objSixDigitWarnMessage=By.xpath("//*[contains(@text,'Please enter 6 digit pin code')]");
    public static By objInvalidPinCode=By.xpath("//*[contains(@text,'Invalid Pin Code')]");
    public static By objRoomNoField = By.xpath("//*[contains(@text,'Room/Flat No. & Building Name')]/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objAddressLineOneField = By.xpath("//*[contains(@text,'Address Line 1')]/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objAddressLineTwoField = By.xpath("//*[contains(@text,'Address Line 2 (Optional)')]/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objLandMarkClr = By.xpath("//*[contains(@text,'Landmark')]/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objpincodeClr = By.xpath("//*[contains(@text,'Pincode')]/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objPopulatedCity=By.xpath("//*[@text='City']/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objPopulatedState=By.xpath("//*[@text='State']/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
}
