package org.automation.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.time.Duration;
import org.automation.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

  private static WebDriver driver;

  @Before
  public void setUp() {
    System.out.println("Initializing WebDriver...");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
  }

  @After
  public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {  // Capture screenshot only if test fails
      byte [] screenshot = Utils.takeScreenshot(driver);
      scenario.attach(screenshot, "image/png", scenario.getName());
    }
    if (driver != null) {
      driver.quit();
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }

}
