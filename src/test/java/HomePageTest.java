import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;

@RunWith(Parameterized.class)
public class HomePageTest {

    private WebDriver driver;
    private final By question;
    private final By textInQuestion;
    private final String expectedTextInQuestion;

    public HomePageTest(By question, By textInQuestion, String expectedTextInQuestion) {
        this.question = question;
        this.textInQuestion = textInQuestion;
        this.expectedTextInQuestion = expectedTextInQuestion;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {By.xpath(".//div[@class='accordion__item'][1]"), By.xpath("//*[@id='accordion__panel-0']"), "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {By.xpath(".//div[@class='accordion__item'][2]") , By.xpath("//*[@id='accordion__panel-1']"),"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {By.xpath(".//div[@class='accordion__item'][3]"), By.xpath("//*[@id='accordion__panel-2']"), "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {By.xpath(".//div[@class='accordion__item'][4]"), By.xpath("//*[@id='accordion__panel-3']"), "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {By.xpath(".//div[@class='accordion__item'][5]"), By.xpath("//*[@id='accordion__panel-4']"), "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {By.xpath(".//div[@class='accordion__item'][6]"), By.xpath("//*[@id='accordion__panel-5']"), "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {By.xpath(".//div[@class='accordion__item'][7]"), By.xpath("//*[@id='accordion__panel-6']"), "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {By.xpath(".//div[@class='accordion__item'][8]"), By.xpath("//*[@id='accordion__panel-7']"), "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Before
    public void setUp() {
        BasePage basePage = new BasePage();
        driver = basePage.setUpDriver("firefox");
    }

    @Test
    public void textInQuestionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnQuestion(question);
        homePage.checkTextInQuestion(textInQuestion, expectedTextInQuestion);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
