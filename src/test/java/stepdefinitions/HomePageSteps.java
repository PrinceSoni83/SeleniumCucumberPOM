package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.junit.Assert;

import com.factory.DriverFactory;
import com.pages.HomePage;



public class HomePageSteps {
	
	private static String title;
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	
	@Given("I am on the home page of {string} application")
	public void i_am_on_the_home_page_of_coinmarketcap_application(String expectedTitleName)  {
		DriverFactory.getDriver()
		.get("https://coinmarketcap.com/");
		title = homePage.getHomePageTitle();
		System.out.println("Home Page title is: "+ title);
		Assert.assertTrue(title.contains(expectedTitleName));
		Assert.assertTrue(homePage.isLogoDisplayed());
	}

	@When("I select {string} dropdown value to {int}")
	public void i_select_dropdown_value_to(String showRowTopLabel, Integer rowCount) {
		homePage.selectRows();
	}

	@Then("Page should display {int} rows")
	public void page_should_display_rows(Integer rowCount) {
		Assert.assertTrue(homePage.getRowCount() == rowCount);
	}
	
	@When("I click on the filter button")
	public void i_click_on_the_filter_button() {
	    homePage.clickOnFilterButton();
	}

	@And("I click on Add filter button")
	public void i_click_on_add_filter_button() {
		homePage.clickOnAddFilterButton();
	}

	@And("I click on Market cap option")
	public void i_click_on_market_cap_option() {
		homePage.clickOnMarketCapButton();
	}

	@And("Select market cap as {string}")
	public void select_market_cap_as(String string) {
	   homePage.selectMarketCapRange();
	   homePage.clickOnApplyFilterButton();
	}

	@When("I click on the Price option")
	public void i_click_on_the_price_option() {
		homePage.clickOnPriceOption();
	}

	@And("Select price range as {string}")
	public void select_price_range_as(String string) {
		homePage.clickOnPriceRangeButton();
		homePage.clickOnApplyFilterButton();
	}

	@And("Show the result")
	public void show_the_result() {
		homePage.clickOnshowResultButton();
	}
	
	@Then("The records on the page should have price between {int} and {int}")
	public void the_records_on_the_page_should_have_price_between_and(Integer minPrice, Integer maxPrice) {
		homePage.checkPriceRangeInEachRow(minPrice, maxPrice);
	}
	
	@And("The records on the page should have market cap between {string} and {string}")
	public void the_records_on_the_page_should_have_market_cap_between_and(String minValue, String maxValue) {
		homePage.checkMarketCapRangeInEachRow(Long.parseLong(minValue), Long.parseLong(maxValue));
	}
}
