package config.driverConfig;

import com.sun.javafx.PlatformUtil;
import enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IeDriver implements ParentDriver {
  @Override
  public Browser getBrowserName() {
    return Browser.IE;
  }

  @Override
  public WebDriver getWebDriver() {
    if(PlatformUtil.isWindows()) {
      WebDriverManager.iedriver().setup();
      log.info("Initializing IE Driver");
      return new InternetExplorerDriver();
    }
    log.error("IE can only be run in windows machine.");
    throw  new IllegalArgumentException("IE browser invoked in non-windows machine");
  }
}
