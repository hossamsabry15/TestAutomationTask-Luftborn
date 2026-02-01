package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.BrowserActions;
import utilities.ElementActions;

public class HomePage {
    private WebDriver driver;
    private SoftAssert softAssert;
    private String url = System.getProperty("baseUrl");
    private String expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";
    private String actualTitle;
    public HomePage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
    }
    ///////////  Locators \\\\\\\\\\\\
    private By searchBox = By.id("gh-ac");
    private By searchButton = By.id("gh-search-btn");

    /////////// Actions \\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public HomePage navigate() {
        BrowserActions.navigate(driver, url);
        return this;
    }
    @Step("Enter search term {term} in search box")
    public HomePage enterSearchTerm(String term) {
        ElementActions.enterData(driver, searchBox, term);
        return this;
    }
    @Step("Click on Search Button")
    public HomePage clickOnSearchButton() {
        ElementActions.click(driver, searchButton);
        return this;
    }
    /////////// Validations \\\\\\\\\\\\
    @Step("Validate Home Page Title")
    public HomePage validateHomePageTitle() {
        actualTitle = driver.getTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, "Home Page title does not match the expected title.");
        return this;
    }

}
