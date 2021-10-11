package com.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;
	
	//1. By Locators:
	private By homePageLogo = By.xpath("//a[@title='Go to homepage']");
	private By showRowDropdown = By.xpath("//div[@class='sc-16r8icm-0 ekMmID table-control-page-sizer']/div");
	private By selectValueButton = By.xpath("//button[text()='100']");
	private By filterButton = By.cssSelector("div[class='sc-1hxnufv-5 fmuRvw'] button[class='x0o17e-0 ewuQaX sc-36mytl-0 bBafzO table-control-filter']");
	private By addFilterButton = By.xpath("//button[normalize-space()='+ Add Filter']");
	private By marketCapButton = By.xpath("//button[normalize-space()='Market Cap']");
	private By marketCapRangeButton = By.xpath("//button[normalize-space()='$1B - $10B']");
	private By applyFilterButton = By.xpath("//button[normalize-space()='Apply Filter']");
	private By priceButton = By.xpath("//button[normalize-space()='Price']");
	private By priceRangeButton = By.xpath("//button[normalize-space()='$101 - $1,000']");
	private By showResultButton = By.xpath("//button[normalize-space()='Show results']");
	private By currencyTable = By.xpath("//table[contains(@class,'')]/tbody");
	
	
	
	//2. Constructor of the page class:
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//3. Page actions: feature(behavior) of the page
	public String getHomePageTitle() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver.getTitle();
	}
	
	public boolean isLogoDisplayed() {
		return driver.findElement(homePageLogo).isDisplayed();
	}
	
	public void selectRows() {
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.findElement(showRowDropdown).click();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.findElement(selectValueButton).click();
	}
	
	public int getRowCount() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement table = driver.findElement(currencyTable);
		List<WebElement> rowList = table.findElements(By.tagName("tr"));
		int count = rowList.size();
		return count;

	}
	
	public void clickOnFilterButton() {
		 driver.findElement(filterButton).click();
	}
	
	public void clickOnAddFilterButton() {
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(addFilterButton).click();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void clickOnMarketCapButton() {
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(marketCapButton).click();
	}
	
	public void selectMarketCapRange() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(marketCapRangeButton).click();
	}
	
	public void clickOnPriceOption() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(priceButton).click();
	}
	
	public void clickOnPriceRangeButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.findElement(priceRangeButton).click();
	}
	
	public void clickOnApplyFilterButton() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(applyFilterButton).click();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickOnshowResultButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.findElement(showResultButton).click();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public boolean checkPriceRangeInEachRow(int minPrice, int maxPrice) {
		// Locate the table 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement table = driver.findElement(currencyTable);
		List<WebElement> rowList = table.findElements(By.tagName("tr"));
		System.out.println("Total row count : "+ rowList.size());
		for (WebElement row : rowList) {
			List<WebElement> colList = row.findElements(By.tagName("td"));
			String colVal = colList.get(3).getText().substring(1);
			int price = Double.valueOf(colVal).intValue();
			System.out.println("Price integer value in current row : "+ price);
			if (price < minPrice || price > maxPrice) {
				return false;
			}
		}
		return true;
	}

	public boolean checkMarketCapRangeInEachRow(long minValue, long maxValue) {
		System.out.println("Inside function for market cap : " + minValue);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement table = driver.findElement(currencyTable);
		List<WebElement> rowList = table.findElements(By.tagName("tr"));
		for (WebElement row : rowList) {
			List<WebElement> colList = row.findElements(By.tagName("td"));
			String colVal = colList.get(6).getText().substring(1);
			long marketCap = Long.parseLong(colVal.replaceAll(",", ""));
			System.out.println("Price integer value in current row : "+ marketCap);
			if (marketCap < minValue || marketCap > maxValue) {
				System.out.println("Failed value for market cap is : "+ marketCap);
				return false;
			}
		}
		return true;
		
	}
}
