Feature: Todo task management
  Scenario: Show list of todo task after add then refresh page
    Given The todo page is showing
    When The user attempt to add items as below
      | Task     |
      | Meeting  |
      | Eating   |
      | Watching |
    And The user try to refresh webpage
    Then The list of tasks is display as below order
      | Task     |
      | Meeting  |
      | Eating   |
      | Watching |

  Scenario: Show list of todo task after remove any item
    Given The todo page is showing
    And The below tasks are added to list
      | Task     |
      | Meeting  |
      | Eating   |
      | Watching |
    When The user attempt to remove "Eating" item from list
    And The user try to refresh webpage
    Then The list of tasks is display as below order
      | Task     |
      | Meeting  |
      | Watching |

  Scenario: Show list of todo task after make any task complete
    Given The todo page is showing
    And The below tasks are added to list
      | Task     |
      | Meeting  |
      | Eating   |
      | Watching |
    When The user attempt to make "Eating" task is completed
    Then The list of active tasks is display as below order
      | Active   |
      | Meeting  |
      | Watching |
    And The list of completed is display as below order
      | Completed |
      | Eating    |

  Scenario: Show new list of todo task after update a item
    Given The todo page is showing
    And The below tasks are added to list
      | Task     |
      | Meeting  |
      | Eating   |
      | Watching |
    When The user attempt to change "Eating" to "Swimming"
    And The user try to refresh webpage
    Then The list of tasks is display as below order
      | Task     |
      | Meeting  |
      | Swimming |
      | Watching |
