package util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
/**
 * Common Commands that are used across all the test cases are curated here.
 */
public final class CommandUtil {

  /**
   * used to bypass stale element and dom refresh exceptions.
   * @param driver - WebDriver
   * @param element - element to be clicked
   *
   */
  public static void clickOnElement(WebDriver driver, WebElement element){
    try {
      element.click();
    } catch (Exception e) {
      log.info("retrying the click and the failure was due to {}", e.getMessage());
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", element);
    }
  }

  public static boolean isElementPresent(WebElement ele) {
    try {
      return ele.isDisplayed();
    } catch (NoSuchElementException e) {
      log.error("Element not found on the page. Exception seen - " + e.getMessage());
      return false;
    }
  }





}
