package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.*;
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
    private boolean useTopButton;

    public OrderTest(String name, String surname, String address, String metro, String phone, String date,
                     String duration, String color, String comment, Boolean useTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
        this.useTopButton = useTopButton;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Пушкина, д.1", "Сокольники", "+79991234455", "12.01.2026", "сутки", "black", "Привет курьеру", true},
                {"Петрова", "Ирина", "ул. Фестивальная, д.4", "Речной вокзал", "+79991112233", "05.05.2026", "трое суток", "grey", "", true},
                {"Иван", "Иванов", "ул. Пушкина, д.1", "Сокольники", "+79991234455", "12.01.2026", "сутки", "black", "Привет курьеру", false},
                {"Петрова", "Ирина", "ул. Фестивальная, д.4", "Речной вокзал", "+79991112233", "05.05.2026", "трое суток", "grey", "", false},
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
    public void orderScooter() {

        driver.get(MainPage.QA_SCOOTER_URL);
        MainPage mainPage = new MainPage(driver);

        if (useTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.scrollToBotOrderButton();
            mainPage.clickBottomOrderButton();
        }
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
        Assert.assertTrue("Не открылось окно подтверждения заказа", orderPage.isConfirmWindowOpened());
    }
}
