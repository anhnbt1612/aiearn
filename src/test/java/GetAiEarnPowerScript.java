import io.qameta.allure.Feature;
import model.MyPowerList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Url;
import config.Constant;
import core.AbstractWebTest;
import core.WebApi;
import pages.common.*;
import utils.PageFactoryManager;

@Feature("GetPower")
public class GetAiEarnPowerScript extends AbstractWebTest {


    @BeforeMethod(alwaysRun = true)
    public void login() throws Exception {
        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .clickStartButton()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSigninButton();
    }

    @Test
    public void GetPowerAndCheckout() throws Exception {
        MyPowerList powerList = new MyPowerList();
        PageFactoryManager.get(HomePage.class)
                .closeReferDialog()
                .clickToNavigateToPowerPage();
        PageFactoryManager.get(PowerPage.class)
                .checkBeforePower()
                .findPowerAndGetIt(powerList.getUserName())
                .verifyPowerChanged();
    }
}
