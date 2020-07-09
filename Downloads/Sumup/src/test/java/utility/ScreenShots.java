package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShots {
	/**
	 * 
	 * @param driver
	 * @param folder
	 * @param name
	 */
	public static void captureScreenShots(WebDriver driver, String folder, String name) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/" + "/" + folder + "/" + name + ".png"));
		} catch (Exception e) {
			System.out.println("captureScreenShots fail" + e.getMessage());
		}
	}
}
