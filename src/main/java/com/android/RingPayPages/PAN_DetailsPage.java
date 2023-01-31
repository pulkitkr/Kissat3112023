package com.android.RingPayPages;

import org.openqa.selenium.By;

public class PAN_DetailsPage {
public static By txtTittlePan=By.xpath("//*[@text='Pan Card Details']");
	
	public static By errorMessage=By.xpath("//*[@text='Please enter a valid pan number']");
	
	public static By panCardNumberField=By.xpath("//*[@resource-id='text-input-outlined']");
	
	public static By txtTittle=By.xpath("//*[@text='Confirm your PAN']");
	
	public static By PanNo=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@text])[2]");

	public static By UserNameOfPAN=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@text])[3]");

	public static By btnyesThisIsMyPlan=By.xpath("//*[@text='Yes, This is my PAN']");
	
	
	public static By btnnoThisIsNotMine=By.xpath("//*[@text='No, this is not mine']");

	public static By handIcon=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*[@class='android.view.ViewGroup'])[1]");
	
	/*-----------------------------------------------------For Internet Connection Check----------------------------------------------------------------------------*/
	
	public static By txtNoInternet=By.xpath("//*[@text=' No Internet! ']");
	
	public static By txtCheckyourInternet=By.xpath("//*[@text=' Check your connection & try again ']");
	
	public static By btnOkaygotIt=By.xpath("//*[@text='Okay Got it']"); 
	
	public static By btnProceed=By.xpath("//*[@text='Proceed']");
	
	
}
