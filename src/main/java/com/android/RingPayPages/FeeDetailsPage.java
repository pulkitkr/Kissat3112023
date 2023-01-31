package com.android.RingPayPages;

import org.openqa.selenium.By;

public class FeeDetailsPage {

	// Fee details Popup
	public static By objFeeDetailsPopup = By.xpath("//*[@text='Fee Details']");
//	    Transaction Fee Text
	public static By objTransactionFee = By.xpath("//*[@text='Transaction Fee: ']");
//	    Transaction Fee Percentage
	public static By objTransactionFeePercentage = By
			.xpath("(//*[@text='Transaction Fee: ']/following-sibling::android.widget.TextView)[1]");
}
