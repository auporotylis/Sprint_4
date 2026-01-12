package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;

public class MainPage {
    private WebDriver driver;
    public static final String QA_SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/";

    //Вопрос
    public By getQuestionLocator(String index) {
        return By.id("accordion__heading-" + index);
    }

    //Ответ
    public By getAnswerLocator(String index) {
        return By.id("accordion__panel-" + index);

    }

    //Кнопка "Заказать" вверху
    By orderTopButton = By.xpath("(//button[text()='Заказать'])[1]");

    //Кнопка "Заказать" внизу
    By orderBotButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //клик по вопросу
    public void clickQuestionByIndex(String index) {
        driver.findElement(getQuestionLocator(index)).click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOfElementLocated(getAnswerLocator(index)));
    }

    //текст ответа
    public String getAnswerText(String index) {
        return driver.findElement(getAnswerLocator(index)).getText();
    }

    //скролл до ответа
    public void scrollToQuestion(String index) {
        WebElement question = driver.findElement(getQuestionLocator(index));
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
