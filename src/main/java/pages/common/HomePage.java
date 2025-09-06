package pages.common;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends WebApi {

    private String referDialogStr = "//section[contains(@id,'chakra-modal')]";

    private String closeDialogBtnStr = "//section//button[@aria-label='Close']";

    @FindBy(xpath = "//button[text()='AI Earn']/following-sibling::span[contains(@class,'chakra-avatar')]")
    private WebElement myAvatar;

    @FindBy(xpath = "//section//a[text()='My Power']")
    private WebElement myPowerInRightMenu;

    @Step
    public HomePage closeReferDialog(){
        waitForPageLoaded();
        if(isControlDisplayed(driver.findElement(By.xpath(referDialogStr)), 3000)){
            int count = 1;
            while (isElementDisplayedBy(By.xpath(referDialogStr)) && count < 3){
                waitForElementClickable(driver.findElement(By.xpath(closeDialogBtnStr)));
                clickToElementByJavascript(closeDialogBtnStr);
                sleepTimeInSecond(3);
            }
        }else {
            System.out.println("Not found referal diaglog on this page");
        }
        return this;
    }


    @Step
    public HomePage clickToNavigateToPowerPage() {
        waitForElementClickable(myAvatar);
        clickElement(myAvatar);
        waitForElementClickable(myPowerInRightMenu);
        clickElement(myPowerInRightMenu);
        return this;
    }

}
