package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage {
    WebDriver driver;
    public OrdersPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> OrdersProductNames;


    //check that in the "Zara Coat3" is added or not
    public boolean verifyOrdersValue(String productName){

        boolean match = OrdersProductNames.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
}
