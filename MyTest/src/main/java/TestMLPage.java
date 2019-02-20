import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.domain.Result;
import com.google.gson.Gson;
import com.pages.HomePage;

public class TestMLPage {

	private static final String FILE_NAME = "demo.json";
	private static final String DRIVER_KEY = "webdriver.chrome.driver";
	// You should write here the path of the chrome/firefox driver
	private static final String DRIVER_PATH = "E:/selenium_tools/chromedriver.exe";

	public static void main(String[] args) throws IOException {		
		HomePage homePage = new HomePage();
		System.setProperty(DRIVER_KEY, DRIVER_PATH);
		
		navigateToMenuHistorial(homePage, "Historial");		
		getFileWithVehicles(homePage);
		
		homePage.close();
	}

	private static void navigateToMenuHistorial(HomePage homePage, String menu) {
		homePage.navigateToMenu(menu);
	}

	private static void getFileWithVehicles(HomePage homePage) throws IOException {		
		List<Result> results = homePage.getResults();
		File file = new File(FILE_NAME);
		FileWriter writer = new FileWriter(file);		
		Gson gson = new Gson();
		writer.write(gson.toJson(results));
		writer.close();		
	}
}
