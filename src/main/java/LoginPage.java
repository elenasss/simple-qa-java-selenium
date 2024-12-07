import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    private static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    @CacheLookup
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        getDriver().get(URL);
    }

    public InventoryPage login(String username, String password){
        fillText(usernameField, username);
        fillText(this.passwordField, password);
        loginButton.click();
        return new InventoryPage(getDriver());
    }

    public boolean isLoaded(){
        return usernameField.isDisplayed();
    }

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(getDriver(), this);
    }
}