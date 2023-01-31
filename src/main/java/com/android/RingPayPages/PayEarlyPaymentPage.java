package com.android.RingPayPages;

import org.openqa.selenium.By;

public class PayEarlyPaymentPage {

	public static By objPayEarlyPayment = By.xpath("//*[@text='Payment']");

	public static By objAmountDue = By.xpath("//*[@text='Amount Due']");

	public static By objAmountToBePaid = By.xpath("//*[@text='Amount to be paid']");

	public static By objChoosePaymentMethod = By.xpath("//*[@text='Choose Payment Method']");

	public static By objPayViaUPIApps = By.xpath("//*[@text='Pay via UPI apps']");

	public static By objGooglePay = By.xpath("//*[@text='Google Pay']");

	public static By objPhonePe = By.xpath("//*[@text='PhonePe']");

	public static By objPaytm = By.xpath("//*[@text='Paytm']");

	public static By objAmazonPay = By.xpath("//*[@text='Amazon Pay']");

	public static By objBHIM = By.xpath("//*[@text='BHIM']");

	public static By objUPIAppsConvenienceFee = By
			.xpath("//*[@text='Pay via UPI apps']/following-sibling::android.widget.TextView");

	public static By objAddUPIId = By.xpath("//*[@text='Add UPI ID']");

	public static By objAddUPIIdConvenienceFee = By
			.xpath("//*[@text='Add UPI ID']/following-sibling::android.widget.TextView");

	public static By objNetBankingAndDebitCard = By.xpath("//*[@text='Net Banking & Debit Card']");

	public static By objNetBankingAndDebitCardConvenienceFee = By
			.xpath("//*[@text='Net Banking & Debit Card']/following-sibling::android.widget.TextView");

	public static By objBankTransfer = By.xpath("//*[@text='Bank Transfer']");

	public static By objPaymentViaUPI = By.xpath("//*[@text='Payment via UPI']");
}
