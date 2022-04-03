package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreatedReportPage {
    WebDriver driver;

    public CreatedReportPage (WebDriver driver) {
        this.driver = driver;
    }

    public String getReportName () {
        return driver.findElement(By.id("report-page-title")).getText();
    }

    public WebElement analysisInfoWidget () {
        return driver.findElement(By.xpath("//*[@id='hs_query_info']/div[4]"));
    }

    public WebElement mapWidget () {
        return driver.findElement(By.id("hotspot_map_info"));
    }

    public WebElement poiCountWidget () {
        return driver.findElement(By.id("hotspot_total_poi_count"));
    }

    public WebElement attributePresenceWidget () {
        return driver.findElement(By.id("hotspot_attribute_completeness"));
    }

    public WebElement distanceAnalysisWidget () {
        return driver.findElement(By.id("hotspot_distance_analysis"));
    }

    public WebElement bayesianScoreWidget() {
        return driver.findElement(By.id("bayesian_average"));
    }

    public WebElement distributionPerProviderWidget() {
        return driver.findElement(By.id("hotspot_category_distribution_per_provider"));
    }

    public WebElement distributionWidget() {
        return driver.findElement(By.id("category_distribution_per_provider"));
    }

    public WebElement mostFrequentWidget() {
        return driver.findElement(By.id("raw_category_frequency"));
    }

    public WebElement reviewDistributionWidget() {
        return driver.findElement(By.id("review_distribution"));
    }

    public WebElement photoDistributionWidget() {
        return driver.findElement(By.id("photo_distribution"));
    }
}
