import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AcceptanceTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginIsSuccessful(){
        loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());

        inventoryPage = loginPage.login(USERNAME, PASSWORD);
        assertTrue(inventoryPage.isLoaded());
    }

    @Test(priority = 2)
    public void productIsAdded(){
        loginPage = new LoginPage(driver);
        loginPage.open();
        inventoryPage = loginPage.login(USERNAME, PASSWORD);
        inventoryPage.addToCart();
        assertTrue(inventoryPage.productIsAdded());

        ShoppingCartPage shoppingCartPage = inventoryPage.openShoppingCartPage();
        assertTrue(shoppingCartPage.isLoaded());
        assertEquals(shoppingCartPage.getItemTitle(), inventoryPage.getItemTitle());
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}