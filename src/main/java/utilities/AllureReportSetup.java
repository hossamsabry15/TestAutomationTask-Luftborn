package utilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;

public class AllureReportSetup {

    @BeforeSuite
    public void deleteAllureReport() {
        File resultsDir = new File("target/allure-results");
        if (resultsDir.exists()) {
            for (File file : resultsDir.listFiles()) {
                file.delete();
            }
        }
    }

    @AfterSuite
    public void openAllureReport() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;

        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "allure", "serve", "target/allure-results");
        } else if (os.contains("mac")) {
            processBuilder = new ProcessBuilder("open", "-a", "Terminal", "allure", "serve", "target/allure-results");
        } else { // Linux
            processBuilder = new ProcessBuilder("bash", "-c", "allure serve target/allure-results");
        }

        processBuilder.start();
    }
}