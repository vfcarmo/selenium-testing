package br.com.vfc.seleniumtesting.browser;

import br.com.vfc.seleniumtesting.helper.BrowserHelper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome extends Browser {

    protected Chrome() {
        super(new ChromeDriver(getChromeOptions()));
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--test-type");
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        options.addArguments("--js-flags=--expose-gc");
        options.addArguments("--enable-precise-memory-info");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        if (Boolean.parseBoolean(BrowserHelper.getEnvValue("headless", "true"))) {
            options.addArguments("--headless");
        }
        return options;
    }
}
