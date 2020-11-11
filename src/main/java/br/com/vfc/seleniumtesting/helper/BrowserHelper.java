package br.com.vfc.seleniumtesting.helper;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

@Log4j2
@Getter
public class BrowserHelper {

    private static final String DEFAULT_TIMEOUT = "30";
    private static final String DEFAULT_POLLING = "1";

    private final WebDriver driver;

    public BrowserHelper(WebDriver driver) {
        this.driver = driver;
    }

    public static String getEnvValue(String envVarName, String defaultValue) {
        log.debug("Reading {} from environment variable", envVarName);
        String envVar = System.getenv(envVarName);
        if (StringUtils.isNoneBlank(envVar)) {
            log.debug("Value found in env");
            return envVar;
        } else {
            log.debug("Default value will be returned: {}", defaultValue);
            return defaultValue;
        }
    }

    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public void navigateForward() {
        getDriver().navigate().forward();
    }

    public void navigateTo(String url) {
        getDriver().navigate().to(url);
    }

    public <T> T navigateTo(String url, Class<T> destinationClass) {
        navigateTo(url);
        return getPage(destinationClass);
    }

    public WebElement getElement(By locator) {
        log.debug("Waiting for element {} with default timeout", locator.toString());
        final WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        log.debug("Wait for {} completed", locator.toString());
        return element;
    }

    public WebElement getElement(By locator, int timeout) {
        log.debug("Waiting for element {} with wait timeout: {}", locator.toString(), timeout);
        final WebElement element = getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        log.debug("Wait for {} completed", locator.toString());
        return element;
    }

    public WebElement getElement(WebElement webElement) {
        log.debug("Waiting for element {} with default timeout", webElement.getText());
        final WebElement element = webElement.isDisplayed() ? webElement :
                getWait().until(ExpectedConditions.visibilityOf(webElement));
        log.debug("Wait for {} completed", webElement.getText());
        return element;
    }

    public WebElement getElement(WebElement webElement, int timeout) {
        log.debug("Waiting for element {} with wait timeout: {}", webElement.getText(), timeout);
        final WebElement element = webElement.isDisplayed() ? webElement :
                getWait(timeout).until(ExpectedConditions.visibilityOf(webElement));
        log.debug("Wait for {} completed", webElement.getText());
        return element;
    }

    public void click(By locator) {
        log.debug("Clicking on element {} with default timeout", locator.toString());
        getElement(locator).click();
        log.debug("Element {} clicked", locator.toString());
    }

    public void click(By locator, int timeout) {
        log.debug("Clicking on element {} with wait timeout: {}", locator.toString(), timeout);
        getElement(locator, timeout).click();
        log.debug("Element {} clicked", locator.toString());
    }

    public void click(WebElement webElement) {
        log.debug("Clicking on element {} with default timeout", webElement.getText());
        getElement(webElement).click();
        log.debug("Element {} clicked", webElement.getText());
    }

    public void click(WebElement webElement, int timeout) {
        log.debug("Clicking on element {} with wait timeout: {}", webElement.getText(), timeout);
        getElement(webElement, timeout).click();
        log.debug("Element {} clicked", webElement.getText());
    }

    public <T> T click(By locator, Class<T> destinationPage) {
        click(locator);
        log.debug("Opening page {}", destinationPage.getSimpleName());
        return getPage(destinationPage);
    }

    public <T> T click(By locator, int timeout, Class<T> destinationPage) {
        click(locator, timeout);
        log.debug("Opening page {}", destinationPage.getSimpleName());
        return getPage(destinationPage);
    }

    public <T> T click(WebElement webElement, Class<T> destinationPage) {
        click(webElement);
        log.debug("Opening page {}", destinationPage.getSimpleName());
        return getPage(destinationPage);
    }

    public <T> T click(WebElement webElement, int timeout, Class<T> destinationPage) {
        click(webElement, timeout);
        log.debug("Opening page {}", destinationPage.getSimpleName());
        return getPage(destinationPage);
    }

    public String getText(By locator) {
        log.debug("Retrieving text on element {} with default timeout", locator.toString());
        String text = getElement(locator).getText();
        log.debug("Text on element {} retrieved", locator.toString());
        return text;
    }

    public String getText(By locator, int timeout) {
        log.debug("Retrieving text on element {} with timeout: {}", locator.toString(), timeout);
        String text = getElement(locator, timeout).getText();
        log.debug("Text on element {} retrieved", locator.toString());
        return text;
    }

    public String getText(WebElement webElement) {
        log.debug("Retrieving text on element {} with default timeout", webElement.getText());
        String text = getElement(webElement).getText();
        log.debug("Text on element {} retrieved", webElement.getText());
        return text;
    }

