// Zadanie 7
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,3);
    }

    @Test
    public void myFirstTest(){
        driver.navigate().to("http://localhost/litecart/admin/");
        WebElement uname = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement log = driver.findElement(By.name("login"));

        uname.sendKeys("admin");
        pass.sendKeys("admin");
        log.click();

        wait.until(titleIs("My Store"));

        List<WebElement> MainMenu = driver.findElements(By.id("app-"));
        int CountMenu = MainMenu.size();
        //System.out.println("count of items" + "|" + CountMenu );

        for (int counter = 0;counter < CountMenu; counter++) {
            List<WebElement> MainMenuS = driver.findElements(By.id("app-"));
            MainMenuS.get(counter).click();
            WebElement titlePage = driver.findElement(By.tagName("h1"));
            String titleMain = titlePage.getAttribute("textContent");
            titleMain = titleMain.replaceAll("\\s+","");
            System.out.println("Page: " + titleMain );

            List<WebElement> LocalElements = driver.findElements(By.xpath("//ul[contains(@class,'docs')]/li/a/span"));
            int countItemChild = LocalElements.size();
            //System.out.println("Size: " + countItemChild );
            if(countItemChild > 0){
                for (int counterItem = 0; counterItem < countItemChild; counterItem++){
                    List<WebElement> insideElements = driver.findElements(By.cssSelector("#app- > .docs > li> a > span"));
                    insideElements.get(counterItem).click();

                    WebElement insideTitlePage = driver.findElement(By.tagName("h1"));
                    String titleSubMain = insideTitlePage.getAttribute("textContent");
                    titleSubMain = titleSubMain.replaceAll("\\s+","");
                    System.out.println("  SubPage: " + titleSubMain);
                }
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
