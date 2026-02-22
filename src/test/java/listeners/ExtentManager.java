package listeners;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {
        if (extent == null) {
            try {
                // This is the folder where the tests are running from
                String projectDir = System.getProperty("user.dir");

                // Create reports folder if not exists
                Path reportsDir = Paths.get(projectDir, "reports");
                if (!Files.exists(reportsDir)) {
                    Files.createDirectories(reportsDir);
                }

                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Path reportFile = reportsDir.resolve("ExtentReport_" + timestamp + ".html");

                ExtentSparkReporter spark = new ExtentSparkReporter(reportFile.toString());
                spark.config().setReportName("bstackdemo Automation Report");
                spark.config().setDocumentTitle("Test Execution Report");

                extent = new ExtentReports();
                extent.attachReporter(spark);

                extent.setSystemInfo("Project", "bstackdemo Capstone");
                extent.setSystemInfo("Framework", "Selenium + TestNG + Maven");

               
                System.out.println("✅ Extent Report will be created at: " + reportFile);

            } catch (Exception e) {
                throw new RuntimeException("Failed to create Extent report folder/file", e);
            }
        }
        return extent;
    }
}
