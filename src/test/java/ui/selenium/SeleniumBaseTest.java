package ui.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumBaseTest {

    protected WebDriver driver;

    protected static final String BASE_URL = "http://localhost:8080";
    protected static final String ADMIN_LOGIN = "admin";
    protected static final String ADMIN_PASSWORD = "secret123";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void loginAsAdmin() {
        driver.get(BASE_URL + "/login");

        driver.findElement(By.id("username"))
                .sendKeys(ADMIN_LOGIN);

        driver.findElement(By.id("password"))
                .sendKeys(ADMIN_PASSWORD);

        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.urlToBe(BASE_URL + "/admin")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("n-name")
                )
        );
    }

}