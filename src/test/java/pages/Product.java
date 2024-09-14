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
    By productSelector = By.cssSelector("div[class=\"p-card-chldrn-cntnr card-border\"] > a");
    List<WebElement> products = BaseTest.driver.findElements(productSelector);

    public void addToCart(){
        for (WebElement product : products){
            errorControl.elementControl(productSelector);

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
