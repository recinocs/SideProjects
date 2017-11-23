Feature: Running a Basic Search With No Parameters
  1) Running a basic search with no parameters should return all cards in the database

  Scenario: User runs a basic search
    Given User is on the AverageCollector landing page
    When User navigates to the AverageCollector home page
    And User clicks on the 'Run Search' button
    Then Total results displayed should equal "209"