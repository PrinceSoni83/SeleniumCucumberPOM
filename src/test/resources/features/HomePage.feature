Feature: Home Page Feature

Background: User is on home screen
	Given I am on the home page of "CoinMarketCap" application


@Test
Scenario: Verify results on show row and the filter for market cap and price 
	When I click on the filter button
	And  I click on Add filter button
	And  I click on Market cap option
	And  Select market cap as "$1B - $10B"
	When I click on the Price option
	And  Select price range as "$101 - $1,000"
	And  Show the result
	Then The records on the page should have price between 101 and 1000
	And  The records on the page should have market cap between "1000000000" and "10000000000"

@Test
Scenario: Verify results on show row
	When I select "Show rows" dropdown value to 100
	Then Page should display 100 rows