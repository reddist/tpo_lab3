package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MainPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.ulmarts.ru/"

    @FindBy(xpath = "//span[@data-target = '#cityselect']")
    lateinit var changeCityBtn: WebElement

    @FindBy(xpath = "//a[@href = 'goroda']")
    lateinit var citiesBtn: WebElement

    lateinit var title: String;

    @FindBy(xpath = "//a[@href = 'o-proekte']")
    lateinit var aboutProjectMenuItem: WebElement
}