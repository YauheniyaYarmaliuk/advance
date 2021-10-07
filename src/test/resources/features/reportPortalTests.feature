Feature: reportPortalTests

Background:
 Given navigate to RP

 Scenario Outline: Check Success logIn form
    When fill login <user>
    And fill password <password>
    And click Submit button
    Then verify message
    |Signed in successfully|
    When logout

    Examples:
    | user | password |
    | superadmin | erebus |
    | default | 1q2w3e |

Scenario: Check Wrong logIn form
    When fill wrong login
       | Fox | Pirat| Jake|
       | Wrong |  Sabrina |Karl|
    And fill password wrongUser
    And click Submit button
    Then verify message
    |An error occurred while connecting to server : You do not have enough permissions. Bad credentials|

    Scenario Outline: Create Dashboard
    When fill login default
    And fill password 1q2w3e
    And click Submit button
    Then verify message
    |Signed in successfully|
    And create new Dashboard <name>
    Then verify message
    |Dashboard has been added|
    When delete Dashboard <name>
    Then verify message
    |Dashboard has been deleted!|

   Examples:
   |name|
   |new|