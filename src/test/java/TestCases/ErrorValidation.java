package TestCases;

import ConfigurationDriver.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationDriver.BaseTest;
import PageObject.CartPage;
import PageObject.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {
    //here we check if we put wrong email/pass error should display. we catch that error message in ladingPage class here we check the error message is same or not.

    @Test(groups = {"ErrorValidationGrp"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException {
        landingPage.loginApplication("ssh@gmail.com", "Hellomampu1");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductCatalogueValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("ssh1@gmail.com", "Hellomampu10");
        List<WebElement> products = productCatalogue.getList();
        //call the addPrdToCart() method pass the product Name as "ZaraCoat3"
        productCatalogue.addPrdToCart(productName);
        //We call the goToCartPage() using child class
        // productCatalogue.goToCartPage();

        //Create object CartPage and call the method
        CartPage pages = new CartPage(driver);
        pages.goToCartPage();
        boolean match = pages.verifyProductValue("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
