package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmOrderModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By placeAnOrder = By.xpath("//*[contains(normalize-space(.),'Хотите оформить заказ')]");
    private final By confirmYes = By.xpath("//div[contains(@class,'Order_Modal')]//div[contains(@class,'Order_Buttons')]//button[normalize-space(.)='Да']");

    public ConfirmOrderModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ConfirmOrderModal waitUntilOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeAnOrder));
        return this;
    }

    public void clickYes() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();
    }
}
