package org.automation.utils;

import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

  public static WebElement waitForElementNotStale(WebDriver driver, Function<WebDriver, WebElement> elementLocator, int timeoutSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

    return wait.until(driver1 -> {
      try {
        return elementLocator.apply(driver1); // Just try to locate it again
      } catch (StaleElementReferenceException e) {
        return null; // Retry if stale
      }
    });
  }

  public static void waitToRender(WebDriver driver) {
    waitAbit(1);
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> {
      Boolean isJQueryIdle = (Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0");
      Boolean isNoAnimation = (Boolean) ((JavascriptExecutor) webDriver).executeScript("return $(\":animated\").length == 0");
      String readyState = (String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
      return isJQueryIdle && isNoAnimation && "complete".equals(readyState);
    });
  }

  private static void waitAbit(int noOfSec) {
    try {
      Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(noOfSec));
    } catch (InterruptedException ignored) {
    }
  }

}
