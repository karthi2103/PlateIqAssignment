package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Service;
import util.CommandUtil;
import util.CommonUtil;
import util.WaitUtil;

import java.util.List;

@Service
public class MmtDashboard {

  @FindBy(xpath = "//a[contains(@href,\"/flights\")]")
  private WebElement flights;
  @FindBy(xpath = "//li[@data-cy='roundTrip']")
  private WebElement roundTrip;
  @FindBy(id = "fromCity")
  private WebElement source;
  @FindBy(id = "toCity")
  private WebElement destination;
  @FindBy(xpath = "//div[@class=\"calc60\"]//p")
  private List<WebElement> searchResult;

  @FindBy(xpath = "//label[@for='departure']")
  private WebElement departureBox;
  @FindBy(css = "div.DayPicker-Day")
  private List<WebElement> dayPicker;
  @FindBy(xpath = "//label[@for='travellers']")
  private WebElement travellerInfoBox;
  @FindBy(xpath = "//li[@data-cy='adults-1']")
  private WebElement adultCounter;
  @FindBy(xpath = "//li[@data-cy='children-0']")
  private WebElement childrenCounter;
  @FindBy(xpath = "//li[@data-cy='infants-0']")
  private WebElement infantsCounter;
  @FindBy(xpath = "//li[@data-cy='travelClass-0']")
  private WebElement travelClass;
  @FindBy(xpath = "//button[@data-cy='travellerApplyBtn']")
  private WebElement applyTravelInfo;
  @FindBy(css = "a.primaryBtn.font24.latoBlack.widgetSearchBtn")
  private WebElement search;
  @FindBy(xpath = "//li[@data-cy='account']")
  private WebElement signIn;
  @FindBy(xpath = "//li[@data-cy='userMenuLogout']")
  private WebElement logout;


  private WebDriver driver;

  public MmtDashboard(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    driver.get("https://www.makemytrip.com/");
  }

  // validations
  public boolean validateFlightsSection(){
    return flights != null && WaitUtil.waitForTheElementToBeSeenOnPage(driver,flights,3)
            && flights.isEnabled();
  }

  public boolean validateRoundTripRadio(){
    return roundTrip != null && WaitUtil.waitForTheElementToBeSeenOnPage(driver, roundTrip, 3);
  }

  public boolean validateRoundTripSelection(){
    return roundTrip.getAttribute("class").contains("selected");
  }

  public boolean validateSourcePicker(){
    return source != null
            && WaitUtil.waitForTheElementToBeSeenOnPage(driver,source,3) && source.isEnabled();
  }


  public boolean validateDestinationPicker(){
    return destination != null && WaitUtil.waitForTheElementToBeSeenOnPage(driver,destination,3);
  }

  public boolean validateSearchResult(String sourceLocation) {
    if (searchResult.isEmpty()) {
      return false;
    }
    WaitUtil.waitForElementToBeClickable(driver,searchResult.get(0),3);
    WebElement matchingElementFromList = getMatchingElementFromList(searchResult, sourceLocation);
    WaitUtil.waitForElementToBeClickable(driver,matchingElementFromList,3);
    CommandUtil.clickOnElement(driver,matchingElementFromList);
    return true;
  }

  private WebElement getMatchingElementFromList(List<WebElement> elements, String value) {
    return elements.stream()
            .filter(element -> element.getText().equals(value))
            .findFirst().orElse(null);
  }

  public boolean validateDeprtureDatePicker(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,departureBox,3)&&
            departureBox != null &&departureBox.isEnabled();
  }

  private boolean validateDayPicker(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,dayPicker.get(7),3) &&
            dayPicker != null && dayPicker.get(7).isEnabled();
  }

  public boolean getMatchingDateForDeparture(int days){
    String date = CommonUtil.resolveOffsetDate(days);
    if(validateDayPicker()){
      WebElement matchingElementFromList = getMatchingElementFromAttribute(dayPicker,"aria-label", date);
      if(null != matchingElementFromList){
        CommandUtil.clickOnElement(driver,matchingElementFromList);
        return true;
      }
    }
    return false;
  }

  private WebElement getMatchingElementFromAttribute(List<WebElement> elements, String attribute, String value){
    return elements.stream()
            .filter(element -> element.getAttribute(attribute).contains(value))
            .findFirst().orElse(null);
  }

  public boolean validateTravellerInfoBox(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,travellerInfoBox,2) &&
            travellerInfoBox  != null && travellerInfoBox.isEnabled();
  }

  public boolean validateAdultCounter(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,adultCounter,2) &&
            adultCounter  != null && adultCounter.isEnabled();
  }

  public boolean validateChildrenCounter(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,childrenCounter,2) &&
            childrenCounter  != null && childrenCounter.isEnabled();
  }

  public boolean validateTravelClass(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,travelClass,2) &&
            travelClass  != null && travelClass.isEnabled();
  }

  public boolean validateApplyTravelInfo(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,applyTravelInfo,2) &&
            applyTravelInfo  != null && applyTravelInfo.isEnabled();
  }

  public boolean validateSearch(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,search,2) &&
            search  != null && search.isEnabled();
  }

  public boolean validateSigninButton(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,signIn,2) &&
            signIn  != null && signIn.isEnabled();
  }

  public boolean validateLogout(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver,logout,2) &&
            logout  != null && logout.isEnabled();
  }

  //operations
  public void clickOnFlights(){
    CommandUtil.clickOnElement(driver,flights);
  }

  public void clickOnRoundTrip(){
    CommandUtil.clickOnElement(driver,roundTrip);
  }

  public void typeInSourceLocation(String value){
    WaitUtil.waitForElementToBeClickable(driver,source,3);
    source.sendKeys(value);
  }

  public void typeInDestinationLocation(String value){
    WaitUtil.waitForElementToBeClickable(driver,destination,3);
    destination.sendKeys(value);
  }

  public void clickOnDepartureBox(){
    CommandUtil.clickOnElement(driver,departureBox);
  }

  public void clickOnTravellerInfoBox(){
    CommandUtil.clickOnElement(driver,travellerInfoBox);
  }

  public void clickOnAdultCounter(){
    CommandUtil.clickOnElement(driver,adultCounter);
  }

  public void clickOnChildrenCounter(){
    CommandUtil.clickOnElement(driver,childrenCounter);
  }

  public void clickOnTravelClass(){
    CommandUtil.clickOnElement(driver,travelClass);
  }

  public void clickOnApplyTravelInfo(){
    CommandUtil.clickOnElement(driver,applyTravelInfo);
  }

  public void clickOnSearchFlights(){
    CommandUtil.clickOnElement(driver,search);
  }

  public void clickOnSignInButton(){
    CommandUtil.clickOnElement(driver,signIn);
  }

  public void clickOnLogout(){
    CommandUtil.clickOnElement(driver,logout);
  }



}
