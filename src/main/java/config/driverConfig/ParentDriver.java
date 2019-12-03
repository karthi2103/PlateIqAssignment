package config.driverConfig;

import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public interface ParentDriver {
  Browser getBrowserName();
  WebDriver getWebDriver();
}
