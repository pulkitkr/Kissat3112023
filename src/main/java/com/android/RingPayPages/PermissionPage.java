package com.android.RingPayPages;

import org.openqa.selenium.By;

public class PermissionPage {
	
	public static By txtPermission=By.xpath("//*[@text='Permissions']");

	public static By errorPopupMessagePermissionPage=By.xpath("//*[@text='You have denied some of the required permissions for this action. Please open settings, go to permissions and allow All Permissions.']");
	
	public static By btnOk=By.xpath("//*[@text='OK']");
	
	public static By btnCancel=By.xpath("//*[@text='CANCEL']");
	
	public static By ckb_Cibil_tc_and_CKY_conset=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.widget.CheckBox'])[1]");
	
	public static By ckbWhatsappbox=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.widget.CheckBox'])[2]");
	
	public static By btnReadAndAccetp=By.xpath("//*[@text='Read & Accept']");
	
	
	public static By allowLocation=By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"); //
	

	public static By btnAllow=By.xpath("//*[@text='Allow']");
	
	
	public static By btnDeny=By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_deny_button']");
	//*[@resource-id='com.android.permissioncontroller:id/permission_deny_button']
	
	
	public static By txtAllowLocation=By.xpath("//*[@text='Allow Ring Debug to access this device\'s location?']");
	public static By btnAllowLocation=By.xpath("//*[@text='Allow only while using the app']");
	
	public static By txtAllowCall=By.xpath("//*[@text='Allow Ring Debug to make and manage phone calls?']");
	
	public static By txtAllowSMS=By.xpath("//*[@text='Allow Ring Debug to send and view SMS messages?']");


	public static By txtandLinkCKYC_Consent=By.xpath("//*[@text='I allow Ring to run credit checks if required and accept CIBIL T&C and CKYC consent']");
	
	public static By txtSMS_Message=By.xpath("//*[@text='Allow Ring Debug to send and view SMS messages?']");
	
	public static By txtHearderCKYC_Consent=By.xpath("//*[@text='CKYC Consent']");
	
	public static By policydetailsCKYC_Consent=By.xpath("//*[@text='I give my consent to Ring app to fetch my CKYC details from CERSAI, I understand the data procured from CERSAI will not be used for any other purpose then processing application.']");
	
	public static By btnOkGotIt=By.xpath("//*[@text='Ok Got It']");
	
	public static By btnExitApp=By.xpath("//*[@text='Exit App']");
	
	public static By txtHoldon=By.xpath("//*[@text='Hold on!']");
	
	public static By btnYes=By.xpath("//*[@id='button1']");
	

}
