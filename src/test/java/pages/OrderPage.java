package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // локаторы для страницы Про Аренду
    private final By firstName = By.xpath("//input[@placeholder = '* Имя']");
    private final By lastName = By.xpath("//input[@placeholder = '* Фамилия']");
    private final By address = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    private final By metro = By.cssSelector("input.select-search__input");
    private final By telephone = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
    private final By further = By.xpath("//button[normalize-space(.)='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // заполняем поля с Ареднйо
    public OrderPage fillClientForm(String firstNameValue, String lastNameValue, String addressValue,
                                    String metroValue, String phoneValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(firstNameValue);
        driver.findElement(lastName).sendKeys(lastNameValue);
        driver.findElement(address).sendKeys(addressValue);

        wait.until(ExpectedConditions.elementToBeClickable(metro)).click();
        driver.findElement(metro).sendKeys(metroValue);
        driver.findElement(metro).sendKeys(Keys.DOWN, Keys.ENTER);

        driver.findElement(telephone).sendKeys(phoneValue);
        return this;
    }

    public OrderPage clickFurther() {
        wait.until(ExpectedConditions.elementToBeClickable(further)).click();
        return this;
    }
}
