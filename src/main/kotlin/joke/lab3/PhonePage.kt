package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PhonePage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.ulmarts.ru/shop/telefonyi"

    lateinit var smartphonesList: List<WebElement>
}