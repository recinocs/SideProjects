Feature: Running a Search With A Chosen Card Set and Player
  Scenario: User runs an advanced with the specified card set and player
    Given User is on the AverageCollector landing page
    And User navigates to the AverageCollector home page
    When User enters "Jake Peavy" as their specified player
    And User selects "2007 Upper Deck Artifacts" as their specified set
    And User clicks on the 'Run Search' button
    Then Total results displayed should equal "2"