package br.com.vfc.seleniumtesting.modelpage.google;

import br.com.vfc.seleniumtesting.pageobject.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    private static final String PAGE_URL = "http://www.google.com.br";

    @FindBy(name = "q")
    private WebElement txtSearch;

    @FindBy(name = "btnK")
    private WebElement btnSearch;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertTrue(getHelper().urlContains("google.com.br"));
    }

    public ResultPage searchFor(String value) {
        getHelper().type(txtSearch, value);
        btnSearch.submit();
        return new ResultPage(getDriver(), this);
    }
}
