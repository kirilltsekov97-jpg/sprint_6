package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By aboutArenda = By.xpath("//div[normalize-space(.)='Про аренду']");
    private final By date = By.xpath("//input[contains(@placeholder,'Когда привезти самокат')]");
    private final By rentalPeriod = By.cssSelector(".Dropdown-control");
    private final By rentalPeriodOptionDay = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space(.)='сутки']");
    private final By colorBlack = By.id("black");
    private final By comments = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    private final By order = By.xpath("//div[contains(@class,'Order_Buttons')]//button[normalize-space(.)='Заказать']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public RentPage waitUntilOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutArenda));
        return this;
    }

    public RentPage fillRentForm(String dateValue, String commentValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(date)).sendKeys(dateValue);
        driver.findElement(date).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOptionDay)).click();

        wait.until(ExpectedConditions.elementToBeClickable(colorBlack)).click();
        driver.findElement(comments).sendKeys(commentValue);
        return this;
    }

    public void clickOrder() {
        WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(order));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderBtn);
        orderBtn.click();
    }
}
