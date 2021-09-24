package com.practice.step_defs;

import com.practice.utilities.DBUtils;
import com.practice.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.concurrent.TimeUnit;


public class Hooks {

	@Before("@db")
	public void dbHook() {
		System.out.println("creating database connection");
		DBUtils.createConnection();
	}

	@After("@db")
	public void afterDbHook() {
		System.out.println("closing database connection");
		DBUtils.destroyConnection();

	}

	@Before
	public void setUp() {
		// we put a logic that should apply to every scenario
		Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@After
	public void tearDown(Scenario scenario) {
		// only takes a screenshot if the scenario fails
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		Driver.closeDriver();
	}

}