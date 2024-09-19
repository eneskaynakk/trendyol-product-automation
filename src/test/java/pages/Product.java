package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

import java.util.List;
import java.util.Set;

public class Product {
    ErrorControl errorControl = new ErrorControl();

    By addToCart = By.cssSelector("button[component-id=\"1\"] > div[class=\"add-to-basket-button-text\"]");
    String cart = "https://www.trendyol.com/sepet";
    By productSelector = By.cssSelector(".p-card-chldrn-cntnr.card-border");
    List<WebElement> products = BaseTest.driver.findElements(productSelector);

    public void addToCart(){
        BaseTest.driver.navigate().refresh();

        for (int i = 0; i < products.size(); i++){
            List<WebElement> updatedProducts = BaseTest.driver.findElements(productSelector);

            errorControl.elementControl(productSelector);
            updatedProducts.get(i).click();

            String originalWindow = BaseTest.driver.getWindowHandle();
            Set<String> allWindows = BaseTest.driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    BaseTest.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            errorControl.elementControl(addToCart);
            BaseTest.driver.findElement(addToCart).click();

            BaseTest.driver.close();
            BaseTest.driver.switchTo().window(originalWindow);
        }

        BaseTest.driver.navigate().to(cart);
    }
}
