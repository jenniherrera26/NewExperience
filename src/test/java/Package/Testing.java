package Package;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Testing{
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/chromedriver/chromedriver.exe").toString());
        if (driver == null) {
            driver = new ChromeDriver();
        }
    }
    @After
    public void tearDown() {
        if (driver!=null) {
            driver.close();
            driver.quit();
        }
    }

    @Given("Navegar a la pagina de olvidar contrasena")
    public void navigateToPageForgotPassword() {
        driver.navigate().to("http://automationpractice.com/index.php?controller=password");
    }

    @When("Un usuario ingresa un correo valido")
    public void aUserEntersAValidEmailId() {
        driver.findElement(By.id("email")).sendKeys("validemail@gmail.com");
    }

    @And("Un usuario hace click en Retrieve Password")
    public void aUserClicksOnRetrievePasswordButton() {
        driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button")).click();
    }

    @When("Un usuario ingresa un correo invalido")
    public void aUserEntersAInvalidEmailId() {
        driver.findElement(By.id("email")).sendKeys("invalid@");
    }

    @Then("La aplicacion muestra que el correo se envio.")
    public void applicationShowsThatTheEmailHasBeenSent() {
        String actualMessage = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/p")).getText();
        assertThat(actualMessage.trim(), is("A confirmation email has been sent to your address: validemail@gmail.com"));
    }

    @Then("La aplicacion muestra que hubo un error.")
    public void applicationDoesNotShowThatEmailHasBeenSent() {
        String actualMessage = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/ol/li")).getText();
        assertThat(actualMessage.trim(), is("Invalid email address."));
    }

}

