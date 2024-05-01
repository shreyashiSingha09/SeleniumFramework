package AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PageObject.OrdersPage;

import java.time.Duration;

public class AbstractComponent {
        WebDriver driver;
    public AbstractComponent(WebDriver driver) {
            this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //Go to Cart page WebElement
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    //Go to Orders Page WebElement
    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersHeader;

    //Place the code of Wait until some elements is not visiable or after visiable of some elements for By Locator
    public void getVisiable(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    //Wait until some elements is visiable of some elements for webelements
    public void waitForElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void getDisappared(By finBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(finBy));
    }
    //After add the "ZaraCOAT3" we have to go Cart page, as the header is common for all screens that's why we place the Cartpage code here
    public void goToCartPage(){
        cartHeader.click();
    }
    public OrdersPage goToOrdersPage(){
        ordersHeader.click();
        OrdersPage orderPage=new OrdersPage(driver);
        return orderPage;
    }
}
