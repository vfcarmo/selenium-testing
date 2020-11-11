package br.com.vfc.seleniumtesting.modelpage.google;

import br.com.vfc.seleniumtesting.pageobject.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class ResultPage extends BasePage {

    @FindBy(css = "div[data-hveid] div.r")
    private List<WebElement> results;

    public ResultPage(WebDriver driver, BasePage parentPage) {
        super(driver, parentPage);
    }

    @Override
    public void load() {
        // This page cannot be loaded manually, browser is redirected from search page.
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertTrue(getHelper().urlContains("/search"));
    }

    public void containsResult(String... values) {
        boolean containsResult = Arrays.stream(values)
                .anyMatch(value -> results.stream().anyMatch(webElement -> webElement.getText().contains(value)));
        Assert.assertTrue(containsResult);
    }

}
