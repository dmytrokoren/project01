package scripts.testing;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import scripts.framework.Script_Base;
import scripts.framework.WebElementUtils;

/**
 * Created by dkorenkov on 11/2/2017.
 */
public class practiceHere extends Script_Base {

    @Test
    public void accountRegistration() {

        WebElement accountLinkMenu = driver.findElement(By.cssSelector(".menu-item .account"));
        WebElementUtils.getInstance().mouseHoverJScript(driver,accountLinkMenu);

        WebElement signinBtn = getWhenVisible(By.cssSelector(".menu-item .first-child.last-child>a"),5);
        signinBtn.click();

        WebElement emailTextbox = driver.findElement(By.cssSelector("#email-sign-in"));
        emailTextbox.sendKeys("dmytro@gmail.com");

        delayFor(5000);

//        WebElement emailTextbox = driver.findElement(By.cssSelector("#email-sign-in"));
//        emailTextbox.sendKeys("dmytro@gmail.com");
//
//        WebElement passwordTextbox = driver.findElement(By.cssSelector("#login-password-field-sign-in"));
//        passwordTextbox.sendKeys("shifted321");
//
//        driver.findElement(By.xpath("//*[@id='social-signon-form-sign-in']/div[4]/button")).click();
//
//        // Checkpoint
//        WebElement invalidEmail = getWhenVisible(By.cssSelector(".error-summary"),5);
//        String invalidEmailText = invalidEmail.getText();
//        Assert.assertEquals("We didn't find an account with this email address.", invalidEmailText);
    }

}
