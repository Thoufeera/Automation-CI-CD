
@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <name> and password<password>
    When I add the product <productname> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name 		  							| password 							  | productName      |
      | thoufidm87@gmail.com 		| thisisEcommerce@123 		| IPHONE 13 PRO    |
     
