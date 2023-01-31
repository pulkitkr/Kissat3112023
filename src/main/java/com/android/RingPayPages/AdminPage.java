package com.android.RingPayPages;

import org.openqa.selenium.By;

public class AdminPage {

    public static By objEmailField = By.xpath("//*[@name='email']");

    public static By objContinueBtn = By.xpath("//*[@class='MuiTouchRipple-root']");

    public static By objDashBoard = By.xpath("//*[@class='pt-4']");

    public static By objConfiguration = By.xpath("//*[contains(text(),'Configuration')]");

    public static By objSearchField = By.xpath("//*[@id='outlined-error-helper-text']");

    public static By objEditBtn = By.xpath("//*[@class='MuiButton-startIcon MuiButton-iconSizeSmall']");

    public static By objMetaValueField = By.xpath("//*[@name='meta_value']");


    public static By objSubmit = By.xpath("//*[contains(text(),'Submit')]");

    public static By objProfileBtn = By.xpath("//*[@type='button']");

    public static By objLogOutBtn = By.xpath("(//*[@class='MuiButtonBase-root MuiListItem-root MuiListItem-gutters MuiListItem-button'])[2]");

    public static By objAdminPage = By.xpath("//*[@class='MuiTypography-root MuiTypography-h4 MuiTypography-colorPrimary']");

}
