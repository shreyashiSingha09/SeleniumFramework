@tag
  Feature: purchase the order from Ecommerce site
    Background:
    Given I landed on Ecommerce page

    @tag2
      Scenario Outline:
      Given Logged in with userName<userName> and password<password>
      When I add product <productName> to the cart
      And checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
        Examples:
          | userName      | password     | productName |
          | ssh@gmail.com | Hellomampu10 | ZARA COAT 3 |
