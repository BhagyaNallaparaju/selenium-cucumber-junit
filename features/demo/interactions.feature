Feature: Interactions functionalities

  @selectable @regression @TEST-01
  Scenario: verify selectable options
    Given User navigate to "url"
    And Verify "image" should be visible on the browser
    When User clicks on "selectable"
    Then Verify "selectableHeading" should be visible on the browser
    And User clicks on "Item2"
    And User wait for 3 sec
    Then Verify attribute "ui-selected" for "Item2"

  @resizable @regression @TEST-02
  Scenario: verify resizable options
    Given User navigate to "url"
    And Verify "image" should be visible on the browser
    When User clicks on "resizable"
    Then Verify "resizableHeading" should be visible on the browser
    And Verify resizable options
    And User wait for 3 sec

  @droppable @regression @TEST-03
  Scenario: verify resizable options
    Given User navigate to "url"
    And Verify "image" should be visible on the browser
    When User clicks on "droppable"
    Then Verify "droppableHeading" should be visible on the browser
    And Verify droppable options
    Then User wait for 3 sec
    And Verify "Dropped!" message from "droppedtext"