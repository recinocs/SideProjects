Feature: User runs search with a player and a memorabilia type
  Scenario: User runs an advanced search with a specified player and a chosen mem type
    Given User is on the AverageCollector landing page
    When User navigates to the AverageCollector home page
    And User enters "Chipper" as their specified player
    And User selects "Relic" as their specified memorabilia type
    When User clicks on the 'Run Search' button
    Then Total results displayed should equal "2"