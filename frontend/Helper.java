package frontend;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	public static WebDriver driver = DriverSetup.getDriver();
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	public static void waiting(By element) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static void writeTxt(By element, String text) {

		waiting(element);
		driver.findElement(element).sendKeys(text);
	}

	public static void click(By element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
//wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		driver.findElement(element).click();
	}

}
