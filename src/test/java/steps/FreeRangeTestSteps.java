package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FreeRangeTestLogin;
import pages.FreeRangeTestPrincipal;

public class FreeRangeTestSteps {

    FreeRangeTestPrincipal freeRangeTestPrincipal = new FreeRangeTestPrincipal();
    FreeRangeTestLogin freeRangeTestLogin = new FreeRangeTestLogin();

    @Given("I navigate to www.freerangetesters.com")
    public void iNavigateToFreeRangeTest() {
        freeRangeTestPrincipal.navigateToFreeRangeTest();
    }

    @When("I go to {word} using the navigation bar")
    // Con la indicacion de {word} le decimos a Cucumber que la palabra va a ser traida de la tabla de ejemplo de la feature
    public void navigationBarUse(String section) {
        freeRangeTestPrincipal.clickOnSectionNavigationBar(section);
    }

    @When("I click on button Entrar")
    public void clickOnButtonEntrar() {
        freeRangeTestPrincipal.clickOnLoginButton();
    }

    @And("I complete input text for login")
    public void completeInputTextForLogin() {
        freeRangeTestLogin.writeInputText();
    }

    @Then("I click on button Inicio de sesion")
    public void clickOnButtonInicioDeSesion() {
        freeRangeTestLogin.clickOnLoginButton();
    }

}