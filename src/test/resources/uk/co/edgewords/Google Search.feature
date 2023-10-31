@RunThis
Feature: Google Search


  Scenario: Edgewords should be the top result for a search of Edgewords
    Given I am on the Google Home page
    When I search for "Edgewords"
    Then 'Edgewords' is the top result

    @Ignore
  Scenario Outline: Edgewords should be the top result for related search tems
    Given I am on the Google Home page
    When I search for "<searchTerm>"
    Then "<result>" is the top result
    Examples:
      | searchTerm        | result    |
      | Edgewords         | Edgewords |
      | Edgewords Testing | Edgewords |
      | BBC               | BBC       |


  Scenario: Edgewords should be on the first page of results for given search terms
    Given I am on the Google Home page
    When I search for "Edgewords"
    Then I see in the results
      | Title              | url                                        |
      | Edgewords Training | https://www.edgewordstraining.co.uk        |
      | YouTube            | https://www.youtube.com › user › Edgewords |


