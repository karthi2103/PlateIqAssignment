package util;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class WaitUtil {

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

  public static boolean waitForTheElementToBeSeenOnPage(WebDriver driver, WebElement element, int waitTimeInSec){

    Wait<? extends WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(waitTimeInSec, TimeUnit.SECONDS)
            .pollingEvery(POLLING_TIME, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class);
    try{
      wait.until(new Function<WebDriver, WebElement>() {
        @Nullable
        public WebElement apply(@Nullable WebDriver driver) {
          assert driver != null;
          return element;
        }
      });
    }
    catch (TimeoutException t){
      log.error(String.format("Element not seen on page after waiting for %d seconds", waitTimeInSec));
      return false;
    }
    return true;
  }

  public static void waitForPageLoad(WebDriver driver){
    new WebDriverWait(driver, 20).until(
            (Function<? super WebDriver, Boolean>) webDriver -> "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
    log.info("Page Loaded successfully!");
  }

  public static void waitWithRetries(WebDriver driver, WebElement element, int timeout){
    new WebDriverWait(driver, timeout)
            .ignoring(StaleElementReferenceException.class)
            .until((Predicate<WebDriver>) driver1 -> element.isDisplayed() && element.isEnabled());
  }

  public static  void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout){
    new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
  }

  public static void waitForVisibility(WebDriver driver, WebElement element, int timeout){
    new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitForStaleElementsToBeLoaded(WebDriver driver, By by){
    new WebDriverWait(driver,10)
            .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public static void waitImplicityly(WebDriver driver, int timeout){
    driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
  }

}
