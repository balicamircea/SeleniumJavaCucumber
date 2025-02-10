Feature: Booking website search functionality

  @smoke
  Scenario: Search for hotels in a city
    Given I open the Booking.com homepage
    When I accept cookies
    When I search for "Paris"
    Then I see search results related to "Paris"