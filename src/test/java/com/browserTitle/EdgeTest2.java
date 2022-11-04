package com.browserTitle;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class EdgeTest2 {
	
	@Test
	public void testinDockerFireFoxTest2() throws MalformedURLException {
		
	DesiredCapabilities cap =  DesiredCapabilities.edge();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("http://gmail.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}
}
