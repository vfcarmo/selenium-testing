package br.com.vfc.seleniumtesting.pageobject;

import br.com.vfc.seleniumtesting.helper.BrowserHelper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

@Getter
public abstract class BasePage extends LoadableComponent<BasePage> {

    private final WebDriver driver;

    private final BrowserHelper helper;

    private BasePage parentPage;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.helper = new BrowserHelper(driver);
        PageFactory.initElements(driver, this);
        load();
    }

    public BasePage(WebDriver driver, BasePage parentPage) {
        this(driver);
        this.parentPage = parentPage;
    }

    @Override
    public abstract void load();

    @Override
    public abstract void isLoaded() throws Error;

}
