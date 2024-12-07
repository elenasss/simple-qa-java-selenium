import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage implements Page{
    private final WebDriver driver;
    private final WebDriverWait wait;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        init(getDriver());
    }

    protected void fillText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait(){
        return wait;
    }
}