package ConfigurationDriver;

import Resources.ExtendReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener
{
   ExtentTest test;
   ExtentReports extend=  ExtendReportNG.getTestReport();
   @Override
   public void onTestStart(ITestResult result) {


// TODO Auto-generated method stub
      //As we know to get the Extend report of every test at first we have to create test so that it can create a test for every method
      //As result parameter of onStart() know about the test which is going to executed that's why here we are using
     test= extend.createTest(result.getMethod().getMethodName());
   }

   @Override
   public void onTestSuccess(ITestResult result) {
// TODO Auto-generated method stub
      test.log(Status.PASS,"Test is passed");
   }

   @Override
   public void onTestFailure(ITestResult result) {
// TODO Auto-generated method stub
       //By getThrowable() we know the error message when test gets failed. It prints the error message in report.
       test.fail(result.getThrowable());
      //Give life to the driver so that for every class for that class driver field will be executed.
       try {
           driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").
                   get(result.getInstance());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

      //on test fail take a ss and store it in the file (Call the getScreenShot() which hold ss taken code
      String filePath;
       try {
            filePath=getScreenShot(result.getMethod().getMethodName(),driver);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       //call the TestExtend class method to save the ss in the report log
      test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

   }

   @Override
   public void onTestSkipped(ITestResult result) {
// TODO Auto-generated method stub  
      System.out.println("Skip of test cases and its details are : "+result.getName());
   }

   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub  
      System.out.println("Failure of test cases and its details are : "+result.getName());
   }

   @Override
   public void onStart(ITestContext context) {
// TODO Auto-generated method stub  
   }

   @Override
   public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
      extend.flush();
   }
}  