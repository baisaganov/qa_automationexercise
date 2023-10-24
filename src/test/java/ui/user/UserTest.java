package ui.user;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ui.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class UserTest extends TestBase {

    private final static String USER_NAME = "Alisher";
    private final static String USER_LASTNAME = "Baisaganov";
    private final static String USER_EMAIL = "test1@gamail.cum333";
    private final static String USER_PASSWORD = "Password1";

    @Test
    @Tags({@Tag("user"), @Tag("registration")})
    @DisplayName("Регистрация пользователя")
    void registerUser(){
        step("Open page", () -> {
            open("https://automationexercise.com ");
            $("html").shouldBe(Condition.visible);
        });

        step("Open Signup / Login form and fill form", () -> {
            $$("div.shop-menu a").filter(Condition.visible)
                    .filter(Condition.text(" Signup / Login")).first()
                    .click();
            $("div.signup-form").should(Condition.visible);
            $("div.signup-form input[data-qa=signup-name]").shouldBe(Condition.visible).setValue(USER_NAME);
            $("div.signup-form input[data-qa=signup-email]").shouldBe(Condition.visible).setValue(USER_EMAIL);
            $("div.signup-form button[data-qa=signup-button]").shouldBe(Condition.visible).click();
        });

        step("Select title", () -> {
            $("div.login-form").shouldBe(Condition.visible);
            $("div.clearfix #id_gender1").click();
        });

        step("Verify name", () -> {
            $("#name").shouldBe(Condition.visible).shouldHave(Condition.value(USER_NAME));
        });

        step("Verify email", () -> {
            $("#email").shouldBe(Condition.disabled).shouldHave(Condition.value(USER_EMAIL));
        });

        step("Enter password", () -> {
            $("#password").setValue(USER_PASSWORD);
        });

        step("Set date of birth", () -> {
            $$("#uniform-days option[value='3']").first().setSelected(true);
            $$("#uniform-months option[value='3']").first().setSelected(true);
            $$("#uniform-years option[value='2000']").first().setSelected(true);
        });

        step("Subscribe to newsletters", () -> {
            $("#newsletter").scrollTo().click();
        });

        step("Enter Name and Last name, address ", () -> {
            $("#first_name").setValue(USER_NAME);
            $("#last_name").setValue(USER_LASTNAME);
            $("#last_name").setValue(USER_LASTNAME);
            $("input[data-qa=address]").setValue("Astana, Turan st. 35");
        });

        step("Set location information", () -> {
            $("#country option[value=Canada]").setSelected(true);
            $("#state").setValue("Ontario");
            $("#city").setValue("Toronto");
            $("#zipcode").setValue("57268");
            $("#mobile_number").setValue("57268");
        });

        step("Create account button", () -> {
            $("button[data-qa=create-account]")
                    .shouldBe(Condition.enabled, Condition.visible)
                    .scrollTo()
                    .click();
        });

        step("Account created verify", () -> {
            $("h2[data-qa=account-created]").shouldHave(Condition.text("Account Created!"));
            $("a[data-qa=continue-button]").shouldBe(Condition.visible).click();
        });

    }

    @Test
    @Tags({@Tag("user"), @Tag("login")})
    @DisplayName("Авторизация")
    void loginUser(){
        step("Open page", () -> {
            open("https://automationexercise.com ");
            $("html").shouldBe(Condition.visible);
        });

        step("Open Signup / Login form and fill form", () -> {
            $$("div.shop-menu a").filter(Condition.visible)
                    .filter(Condition.text(" Signup / Login")).first()
                    .click();
            $("div.login-form").should(Condition.visible);
            $("div.login-form input[data-qa=login-email]").shouldBe(Condition.visible).setValue(USER_EMAIL);
            $("div.login-form input[data-qa=login-password]").shouldBe(Condition.visible).setValue(USER_PASSWORD);
            $("div.login-form button[data-qa=login-button]").shouldBe(Condition.visible).click();
        });

        step("", () -> {
            $$("ul.navbar-nav li")
                    .filter(Condition.text(" Logged in as " + USER_NAME))
                    .first()
                    .shouldBe(Condition.visible);
        });

    }

}
