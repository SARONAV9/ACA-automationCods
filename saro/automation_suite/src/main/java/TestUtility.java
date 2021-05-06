import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class TestUtility {

    public static WebDriver driver;

    public static void checkElementExistence(By locator){
        boolean element = false;
        try {
            driver.findElement(locator);
            element = true;
        }catch (NoSuchElementException e){
            element = false;
        }
        Assert.assertTrue(element);
    }

    public static void urlCheck(String linq){
       String URL=driver.getCurrentUrl();
        Assert.assertEquals(URL,linq);
    }
    public static void takeScreenShotMethod() {
        try {
            Thread.sleep(1000);
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "jpg", new File("./target/surefire-reports/screenshot.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
