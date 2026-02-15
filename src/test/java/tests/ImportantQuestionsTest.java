import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.openqa.selenium.*;


//создал драйвер, написали ожидаемое в FAQ
public class ImportantQuestionsTest {

    private WebDriver driver;

    static Stream<org.junit.jupiter.params.provider.Arguments> frequentQuestions() {
        return Stream.of(
                arguments(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                arguments(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                arguments(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                arguments(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                arguments(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                arguments(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                arguments(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                arguments(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")

        );

    }


    @ParameterizedTest(name = "FAQ {0}")
    @MethodSource("frequentQuestions")
    public void dropDownListTest(int index, String expectedAnswer) {
        // создаем браузер
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://qa-scooter.praktikum-services.ru/");  // открываем главную страницу

        By heading = By.id("accordion__heading-" + index);
        By panel = By.id("accordion__panel-" + index);


        //ждем, когда раскроется выпадашка
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(heading));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
        //Ждем, когда появится ответ
        String answerText = wait.until(ExpectedConditions.visibilityOfElementLocated(panel)).getText();

        assertEquals(expectedAnswer, answerText);

    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}