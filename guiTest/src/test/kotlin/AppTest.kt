import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import kotlin.test.assertEquals


class AppTest {
    init {
        System.setProperty("webdriver.chrome.driver", "c:/prog/chromedriver.exe")
    }

    @Test
    fun listsTest() {
        ChromeDriver().apply {
            get("http://localhost:8080/")
            pause()
            findElementByXPath("//a[text()='Lessons']").click()
            addElement(arrayOf("Math"))
            addElement(arrayOf("Innovatica"))
            addElement(arrayOf("Story"))
            delElement("Innovatica")
            findElementByXPath("//a[text()='Students']").click()
            addElement(arrayOf("Sheldon", "Cooper"))
            addElement(arrayOf("Leonard", "Hofstadter"))
            addElement(arrayOf("Howard", "Wolowitz"))
            delElement("Leonard")
            findElementByXPath("//span[contains(text(),'Sheldon')]").click()
            pause()
            findElementByXPath("//span[text()='Math']").click()
            pause()
            findElementByXPath("//span[text()='Story']").click()
            pause()
            findElementByXPath("//button[text()='Save']").click()
            pause()
            findElementByXPath("//a[text()='Students']").click()
            pause()
            delElement("Sheldon")
            pause()
            delElement("Howard")
            pause()
            findElementByXPath("//button[text()='Load']").click()
            pause(3)
            findElementByXPath("//a[text()='Lessons']").click()
            findElementByXPath("//span[text()='Story']").click()
            assertEquals(
                "present",
                findElementByXPath("//span[contains(text(),'Sheldon')]")
                    .getAttribute("class")
            )
            assertEquals(
                "absent",
                findElementByXPath("//span[contains(text(),'Wolowitz')]")
                    .getAttribute("class")
            )
            pause(5)
            close()
        }
    }
}

fun pause(sec: Long = 1) = Thread.sleep(sec * 1000)

fun ChromeDriver.addElement(inputs: Array<String>, pause: Long = 1) {
    findElementByXPath("//span[text()='Add']").click()
    pause(pause)
    findElementByXPath("//table/tr[last()]/td[2]/a").click()
    pause(pause)
    inputs.mapIndexed { index, input ->
        findElementByXPath("//input[$index+1]").apply {
            clear()
            sendKeys(input)
        }
    }
    pause(pause)
    findElementByXPath("//span/button[text()='Save']").click()
    pause(pause)
}

fun ChromeDriver.delElement(name: String) {
    findElementByPartialLinkText(name)
        .findElement(By.xpath("./../..//span[text()=' Delete']"))
        .click()
}