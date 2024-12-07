import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage{

    private String itemTitle;

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".inventory_item:first-of-type .inventory_item_name")
    private WebElement itemName;

    @FindBy(css = ".inventory_item:first-of-type .btn_primary")
    private WebElement addToCart;

    @FindBy(className = "shopping_cart_badge")
    @CacheLookup
    private WebElement shoppingCart;

    protected InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        return getWait().until(d -> inventoryList.isDisplayed());
    }

    public void addToCart(){
        itemTitle = itemName.getText();
        addToCart.click();
    }

    public boolean productIsAdded(){
        return shoppingCart.getText().equals("1");
    }

    public ShoppingCartPage openShoppingCartPage(){
        shoppingCart.click();
        return new ShoppingCartPage(getDriver());
    }

    public String getItemTitle(){
        return itemTitle;
    }

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(getDriver(), this);
    }
}