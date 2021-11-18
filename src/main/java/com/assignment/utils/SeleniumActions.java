package com.assignment.utils;

import com.assignment.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.function.Consumer;

public final class SeleniumActions {

    private SeleniumActions(){}

    public static void waitAndClick(By by,String elementname){
        waitForElementToBePresent(by).click();
        //TODO element name for logging
    }
    public static String getText(By by){
        return waitForElementToBePresent(by).getText();
    }

    public static void selectValueInDropDown(By by, Consumer<Select> consumer) {
        WebElement element = waitForElementToBePresent(by);
        Select select = new Select(element);
        consumer.accept(select);
    }

    public static void switchToNewlyOpenedWindow(){
        String parentWinHandle = DriverManager.getDriver().getWindowHandle();

        Set<String> winHandles = DriverManager.getDriver().getWindowHandles();
        for(String temp:winHandles) {
            if(!temp.equalsIgnoreCase(parentWinHandle)) {
                DriverManager.getDriver().switchTo().window(temp);
            }
        }

    }
    public static void waitScrollAndClick(By by, String elementname){
        WebElement element = waitForElementToBePresent(by);
        ((JavascriptExecutor)DriverManager.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitAndEnterText(By by,String value,String elementname){
        waitForElementToBePresent(by).sendKeys(value);
    }

    private static WebElement waitForElementToBePresent(By by){
        return new WebDriverWait(DriverManager.getDriver(),10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}