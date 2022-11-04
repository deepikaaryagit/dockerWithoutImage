package com.browserTitle;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class ChromeTest1  extends BaseTest{
	
	@Test
	public void testinChrome1Docker() throws MalformedURLException {
	System.out.println("*****Chrome Test 1*** ");	
	DesiredCapabilities cap =  DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}


}
