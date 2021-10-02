package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PromosPageTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: PromosPage
    private var searchCityInputString: String = "мо"
    private var searchedCitiesList: List<String> = listOf("moskva", "lomonosov")

    @Before override fun setup() {
        with(DriverFactory.create(PromosPage::class.java, browserType)) {
            driver = first
            page = second as PromosPage
        }
        driver.get(page.url)
    }

    @Test fun `check last popular smartphone`() {
        var log_text : StringBuilder = StringBuilder("")
        log_text.append("\n" + page.searchCityInput)
        try {
            page.searchCityInput.sendKeys(searchCityInputString)
            log_text.append("\n" + page.searchCityListDiv)
            log_text.append("\n" + page.searchCityListDiv.findElement(By.xpath("//a[@href='skidki/moskva']")))
            searchedCitiesList.forEach {
                assertTrue(page.searchCityListDiv.findElement(By.xpath("//a[@href='skidki/${it}']")).isDisplayed())
            }
        } catch (e: Exception) {
            println("-----------------")
            println(log_text)
            println("-----------------")
            println(e)
            assertTrue(false)
        } finally {
            println("-----------------")
            println(log_text)
            println("-----------------")
        }
    }
}