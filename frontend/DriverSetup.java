package frontend;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import util.Data;

public class DriverSetup {

	public static FirefoxOptions getOptions() {

		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--headless");
		return option;
	}

	public static WebDriver getDriver() {

		FirefoxOptions myOption = getOptions();
		WebDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.gecko.driver", Data.geckoPath);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
		return driver;

	}

}
