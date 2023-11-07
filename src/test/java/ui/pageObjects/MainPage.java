package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    @Step("Open main page ")
    public void openMainPage(){
        open("/");
    }

    @Step("Check is {1} is visible")
    public void checkMenuButtonIsVisibleAndClick(String buttonText){
        $$(".navbar-nav li")
                .filter(Condition.text(buttonText))
                .first()
                .shouldBe(Condition.visible)
                .click();
    }

}
