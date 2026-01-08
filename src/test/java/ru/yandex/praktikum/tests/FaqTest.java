package ru.yandex.praktikum.tests;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import ru.yandex.praktikum.pages.MainPage;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    private String questionId;
    private String answerId;
    private String expectedAnswer;

    public FaqTest(String questionId, String answerId, String expectedAnswer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionIdAndAnswer() {
        return new Object[][]{
                {"accordion__heading-0", "accordion__panel-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "accordion__panel-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "accordion__panel-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "accordion__panel-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "accordion__panel-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "accordion__panel-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "accordion__panel-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "accordion__panel-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void questionShouldOpenCorrectAnswerChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        By questionLocator = mainPage.getQuestionLocator(questionId);
        By answerLocator = mainPage.getAnswerLocator(answerId);

        mainPage.scrollToQuestion(questionLocator);
        mainPage.clickQuestionById(questionLocator);

        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));

        String actualText = mainPage.getAnswerText(answerLocator);
        Assert.assertEquals("Текст ответа не совпадает для вопроса: " + questionId, expectedAnswer, actualText);
    }

    @Test
    public void questionShouldOpenCorrectAnswerFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        By questionLocator = mainPage.getQuestionLocator(questionId);
        By answerLocator = mainPage.getAnswerLocator(answerId);

        mainPage.scrollToQuestion(questionLocator);
        mainPage.clickQuestionById(questionLocator);

        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));

        String actualText = mainPage.getAnswerText(answerLocator);
        Assert.assertEquals("Текст ответа не совпадает для вопроса: " + questionId, expectedAnswer, actualText);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
