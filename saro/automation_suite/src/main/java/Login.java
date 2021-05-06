
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Login {

    @BeforeTest
    public  void setUp() throws IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\OneDrive\\Desktop\\chromedriver.exe");
        TestUtility.driver = new ChromeDriver();
        TestUtility.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }
    //register for the lesson   QA
    @Test
    public void test1() throws InterruptedException, AWTException, IOException {

    //    WebDriver argumentedDriver = new Augmenter().augment(TestUtility.driver);
     //   File screenshot = ((TakesScreenshot)argumentedDriver).getScreenshotAs(OutputType.FILE);

     //   File scrFile = ((TakesScreenshot)TestUtility.driver).getScreenshotAs(OutputType.FILE);
     //   FileUtils.copyFile(scrFile,new File("c:/tmp/screenshot.png"));
//TestUtility.takeScreenShotMethod();

        TestUtility.driver.get("https://www.aca.am");
        //Checking the title
        String title = TestUtility.driver.getTitle();
        Assert.assertTrue(title.contains("ACA | Hom"));
        //register for the lesson   QA
        WebDriverWait wait = new WebDriverWait(TestUtility.driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Ընդունելություն']")));

        WebElement masnakcel = TestUtility.driver.findElement(By.xpath("//a[text()='Ընդունելություն']"));
        masnakcel.click();
        WebElement elementQa = TestUtility.driver.findElement(By.xpath("//img[@src='../assets/img/intro-level/qa.png']"));
        elementQa.click();
        WebElement miacir = TestUtility.driver.findElements(By.xpath("//a[text()='Միացի՛ր հիմա']")).get(0);
        miacir.click();
        //Checking the title
        String title1 = TestUtility.driver.getTitle();
        Assert.assertTrue(title1.contains("ACA | Fundamentals of Quality Assurance"));

        WebElement sharunakel = TestUtility.driver.findElement(By.xpath("//button[@class='btn btn-success js-btn-step']"));
        if (sharunakel.isEnabled()) {
            System.out.println("Element is clickable");
        } else {
            System.out.println("element is not clickable");
        }

        WebElement hamadzaynEm = TestUtility.driver.findElement(By.xpath(" //input[@id =\"agree\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id =\"agree\"]")));
        hamadzaynEm.click();

        if (sharunakel.isEnabled()) {
            sharunakel.click();
            System.out.println("Now Element is clickable ");
        } else {
            System.out.println("element is not clickable");
        }


        String[] arr = {"//input[@id='applicantName']", "//input[@id='applicantEmail']", "//input[@name='applicantPhone']"};
        for (int i = 0; i < arr.length; i++) {
            TestUtility.checkElementExistence(By.xpath(arr[i]));
        }

        String[] values = {"aaaaa", "aaa@aaa.com", "000000"};
        for (int i = 0; i < values.length; i++) {
            TestUtility.driver.findElement(By.xpath(arr[i])).sendKeys(values[i]);
        }

        WebElement grancvel = TestUtility.driver.findElement(By.xpath("//span[text()='Գրանցվել']"));
        grancvel.click();
        //Checking the elements
        TestUtility.checkElementExistence(By.xpath("//h4[@class='js-title-step']"));
        TestUtility.checkElementExistence(By.xpath("//p[@class='enroll-labels']"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ավարտել']")));
        WebElement avart = TestUtility.driver.findElement(By.xpath("//span[text()='Ավարտել']"));
        avart.click();
    }
    //register for the lesson   QA with incorrect data
    @Test
    public void  test2() throws InterruptedException {
        WebElement miacir = TestUtility.driver.findElements(By.xpath("//a[text()='Միացի՛ր հիմա']")).get(0);
        miacir.click();
        WebElement hamadzaynEm = TestUtility.driver.findElement(By.xpath(" //input[@id =\"agree\"]"));

        WebDriverWait wait = new WebDriverWait(TestUtility.driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id =\"agree\"]")));
        hamadzaynEm.click();

        WebElement sharunakel = TestUtility.driver.findElement(By.xpath("//button[@class='btn btn-success js-btn-step']"));
        sharunakel.click();

        String[] arr = {"//input[@id='applicantName']", "//input[@id='applicantEmail']", "//input[@name='applicantPhone']"};
        for (int i = 0; i < arr.length; i++) {
            TestUtility.driver.findElement(By.xpath(arr[i])).clear();
        }
        WebElement grancvel = TestUtility.driver.findElement(By.xpath("//span[text()='Գրանցվել']"));
        grancvel.click();
        //Checking the warning message
        String[] arrElement = {"//label[@for='applicantName']","//label[@for='applicantEmail']","//label[@for='applicantPhone']"};
        for (int i = 0; i < arr.length; i++) {
            TestUtility.checkElementExistence(By.xpath(arrElement[i]));
        }
        //register for the lesson   QA with incorrect emails
        String[] valuesEmail = {"aaaaa", "aaa", "000000000"};
        for (int i = 0; i < arr.length; i++) {

            TestUtility.driver.findElement(By.xpath(arr[i])).sendKeys(valuesEmail[i]);
        }
        grancvel.click();
        //Checking the warning message
        TestUtility.checkElementExistence(By.xpath("//label[text()='Խնդրում ենք լրացնել վավեր Էլ. փոստ']"));
        //Checking the warning message and register for lesson
        String[] valuesEmails= {"aa@","aa",".com"};
        String[] valuescheck = {"//label[text()='Խնդրում ենք լրացնել վավեր Էլ. փոստ']","//label[text()='Խնդրում ենք լրացնել վավեր Էլ. փոստ']","//h4[@class='js-title-step']"};
        for (int a = 0; a < valuescheck.length; a++) {
            TestUtility.driver.findElement(By.xpath("//input[@id='applicantEmail']")).sendKeys(valuesEmails[a]);
            grancvel.click();
            TestUtility.checkElementExistence(By.xpath(valuescheck[a]));
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ավարտել']")));
        WebElement avart = TestUtility.driver.findElement(By.xpath("//span[text()='Ավարտել']"));
        avart.click();
     }
    //register for the become partner
    @Test
    public void  test3() throws InterruptedException {

        TestUtility.driver.navigate().back();

        String[] gorcnkerArray = {"//div[@id='menuToggle']","//div[@id='menuToggle']","//*[@id='partnerThanks']/a"};
        for (int i = 0;i < gorcnkerArray.length; i++) {
            TestUtility.driver.findElement(By.xpath(gorcnkerArray[i])).click();
        }

        String[] arryy = {"//input[@id='partnerName']","//input[@id='partnerEmail']","//input[@id='partnerPhone']"};
        for (int i = 0;i < arryy.length; i++) {
            TestUtility.checkElementExistence(By.xpath(arryy[i]));
        }
        String[] arry={"aaaa","aaaaa","000000000"};
        for (int i = 0;i < arry.length; i++){
            TestUtility.driver.findElement(By.xpath(arryy[i])).sendKeys(arry[i]);
        }

        WebElement gorcnkerdarnal = TestUtility.driver.findElement(By.xpath("//*[@id='partnerSubmit']"));
        gorcnkerdarnal.click();
        //Checking the warning message
        TestUtility.checkElementExistence(By.xpath("//label[text()='Please provide a valid e-mail address.']"));
    }
    //Checking the url
    @Test
    public void  test4() throws InterruptedException {

        int size = TestUtility.driver.findElements(By.xpath("//button[@class='close']")).size();
        WebElement close = TestUtility.driver.findElements(By.xpath("//button[@class='close']")).get(size-1);
        close.click();

        WebElement menuToggle = TestUtility.driver.findElement(By.xpath("//div[@id='menuToggle']"));
        menuToggle.click();

        String[] arrUrl = {"https://www.aca.am/hy/#headerwrap","https://www.aca.am/hy/#courses","https://www.aca.am/hy/#partners","https://www.aca.am/hy/#subscribe"};

        String[] menu = { "//a[@href='#headerwrap']","//a[@href='#courses']","//a[@href='#partners']","//a[@href='#subscribe']"};

        for (int i = 0;i < menu.length; i++){
            TestUtility.driver.findElement(By.xpath(menu[i])).click();

            TestUtility.urlCheck(arrUrl[i]);

            String[]menuElements = {"//a[@href='#description']","//img[@class ='grad-ico']","//a[@href='#partnerbox']","//div[@class='info col-sm-4 col-xs-12 contact-box']"};

            for (int a = 0;a < menuElements.length; a++) {
                TestUtility.checkElementExistence(By.xpath(menuElements[a]));
            }
        }
    }

}