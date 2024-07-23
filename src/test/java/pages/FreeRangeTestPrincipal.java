package pages;

public class FreeRangeTestPrincipal extends BasePage {

    private final String sectionLink = "//a[normalize-space()='%s' and @href]";
    private final String loginButton = "//a[@class='sc-eeDSqt gLwFuo']";

    public void navigateToFreeRangeTest() {
        navigateTo("https://www.freerangetesters.com");
    }

    public void clickOnSectionNavigationBar(String section) {
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    }

    public void clickOnLoginButton() {
        clickElement(loginButton);
    }

}
