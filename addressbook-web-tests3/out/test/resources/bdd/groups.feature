Feature: Groups
Scenario Outline: Group Creation
  Given a set of groups
  When I create a new group with name <name>, header <header> and footer <footer>
  Then The new set of groups is equal to the old set with the added group

  Examples:
  |name    |header    |footer   |
  |name1   |header1   |footer1  |
  |name2   |header2   |footer2  |