package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessOrderModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By successText = By.xpath("//*[contains(normalize-space(.),'Заказ оформлен')]");
    private final By checkingStatus = By.xpath("//button[normalize-space(.)='Посмотреть статус']");

    public SuccessOrderModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitSuccess() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successText));
    }

    public void waitStatusButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkingStatus));
    }
}
