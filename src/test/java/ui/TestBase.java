package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class TestBase {

    @BeforeAll
    static void configurationSettings(){
        Configuration.remote = System.getProperty("remote_driver_url");
        Configuration.browser = "chrome";
        Configuration.browserVersion = "117.0";
    }

    @BeforeEach
    public void setup(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.pageLoadTimeout = 60000;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
//        Attach.addVideo();
    }

}