    public String getText(WebElement webElement, int timeout) {
        log.debug("Retrieving text on element {} with timeout: {}", webElement.getText(), timeout);
        String text = getElement(webElement, timeout).getText();
        log.debug("Text on element {} retrieved", webElement.getText());
        return text;
    }

    public void type(By locator, String text) {
        clear(locator);
        log.debug("Typing text {} on element {} with default timeout", text, locator.toString());
        getElement(locator).sendKeys(text);
        log.debug("Typed");
    }

    public void type(By locator, String text, int timeout) {
        clear(locator, timeout);
        log.debug("Typing text {} on element {} with timeout: {}", text, locator.toString(), timeout);
        getElement(locator, timeout).sendKeys(text);
        log.debug("Typed");
    }

    public void type(WebElement webElement, String text) {
        clear(webElement);
        log.debug("Typing text {} on element {} with default timeout", text, webElement.getText());
        getElement(webElement).sendKeys(text);
        log.debug("Typed");
    }

    public void type(WebElement webElement, String text, int timeout) {
        clear(webElement, timeout);
        log.debug("Typing text {} on element {} with timeout: {}", text, webElement.getText(), timeout);
        getElement(webElement, timeout).sendKeys(text);
        log.debug("Typed");
    }

    public void pressKey(By locator, Keys key) {
        getElement(locator).sendKeys(key);
    }

    public void pressKey(By locator, int timeout, Keys key) {
        getElement(locator, timeout).sendKeys(key);
    }

    public void pressKey(WebElement webElement, Keys key) {
        getElement(webElement).sendKeys(key);
    }

    public void pressKey(WebElement webElement, int timeout, Keys key) {
        getElement(webElement, timeout).sendKeys(key);
    }

    public void clear(By locator) {
        log.debug("Clearing text on element {} with default timeout", locator.toString());
        getElement(locator).clear();
        log.debug("Element {} cleared", locator.toString());
    }

    public void clear(By locator, int timeout) {
        log.debug("Clearing text on element {} with wait timeout: {}", locator.toString(), timeout);
        getElement(locator, timeout).clear();
        log.debug("Element {} cleared", locator.toString());
    }

    public void clear(WebElement webElement) {
        log.debug("Clearing text on element {} with default timeout", webElement.getText());
        getElement(webElement).clear();
        log.debug("Element {} cleared", webElement.getText());
    }

    public void clear(WebElement webElement, int timeout) {
        log.debug("Clearing text on element {} with wait timeout: {}", webElement.getText(), timeout);
        getElement(webElement, timeout).clear();
        log.debug("Element {} cleared", webElement.getText());
    }

    public boolean urlContains(String value) {
        return getWait().until(ExpectedConditions.urlContains(value));
    }

    public boolean urlContains(String value, int timeout) {
        return getWait(timeout).until(ExpectedConditions.urlContains(value));
    }

    public void executeJs(String script) {
        log.debug("Executing JS {}", script);
        executeJavaScript(script);
    }

    public void executeJs(By locator, String script) {
        log.debug("Executing JS {} on element {}", script, locator.toString());
        executeJavaScript(script, getElement(locator));
    }

    public void executeJs(WebElement webElement, String script) {
        log.debug("Executing JS {} on element {}", script, webElement.getText());
        executeJavaScript(script, getElement(webElement));
    }

    public Wait<WebDriver> getWait() {
        int timeout = Integer.parseInt(getEnvValue("WAIT", DEFAULT_TIMEOUT));
        return getWait(timeout);
    }

    public Wait<WebDriver> getWait(int timeout) {
        log.debug("Retrieving wait with {} seconds", timeout);
        int polling = Integer.parseInt(getEnvValue("POLLING", DEFAULT_POLLING));
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver());
        wait.withTimeout(Duration.ofSeconds(timeout));
        wait.pollingEvery(Duration.ofSeconds(polling));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        log.debug("Wait retrieved");
        return wait;
    }

    private void executeJavaScript(String script, Object... args) {
        log.debug("Executing JS: {}", script);
        try {
            getJsExecutor().executeScript(script, args);
        } catch (Exception e) {
            log.error("Error on execute JS: {}", script);
        }
    }

    private JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    private <T> T getPage(Class<T> destinationClass) {
        try {
            return destinationClass.getDeclaredConstructor(WebDriver.class).newInstance(getDriver());
        } catch (Exception e) {
            log.error("Error on open destination page", e);
            throw new RuntimeException(e);
        }
    }

}
