Feature: Booking website search functionality

  @smoke
  Scenario Outline: Search for hotels in a city
    Given I open the Booking.com homepage
    When I accept cookies
    When I search for "<city>"
    Then I see search results related to "<city>"

    Examples:
    | city          |
    | Paris         |
    | Rome          |
    | London        |
    | San Francisco |