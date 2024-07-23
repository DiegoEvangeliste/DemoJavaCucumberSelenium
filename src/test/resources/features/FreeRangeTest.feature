@All
@FreeRangeTest
Feature: Test navigation
  Test navigation bar options
  Try to log in

#  @Background: navigate to home page
#    Given I navigate to www.freerangetesters.com

  @NavigationBar
  Scenario Outline: I can access the subpages through the navigation bar
    Given I navigate to www.freerangetesters.com
    When I go to <section> using the navigation bar
    Examples:
      | section  |
      | Cursos   |
      | Recursos |
      | Blog     |
      | Udemy    |

  @Login
  Scenario: Users can select a plan when signing up
    Given I navigate to www.freerangetesters.com
    When I click on button Entrar
    And I complete input text for login
    Then I click on button Inicio de sesion