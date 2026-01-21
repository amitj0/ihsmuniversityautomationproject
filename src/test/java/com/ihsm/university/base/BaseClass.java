package com.ihsm.university.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.ihsm.university.common.ChooseDepartmentPage;
import com.ihsm.university.common.ChooseFacultyPage;
import com.ihsm.university.common.LoginPage;

import org.testng.annotations.Optional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BaseClass {

	// Thread-safe WebDriver using ThreadLocal
	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

	// Make logger static so it's available on instances created outside TestNG
	// lifecycle
	public static Logger logger;
	public Properties prop;
	LoginPage loginPage;
	ChooseFacultyPage chooseFacultyPage;
	ChooseDepartmentPage chooseDepartmentPage;

	// Public getter methods to access driver and wait from test classes
	public static WebDriver getDriver() {
		return driver.get();
	}

	public WebDriverWait getWait() {
		return wait.get();
	}

	// load configuration and initialize logger only
	@BeforeClass(alwaysRun = true)
	public void initConfig() throws IOException {
		logger = LogManager.getLogger("BaseClass");

		prop = new Properties();
		String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		File configFile = new File(configPath);

		if (!configFile.exists()) {
			logger.error("config.properties file not found at: " + configPath);
			throw new IOException("config.properties file not found");
		}

		try (FileInputStream fis = new FileInputStream(configFile)) {
			prop.load(fis);
		}

		String appUrl = prop.getProperty("url");
		if (appUrl == null || appUrl.trim().isEmpty()) {
			logger.error("Application URL is not defined in config.properties");
			throw new RuntimeException("Application URL not configured");
		}
	}

	// Per-test-method setup: initialize WebDriver and navigate to the app. This
	// ensures ThreadLocal is set
	@Parameters({ "os", "browser" })
	@BeforeMethod(alwaysRun = true)
	public void beforeEachTest(@Optional("windows") String os, @Optional("chrome") String browser) throws IOException {
		boolean seleniumGrid = Boolean.parseBoolean(prop.getProperty("seleniumGrid", "false"));
		String gridUrl = prop.getProperty("gridURL");

		WebDriver webDriver = null;

		if (seleniumGrid) {
			logger.info("Running on Selenium Grid: " + gridUrl);
			try {
				if (browser.equalsIgnoreCase("chrome")) {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless=new");
					options.addArguments("--disable-gpu");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--window-size=1920,1080");

					webDriver = new RemoteWebDriver(new URL(gridUrl), options);
				} else if (browser.equalsIgnoreCase("firefox")) {
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("--headless");
					options.addArguments("--width=1920");
					options.addArguments("--height=1080");
					webDriver = new RemoteWebDriver(new URL(gridUrl), options);
				} else {
					throw new IllegalArgumentException("Unsupported browser for Grid: " + browser);
				}
			} catch (MalformedURLException e) {
				logger.error("Invalid Selenium Grid URL: " + gridUrl, e);
				throw new RuntimeException("Invalid Grid URL", e);
			}
		} else {
			logger.info("Running locally on browser: " + browser);

			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();

				Map<String, Object> prefs = new HashMap<>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				prefs.put("profile.password_manager_leak_detection", false);
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				chromeOptions.setExperimentalOption("useAutomationExtension", false);
				chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
				chromeOptions.addArguments("--disable-infobars");
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				webDriver = new ChromeDriver(chromeOptions);
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--start-maximized");
				firefoxOptions.addPreference("signon.rememberSignons", false);
				firefoxOptions.addPreference("signon.autofillForms", false);

				webDriver = new FirefoxDriver(firefoxOptions);
				break;

			default:
				logger.error("Unsupported browser: " + browser);
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
		}

		// Set ThreadLocal values
		driver.set(webDriver);
		// Increase default wait to 10s for better stability
		wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(10)));

		// Common configuration
		getDriver().manage().deleteAllCookies();

		String appUrl = prop.getProperty("url");
		getDriver().get(appUrl.trim());
		logger.info("Thread " + Thread.currentThread().getId() + " - Launched browser: " + browser
				+ " and navigated to: " + appUrl);

		// Perform login/navigation for each test method to ensure isolation.
		loginPage = new LoginPage(getDriver());
		loginPage.login(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		logger.info("Thread " + Thread.currentThread().getId() + " - Logged in successfully");
		chooseFacultyPage = new ChooseFacultyPage(getDriver());
		chooseFacultyPage.degreeFaculty();
		logger.info("Thread " + Thread.currentThread().getId() + " - Chose Faculty successfully");
		chooseDepartmentPage = new ChooseDepartmentPage(getDriver());
		chooseDepartmentPage.chooseDepartPosition();
		logger.info("Thread " + Thread.currentThread().getId() + " - Chose Department successfully");

	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp() {
		if (getDriver() != null) {
			try {
				getDriver().quit();
				logger.info("Thread " + Thread.currentThread().getId() + " - Browser closed successfully");
			} catch (WebDriverException e) {
				logger.error("Error while quitting driver", e);
			} finally {
				driver.remove(); // Critical: Clean up ThreadLocal
				wait.remove();
			}
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		// Fallback cleanup if any driver remains.
		// Usually drivers are cleaned per-method.
		if (getDriver() != null) {
			try {
				getDriver().quit();
				logger.info("Class-level cleanup: browser closed");
			} catch (WebDriverException e) {
				logger.error("Error during class-level teardown", e);
			} finally {
				driver.remove();
				wait.remove();
			}
		}
	}

	// window switching
	public void switchToChildWindow() {
		// Get the handle of the current (parent) window
		String parentHandle = getDriver().getWindowHandle();

		// Get all open window handles
		Set<String> allHandles = getDriver().getWindowHandles();

		for (String handle : allHandles) {
			if (!handle.equals(parentHandle)) {
				getDriver().switchTo().window(handle);
				System.out.println("Switched to window: " + getDriver().getTitle());
				break;
			}
		}
	}

	// Method to get test data file paths
	protected String getTestDataPath(String fileName) {
		return System.getProperty("user.dir") + "/testData/" + fileName;
	}

	// Utility method to capture screenshot
	public static String captureScreenshot(String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_"
				+ Thread.currentThread().getId() + "_" + timestamp + ".png";

		File destination = new File(screenshotPath);
		// Ensure directory exists to avoid IOException
		File parent = destination.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		FileUtils.copyFile(source, destination);

		logger.info("Screenshot captured: " + screenshotPath);
		return screenshotPath; // Return path
	}
}
