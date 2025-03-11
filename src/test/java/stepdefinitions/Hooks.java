package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Tự động tải phiên bản ChromeDriver phù hợp
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Phóng to cửa sổ trình duyệt
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) { // Kiểm tra null để tránh NullPointerException
            if (scenario.isFailed()) { // Chỉ chụp ảnh màn hình khi kịch bản thất bại
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
            driver.quit(); // Đóng trình duyệt
        }
    }
}