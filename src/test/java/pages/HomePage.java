package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

public class HomePage {

    WebDriver driver;
    JavascriptExecutor js;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void clickOnQuestion(By question) {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(question));
        driver.findElement(question).click();
    }

    public void checkTextInQuestion(By textInQuestion, String text) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(textInQuestion));
        MatcherAssert.assertThat(driver.findElement(textInQuestion).getText(), CoreMatchers.is(text));
    }

    public OrderPage clickOnOrderButton(By orderButton) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        driver.findElement(orderButton).click();
        return new OrderPage(driver);
    }




}
