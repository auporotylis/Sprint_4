package ru.yandex.praktikum.tests;

import com.sun.tools.javac.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import ru.yandex.praktikum.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    private String questionIndex;
    private String expectedAnswer;

    public FaqTest(String questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionIdAndAnswer() {
        return new Object[][]{
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void runBrowser() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    @Test
    public void questionShouldOpenCorrectAnswer() {
        driver.get(MainPage.QA_SCOOTER_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToQuestion(questionIndex);
        mainPage.clickQuestionByIndex(questionIndex);
        String actualText = mainPage.getAnswerText(questionIndex);
        Assert.assertEquals("Текст ответа не совпадает для вопроса: " + questionIndex, expectedAnswer, actualText);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
