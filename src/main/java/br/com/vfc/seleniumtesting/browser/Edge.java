package br.com.vfc.seleniumtesting.browser;

import org.openqa.selenium.edge.EdgeDriver;

public class Edge extends Browser {

    protected Edge() {
        super(new EdgeDriver());
    }
}
