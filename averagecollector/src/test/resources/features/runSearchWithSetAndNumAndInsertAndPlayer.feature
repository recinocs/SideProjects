Feature: Running a Search With A Chosen Card Set, Card Num, Insert Type, and Player
  Scenario: User runs an advanced search with a specified card set, card num, insert type, and player
    Given User is on the AverageCollector landing page
    When User navigates to the AverageCollector home page
    And User selects "2017 Topps Allen & Ginter" as their specified set
    And User enters "GS" as their specified card number
    And User enters "Full-Size Relic" as their specified insert
    And User enters "Giancarlo Stanton" as their specified player
    And User clicks on the 'Run Search' button
    Then Total results displayed should equal "1"