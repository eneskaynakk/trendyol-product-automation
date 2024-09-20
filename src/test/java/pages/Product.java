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
    By product = By.cssSelector(".p-card-chldrn-cntnr.card-border");
    By notification = By.className("onboarding-popover__default-renderer-primary-button");
    List<WebElement> products = BaseTest.driver.findElements(product);

    public void addToCart(){
        BaseTest.driver.navigate().refresh();

        for (int i = 0; i < products.size(); i++){
            List<WebElement> updatedProducts = BaseTest.driver.findElements(product);

            errorControl.elementControl(product);
            updatedProducts.get(i).click();

            String originalWindow = BaseTest.driver.getWindowHandle();
            Set<String> allWindows = BaseTest.driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    BaseTest.driver.switchTo().window(windowHandle);
                    if(i==0){
                        errorControl.elementControl(notification);
                        BaseTest.driver.findElement(notification).click();
                    }
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
