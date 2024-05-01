package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
    //Local driver variable
    WebDriver driver;

    //Create a constructor to use driver object of Standalone class and this constructor code always execute first
    public LandingPage(WebDriver driver) {
        //send the driver object to parent class(AbstractComponent) so that it can use driver object
        super(driver);
        this.driver = driver;

        //initElements() pass this driver object so that findBy annotation and use this object and construct driver.findElement
        PageFactory.initElements(driver, this);
    }
    // WebElement email=driver.findElement(By.id("userEmail"));

    //Use Page Factory annotation to simplify this code
    @FindBy(id = "userEmail")
    WebElement email;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submitButton;

    //For incorrect email/pass error message
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    //Use an action method to perform the actions in landing page like enter the email,password and click on the button
    //Use this method in Standalone to and provide the values in parameter
    public ProductCatalogue loginApplication(String emailId, String pass){
        email.sendKeys(emailId);
        password.sendKeys(pass);
        submitButton.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;
    }
    //method for incorrect email/pass error message
    public String getErrorMessage(){
        //Before get the error message we need to wait for sometime for that we called abstractComponent method
        waitForElementToAppear(errorMessage);
       return errorMessage.getText();
    }
    //method for landing the web page
    public void UrlLand(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
