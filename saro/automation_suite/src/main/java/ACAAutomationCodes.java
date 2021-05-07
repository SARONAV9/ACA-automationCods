import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class ACAAutomationCodes {

    @BeforeTest
    public  void setUp() {
        WebDriverManager.chromedriver().setup();
        TestUtility.driver = new ChromeDriver();
        TestUtility.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void request_to_join_qa_lesson() {
        TestUtility.driver.get("https://www.aca.am");
        String title = TestUtility.driver.getTitle();
        Assert.assertTrue(title.contains("ACA | Hom"));

        WebDriverWait wait = new WebDriverWait(TestUtility.driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Ընդունելություն']")));

        WebElement participate = TestUtility.driver.findElement(By.xpath("//a[text()='Ընդունելություն']"));
        participate.click();
        WebElement qaElement = TestUtility.driver.findElement(By.xpath("//img[@src='../assets/img/intro-level/qa.png']"));
        qaElement.click();
        WebElement join = TestUtility.driver.findElements(By.xpath("//a[text()='Միացի՛ր հիմա']")).get(0);
        join.click();

        String title1 = TestUtility.driver.getTitle();
        Assert.assertTrue(title1.contains("ACA | Fundamentals of Quality Assurance"));

        WebElement continueElement = TestUtility.driver.findElement(By.xpath("//button[@class='btn btn-success js-btn-step']"));
        if (continueElement.isEnabled()) {
            System.out.println("Element is clickable");
        } else {
            System.out.println("Element is not clickable");
        }

        WebElement agree = TestUtility.driver.findElement(By.xpath(" //input[@id =\"agree\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id =\"agree\"]")));
        agree.click();

        if (continueElement.isEnabled()) {
            continueElement.click();
            System.out.println("Now Element is clickable ");
        } else {
            System.out.println("element is not clickable");
        }

        String[] arr = {"//input[@id='applicantName']", "//input[@id='applicantEmail']", "//input[@name='applicantPhone']"};
        for (int i = 0; i < arr.length; i++) {
            TestUtility.checkElementExistence(By.xpath(arr[i]));
        }

        String[] values = {"Maria", "maria@gmail.com", "099999999"};
        for (int i = 0; i < values.length; i++) {
            TestUtility.driver.findElement(By.xpath(arr[i])).sendKeys(values[i]);
        }

        WebElement register = TestUtility.driver.findElement(By.xpath("//span[text()='Գրանցվել']"));
        register.click();
        TestUtility.checkElementExistence(By.xpath("//h4[@class='js-title-step']"));
        TestUtility.checkElementExistence(By.xpath("//p[@class='enroll-labels']"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ավարտել']")));
        WebElement finish = TestUtility.driver.findElement(By.xpath("//span[text()='Ավարտել']"));
        finish.click();
    }

    @Test(priority = 2)
    public void register_to_partner() {
        TestUtility.driver.navigate().back();

        String[] partner = {"//div[@id='menuToggle']","//div[@id='menuToggle']","//*[@id='partnerThanks']/a"};
        for (int i = 0;i < partner.length; i++) {
            TestUtility.driver.findElement(By.xpath(partner[i])).click();
        }

        String[] array = {"//input[@id='partnerName']","//input[@id='partnerEmail']","//input[@id='partnerPhone']"};
        for (int i = 0;i < array.length; i++) {
            TestUtility.checkElementExistence(By.xpath(array[i]));
        }
        String[] data={"Aren","email","0999999"};
        for (int i = 0;i < data.length; i++){
            TestUtility.driver.findElement(By.xpath(array[i])).sendKeys(data[i]);
        }

        WebElement submit = TestUtility.driver.findElement(By.xpath("//*[@id='partnerSubmit']"));
        submit.click();

        //Checking the warning message
        TestUtility.checkElementExistence(By.xpath("//label[text()='Please provide a valid e-mail address.']"));
    }

    @Test(priority = 4)
    public void url_check() {
        int size = TestUtility.driver.findElements(By.xpath("//button[@class='close']")).size();
        WebElement close = TestUtility.driver.findElements(By.xpath("//button[@class='close']")).get(size-1);
        close.click();

        WebElement menuToggle = TestUtility.driver.findElement(By.xpath("//div[@id='menuToggle']"));
        menuToggle.click();

        String[] arrUrl = {"https://www.aca.am/hy/#headerwrap","https://www.aca.am/hy/#courses","https://www.aca.am/hy/#partners","https://www.aca.am/hy/#subscribe"};
        String[] menu = { "//a[@href='#headerwrap']","//a[@href='#courses']","//a[@href='#partners']","//a[@href='#subscribe']"};

        for (int i = 0;i < menu.length; i++) {
            TestUtility.driver.findElement(By.xpath(menu[i])).click();
            TestUtility.urlCheck(arrUrl[i]);
            String[]menuElements = {"//a[@href='#description']","//img[@class ='grad-ico']","//a[@href='#partnerbox']","//div[@class='info col-sm-4 col-xs-12 contact-box']"};
            for (int a = 0; a < menuElements.length; a++) {
                TestUtility.checkElementExistence(By.xpath(menuElements[a]));
            }
        }
    }

    @AfterTest
    public void quit() {
        TestUtility.driver.quit();
    }
}