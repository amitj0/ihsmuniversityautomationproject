package com.ihsm.university.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.ihsm.university.common.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

public class BaseClass {

	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

	public static Logger logger;
	protected Properties prop;
	protected LoginPage loginPage;

	// ================= DRIVER GETTERS =================
	public static WebDriver getDriver() {
		return driver.get();
	}

	public WebDriverWait getWait() {
		return wait.get();
	}

	// ================= LOAD CONFIG =================
	@BeforeClass(alwaysRun = true)
	public void initConfig() {
		try {
			logger = LogManager.getLogger("BaseClass");

			prop = new Properties();
			String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";

			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);

			validateConfig();

		} catch (Exception e) {
			throw new RuntimeException("❌ Failed to load config.properties", e);
		}
	}

	private void validateConfig() {
		if (prop.getProperty("url") == null)
			throw new RuntimeException("❌ 'url' is missing in config.properties");

		if (prop.getProperty("username") == null)
			throw new RuntimeException("❌ 'username' is missing in config.properties");

		if (prop.getProperty("password") == null)
			throw new RuntimeException("❌ 'password' is missing in config.properties");
	}

	// ================= BEFORE EACH TEST =================
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void beforeEachTest(@Optional("chrome") String browser) {

		boolean seleniumGrid = Boolean.parseBoolean(prop.getProperty("seleniumGrid", "false"));
		boolean headless = Boolean.parseBoolean(prop.getProperty("headless", "false"));
		String gridUrl = prop.getProperty("gridURL");

		WebDriver webDriver;

		try {
			if (seleniumGrid) {
				logger.info("Running on Selenium Grid");
				webDriver = getGridDriver(browser, headless, gridUrl);
			} else {
				logger.info("Running locally on browser: " + browser);
				webDriver = getLocalDriver(browser, headless);
			}

			driver.set(webDriver);
			wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(10)));

			if (getDriver() == null) {
				throw new RuntimeException("WebDriver is NULL after initialization");
			}

			getDriver().manage().deleteAllCookies();
			getDriver().get(prop.getProperty("url"));
			logger.info("Navigated to URL: " + prop.getProperty("url"));

			loginPage = new LoginPage(getDriver());
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

			logger.info("Logged in successfully");

		} catch (Exception e) {
			logger.error("❌ Setup failed. Aborting test.", e);
			throw new RuntimeException("Test setup failed", e);
		}
	}

	// ================= LOCAL DRIVER =================
	private WebDriver getLocalDriver(String browser, boolean headless) {

		switch (browser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();

			ChromeOptions chrome = new ChromeOptions();
			chrome.addArguments("--disable-infobars", "--disable-blink-features=AutomationControlled");
			chrome.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			chrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);

			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			chrome.setCapability("goog:loggingPrefs", logPrefs);

			if (headless) {
				chrome.addArguments("--headless=new", "--window-size=1920,1080", "--disable-gpu", "--no-sandbox",
						"--disable-dev-shm-usage");
			} else {
				chrome.addArguments("--start-maximized");
			}

			return new ChromeDriver(chrome);

		case "firefox":
			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions firefox = new FirefoxOptions();
			if (headless) {
				firefox.addArguments("--headless");
				firefox.addArguments("--width=1920", "--height=1080");
			}

			return new FirefoxDriver(firefox);

		default:
			throw new RuntimeException("Unsupported browser: " + browser);
		}
	}

	// ================= GRID DRIVER =================
	private WebDriver getGridDriver(String browser, boolean headless, String gridUrl) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (headless) {
				options.addArguments("--headless=new", "--window-size=1920,1080", "--disable-gpu", "--no-sandbox",
						"--disable-dev-shm-usage");
			}
			return new RemoteWebDriver(new URL(gridUrl), options);
		}

		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (headless) {
				options.addArguments("--headless");
				options.addArguments("--width=1920", "--height=1080");
			}
			return new RemoteWebDriver(new URL(gridUrl), options);
		}

		throw new RuntimeException("Unsupported Grid browser: " + browser);
	}

	// ================= AFTER EACH TEST =================
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
			wait.remove();
		}
	}

	// ================= SCREENSHOT =================
	public static String captureScreenshot(String testName) throws IOException {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + time + ".png";

		File dest = new File(path);
		dest.getParentFile().mkdirs();
		FileUtils.copyFile(src, dest);

		return path;
	}

	// ================= Get test data file paths ==========
	protected String getTestDataPath(String fileName) {
		return System.getProperty("user.dir") + "/src/test/resources/images/" + fileName;
	}
}
