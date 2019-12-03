package util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
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
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", element);
    }
  }




}
