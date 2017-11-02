package scripts.framework;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;


public class Script_Base {

    protected WebDriver driver = null;

    @Before
    public void setUp() {
//        String driverPath = System.getProperty("user.dir");
//        String webDriverExe = driverPath + "/drivers/32/FF/geckodriver.exe";
//        String webDriverExe = "C:\\MyDevelopment\\DevTools\\Webdriver\\32\\CH\\chromedriver.exe";
//        System.out.println("DriverPath: " + webDriverExe);
//
//
//        File file = new File(webDriverExe);
//        if(file.exists()){
//            //System.setProperty("webdriver.gecko.driver", webDriverExe);
//            //driver = new FirefoxDriver();
//
//            System.setProperty("webdriver.chrome.driver", webDriverExe);
//            driver = new ChromeDriver();
//
//        }
//        else{
//            throw new RuntimeException("Driver does not exist in " + webDriverExe);
//        }

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.lordandtaylor.com/");
        // driver.manage().window().maximize();

        // Newsletter
        WebElement newsletterPopup = null;
        try {
            newsletterPopup = getWhenVisible(By.cssSelector("#generic-modal"),5);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        }

        if(newsletterPopup != null) {
            WebElement popupClose = getWhenVisible(By.xpath(".//*[@id='close-button']"),5);
            popupClose.click();
            System.out.println("Continue script...");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


    // Use this to delay in 'x' amount of seconds

    public void delayFor(int timeinMilli) {
        try {
            Thread.sleep(timeinMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Use this to click the CheckBox if it's unchecked
//    public void unCheckCheckbox(WebElement element){
//        String checkBoxState = element.getAttribute("checked");
//        if((checkBoxState == null || checkBoxState.contentEquals("false"))){
//            element.click();
//        }
//        else if((checkBoxState != null && checkBoxState.contentEquals("true"))){
//            element.click();
//        }
//
//    }

    // Use this to click the CheckBox if it's checked
//    public void checkCheckbox(WebElement element){
//        String checkBoxState = element.getAttribute("checked");
//        if((checkBoxState == null || checkBoxState.contentEquals("true"))){
//            element.click();
//        }
//        else if((checkBoxState != null && checkBoxState.contentEquals("false"))){
//            element.click();
//        }
//
//    }

//    public void fluentWait(){
//        ExplicitWaitUtils wait = new ExplicitWaitUtils(driver);
//        wait.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rs-nav-link--minor")));
//    }


    // This is used fluentWait for specific element

    protected WebElement getWhenVisible(final By locator, int timeToWaitInSec) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement foo = wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element != null && element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        });
        return foo;
    }
}