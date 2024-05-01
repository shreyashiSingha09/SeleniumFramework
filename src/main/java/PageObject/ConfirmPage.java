package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.AbstractComponent;

public class ConfirmPage extends AbstractComponent {
    WebDriver driver;
    public ConfirmPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css = ".hero-primary")
    WebElement confirmMsg;

    public String getConfirmationMessage(){
        return confirmMsg.getText();
    }
}
