package com.pages.dashboard;

import com.AbstractWebTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.MmtDashboard;
import pom.MmtFlightSearchResult;
import util.WaitUtil;

import java.util.concurrent.TimeUnit;

public class FlightSearchTest extends AbstractWebTest {

  private MmtDashboard mmtDashboard;
  private MmtFlightSearchResult mmtFlightSearchResult;

  @BeforeClass
  public void pageSetup(){
    driver = driverConfig.configureWebDriver().orElse(null);
    if(driver == null){
      throw new IllegalArgumentException("Could not initialize WebDriver while testing Dashboard test cases");
    }
    mmtDashboard = new MmtDashboard(driver);

  }

  @Test(description = "click on flights section", priority = 1)
  public void testClickOnFlightSection(){
    Assert.assertTrue(mmtDashboard.validateFlightsSection(), "Could not validate flights section");
    mmtDashboard.clickOnFlights();
    Assert.assertTrue(driver.getCurrentUrl().contains("/flights"));
  }

  @Test(description = "validate and fill source and destination", priority = 2)
  public void testSourceAndDestinationForm(){
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    Assert.assertTrue(mmtDashboard.validateSourcePicker(), "could not validate source picker");
    mmtDashboard.typeInSourceLocation("Goa");
    Assert.assertTrue(mmtDashboard.validateSearchResult("Goa, India"));
    Assert.assertTrue(mmtDashboard.validateDestinationPicker(),"could not validate destination picker");
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    mmtDashboard.typeInDestinationLocation("Mumbai");
    Assert.assertTrue(mmtDashboard.validateSearchResult("Mumbai, India"));
  }

  @Test(description = "select round trip option", priority = 3)
  public void testRoundTrip(){
    Assert.assertTrue(mmtDashboard.validateRoundTripRadio());
    mmtDashboard.clickOnRoundTrip();
    Assert.assertTrue(mmtDashboard.validateRoundTripSelection());
  }

  @Test(description = "fill in date pickers", priority = 4)
  public void testDatePickers(){
    Assert.assertTrue(mmtDashboard.validateDeprtureDatePicker());
    mmtDashboard.clickOnDepartureBox();
    Assert.assertTrue(mmtDashboard.getMatchingDateForDeparture(2));
    Assert.assertTrue(mmtDashboard.getMatchingDateForDeparture(3));
  }

  @Test(description = "fill travel information", priority = 5)
  public void testTravelInfoForm(){
    Assert.assertTrue(mmtDashboard.validateTravellerInfoBox());
    mmtDashboard.clickOnTravellerInfoBox();
    Assert.assertTrue(mmtDashboard.validateAdultCounter());
    mmtDashboard.clickOnAdultCounter();
    Assert.assertTrue(mmtDashboard.validateChildrenCounter());
    mmtDashboard.clickOnChildrenCounter();
    Assert.assertTrue(mmtDashboard.validateTravelClass());
    mmtDashboard.clickOnTravelClass();
    Assert.assertTrue(mmtDashboard.validateApplyTravelInfo());
    mmtDashboard.clickOnApplyTravelInfo();
  }

  @Test(description = "verify flight search submission", priority = 6)
  public void testFlightSearchSubmission(){
    Assert.assertTrue(mmtDashboard.validateSearch());
    mmtDashboard.clickOnSearchFlights();
    WaitUtil.waitForPageLoad(driver);
    Assert.assertTrue(driver.getCurrentUrl().contains("flight/search?"));
  }

  @Test(description = "pick flights for departure and return", priority = 7)
  public void testDepartureAndReturnFlights() throws InterruptedException {
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    mmtFlightSearchResult = new MmtFlightSearchResult(driver);
    Assert.assertTrue(mmtFlightSearchResult.validateDepartureColumn());
    Assert.assertTrue(mmtFlightSearchResult.validateReturnColumn());
    mmtFlightSearchResult.clickOnSecondFlightDeparting();
    mmtFlightSearchResult.clickOnSecondReturnFlight();
    Assert.assertTrue(mmtFlightSearchResult.validateBookNow());
    mmtFlightSearchResult.clickOnBookNow();
  }

  @AfterClass
  public void tearDown(){
    driver.quit();
  }
}
