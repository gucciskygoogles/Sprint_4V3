package pages;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderPage {

    WebDriver driver;

    // Блок полей для заполнения контактной информации
    private final By nameField    = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField  = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField    = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField    = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton    = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");
    private final By metroFieldDropdownSelect = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[5]");

    // Блок полей для заполнения информвции "Про аренду"
    private final By dateOfOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div/div/input");
    private final By rentalPeriodDropdown = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]");
    private final By rentalPeriodPick = By.xpath(".//div[text()='двое суток']");
    private final By colorOfScooter = By.xpath(".//label[@for='black']");
    private final By commentOfOrder = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");

    //Блок полей, связанных с завершением оформления заказа
    private final By doYouWantToOrderButtonYes = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");
    private final By messageOfCompletedOrder = By.xpath("//button[contains(text(),'Посмотреть статус')]");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void setFieldValue(By fieldLocator, String text) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        driver.findElement(fieldLocator).sendKeys(text);
    }

    public void setData(String name, String surname, String address, String phone) {
        setFieldValue(nameField, name);
        setFieldValue(surnameField, surname);
        setFieldValue(addressField, address);
        setFieldValue(phoneField, phone);
        setMetroField();
    }

    public void setDataOnDateSection(String date, String comment) {
        setFieldValue(commentOfOrder, comment);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodDropdown));
        driver.findElement(rentalPeriodDropdown).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodPick));
        driver.findElement(rentalPeriodPick).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(colorOfScooter));
        driver.findElement(colorOfScooter).click();
        setFieldValue(dateOfOrder, date);
    }

    public void setMetroField() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(metroField));
        driver.findElement(metroField).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(metroFieldDropdownSelect));
        driver.findElement(metroFieldDropdownSelect).click();
    }

    public void clickOnNextButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(nextButton));
        driver.findElement(nextButton).click();
    }

    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickOnYesButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(doYouWantToOrderButtonYes));
        driver.findElement(doYouWantToOrderButtonYes).click();
    }

    public void checkVisibleOfMessage() {
        MatcherAssert.assertThat("Элемент не найден или не отображается",
                driver.findElement(messageOfCompletedOrder).isDisplayed(),
                CoreMatchers.is(true));
    }

    public static String getTomorrowDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String tomorrowDate = sdf.format(calendar.getTime());

        return tomorrowDate;
    }

}
