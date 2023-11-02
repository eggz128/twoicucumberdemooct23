@API
Feature: API Tests using Cucumber

  Scenario Outline: Products are what they should be
    When I get product <prodId>
    Then it is an "<prodName>"
    Examples:
      | prodId | prodName |
      | 1      | iPad     |
      | 2      | iPhone X |