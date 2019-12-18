Feature: Widgets functionalities

  @selectmenu @regression @TEST-Widgets-01
  Scenario: verify selectmenu options
    Given User navigate to "url"
    And Verify "image" should be visible on the browser
    When User clicks on "selectMenu"
    Then Verify "selectMenuHeading" should be visible on the browser
    And Verify "selectASpeed" should be visible on the browser
    Then User clicks on "speedButton"
    And User wait for 3 sec
    And Verify "slow" should be visible on the browser
    Then User clicks on "slow"
