package ui.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.TestBase;
import ui.pageObjects.MainPage;
import ui.pageObjects.ProductsPage;

public class ProductsPageTest extends TestBase {

    @Test
    @DisplayName("Test Case 8: Verify All Products and product detail page")
    void allProductsVisibleTest(){
        MainPage mainPage = new MainPage();
        ProductsPage productsPage = new ProductsPage();

        mainPage.openMainPage();
        mainPage.checkMenuButtonIsVisibleAndClick("Products");
        productsPage.isAllProductsBlockVisible();
        productsPage.clickOnViewProduct();
        productsPage.isProductDetailsVisible();
    }


}
