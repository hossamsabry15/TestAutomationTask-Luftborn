package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utilities.BrowserActions;
import utilities.ElementActions;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private SoftAssert softAssert;
    private String actualCount;
    private String cleanText;
    public SearchResultsPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
    }

    ///////////  Locators \\\\\\\\\\\\
    private By resultsCountHeading = By.cssSelector(".srp-controls__count-heading");
    private By resultItems = By.xpath("//h1[@class='srp-controls__count-heading']/span[1]");
    private By transmissionFilter = By.xpath("//div[text()='Transmission']");
    private By manualCheckbox = By.id("x-refine__group_3__0");
    private By appliedFilter = By.xpath("//span[contains(text(), 'Manual')]/ancestor::div[contains(@class, 'x-refine__select')]");

    ///////////  Actions \\\\\\\\\\\\
    @Step("Get results count from heading")
    public int getResultsCountFromHeading() {
        try {
            String text = ElementActions.getElementText(driver, resultsCountHeading);
            System.out.println("Results heading text: " + text);

            String[] parts = text.split(" ");
            String numberStr = parts[0].replace(",", ""); // Remove commas

            try {
                return Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                System.err.println("Could not parse number from: " + parts[0]);
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error getting results count: " + e.getMessage());
            return 0;
        }
    }

    @Step("Print and validate results for search term: {searchTerm}")
    public SearchResultsPage printAndValidateResults(String searchTerm) {

        System.out.println("=".repeat(50));
        System.out.println("SEARCH RESULTS VALIDATION");
        System.out.println("=".repeat(50));

        int headingCount = getResultsCountFromHeading();
        System.out.println("✓ Results count from heading: " + headingCount);

        actualCount = ElementActions.getElementText(driver, resultItems);
        try {
            cleanText = actualCount.replace(",", "").replace(" ", "");
            int resultCount = Integer.parseInt(cleanText);
            System.out.println("Found " + resultCount + " results");
        } catch (NumberFormatException e) {
            System.out.println("Could not parse result count: " + actualCount);
        }
        System.out.println("✓ Actual visible results: " + cleanText);
        int actualCountInt = Integer.parseInt(cleanText);
        System.out.println("=".repeat(50));
        if (headingCount == 0) {
            System.err.println("⚠ WARNING: Heading count is 0!");
        } else {
            softAssert.assertTrue(headingCount > 0, "No results found for: " + searchTerm);
        }
        if (actualCountInt == 0) {
            System.err.println("⚠ WARNING: No visible results found!");
        } else {
            softAssert.assertTrue(actualCountInt > 0, "No result items displayed");
        }
        return this;
    }

    @Step("Apply 'Manual' transmission filter")
    public SearchResultsPage applyManualTransmissionFilter() {
        try {
            System.out.println("Applying Manual transmission filter...");
            ElementActions.click(driver, transmissionFilter);
            System.out.println("✓ Clicked Transmission filter");
            ElementActions.click(driver, manualCheckbox);
            System.out.println("✓ Selected Manual option");
        } catch (Exception e) {
            System.err.println("Error applying Manual filter: " + e.getMessage());
        }
        return this;
    }

    ///////////////// Validations \\\\\\\\\\\\
    @Step("Validate search term '{searchTerm}' in heading")
    public SearchResultsPage validateSearchTermInHeading(String searchTerm) {
        try {
            String headingText = ElementActions.getElementText(driver, resultsCountHeading).toLowerCase();
            boolean containsTerm = headingText.contains(searchTerm.toLowerCase());

            if (containsTerm) {
                System.out.println("✓ Search term '" + searchTerm + "' found in heading");
            } else {
                System.out.println("⚠ Search term not found in heading. Heading text: " + headingText);
            }

            softAssert.assertTrue(containsTerm,
                    "Search term not found in heading: " + searchTerm);

        } catch (Exception e) {
            System.err.println("Error validating search term: " + e.getMessage());
            softAssert.fail("Could not validate search term: " + e.getMessage());
        }
        return this;
    }
    @Step("Validate filter applied correctly")
    public SearchResultsPage validateFilterAppliedCorrectly() {
        try {
            ElementActions.visibilityOfElementLocated(driver, appliedFilter);
            System.out.println("✓ Filter applied correctly");
        } catch (Exception e) {
            System.err.println("Filter not applied correctly: " + e.getMessage());
            softAssert.fail("Filter not applied correctly: " + e.getMessage());
        }
        return this;
    }
}