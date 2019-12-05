package config.driverConfig;

import com.google.common.base.Enums;
import com.google.common.base.Preconditions;
import enums.Browser;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DriverConfig {
  @Value("${browserName}")
  private String browserName;

  private Map<Browser,ParentDriver> browserToDriverMap;

  @Autowired
  public DriverConfig(List<ParentDriver> webDriverList) {
    this.browserToDriverMap = webDriverList.stream()
            .collect(Collectors.toMap(ParentDriver::getBrowserName,Function.identity()));
  }


  public Optional<WebDriver> configureWebDriver(){
    Browser browser = Enums.getIfPresent(Browser.class, browserName.toUpperCase()).orNull();
    Preconditions.checkState(browser != null, "provide valid browser name");
    return Optional.ofNullable(browserToDriverMap.get(browser).getWebDriver());
  }
}
