package util;

import com.google.common.base.Function;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WaitUtil {

  private static final int POLLING_TIME = 100;

  public static boolean waitForTheElementToBeSeenOnPage(WebDriver driver, final By by, int waitTimeInSec){

    Wait<? extends WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(waitTimeInSec, TimeUnit.SECONDS)
            .pollingEvery(POLLING_TIME, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class);
    try{
      wait.until(new Function<WebDriver, WebElement>() {
        @Nullable
        public WebElement apply(@Nullable WebDriver driver) {
          assert driver != null;
          return driver.findElement(by);
        }
      });
    }
    catch (TimeoutException t){
      log.error(String.format("Element %s not seen on page after waiting for %d seconds", by, waitTimeInSec));
      return false;
    }
    return true;
  }

  public static void waitForPageLoad(WebDriver driver){
    new WebDriverWait(driver, 10).until(
            (Function<? super WebDriver, Boolean>) webDriver -> "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
  }
}
