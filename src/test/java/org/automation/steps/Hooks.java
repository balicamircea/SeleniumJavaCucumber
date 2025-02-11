package org.automation.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import org.automation.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

  private static WebDriver driver;

  @Before
  public void setUp() {
    System.out.println("Initializing WebDriver...");
    Path tempDir;
    try {
      tempDir = Files.createTempDirectory("chrome-user-data");
    } catch (Exception e) {
      throw new RuntimeException("Failed to create temp directory", e);
    }
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--user-data-dir=" + tempDir.toAbsolutePath());
    driver = new ChromeDriver(options);
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
