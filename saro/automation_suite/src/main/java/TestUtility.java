import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestUtility {

    public static WebDriver driver;

    public static void checkElementExistence(By locator){
        boolean element;
        try {
            driver.findElement(locator);
            element = true;
        } catch (NoSuchElementException e){
            element = false;
        }
        Assert.assertTrue(element);
    }

    public static void urlCheck(String linq) {
       String URL=driver.getCurrentUrl();
        Assert.assertEquals(URL,linq);
    }

}
