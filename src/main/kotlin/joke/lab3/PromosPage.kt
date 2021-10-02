package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PromosPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.ulmarts.ru/skidki"

    @FindBy(xpath = "//input[@id='CitySearchInput']")
    lateinit var searchCityInput: WebElement

    @FindBy(xpath = "//ul[@id='CitySearchUl']")
    lateinit var searchCityListDiv: WebElement

}