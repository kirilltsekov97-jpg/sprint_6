package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.OrderPage;
import pages.RentPage;
import pages.ConfirmOrderModal;
import pages.SuccessOrderModal;

import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;




import java.time.Duration;
import java.util.stream.Stream;

public class OrderVerificationTestMozilaParameterizedTest {
    private WebDriver driver;


    @BeforeEach
    void startBrowser() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1280, 1024));

    }

    static Stream<Arguments> orderCases() {
        return Stream.of(
                // 1й набор для ВЕРХНЕЙ кнопки
                arguments(By.xpath("(//button[normalize-space(.)='Заказать'])[1]"),
                        "Кирилл", "Тостер", "Ленина 4", "Сокольники", "+79000000001",
                        "10.02.2026", "сутки", "black",
                        "Как же я устал это писать... Но результат того стоит!"),
                // 1й набор для НИЖНЕЙ кнопки
                arguments(By.xpath("(//button[normalize-space(.)='Заказать'])[2]"),
                        "Кирилл", "Тостер", "Ленина 4", "Сокольники", "+79000000001",
                        "10.02.2026", "сутки", "black",
                        "Как же я устал это писать... Но результат того стоит!"),

                // 2й набор для ВЕРХНЕЙ кнопки
                arguments(By.xpath("(//button[normalize-space(.)='Заказать'])[1]"),
                        "Ллирик", "Ретсот", "Олимпиады 4", "Лубянка", "+7934534521",
                        "10.02.2026", "сутки", "black",
                        "Продам Mazda 6 2012гв, собственник, салоны не беспокоить"),

                // 2й набор для НИЖНЕЙ кнопки
                arguments(By.xpath("(//button[normalize-space(.)='Заказать'])[2]"),
                        "Ллирик", "Ретсот", "Олимпиады 4", "Лубянка", "+7934534521",
                        "10.02.2026", "сутки", "black",
                        "Продам Mazda 6 2012гв, собственник, салоны не беспокоить")

        );
    }

    @ParameterizedTest
    @MethodSource("orderCases")
    void orderButtons(By orderButton, String firstNameValue, String lastNameValue, String addressValue, String metroValue, String telephoneValue, String dateValue, String periodValue, String colorValue,
                      String commentValue) {
        //открываем главную и жмем куки
        new MainPage(driver)
                .open()
                .acceptCookies();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(orderButton))
                .click();


        // заполнить первые поля про аренду
        new OrderPage(driver)
                .fillClientForm(firstNameValue, lastNameValue, addressValue, metroValue, telephoneValue)
                .clickFurther();
        // заполняем второе окно про аренду
        new RentPage(driver)
                .waitUntilOpened()
                .fillRentForm(dateValue, commentValue)
                .clickOrder();
        // подтверждаем заказ
        new ConfirmOrderModal(driver)
                .waitUntilOpened()
                .clickYes();
        // окно с созданым заказом
        SuccessOrderModal success = new SuccessOrderModal(driver);
        success.waitSuccess();
        success.waitStatusButton();

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
