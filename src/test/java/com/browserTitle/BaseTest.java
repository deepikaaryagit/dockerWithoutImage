package com.browserTitle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class BaseTest {
	Runtime runtime = Runtime.getRuntime();
	@BeforeSuite
	public void startFile() throws IOException, InterruptedException {
		System.out.println("***In BaseTest***");
		File file = new File(System.getProperty("user.dir")+"\\output.txt");
		System.out.println("****Output.txt present or not  : " + file.delete());
		if(!file.delete())
		{
			System.out.println("***output.txt file File is deleted successfully*** ");
		}
		runtime.exec("cmd /c start dockerUp.bat");
		Thread.sleep(3000);
		
		String logs = "output.txt";
	//	Calendar caldendar = Calendar.getInstance();
	//	caldendar.add(Calendar.SECOND, 320);
	//	long stopNow = caldendar.getTimeInMillis();
	//	System.out.println("CurrentTime: "+ System.currentTimeMillis());
	//	System.out.println("StopNow: " +stopNow);
		TimeUnit.SECONDS.sleep(40);
		boolean isHubStarted = false;
//		while (System.currentTimeMillis() < stopNow) {
	//	if (isHubStarted == true) {
	//			System.out.println("Hus is already started");
	//			break;
	//		}
		ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(logs), StandardCharsets.UTF_8);
		
			
			String currentLine = reader.readLine();
			System.out.println("Current Line : " + currentLine);
			while (currentLine != null && isHubStarted == false) {
				System.out.println(currentLine);
				if (currentLine.contains("Node has been added")) {
					isHubStarted = true;
					System.out.println("Hub is started");
					
					break;
				}
				currentLine = reader.readLine();
				System.out.println("Hub is NOT started yet...");
			}
			reader.close();
	//	}
		Assert.assertTrue(isHubStarted);

		

	}

	@AfterSuite
	public void tearDown() throws IOException {
	
		runtime.exec("cmd /c start dockerDown.bat");
		File file = new File(System.getProperty("user.dir")+"\\output.txt");
		System.out.println("****Output.txt present or not  : " + file.delete());
		if(!file.delete())
		{
			System.out.println("***output.txt file File is deleted successfully*** ");
		}
		
	}
}
