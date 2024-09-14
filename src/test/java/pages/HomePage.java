package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import tests.BaseTest;

public class HomePage {
    ErrorControl errorControl = new ErrorControl();

    By productNamePath = By.cssSelector("input[data-testid=\"suggestion\"]");
    By notification = By.cssSelector("a[id=\"rejectAllButton\"]");

    public void notifications (){
        errorControl.elementControl(notification);
        BaseTest.driver.findElement(notification).click();
    }

    public void productSearch(String productName){
        errorControl.elementControl(productNamePath);
        BaseTest.driver.findElement(productNamePath).sendKeys(productName, Keys.ENTER);
    }
}