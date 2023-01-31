package com.android.RingPayPages;

import org.openqa.selenium.By;

public class FeedbackPage {

    public static By objFeedbackPopup = By.xpath("//*[@text='Are you enjoying the app?']");

    public static By objYesBtn = By.xpath("//*[@text='Yes']");

    public static By objNoBtn = By.xpath("//*[@text='No']");

    public static By objRateUrExp = By.xpath("//*[@text='Rate your experience']");

    public static By objRating(int nRating){
        return By.xpath("(((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]/*[@class='android.view.ViewGroup'])[2]/*[@class='android.view.ViewGroup'])[2]/*[@class='android.view.ViewGroup'])["+nRating+"]");
    }
    public static By objChips(int nChips){
        return By.xpath("(//*[@class='android.widget.Button'])["+nChips+"]");
    }

    public static By objSubmitBtn = By.xpath("//*[@text='Submit']");

    public static By objCloseBtn = By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[@text='Rate your experience']]]]]") ;

    public static By objRateUs = By.xpath("//*[@text='Rate Us On Play Store']");

    public static By objPlayStore = By.xpath("//*[@text='Pay with Ring']");

}
