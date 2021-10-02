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

class PhonePageTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: PhonePage
    private var lastSmartphoneName: String = "Смартфон Xiaomi Mi 8 Lite 4/64GB Midnight Black"

    @Before override fun setup() {
        with(DriverFactory.create(PhonePage::class.java, browserType)) {
            driver = first
            page = second as PhonePage
        }
        driver.get(page.url)
        page.smartphonesList = driver.findElements(
            By.xpath("//div[@class = 'owl-item active']/div/div[@class = 'h4']/a")
        )
    }

    @Test fun `check last popular smartphone`() {
        var log_text : StringBuilder = StringBuilder("")
        log_text.append("\n" + page.smartphonesList)
        try {
            page.smartphonesList.forEach() {
                log_text.append("\n" + "smartphone displayed: ${it.isDisplayed()}")
                assertTrue(it.isDisplayed())
            }
            log_text.append("\n" + page.smartphonesList.last {true})
            val lastSmartphone = page.smartphonesList.last {true}
            (driver as JavascriptExecutor).executeScript("arguments[0].click()", lastSmartphone)
            assertTrue(driver isExist By.xpath("//ol[@class = 'breadcrumb']"))
            val breadcrumbSectionName = this.getLastBreadcrumbValue(driver)
            log_text.append("\n" + breadcrumbSectionName.get("innerHTML"))
            assertTrue(breadcrumbSectionName.get("innerHTML") == this.lastSmartphoneName)
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

    fun getLastBreadcrumbValue (driver: WebDriver): WebElement {
        val breadcrumbElements = driver.findElements(By.xpath("//ol[@class = 'breadcrumb']/li"))
        return breadcrumbElements.last {true}
    }
}