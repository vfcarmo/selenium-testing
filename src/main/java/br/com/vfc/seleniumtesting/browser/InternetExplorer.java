package br.com.vfc.seleniumtesting.browser;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class InternetExplorer extends Browser {

    protected InternetExplorer() {
        super(new InternetExplorerDriver());
    }
}
