package ui.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class ProductsPage {

    @Step("Check is All Products block visible")
    public void isAllProductsBlockVisible(){
        $(".features_items").shouldBe(Condition.visible)
                .$$(".col-sm-4").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(10));
    }

    @Step("View product button click")
    public void clickOnViewProduct(){
        $$(".features_items .col-sm-4")
                .first()
                .$(".choose a")
                .scrollTo()
                .hover()
                .click();
    }

    public void isProductDetailsVisible(){
        $(".product-information")
                .$$("p")
                .filter(Condition.text("Availability:"))
                .first()
                .shouldBe(Condition.visible);
    }

}
