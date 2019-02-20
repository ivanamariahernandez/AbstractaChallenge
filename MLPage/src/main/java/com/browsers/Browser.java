package com.browsers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains all the necessary methods to interact with the page.
 * @author Ivana
 *
 */
public class Browser {
	
	private static final WebDriver DRIVER = new ChromeDriver();
	private static final Browser INSTANCE = new Browser();
	private static final String MENUOPTION = "//a[normalize-space(.)='%s']";
	private static final String XPATH_BUTTON_SEARCH = "//button[@class='ch-btn nav-search-classi-submit']";
	private static final String CSS_CLASS_NAV_MENU_CATEGORIES_LINK = "nav-menu-categories-link";
	private static final String CSS_CLASS_NAV_MENU = "nav-menu-list";
	private static final String CSS_SELECTOR_FOR_ARTICLES = 
			"#results-section > #searchResults > li.results-item.highlighted.article.grid";

	private Browser() {
		DRIVER.manage().window().maximize();
	}

	public static Browser getInstance() {
		return INSTANCE;
	}

	public void goTo(String url) {
		DRIVER.navigate().to(url);
	}

	public String getTitle() {
		return DRIVER.getTitle();
	}

	public void goToCategoryMenu() {
		WebDriverWait wait = new WebDriverWait(DRIVER, 10);

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(CSS_CLASS_NAV_MENU_CATEGORIES_LINK)));
		WebElement categoryMenu = DRIVER.findElement(By.className(CSS_CLASS_NAV_MENU_CATEGORIES_LINK));
		Actions actions = new Actions(DRIVER);
		actions.moveToElement(categoryMenu).build().perform();
	}

	public void goToCategorySubMenu(String subMenuName) {
		WebDriverWait wait = new WebDriverWait(DRIVER, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(subMenuName)));
		DRIVER.findElement(By.linkText(subMenuName)).click();
	}

	public void close() {
		DRIVER.quit();
	}

	public void searchAllVehicles() {
		WebDriverWait wait = new WebDriverWait(DRIVER, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath(XPATH_BUTTON_SEARCH)));
		DRIVER.findElement(By.xpath(XPATH_BUTTON_SEARCH)).click();
	}

	public List<WebElement> getResults() {
		List<WebElement> elements = DRIVER.findElements(By.cssSelector(CSS_SELECTOR_FOR_ARTICLES));
		return elements;
	}

	public void goToMenu(String menuOption) {
		WebDriverWait wait = new WebDriverWait(DRIVER, 10);
		String menuOptionValue = String.format(MENUOPTION, menuOption);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(CSS_CLASS_NAV_MENU)));
		DRIVER.findElement(By.xpath(menuOptionValue)).click();
	}
}
