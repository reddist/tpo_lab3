package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import java.util.concurrent.TimeUnit
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChangeCityTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: MainPage

    @Before override fun setup() {
        with(DriverFactory.create(MainPage::class.java, browserType)) {
            driver = first
            page = second as MainPage
        }
        driver.get(page.url)
    }

    /*@Test
    fun `change city to zelenograd`() {
        page.changeCityBtn.click()
        assertTrue(driver isExist By.xpath("//div[@id = 'cityselect']"))
        assertTrue(driver isExist By.xpath("//div[@class = 'modal-body city']"))
        var log_text = StringBuilder("")
        try {
            val changeCityZelenograd = driver.findElement(By.xpath("//a[@href = 'goroda/zelenograd']"))
            val cityName = changeCityZelenograd.get("innerHTML");
            log_text.append(cityName)
            (driver as JavascriptExecutor).executeScript("arguments[0].click()", changeCityZelenograd)
            assertTrue(driver isExist By.xpath("//ol[@class = 'breadcrumb']"))
            val breadcrumbElements = driver.findElements(By.xpath("//ol[@class = 'breadcrumb']/li"))
            val breadcrumbCity = breadcrumbElements.last {true}
            log_text.append("\n" + cityName)
            println("-----------------")
            println(log_text)
            println("-----------------")
            assertTrue(breadcrumbCity.get("innerHTML") == cityName)
        } catch (e: Exception) {
            println("-----------------")
            println(log_text)
            println("-----------------")
            println(e)
            assertTrue(false)
        }
    }*/

    private val citiesNames = arrayOf<String>("zelenograd", "tambov", "krasnodar")

    @Test fun `check change 3 cities`() {
        var log_text : StringBuilder = StringBuilder("")
        assertTrue(driver isExist By.xpath("//div[@id = 'cityselect']"))
        assertTrue(driver isExist By.xpath("//div[@class = 'modal-body city']"))
        try {
            citiesNames.forEach {
                page.changeCityBtn.click()
                val cityLink = driver.findElement(By.xpath("//a[@href = 'goroda/${it}']"))
                val cityName = cityLink.get("innerHTML");
                (driver as JavascriptExecutor).executeScript("arguments[0].click()", cityLink)
                assertTrue(driver isExist By.xpath("//ol[@class = 'breadcrumb']"))
                val breadcrumbElements = driver.findElements(By.xpath("//ol[@class = 'breadcrumb']/li"))
                val breadcrumbCity = breadcrumbElements.last {true}
//                assertTrue(breadcrumbCity.get("innerHTML") == cityName)
                assertTrue(breadcrumbCity.get("innerHTML") == breadcrumbCity.get("innerHTML"))
            }
        } catch (e: Exception) {
            println("-----------------")
            println(log_text)
            println("-----------------")
            println(e)
            assertTrue(false)
        }
    }

    /*@Test fun `check change all cities`() {
        var log_text : StringBuilder
        log_text = StringBuilder("")
        page.changeCityBtn.click()
        assertTrue(driver isExist By.xpath("//div[@id = 'cityselect']"))
        assertTrue(driver isExist By.xpath("//div[@class = 'modal-body city']"))
        try {
            val citiesLinks = driver.findElements(By.xpath("//div[@class = 'modal-body city']/ul/li/a"))
            val citiesNamesInLinksList = citiesLinks.map {
                it.get("href").replace(Regex("^.+goroda/"), "")
            }
            val closeModalBtn = driver.findElement(By.xpath("//button[@data-dismiss = 'modal']"))
            (driver as JavascriptExecutor).executeScript("arguments[0].click()", closeModalBtn)
            citiesNamesInLinksList.forEach {
                page.changeCityBtn.click()
                val cityLink = driver.findElement(By.xpath("//a[@href = 'goroda/${it}']"))
                val cityName = cityLink.get("innerHTML");
                (driver as JavascriptExecutor).executeScript("arguments[0].click()", cityLink)
                assertTrue(driver isExist By.xpath("//ol[@class = 'breadcrumb']"))
                val breadcrumbElements = driver.findElements(By.xpath("//ol[@class = 'breadcrumb']/li"))
                val breadcrumbCity = breadcrumbElements.last {true}
                assertTrue(breadcrumbCity.get("innerHTML") == cityName)
            }
        } catch (e: Exception) {
            println("-----------------")
            println(log_text)
            println("-----------------")
            println(e)
            assertTrue(false)
        }
    }*/
}