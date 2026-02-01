package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.*;

public class EbaySearchTest extends AllureReportSetup {
    // Variables
    private WebDriver driver;
    private SoftAssert softAssert;
    JsonFileManager testData;

    @Test(description = "Ebay Search Functionality Test")
    @Description("Given that I am on the Ebay homepage, When I search for a product, Then the search results should be displayed correctly")
    public void ebaySearchFunctionalityTest() {
        driver = DriverFactory.initDriver();
        new pages.HomePage(driver, softAssert)
                .navigate()
                .validateHomePageTitle()
                .enterSearchTerm(testData.getTestData("searchTerm"))
                .clickOnSearchButton();
        new pages.SearchResultsPage(driver, softAssert)
                .printAndValidateResults(testData.getTestData("searchTerm"))
                .validateSearchTermInHeading(testData.getTestData("searchTerm"))
                .applyManualTransmissionFilter()
                .validateFilterAppliedCorrectly();

    }
    
    //////////////////////// Configurations \\\\\\\\\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        PropertiesReader.loadProperties();
        softAssert = new SoftAssert();
        testData = new JsonFileManager("src/test/resources/TestDataJsonFiles/TestData.json");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}
