package com.android.RingPayPages;

import org.openqa.selenium.By;

public class OfferPage {

	public static By offerTitle=By.xpath("//*[@text and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]");
	
	public static By ckbAcceptTermandcondition=By.xpath("//*[@class='android.widget.CheckBox']");
	
	public static By btnAcceptOffer=By.xpath("//*[@text='Accept Offer']");
	//*[@class='android.widget.CheckBox']
	//*[@text='Accept Offer']
}
