package pages.common;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends WebApi {

    @FindBy(xpath = "//button[text()='Get Start']")
    private WebElement startBtn;

    @FindBy(name = "username")
    private WebElement userId;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement signinBtn;

    @Step
    public LoginPage verifyLoginPageDisplayed() {
        System.out.println("Verify login page displayed");
        waitForPageLoaded();
        verifyControlDisplayed(startBtn, "Get Start button");
        return this;
    }

    @Step
    public LoginPage clickStartButton() {
        waitForElementClickable(startBtn);
        clickElement(startBtn);
        return this;
    }

    @Step
    public LoginPage inputCredentials(String id, String pass) {
        waitForElementVisible(userId);
        sendKeyToElement(userId, id);
        sendKeyToElement(password, pass);
        return this;
    }

    @Step
    public LoginPage clickSigninButton() {
        waitForElementClickable(signinBtn);
        clickElement(signinBtn);
        return this;
    }
}
