package ConfigurationDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import PageObject.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        //Use properties class to extract the global properties file
        Properties prob = new Properties();
        //Use FileInputStream class of java to convert the file to input Stream as load() can take argument as stream
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//Resources//globalData.properties");
        prob.load(fis);
        //now extract the value from the file
        String browserName = prob.getProperty("Browser");
        //set the browser at run time using maven commands
        //If the browser Name is not null then it is executed with maven commands(1st argument) or otherwise it is executed from globalData properties file(2nd argument)
       // String browserName=System.getProperty("browser")!=null ?System.getProperty("browser"):prob.getProperty("browser");

        if (browserName.equalsIgnoreCase("Chrome")) {
            //ChromeOptions options=new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
//            if(browserName.contains("headless")){
//                options.addArguments("headless");
//            }

        }
        else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            //edge driver code
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    //how to read data from JSON file
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //Using Java Class FileUtils method at first convert the json to string
        String jsonContent= FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);
        //Convert string to Hashmap
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
    //Create a utility method to take ss when the test case is getting failed
    public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
        //convert the driver so that it takes ss
        TakesScreenshot ts=(TakesScreenshot)driver;
        File fSrc=  ts.getScreenshotAs(OutputType.FILE);
        //create a new folder name reports where ss is getting stored and file name should be test case name
       // File fDesc=new File(System.getProperty("user.dir")+ "//reports" + testCaseName + ".png");
        File fDesc= new File("E:\\Intelij Projects\\SeleniumFrameworkEx\\reports\\TestCaseName.png");
        FileUtils.copyFile(fSrc,fDesc);
        return "E:\\Intelij Projects\\SeleniumFrameworkEx\\reports\\TestCaseName.png";
    }

//Create another method to call the initializeDriver() so that browser will open
    @BeforeMethod(alwaysRun = true)
    public LandingPage loadApplication() throws IOException {
       driver =initializeDriver();
       //call the url loading method here as it is the global step
        landingPage=new LandingPage(driver);
        landingPage.UrlLand();
        return landingPage;
    }
}

