package com;

import config.Application;
import config.PropertiesInitializer;
import config.driverConfig.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = Application.class, initializers = PropertiesInitializer.class)

public class AbstractWebTest extends AbstractTestNGSpringContextTests {
  @Autowired
  protected DriverConfig driverConfig;
  protected WebDriver driver;

}
