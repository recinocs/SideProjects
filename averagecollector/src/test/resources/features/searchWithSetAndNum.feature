Feature: Running a Search With A Chosen Card Set and Card Num
  Scenario: User runs an advanced search with a specified card set and card num
    Given User is on the AverageCollector landing page
    When User navigates to the AverageCollector home page
    And User selects "2007 TriStar Elegance" as their specified set
    And User enters "C" as their specified card number
    And User clicks on the 'Run Search' button
    Then Total results displayed should equal "6"