package config.driverConfig;

import enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FfDriver implements ParentDriver {
  @Override
  public Browser getBrowserName() {
    return Browser.FF;
  }

  @Override
  public WebDriver getWebDriver() {
    WebDriverManager.firefoxdriver().setup();
    log.info("Initializing Fire Fox Driver");
    return new FirefoxDriver();
  }
}
