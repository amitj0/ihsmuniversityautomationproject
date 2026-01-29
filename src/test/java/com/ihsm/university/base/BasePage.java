package com.ihsm.university.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BasePage {

	protected static WebDriver driver;
	protected WebDriverWait wait;
	private static final Duration WAIT_TIMEOUT = Duration
			.ofSeconds(Integer.parseInt(System.getProperty("wait.timeout", "10")));

	protected static Logger logger;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
		logger = LogManager.getLogger((this.getClass()));
		PageFactory.initElements(driver, this);
	}

	protected void enterDate(WebElement dateElement, String dateValue) {

		if (dateValue == null || dateValue.trim().isEmpty()) {
			throw new IllegalArgumentException("Date value cannot be null or empty");
		}

		String input = dateValue.trim();

		LocalDate parsedDate = null;

		// Patterns for 4-digit year
		String[] patterns = { "dd/MM/yyyy", "dd-MM-yyyy", "dd.MM.yyyy", "ddMMyyyy", "d/M/yyyy", "d-M-yyyy", "d.M.yyyy",
				"yyyy-MM-dd" };

		// Try 4-digit year patterns first
		for (String pattern : patterns) {
			try {
				parsedDate = LocalDate.parse(input, DateTimeFormatter.ofPattern(pattern));
				break;
			} catch (DateTimeParseException ignored) {
			}
		}

		// If still null, try 2-digit year patterns and interpret as 1900+
		if (parsedDate == null) {
			String[] shortPatterns = { "dd/MM/yy", "dd-MM-yy", "dd.MM.yy", "d/M/yy", "d-M-yy", "d.M.yy" };
			for (String pattern : shortPatterns) {
				try {
					DateTimeFormatter formatter = new DateTimeFormatterBuilder()
							.appendPattern(pattern.substring(0, pattern.length() - 2)) // up to day/month separator
							.appendValueReduced(ChronoField.YEAR, 2, 2, 1900) // 2-digit year offset from 1900
							.toFormatter();
					parsedDate = LocalDate.parse(input, formatter);
					break;
				} catch (DateTimeParseException ignored) {
				}
			}
		}

		if (parsedDate == null) {
			throw new IllegalArgumentException(
					"Invalid date format: " + dateValue + ". Use dd/MM/yyyy, dd-MM-yyyy, dd/MM/yy, etc.");
		}

		// Minimal validation
		if (parsedDate.getYear() < 1900) {
			throw new IllegalArgumentException("Invalid year: " + parsedDate.getYear());
		}

		// UI-friendly format
		String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		try {
			safeClick(dateElement);
			dateElement.clear();
			dateElement.sendKeys(formattedDate);
			dateElement.sendKeys(Keys.TAB);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].value=arguments[1];"
							+ "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));"
							+ "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
					dateElement, formattedDate);
			dateElement.sendKeys(Keys.TAB);
		}
	}

	// ====================== Core Click Methods ======================

	protected void safeClick(WebElement element) {
		try {
			// Always clear JS alert before clicking
			handleAlertIfPresent();

			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

		} catch (UnhandledAlertException e) {
			// Alert appeared DURING click
			handleAlertIfPresent();
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

		} catch (StaleElementReferenceException e) {
			reinitializePageElements();
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

		} catch (ElementClickInterceptedException e) {
			scrollToCenter(element);
			jsClick(element);

		} catch (Exception e) {
			jsClick(element);
		}
	}

	protected void jsClick(WebElement element) {
		handleAlertIfPresent();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	private void clickWithFallback(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception ex) {
			logger.warn("clickWithFallback failed, trying Actions then JS click", ex);
			try {
				new Actions(driver).moveToElement(element).click().perform();
			} catch (Exception aex) {
				logger.warn("Actions fallback failed, using JS click", aex);
				jsClick(element);
			}
		}
	}

	public void handleModalOk(WebElement okButton) {
		try {
			// Ensure no JS alert is blocking
			handleAlertIfPresent();

			WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement ok = modalWait.until(ExpectedConditions.elementToBeClickable(okButton));
			ok.click();

			// Wait until modal disappears
			modalWait.until(d -> d.findElements(By.cssSelector(".modal.show")).isEmpty());

		} catch (TimeoutException e) {
			logger.debug("Modal not present or already closed");
		}
	}

	protected void waitForNgSelectOptions() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ng-dropdown-panel-items")));
	}

	protected void refreshPageSafely() {
		try {
			logger.info("Refreshing page...");
			driver.navigate().refresh();

			// wait for page load
			new WebDriverWait(driver, Duration.ofSeconds(2)).until(
					d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

			// reinitialize page elements
			PageFactory.initElements(driver, this);

		} catch (Exception e) {
			logger.warn("Page refresh failed", e);
		}
	}

	// ====================== Input & Visibility ======================

	protected void clickWhenClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	protected void safeSendKeys(WebElement element, String text) {
		try {
			WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
			visibleElement.clear();
			visibleElement.sendKeys(text);
		} catch (StaleElementReferenceException e) {
			logger.warn("Stale element encountered while sending keys, reinitializing and retrying", e);
			reinitializePageElements();
			try {
				WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
				visibleElement.clear();
				visibleElement.sendKeys(text);
			} catch (Exception ex) {
				logger.error("Failed to send keys after retry", ex);
				throw ex;
			}
		} catch (TimeoutException e) {
			logger.warn("Timeout waiting for element to be visible before sendKeys", e);
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error in safeSendKeys", e);
			throw e;
		}
	}

	protected void waitForModalToClose() {
		try {
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'modal-content')]")));
		} catch (TimeoutException e) {
			logger.warn("Modal did not close in expected time");
		}
	}

	protected boolean handleAlertIfPresent() {
		try {
			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(1));
			Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());

			logger.warn("Browser alert detected: {}", alert.getText());
			alert.accept();
			return true;

		} catch (TimeoutException e) {
			return false; // No alert appeared
		}
	}

	// ====================== Utilities ======================

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	private void scrollToCenter(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}

	/*
	 * public void highlightElement(WebElement element) { try { if
	 * (waitUntilVisible(element, Duration.ofSeconds(1))) { ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].style.border='3px solid red'", element);
	 * } } catch (Exception e) { logger.debug("Unable to highlight element", e); } }
	 */

	public void blinkElement(WebElement element) {
		try {
			if (waitUntilVisible(element, Duration.ofSeconds(1))) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String originalStyle = element.getAttribute("style");

				for (int i = 0; i < 3; i++) {
					js.executeScript("arguments[0].setAttribute('style','border:3px solid black; background: yellow;')",
							element);
					Thread.sleep(200);

					js.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
					Thread.sleep(200);
				}
			}
		} catch (Exception e) {
			logger.debug("Unable to highlight element", e);
			Thread.currentThread().interrupt();
		}
	}

	public void blinkElementToVerify(WebElement element) {
		try {
			if (waitUntilVisible(element, Duration.ofSeconds(1))) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String originalStyle = element.getAttribute("style");

				for (int i = 0; i < 3; i++) {
					js.executeScript("arguments[0].setAttribute('style','border:3px solid black; background: green;')",
							element);
					Thread.sleep(200);

					js.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
					Thread.sleep(200);
				}
			}
		} catch (Exception e) {
			logger.debug("Unable to highlight element", e);
			Thread.currentThread().interrupt();
		}
	}

	public void handleSubmissionConfirmation() {

		// Handle browser alert if present
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("[WARN] Browser alert detected: " + alert.getText());
			alert.accept();
		} catch (TimeoutException e) {
			// No browser alert appeared
		}

		// Handle success modal OK button if present
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")));
			okBtn.click();
		} catch (TimeoutException e) {
			// Modal not present
		}
	}

	// Random Image picker
	public static String getRandomImage() {
		File folder = new File("src/test/resources/images");
		File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));

		if (files != null && files.length > 0) {
			Random rand = new Random();
			return files[rand.nextInt(files.length)].getName(); // return a random image file name
		}
		return "default.png"; // fallback image
	}

	private void reinitializePageElements() {
		PageFactory.initElements(driver, this);
	}

	// Helper waits that return boolean instead of throwing when you need that
	// behavior
	protected boolean waitUntilVisible(WebElement element, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			logger.debug("Element not visible within timeout", e);
			return false;
		}
	}

	protected boolean waitUntilClickable(WebElement element, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			logger.debug("Element not clickable within timeout", e);
			return false;
		}
	}

	// ====================== Additional helpers ======================

	// Wait until document readyState is 'complete'
	protected void waitForPageLoad() {
		try {
			new WebDriverWait(driver, WAIT_TIMEOUT).until(webDriver -> ((JavascriptExecutor) webDriver)
					.executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			logger.debug("Page did not reach readyState 'complete' within timeout", e);
		}
	}

	// Wait for jQuery AJAX calls to finish if jQuery is present, otherwise return
	// immediately
	protected void waitForAjax() {
		try {
			new WebDriverWait(driver, WAIT_TIMEOUT).until(d -> {
				try {
					Object active = ((JavascriptExecutor) d)
							.executeScript("return (window.jQuery != null) ? jQuery.active : 0");
					if (active instanceof Long) {
						return (Long) active == 0L;
					} else if (active instanceof Double) {
						return ((Double) active).intValue() == 0;
					}
					return true;
				} catch (Exception ex) {
					// If jQuery is not present or script fails, assume no AJAX to wait for
					return true;
				}
			});
		} catch (Exception e) {
			logger.debug("waitForAjax: exception while waiting for ajax to complete", e);
		}
	}

	protected WebElement findWhenVisible(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	protected boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

	protected void clickIfVisible(WebElement element, int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (TimeoutException e) {
			logger.info("Element not visible/clickable, skipping");
		}
	}

	public void selectDropdownOption(WebElement dropdown, String optionText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		int attempts = 0;

		while (attempts < 3) { // Retry to handle stale elements
			try {
				// Open the dropdown
				safeClick(dropdown);

				// Wait for options to appear
				List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")));

				// Loop through options and click the matching one
				for (WebElement option : options) {
					if (option.getText().trim().equalsIgnoreCase(optionText)) {
						safeClick(option);
						return;
					}
				}

				throw new NoSuchElementException("Option not found: " + optionText);

			} catch (StaleElementReferenceException e) {
				// Retry if element became stale
				attempts++;
			}
		}

		throw new StaleElementReferenceException("Dropdown element is stale after multiple attempts: " + dropdown);
	}

	// Utility method to capture screenshot
	public static String captureScreenshot(String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
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