package pages;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    JavascriptExecutor js;

    private final By firstQuestion        = By.xpath(".//div[@class='accordion__item'][1]");
    private final By secondQuestion       = By.xpath(".//div[@class='accordion__item'][2]");
    private final By textInFirstQuestion = By.xpath("//*[@id='accordion__panel-0']");
    private final By textInSecondQuestion = By.xpath("//*[@id='accordion__panel-1']");



    public HomePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void clickOnFirstQuestionInDropdownList() {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(firstQuestion));
        driver.findElement(firstQuestion).click();
    }

    public void clickOnSecondQuestionInDropdownList () {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(secondQuestion));
        driver.findElement(secondQuestion).click();
    }

    public void checkTextInFirstQuestionInDropdownList(String text) {
        clickOnFirstQuestionInDropdownList();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(textInFirstQuestion));
        MatcherAssert.assertThat(driver.findElement(textInFirstQuestion).getText(), CoreMatchers.is(text));
    }

    public void checkTextInSecondQuestionInDropdownList(String text) {
        clickOnSecondQuestionInDropdownList();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(textInSecondQuestion));
        MatcherAssert.assertThat(driver.findElement(textInSecondQuestion).getText(), CoreMatchers.is(text));
    }

    public OrderPage clickOnOrderButton(By orderButton) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        driver.findElement(orderButton).click();
        return new OrderPage(driver);
    }




}
