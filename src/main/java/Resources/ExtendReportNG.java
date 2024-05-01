package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportNG {
    public static ExtentReports getTestReport(){
       // String path=System.getProperty("user.dir")+"\\reports\\index.html";
        String path= "E:\\Intelij Projects\\SeleniumFrameworkEx\\reports";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Test");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("TesterName","Shreyashi");
        return extent;
    }
}
