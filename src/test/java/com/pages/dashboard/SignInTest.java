package com.pages.dashboard;

import com.AbstractWebTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.MmtDashboard;
import pom.MmtSignInModal;

public class SignInTest extends AbstractWebTest {
  private MmtDashboard mmtDashboard;
  private MmtSignInModal mmtSignInModal;

  @BeforeClass
  public void pageSetUp(){
    driver = driverConfig.configureWebDriver().orElse(null);
    if(driver == null){
      throw new IllegalArgumentException("Could not initialize WebDriver while testing Dashboard test cases");
    }
    mmtDashboard = new MmtDashboard(driver);
  }

  @Test(priority = 1, description = "test successful login")
  public void testSuccessfulLogin(){
    testSignInModalClick();
    passUserScreen("test@plateiq.com");
    passPasswordScreen("test@123");
    Assert.assertTrue(mmtSignInModal.validateClose());
    mmtSignInModal.clickOnClose();
    logoutFromAccount();
  }

  @Test(priority = 2, description = "test for failure when user email is in invalid format")
  public void testFailureForEmailFormat(){
    testSignInModalClick();
    failedUserNameFormat("abc");
  }

  @Test(priority = 3, description = "test for failure when user is not in the database")
  public void testFailureWhenUserIsNotInDb(){
    testSignInModalClick();
    failedUserScreen("test@test.com");
  }

  @Test(priority = 4, description = "test failure due to incorrect password")
  public void testFailureDueToIncorrectpassword(){
    testSignInModalClick();
    passUserScreen("test@plateiq.com");
    testIncorrectPassword("test@1234");
  }
  @Test(priority = 5, description = "test failure due to wrong length of password")
  public void testFailureDueToPasswordLength(){
    testSignInModalClick();
    passUserScreen("test@plateiq.com");
    testPasswordLength("123");
  }

  private void logoutFromAccount(){
    Assert.assertTrue(mmtDashboard.validateSigninButton());
    mmtDashboard.clickOnSignInButton();
    Assert.assertTrue(mmtDashboard.validateLogout());
    mmtDashboard.clickOnLogout();

  }

  private void testSignInModalClick(){
    Assert.assertTrue(mmtDashboard.validateSigninButton());
    mmtDashboard.clickOnSignInButton();
    mmtSignInModal = new MmtSignInModal(driver);
  }
  
   private void passUserScreen(String userName){
     Assert.assertTrue(mmtSignInModal.validateEmailBox());
     mmtSignInModal.typeInUserName(userName);
     Assert.assertTrue(mmtSignInModal.validateContinueButton());
     mmtSignInModal.clickOnContinue();
     Assert.assertFalse(mmtSignInModal.validateUserNotFoundError("This username does not exist. CLICK HERE If your are trying to create a new personal account"));
   }

   private void failedUserScreen(String userName){
     Assert.assertTrue(mmtSignInModal.validateEmailBox());
     mmtSignInModal.typeInUserName(userName);
     Assert.assertFalse(mmtSignInModal.validateContinueButton());
   }

   private void failedUserNameFormat(String userName){
     Assert.assertTrue(mmtSignInModal.validateEmailBox());
     mmtSignInModal.typeInUserName(userName);
     Assert.assertTrue(mmtSignInModal.validateContinueButton());
     mmtSignInModal.clickOnContinue();
     Assert.assertTrue(mmtSignInModal.validateUserNotFoundError("This username does not exist. CLICK HERE If your are trying to create a new personal account"));
   }

   private void passPasswordScreen(String password){
    Assert.assertTrue(mmtSignInModal.validatePassword());
    mmtSignInModal.typeInPassword(password);
    Assert.assertFalse(mmtSignInModal.validatePasswordLengthError("Password cannot be less than 6 characters."));
    Assert.assertTrue(mmtSignInModal.validateLogin());
    mmtSignInModal.clickOnLogin();
    Assert.assertFalse(mmtSignInModal.validatePasswordNotFoundError("Either Username or Password is incorrect."));
   }

   private void testIncorrectPassword(String password){
     Assert.assertTrue(mmtSignInModal.validatePassword());
     mmtSignInModal.typeInPassword(password);
     Assert.assertTrue(mmtSignInModal.validateLogin());
     mmtSignInModal.clickOnLogin();
     Assert.assertTrue(mmtSignInModal.validatePasswordNotFoundError("Either Username or Password is incorrect."));
   }

   private void testPasswordLength(String password){
     Assert.assertTrue(mmtSignInModal.validatePassword());
     mmtSignInModal.typeInPassword(password);
     Assert.assertTrue(mmtSignInModal.validatePasswordLengthError("Password cannot be less than 6 characters."));
     Assert.assertFalse(mmtSignInModal.validateLogin());
   }

}
