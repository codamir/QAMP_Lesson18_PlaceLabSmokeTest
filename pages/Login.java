package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername (final String username) {
        WebElement enterUsername = driver.findElement(By.id("email"));
        enterUsername.sendKeys(username);
    }

    public void enterPassword (final String password) {
        WebElement enterPassword = driver.findElement(By.id("password"));
        enterPassword.sendKeys(password);
    }

    public void Submit () {
        WebElement sumbit = driver.findElement(By.xpath("//input[@type='submit']"));
        sumbit.click();
    }
}
