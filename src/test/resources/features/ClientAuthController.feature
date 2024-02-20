Feature: Customer Endpoint Tests
  This feature represents the customer management system, including creating and deleting customers.

  Scenario: Successfully creating a customer
    Given Valid data
    When The registration request is sent
    Then The customer is created and the status 201 is received

  Scenario: Successfully getting all clients
    Given There are clients registered
    When I request a list of all clients
    Then I receive a successful response with a list of clients
    And The list is paginated according to the default settings

  Scenario: Successfully getting a client by ID
    Given There is a registered client with a known ID
    When I request the client details by this ID
    Then I receive a successful response with the client's details

  Scenario: Successfully deleting a customer
    Given a valid id
    When a request to delete the customer is sent
    Then a 204 no content status is received