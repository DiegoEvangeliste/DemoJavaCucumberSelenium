package pages;

public class FreeRangeTestLogin extends BasePage {

    private final String emailInput = "//input[@id='email']";
    private final String passwordInput = "//input[@id='password']";
    private final String loginButton = "//span[normalize-space()='Inicio de sesión']";

    public void writeInputText() {
        write(emailInput, "example@email.com");
        write(passwordInput, "password");
    }

    public void clickOnLoginButton() {
        clickElement(loginButton);
    }

}
