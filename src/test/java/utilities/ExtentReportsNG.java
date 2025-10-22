package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBasePackage.baseClassSetUp;

public class ExtentReportsNG extends baseClassSetUp implements ITestListener {

	String reportName;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public static WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		String currentTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test_Summary_Report-" + currentTimeStamp + ".html";

		// Create reports folder if missing
		File reportsDir = new File(System.getProperty("user.dir") + File.separator + "Reports");
		if (!reportsDir.exists()) {
			reportsDir.mkdir();
		}

		sparkReporter = new ExtentSparkReporter(reportsDir + File.separator + reportName);
		sparkReporter.config().setDocumentTitle("Automation Execution Report");
		sparkReporter.config().setReportName("Codenbox Web Test Summary");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Codenbox");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		// fetch parameters from testng.xml
		String osName = context.getCurrentXmlTest().getParameter("OS");
		String browserName = context.getCurrentXmlTest().getParameter("Browser");
		if (osName != null)
			extent.setSystemInfo("Operating System", osName);
		if (browserName != null)
			extent.setSystemInfo("Browser Name", browserName);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups Name", includedGroups.toString());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " passed successfully ✅");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " failed ❌. Error: " + result.getThrowable());

//        try {
//            String screenPath = getScreenshot(result.getName());
//            test.addScreenCaptureFromPath(screenPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " was skipped ⚠️");
		if (result.getThrowable() != null)
			test.log(Status.INFO, result.getThrowable().getMessage());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

		try {
			File extentReport = new File(
					System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName);
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(extentReport.toURI());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Screenshot helper
//    public String getScreenshot(String testName) throws IOException {
//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + testName + ".png";
//        File target = new File(dest);
//        FileUtils.copyFile(src, target);
//        return dest;
//    }
}
