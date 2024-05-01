package StepDefination;

import ConfigurationDriver.BaseTest;
import PageObject.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefination extends BaseTest {
  public LandingPage landingPage;
    ProductCatalogue productCatalogue;
    ConfirmPage confirmPage;
    //Given I landed on Ecommerce page
    @Given("I landed on Ecommerce page")
    public void landed_on_Ecommerce_page() throws IOException {
        landingPage= loadApplication();
    }

    //Logged in with userName<name> and password<password>
    @Given("^Logged in with userName(.+) and password(.+)$")
    public void logged_in_with_userName_and_password(String userName, String password){
       productCatalogue = landingPage.loginApplication(userName,password);
    }

    //When I add product <productName> to the cart
    @When("^I add product (.+) to the cart$")
    public void add_product_to_the_cart(String productName){
        List<WebElement> products = productCatalogue.getList();
        productCatalogue.addPrdToCart(productName);
    }

    //And checkout <productName> and submit the order
    @And("^checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName){
        productCatalogue.goToCartPage();
        //Create object CartPage and call the method
        CartPage pages = new CartPage(driver);
        boolean match = pages.verifyProductValue(productName);
        Assert.assertTrue(match);
        pages.goToCheckOut();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.selectCountry("india");
        confirmPage = checkOutPage.submitOrder();
    }

    //Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
    @Then("{string} message is displayed on confirmation page")
    public void messageIsDisplayedOnConfirmationPage(String string) {
        String confirmMessage = confirmPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

    @Then("{string} message is displayed.")
    public void messageIsDisplayed(String stringArg) {
        Assert.assertEquals(stringArg, landingPage.getErrorMessage());
    }
}
