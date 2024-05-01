package TestCases;
import PageObject.CartPage;
import PageObject.CheckOutPage;
import PageObject.ConfirmPage;
import PageObject.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationDriver.BaseTest;

import java.io.IOException;
import java.util.List;


public class StandAloneEx extends BaseTest {
    @Test
    public void submitOrder() throws IOException {

        String productName = "ZARA COAT 3";
        //LandingPage landingPage=loadApplication();
        ProductCatalogue productCatalogue=landingPage.loginApplication("ssh@gmail.com","Hellomampu10");
       List<WebElement> products=productCatalogue.getList();
        //call the addPrdToCart() method pass the product Name as "ZaraCoat3"
        productCatalogue.addPrdToCart(productName);
        //We call the goToCartPage() using child class
        productCatalogue.goToCartPage();

        //Create object CartPage and call the method
        CartPage pages=new CartPage(driver);
        boolean match = pages.verifyProductValue(productName);
        Assert.assertTrue(match);

        //Call the checkOut() to go to check out page
        pages.goToCheckOut();
        //Call the CheckOutPage methods
      CheckOutPage checkOutPage=new CheckOutPage(driver);
        checkOutPage.selectCountry("india");
        ConfirmPage confirmPage=checkOutPage.submitOrder();
        String confirmMessage =confirmPage.getConfirmationMessage();
      Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }

}
