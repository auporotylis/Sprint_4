package ru.yandex.praktikum.tests;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import ru.yandex.praktikum.pages.*;

import java.awt.image.*;

@RunWith(Parameterized.class)
public class OrderTest {

    WebDriver driver;

    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private String duration;
    private String color;
    private String comment;

    public OrderTest(String name, String surname, String address, String metro, String phone, String date,
                     String duration, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Пушкина, д.1", "Сокольники", "+79991234455", "12.01.2026", "сутки", "black", "Привет курьеру"},
                {"Петрова", "Ирина", "ул. Фестивальная, д.4", "Речной вокзал", "+79991112233", "05.05.2026", "трое суток", "grey", ""},
        };
    }

    @Test
    public void orderWithTopButtonChromeShouldSucceed() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        fillFormAndSubmit(orderPage);
    }

    @Test
    public void orderWithBottomButtonChromeShouldSucceed() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToBotOrderButton();
        mainPage.clickBottomOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        fillFormAndSubmit(orderPage);
    }

    @Test
    public void orderWithTopButtonFirefoxShouldSucceed() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        fillFormAndSubmit(orderPage);
    }

    @Test
    public void orderWithBottomButtonFirefoxShouldSucceed() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToBotOrderButton();
        mainPage.clickBottomOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        fillFormAndSubmit(orderPage);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    private void fillFormAndSubmit(OrderPage orderPage) {
        orderPage.fillFormName(name);
        orderPage.fillFormSurname(surname);
        orderPage.fillFormAddress(address);
        orderPage.openMetroList();
        orderPage.scrollToMetroStation(metro);
        orderPage.chooseMetroStation(metro);
        orderPage.fillFormPhone(phone);
        orderPage.clickContinueButton();
        orderPage.fillFormDate(date);
        orderPage.openDurationList();
        orderPage.chooseDuration(duration);
        orderPage.chooseColor(color);
        orderPage.fillFormComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickOrderYesButton();
        Assert.assertTrue(orderPage.isConfirmWindowOpened());
    }
}
