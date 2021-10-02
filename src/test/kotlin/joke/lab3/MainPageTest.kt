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
import java.util.concurrent.TimeUnit
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MainPageTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: MainPage
    private var title: String = "Юлмарт - интернет-магазин бытовой техники и электроники, официальные магазины Ulmart"
    private var aboutProjectMenuItemTitle: String = "О проекте"

    @Before override fun setup() {
        with(DriverFactory.create(MainPage::class.java, browserType)) {
            driver = first
            page = second as MainPage
        }
        driver.get(page.url)
        page.title = driver.getTitle()
    }

    @Test fun `check title of page`() {
        assertEquals(title, page.title);
    }

    @Test fun `check menu items`() {
        var log_text : StringBuilder = StringBuilder("")
        assertTrue(page.aboutProjectMenuItem.isDisplayed())
        try {
            (driver as JavascriptExecutor).executeScript("arguments[0].click()", page.aboutProjectMenuItem)
            assertTrue(driver isExist By.xpath("//ol[@class = 'breadcrumb']"))
            val breadcrumbSectionName = this.getLastBreadcrumbValue(driver)
            assertTrue(breadcrumbSectionName.get("innerHTML") == this.aboutProjectMenuItemTitle)
        } catch (e: Exception) {
            println("-----------------")
            println(log_text)
            println("-----------------")
            println(e)
            assertTrue(false)
        }
    }

    fun getLastBreadcrumbValue (driver: WebDriver): WebElement {
        val breadcrumbElements = driver.findElements(By.xpath("//ol[@class = 'breadcrumb']/li"))
        return breadcrumbElements.last {true}
    }
}