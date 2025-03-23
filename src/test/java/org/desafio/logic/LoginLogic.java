package org.desafio.logic;

import lombok.extern.log4j.Log4j2;
import org.desafio.utils.Utilities;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginLogic {
    private String step = "";
    private WebDriver driver;
    private Utilities utilities;

    public LoginLogic() {
        utilities = new Utilities();
    }

    public void navigateTo(String url,WebDriver driver) throws InterruptedException {
        String step = "Navigate to URL "+ url;
        log.info(step);
        driver.get(url);
        driver.manage().window().maximize();
    }
    public void validateTitle(String expectedTitle, WebDriver driver) {
        String step = "Validate title";
        log.info(step);
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            log.info("Title is correct");
            utilities.takeScreenshot(driver, step + ".png");
        } else {
            log.error("Title is incorrect");
            utilities.takeScreenshot(driver, step + ".png");
            Assert.fail("Title is incorrect");
        }
    }

    public void validateText(String expectedText, WebDriver driver) {
        String step = "Validate text";
        log.info(step);
        boolean isTextPresent = driver.getPageSource().contains(expectedText);
        if (isTextPresent) {
            log.info("Text is present");
            utilities.takeScreenshot(driver, step + ".png");
        } else {
            log.error("Text is not present");
            utilities.takeScreenshot(driver, step + ".png");
            Assert.fail("Text is not present");
        }
    }
}
