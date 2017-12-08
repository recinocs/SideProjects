Feature: Running a Search With A Chosen Card Set, Card Num, and Insert Type
  Scenario: User runs a search with chosen card set, card num, and insert type
    Given User is on the AverageCollector landing page
    When User navigates to the AverageCollector home page
    And User selects "2006 Upper Deck Base Set" as their specified set
    And User enters "BR" as their specified card number
    And User enters "UD" as their specified insert
    And User clicks on the 'Run Search' button
    Then Total results displayed should equal "1"

  Scenario: User runs a search with a valid set but invalid num and insert type
  Should return all cards associated with set if no results are found for num and insert type

    Given User enters "OF" as their specified card number
    And User enters "OR" as their specified insert
    When User clicks on the 'Run Search' button
    Then Total results displayed should equal "5"