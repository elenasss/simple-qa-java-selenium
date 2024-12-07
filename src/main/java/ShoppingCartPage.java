import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage{

    @FindBy(className = "cart_list")
    WebElement cartList;

    @FindBy(className = "inventory_item_name")
    WebElement itemName;

    protected ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        return getWait().until(d -> cartList.isDisplayed());
    }

    public String getItemTitle(){
        return itemName.getText();
    }

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(getDriver(), this);
    }
}