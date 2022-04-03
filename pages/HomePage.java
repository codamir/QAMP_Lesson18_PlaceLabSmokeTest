package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    public String getUserRole () {
        return driver.findElement(By.id("user-role")).getText();
    }

    public String getUserName () {
        return driver.findElement(By.id("user-name")).getText();
    }

    public void signOut () {
        driver.findElement(By.xpath("//*[@id='actions-nav-item']")).click();
        driver.findElement(By.linkText("Sign out")).click();
    }

    public void createReportDropdownMenu () {
        driver.findElement(By.id("create-menu")).click();
    }

    public void createHotSpotAreaReport () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement hotspotAreaLink = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/li[1]/a"));

        createReportDropdownMenu();
        wait.until(ExpectedConditions.elementToBeClickable(hotspotAreaLink)).click();
    }

    public void clickOnReport(String reportName) {
        String path = "//div[@class='query_name']/a[text()='" + reportName + "']";
        WebElement report = driver.findElement(By.xpath(path));
        report.click();
    }


    public void clickOnCheckBox(){
        driver.findElement(By.xpath("//*[@id='table_queries']/tbody/tr[1]/td[2]/div")).click();
    }

    public void deleteReport() {
        driver.findElement(By.xpath("//*[@id='action-delete']/a")).click();
    }

    public void confirmDelete() {
        driver.findElement(By.xpath("//*[@id='confirm-link']")).click();
    }


}
