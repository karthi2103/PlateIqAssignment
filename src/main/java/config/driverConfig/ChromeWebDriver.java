package config.driverConfig;

import enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    log.info("Initializing Chrome Driver");
    return new ChromeDriver();
  }
}
