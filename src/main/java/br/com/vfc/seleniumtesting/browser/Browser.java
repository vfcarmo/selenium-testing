package br.com.vfc.seleniumtesting.browser;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Getter
public abstract class Browser {

    protected final WebDriver driver;

    protected Browser(WebDriver driver) {
        this.driver = driver;
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    public static Browser getInstance(BrowserType type) {
        switch (type) {
            case CHROME:
                return new Chrome();
            case FIREFOX:
                return new Firefox();
            case SAFARI:
                return new Safari();
            case EDGE:
                return new Edge();
            case IE:
                return new InternetExplorer();
            default:
                throw new IllegalArgumentException("Invalid browser type");
        }
    }

    public static Browser getInstance(BrowserType type, String driverPath) {
        return getInstance(type, type.getBrowserDriver(), driverPath);
    }

    public static Browser getInstance(BrowserType type, String browserDriverKey, String driverPath) {
        validateDriverLocation(driverPath);
        System.setProperty(browserDriverKey, driverPath);
        return getInstance(type);
    }

    public void quit() {
        if (Objects.nonNull(driver)) {
            this.driver.quit();
        }
    }

    private static void validateDriverLocation(String driverPath) {
        File driver = new File(driverPath);
        if (!driver.exists()) {
            throw new IllegalArgumentException(String.format("Driver not found at %s", driverPath));
        }
    }
}
