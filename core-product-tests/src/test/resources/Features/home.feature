Feature: checking

  @test2
  Scenario: Storing Mens Jacket price, Title and Top Seller text
    Given user is in home page
    When user clicks on Mens in shop menu
    Then user should be redirected to shop window
    When user select jacket radio button
    Then user should store those data in file
    And attach to the report

    @test
    Scenario: Retrieving the count of videos present in new & featured and counting the videos which are >=3
      Given user is in home page
      When user selects News & Feature from menu item
      Then user should be redirected to News & Feature page
      And user should count the number of videos present in page
      And also count number of videos that are greater than <3> days

