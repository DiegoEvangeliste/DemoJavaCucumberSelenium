package pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@NoArgsConstructor
public class BasePage {

    protected static WebDriver pageDriver;

    static {
        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");  //Habilitamos la relacion de geckodriver para que Selenium utilice Firefox

        FirefoxOptions firefoxOptions = new FirefoxOptions()
                .addPreference("remote.active-protocols", 3)    //Definimos los protocolos activos (HTTP/1.1 y HTTP/2)
                .setAcceptInsecureCerts(true);  //Habilitamos los certificados SSL/TLS inseguros para Firefox (Solo hacer para pruebas de entorno)

        pageDriver = new FirefoxDriver(firefoxOptions);
    }

    WebDriverWait wait = new WebDriverWait(pageDriver, Duration.ofSeconds(5));

    //Método estático para navegar a una URL.
    public static void navigateTo(String url) {
        pageDriver.get(url);
    }

    //Método estático para cerrar la instancia del driver
    public static void closeBrowser() {
        pageDriver.quit();
    }


    // Encuentra y devuelve un WebElement en la página utilizando un locator XPath, esperando a que esté presentente en el DOM
    private WebElement Find(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator) {
        Find(locator).click();
    }

    public void write(String locator, String keysToSend) {
        Find(locator).clear();  // Borramos (si tiene) el texto que tenga el input
        Find(locator).sendKeys(keysToSend);
    }

/*
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByValue(value);
    }

    public void selectFromDropdownByIndex(String locator, Integer index){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByIndex(index);
    }

    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();
    }

    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(Find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }

        return values;

    }
*/

}
