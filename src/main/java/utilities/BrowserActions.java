package utilities;

import org.openqa.selenium.WebDriver;

public class BrowserActions {
    static WebDriver driver;

    public static void navigate(WebDriver driver, String url) {
        System.out.println("navigating to url: " + url);
        driver.navigate().to(url);
    }
}
