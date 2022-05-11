package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.events.Event;
import pages.WebListener;

public class TestBase {

    private static Logger log = LoggerFactory.getLogger("TestBase.class");
    public WebDriver webDriver;
    public EventFiringWebDriver driver;
    private WebListener webListener;
    String url = "http://automationpractice.com/index.php";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        log.info("DRIVER INITIATED PROPERLY");
    }

    @BeforeEach
    void beforeEach() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        driver = new EventFiringWebDriver(webDriver);
        webListener = new WebListener();
        driver.register(webListener);
        driver.get(url);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
        log.info("DRIVER CLOSED PROPERLY");
    }
}
