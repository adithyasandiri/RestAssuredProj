package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	// public ExtentHtmlReporter htmlReport;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports xReports;
	public ExtentTest xTest;

	// Loc to configure the extent report
	// create a new HTml report on every test execution triggered by testing.html
	// HTML file should be casted as ExtentReport

	public void onStart(ITestContext testContext) {
		// Location and fileName for the HTML report

		String dateStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report" + dateStamp + ".html";

		// htmlReport = new
		// ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName);
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + repName);
		sparkReporter.config().setDocumentTitle("RestAssuredAutomation Report");
		sparkReporter.config().setReportName("Pet Store users Api Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		// htmlReport.config().setAutoCreateRelativePathMedia(true); //for screenShot

		xReports = new ExtentReports();
		xReports.attachReporter(sparkReporter);
		xReports.setSystemInfo("Application", "Pet Store Users API");
		xReports.setSystemInfo("Operatiing System", System.getProperty("os.name"));
		xReports.setSystemInfo("User Name", System.getProperty("user.name"));
		xReports.setSystemInfo("Environment", "QA");

		xReports.setSystemInfo("user", "Aditya");
		xReports.setSystemInfo("Platform", "Windows");

	}

	public void onTestSuccess(ITestResult tr) {
		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.PASS, "Test is Passed");
		xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult tr) {
		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.FAIL, "Test is Failed");
		xTest.log(Status.FAIL, tr.getThrowable());
		xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String ssPath = System.getProperty("user.dir") + "/ScreenShots/" + tr.getName() + ".png";
		File file = new File(ssPath);
		if (file.exists()) {
			xTest.fail("Screenshot for the failed test is : " + xTest.addScreenCaptureFromPath(ssPath));
		}

	}

	public void onTestSkipped(ITestResult tr) {
		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.SKIP, "Test is Failed");
		xTest.log(Status.SKIP, tr.getThrowable());
		xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));

	}

	public void onFinish(ITestContext testContext) {
		xReports.flush();
	}

}
