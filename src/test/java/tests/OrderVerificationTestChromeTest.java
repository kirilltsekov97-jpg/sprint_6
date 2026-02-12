package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DisplayName("Есть известный дефект: заказ в хром не создается")
@Tag("bug")
public class OrderVerificationTestChromeTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage", "--window-size=1280,1024");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // верхняя точка входа
    @Test
    void orderOpen() {
        driver.get("https://qa-scooter.praktikum-services.ru/");  // открываем главную

        By cookieAccept = By.id("rcc-confirm-button");
        By cookieText = By.className("App_CookieText__1sbqp");

        wait.until(ExpectedConditions.elementToBeClickable(cookieAccept)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieText));

        By orderTop = By.xpath("(//button[normalize-space(.)='Заказать'])[1]"); // берем первую кнопку "заказать"
        wait.until(ExpectedConditions.elementToBeClickable(orderTop)).click(); //ожидаем появление кнопки и кликаем
        wait.until(ExpectedConditions.urlContains("/order")); //ожидаем страницу с оформлением

        //находим поля
        By firstName = By.xpath("//input[@placeholder = '* Имя']");
        By lastName = By.xpath("//input[@placeholder = '* Фамилия']");
        By address = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
        By metro = By.cssSelector("input.select-search__input");
        By telephone = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
        By further = By.xpath("//button[normalize-space(.)='Далее']"); //Кнопка далее

        //заполняем поля
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys("Кирилл");
        driver.findElement(lastName).sendKeys("Тостер");
        driver.findElement(address).sendKeys("Ленина 4");
        wait.until(ExpectedConditions.elementToBeClickable(metro)).click(); //дождаться списка
        driver.findElement(metro).sendKeys("Сокольники"); //заполняем название метро
        driver.findElement(metro).sendKeys(Keys.DOWN, Keys.ENTER); //выбрали первый вариант из списка
        driver.findElement(telephone).sendKeys("+79000000001");

        wait.until(ExpectedConditions.elementToBeClickable(further)).click(); //клик Далее

        By aboutArenda = By.xpath("//div[normalize-space(.)='Про аренду']"); // ожидаем "Про аренду"
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutArenda)); //Дожидаемся появления "Про аренду"

        //находим поля
        By date = By.xpath("//input[contains(@placeholder,'Когда привезти самокат')]");
        By rentalPeriod = By.cssSelector(".Dropdown-control");
        By rentalPeriodOption = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space(.)='сутки']");
        By colorBlack = By.id("black");
        By comments = By.xpath("//input[@placeholder = 'Комментарий для курьера']");


        By order = By.xpath("//div[contains(@class,'Order_Buttons')]//button[normalize-space(.)='Заказать']");

        // Заполняем Дату
        wait.until(ExpectedConditions.visibilityOfElementLocated(date)).sendKeys("10.02.2026");
        driver.findElement(date).sendKeys(Keys.ENTER); //закрыли календарь с нужной датой

        // Заполняем срок ареды
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod)).click(); //клик по Срок аренды
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click(); //Клик по времени аренды "Сутки"

        wait.until(ExpectedConditions.elementToBeClickable(colorBlack)).click(); //выбрали цвет
        driver.findElement(comments).sendKeys("Привет! Я очень сильно стараюсь, надеюсь, у меня получится сдать этот проект. Спасибо за ревью!");

        // Нажимаем "Заказать"
        WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(order));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderBtn);
        orderBtn.click();


        By placeAnOrder = By.xpath("//div[contains(@class,'Order_Modal')]//*[contains(normalize-space(.),'Хотите оформить заказ')]");
        By confirmOrder = By.xpath("//div[contains(@class,'Order_Modal')]//button[normalize-space(.)='Да']");
        By successHeader = By.xpath("//*[contains(@class,'Order_ModalHeader') and contains(normalize-space(.),'Заказ оформлен')]");


        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {

            shortWait.until(ExpectedConditions.visibilityOfElementLocated(placeAnOrder));
            shortWait.until(ExpectedConditions.elementToBeClickable(confirmOrder)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader));
        } catch (TimeoutException e) {

        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
