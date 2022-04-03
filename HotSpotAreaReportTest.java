package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import placelab.pages.CreatedReportPage;
import placelab.pages.HomePage;
import placelab.pages.HotspotAreaReportPage;
import placelab.pages.Login;
import placelab.utilities.WebDriverSetup;

import java.time.Duration;

public class HotSpotAreaReportTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String homePageUrl = System.getProperty("homepage");
    private String areaAnalysisUrl = System.getProperty("areareport");
    private String password = System.getProperty("password");
    private String username = System.getProperty("username");
    private String reportListUrl = System.getProperty("reportlist");
    private String user = System.getProperty("user");
    private String userRole = System.getProperty("userrole");
    private Login login;
    private HomePage homePage;
    private HotspotAreaReportPage hotspotAreaReportPage;
    private WebDriverWait wait;

    private CreatedReportPage createdReportPage;


    @Parameters({"browser"})

    @BeforeSuite(alwaysRun = true)
    public void initDriver(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        login = new Login(driver);
        homePage = new HomePage(driver);
        hotspotAreaReportPage = new HotspotAreaReportPage(driver);
        createdReportPage = new CreatedReportPage(driver);
    }

    @BeforeTest (alwaysRun = true, description = "User login to the PlaceLab homepage, and" +
            "validation that the user is successfully logged in to the homepage.")
    public void userLogin() {
        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Login with user credentials
        login.enterUsername(username);
        login.enterPassword(password);
        login.Submit();

        //Validate that user is on the homepage
        Assert.assertEquals(driver.getCurrentUrl(), homePageUrl);

        //Validate that user is logged in
        Assert.assertEquals(homePage.getUserRole(),userRole);
        assert (homePage.getUserName().contains(user));
    }

    @Test (priority = 1, alwaysRun = true, groups = {"Positive"}, description = "This test verifies " +
            "that user can access and open Hotspot Area Report page")

    public void goToAreaAnalysisPage () throws InterruptedException {

        //Validate that user can click on Hotspot Area Analysis link
        homePage.createHotSpotAreaReport();

        //Validation that user is on the Create Hotspot Area Analysis Report page
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(hotspotAreaReportPage.getCreateReportButton()));

        //Validation that elements for the creation of the Hotspot Area Report are visible
        Assert.assertEquals(driver.getCurrentUrl(),areaAnalysisUrl,"User is not directed to the Create Hotspot Area page.");
        assert (hotspotAreaReportPage.getHeaderText().contains(hotspotAreaReportPage.expectedHeaderText()));
        Assert.assertTrue(hotspotAreaReportPage.getReportNameField().isDisplayed(),"Report name field is not visible.");
        Assert.assertTrue(hotspotAreaReportPage.placesCategoryMenu().isDisplayed(),"Places Category menu is not visible.");
        Assert.assertTrue(hotspotAreaReportPage.getLocationNameField().isDisplayed(),"Location name field is not visible.");
        Assert.assertTrue(hotspotAreaReportPage.getRadiusField().isDisplayed(),"Radius field is not visible.");
        Assert.assertTrue(hotspotAreaReportPage.getLatitudeField().isDisplayed(),"Latitude field is not visible.");
        Assert.assertTrue(hotspotAreaReportPage.getLongitudeField().isDisplayed(),"Longitude field is not visible.");
        Assert.assertTrue(hotspotAreaReportPage.getCreateReportButton().isDisplayed(),"Create report button is not visible.");

    }

    @Test (priority = 2, alwaysRun = true, groups = {"Positive"}, description = "Validation that user can input data" +
            "into the report fields and create Hotspot Area report.")

    public void inputDataIntoReportFields() throws InterruptedException {

        //Validate that user can enter data into the fields
        hotspotAreaReportPage.setLatitude();
        hotspotAreaReportPage.setRadius();
        hotspotAreaReportPage.attractionsOption();
        hotspotAreaReportPage.setReportName();
        hotspotAreaReportPage.setLongitude();
        hotspotAreaReportPage.createReport();
        Thread.sleep(3000);
    }

    @Test (priority = 3, alwaysRun = true, groups = {"Positive"}, description = "Validation that the Hotspot Area " +
            "report is created.")

    public void reportCreatedValidation() throws InterruptedException {
        //Waiting for the report to be created
        Thread.sleep(30000);

        //Open created report
        homePage.clickOnReport(hotspotAreaReportPage.getCreatedReportName());

        //Validation that report elements/widgets are visible
        assert (createdReportPage.getReportName().contains(hotspotAreaReportPage.getCreatedReportName()));
        assert (createdReportPage.analysisInfoWidget().isDisplayed()):"Analysis widget is not displayed.";
        assert (createdReportPage.attributePresenceWidget().isDisplayed()):"Attribute Presence widget is not displayed.";
        assert (createdReportPage.bayesianScoreWidget().isDisplayed()):"Bayesian Score widget is not displayed.";
        assert (createdReportPage.distanceAnalysisWidget().isDisplayed()):"Distance Analysis widget is not displayed.";
        assert (createdReportPage.distributionPerProviderWidget().isDisplayed()):"Distribution per Provider widget is not displayed.";
        assert (createdReportPage.distributionWidget().isDisplayed()):"Distribution widget is not displayed.";
        assert (createdReportPage.mapWidget().isDisplayed()):"Map widget is not displayed.";
        assert (createdReportPage.mostFrequentWidget().isDisplayed()):"Most Frequent categories widget is not displayed.";
        assert (createdReportPage.reviewDistributionWidget().isDisplayed()):"Review Distribution widget is not displayed.";
        assert (createdReportPage.photoDistributionWidget().isDisplayed()):"Photo Distribution widget is not displayed.";
        assert (createdReportPage.poiCountWidget().isDisplayed()):"Total POI count widget is not displayed.";
    }

    @AfterTest (alwaysRun = true, groups = {"Positive"}, description = "Delete created Hotspot Area report")

    public void deleteReport() {
        //Navigate back to the report list
        driver.navigate().to(reportListUrl);

        //Choose created report and delete it
        homePage.clickOnCheckBox();
        homePage.deleteReport();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("myModalLabel"))));
        homePage.confirmDelete();

        //User sign out from the application
        homePage.signOut();
    }

    @AfterSuite (alwaysRun = true)
    public void quitBrowser() {
        driver.quit();
    }
}