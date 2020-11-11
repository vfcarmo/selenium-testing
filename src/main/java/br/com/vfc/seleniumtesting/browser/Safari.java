package br.com.vfc.seleniumtesting.browser;

import org.openqa.selenium.safari.SafariDriver;

public class Safari extends Browser {

    protected Safari() {
        super(new SafariDriver());
    }
}
