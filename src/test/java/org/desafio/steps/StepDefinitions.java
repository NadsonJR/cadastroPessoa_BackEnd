package org.desafio.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.desafio.logic.LoginLogic;
import org.desafio.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Log4j2
public class StepDefinitions {

    private WebDriver driver;
    private LoginLogic loginLogic;
    private Utilities utilities;

    @Before
    public void setUp() {
        utilities = new Utilities();
        loginLogic = new LoginLogic();
        String edgeDriverPath = "C:\\edgedriver_win64\\msedgedriver.exe"; // Caminho correto para o executável
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
        driver = new EdgeDriver();
    }

    @Given("I open the home page")
    public void i_open_the_home_page() throws InterruptedException {
        loginLogic.navigateTo("http://localhost:3000", driver);
    }
    @Then("I should see the title {string}")
    public void i_should_see_the_title(String expectedTitle) {
        loginLogic.validateTitle(expectedTitle, driver);
    }

    @Then("I should see the text {string}")
    public void i_should_see_the_text(String string) {
        loginLogic.validateText(string, driver);
        utilities.generateDocument();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
