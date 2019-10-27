package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.$;

public class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldBeSuccessIfDataIsCorrect() {

        driver.get("http://localhost:9999");
        sleep(1);

        fillWithSelenium();
    }

    void fillWithSelenium() {

        WebElement name = driver.findElement(By.cssSelector("[data-test-id=name] input"));
        name.sendKeys("Дарья");
        sleep(1);

        WebElement phone = driver.findElement(By.cssSelector("[data-test-id=phone] input"));
        phone.sendKeys("+79032596295");
        sleep(1);

        WebElement agreement = driver.findElement(By.cssSelector("[data-test-id=agreement]"));
        agreement.click();
        sleep(1);

        WebElement continueButton = driver.findElement(By.tagName("button"));
        continueButton.click();
        sleep(1);
    }

    /*@Test
    void shouldBeSuccessIfDataIsCorrectWithSelenide() {
        SelenideElement form = $(By.className("form"));
        form.$(By.cssSelector("[data-test-id=name] input")).setValue("Daria");

        SelenideElement name = $(By.cssSelector("[data-test-id=name] input"));
        name.sendKeys("Дарья");
     }*/

    void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
