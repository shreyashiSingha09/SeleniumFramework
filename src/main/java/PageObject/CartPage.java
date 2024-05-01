package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".cartSection h3")
  List<WebElement>  cartProductsList;

    @FindBy(css = ".totalRow button")
    WebElement checkOut;

    //check that in the "Zara Coat3" is added or not
    public boolean verifyProductValue(String productName){

        boolean match = cartProductsList.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
    //Move to checkOut Page
    public void goToCheckOut(){
        checkOut.click();
    }
}
