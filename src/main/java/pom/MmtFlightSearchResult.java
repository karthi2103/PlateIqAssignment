package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Service;
import util.CommandUtil;
import util.WaitUtil;

import java.util.List;

@Service
public class MmtFlightSearchResult {
  @FindBy(xpath = "//div[@class='splitVw-sctn pull-left']//div//div[@class='fli-list splitVw-listing ']//label//div//span[@class='splitVw-inner']")
  private List<WebElement> departureFlight;
  @FindBy(xpath = "//div[@class='splitVw-sctn pull-right']//div//div[@class='fli-list splitVw-listing ']//label//div//span[@class='splitVw-inner']")
  private List<WebElement> returnFlight;
  @FindBy(id = "bookbutton-RKEY:2ee22b9a-3778-49b1-8d85-71452609317d:0~~~RKEY:2ee22b9a-3778-49b1-8d85-71452609317d:35")
  private WebElement book;

  private WebDriver driver;

  public MmtFlightSearchResult(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public boolean validateDepartureColumn(){
    return !departureFlight.isEmpty() && WaitUtil.waitForTheElementToBeSeenOnPage(driver, departureFlight.get(0),10)
            && departureFlight.get(0).isEnabled();
  }

  public boolean validateReturnColumn(){
    return !returnFlight.isEmpty() && WaitUtil.waitForTheElementToBeSeenOnPage(driver, returnFlight.get(0),3)
            && returnFlight.get(0).isEnabled();
  }

  public boolean validateBookNow(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,book,3)
            && book != null && book.isEnabled();
  }

  public void clickOnSecondFlightDeparting(){
     CommandUtil.clickOnElement(driver, departureFlight.get(0));
  }

  public void clickOnSecondReturnFlight(){
    CommandUtil.clickOnElement(driver, returnFlight.get(0));
  }

  public void clickOnBookNow(){
    CommandUtil.clickOnElement(driver,book);
  }

}
