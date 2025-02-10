package org.automation.pages;
import static org.automation.utils.Utils.waitToRender;
import static org.automation.utils.Utils.waitForElementNotStale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingHomePage {

  private WebDriver driver;
  private final String url = "https://www.booking.com";

  public BookingHomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void open() {
    driver.get(url);
  }

  public void acceptCookies() {
    driver.findElement(By.id("onetrust-accept-btn-handler")).click();
  }

  public void searchForCity(String city) {
    WebElement searchBox = driver.findElement(By.cssSelector("[data-testid = 'destination-container']"));
    searchBox.click();
    searchBox.findElement(By.tagName("input")).clear();
    searchBox.findElement(By.tagName("input")).sendKeys(city);
    waitToRender(driver);
    List<WebElement> searchResultElements = driver.findElements(By.cssSelector("[data-testid='autocomplete-results-options'] ul li"));
    waitForElementNotStale(driver, d -> searchResultElements.get(0), 5);
    assertEquals(5, searchResultElements.size());
    searchResultElements.get(0).click();
    driver.findElement(By.cssSelector("[type = submit]")).click();
  }

  public void cityIs(String city) {
    assertTrue(driver.findElement(By.cssSelector("[aria-live = assertive]")).getText().startsWith(city));
  }
}