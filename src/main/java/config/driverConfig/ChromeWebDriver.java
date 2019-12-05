package config.driverConfig;

import enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChromeWebDriver implements ParentDriver {

  @Override
  public Browser getBrowserName() {
    return Browser.CHROME;
  }

  @Override
  public WebDriver getWebDriver() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");
    options.addArguments("--kiosk");
    options.addArguments("--disable-popup-blocking");
    log.info("Initializing Chrome Driver");
    return new ChromeDriver(options);
  }
}
