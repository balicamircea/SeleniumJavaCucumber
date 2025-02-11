Feature: Booking website search functionality

  @smoke
  Scenario Outline: Search for hotels in Europe
    Given I open the Booking.com homepage
    When I accept cookies
    When I search for "<city>"
    Then I see search results related to "<city>"

    Examples:
    | city          |
    | Paris         |
    | Rome          |
    | London        |
    | Should fail   |

  @smoke
  Scenario Outline: Search2 for hotels in America
    Given I open the Booking.com homepage
    When I accept cookies
    When I search for "<city>"
    Then I see search results related to "<city>"
    Examples:
      | city          |
      | San Francisco |
      | Los Angeles   |
      | New York      |
      | Should fail   |