package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Service;
import util.CommandUtil;
import util.WaitUtil;

import java.util.concurrent.TimeUnit;

@Service
public class MmtSignInModal {

  @FindBy(id= "username")
  private WebElement emailBox;
  @FindBy(xpath="//button[@data-cy='continueBtn']")
  private WebElement continueButton;
  @FindBy(id="password")
  private WebElement password;
  @FindBy(xpath = "//button[@data-cy='login']")
  private WebElement login;
  @FindBy(xpath = "//button[@data-cy='modalClose']")
  private WebElement close;
  @FindBy(xpath = "//p[@data-cy='loggedInUser']")
  private WebElement loggedInUser;
  @FindBy(xpath = "//span[@data-cy='userNotPresent']")
  private WebElement userNotFoundError;
  @FindBy(xpath = "//span[@data-cy='serverError']")
  private WebElement passwordNotFoundErr;
  @FindBy(xpath = "//p[@data-cy='error']")
  private WebElement paswordLengthError;

  private WebDriver driver;

  public MmtSignInModal(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver,this);
  }

  public boolean validateEmailBox(){
    WaitUtil.waitForElementToBeClickable(driver,emailBox,2);
    return emailBox != null && emailBox.isEnabled();
  }

  public boolean validateContinueButton(){
    WaitUtil.waitForElementToBeClickable(driver,continueButton,2);
    return continueButton != null && continueButton.isEnabled();
  }

  public boolean validatePassword(){
    WaitUtil.waitForElementToBeClickable(driver,password,2);
    return password != null && password.isEnabled();
  }

  public boolean validateLogin(){
    WaitUtil.waitForElementToBeClickable(driver,login,2);
    return login != null && login.isEnabled();
  }

  public boolean validateUserNotFoundError(String error){
    WaitUtil.waitForElementToBeClickable(driver, userNotFoundError,2);
    return userNotFoundError != null && userNotFoundError.isEnabled() && userNotFoundError.getText().equals(error);
  }

  public boolean validatePasswordNotFoundError(String error){
    WaitUtil.waitForElementToBeClickable(driver, passwordNotFoundErr,2);
    return passwordNotFoundErr != null && passwordNotFoundErr.isEnabled() && passwordNotFoundErr.getText().equals(error);
  }

  public boolean validatePasswordLengthError(String error){
    WaitUtil.waitForElementToBeClickable(driver, paswordLengthError,2);
    return paswordLengthError != null && paswordLengthError.isEnabled() && paswordLengthError.getText().equals(error);
  }

  public boolean validateClose(){
    WaitUtil.waitForElementToBeClickable(driver,close,2);
    return close != null && close.isEnabled();
  }

  public boolean validateLoggedInCard(){
    WaitUtil.waitForElementToBeClickable(driver,loggedInUser,2);
    return loggedInUser != null && loggedInUser.isEnabled();
  }

  public void typeInUserName(String value){
    WaitUtil.waitForElementToBeClickable(driver,emailBox,3);
    emailBox.sendKeys(value);
  }

  public void clickOnContinue(){
    driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
    continueButton = driver.findElement(By.xpath("//button[@data-cy='continueBtn']"));
    WaitUtil.waitForElementToBeClickable(driver,continueButton,3);
    CommandUtil.clickOnElement(driver,continueButton);
  }

  public void typeInPassword(String value){
    WaitUtil.waitForElementToBeClickable(driver,password,3);
    password.sendKeys(value);
  }

  public void clickOnLogin(){
    WaitUtil.waitImplicityly(driver,2);
    WaitUtil.waitForElementToBeClickable(driver,login,3);
    password.sendKeys(Keys.ENTER);
  }

  public void clickOnClose(){
    WaitUtil.waitForElementToBeClickable(driver,close,3);
    CommandUtil.clickOnElement(driver,close);
  }

  public boolean verifyLogin(String value){
    return loggedInUser.getText().equals(value);
  }

}
