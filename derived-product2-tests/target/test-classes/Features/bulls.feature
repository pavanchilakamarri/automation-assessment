Feature: checking

  @test
  Scenario: Storing footer links and reporting duplicate links
    Given user is in home page
    When user retrieves all the links from footer and add to csv file
    Then add the href of those links to csv file and add to report
    And report the duplicate links

