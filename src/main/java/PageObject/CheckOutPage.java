package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage( WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submitBtn;

    @FindBy(css = ".ta-results button:nth-of-type(2)")
    WebElement selectCountry;
    By visibleElement=By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        //type the country Name
        a.sendKeys(country, countryName).build().perform();
        //Wait for some time to get the courty list
        getVisiable(visibleElement);
        //choose the 2nd countryName from the list
       selectCountry.click();
    }
    public ConfirmPage submitOrder(){
        //Click on submit button to place the order
        submitBtn.click();
       return new ConfirmPage(driver);
    }
}
