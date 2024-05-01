package TestCases;

import PageObject.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ConfigurationDriver.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "purchaseData",groups = {"purchase"})
    public void submitOrder( HashMap<String,String>input) throws IOException {
        //LandingPage landingPage=loadApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getList();
        //call the addPrdToCart() method pass the product Name as "ZaraCoat3"
        productCatalogue.addPrdToCart(input.get("productName"));
        //We call the goToCartPage() using child class
        productCatalogue.goToCartPage();

        //Create object CartPage and call the method
        CartPage pages = new CartPage(driver);
        boolean match = pages.verifyProductValue(input.get("productName"));
        Assert.assertTrue(match);

        //Call the checkOut() to go to check out page
        pages.goToCheckOut();
        //Call the CheckOutPage methods
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.selectCountry("india");
        ConfirmPage confirmPage = checkOutPage.submitOrder();
        String confirmMessage = confirmPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    //Dependancy Test here we check after placing the order go to MyOrders page and check the order contain zara coat 3 or not
    //This method is only executed when submitOrder() is excuted successfully
    @Test(dependsOnMethods = {"submitOrder"})
    public void myOrdersHistory(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("ssh@gmail.com", "Hellomampu10");
      OrdersPage ordersPage= productCatalogue.goToOrdersPage();
      Assert.assertTrue(ordersPage.verifyOrdersValue(productName));

    }
    //Here we used Data driven concept to test with multiple data sets
    //@DataProvider(name = "purchaseData")
   // public Object[][] getData(){
      // return new Object[][] {{"ssh@gmail.com","Hellomampu10","ZARA COAT 3"},{"ssh1@gmail.com","Hellomampu10","ADIDAS ORIGINAL"}};
  //  }
    //Give the same test data using HashMap as DataProvider returns HashMap also
   // @DataProvider(name = "purchaseData")
    // public Object[][] getData() {
        //HashMap<String,String> map=new HashMap<String ,String>();
        //map.put("email","ssh@gmail.com");
       // map.put("password","Hellomampu10");
       // map.put("productName","ZARA COAT 3");

        //second set of data
       // HashMap<String,String> map1=new HashMap<String,String>();
        //map1.put("email","ssh1@gmail.com");
        //map1.put("password","Hellomampu10");
        //map1.put("productName","ADIDAS ORIGINAL");
        //return new Object[][] {{map},{map1}};
    @DataProvider(name = "purchaseData")
    public Object[][] getData() throws IOException {
        //Call the json convert method to read the data from file
      List<HashMap<String,String>> data= getJsonDataToMap("E:\\Intelij Projects\\SeleniumFrameworkEx\\src\\test\\java\\TestData\\PurchaseOrderJsonData.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}


