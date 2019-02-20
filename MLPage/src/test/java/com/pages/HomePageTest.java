package com.pages;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.browsers.Browser;
import com.domain.Result;

public class HomePageTest {

	private static final String SUB_MENU_VEHICLES = "Vehículos";
	private static final String DRIVER_KEY = "webdriver.chrome.driver";
	private static final String DRIVER_PATH = "E:/selenium_tools/chromedriver.exe";
	private static final String ALL_VEHICLES_TITLE = "Autos y Camionetas en Mercado Libre Argentina";
	private static final String VEHICLES_PAGE_TITLE = "Vehículos en Mercado Libre Argentina";
	private static final String HOME_PAGE_TITLE = "Mercado Libre Argentina";
	private static final String MENUOPTIONITEM = "Historial";

	@Before
	public void setup() {
		System.setProperty(DRIVER_KEY, DRIVER_PATH);
	}

	@Test
	public void shouldNavigateTo() {
		HomePage homePage = new HomePage();
		homePage.navigateToHomePage();
		Assert.assertEquals(HOME_PAGE_TITLE, homePage.isAt());
	}
	
	@Test
	public void shouldnavigateToMenu() {
		HomePage homePage = new HomePage();
		homePage.navigateToMenu(MENUOPTIONITEM);	
	}

	@Test
	public void shouldNavigateToVehicleSubMenu() {
		HomePage homePage = new HomePage();
		homePage.navigateToSubMenu(SUB_MENU_VEHICLES);
		Assert.assertEquals(VEHICLES_PAGE_TITLE, homePage.isAt());
	}

	@Test
	public void shouldSearchArticlesByClickingOnButton() {
		HomePage homePage = new HomePage();
		homePage.searchAllArticles();
		Assert.assertEquals(ALL_VEHICLES_TITLE, homePage.isAt());
	}

	@Test
	public void shouldGetFileWithResults() {
		HomePage homePage = new HomePage();
		List<Result> results = homePage.getResults();
		Assert.assertNotNull(results);
	}

	@AfterClass
	public static void cleanUp() {
		Browser.getInstance().close();
	}

}
