package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Страница заказа
    //поле ввода имени
    private By nameLocator = By.xpath("//input[contains(@placeholder, 'Имя')]");
    //поле ввода фамилии
    private By surnameLocator = By.xpath("//input[contains(@placeholder, 'Фамилия')]");
    //поле ввода адреса
    private By addressLocator = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    //селектор выбрра метро
    private By metroLocator = By.xpath("//input[contains(@placeholder, 'Станция метро')]");
    //поле ввода телефона
    private By phoneLocator = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    //кнопка "Далее"
    private By contButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Далее')]");
    //поле даты
    private By dateLocator = By.xpath("//input[contains(@placeholder, 'Когда привезти')]");
    //поле длительности аренды
    private By durationLocator = By.className("Dropdown-placeholder");
    //чекбокс "черный жемчуг"
    private By blackColorLocator = By.xpath("//*[@id=\"black\"]");
    //чекбокс "серая безысходность"
    private By greyColorLocator = By.xpath("//*[@id=\"grey\"]");
    //поле комментария
    private By commentLocator = By.xpath("//input[contains(@placeholder, 'Комментарий')]");
    //кнопка Заказать
    private By orderButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Заказать')]");
    //кнопка Да
    private By orderYesButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Да')]");
    //текст подтверждения заказа
    private By confirmWindowLocator = By.xpath(".//*[contains(text(), 'Заказ оформлен')]");

    //заполнить имя
    public void fillFormName(String name) {
        driver.findElement(nameLocator).sendKeys(name);
    }

    //заполнить фамилию
    public void fillFormSurname(String surname) {
        driver.findElement(surnameLocator).sendKeys(surname);
    }

    //заполнить фамилию
    public void fillFormAddress(String address) {
        driver.findElement(addressLocator).sendKeys(address);
    }

    //открыть список метро
    public void openMetroList() {
        driver.findElement(metroLocator).click();
    }

    //скролл до станции
    public void scrollToMetroStation(String stationName) {
        WebElement station = driver.findElement(By.xpath("//div[@class='Order_Text__2broi' and text() = '" + stationName + "']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", station);
    }

    //выбрать станцию метро по названию
    public void chooseMetroStation(String station) {
        By stationLocator = By.xpath("//div[@class='Order_Text__2broi' and contains(text(), '" + station + "')]");
        driver.findElement(stationLocator).click();
    }

    //заполнить телефон
    public void fillFormPhone(String phone) {
        driver.findElement(phoneLocator).sendKeys(phone);
    }

    //клик по кнопке Далее
    public void clickContinueButton() {
        driver.findElement(contButtonLocator).click();
    }

    //заполнить поле с датой
    public void fillFormDate(String date) {
        driver.findElement(dateLocator).sendKeys(date);
        driver.findElement(By.className("react-datepicker__day--selected")).click();
    }

    //открыть список длительностей
    public void openDurationList() {
        driver.findElement(durationLocator).click();
    }

    //выбрать длительность аренды
    public void chooseDuration(String duration) {
        By durationLocator = By.xpath("//div[@class='Dropdown-option' and text() = '" + duration + "']");
        driver.findElement(durationLocator).click();
    }

    //выбрать цвет
    public void chooseColor(String color) {
        if (color.equals("black"))
            driver.findElement(blackColorLocator).click();
        else if (color.equals("grey"))
            driver.findElement(greyColorLocator).click();
    }

    //заполнить комментарий
    public void fillFormComment(String comment) {
        driver.findElement(commentLocator).sendKeys(comment);
    }

    //клик по кнопке Заказать
    public void clickOrderButton() {
        driver.findElement(orderButtonLocator).click();
    }

    //клик по кнопке Да во всплывающем окне
    public void clickOrderYesButton() {
        driver.findElement(orderYesButton).click();
    }

    //проверка отображения всплывающего окна
    public boolean isConfirmWindowOpened() {
        return new WebDriverWait(driver, 5).
                until(ExpectedConditions.visibilityOfElementLocated(confirmWindowLocator)).isDisplayed();
    }
}
