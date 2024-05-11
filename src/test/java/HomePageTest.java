import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

@RunWith(Parameterized.class)
public class HomePageTest {

    private WebDriver driver;
    private final String textInQuestion;

    public HomePageTest(String textInQuestion) {
        this.textInQuestion = textInQuestion;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Раз раз раз это хардбасс"},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .xpath("//*[@id=\"rcc-confirm-button\"]")));
        driver.findElement(By.xpath("//*[@id=\"rcc-confirm-button\"]")).click();
    }

    @Test
    public void textInFirstQuestionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnFirstQuestionInDropdownList();
        homePage.checkTextInFirstQuestionInDropdownList(textInQuestion);

    }

    @Test
    public void textInSecondQuestionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSecondQuestionInDropdownList();
        homePage.checkTextInSecondQuestionInDropdownList(textInQuestion);

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
