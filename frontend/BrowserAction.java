package frontend;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import util.Data;

public class BrowserAction extends Helper {

	public String actualPhotoID = "";

	public List<String> getData() throws InterruptedException {

		openPage();
		login();
		openSubPage();
		Thread.sleep(4000);
		List<String> posts = getAllPosts();
		getLastPhoto();
		actualPhotoID = getIDfromURL(driver.getCurrentUrl());
		logout();
		driver.quit();
		return posts;
	}

	public void openPage() {

		driver.get(Data.pageUrl);
	}

	public void login() {

		Helper.writeTxt(By.xpath("//input[@id='email']"), Data.myMail);
		Helper.writeTxt(By.xpath("//input[@id='pass']"), Data.myPassword);
		Helper.click(By.xpath("//button[@name='login']"));
	}

	public void openSubPage() throws InterruptedException {

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert a = driver.switchTo().alert();
			a.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		Helper.click(By.xpath(gridMenuxp));
		Helper.click(By.xpath(pagesXp));

		Thread.sleep(2000);
		Helper.click(By.xpath(myPagexp));
		Thread.sleep(1000);
		Helper.click(By.xpath(closeMenuxp));
	}

	public List<String> getAllPosts() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1900)");
		Thread.sleep(5000);
		List<String> posts = driver.findElements(By.xpath(postsxp)).stream().map(el -> el.getText())
				.collect(Collectors.toList());

		return posts;
	}

	public void getLastPhoto() {

		WebElement el = driver.findElement(By.xpath(photoxp));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", el);
		Helper.click(By.xpath(photoxp));
		Helper.click(By.xpath(lastPhotoxp));

	}

	public String getIDfromURL(String url) {

		String[] url1 = url.split("bid=");
		String id = url1[1].split("&set=")[0];
		return id;
	}

	public void logout() {

		Helper.click(By.xpath(profileButtonxp));
		Helper.click(By.xpath(logoutxp));
	}

	String gridMenuxp = "/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[1]/div[1]/div/span";
	String pagesXp = "//a[@href='https://www.facebook.com/pages/?category=your_pages&ref=bookmarks']";
	String myPagexp = "//a[@href='https://www.facebook.com/profile.php?id=100092035996801']";
	String closeMenuxp = "//div[@class='x1i10hfl xjqpnuy xa49m3k xqeqjp1 x2hbi6w x13fuv20 xu3j5b3 x1q0q8m5 x26u7qi x1ypdohk xdl72j9 x2lah0s xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r x2lwn1j xeuugli x16tdsg8 x1hl2dhg xggy1nq x1ja2u2z x1t137rt x1q0g3np x87ps6o x1lku1pv x1a2a7pz x6s0dn4 xzolkzo x12go9s9 x1rnf11y xprq8jg x972fbf xcfux6l x1qhh985 xm0m39n x9f619 x78zum5 xl56j7k xexx8yu x4uap5 x18d9i69 xkhd6sd x1n2onr6 xc9qbxq x14qfxbe x1qhmfi1']";
	String postsxp = "//div[@class='xdj266r x11i5rnm xat24cr x1mh8g0r x1vvkbs']";
	String photoxp = "/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div/div/div[3]/div/div/div/div[1]/div/div/div[1]/div/div/div/div/div/div/a[6]/div[1]";
	String lastPhotoxp = "/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div/div/div[4]/div/div/div/div[1]/div/div/div/div/div[3]/div/div[1]/div/div/div/div/a/img";
	String profileButtonxp = "/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[1]/span";
	String logoutxp = "//span[@id=':rgi:']";

}
