package com.android.RingPayPages;

import org.openqa.selenium.By;

public class TransactionPIN {
	
	public static By txtTitleOfSetPin=By.xpath("//*[@text='Set Pin']");
	public static By objEnterPin = By.xpath("//*[@class='android.widget.EditText']");
	public static By objReEnterPin = By.xpath("(//*[@class='android.widget.EditText'])[2]");
	
	public static By objSubmit=By.xpath("//*[@text='Submit']");

}
