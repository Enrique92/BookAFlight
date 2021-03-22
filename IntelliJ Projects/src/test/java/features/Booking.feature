Feature: Automated Booking
  Existing an Ryanair user should be able to login into account and book a flight

  Scenario: Booking up to a declined payment on Ryanair web
    Given Go to the Ryanair website, go and book a flight
    And I make a booking selecting the origin and destination
      | Origin | Destination | Email                       | Password       |
      | Dublin | Madrid      | enriquetestingweb@gmail.com | TestingWeb2021 |
    When I pay for booking with card details
      | FirstName | LastName | Credit Card      | CVC |
      | Testing   | Tester   | 5555555555555555 | 265 |
    Then I should get payment declined message