package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.browsers.Browser;
import com.domain.Result;

/**
 * 
 * @author Ivana
 *
 */

public class HomePage {

	private static final String SUB_MENU_VEHICLES = "Vehículos";
	private static final String ATTRIBUTE_HREF = "href";
	private static final String CSS_SELECTOR_FOR_TITLE_ARTICLE = "div > h2 > span.main-title";
	private static final String CSS_SELECTOR_FOR_ARTICLE_LINK = "a:nth-child(3)";
	private static final String HOME_PAGE_URL = "http://www.mercadolibre.com.ar";

	public void navigateToHomePage() {
		Browser.getInstance().goTo(HOME_PAGE_URL);
	}
	
	public void navigateToMenu(String menuOption) {
		navigateToHomePage();
		Browser.getInstance().goToMenu(menuOption);
	}

	public void navigateToCategoryMenu() {
		Browser.getInstance().goToCategoryMenu();
	}

	public void navigateToSubMenu(String subMenuName) {
		navigateToHomePage();
		Browser.getInstance().goToCategoryMenu();
		Browser.getInstance().goToCategorySubMenu(subMenuName);
	}

	public String isAt() {
		return Browser.getInstance().getTitle();
	}

	public void searchAllArticles() {
		navigateToSubMenu(SUB_MENU_VEHICLES);
		Browser.getInstance().searchAllVehicles();
	}

	public List<Result> getResults() {
		searchAllArticles();
		List<WebElement> webElements = Browser.getInstance().getResults();

		List<Result> result = new ArrayList<Result>();
		for (WebElement element : webElements) {
			WebElement linkElement = element.findElement(By.cssSelector(CSS_SELECTOR_FOR_ARTICLE_LINK));
			WebElement titleElement = linkElement.findElement(By.cssSelector(CSS_SELECTOR_FOR_TITLE_ARTICLE));
			result.add(new Result(titleElement.getText(), linkElement.getAttribute(ATTRIBUTE_HREF)));
		}
		return result;
	}

	public void close() {
		Browser.getInstance().close();
	}
}
