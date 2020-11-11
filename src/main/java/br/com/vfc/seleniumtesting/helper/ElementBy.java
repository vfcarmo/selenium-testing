package br.com.vfc.seleniumtesting.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementBy extends By {

    public static By buttonWithSpanText(String value) {
        return By.xpath(String.format(".//button[span[text()='%s']]", value));
    }

    public static By liWithValue(String value) {
        return By.xpath(String.format(".//li[value='%s']", value));
    }

    public static By liWithValueStartWith(String value) {
        return By.xpath(String.format(".//li[value^='%s']", value));
    }

    public static By liContainsValue(String value) {
        return By.xpath(String.format(".//li[value*='%s']", value));
    }

    public static By attributeWithValue(String attributeName, String value) {
        return By.cssSelector(String.format("[%s='%s']", attributeName, value));
    }

    public static By attributeWithValueStartWith(String attributeName, String value) {
        return By.xpath(String.format("[%s^='%s']", attributeName, value));
    }

    public static By attributeContainsValue(String attributeName, String value) {
        return By.xpath(String.format("[%s*='%s']", attributeName, value));
    }

    public static By elementWithText(String text) {
        return elementWithText(text, 1);
    }

    public static By elementWithText(String text, int index) {
        return By.xpath(String.format(".//*[text()='%s'][%d]", text, index));
    }

    @Deprecated
    @Override
    public List<WebElement> findElements(SearchContext context) {
        return null;
    }
}
