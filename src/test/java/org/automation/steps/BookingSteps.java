package org.automation.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.automation.pages.BookingHomePage;

public class BookingSteps {
  private WebDriver driver = Hooks.getDriver();
  private BookingHomePage bookingHomePage = new BookingHomePage(driver);

  @Given("I open the Booking.com homepage")
  public void i_open_the_booking_homepage() {
    bookingHomePage = new BookingHomePage(driver);
    bookingHomePage.open();
  }

  @When("I accept cookies")
  public void iAcceptCookies() {
    bookingHomePage.acceptCookies();
  }

  @When("I search for {string}")
  public void iSearchFor(String city) {
    bookingHomePage.searchForCity(city);
  }

  @Then("I see search results related to {string}")
  public void iSeeSearchResultsRelatedTo(String city) {
    bookingHomePage.cityIs(city);
  }
}
