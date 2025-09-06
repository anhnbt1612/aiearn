package pages.common;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PowerPage extends WebApi {

    private static final String userNameInPowerTableStr = "//table/tbody//td//p[text()='%s']";

    private static final String powerImgStr = "//table/tbody//td//p[text()='%s']//ancestor::td/following-sibling::td//img[contains(@class,'chakra-image')]";

    @FindBy(xpath = "//p[text()='My Power']/preceding-sibling::div//div[text()]")
    private WebElement beforePower;

    @FindBy(xpath = "//button[text()='AI Earn']/following-sibling::span[contains(@class,'chakra-avatar')]")
    private WebElement myAvatar;

    @FindBy(xpath = "//section//p[text()='Sign out']")
    private WebElement signoutInRightMenu;

    private String beforePowerText;

    @Step
    public PowerPage checkBeforePower() {
        waitForPageLoaded();
        waitForElementVisible(beforePower);
        beforePowerText = beforePower.getText().trim();
        System.out.println("Before Power value: " + beforePowerText);
        return this;
    }

    @Step
    public PowerPage findPowerAndGetIt(String userName){
        WebElement userNameInPowerTable = driver.findElement(By.xpath(String.format(userNameInPowerTableStr, userName)));
        waitForElementVisible(userNameInPowerTable);
        clickElement(userNameInPowerTable);
        sendKeyBoardToElement(userNameInPowerTable, Keys.ARROW_RIGHT);
        sendKeyBoardToElement(userNameInPowerTable, Keys.ARROW_RIGHT);
        sleepTimeInSecond(1);
        WebElement powerImg = driver.findElement(By.xpath(String.format(powerImgStr, userName)));
        verifyControlDisplayed(powerImg, "Power Image");
        clickElement(powerImg);
        sleepTimeInSecond(5);
        return this;
    }

    @Step
    public PowerPage verifyPowerChanged() {
        waitForElementVisible(beforePower);
        String afterPowerText = beforePower.getText().trim();
        System.out.println("After Power value: " + afterPowerText);

        if (afterPowerText.equals(beforePowerText)) {
            Assert.fail("Power value did not change. Value = " + afterPowerText);
        } else {
            System.out.println("Power value changed successfully. Value = " + afterPowerText);
        }
        return this;
    }

    @Step
    public PowerPage signoutThisAccount() throws Exception {
        waitForElementClickable(myAvatar);
        clickElement(myAvatar);
        waitForElementClickable(signoutInRightMenu);
        clickElement(signoutInRightMenu);
        Thread.sleep(3000);
        return this;
    }

}
