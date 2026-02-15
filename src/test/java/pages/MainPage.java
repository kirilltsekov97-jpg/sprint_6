package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // локаторы главной
    private final By cookieAccept = By.id("rcc-confirm-button");
    private final By cookieText = By.className("App_CookieText__1sbqp");
    private final By orderTop = By.xpath("(//button[normalize-space(.)='Заказать'])[1]");
    private final By orderBottom = By.xpath("(//button[normalize-space(.)='Заказать'])[2]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MainPage open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public MainPage acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieAccept)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieText));
        return this;
    }

    public void clickOrderTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderTop)).click();
    }

    public void clickOrderBottom() {
        wait.until(ExpectedConditions.elementToBeClickable(orderBottom)).click();
    }
}
