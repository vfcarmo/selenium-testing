package br.com.vfc.seleniumtesting.browser;

import lombok.Getter;

@Getter
public enum BrowserType {
    CHROME("webdriver.chrome.driver"),
    FIREFOX("webdriver.gecko.driver"),
    SAFARI("webdriver.safari.driver"),
    EDGE("webdriver.edge.driver"),
    IE("webdriver.ie.driver");

    private final String browserDriver;

    BrowserType(String browserDriver) {
        this.browserDriver = browserDriver;
    }
}
