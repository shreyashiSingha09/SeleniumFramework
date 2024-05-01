package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    //Create a constructor to use driver object of Standalone class and this constructor code always execute first
    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;

        //initElements() pass this driver object so that findBy annotation and use this object and construct driver.findElement
        PageFactory.initElements(driver, this);
    }

    //Collect all the available products into list
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    By products1 = By.cssSelector(".mb-3");
    By addToCART = By.cssSelector(".card-body button:last-of-type");
    By taoastMessage = By.cssSelector("#toast-container");
    By spinner=By.cssSelector(".ng-animating");

    //Get the list of the products which are collected(This is action method)
    public List<WebElement> getList() {
        //we need to load all the products first
        getVisiable(products1);
        return products;
    }

    //get the product from products List, here we find "ZARA COAT3"
    public WebElement getProductByName(String productName) {
        WebElement prod = getList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    //After get the productName="ZARA COAT3" add this to add
    public void addPrdToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCART).click();
        //here we wait for sometime to visibility of the toast message for this we use abstractComponent method
        getVisiable(taoastMessage);
        //here we wait for sometime to disappared the spinner for this we also use abstractComponenet another method
        getDisappared(spinner);
    }
}
