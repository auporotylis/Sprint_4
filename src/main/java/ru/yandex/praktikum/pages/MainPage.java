package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;

import java.util.*;

public class MainPage {
    private WebDriver driver;

    //Вопрос
    public By getQuestionLocator(String questionId) {
        return By.id(questionId);
    }

    //Ответ
    public By getAnswerLocator(String answerId) {
        return By.id(answerId);
    }

    //Кнопка "Заказать" вверху
    By orderTopButton = By.xpath("(//button[text()='Заказать'])[1]");

    //Кнопка "Заказать" внизу
    By orderBotButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //клик по вопросу
    public void clickQuestionById(By questionLocator) {
        driver.findElement(questionLocator).click();
    }

    //текст ответа
    public String getAnswerText(By answerLocator) {
        return driver.findElement(answerLocator).getText();
    }

    //скролл до ответа
    public void scrollToQuestion(By questionLocator) {
        WebElement question = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
    }

    //клик по верхней кнопке заказа
    public void clickTopOrderButton() {
        driver.findElement(orderTopButton).click();
    }

    //скролл до нижней кнопки заказа
    public void scrollToBotOrderButton() {
        WebElement button = driver.findElement(orderBotButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
    }

    //клик по нижней кнопке заказа
    public void clickBottomOrderButton() {
        driver.findElement(orderBotButton).click();
    }
}
