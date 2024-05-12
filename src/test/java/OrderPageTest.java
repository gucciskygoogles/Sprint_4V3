import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderPageTest {

    WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;

    private final String phone;
    private final By orderButton;
    private final String dateOfOrder;
    private final String comment;


    public OrderPageTest(String name, String surname, String address, String phone, By orderButton, String dateOfOrder, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.orderButton = orderButton;
        this.dateOfOrder = dateOfOrder;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Никита", "Зяблов", "Пушкина 3", "+73847220934", By.xpath(".//button[@class='Button_Button__ra12g']"), OrderPage.getTomorrowDate(), "Привет"},
                {"Рауль", "Гонзалес", "Сан марино 6", "+73847220934", By.xpath(".//button[@class='Button_Button__ra12g']"), OrderPage.getTomorrowDate(), "Привет"},
                {"Никита", "Зяблов", "Пушкина 3", "+73847220934", By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"), OrderPage.getTomorrowDate(), "Курьер"},
                {"Рауль", "Гонзалес", "Сан марино 6", "+73847220934", By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"), OrderPage.getTomorrowDate(), "У дома"},
        };
    }

    @Before
    public void setUp() {
        BasePage basePage = new BasePage();
        driver = basePage.setUpDriver("chrome");
    }

    @Test
    public void orderWithHeaderButtonTest() {
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = homePage.clickOnOrderButton(orderButton);
        orderPage.setData(name, surname, address, phone);
        orderPage.setMetroField();
        orderPage.clickOnNextButton();
        orderPage.setDataOnDateSection(dateOfOrder, comment);
        orderPage.clickOnOrderButton();
        orderPage.clickOnYesButton();
        orderPage.checkVisibleOfMessage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
