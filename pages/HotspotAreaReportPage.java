package placelab.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HotspotAreaReportPage {
    WebDriver driver;
    private String reportName = "Empire State Building attractions";
    private Double latitude = 40.748563573199924;
    private Double longitude = -73.9856319450437;
    private Integer radius = 70;

    public HotspotAreaReportPage(WebDriver driver) {
        this.driver = driver;
    }


    public String expectedHeaderText() {
        return "Create Hotspot Area Analysis Report";
    }

    public String getHeaderText() {
        return driver.findElement(By.id("hotspot_dialog")).getText();
    }

    public void setReportName() {
        pause(5).until(ExpectedConditions.visibilityOf(getReportNameField()));
        getReportNameField().clear();
        getReportNameField().sendKeys(reportName);
    }

    public WebElement getReportNameField () {
        return driver.findElement(By.id("name"));
    }

    public String getCreatedReportName() {
        return reportName;
    }

    public WebElement placesCategoryMenu () {
        return driver.findElement(By.id("hotspot_poi_query"));
    }

    public void setLocationName(String locationName) {
        getLocationNameField().sendKeys(locationName);
    }

    public WebElement getLocationNameField () {
        return driver.findElement(By.id("location_name"));
    }

    public void setRadius () {
        getRadiusField().clear();
        getRadiusField().sendKeys(String.valueOf(radius));
    }

    public WebElement getRadiusField () {
        return driver.findElement(By.id("radius"));
    }

    public void setLatitude () {
        getLatitudeField().sendKeys(String.valueOf(latitude));
    }

    public WebElement getLatitudeField () {
        return driver.findElement(By.id("city_lat"));
    }

    public void setLongitude () {
        getLongitudeField().sendKeys(Keys.CONTROL+Keys.chord("a"));
        getLongitudeField().sendKeys(String.valueOf(longitude));
    }

    public WebElement getLongitudeField () {
        return driver.findElement(By.id("city_lng"));
    }

    public void createReport () {
        pause(5).until(ExpectedConditions.visibilityOf(getCreateReportButton()));
        getCreateReportButton().click();
    }

    public WebElement getCreateReportButton () {
        return driver.findElement(By.xpath("//*[@id='hotspot_poi_query']/button"));
    }

    public void attractionsOption() {
        driver.findElement(By.xpath("//*[@id='hotspot_poi_query']/div[2]/div/button/b")).click();
        pause(5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='hotspot_poi_query']/div[2]/div/ul/div/li[1]/a/label"))));
        WebElement attractions = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div[2]/form/div[2]/div/ul/div/li[3]/a/label"));
        attractions.click();
    }

    private WebDriverWait pause (int seconds) {
        return new WebDriverWait(driver,Duration.ofSeconds(seconds));
    }
}


